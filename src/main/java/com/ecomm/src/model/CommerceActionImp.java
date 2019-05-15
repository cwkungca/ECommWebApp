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

import java.sql.Timestamp;
import java.util.Optional;

import com.ecomm.src.dao.Customer;
import com.ecomm.src.dao.CustomerOrder;
import com.ecomm.src.dao.Delivery;

/**
 *
 * @author WayneKung-TPX
 */
public class CommerceActionImp implements CommerceAction {
    java.util.Date currentTime = new java.util.Date();

    @Override
    public CustomerOrder generateOrder(Customer cust, ShoppingCart cart, Delivery del) {
        String orderId = java.util.UUID.randomUUID().toString();
        CustomerOrder order = new CustomerOrder(
                    orderId,
                    cart.getTotal(),
                    new Timestamp(currentTime.getTime()),
                    orderId.subSequence(0, 8) + String.valueOf(currentTime.getTime()),
                    cust.getId()
                );
        order.setOptShoppingCart(Optional.ofNullable(cart));
        order.setOptDelivery(Optional.ofNullable(del));
        return order;
    }

}
