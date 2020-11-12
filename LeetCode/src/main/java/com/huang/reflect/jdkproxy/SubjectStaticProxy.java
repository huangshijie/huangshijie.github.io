package com.huang.reflect.jdkproxy;

import com.huang.reflect.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: I325805
 * @description:
 */
public class SubjectStaticProxy implements InvocationHandler {

    private Subject subject;

    public SubjectStaticProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------------begin-------------");
        Object invoke = method.invoke(subject, args);
        System.out.println("--------------end-------------");
        return invoke;
    }

    public static void main(String[] args) {
        Subject subject = new SubjectImp();
        InvocationHandler subjectProxy = new SubjectStaticProxy(subject);
        Subject proxyInstance = (Subject) Proxy.newProxyInstance(subjectProxy.getClass().getClassLoader(), subject.getClass().getInterfaces(), subjectProxy);
        proxyInstance.sayHello("world");
    }
}
