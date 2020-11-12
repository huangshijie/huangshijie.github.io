package com.huang.reflect.jdkproxy;

import com.huang.reflect.Subject;

/**
 * @author: I325805
 * @description:
 */
public class SubjectImp implements Subject {

    @Override
    public void sayHello(String param) {
        System.out.println("subject implementation " + param);
    }
}
