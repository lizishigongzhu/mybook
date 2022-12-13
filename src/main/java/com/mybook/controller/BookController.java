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
    public ResultInfo borrowBook(Integer bId,Integer uId){
        return  bookService.borrowBook(bId,uId);
    }

    /**
     * 还书系统
     */
    @PostMapping("return")
    @ResponseBody
    public ResultInfo returnBook(Integer bId,Integer uId){
        return bookService.returnBook(bId,uId);
    }

    /**
     * 挂失（管理员）
     */
    @PostMapping("lossOf")
    @ResponseBody
    public ResultInfo lossOf(Integer bId,Integer uId){
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
        return bookService.lossOf(bId);
    }

    /**
     * 追回（管理员）
     */
    @PostMapping("recover")
    @ResponseBody
    public ResultInfo recover(Integer bId,Integer uId){
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

        return bookService.recover(bId);
    }

    /**
     * 逾期（管理员）
     */
    @PostMapping("overdue")
    @ResponseBody
    public ResultInfo overdue(Integer rId,Integer uId){
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
        return bookService.overdue(rId);
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
