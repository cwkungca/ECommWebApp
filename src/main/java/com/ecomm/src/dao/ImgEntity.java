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

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author WayneKung-TPX
 */
public class ImgEntity {
    private String id;
    private String name;
    private String imgFormate;
    private InputStream img;
    private String imgPath;
    private Date lastUpdate;
    private boolean isDelete;
    
    public ImgEntity() {}
    
    public ImgEntity(String id, String name, String imgFormate, InputStream img, String imgPath, Date lastUpdate, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.imgFormate = imgFormate;
        this.img = img;
        this.imgPath = imgPath;
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
    
    public String getImgFormate() {
        return imgFormate;
    }
    public void setImgFormate(String imgFormate) {
        this.imgFormate = imgFormate;
    }
    
    public InputStream getImg() {
        return img;
    }
    public void setImg(InputStream img) {
        this.img = img;
    }
    
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
}
