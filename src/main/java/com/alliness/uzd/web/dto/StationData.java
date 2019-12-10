package com.alliness.uzd.web.dto;

import com.alliness.uzd.core.Serializable;

public class StationData extends Serializable {

    protected int value;
    protected String title;

    public int getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
}
