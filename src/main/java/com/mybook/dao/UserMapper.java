package com.mybook.dao;

import com.mybook.base.BaseMapper;
import com.mybook.vo.User;

public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByUid(Integer uId);
}