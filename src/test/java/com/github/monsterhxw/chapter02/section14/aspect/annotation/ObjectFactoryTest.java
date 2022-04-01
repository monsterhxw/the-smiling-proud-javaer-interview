package com.github.monsterhxw.chapter02.section14.aspect.annotation;

import com.github.monsterhxw.chapter02.section14.order.IOrder;
import com.github.monsterhxw.chapter02.section14.order.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author XueweiHuang
 * @created 2022-04-01
 */
class ObjectFactoryTest {

    @Test
    public void newInstance() {
        IOrder order = ObjectFactory.newInstance(Order.class);
        order.pay();
        order.show();
    }
}