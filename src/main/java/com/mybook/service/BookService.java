package com.mybook.service;

import com.mybook.base.BaseService;
import com.mybook.base.ResultInfo;
import com.mybook.dao.BookMapper;
import com.mybook.dao.RecordMapper;
import com.mybook.utils.AssertUtil;
import com.mybook.vo.Book;
import com.mybook.vo.Record;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class BookService extends BaseService<Book,Integer> {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private RecordMapper recordMapper;

    public Book queryBookById(Integer bId){
        return bookMapper.queryBookById(bId);
    }

    /**
     * 图书借阅
     * 1.接收图书id
     * 2.非空判断 图书id不为空
     * 3.调用dao层方法 根据图书id查询，返回书本对象
     * 4.判断返回对象
     * 	对象非空（书本对象不存在）
     * 	状态码判断（1可借阅 2已借出 3书本丢失）
     * 5.更改数据库中图书的状态码
     * 添加借书记录
     * 6.返回resultinfo对象
     */
    public ResultInfo borrowBook(Integer bId,Integer uId){
        ResultInfo resultInfo = new ResultInfo();
        //非空判断 图书id不为空
        AssertUtil.isTrue(bId == null,"图书序号不能为空");
        //调用dao层方法 根据图书id查询，返回书本对象
        Book book = bookMapper.queryBookById(bId);
        //判断返回对象 对象非空（书本对象不存在）
        AssertUtil.isTrue(book == null,"图书不存在");
        //状态码判断（1可借阅 2已借出 3书本丢失）
        if (book.getbState() == 3){
            resultInfo.setCode(300);
            resultInfo.setMsg("该图书丢失，暂无法借阅");
            return resultInfo;
        }else if (book.getbState() == 2){
            resultInfo.setCode(300);
            resultInfo.setMsg("该图书已被借阅");
            return resultInfo;
        }

        //更改数据库中图书的状态码 返回影响行数
        //判断影响行数是否小于一
        if (bookMapper.updateBState(bId) < 1){
            resultInfo.setCode(500);
            resultInfo.setMsg("图书状态更新异常，借阅失败");
            return resultInfo;
        }
        //添加借书记录
        //设置记录对象
        Record record = new Record();
        record.setbId(uId);
        record.setbId(bId);
        record.setrBorrowtime(new Date());
        //判断影响行数
        if (recordMapper.insertRecord(record) < 1){
            resultInfo.setCode(600);
            resultInfo.setMsg("借阅记录添加异常，借阅失败");
            return resultInfo;
        }
        resultInfo.setMsg("借阅成功");
        return resultInfo;
    }

    /**
     * 还书:
     * 	1.接收图书id
     * 	2.非空判断 图书id不能为空
     * 	3.调用dao层方法 根据图书id查询，返回书本对象
     * 	4.判断返回对象
     * 		对象非空（书本对象不存在）
     * 		状态码判断（1在库 2已借出 3书本丢失）
     * 			3——挂失追回
     *
     * 	5.调用dao层方法查询 借书记录
     * 		存在的话更新时间
     * 			判断是否逾期——30天
     * 				若逾期 ——逾期处理
     * 	6.调用dao层更改数据库中图书的状态码
     * 	7.返回resultinfo对象
     */
    public ResultInfo returnBook(Integer bId,Integer uId){
        ResultInfo resultInfo = new ResultInfo();
        //非空判断 图书id不为空
        AssertUtil.isTrue(bId == null,"图书序号不能为空");
        //调用dao层方法 根据图书id查询，返回书本对象
        Book book = bookMapper.queryBookById(bId);
        //判断返回对象 对象非空（书本对象不存在）
        AssertUtil.isTrue(book == null,"图书不存在");
        //状态码判断（1在库 2已借出 3书本丢失）
        if (book.getbState()==1){
            resultInfo.setCode(500);
            resultInfo.setMsg("图示状态异常，还书失败");
            return resultInfo;
        }else if (book.getbState()==3){
            resultInfo.setCode(500);
            resultInfo.setMsg("挂失图书操作，请联系管理员");
            return resultInfo;
        }
        //调用dao层方法查询 借书记录
        //根据用户id和图示id 查询记录 返回集合
        List<Record> recordList = recordMapper.qureyRecordsByUidAndBid(uId,bId);
        //判断结果集
        if (recordList == null){
            resultInfo.setCode(500);
            resultInfo.setMsg("未查询到借阅记录，还书失败");
            return resultInfo;
        }
        //遍历集合找到未还书的记录对象
        for (Record re:recordList) {
            if (re.getrReturntime()==null){
                //添加还书时间
                Date reDate = new Date();
                re.setrReturntime(reDate);
                //判断是否逾期
                //Date转LocalDate
                LocalDate localDate1 = reDate.toInstant()
                        .atZone(ZoneId.systemDefault())	//设置当前系统时区
                        .toLocalDate();
                LocalDate localDate2 = re.getrBorrowtime().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                Period p = Period.between(localDate2,localDate1);
                //判断是否超过一月的还书日期
                if (p.getYears()>0 || p.getMonths()>0){
                    resultInfo.setCode(800);
                    resultInfo.setMsg("您已逾期，请联系管理员");
                    return resultInfo;
                }
                //未逾期 更改状态码
                if (bookMapper.updateBState(uId) < 1){
                    resultInfo.setCode(600);
                    resultInfo.setMsg("图书状态更新失败");
                    return resultInfo;
                }
                resultInfo.setMsg("还书成功");
            }
        }
        resultInfo.setMsg("未查询到未归还记录，还书失败");
        resultInfo.setCode(500);
        return resultInfo;
    }

    /**
     * 挂失（管理员）：
     * 	1.接收图书id
     * 	2.非空判断 图书id不能为空
     * 	3.调用dao层方法 根据图书id查询，返回书本对象
     * 		非空判断
     * 		状态码为1、2——挂失：更改状态码为3
     * 		状态码为3——追回：更改状态码为1
     * 	4.调用更新方法 返回影响行数
     * 	5.返回resultinfo
     */
    public ResultInfo lossOf(Integer bId){
        ResultInfo resultInfo = new ResultInfo();
        //调用dao层方法 根据图书id查询，返回书本对象
        Book book = getBook(bId);
        //判断状态码
        //挂失操作
        if (book.getbState() == 1 || book.getbState() == 2){
           if ( bookMapper.updateBState2(bId) < 1 ){
               resultInfo.setCode(500);
               resultInfo.setMsg("图书状态更新异常，挂失失败");
               return resultInfo;
           }
           resultInfo.setMsg("挂失成功");
            return resultInfo;
        }else {//追回操作
                resultInfo.setCode(500);
                resultInfo.setMsg("请进行追回操作");
                return resultInfo;
        }

    }

    /**
     * 追回（管理员）：
     * 	1.接收图书id
     * 	2.非空判断 图书id不能为空
     * 	3.调用dao层方法 根据图书id查询，返回书本对象
     * 		非空判断
     * 		状态码为1、2——无需追回
     * 		状态码为3——追回：更改状态码为1
     * 	4.调用更新方法 返回影响行数
     * 	5.返回resultinfo
     */
    public ResultInfo recover(Integer bId){
        ResultInfo resultInfo = new ResultInfo();
        //调用dao层方法 根据图书id查询，返回书本对象
        Book book = getBook(bId);
        //判断状态码
        if (book.getbState() == 1 || book.getbState() == 2){
                resultInfo.setCode(500);
                resultInfo.setMsg("无需追回");
                return resultInfo;
        }else {//追回操作
            if (bookMapper.updateBState1(bId) < 1){
                resultInfo.setCode(500);
                resultInfo.setMsg("图书状态更新异常，追回失败");
                return resultInfo;
            }
            resultInfo.setMsg("追回成功");
            return resultInfo;
        }

    }

    /**
     *逾期（管理员)：
     * 	1.接收图书id
     * 	2.非空判断 图书id不能为空
     * 	3.调用dao层方法 根据图书id查询，返回书本对象
     * 		非空判断
     * 	    状态码 2
     * 	        逾期
     * 	4.调用更新方法 返回影响行数
     * 	5.返回resultinfo
     */
    public ResultInfo overdue(Integer rId){
        ResultInfo resultInfo = new ResultInfo();
        //调用dao层方法查询 借书记录
        Record record = recordMapper.qureyRecordByRid(rId);
        //接收图书id非空判断 图书id不能为空,调用dao层方法 根据图书id查询，返回书本对象
        Book book =getBook(record.getbId());
        //判断状态码
        if (book.getbState() != 2){
            resultInfo.setCode(400);
            resultInfo.setMsg("非借阅图书，无法进行逾期操作");
            return resultInfo;
        }
        //判断是否逾期

        //判断结果
        if (record == null){
            resultInfo.setCode(600);
            resultInfo.setMsg("未查询到借阅记录");
            return resultInfo;
        }
        //判断是否逾期
        LocalDate localDate1 = record.getrBorrowtime().toInstant()
                .atZone(ZoneId.systemDefault())	//设置当前系统时区
                .toLocalDate();
        LocalDate localDate2 = record.getrReturntime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Period p = Period.between(localDate2,localDate1);
        if (p.getYears()>0 || p.getMonths()>0){
            //更新状态码
            if (bookMapper.updateBState(record.getbId()) < 1){
                resultInfo.setCode(600);
                resultInfo.setMsg("图书状态更新失败");
                return resultInfo;
            }
            resultInfo.setMsg("逾期处理成功");
            return resultInfo;
        }
        resultInfo.setMsg("无需进行逾期操作");
        resultInfo.setCode(400);
        return resultInfo;
    }


    /**
     * 封装
     */
    public Book getBook(Integer bId){
        //非空判断 图书id不为空
        AssertUtil.isTrue(bId == null,"图书序号不能为空");
        //调用dao层方法 根据图书id查询，返回书本对象
        Book book = bookMapper.queryBookById(bId);
        //判断返回对象 对象非空（书本对象不存在）
        AssertUtil.isTrue(book == null,"图书不存在");
        return book;
    }
}
