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
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author WayneKung
 */
public class Category extends ImgEntity{
    private Optional<Collection<Product>> optCatProducts = Optional.empty();
    
    public Category() {}
    
    public Category(String id, String name, String imgFormate, InputStream img, String imgPath, Date lastUpdate, boolean isDelete) {
        super(id, name, imgFormate, img, imgPath, lastUpdate, isDelete);
    }

    public Optional<Collection<Product>> getCatProducts() {
        return optCatProducts;
    }

    public void setCatProducts(Optional<Collection<Product>> optCatProducts) {
        this.optCatProducts = optCatProducts;
    }
}
