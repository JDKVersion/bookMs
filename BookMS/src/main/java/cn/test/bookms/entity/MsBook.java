package cn.test.bookms.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class MsBook implements Serializable {
    private Integer id;

    private String title;

    private String isbn;

    private String author;

    private String introduction;

    private String price;

    private String publishTime;

    private Integer categoryId;

    private String categoryName;

    private String image;

    private Date createTime;

    private String createAdmin;

    private String updatePreAdmin;

    private Integer delFlg;

    private static final long serialVersionUID = 1L;

    
}