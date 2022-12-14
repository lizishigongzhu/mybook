package com.mybook.query;

import com.mybook.base.BaseQuery;

public class UserQuery extends BaseQuery {

    private String uName;  //姓名

    private String uPhone;  //电话

    private String uEmil;  //邮箱

    private String uProfession;  //专业

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuEmil() {
        return uEmil;
    }

    public void setuEmil(String uEmil) {
        this.uEmil = uEmil;
    }

    public String getuProfession() {
        return uProfession;
    }

    public void setuProfession(String uProfession) {
        this.uProfession = uProfession;
    }
}
