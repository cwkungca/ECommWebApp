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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ecomm.src.model.ShoppingCart;

/**
 *
 * @author WayneKung-TPX
 */
public class CustomerOrder {
    private String id;
    private Double amount;
    private Date dateCreated;
    private String confirNumber;    // CustomerOder's ID firset 7 character + Date.getTime() long
    private String customerId;
    private Optional<ShoppingCart> optShoppingCart = Optional.empty();
    private Optional<List<OrderedProduct>> optOrderedProducts = Optional.empty();
    private Optional<Delivery> optDelivery = Optional.empty();
    
	public CustomerOrder() {}
    
    public CustomerOrder(String id, Double amount, Date dateCreated, String confirNumber, String customerId) {
        this.id = id;
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.confirNumber = confirNumber;
        this.customerId = customerId;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public String getConfirNumber() {
        return confirNumber;
    }
    public void setConfirNumber(String confirNumber) {
        this.confirNumber = confirNumber;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public Optional<ShoppingCart> getOptShoppingCart() {
        return optShoppingCart;
    }
    public void setOptShoppingCart(Optional<ShoppingCart> optShoppingCart) {
        this.optShoppingCart = optShoppingCart;
    }

    public Optional<List<OrderedProduct>> getOptOrderedProducts() {
        return optOrderedProducts;
    }
    public void setOptOrderedProducts(Optional<List<OrderedProduct>> optOrderedProducts) {
        this.optOrderedProducts = optOrderedProducts;
    }
    
    public Optional<Delivery> getOptDelivery() {
		return optDelivery;
	}
	public void setOptDelivery(Optional<Delivery> optDelivery) {
		this.optDelivery = optDelivery;
	}
}
