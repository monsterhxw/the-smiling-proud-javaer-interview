package com.github.monsterhxw.chapter02.section14.aspect;

/**
 * @author XueweiHuang
 * @created 2022-03-31
 */
public class LogAspect implements Aspect {

    @Override
    public void before() {
        System.out.println("-> Log aspect before method.");
    }

    @Override
    public void after() {
        System.out.println("-> Log aspect after method.");
    }
}
