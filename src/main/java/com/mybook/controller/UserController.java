package com.mybook.controller;

import com.mybook.base.BaseController;
import com.mybook.base.ResultInfo;
import com.mybook.model.UserModel;
import com.mybook.query.UserQuery;
import com.mybook.service.UserService;
import com.mybook.utils.LoginUserUtil;
import com.mybook.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    /**
     * 多条件查询⽤户数据
     */
    @RequestMapping("user/list")
    @ResponseBody
    public Map<String, Object> queryUserByParams(UserQuery userQuery) {
        return userService.queryUserByParams(userQuery);
    }

    /**
     * 用户添加
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(User user){
        userService.addUser(user);
        return success("用户添加成功");
    }

    /**
     * 用户修改
     */
    @PostMapping("updateUser")
    @ResponseBody
    public ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户修改成功");
    }

    /**
     * 用户删除
     */
    @DeleteMapping("deleteUser")
    @ResponseBody
    public ResultInfo deleteUser(Integer uId){
        userService.deleteUser(uId);
        return success("用户删除成功");
    }

}

