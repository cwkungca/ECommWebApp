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

import com.ecomm.src.dao.Customer;
import com.ecomm.src.dao.CustomerOrder;
import com.ecomm.src.dao.Delivery;

/**
 *
 * @author WayneKung-TPX
 */
public interface CommerceAction {
    
    public CustomerOrder generateOrder(Customer cust, ShoppingCart cart, Delivery del);

}
