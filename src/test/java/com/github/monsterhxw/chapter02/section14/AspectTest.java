package com.github.monsterhxw.chapter02.section14;

import com.github.monsterhxw.chapter02.section14.aspect.Aspect;
import com.github.monsterhxw.chapter02.section14.aspect.LogAspect;
import com.github.monsterhxw.chapter02.section14.aspect.TimeUsageAspect;
import com.github.monsterhxw.chapter02.section14.order.IOrder;
import com.github.monsterhxw.chapter02.section14.order.Order;
import org.junit.jupiter.api.Test;

/**
 * @author XueweiHuang
 * @created 2022-03-31
 */
class AspectTest {

    @Test
    public void aspectUsingProxy() {
        IOrder order = Aspect.getProxy(Order.class, TimeUsageAspect.class.getName(), LogAspect.class.getName());
        order.pay();
        order.show();
    }
}
