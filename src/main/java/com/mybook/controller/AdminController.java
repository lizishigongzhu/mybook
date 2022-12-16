package com.mybook.controller;

import com.mybook.base.BaseController;
import com.mybook.base.ResultInfo;
import com.mybook.query.AdminQuery;
import com.mybook.sevice.AdminService;
import com.mybook.vo.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {
    @Resource
    private AdminService adminService;



    /**
     * 管理员列表查询
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryAllAdmin(AdminQuery adminQuery){
        return adminService.queryByParamsForTable(adminQuery);
    }



    /**
     * 添加管理员
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addAdmin(Admin admin){
        adminService.addAdmin(admin);
        return success("管理员授权成功");
    }

    /**
     * 修改管理员信息
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateAdmin(Admin admin){
        adminService.updateAdmin(admin);
        return success("管理员信息修改成功");
    }

    /**
     * 删除管理员
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteAdmin(Integer uId){
        adminService.deleteAdmin(uId);
        return success("管理员删除成功");
    }

    /**
     * 跳转管理员界面
     */
    @RequestMapping("index")
    public String index(){
        return "user/manager";
    }


    /**
     * 跳转添加修改页面
     */
    @RequestMapping("toAddOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer uId, HttpServletRequest request){
        //uId为空 添加,不为空 更新,
        if (uId!=null){
            Admin admin=adminService.selectByPrimaryKey(uId);
            //将数据设置到请求域中
            request.setAttribute("adminInfo",admin);
        }
        return "user/add_update";
    }





}
