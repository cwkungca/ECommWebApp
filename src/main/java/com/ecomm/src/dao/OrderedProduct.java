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
 * @author WayneKung-TPX
 */
public class OrderedProduct {
    private String OrderId;
    private String productId;
    private String productName;
    private Integer quantity;
    
    public OrderedProduct() {}
    
    public OrderedProduct(String OrderId, String productId, Integer quantity) {
        this.OrderId = OrderId;
        this.productId = productId;
        this.quantity = quantity;
    }
    
    public OrderedProduct(String OrderId, String productId, String productName, Integer quantity) {
        this.OrderId = OrderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }
    
    public String getOrderId() {
        return OrderId;
    }
    public void setOrderId(String orderId) {
        OrderId = orderId;
    }
    
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
