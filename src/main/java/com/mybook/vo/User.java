package com.mybook.vo;

public class User {
    private Integer uId;

    private String uName;

    private String uPwd;

    private Integer cId;

    private String uPhone;

    private String uEmil;

    private String uProfession;

    private String uHead;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd == null ? null : uPwd.trim();
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone == null ? null : uPhone.trim();
    }

    public String getuEmil() {
        return uEmil;
    }

    public void setuEmil(String uEmil) {
        this.uEmil = uEmil == null ? null : uEmil.trim();
    }

    public String getuProfession() {
        return uProfession;
    }

    public void setuProfession(String uProfession) {
        this.uProfession = uProfession == null ? null : uProfession.trim();
    }

    public String getuHead() {
        return uHead;
    }

    public void setuHead(String uHead) {
        this.uHead = uHead == null ? null : uHead.trim();
    }
}