package com.github.monsterhxw.chapter02.section14.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * @author XueweiHuang
 * @created 2022-03-31
 */
public interface Aspect {

    static <T> T getProxy(Class<T> proxyClazz, String... aspectClassNames) {
        try {
            T target = proxyClazz.getDeclaredConstructor().newInstance();
            var aspectList = new ArrayList<Aspect>();
            for (String aspectClassName : aspectClassNames) {
                var aspect = (Aspect) Class.forName(aspectClassName).getDeclaredConstructor().newInstance();
                aspectList.add(aspect);
            }
            return (T) Proxy.newProxyInstance(proxyClazz.getClassLoader(), proxyClazz.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("proxy call method is: " + method.getName());
                    aspectList.stream().forEach(Aspect::before);
                    Object result = method.invoke(target, args);
                    aspectList.stream().forEach(Aspect::after);
                    return result;
                }
            });
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("error");
    }

    void before();

    void after();
}
