package com.ramdisi.erp.validation;

import com.ramdisi.erp.model.dto.CashierStockDTO;

import java.util.regex.Pattern;

public class Validator {
    public static int checkEligabilityToOrder(CashierStockDTO selectedItem, int orderQTY) {
        if (selectedItem.getAvailability().equals("available")){
            if (selectedItem.getAvailableQTY()>=orderQTY){
                return 0;
            }else {
                return 1;
            }
        }
        return 2;
    }

    public static int checkEligabilityToPay(String telNo, String payment, Double amount) {
        if (telNo == null) {
            return 0;
        } else if (payment==null) {
            return 1;
        }else if (!Pattern.matches("\\d+",telNo) | telNo.length()!=10){
            return 0;
        } else if (!Pattern.matches("\\d+",payment) ) {
            return 1;
        }else if (Double.parseDouble(payment)<amount){
            return 2;
        }else {
            return 3;
        }
    }
    //only static boolean methods here
}
