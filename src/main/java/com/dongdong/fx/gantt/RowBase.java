package com.dongdong.fx.gantt;

import java.io.Serializable;
import java.util.List;

public interface RowBase<T> extends Serializable {

    String getTitle();

    String getTipText();

    List<T> getChildren();

}
