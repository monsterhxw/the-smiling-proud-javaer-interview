package com.github.monsterhxw.chapter02.section14.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author XueweiHuang
 * @created 2022-03-31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
public @interface Aspect {

    Class<? extends com.github.monsterhxw.chapter02.section14.aspect.Aspect> value();
}
