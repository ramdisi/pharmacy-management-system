package com.ramdisi.erp.validation;

import com.ramdisi.erp.model.dto.CashierStockDTO;

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
    //only static boolean methods here
}
