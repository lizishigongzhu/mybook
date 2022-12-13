package com.mybook.dao;

import com.mybook.base.BaseMapper;
import com.mybook.base.ResultInfo;
import com.mybook.query.QueryBook;
import com.mybook.vo.Book;

public interface BookMapper extends BaseMapper<Book,Integer> {

    Book queryBookById(Integer bId);//根据id查询

    Integer updateBState(Integer bId);//更改状态码(借阅)

    Integer updateBState1(Integer bId);//更改状态码(还书/逾期/追回)

    Integer updateBState2(Integer bId);//更改状态码(挂失)
}