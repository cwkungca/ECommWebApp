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
import java.util.List;
import java.util.Optional;

import javax.naming.Context;
import javax.sql.DataSource;

/**
 *
 * @author WayneKung
 */
public class ProductImp implements ProductDao {

    @Override
    public boolean add(Product product) {
        boolean isAdded = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "INSERT INTO product (id, name, price, quantity, description, imgFormate, img, imgPath, last_Update, category_id, isDelete) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, product.getId());
                    pstm.setString(2, product.getName());
                    pstm.setDouble(3, product.getPrice());
                    pstm.setInt(4, product.getQuantity());
                    pstm.setString(5, product.getDescription());
                    pstm.setString(6, product.getImgFormate());
                    pstm.setBlob(7, product.getImg());
                    pstm.setString(8, product.getImgPath());
                    pstm.setTimestamp(9, new Timestamp(currentTime.getTime()));
                    pstm.setString(10, product.getCategoryId());
                    pstm.setBoolean(11, product.isDelete());
                    
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
    public List<Product> getAll(){
        List<Product> productList = new ArrayList<Product>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, price, quantity, description, imgFormate, img, imgPath, last_Update, category_id, isDelete FROM product";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        productList.add(new Product(
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getDouble(3),
                                    rs.getInt(4),
                                    rs.getString(5),
                                    rs.getString(6),
                                    rs.getBinaryStream(7),
                                    rs.getString(8),
                                    rs.getTimestamp(9),
                                    rs.getString(10),
                                    rs.getBoolean(11)
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
        return productList;
    }
    
    @Override
    public boolean update(Product product) {
        boolean isUpdated = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "UPDATE product SET name = ?, price = ?, quantity = ?, description = ?, imgFormate = ?, img = ?, imgPath = ?, last_Update = ?, category_id = ?, isDelete = ? WHERE id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, product.getName());
                    pstm.setDouble(2, product.getPrice());
                    pstm.setInt(3, product.getQuantity());
                    pstm.setString(4, product.getDescription());
                    pstm.setString(5, product.getImgFormate());
                    pstm.setBlob(6, product.getImg());
                    pstm.setString(7, product.getImgPath());
                    pstm.setTimestamp(8, new Timestamp(currentTime.getTime()));
                    pstm.setString(9, product.getCategoryId());
                    pstm.setBoolean(10, product.isDelete());
                    pstm.setString(11, product.getId());
                    
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
    public Optional<Product> getEntity(Object id) {
        Optional<Product> optProduct = Optional.empty();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, price, quantity, description, imgFormate, img, imgPath, last_Update, category_id, isDelete FROM product WHERE id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, (String)id);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        optProduct = Optional.ofNullable(
                                    new Product(rs.getString(1),
                                            rs.getString(2),
                                            rs.getDouble(3),
                                            rs.getInt(4),
                                            rs.getString(5),
                                            rs.getString(6),
                                            rs.getBinaryStream(7),
                                            rs.getString(8),
                                            rs.getTimestamp(9),
                                            rs.getString(10),
                                            rs.getBoolean(11))
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
        return optProduct;
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
