package com.mybook.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybook.base.BaseService;
import com.mybook.dao.UserMapper;
import com.mybook.query.UserQuery;
import com.mybook.utils.AssertUtil;
import com.mybook.utils.Md5Util;
import com.mybook.utils.PhoneUtil;
import com.mybook.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends BaseService <User,Integer>{
    @Resource
    private UserMapper userMapper;

    /**
     * 多条件分⻚查询⽤户数据
     */
    public Map<String, Object> selectUserInfo (UserQuery query) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByParams(query));
        map.put("code",0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }


    /**
     * 添加⽤户
     */
    @Transactional(propagation = Propagation.REQUIRED)  //事务控制
    public void saveUser(User user) {
        // 1. 参数校验
        checkParams(user.getuName(), user.getuPhone(),user.getuEmil());
        // 2. 设置默认参数
        user.setuName("lisi");
        user.setuPhone("12345678");
        user.setuEmil("12345678@qq.com");
        // 3. 执⾏添加，判断结果
        AssertUtil.isTrue(userMapper.insertSelective(user) == null, "⽤户添加失败！");
    }
    /**
     * 参数校验
     */
    private void checkParams(String uName,String uPhone,String uEmil) {
        AssertUtil.isTrue(StringUtils.isBlank(uName), "⽤户名不能为空！");
        // 验证⽤户名是否存在
        User temp =userMapper.selectByParams(uName);
        AssertUtil.isTrue(null != temp, "该⽤户已存在！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(uPhone), "⼿机号码格式不正确！");
        AssertUtil.isTrue(StringUtils.isBlank(uEmil), "请输⼊邮箱地址！");
    }

    /**
     * 更新⽤户
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        // 1. 参数校验
        // 通过id查询⽤户对象
        User temp = userMapper.selectByPrimaryKey(user.getuId());
        // 判断对象是否存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        // 验证参数
        checkParams(user.getuName(),user.getuPhone(),user.getuEmil());
        // 2. 设置默认参数
        temp.setuName(new String());
        // 3. 执⾏更新，判断结果
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "⽤户更新失败！");
    }

    /**
     * 参数校验
     */
    private void checkParams(String uName, String uPhone, String uEmil) {

        AssertUtil.isTrue(StringUtils.isBlank(uName), "⽤户名不能为空！");
        // 验证⽤户名是否存在
        User temp = userMapper.queryUserByuName(uName);
        // 如果是添加操作，数据库是没有数据的，数据库中只要查询到⽤户记录就表示不可⽤
        // 如果是修改操作，数据库是有数据的，查询到⽤户记录就是当前要修改的记录本身就表示可⽤，否则不可⽤
        // 数据存在，且不是当前要修改的⽤户记录，则表示其他⽤户占⽤了该⽤户名
        AssertUtil.isTrue(null != temp && !(temp.getuId().equals(uId)), "该⽤户已存在！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(uPhone), "⼿机号码格式不正确！");
        AssertUtil.isTrue(StringUtils.isBlank(uEmil), "请输⼊邮箱地址！");
    }

    /**
     * 删除⽤户
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserByIds(Integer[] ids) {
        AssertUtil.isTrue(null==ids || ids.length == 0,"请选择待删除的⽤户记录!");
        AssertUtil.isTrue(deleteBatch(ids) != ids.length,"⽤户记录删除失败!");
    }
}
