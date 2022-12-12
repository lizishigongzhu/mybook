package com.mybook.base;


import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {


    @ModelAttribute
    /*被@ModelAttribute注释的方法会在此controller的每个方法执行前被执行 ，如果有返回值，
    则自动将该返回值加入到ModelMap中。因此对于一个controller映射多
    个URL的用法来说，要谨慎使用。我们编写控制器代码时，会将保存方法独立成一个控制器也是如此。*/
    public void preHandler(HttpServletRequest request){
        request.setAttribute("ctx", request.getContextPath());
    }


    public ResultInfo success(){
        return new ResultInfo();
    }

    public ResultInfo success(String msg){
        ResultInfo resultInfo= new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public ResultInfo success(String msg,Object result){
        ResultInfo resultInfo= new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

}
