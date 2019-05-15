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
import com.ecomm.src.dao.Product;
import com.ecomm.src.dao.ProductDao;

/**
 *
 * @author WayneKung-TPX
 */
public class ProductFacade extends AbstractFacade<Product, ProductDao> {
    private ProductDao productDao;
    
    public ProductFacade(Product product, CommonDao<Product> productDao) {
        super(Product.class, productDao);
        this.productDao = (ProductDao)productDao;
    }

    @Override
    protected CommonDao<Product> getDao(CommonDao<Product> productDao) {
        return productDao;
    }
}
