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

package com.ecomm.src.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 *
 * @author WayneKung-TPX
 */
public class ToolLib {
    
    public Optional<String> encrypt(String plaintext){
        Optional<String> optCyphertext = Optional.empty();
        
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte byteData[] = md.digest(plaintext.getBytes());
            
            //convert the byte to hex format
            StringBuffer sb = new StringBuffer();
            for(int i= 0; i < byteData.length; i++){
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            optCyphertext = Optional.ofNullable(sb.toString());
            
        }catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return optCyphertext;
    }

}
