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

import com.ecomm.src.model.ShoppingCartItem;

/**
 *
 * @author WayneKung-TPX
 */
public class CustomerOrderImp implements CustomerOrderDao {

    @Override
    public boolean add(CustomerOrder customerOrder) {
        boolean isAdded = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String queryCusOrder = "INSERT INTO customer_order (id, amount, date_created, confirmation_number, customer_id) VALUES(?,?,?,?,?)";
        String queryOrderProduct = "INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES(?,?,?)";
        String queryDelivery = "INSERT INTO customer (id, name, phone, address, city_region, cc_number) VALUES(?,?,?,?,?,?)";
        
        // update product's quantity
        String queryProdRenew = "UPDATE product SET quantity = quantity - ? WHERE id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    
                    //  transaction block start
                    optConn.get().setAutoCommit(false);
                    
                    // add CustomerOrder
                    PreparedStatement pstm = optConn.get().prepareStatement(queryCusOrder);
                    pstm.setString(1, customerOrder.getId());
                    pstm.setDouble(2, customerOrder.getAmount());
                    pstm.setTimestamp(3, new Timestamp(currentTime.getTime()));
                    pstm.setString(4, customerOrder.getConfirNumber());
                    pstm.setString(5, customerOrder.getCustomerId());
                    
                    pstm.executeUpdate();
                    
                    // add Delivery
                    PreparedStatement pstmDel = optConn.get().prepareStatement(queryDelivery);
                    pstmDel.setString(1, customerOrder.getId());
                    pstmDel.setString(2, customerOrder.getOptDelivery().get().getName());
                    pstmDel.setString(3, customerOrder.getOptDelivery().get().getPhone());
                    pstmDel.setString(4, customerOrder.getOptDelivery().get().getAddress());
                    pstmDel.setString(5, customerOrder.getOptDelivery().get().getCityRegion());
                    pstmDel.setString(6, customerOrder.getOptDelivery().get().getCcNumber());
                    
                    pstmDel.executeUpdate();
                    
                    // add OrderedProduct
                    for(ShoppingCartItem item : customerOrder.getOptShoppingCart().get().getItems()) {
                        PreparedStatement pstmOrdProd = optConn.get().prepareStatement(queryOrderProduct);
                        pstmOrdProd.setString(1, customerOrder.getId());
                        pstmOrdProd.setString(2, item.getProduct().getId());
                        pstmOrdProd.setInt(3, item.getQuantity());
                        
                        pstmOrdProd.executeUpdate();
                    }
                    
                    //  update product's quantity
                    for(ShoppingCartItem item : customerOrder.getOptShoppingCart().get().getItems()) {
                        PreparedStatement pstmProdRenew = optConn.get().prepareStatement(queryProdRenew);
                        pstmProdRenew.setInt(1, item.getQuantity());
                        pstmProdRenew.setString(2, item.getProduct().getId());
                        
                        pstmProdRenew.executeUpdate();
                    }
                    isAdded = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                
                try {
                    // any error transaction roll back
                    optConn.get().rollback();
                    isAdded = false;
                    
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } finally {
                try{
                    if(optConn.isPresent()){
                        // transaction block stop
                        optConn.get().setAutoCommit(true);
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
    public List<CustomerOrder> getAll() {
        List<CustomerOrder> custOrderList = new ArrayList<CustomerOrder>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, amount, date_created, confirmation_number, customer_id FROM customer_order";

        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        custOrderList.add(new CustomerOrder(
                                    rs.getString(1),
                                    rs.getDouble(2),
                                    rs.getTimestamp(3),
                                    rs.getString(4),
                                    rs.getString(5)
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
        return custOrderList;
    }

    @Override
    public boolean update(CustomerOrder customerOrder) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<CustomerOrder> getEntity(Object id) {
        Optional<CustomerOrder> optCustOrder = Optional.empty();
        List<OrderedProduct> ordProductList = new ArrayList<OrderedProduct>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, amount, date_created, confirmation_number, customer_id FROM customer_order WHERE id = ?";
        String queryOrdProduct = "SELECT o.customer_order_id, o.product_id, p.name, o.quantity FROM ordered_product o INNER JOIN product p ON o.product_id = p.id WHERE o.customer_order_id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, (String)id);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        optCustOrder = Optional.ofNullable(
                                new CustomerOrder(
                                        rs.getString(1),
                                        rs.getDouble(2),
                                        rs.getTimestamp(3),
                                        rs.getString(4),
                                        rs.getString(5))
                                );
                    }
                    
                    if(optCustOrder.isPresent()) {
                        PreparedStatement pstmOrdProd = optConn.get().prepareStatement(queryOrdProduct);
                        pstmOrdProd.setString(1, optCustOrder.get().getId());
                        
                        ResultSet rsOrdProd = pstmOrdProd.executeQuery();
                        while(rsOrdProd.next()) {
                            ordProductList.add(new OrderedProduct(
                                        rsOrdProd.getString(1),
                                        rsOrdProd.getString(2),
                                        rsOrdProd.getString(3),
                                        rsOrdProd.getInt(4)
                                    ));
                        }
                        optCustOrder.get().setOptOrderedProducts(Optional.ofNullable(ordProductList));
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
        return optCustOrder;
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
