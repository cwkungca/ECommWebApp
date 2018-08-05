/**
 * Copyright (c) 2018 WayneKung Co., Ltd. All Right Reserved.
 *
 * This software is the confidential and proprietary information of
 * WayneKung Co., Ltd. ("Confidential Informaion").
 *
 * You shall not disclose such Confidential Informaion and shall use it
 * only in accordance with the terms of license agreement you entered
 * into with WayneKung Co., Ltd.
 */

package com.ecomm.src.dao;

/**
 *
 * @author WayneKung
 */
public class Customer {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String cityRegion;
    private String ccNumber;
    private boolean isActive;
    private String activeCode;
    private boolean isDelete;
    
    public Customer() {}
    
    public Customer(String id, String name, String email, String phone, String address, String cityRegion, String ccNumber, boolean isActive, String activeCode, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cityRegion = cityRegion;
        this.ccNumber = ccNumber;
        this.isActive = isActive;
        this.activeCode = activeCode;
        this.isDelete = isDelete;
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
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public String getActiveCode() {
        return activeCode;
    }
    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }
    
    public boolean isDelete() {
        return isDelete;
    }
    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
}
