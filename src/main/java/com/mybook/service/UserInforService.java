package com.mybook.service;

import com.mybook.utils.AssertUtil;
import com.mybook.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service
public class UserInforService {

    /**
     * 显示所有读者
     */

    public String getUserInfor(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                     int currentPage, Model model) {
        logger.info("===ManageReaderController类的showReader方法===");
        model.addAttribute("pageMsg",msAdminService.selectReaderByPage(null, null, null, currentPage));
        return "showAllReader";
    }

    /**
     * 查询读者
     */

    public String searchReader() {
        logger.info("===ManageReaderController类的searchReader方法===");
        return "searchReader";
    }

    public String searchAdmin(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                      int currentPage,String adminNumber,String adminName,Integer identity, Model model, HttpServletRequest request) {
        logger.info("===ManageReaderController类的searchAdmin方法===");
        request.setAttribute("placeholder1", adminNumber);	// 显示查询时输入内容
        request.setAttribute("placeholder2", adminName);
        request.setAttribute("placeholder3", identity);
        model.addAttribute("pageMsg",msAdminService.selectReaderByPage(adminNumber, adminName, identity, currentPage));
        return "searchReader";
    }

    /**
     * 点击查看按钮：查看读者借阅信息
     */

    public String showReaderBorrowDetail(int id, String adminName, Model model, HttpSession httpSession, HttpServletRequest request) {
        logger.info("===ManageReaderController类的showReaderBorrowDetail方法===");
//		System.out.println(" ==test== adminId: "+id);
        request.setAttribute("readerName", adminName);
        request.setAttribute("readerId", id);	// 将读者id传到读者借阅信息界面，用于读者的借阅操作（谁：readerId；操作哪本书：bookId）
        return borrowBookController.showBorrowBook(1, model, httpSession, request, id, null);
    }



    /**
     * 添加用户信息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public String addUserInfor (User user) {
        // 1. 参数校验
        checkParams(user.getUserName(), user.getEmail(), user.getPhone());
        // 2. 设置默认参数
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));
        // 3. 执⾏添加，判断结果
        AssertUtil.isTrue(userMapper.insertSelective(user) == null, "⽤户添加失败！");
    }
    /**
     * 参数校验
     */
    private void checkParams(String userName, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "⽤户名不能为空！");
        // 验证⽤户名是否存在
        User temp = userMapper.queryUserByUserName(userName);
        AssertUtil.isTrue(null != temp, "该⽤户已存在！");
        AssertUtil.isTrue(StringUtils.isBlank(email), "请输⼊邮箱地址！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "⼿机号码格式不正确！");
    }


    /**
     * 删除用户信息
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserInfor(Integer[] ids) {
        AssertUtil.isTrue(null==ids || ids.length == 0,"请选择待删除的⽤户记录!");
        AssertUtil.isTrue(deleteBatch(ids) != ids.length,"⽤户记录删除失败!");
    }


}
