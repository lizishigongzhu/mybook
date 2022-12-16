package com.mybook.query;

import com.mybook.base.BaseQuery;

public class AdminQuery extends BaseQuery {
    //管理员姓名
    private String uName;
    //管理员电话
    private String uPhone;
    //管理员邮箱
    private String uEmil;
    //管理员部门/专业
    private String uProfession;

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
