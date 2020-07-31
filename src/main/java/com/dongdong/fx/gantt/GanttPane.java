package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.skin.GanttPaneSkin;
import com.sun.javafx.css.converters.EnumConverter;
import com.sun.javafx.css.converters.SizeConverter;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.*;

/**
 * 甘特图面板
 * @param <R> 甘特行的实体类
 * @param <T> 甘特节点的实体类
 */
public class GanttPane<R extends RowBase<T>, T extends NodeBase> extends Control {

    private static <R extends RowBase<T>, T extends NodeBase> Callback<GanttRow<R, T>, DockButton<R>> defaultDockFactory() {
        return DockButton::new;
    }

    private static Date today() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static Date nextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    public GanttPane() {
        this(FXCollections.observableArrayList());
    }

    public GanttPane(ObservableList<T> items) {
        this.items = items;
        this.rows.addListener(rowsListener);
        this.items.addListener(itemsListener);
    }

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    private final Map<R, List<T>> rowItemsMap = new HashMap<>();

    List<T> getItems(R row) {
        return rowItemsMap.get(row);
    }

    /**
     * 甘特图中所有的行
     */
    private final ObservableList<R> rows = FXCollections.observableArrayList();

    private final ObjectProperty<Callback<GanttRow<R, T>, DockButton<R>>> dockFactory =
            new SimpleObjectProperty<>(this, "dockFactory", defaultDockFactory());
    public final ObjectProperty<Callback<GanttRow<R, T>, DockButton<R>>> dockFactoryProperty() {
        return dockFactory;
    }
    public final void setDockFactory(Callback<GanttRow<R, T>, DockButton<R>> value) {
        if (value == null) {
            throw new IllegalArgumentException("The RowFactory is not allowed to be null");
        }
        dockFactoryProperty().set(value);
    }
    public final Callback<GanttRow<R, T>, DockButton<R>> getDockFactory() {
        return dockFactory.get();
    }

    /**
     * 甘特图中的实体对象列表
     */
    private final ObservableList<T> items;

    /**
     * 甘特图界面的标题，显示在DockButton的顶端
     */
    private final StringProperty title = new SimpleStringProperty(this, "title");
    public final StringProperty titleProperty() {
        return title;
    }
    public final String getTitle() {
        return titleProperty().getValue();
    }
    public final void setTitle(String value) {
        titleProperty().setValue(value);
    }


    private final ObjectProperty<Callback<T, R>> valueBindFactory = new SimpleObjectProperty<>(this, "valueBindFactory");
    public ObjectProperty<Callback<T, R>> valueBindFactoryProperty() {
        return valueBindFactory;
    }
    public Callback<T, R> getValueBindFactory() {
        return valueBindFactoryProperty().getValue();
    }
    public void setValueBindFactory(Callback<T, R> factory) {
        valueBindFactory.setValue(factory);
    }


    /**
     * Dock栏的显示位置，支持的显示位置见{@link DockPos}
     */
    private ObjectProperty<DockPos> dockPosition;

    /**
     * 甘特行高度
     */
    private DoubleProperty rowHeight;

    /**
     * 是否显示时间线
     */
    private BooleanProperty timelineVisible;

    /**
     * 时间轴的开始时间
     */
    private ObjectProperty<Date> startTime;
    /**
     * 时间轴的结束时间
     */
    private ObjectProperty<Date> endTime;

    /**
     * 鼠标指针的十字准星
     */
    private BooleanProperty showCrosshair;

    public ObservableList<T> getItems() {
        return items;
    }

    public ObservableList<R> getRows() {
        return rows;
    }

    public DoubleProperty rowHeightProperty() {
        if (rowHeight == null) {
            rowHeight = new StyleableDoubleProperty(42.0d) {

                @Override
                public CssMetaData<GanttPane<?, ?>,Number> getCssMetaData() {
                    return ROW_HEIGHT;
                }

                @Override
                public Object getBean() {
                    return GanttPane.this;
                }

                @Override
                public String getName() {
                    return "rowHeight";
                }
            };
        }
        return rowHeight;
    }

    public double getRowHeight() {
        return rowHeightProperty().get();
    }

    public void setRowHeight(double rowHeight) {
        this.rowHeightProperty().set(rowHeight);
    }

    public ObjectProperty<DockPos> dockPositionProperty() {
        if (dockPosition == null) {
            dockPosition = new StyleableObjectProperty<DockPos>(DockPos.LEFT) {
                public CssMetaData<? extends Styleable, DockPos> getCssMetaData() {
                    return DOCK_POS;
                }

                public Object getBean() {
                    return GanttPane.this;
                }

                public String getName() {
                    return "dock-position";
                }
            };
        }
        return dockPosition;
    }

    public void setDockPosition(DockPos dockPos) {
        dockPositionProperty().setValue(dockPos);
    }

    public DockPos getDockPosition() {
        return dockPositionProperty().getValue();
    }

