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

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author WayneKung
 */
public class Product {
    private String id;
    private String name;
    private Double price;
    private String description;
    private String imgFormate;
    private InputStream img;
    private Date lastUpdate;
    private String categoryId;
    private boolean isDelete;
    
    public Product() {}
    
    public Product(String id, String name, Double price, String description, String imgFormate, InputStream img, Date lastUpdate, String categoryId, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imgFormate = imgFormate;
        this.img = img;
        this.lastUpdate = lastUpdate;
        this.categoryId = categoryId;
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
    
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImgFormate() {
        return imgFormate;
    }
    public void setImgFormate(String imgFormate) {
        this.imgFormate = imgFormate;
    }
    
    public InputStream getImg() {
        return img;
    }
    public void setImg(InputStream img) {
        this.img = img;
    }
    
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public boolean isDelete() {
        return isDelete;
    }
    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
}
