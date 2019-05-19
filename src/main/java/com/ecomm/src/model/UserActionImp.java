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

/**
 *
 * @author WayneKung-TPX
 */
public class UserActionImp implements UserAction {
	
	public String encrypt(String plaintext) {
		StringBuffer sbCiphertext = new StringBuffer();
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte byteData[] = md.digest(plaintext.getBytes());
			
			for(int i= 0; i < byteData.length; i++){
				sbCiphertext.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sbCiphertext.toString();
	}

}
