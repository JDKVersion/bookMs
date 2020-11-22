package cn.test.bookms.service.impl;

import cn.test.bookms.entity.MsBook;
import cn.test.bookms.entity.MsMembers;
import cn.test.bookms.entity.MsRentBookHistory;
import cn.test.bookms.entity.PageBean;
import cn.test.bookms.mapper.MsMemberMapper;
import cn.test.bookms.service.MsMemberService;
import cn.test.bookms.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsMemberServiceImpl implements MsMemberService {

    @Autowired
    private MsMemberMapper msMemberMapper;

    @Override
    public void instertMember(MsMembers member) {

        msMemberMapper.instertMember(member);
    }

    @Override
    public MsMembers selectByNo(String memberNo, String password) {
        return msMemberMapper.selectByNo(memberNo,password);
    }

    @Override
    public void insertRecord(MsRentBookHistory msRentBookHistory) {

        msMemberMapper.insertRecord(msRentBookHistory);
    }

    @Override
    public PageBean<MsRentBookHistory> revertBookList(String memberNo, int currentPage) {

        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<MsRentBookHistory> pageBean = new PageBean<MsRentBookHistory>();

        //设置当前页数
        pageBean.setCurrPage(currentPage);


        //设置每页显示的数据
        int pageSize = Message.PAGE_SIZE;
        pageBean.setPageSize(pageSize);

        //设置总页数
        int totalCount =msMemberMapper.selectCount(memberNo);
        pageBean.setTotalCount(totalCount);

        //设置总页数
        double tc = totalCount;
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());

        map.put("start", (currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("memberNo",memberNo);


        //封装每页显示的数据
        List<MsRentBookHistory> bookList = msMemberMapper.revertBookList(map);
        pageBean.setLists(bookList);
        return pageBean;
    }

    @Override
    public void updateStatus(int id, String memberNo) {

        msMemberMapper.updateStatus(id,memberNo);
    }

    @Override
    public PageBean<MsRentBookHistory> selectRecords(String memberNo, int currentPage) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<MsRentBookHistory> pageBean = new PageBean<MsRentBookHistory>();

        //设置当前页数
        pageBean.setCurrPage(currentPage);


        //设置每页显示的数据
        int pageSize = Message.PAGE_SIZE;
        pageBean.setPageSize(pageSize);

        //设置总页数
        int totalCount =msMemberMapper.selectCountRecords(memberNo);
        pageBean.setTotalCount(totalCount);

        //设置总页数
        double tc = totalCount;
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());

        map.put("start", (currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
        map.put("memberNo",memberNo);


        //封装每页显示的数据
        List<MsRentBookHistory> bookList = msMemberMapper.selectRecords(map);
        pageBean.setLists(bookList);
        return pageBean;
    }

    @Override
    public int noRevertCount(int bookId, String memberNo) {

        return msMemberMapper.noRevertCount(bookId,memberNo) ;
    }

    @Override
    public void modify(MsRentBookHistory msRentBookHistory) {
        msMemberMapper.modify(msRentBookHistory);
    }
}

