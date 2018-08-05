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
    public boolean addProduct(Product product) {
        boolean isAdded = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "INSERT INTO product (id, name, price, description, formate, img, last_Update, category_id, isDelete) VALUES(?,?,?,?,?,?,?,?,?)";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, product.getId());
                    pstm.setString(2, product.getName());
                    pstm.setDouble(3, product.getPrice());
                    pstm.setString(4, product.getDescription());
                    pstm.setString(5, product.getImgFormate());
                    pstm.setBlob(6, product.getImg());
                    pstm.setTimestamp(7, new Timestamp(currentTime.getTime()));
                    pstm.setString(8, product.getCategoryId());
                    pstm.setBoolean(9, product.isDelete());
                    
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
    public List<Product> getProducts(String categoryId) {
        List<Product> productList = new ArrayList<Product>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, price, description, formate, img, last_Update, category_id, isDelete FROM product WHERE category_id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, categoryId);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        productList.add(new Product(
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getDouble(3),
                                    rs.getString(4),
                                    rs.getString(5),
                                    rs.getBinaryStream(6),
                                    rs.getTimestamp(7),
                                    rs.getString(8),
                                    rs.getBoolean(9)
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

    private Optional<DataSource> getDataSource(){
        Optional<DataSource> optDs = Optional.empty();

        try{
            Context ctx = new javax.naming.InitialContext();
            optDs = Optional.ofNullable((DataSource) ctx.lookup("java:comp/env/jdbc/AffablebeanPraDB"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return optDs;
    };
}
