package com.mybook.query;

import com.mybook.base.BaseQuery;

public class UserInforQuery extends BaseQuery {
    // ⽤户id
    private Integer u_id;
    // 用户名
    private String u_name;
    //用户身份
    private Integer c_id;
    //用户密码
    private String u_pwd;
    //用户手机
    private Integer u_phone;
    //用户邮箱
    private String u_email;
    //用户头像
    private String u_head;
    //用户专业
    private String u_profession;

    public UserInforQuery() {
    }

    public UserInforQuery(Integer u_id, String u_name, Integer c_id, String u_pwd,
                          Integer u_phone, String u_email, String u_head, String u_profession) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.c_id = c_id;
        this.u_pwd = u_pwd;
        this.u_phone = u_phone;
        this.u_email = u_email;
        this.u_head = u_head;
        this.u_profession = u_profession;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public Integer getU_phone() {
        return u_phone;
    }

    public void setU_phone(Integer u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_head() {
        return u_head;
    }

    public void setU_head(String u_head) {
        this.u_head = u_head;
    }

    public String getU_profession() {
        return u_profession;
    }

    public void setU_profession(String u_profession) {
        this.u_profession = u_profession;
    }
}
