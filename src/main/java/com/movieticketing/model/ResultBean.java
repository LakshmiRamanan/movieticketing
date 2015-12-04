package com.movieticketing.model;

import java.util.List;

/**
 * Created by nagal_000 on 12/2/2015.
 */
public class ResultBean {

    List rows;
    int  status;
    String exception;

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
