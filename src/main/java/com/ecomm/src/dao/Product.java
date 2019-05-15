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
public class Product extends ImgEntity{
    private Double price;
    private Integer quantity;
    private String description;
    private String categoryId;
    
    public Product() {}
    
    public Product(String id, String name, Double price, Integer quantity, String description, String imgFormate, InputStream img, String imgPath, Date lastUpdate, String categoryId, boolean isDelete) {
        super(id, name, imgFormate, img, imgPath, lastUpdate, isDelete);
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.categoryId = categoryId;
    }
    
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}
