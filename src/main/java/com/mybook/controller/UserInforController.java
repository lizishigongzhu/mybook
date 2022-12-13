package com.mybook.controller;

import com.mybook.base.BaseController;
import com.mybook.base.ResultInfo;
import com.mybook.service.UserInforService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class UserInforController extends BaseController {

    /**
     *查询⽤户信息
     */
    @RequestMapping("user/list")
    @ResponseBody
    public Map<String, Object> getUserInfor(UserQuery userQuery) {
        return userService.queryUserByParams(userQuery);
    }


    /**
     * 添加⽤户
     */
    @RequestMapping("user/save")
    @ResponseBody
    public ResultInfo addUserInfor(User user) {
        UserInforService.addUserInfor(user);
        return success("⽤户添加成功！");
    }

    /**
     * 删除⽤户
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteUserInfor (Integer[] ids){
        UserInforService.deleteBatch(ids);
        return success("⽤户记录删除成功");
    }
}