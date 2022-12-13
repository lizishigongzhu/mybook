package com.mybook.vo;

public class Book {
    private Integer bId;

    private String bName;

    private String bAuthor;

    private String cbId;

    private Integer bState;

    private String bPresshouse;

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName == null ? null : bName.trim();
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor == null ? null : bAuthor.trim();
    }

    public String getCbId() {
        return cbId;
    }

    public void setCbId(String cbId) {
        this.cbId = cbId == null ? null : cbId.trim();
    }

    public Integer getbState() {
        return bState;
    }

    public void setbState(Integer bState) {
        this.bState = bState;
    }

    public String getbPresshouse() {
        return bPresshouse;
    }

    public void setbPresshouse(String bPresshouse) {
        this.bPresshouse = bPresshouse == null ? null : bPresshouse.trim();
    }
}