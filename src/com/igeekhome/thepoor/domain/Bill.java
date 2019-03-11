package com.igeekhome.thepoor.domain;

public class Bill {
	private int billId;
	private String category;
	private double money;
	private String createTime;
	private String description;
	
	public Bill(String category,double money , String createTime,String description){
		this.category = category;
		this.money = money;
		this.createTime = createTime;
		this.description = description;
	}
	
	
	public Bill(int billId, String category, double money, String createTime, String description) {
		
		this.billId = billId;
		this.category = category;
		this.money = money;
		this.createTime = createTime;
		this.description = description;
	}


	public Bill() {
		
	}


	public int getBillId() {
		return billId;
	}


	public void setBillId(int billId) {
		this.billId = billId;
	}


	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
