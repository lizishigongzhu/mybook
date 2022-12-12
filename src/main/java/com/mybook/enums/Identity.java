package com.mybook.enums;

public enum Identity {
    //老师身份
    TEACHER(1),
    //学生身份
    STUDENT(2),
    //管理员身份
    ADMIN(3);
 private Integer density;

    Identity(Integer density) {
        this.density = density;
    }
}
