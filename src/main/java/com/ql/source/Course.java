package com.ql.source;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wangmengguang
 *
 */
public class Course implements Serializable {

	private Long id;
	
	private Date purchasedate;
	
	private String name;
	
	private Double price;
	
	public Course(Long id, Date purchasedate, String name, Double price) {
		super();
		this.id = id;
		this.purchasedate = purchasedate;
		this.name = name;
		this.price = price;
	}
	public Course() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getPurchasedate() {
		return purchasedate;
	}
	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
