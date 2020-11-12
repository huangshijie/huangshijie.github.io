package com.huang.reflect.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: I325805
 * @description:
 */
public class SubjectCGLibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("begin time -----> "+ System.currentTimeMillis());
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("end time -----> "+ System.currentTimeMillis());
        return o1;
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SubjectImp.class);
        enhancer.setCallback(new SubjectCGLibProxy());
        SubjectImp subjectImp = (SubjectImp) enhancer.create();
        subjectImp.sayHello("world");
    }
}
