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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecomm.src.dao.Product;

/**
 *
 * @author WayneKung-TPX
 */
public class ShoppingCart {
    List<ShoppingCartItem> items;
    int numOfItems;
    double total;
    
    public ShoppingCart() {
        this.items = new ArrayList<ShoppingCartItem>();
        this.numOfItems = 0;
        this.total = 0;
    }

    /**
     * Adds a <code>ShoppingCartItem</code> to the <code>ShoppingCart</code>'s
     * <code>items</code> list. If item of the specified <code>product</code>
     * already exists in shopping cart list, the quantity of that item is
     * incremented.
     *
     */
    public synchronized void addItem(Product product) {
        boolean isNewItem = true;
        
        for(ShoppingCartItem scItem : items) {
            if(scItem.getProduct().getId().equals(product.getId())) {
                isNewItem = false;
                scItem.incrementQuantity();
            }
        }
        
        if(isNewItem) {
            ShoppingCartItem scItem = new ShoppingCartItem(product);
            this.items.add(scItem);
        }
    }
    
    /**
     * Updates the <code>ShoppingCartItem</code> of the specified
     * <code>product</code> to the specified quantity. If '<code>0</code>'
     * is the given quantity, the <code>ShoppingCartItem</code> is removed
     * from the <code>ShoppingCart</code>'s <code>items</code> list.
     *
     */
    public synchronized void update(Product product, String quantity) {
        short qty = -1;
        
        // cast quantity as short
        qty = Short.parseShort(quantity);
        
        if(qty >= 0) {
            Optional<ShoppingCartItem> optItem = Optional.empty();
            
            for(ShoppingCartItem scItem : items) {
                if(scItem.getProduct().getId().equals(product.getId())) {
                    if(qty != 0) {
                        // set item quantity to new value
                        scItem.setQuantity(qty);
                    } else {
                        // if quantity equals 0, save item and break
                        optItem = Optional.ofNullable(scItem);
                        break;
                    }
                }
            }
            if(optItem.isPresent()) {
                // remove from cart
                items.remove(optItem.get());
            }
        }
    }
    
    /**
     * Returns the list of <code>ShoppingCartItems</code>.
     *
     */
    public synchronized List<ShoppingCartItem> getItems() {
        return items;
    }
    
    /**
     * Returns the sum of quantities for all items maintained in shopping cart
     * <code>items</code> list.
     *
     */
    public synchronized int getnumOfItems() {
        numOfItems = 0;
        
        for(ShoppingCartItem scItem : items) {
            numOfItems += scItem.getQuantity();
        }
        return numOfItems;
    }
    
    /**
     * Returns the sum of the product price multiplied by the quantity for all
     * items in shopping cart list. This is the total cost excluding the surcharge.
     *
     */
    public synchronized double getSubtotal() {
        double amount = 0;
        
        for(ShoppingCartItem scItem : items) {
            amount += (scItem.getProduct().getPrice().doubleValue() * scItem.getQuantity());
        }
        return amount;
    }
    
    /**
     * Calculates the total cost of the order. This method adds the subtotal to
     * the designated surcharge and sets the <code>total</code> instance variable
     * with the result.
     *
     */
    public synchronized void calculateTotal(String surcharge) {
        double amount = 0;
        
        // cast surcharge as double
        double s = Double.parseDouble(surcharge);
        
        amount = this.getSubtotal();
        amount += s;
        
        total = amount;
    }
    
    /**
     * Returns the total cost of the order for the given
     * <code>ShoppingCart</code> instance.
     *
     */
    public synchronized double getTotal() {
        return total;
    }
    
    /**
     * Empties the shopping cart. All items are removed from the shopping cart
     * <code>items</code> list, <code>numOfItems</code> and
     * <code>total</code> are reset to '<code>0</code>'.
     *
     */
    public synchronized void clear() {
        items.clear();
        numOfItems = 0;
        total = 0;
    }
}
