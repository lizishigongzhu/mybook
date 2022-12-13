package com.mybook.service;

import com.mybook.base.BaseService;
import com.mybook.dao.UserMapper;
import com.mybook.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    private UserMapper userMapper;
    //查询用户信息
    public User queryUserByUid(Integer uId){
        return userMapper.queryUserByUid(uId);
    }


}
