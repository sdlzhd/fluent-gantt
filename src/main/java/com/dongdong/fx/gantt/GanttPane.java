package com.dongdong.fx.gantt;

import com.dongdong.fx.gantt.skin.GanttPaneSkin;
import com.sun.javafx.css.converters.EnumConverter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.text.Font;

import java.util.Date;

public class GanttPane<T> extends Control {

    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    public GanttPane() {
        this.items = FXCollections.observableArrayList();
    }

    public GanttPane(ObservableList<T> items) {
        this.items = items;
    }

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    /**
     * 甘特图中所有的行
     */
    private final ObservableList<GanttRow<T>> rows = FXCollections.observableArrayList();

    /**
     * 甘特图中的实体对象列表
     */
    private final ObservableList<T> items;

    /**
     * Dock栏的显示位置，支持的显示位置见{@link DockPos}
     */
    private ObjectProperty<DockPos> dockPosition;

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

    public ObservableList<GanttRow<T>> getRows() {
        return rows;
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
            startTime = new SimpleObjectProperty<Date>();
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
            endTime = new SimpleObjectProperty<Date>();
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

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GanttPaneSkin<T>(this);
    }

    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/



    private static final CssMetaData<GanttPane, DockPos> DOCK_POS =
            new CssMetaData<GanttPane, DockPos>("-fx-dock-position",
                    new EnumConverter<DockPos>(DockPos.class), DockPos.LEFT) {

                @Override
                public boolean isSettable(GanttPane n) {
                    return n.dockPosition == null || !n.dockPosition.isBound();
                }

                @SuppressWarnings("unchecked")
                @Override
                public StyleableProperty<DockPos> getStyleableProperty(GanttPane n) {
                    return (StyleableProperty<DockPos>) n.dockPositionProperty();
                }
            };

    private static final CssMetaData<GanttPane, Boolean> TIMELINE_VISIBLE =
            new CssMetaData<GanttPane, Boolean>("-fx-timeline-visible",
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
