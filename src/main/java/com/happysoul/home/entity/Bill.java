package com.happysoul.home.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(precision=10,scale=2,nullable=false,columnDefinition="decimal(10,2) NOT NULL COMMENT '流转钱数，花费是正，收入是负' ")
	private BigDecimal money;
	@Column(name="sign_time")
	private Date signTime;
	@Column(name="create_time",nullable=false)
	private Date createTime;
	@Column(columnDefinition="int NOT NULL DEFAULT 0 COMMENT '类型 0未分类 1个人消费 2共同消费 3家庭成员消费(如代缴话费等)	'")
	private Integer type;
	@Column(columnDefinition="int NOT NULL DEFAULT '0' COMMENT '预花费 0默认正常花费 1储值卡消费 '")
	private Integer prepaid;
	@Column(columnDefinition="varchar(255) NOT NULL COMMENT '收支备注'")
	private String mark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPrepaid() {
		return prepaid;
	}
	public void setPrepaid(Integer prepaid) {
		this.prepaid = prepaid;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
}
