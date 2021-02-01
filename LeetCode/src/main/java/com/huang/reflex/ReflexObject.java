package com.huang.reflex;

import java.lang.reflect.Field;

/**
 * @author: I325805
 * @description:
 */public class ReflexObject {
     public String publicProp;
     private String privateProp;

     public ReflexObject(String publicProp, String privateProp){
         this.privateProp = privateProp;
         this.publicProp = publicProp;
     }

    public String getPublicProp() {
        return publicProp;
    }

    public void setPublicProp(String publicProp) {
        this.publicProp = publicProp;
    }

    public String getPrivateProp() {
        return privateProp;
    }

    public void setPrivateProp(String privateProp) {
        this.privateProp = privateProp;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ReflexObject object = new ReflexObject("public","private");

        Class reflexObjectClass = object.getClass();
        Field field = reflexObjectClass.getField("publicProp");
        Object publicProperty = field.get(object);
        System.out.println(publicProperty);
    }
}
