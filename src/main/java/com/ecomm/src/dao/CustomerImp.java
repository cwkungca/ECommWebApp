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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.Context;
import javax.sql.DataSource;

/**
 *
 * @author WayneKung-TPX
 */
public class CustomerImp implements CustomerDao {

    @Override
    public boolean add(Customer customer) {
        boolean isAdded = false;
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "INSERT INTO customer (id, name, email, password, phone, address, city_region, cc_number, activeCode, isDelete) VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, customer.getId());
                    pstm.setString(2, customer.getName());
                    pstm.setString(3, customer.getEmail());
                    pstm.setString(4, customer.getPassword());
                    pstm.setString(5, customer.getPhone());
                    pstm.setString(6, customer.getAddress());
                    pstm.setString(7, customer.getCityRegion());
                    pstm.setString(8, customer.getCcNumber());
                    pstm.setString(9, customer.getActiveCode());
                    pstm.setBoolean(10, customer.isDelete());
                    
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
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<Customer>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();

        String query = "SELECT id, name, email, password, phone, address, city_region, cc_number, isActive, activeCode, isDelete FROM customer";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        customerList.add(new Customer(
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4),
                                    rs.getString(5),
                                    rs.getString(6),
                                    rs.getString(7),
                                    rs.getString(8),
                                    rs.getBoolean(9),
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
        return customerList;
    }

    @Override
    public boolean update(Customer customer) {
        boolean isUpdated = false;
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "UPDATE customer SET name = ?, email = ?, password = ?, phone = ?, address = ?, city_region = ?, cc_number = ?, activeCode = ?, isDelete = ? WHERE id = ?";

        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, customer.getName());
                    pstm.setString(2, customer.getEmail());
                    pstm.setString(3, customer.getPassword());
                    pstm.setString(4, customer.getPhone());
                    pstm.setString(5, customer.getAddress());
                    pstm.setString(6, customer.getCityRegion());
                    pstm.setString(7, customer.getCcNumber());
                    pstm.setString(8, customer.getActiveCode());
                    pstm.setBoolean(9, customer.isDelete());
                    pstm.setString(10, customer.getId());
                    
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
    public Optional<Customer> getEntity(Object email) {
        Optional<Customer> optCustomer = Optional.empty();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, email, password, phone, address, city_region, cc_number, isActive, activeCode, isDelete FROM customer WHERE email = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, (String)email);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        optCustomer = Optional.ofNullable(
                                    new Customer(rs.getString(1),
                                            rs.getString(2),
                                            rs.getString(3),
                                            rs.getString(4),
                                            rs.getString(5),
                                            rs.getString(6),
                                            rs.getString(7),
                                            rs.getString(8),
                                            rs.getBoolean(9),
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
        return optCustomer;
    }
    
    @Override
    public Optional<Customer> getUser(String acc, String pwd) {
        Optional<Customer> optCustomer = Optional.empty();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, email, password, phone, address, city_region, cc_number, isActive, activeCode, isDelete FROM customer WHERE email = ? AND password = ? AND isActive = true AND isDelete = false";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, (String)acc);
                    pstm.setString(2, (String)pwd);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        optCustomer = Optional.ofNullable(
                                    new Customer(rs.getString(1),
                                            rs.getString(2),
                                            rs.getString(3),
                                            rs.getString(4),
                                            rs.getString(5),
                                            rs.getString(6),
                                            rs.getString(7),
                                            rs.getString(8),
                                            rs.getBoolean(9),
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
        return optCustomer;
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
