package com.github.monsterhxw.chapter02.section14.order;

import com.github.monsterhxw.chapter02.section14.aspect.TimeUsageAspect;
import com.github.monsterhxw.chapter02.section14.aspect.annotation.Aspect;

import java.util.concurrent.TimeUnit;

/**
 * @author XueweiHuang
 * @created 2022-03-31
 */
@Aspect(TimeUsageAspect.class)
public class Order implements IOrder {

    private int state = 0;

    @Override
    public void pay() {
        try {
            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.state = 1;
    }

    @Override
    public void show() {
        System.out.format("Order status is: %d\n", this.state);
    }
}
