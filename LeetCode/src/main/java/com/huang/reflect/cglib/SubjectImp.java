package com.huang.reflect.cglib;

import com.huang.reflect.Subject;

/**
 * @author: I325805
 * @description:
 */
public class SubjectImp implements Subject {

    @Override
    public void sayHello(String param) {
        System.out.println("cglib " + param);
    }
}
