package cn.test.bookms.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@ToString
@Data
@Table(name="ms_rentbookhistory")
public class MsRentBookHistory {

    @Column(name="id")
    private Integer id;

    @Column(name="bookid")
    private Integer bookId;

    @Column(name="title")
    private String title;

    @Column(name="isbn")
    private String isbn;

    @Column(name="author")
    private String author;

    @Column(name="categoryid")
    private Integer categoryId;

    @Column(name="renttime")
    private Date rentTime;

    @Column(name="revertTime")
    private Date revertTime;

    @Column(name="memberNo")
    private String memberNo;

    @Column(name="status")
    private String status;     // 0----未还，1----已还

    @Column(name="revertday")
    private String revertDay;

    @Column(name="revertfee")
    private String revertFee;

    private static final long serialVersionUID = 1L;

}
