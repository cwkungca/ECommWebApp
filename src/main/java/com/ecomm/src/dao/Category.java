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

/**
 *
 * @author WayneKung
 */
public class Category {
    private String id;
    private String name;
    private boolean isDelete;
    
    public Category() {}
    
    public Category(String categId, String categName, boolean isDelete) {
        this.id = categId;
        this.name = categName;
        this.isDelete = isDelete;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isDelete() {
        return isDelete;
    }
    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

}
