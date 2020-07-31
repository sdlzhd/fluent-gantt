package com.dongdong.fx.gantt;

import java.io.Serializable;
import java.util.Date;

public interface NodeBase extends Serializable {

    Date getStartDate();

    Date getEndDate();

}
