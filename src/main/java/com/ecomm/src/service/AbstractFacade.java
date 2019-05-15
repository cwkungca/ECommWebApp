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

import java.util.List;
import java.util.Optional;

import com.ecomm.src.dao.CommonDao;

/**
 *
 * @author WayneKung-TPX
 * @param <S>
 */
public abstract class AbstractFacade<T, U> {
    private Class<T> entityClass;
    protected CommonDao<T> entityDao;
    
    public AbstractFacade(Class<T> entity, CommonDao<T> entityDao) {
        this.entityClass = entity;
        this.entityDao = entityDao;
    }
    
    protected abstract CommonDao<T> getDao(CommonDao<T> dao);
    
    public boolean create(T entity) {
        return getDao(entityDao).add(entity);
    }
    
    public List<T> findAll() {
        return getDao(entityDao).getAll();
    }
    
    public Optional<T> find(Object id) {
        return getDao(entityDao).getEntity(id);
    }
}
