package com.mybook.dao;

import com.mybook.base.BaseMapper;
import com.mybook.vo.Record;

import java.util.List;

public interface RecordMapper extends BaseMapper<Record,Integer> {

    Integer insertRecord (Record record);//添加借阅记录

    List<Record> qureyRecordsByUidAndBid(Integer uId,Integer bId);//根据用户id和图书id查询记录 返回记录的集合

    Record qureyRecordByRid(Integer rId);//根据记录id查询记录

    Integer updateRecord(Record record);//更新记录
}