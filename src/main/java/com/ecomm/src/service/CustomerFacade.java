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

import java.util.Optional;

import com.ecomm.src.dao.CommonDao;
import com.ecomm.src.dao.Customer;
import com.ecomm.src.dao.CustomerDao;

/**
 *
 * @author WayneKung-TPX
 */
public class CustomerFacade extends AbstractFacade<Customer, CustomerDao> {
    private CustomerDao customerDao;
    
    public CustomerFacade(Customer customer, CommonDao<Customer> customerDao) {
        super(Customer.class, customerDao);
        this.customerDao = (CustomerDao)customerDao;
    }

    @Override
    protected CommonDao<Customer> getDao(CommonDao<Customer> customerDao) {
        return customerDao;
    }
    
    public Optional<Customer> getLoginUser(String acc, String pwd) {
        return customerDao.getUser(acc, pwd);
    }
}
