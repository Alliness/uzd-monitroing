package com.alliness.uzd.web.dto;

import com.alliness.uzd.core.Serializable;

public class TrainRequestData extends Serializable {

    private int    from;
    private int    to;
    private String date;
    private String dateUntil;
    private String time;

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDateUntil() {
        return dateUntil;
    }
}
