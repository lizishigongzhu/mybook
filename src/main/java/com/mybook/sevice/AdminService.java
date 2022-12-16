package com.mybook.sevice;

import com.mybook.base.BaseService;
import com.mybook.dao.AdminMapper;
import com.mybook.utils.AssertUtil;
import com.mybook.utils.PhoneUtil;
import com.mybook.vo.Admin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AdminService extends BaseService<Admin,Integer> {
    @Resource
    private AdminMapper adminMapper;

    /**
     * 添加管理员
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addAdmin(Admin admin){
        //参数校验
        /*管理员姓名不能为空*/
        AssertUtil.isTrue(StringUtils.isBlank(admin.getuName()),"姓名不能为空");
        /*联系电话不能为空且格式正确*/
        AssertUtil.isTrue(StringUtils.isBlank(admin.getuPhone()),"电话号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(admin.getuPhone()),"号码格式不正确");
        /*邮箱不能为空*/
        AssertUtil.isTrue(StringUtils.isBlank(admin.getuEmil()),"邮箱不能为空");
        /*部门专业不能为空*/
        AssertUtil.isTrue(StringUtils.isBlank(admin.getuProfession()),"部门专业不能为空");

        //设置默认值
        admin.setcId(3);

        //调用添加方法
        AssertUtil.isTrue(adminMapper.insertSelective(admin)<1,"管理员授权失败");

    }

    /**
     * 修改管理
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAdmin(Admin admin){
        //参数校验
        /*管理员Id不能为空*/
        AssertUtil.isTrue(null==admin.getuId()||adminMapper.selectByPrimaryKey(admin.getuId())==null,"待修改信息不存在");

        //设置默认值
        /*身份Id等于3*/
        admin.setcId(3);

        //调用方法
        AssertUtil.isTrue(adminMapper.updateByPrimaryKeySelective(admin)<1,"修改管理员信息失败");
    }

    /**
     * 删除管理员
     */
    public void deleteAdmin(Integer uId){
        //参数校验
        /*uId不能为空*/
        AssertUtil.isTrue(uId==null,"待删信息不存在");


        Admin admin=adminMapper.selectByPrimaryKey(uId);
        /*cId只能为3*/
        AssertUtil.isTrue(null==admin.getcId()||admin.getcId()!=3,"数据异常");

        //设置默认值
        admin.setcId(1);

        //调用方法
        AssertUtil.isTrue(adminMapper.updateByPrimaryKeySelective(admin)<1,"管理员删除失败");

    }
}
