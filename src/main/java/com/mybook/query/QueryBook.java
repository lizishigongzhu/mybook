package com.mybook.query;

import com.mybook.base.BaseQuery;
import lombok.Data;

@Data
public class QueryBook extends BaseQuery {

    private Integer bId;//图书id
    private String bName;//图书名称
    private Integer bState;//状态码

}
