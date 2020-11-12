package com.huang.Stream;

/**
 * @Author : I325805
 * @Description:
 */
public class SaleInvoice extends Invoice{
    SaleInvoice(){
        num++;
    }
    public String formatId(String id) {
        num++;
        return id+"_s";
    }

    public static void main(String[] args) {
        System.out.println(getNum());
        SaleInvoice c = new SaleInvoice();
        System.out.println(getNum());

        int i = 10,j=12;
        for(;;){
            if(i++<j--){
                continue;
            }else {
                break;
            }
        }
    }
}