    public BooleanProperty timelineVisibleProperty() {
        if (timelineVisible == null) {
            timelineVisible = new StyleableBooleanProperty(true) {
                public Object getBean() {
                    return GanttPane.this;
                }

                public String getName() {
                    return "timelineVisible";
                }

                public CssMetaData<? extends Styleable, Boolean> getCssMetaData() {
                    return TIMELINE_VISIBLE;
                }
            };
        }
        return timelineVisible;
    }

    public void setTimelineVisible(boolean visible) {
        timelineVisibleProperty().setValue(visible);
    }

    public boolean isTimelineVisible() {
        return timelineVisibleProperty().getValue();
    }

    public ObjectProperty<Date> startTimeProperty() {
        if (startTime == null) {
            startTime = new SimpleObjectProperty<>(this, "startTime", today());
        }
        return startTime;
    }

    public void setStartTime(Date date) {
        startTimeProperty().setValue(date);
    }

    public Date getStartTime() {
        return startTimeProperty().getValue();
    }

    public ObjectProperty<Date> endTimeProperty() {
        if (endTime == null) {
            endTime = new SimpleObjectProperty<>(this, "endTime", nextDay());
        }
        return endTime;
    }

    public void setEndTime(Date date) {
        endTimeProperty().setValue(date);
    }

    public Date getEndTime() {
        return endTimeProperty().getValue();
    }

    public BooleanProperty showCrosshair() {
        if (showCrosshair == null) {
            showCrosshair = new SimpleBooleanProperty(true);
        }
        return showCrosshair;
    }

    public void setShowCrosshair(boolean show) {
        showCrosshair().set(show);
    }

    public boolean isShowCrosshair() {
        return showCrosshair().get();
    }

    /***************************************************************************
     *                                                                         *
     * Callbacks and Events                                                    *
     *                                                                         *
     **************************************************************************/

    private final ListChangeListener<T> itemsListener = c -> {
        while (c.next()) {
            if (c.wasRemoved()) {
                for (T t : c.getRemoved()) {
                    R row = getValueBindFactory().call(t);
                    if (rowItemsMap.get(row) != null) {
                        rowItemsMap.get(row).remove(t);
                    }
                }
            }

            if (c.wasAdded()) {
                for (T t : c.getAddedSubList()) {
                    R row = getValueBindFactory().call(t);
                    if (rowItemsMap.get(row) != null) {
                        if (!rowItemsMap.get(row).contains(t)) {
                            rowItemsMap.get(row).add(t);
                        }
                    } else {
                        rowItemsMap.put(row, Arrays.asList(t));
                    }
                }
            }
        }
    };

    private final ListChangeListener<R> rowsListener = c -> {
        while (c.next()) {
            if (c.wasRemoved()) {
                for (R row : c.getRemoved()) {
                    rowItemsMap.remove(row);
                }
            }
            if (c.wasAdded()) {
                for (R row : c.getAddedSubList()) {
                    rowItemsMap.putIfAbsent(row, new ArrayList<>());
                }
            }
        }
    };


    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GanttPaneSkin<>(this);
    }

    private static final CssMetaData<GanttPane<?, ?>,Number> ROW_HEIGHT =
            new CssMetaData<GanttPane<?, ?>,Number>("-fx-row-height",
                    SizeConverter.getInstance(), 42.0d) {

                @Override
                public boolean isSettable(GanttPane<?, ?> ganttPane) {
                    return ganttPane.rowHeight == null || !ganttPane.rowHeight.isBound();
                }

                @SuppressWarnings("unchecked")
                @Override
                public StyleableProperty<Number> getStyleableProperty(GanttPane ganttPane) {
                    return (StyleableProperty<Number>)ganttPane.rowHeightProperty();
                }
            };

    private static final CssMetaData<GanttPane<?, ?>, DockPos> DOCK_POS =
            new CssMetaData<GanttPane<?, ?>, DockPos>("-fx-dock-position",
                    new EnumConverter<>(DockPos.class), DockPos.LEFT) {

                @Override
                public boolean isSettable(GanttPane<?, ?> n) {
                    return n.dockPosition == null || !n.dockPosition.isBound();
                }

                @SuppressWarnings("unchecked")
                @Override
                public StyleableProperty<DockPos> getStyleableProperty(GanttPane<?, ?> n) {
                    return (StyleableProperty<DockPos>) n.dockPositionProperty();
                }
            };

    private static final CssMetaData<GanttPane<?, ?>, Boolean> TIMELINE_VISIBLE =
            new CssMetaData<GanttPane<?, ?>, Boolean>("-fx-timeline-visible",
                    new StyleConverter<String, Boolean>() {
                        @Override
                        public Boolean convert(ParsedValue<String, Boolean> value, Font font) {
                            final String sval = value != null ? value.getValue() : null;
                            return "visible".equalsIgnoreCase(sval);
                        }

                    }, Boolean.TRUE) {
                @Override
                public boolean isSettable(GanttPane styleable) {
                    return styleable.timelineVisible == null || !styleable.timelineVisible.isBound();
                }

                @SuppressWarnings("unchecked")
                @Override
                public StyleableProperty<Boolean> getStyleableProperty(GanttPane styleable) {
                    return (StyleableProperty<Boolean>) styleable.timelineVisibleProperty();
                }
            };
}
