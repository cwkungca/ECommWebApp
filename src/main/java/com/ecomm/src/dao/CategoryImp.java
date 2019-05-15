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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.naming.Context;
import javax.sql.DataSource;

/**
 *
 * @author WayneKung
 */
public class CategoryImp implements CategoryDao {

    public boolean add(Category category) {
        boolean isAdded = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "INSERT INTO category (id, name, imgFormate, img, imgPath, last_update, isDelete) VALUES(?,?,?,?,?,?,?)";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, category.getId());
                    pstm.setString(2, category.getName());
                    pstm.setString(3, category.getImgFormate());
                    pstm.setBlob(4, category.getImg());
                    pstm.setString(5, category.getImgPath());
                    pstm.setTimestamp(6, new Timestamp(currentTime.getTime()));
                    pstm.setBoolean(7, category.isDelete());
                    
                    if(pstm.executeUpdate() == 1) {
                        isAdded = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try{
                    if(optConn.isPresent()){
                        optConn.get().close();
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
        return isAdded;
    }
    
    @Override
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<Category>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, imgFormate, img, imgPath, last_update, isDelete FROM category";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        categoryList.add(new Category(
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getBinaryStream(4),
                                    rs.getString(5),
                                    rs.getTimestamp(6),
                                    rs.getBoolean(7)
                                ));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try{
                    if(optConn.isPresent()){
                        optConn.get().close();
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
        return categoryList;
    }
    
    @Override
    public boolean update(Category category) {
        boolean isUpdated = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "UPDATE category SET name = ?, imgFormate = ?, img = ?, imgPath = ?, last_update = ?, isDelete = ? WHERE id = ?";

        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, category.getName());
                    pstm.setString(2, category.getImgFormate());
                    pstm.setBlob(3, category.getImg());
                    pstm.setString(4, category.getImgPath());
                    pstm.setTimestamp(5, new Timestamp(currentTime.getTime()));
                    pstm.setBoolean(6, category.isDelete());
                    pstm.setString(7, category.getId());
                    
                    if(pstm.executeUpdate() == 1) {
                        isUpdated = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try{
                    if(optConn.isPresent()){
                        optConn.get().close();
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
        return isUpdated;
    }
    
    @Override
    public Optional<Category> getEntity(Object id) {
        Optional<Category> optCategory = Optional.empty();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, imgFormate, img, imgPath, last_update, isDelete FROM category WHERE id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, (String)id);
                    
                    ResultSet rs = pstm.executeQuery();
                    
                    while(rs.next()){
                        optCategory = Optional.ofNullable(
                                new Category(rs.getString(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getBinaryStream(4),
                                        rs.getString(5),
                                        rs.getTimestamp(6),
                                        rs.getBoolean(7))
                                );
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try{
                    if(optConn.isPresent()){
                        optConn.get().close();
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
        return optCategory;
    }

    @Override
    public Category getCatProducts(Object categoryId) {
        Category category = new Category();
        List<Product> productsList = new ArrayList<Product>();
        Optional<Collection<Product>> optProducts = Optional.empty();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, imgFormate, img, imgPath, last_update, isDelete FROM category WHERE id = ?";
        String queryProducts = "SELECT id, name, price, quantity, description, imgFormate, img, imgPath, last_Update, category_id, isDelete FROM product WHERE category_id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, (String)categoryId);
                    
                    PreparedStatement pstmProducts = optConn.get().prepareStatement(queryProducts);
                    pstmProducts.setString(1, (String)categoryId);
                    
                    ResultSet rs = pstm.executeQuery();
                    ResultSet rsProducts = pstmProducts.executeQuery();
                    
                    while(rs.next()){
                        category.setId(rs.getString(1));
                        category.setName(rs.getString(2));
                        category.setImgFormate(rs.getString(3));
                        category.setImg(rs.getBinaryStream(4));
                        category.setImgPath(rs.getString(5));
                        category.setLastUpdate(rs.getTimestamp(6));
                        category.setDelete(rs.getBoolean(7));
                        
                        while(rsProducts.next()) {
                            productsList.add(new Product(
                                    rsProducts.getString(1),
                                    rsProducts.getString(2),
                                    rsProducts.getDouble(3),
                                    rsProducts.getInt(4),
                                    rsProducts.getString(5),
                                    rsProducts.getString(6),
                                    rsProducts.getBinaryStream(7),
                                    rsProducts.getString(8),
                                    rsProducts.getTimestamp(9),
                                    rsProducts.getString(10),
                                    rsProducts.getBoolean(11)
                                ));
                        }
                        if(productsList.size() > 0) {
                            optProducts = Optional.ofNullable(productsList);
                        }
                        category.setCatProducts(optProducts);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try{
                    if(optConn.isPresent()){
                        optConn.get().close();
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
        return category;
    }
    
    private Optional<DataSource> getDataSource(){
        Optional<DataSource> optDs = Optional.empty();

        try{
            Context ctx = new javax.naming.InitialContext();
            optDs = Optional.ofNullable((DataSource) ctx.lookup("java:comp/env/jdbc/AffablebeanPraDB"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return optDs;
    }
}
