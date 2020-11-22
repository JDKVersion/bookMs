package cn.test.bookms.mapper;

import cn.test.bookms.entity.MsMembers;
import cn.test.bookms.entity.MsRentBookHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MsMemberMapper {

    void instertMember(@Param("member") MsMembers member);

    MsMembers selectByNo(@Param("memberNo") String memberNo,@Param("password") String password);

    void insertRecord(@Param("msRentBookHistory")MsRentBookHistory msRentBookHistory);

    List<MsRentBookHistory> revertBookList(Map map);

    int selectCount(String memberNo);

    void updateStatus( @Param("id")int id,@Param("memberNo")String memberNo);

    List<MsRentBookHistory>  selectRecords(Map map);

    int selectCountRecords(String memberNo);

    int noRevertCount(@Param("bookId")int bookId,@Param("memberNo")String memberNo);

    void modify(@Param("msRentBookHistory")MsRentBookHistory msRentBookHistory);
}
