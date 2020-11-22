package cn.test.bookms.service;

import cn.test.bookms.entity.MsBook;
import cn.test.bookms.entity.MsMembers;
import cn.test.bookms.entity.MsRentBookHistory;
import cn.test.bookms.entity.PageBean;


public interface MsMemberService {

    void instertMember(MsMembers member);
    MsMembers selectByNo(String memberNo,String password);
    void insertRecord(MsRentBookHistory msRentBookHistory);
    PageBean<MsRentBookHistory> revertBookList(String memberNo, int currentPage);
    void updateStatus(int id,String memberNo);
    PageBean<MsRentBookHistory> selectRecords(String memberNo, int currentPage);
    int noRevertCount(int bookId,String memberNo);
    void modify(MsRentBookHistory msRentBookHistory);


}
