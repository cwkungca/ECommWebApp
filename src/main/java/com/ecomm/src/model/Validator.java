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

import java.util.Optional;

/**
 *
 * @author WayneKung-TPX
 */
public class Validator {

    // ensures input is secure
    public boolean validateInput(Optional<String> optInput) {
        boolean isValidated = false;
        
        if(optInput.isPresent()) {
            isValidated = true;
        }
        return isValidated;
    }
    
    // ensures that quantity input is number between 0 and 99
    // applies to quantity fields in cart page
    public boolean validateQuantity (String productId, String quantity) {
        boolean isValidated = true;
        
        if (!productId.isEmpty() && !quantity.isEmpty()) {
            int i = -1;

            try {
                i = Integer.parseInt(quantity);
                
            } catch (NumberFormatException nfe) {
                System.out.println("User did not enter a number in the quantity field");
            }

            if (i < 0 || i > 99) {
                isValidated = false;
            }
        }
        return isValidated;
    }
    
    public boolean validateForm(String name, String email, String phone, String address, String cityRegion, String ccNumber) {
    	boolean isValidated = true;
    	
    	return isValidated;
    }
}
