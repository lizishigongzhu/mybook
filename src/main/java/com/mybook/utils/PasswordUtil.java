package com.mybook.utils;
/*检验密码工具类*/
public class PasswordUtil {

    public static boolean isPassword(String password) {

        /*密码不能小于6位*/
        //密码为空及长度8-12位判断
        if (password.length() <= 6 || password.length() >= 12) {
            return true;
        }

        return false;

    }

}
