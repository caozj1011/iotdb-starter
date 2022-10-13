package org.apache.autoconfigure.starter;

import org.apache.iotdb.session.Session;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IoTDBRepositoryImpl implements InvocationHandler {
    Session session;
    Class pojoClass;


    public IoTDBRepositoryImpl(Session session, Class pojoClass) {
        this.session = session;
        this.pojoClass = pojoClass;
    }

    // method 当前调用的方法   = findById
    // args 当前调用方法的参数  = 1
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        // jpa统一实现类
        IoTDBJPAProxy myJpaProxy = new IoTDBJPAProxy(session, pojoClass);
        Method jpaMethod = myJpaProxy.getClass().getMethod(methodName, method.getParameterTypes());
        return jpaMethod.invoke(myJpaProxy,args);
    }
}
