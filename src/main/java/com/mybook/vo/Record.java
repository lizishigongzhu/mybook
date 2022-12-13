package com.mybook.vo;

import java.util.Date;

public class Record {
    private Integer rId;

    private Integer uId;

    private Integer bId;

    private Date rBorrowtime;

    private Date rReturntime;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public Date getrBorrowtime() {
        return rBorrowtime;
    }

    public void setrBorrowtime(Date rBorrowtime) {
        this.rBorrowtime = rBorrowtime;
    }

    public Date getrReturntime() {
        return rReturntime;
    }

    public void setrReturntime(Date rReturntime) {
        this.rReturntime = rReturntime;
    }
}