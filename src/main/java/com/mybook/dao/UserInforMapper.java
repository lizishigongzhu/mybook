package com.mybook.dao;

import java.util.List;
import java.util.Map;

public interface UserInforMapper<MsAdmin> {


    /**
     * 登录(Map 放入登录号和密码)
     */
    MsAdmin selectAdmin(Map<String,String> map);


    /**
     * 查询总数
     * @return
     */
    int selectCount();

    /**
     * 分页查询
     */
    List<MsAdmin> selectByPage(Map<String,Object> map);

}
