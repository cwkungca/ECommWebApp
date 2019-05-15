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

package com.ecomm.src.model;

import com.ecomm.src.dao.Product;

/**
 *
 * @author WayneKung-TPX
 */
public class ShoppingCartItem {
    private Product product;
    private short quantity;

    public ShoppingCartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }
    
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public short getQuantity() {
        return quantity;
    }
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }
    
    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }
    
    public Double getTotal() {
        double amount = 0;
        amount = this.getQuantity() * product.getPrice().doubleValue();
        return (Double) amount;        
    }
}
