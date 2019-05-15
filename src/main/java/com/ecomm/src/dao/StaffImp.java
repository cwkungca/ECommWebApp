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
public class StaffImp implements StaffDao {

    @Override
    public boolean add(Staff staff) {
        boolean isAdded = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "INSERT INTO staff (id, first_name, last_name, email, password, phone, address, isActive, activeCode, last_update, rol_id, isDelete) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, staff.getId());
                    pstm.setString(2, staff.getFirstName());
                    pstm.setString(3, staff.getLastName());
                    pstm.setString(4, staff.getEmail());
                    pstm.setString(5, staff.getPassword());
                    pstm.setString(6, staff.getPhone());
                    pstm.setString(7, staff.getAddress());
                    pstm.setBoolean(8, staff.isDelete());
                    pstm.setString(9, staff.getActiveCode());
                    pstm.setTimestamp(10, new Timestamp(currentTime.getTime()));
                    pstm.setString(11, staff.getRol().getId());
                    pstm.setBoolean(12, staff.isDelete());
                    
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
    public List<Staff> getAll() {
        List<Staff> staffList = new ArrayList<Staff>();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        //  get Rols
        RolDao rolDao = new RolImp();
        Optional<List<Rol>> optRol = Optional.ofNullable(rolDao.getAll());
        
        String query = "SELECT id, first_name, last_name, email, password, phone, address, isActive, activeCode, last_update, rol_id, isDelete FROM staff";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    
                    ResultSet rs = pstm.executeQuery();
                    while(rs.next()){
                        Staff staff = new Staff();
                        staff.setId(rs.getString(1));
                        staff.setFirstName(rs.getString(2));
                        staff.setLastName(rs.getString(3));
                        staff.setEmail(rs.getString(4));
                        staff.setPassword(rs.getString(5));
                        staff.setPhone(rs.getString(6));
                        staff.setAddress(rs.getString(7));
                        staff.setActive(rs.getBoolean(8));
                        staff.setActiveCode(rs.getString(9));
                        staff.setLastUpdate(rs.getTimestamp(10));
                        staff.setDelete(rs.getBoolean(12));
                        
                        // set staff's rol 
                        for(Rol rol : optRol.get()) {
                            if (rs.getString(11).equals(rol.getId())) {
                                staff.setRol(rol);
                                break;
                            }
                        }
                        
                        staffList.add(staff);
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
        return staffList;
    }

    @Override
    public boolean update(Staff staff) {
        boolean isUpdated = false;
        java.util.Date currentTime = new java.util.Date();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "UPDATE rol SET first_name = ?, last_name = ?, email = ?, password = ?, phone = ?, address = ?, isActive = ?, activeCode = ?, last_update = ?, rol_id = ?, isDelete = ? WHERE id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, staff.getFirstName());
                    pstm.setString(2, staff.getLastName());
                    pstm.setString(3, staff.getEmail());
                    pstm.setString(4, staff.getPassword());
                    pstm.setString(5, staff.getPhone());
                    pstm.setString(6, staff.getAddress());
                    pstm.setBoolean(7, staff.isActive());
                    pstm.setString(8, staff.getActiveCode());
                    pstm.setTimestamp(9, new Timestamp(currentTime.getTime()));
                    pstm.setString(10, staff.getRol().getId());
                    pstm.setBoolean(11, staff.isDelete());
                    pstm.setString(12, staff.getId());
                    
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
    public Optional<Staff> getEntity(Object id) {
        Optional<Staff> optStaff = Optional.empty();
        Optional<DataSource> optDs = getDataSource();
        Optional<Connection> optConn = Optional.empty();
        
        String query = "SELECT id, first_name, last_name, email, password, phone, address, isActive, activeCode, last_update, rol_id, isDelete FROM staff WHERE id = ?";
        
        if(optDs.isPresent()) {
            try {
                optConn = Optional.ofNullable(optDs.get().getConnection());
                
                if(optConn.isPresent()) {
                    PreparedStatement pstm = optConn.get().prepareStatement(query);
                    pstm.setString(1, (String)id);
                    
                    ResultSet rs = pstm.executeQuery();
                    
                    //  get Rols
                    RolDao rolDao = new RolImp();
                    
                    while(rs.next()){
                        Staff staff = new Staff();
                        staff.setId(rs.getString(1));
                        staff.setFirstName(rs.getString(2));
                        staff.setLastName(rs.getString(3));
                        staff.setEmail(rs.getString(4));
                        staff.setPassword(rs.getString(5));
                        staff.setPhone(rs.getString(6));
                        staff.setAddress(rs.getString(7));
                        staff.setActive(rs.getBoolean(8));
                        staff.setActiveCode(rs.getString(9));
                        staff.setLastUpdate(rs.getTimestamp(10));
                        staff.setDelete(rs.getBoolean(12));
                        
                        // get staff's rol
                        staff.setRol(rolDao.getEntity(rs.getString(11)).get());
                        
                        optStaff = Optional.ofNullable(staff);
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
        return optStaff;
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
