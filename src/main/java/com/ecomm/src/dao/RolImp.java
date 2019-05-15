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
public class RolImp implements RolDao {

    @Override
    public boolean add(Rol rol) {
        boolean isAdded = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "INSERT INTO rol (id, name, description, last_Update, isDelete) VALUES(?,?,?,?,?)";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, rol.getId());
                    pstm.setString(2, rol.getName());
                    pstm.setString(3, rol.getDescription());
                    pstm.setTimestamp(4, new Timestamp(currentTime.getTime()));
                    pstm.setBoolean(5, rol.isDelete());
                    
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
    public List<Rol> getAll() {
        List<Rol> rolList = new ArrayList<Rol>();
        List<Permission> permList = new ArrayList<Permission>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, description, last_Update, isDelete FROM rol";
        String queryRolPermi = "SELECT p.id, p.name p.description, p.last_update, p.isDelete FROM rol_permission r INNER JOIN permission p ON r.permission_id = p.id WHERE r.rol_id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        rolList.add(new Rol(
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getTimestamp(4),
                                    rs.getBoolean(5)
                                ));
                    }
                    
                    // get rol's permission
                    for(Rol rol : rolList) {
                        PreparedStatement pstmRP = optConn.get().prepareStatement(queryRolPermi);
                        pstm.setString(1, (String)rol.getId());
                        
                        ResultSet rsRP = pstmRP.executeQuery();
                        while(rsRP.next()) {
                            permList.add(new Permission(
                                        rsRP.getString(1),
                                        rsRP.getString(2),
                                        rsRP.getString(3),
                                        rsRP.getTimestamp(4),
                                        rsRP.getBoolean(5)
                                    ));
                        }
                        rol.setOptPermissions(Optional.ofNullable(permList));
                        permList.clear();
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
        return rolList;
    }

    @Override
    public boolean update(Rol rol) {
        boolean isUpdated = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "UPDATE rol SET name = ?, description = ?, last_Update = ?, isDelete = ? WHERE id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, rol.getName());
                    pstm.setString(2, rol.getDescription());
                    pstm.setTimestamp(3, new Timestamp(currentTime.getTime()));
                    pstm.setBoolean(4, rol.isDelete());
                    pstm.setString(5, rol.getId());
                    
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
    public Optional<Rol> getEntity(Object id) {
        Optional<Rol> optRol = Optional.empty();
        List<Permission> permList = new ArrayList<Permission>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, name, description, last_Update, isDelete FROM rol WHERE id = ?";
        String queryRolPermi = "SELECT p.id, p.name, p.description, p.last_update, p.isDelete FROM rol_permission r INNER JOIN permission p ON r.permission_id = p.id WHERE r.rol_id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, (String)id);
                    
                    ResultSet rs = pstm.executeQuery();
                    
                    while(rs.next()){
                        optRol = Optional.ofNullable(
                                new Rol(rs.getString(1),
                                        rs.getString(2),
                                        rs.getString(3),
                                        rs.getTimestamp(4),
                                        rs.getBoolean(5))
                                );
                    }
                    
                    // get rol's permission
                    PreparedStatement pstmRP = optConn.get().prepareStatement(queryRolPermi);
                    pstm.setString(1, (String)id);
                    
                    ResultSet rsRP = pstmRP.executeQuery();
                    while(rsRP.next()) {
                        permList.add(new Permission(
                                    rsRP.getString(1),
                                    rsRP.getString(2),
                                    rsRP.getString(3),
                                    rsRP.getTimestamp(4),
                                    rsRP.getBoolean(5)
                                ));
                    }
                    optRol.get().setOptPermissions(Optional.ofNullable(permList));
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
        return optRol;
    }

    @Override
    public boolean addRolPermission(Rol rol) {
        boolean isAdded = false;
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "INSERT INTO rol_permission (rol_id, permission_id) VALUES(?,?)";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    
                    //  transaction block start
                    optConn.get().setAutoCommit(false);
                    
                    for(Permission perm : rol.getOptPermissions().get()) {
                        PreparedStatement pstm = optConn.get().prepareStatement(query);
                        pstm.setString(1, rol.getId());
                        pstm.setString(2, perm.getId());
                        
                        if(pstm.executeUpdate() == 1) {
                            isAdded = true;
                        }
                    }
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
    public boolean updateRolPermission(Rol rol) {
        boolean isUpdated = false;
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String queryOld = "DELETE FROM rol_permission WHERE rol_id = ?";
        String queryNew = "INSERT INTO rol_permission (rol_id, permission_id) VALUES(?,?)";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    
                    //  transaction block start
                    optConn.get().setAutoCommit(false);
                    
                    PreparedStatement pstmOld = optConn.get().prepareStatement(queryOld);
                    pstmOld.setString(1, rol.getId());
                    pstmOld.executeUpdate();
                    
                    PreparedStatement pstmNew = optConn.get().prepareStatement(queryNew);
                    for(Permission permi : rol.getOptPermissions().get()) {
                        pstmNew.setString(1, rol.getId());
                        pstmNew.setString(2, permi.getId());
                        
                        pstmNew.executeUpdate();
                    }
                    
                    optConn.get().commit();
                    isUpdated = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                
                try {
                    // any error transaction roll back
                    optConn.get().rollback();
                    
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
        return isUpdated;
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
