package com.huang.Stream;

/**
 * @Author : I325805
 * @Description:
 */
public class Invoice {
    protected static int num=0;
    Invoice(){
        num++;
    }
    static int getNum(){
        return num;
    }
    public String formatId(String id) {
        return id+"_in";
    }
}
