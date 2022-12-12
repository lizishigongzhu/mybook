package com.mybook.enums;

public enum State {
    //在库
    STOCK(1),
    //借出
    LEND(2),
    //丢失
    LOSE(3);


    private  Integer status;

    State(Integer status) {
        this.status = status;
    }
}
