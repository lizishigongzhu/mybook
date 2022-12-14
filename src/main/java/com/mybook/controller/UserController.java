package com.mybook.controller;

import com.mybook.base.ResultInfo;
import com.mybook.query.UserQuery;
import com.mybook.service.UserService;
import com.mybook.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 进⼊⽤户⻚⾯
     */
    @RequestMapping("user/index")
    public String index(){
        return "user/user";
    }


    /**
     *查询⽤户数据
     */
    @RequestMapping("user/list")
    @ResponseBody
    public Map<String, Object> selectUserInfo(UserQuery userQuery) {
        return userService.selectUserInfo(userQuery);
    }

    /**
     * 添加⽤户
     */
    @RequestMapping("user/save")
    @ResponseBody
    public ResultInfo saveUser(User user) {
        userService.saveUser(user);
        return saveUser(用户添加成功);
    }



    /**
     * 更新⽤户
     */
    @RequestMapping("user/update")
    @ResponseBody
    public ResultInfo updateUser(User user) {
        userService.updateUser(user);
        return success("⽤户更新成功！");
    }

    /**
     * 删除⽤户
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteBatch(ids);
        return success("⽤户记录删除成功");
    }
}
