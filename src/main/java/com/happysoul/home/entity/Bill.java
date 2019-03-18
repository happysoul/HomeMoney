package com.happysoul.home.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author fuhs
 * @since 2019-03-14
 */
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date createTime;

    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 收支备注
     */
    private String mark;

    /**
     * 流转钱数，花费是正，收入是负
     */
    private BigDecimal money;

    private String name;

    /**
     * 预花费 0默认正常花费 1储值卡消费 
     */
    private Integer prepaid;

    private Date signTime;

    /**
     * 类型 0未分类 1个人消费 2共同消费 3家庭成员消费(如代缴话费等)	
     */
    private Integer type;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Integer prepaid) {
        this.prepaid = prepaid;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return "Bill{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", mark=" + mark +
        ", money=" + money +
        ", name=" + name +
        ", prepaid=" + prepaid +
        ", signTime=" + signTime +
        ", type=" + type +
        "}";
    }
}
