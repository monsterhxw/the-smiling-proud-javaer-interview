package com.github.monsterhxw.chapter02.section14.aspect;

/**
 * @author XueweiHuang
 * @created 2022-03-31
 */
public class TimeUsageAspect implements Aspect {

    private long startTime;

    @Override
    public void before() {
        System.out.println("-> Time usage aspect before method.");
        startTime = System.currentTimeMillis();
    }

    @Override
    public void after() {
        System.out.println("-> Time usage aspect after method.");
        var usage = System.currentTimeMillis() - startTime;
        System.out.format("Time usage is: %dms\n", usage);
    }
}
