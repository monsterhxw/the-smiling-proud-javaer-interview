package com.github.monsterhxw.chapter02.section14.aspect.annotation;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author XueweiHuang
 * @created 2022-04-01
 */
public class ObjectFactory {

    public static <T> T newInstance(Class<T> clazz) {
        var annotations = clazz.getAnnotations();
        var aspectList = new ArrayList<com.github.monsterhxw.chapter02.section14.aspect.Aspect>();
        Arrays.stream(annotations).filter(a -> a instanceof Aspect).map(a -> (Aspect) a).map(Aspect::value).map(v -> {
            com.github.monsterhxw.chapter02.section14.aspect.Aspect aspect = null;
            try {
                aspect = v.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            return aspect;
        }).forEach(aspectList::add);
        if (aspectList.size() == 0) {
            return null;
        }
        T inst = null;
        try {
            inst = clazz.getConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        T finalInst = inst;
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                aspectList.forEach(com.github.monsterhxw.chapter02.section14.aspect.Aspect::before);
                var result = method.invoke(finalInst, args);
                aspectList.forEach(com.github.monsterhxw.chapter02.section14.aspect.Aspect::after);
                return result;
            }
        });
    }
}
