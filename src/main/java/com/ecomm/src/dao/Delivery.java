/**
 * Copyright (c) 2019 WayneKung Co., Ltd. All Right Reserved.
 *
 * This software is the confidential and proprietary information of
 * WayneKung Co., Ltd. ("Confidential Informaion").
 *
 * You shall not disclose such Confidential Informaion and shall use it
 * only in accordance with the terms of license agreement you entered
 * into with WayneKung Co., Ltd.
 */

package com.ecomm.src.dao;

import java.util.Date;

/**
 *
 * @author WayneKung-TPX
 */
public class Delivery {
	private String id;
    private String name;
    private String phone;
    private String address;
    private String cityRegion;
    private String ccNumber;
    private Date lastUpdate;
    private boolean isDelete;
    
    public Delivery() {}
    
    public Delivery(String name, String phone,String address, String cityRegion, String ccNumber) {
    	this.name = name;
    	this.phone = phone;
    	this.address = address;
    	this.cityRegion = cityRegion;
    	this.ccNumber = ccNumber;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCityRegion() {
		return cityRegion;
	}
	public void setCityRegion(String cityRegion) {
		this.cityRegion = cityRegion;
	}
	
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
}
