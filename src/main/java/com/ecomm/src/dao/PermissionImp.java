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
 * @author WayneKung-TPX
 */
public class PermissionImp implements PermissionDao {

    @Override
    public boolean add(Permission permission) {
        boolean isAdded = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "INSERT INTO permission (id, name, description, last_Update, isDelete) VALUES(?,?,?,?,?)";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, permission.getId());
                    pstm.setString(2, permission.getName());
                    pstm.setString(3, permission.getDescription());
                    pstm.setTimestamp(4, new Timestamp(currentTime.getTime()));
                    pstm.setBoolean(5, permission.isDelete());
                    
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
    public List<Permission> getAll() {
        List<Permission> permList = new ArrayList<Permission>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, description, last_Update, isDelete FROM permission";

        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        permList.add(new Permission(
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getTimestamp(4),
                                    rs.getBoolean(5)
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
        return permList;
    }

    @Override
    public boolean update(Permission permission) {
        boolean isUpdated = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "UPDATE permission SET name = ?, description = ?, last_Update = ?, isDelete = ? WHERE id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, permission.getName());
                    pstm.setString(2, permission.getDescription());
                    pstm.setTimestamp(3, new Timestamp(currentTime.getTime()));
                    pstm.setBoolean(4, permission.isDelete());
                    pstm.setString(5, permission.getId());
                    
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
    public Optional<Permission> getEntity(Object id) {
        Optional<Permission> optPermission = Optional.empty();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, description, last_Update, isDelete FROM permission WHERE id = ?";

        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, (String)id);
                    
                    ResultSet rs = pstm.executeQuery();
                    
                    while(rs.next()){
                        optPermission = Optional.ofNullable(
                                new Permission(rs.getString(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getTimestamp(4),
                                        rs.getBoolean(5))
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
        return optPermission;
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
