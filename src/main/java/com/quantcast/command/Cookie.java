package com.quantcast.command;

import java.util.Date;

public class Cookie {

    private String cookieName;
    private Date date;

    public String getCookieName() {
        return cookieName;
    }

    public Date getDate() {
        return date;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
