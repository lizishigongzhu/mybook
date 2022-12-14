package com.mybook.service;

import com.mybook.base.BaseService;
import com.mybook.dao.UserMapper;
import com.mybook.model.UserModel;
import com.mybook.utils.*;
import com.mybook.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    private UserMapper userMapper;



    /**
     * 添加用户信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        /*  参数校验 */
        checkUserParams(user.getuName(),  user.getuEmil(),user.getuPhone(), user.getuProfession(),null);

        // 设置默认密码
        user.setuPwd(Md5Util.encode("1234567"));

        /* 执行添加操作，判断受影响的行数 */
        AssertUtil.isTrue(userMapper.insertSelective(user) < 1, "用户添加失败！");
    }

    /**
     * 修改用户信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        // 判断用户ID是否为空，且数据存在
        AssertUtil.isTrue(null == user.getuId(), "待更新记录不存在！");
        // 通过id查询数据
        User temp = userMapper.selectByPrimaryKey(user.getuId());
        // 判断是否存在
        AssertUtil.isTrue(null == temp, "待更新记录不存在！");
        // 参数校验
        checkUserParams(user.getuName(),  user.getuEmil(),user.getuPhone(), user.getuProfession(), user.getuId());


        // 执行更新操作，判断受影响的行数
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1, "用户更新失败！");
    }

    /**
     * 用户删除
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Integer uId){

        // 判断用户ID是否为空，且数据存在
        AssertUtil.isTrue(null==userMapper.selectByPrimaryKey(uId), "待删除记录不存在！");
        //调用方法
        AssertUtil.isTrue(userMapper.deleteByPrimaryKey(uId)<1,"用户删除失败");

    }


    /**
     * 添加与修改参数校验
     */
    private void checkUserParams(String uName, String uEmil, String uPhone, String uProfession, Integer uId) {
        // 判断用户名是否为空
        AssertUtil.isTrue(StringUtils.isBlank(uName), "用户名不能为空！");
        // 判断用户名的唯一性
        // 通过用户名查询用户对象
        User temp = userMapper.queryUserByName(uName);
        // 如果用户对象为空，则表示用户名可用；如果用户对象不为空，则表示用户名不可用
        // 如果是添加操作，数据库中无数据，只要通过名称查到数据，则表示用户名被占用
        // 如果是修改操作，数据库中有对应的记录，通过用户名查到数据，可能是当前记录本身，也可能是别的记录
        // 如果用户名存在，且与当前修改记录不是同一个，则表示其他记录占用了该用户名，不可用
        AssertUtil.isTrue(null != temp && !(temp.getuId().equals(uId)), "用户名已存在，请重新输入！");

        // 邮箱 非空
        AssertUtil.isTrue(StringUtils.isBlank(uEmil), "用户邮箱不能为空！");

        // 手机号 非空
        AssertUtil.isTrue(StringUtils.isBlank(uPhone), "用户手机号不能为空！");

        // 手机号 格式判断
        AssertUtil.isTrue(!PhoneUtil.isMobile(uPhone), "手机号格式不正确！");

        //部门专业 非空
        AssertUtil.isTrue(StringUtils.isBlank(uProfession),"请输入正确的部门专业名");
    }

    /**
     * 修改密码的参数校验
     */
    private void checkPasswordParams(User user,String oldPwd, String newPwd, String repeatPwd) {
        //  判断原始密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "原始密码不能为空！");
        // 判断原始密码是否正确（查询的用户对象中的用户密码是否原始密码一致）
        AssertUtil.isTrue(!user.getuPwd().equals(Md5Util.encode(oldPwd)), "原始密码不正确！");

        // 判断新密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(newPwd), "新密码不能为空！");
        // 判断新密码是否与原始密码一致 （不允许新密码与原始密码）
        AssertUtil.isTrue(oldPwd.equals(newPwd),"新密码不能与原始密码相同！");

        // 判断确认密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"确认密码不能为空！");
        // 判断确认密码是否与新密码一致
        AssertUtil.isTrue(!newPwd.equals(repeatPwd), "确认密码与新密码不一致！");

    }

    /**
     *  构建需要返回给客户端的用户对象
     * @param user
     * @return
     */
    private UserModel buildUserInfo(User user) {
        UserModel userModel=new UserModel();
        //userModel.setId(user.getId());
        // 设置加密的用户ID
        userModel.setuIdStr(UserIDBase64.encoderUserID(user.getuId()));

        userModel.setuName(user.getuName());

        return userModel;
    }

    /**
     * 参数判断
     *    如果参数为空，抛出异常（异常被控制层捕获并处理）
     */
    private void checkLoginParams(String uName, String pwd) {
        AssertUtil.isTrue(StringUtils.isBlank(uName),"用户姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(pwd),"用户密码不能为空");
    }

    /**
     * 密码判断
     *    先将客户端传递的密码加密，再与数据库中查询到的密码作比较
     */
    private void checkUserPwd(String uPwd,String pwd) {
        // 将客户端传递的密码加密
        pwd= Md5Util.encode(pwd);
        // 判断密码是否相等
        AssertUtil.isTrue(!uPwd.equals(pwd),"用户密码不正确");

    }

}

