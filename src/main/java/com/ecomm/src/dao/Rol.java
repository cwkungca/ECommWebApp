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

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author WayneKung-TPX
 */
public class Rol {
    private String id;
    private String name;
    private String description;
    private Date lastUpdate;
    private boolean isDelete;
    private Optional<List<Permission>> optPermissions = Optional.empty();
    
    public Rol() {}
    
    public Rol(String id, String name, String description, Date lastUpdate, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lastUpdate = lastUpdate;
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
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public boolean isDelete() {
        return isDelete;
    }
    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
    public Optional<List<Permission>> getOptPermissions() {
        return optPermissions;
    }
    public void setOptPermissions(Optional<List<Permission>> optPermissions) {
        this.optPermissions = optPermissions;
    }
    
}
