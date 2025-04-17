package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    private Object targetObj;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行前置逻辑.....");
        Object invoke = method.invoke(targetObj, args);
        System.out.println("执行后置逻辑.....");
        return invoke;
    }

    public Object getInstance(Object targetObj){
        this.targetObj = targetObj;
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), this);
    }
}
