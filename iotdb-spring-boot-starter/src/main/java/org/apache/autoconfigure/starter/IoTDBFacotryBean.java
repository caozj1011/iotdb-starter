package org.apache.autoconfigure.starter;

import org.apache.iotdb.session.Session;
import org.apache.iotdb.session.util.Version;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;


public class IoTDBFacotryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        Session session = new Session.Builder().host("127.0.0.1").port(6667).username("root").password("root").version(Version.V_0_13).build();
        session.open(false);
        session.setFetchSize(10000);
        // 能拿到接口的泛型 <T>
        ParameterizedType parameterizedType = (ParameterizedType) repositoryInterface.getGenericInterfaces()[0];
        Type type = parameterizedType.getActualTypeArguments()[0];
        Class clazz = (Class)type;
        return Proxy.newProxyInstance(repositoryInterface.getClassLoader(), new Class[]{repositoryInterface}, new IoTDBRepositoryImpl(session, clazz));
    }


    Class<?> repositoryInterface;

    public IoTDBFacotryBean(Class<?> repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }



    // getObject返回对象的类型
    @Override
    public Class<?> getObjectType() {
        return repositoryInterface;
    }
}
