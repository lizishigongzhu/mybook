package com.mybook.model;

public class UserModel {
    private String uIdStr;// 加密后的用户ID

    private String uName;

    public String getuIdStr() {
        return uIdStr;
    }

    public void setuIdStr(String uIdStr) {
        this.uIdStr = uIdStr;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
}

