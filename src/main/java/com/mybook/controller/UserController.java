package com.mybook.controller;

import com.mybook.base.BaseController;
import com.mybook.base.ResultInfo;
import com.mybook.model.UserModel;
import com.mybook.query.UserQuery;
import com.mybook.sevice.UserService;
import com.mybook.utils.LoginUserUtil;
import com.mybook.vo.Admin;
import com.mybook.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    /**
     * 用户登录
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String uName,String pwd){
        ResultInfo resultInfo=new ResultInfo();
        // 调用service层登录方法
        UserModel userModel=userService.userLogin(uName,pwd);
        // 设置ResultInfo的result的值 （将数据返回给请求）
        resultInfo.setResult(userModel);
        resultInfo.setMsg("登录成功");
        return resultInfo;
    }


    /**
     * 通过用户ID修改用户密码
     */
    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request, String oldPassword, String newPassword, String repeatPassword){
        ResultInfo resultInfo = new ResultInfo();
        // 获取cookie中的userId
        Integer uId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 调用Service层修改密码方法
        userService.updatePassWord(uId, oldPassword, newPassword, repeatPassword);

        return resultInfo;
    }

    /**
     * 用户注册
     */
    @PostMapping("register")
    @ResponseBody
    public ResultInfo registerUser(String uName,String uPwd,String repeatPwd){
        userService.registerUser(uName,uPwd,repeatPwd);
        return success("用户注册成功");
    }


    /**
     * 用户列表查询
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryAllUser(UserQuery userQuery){

        return userService.queryByParamsForTable(userQuery);

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
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户修改成功");
    }

    /**
     * 用户删除
     */
    @DeleteMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] uId){
        userService.deleteByuIds(uId);
        return success("用户删除成功");
    }

    /**
     * 跳转用户页面
     */
    @RequestMapping("index")
    public String index(){
        return "user/user";
    }

    /**
     * 跳转添加修改页面
     */
    @RequestMapping("toAddOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer uId, HttpServletRequest request){
        //uId为空 添加,不为空 更新,
        if (uId!=null){
            User user=userService.selectByPrimaryKey(uId);
            //将数据设置到请求域中
            request.setAttribute("user",user);
        }
        return "user/add_update";
    }





}
