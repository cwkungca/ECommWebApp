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

import com.ecomm.src.dao.Category;
import com.ecomm.src.dao.CategoryDao;
import com.ecomm.src.dao.CommonDao;

/**
 *
 * @author WayneKung-TPX
 */
public class CategoryFacade extends AbstractFacade<Category, CategoryDao> {
    private CategoryDao categoryDao;
    
    public CategoryFacade(Category category, CommonDao<Category> categoryDao) {
        super(Category.class, categoryDao);
        this.categoryDao = (CategoryDao)categoryDao;
    }

    @Override
    protected CommonDao<Category> getDao(CommonDao<Category> categoryDao) {
        return categoryDao;
    }
    
    public Category findAllByCat(Object catId) {
        return categoryDao.getCatProducts(catId);
    }
}
