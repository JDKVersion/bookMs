package cn.test.bookms.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用来分页
 * @author wk
 *
 */
@Data
@ToString
public class PageBean<T> {

	private String title;  //书名
	private String author; //作者
	private int currPage;//当前页数
    private int pageSize;//每页显示的记录数
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private List<T> lists;//每页的显示的数据
    
    
    
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

    
	
}
