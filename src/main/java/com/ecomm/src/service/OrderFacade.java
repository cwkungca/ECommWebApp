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

package com.ecomm.src.service;

import com.ecomm.src.dao.CommonDao;
import com.ecomm.src.dao.CustomerOrder;
import com.ecomm.src.dao.CustomerOrderDao;

/**
 *
 * @author WayneKung-TPX
 */
public class OrderFacade extends AbstractFacade<CustomerOrder, CustomerOrderDao> {
    private CustomerOrderDao customerOrderDao;
    
    public OrderFacade(CustomerOrder customerOrder, CommonDao<CustomerOrder> customerOrderDao) {
        super(CustomerOrder.class, customerOrderDao);
        this.customerOrderDao = (CustomerOrderDao)customerOrderDao;
    }

    @Override
    protected CommonDao<CustomerOrder> getDao(CommonDao<CustomerOrder> customerOrderDao) {
        return customerOrderDao;
    }
}
