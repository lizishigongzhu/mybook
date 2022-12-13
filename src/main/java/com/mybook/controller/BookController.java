package com.mybook.controller;

import com.mybook.base.BaseController;
import com.mybook.base.ResultInfo;
import com.mybook.service.BookService;
import com.mybook.service.UserService;
import com.mybook.utils.LoginUserUtil;
import com.mybook.vo.Book;
import com.mybook.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("book")
public class BookController extends BaseController {
    @Resource
    private BookService bookService;
    @Resource
    private UserService userService;

    /**
     * 图书借阅
     */
    @PostMapping("borrow")
    @ResponseBody
    public ResultInfo borrowBook(Integer bId,HttpServletRequest request){
        // 获取cookie中的用户Id
        Integer uId = LoginUserUtil.releaseUserIdFromCookie(request);
        bookService.borrowBook(bId,uId);
        return success("借阅成功");
    }

    /**
     * 还书系统
     */
    @PostMapping("return")
    @ResponseBody
    public ResultInfo returnBook(Integer bId,HttpServletRequest request){
        Integer uId = LoginUserUtil.releaseUserIdFromCookie(request);
        bookService.returnBook(bId,uId);
        return success("还书成功");
    }

    /**
     * 挂失（管理员）
     */
    @PostMapping("lossOf")
    @ResponseBody
    public ResultInfo lossOf(Integer bId,HttpServletRequest request){
        // 获取cookie中的用户Id
        Integer uId = LoginUserUtil.releaseUserIdFromCookie(request);
        //查询用户信息
        User user = userService.queryUserByUid(uId);
        //判断身份
        if(user.getCId() != 3){
            ResultInfo resultInfo  = new ResultInfo();
            resultInfo.setCode(550);
            resultInfo.setMsg("无权访问");
            return resultInfo;
        }
        //调用Service方法
        bookService.lossOf(bId);
        return success("操作成功");
    }
    /**
     * 追回（管理员）
     */
    @PostMapping("recover")
    @ResponseBody
    public ResultInfo recover(Integer bId,HttpServletRequest request){
        // 获取cookie中的用户Id
        Integer uId = LoginUserUtil.releaseUserIdFromCookie(request);
        //查询用户信息
        User user = userService.queryUserByUid(uId);
        //判断身份
        if(user.getCId() != 3){
            ResultInfo resultInfo  = new ResultInfo();
            resultInfo.setCode(550);
            resultInfo.setMsg("无权访问");
            return resultInfo;
        }
        //调用Service方法
        bookService.recover(bId);
        return success("操作成功");
    }

    /**
     * 逾期（管理员）
     */
    @PostMapping("overdue")
    @ResponseBody
    public ResultInfo overdue(Integer rId,HttpServletRequest request){
        // 获取cookie中的用户Id
        Integer uId = LoginUserUtil.releaseUserIdFromCookie(request);
        //查询用户信息
        User user = userService.queryUserByUid(uId);
        //判断身份
        if(user.getCId() != 3){
            ResultInfo resultInfo  = new ResultInfo();
            resultInfo.setCode(550);
            resultInfo.setMsg("无权访问");
            return resultInfo;
        }
        //调用Service方法
        bookService.overdue(rId);
        return success("操作成功");
    }



    /**
     * 进入借还界面
     */
   @RequestMapping("borrowOrReturn")
    public String borrowOrReturn(Integer bId, HttpServletRequest request){
       if (bId != 0) {
           Book book = bookService.queryBookById(bId);
           request.setAttribute("book",book);
       }
        return "book/borrow_return";
    }

}
