package com.mybook.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserInformation {
    private Integer u_id;  //用户id

    private String u_name;  //用户名

    private Integer c_id;  //用户身份

    private String u_Pwd;  //用户密码

    private String u_phone;  //用户手机

    private String u_email;  //用户邮箱

    private String u_profession;  //用户专业

    private Integer b_id;  //书本id

    private String b_name;  //书本名

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date r_borrowtime;  //借出时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date r_returntime;  //归还时间

    public UserInformation() {
    }

    public UserInformation(Integer u_id, String u_name, Integer c_id, String u_Pwd, String u_phone,
                           String u_email, String u_profession, Integer b_id, String b_name, Date r_borrowtime, Date r_returntime) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.c_id = c_id;
        this.u_Pwd = u_Pwd;
        this.u_phone = u_phone;
        this.u_email = u_email;
        this.u_profession = u_profession;
        this.b_id = b_id;
        this.b_name = b_name;
        this.r_borrowtime = r_borrowtime;
        this.r_returntime = r_returntime;
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

    public String getU_Pwd() {
        return u_Pwd;
    }

    public void setU_Pwd(String u_Pwd) {
        this.u_Pwd = u_Pwd;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_profession() {
        return u_profession;
    }

    public void setU_profession(String u_profession) {
        this.u_profession = u_profession;
    }

    public Integer getB_id() {
        return b_id;
    }

    public void setB_id(Integer b_id) {
        this.b_id = b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public Date getR_borrowtime() {
        return r_borrowtime;
    }

    public void setR_borrowtime(Date r_borrowtime) {
        this.r_borrowtime = r_borrowtime;
    }

    public Date getR_returntime() {
        return r_returntime;
    }

    public void setR_returntime(Date r_returntime) {
        this.r_returntime = r_returntime;
    }
}
