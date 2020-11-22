package cn.test.bookms.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Table(name ="ms_members")
public class MsMembers {

@Column(name="username")
private String username;

@Column(name="password")
private String password;

@Column(name="memberNo")
private String memberNo;

@Column(name="level")
private Integer level;

@Column(name="createTime")
private Date createTime;

@Column(name="lastLoginTime")
private Date lastLoginTime;

private static final long serialVersionUID = 1L;

}
