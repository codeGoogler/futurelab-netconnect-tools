package com.net.tools.futurelabnetconnecttools.common;

import java.lang.annotation.*;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJson {
    String value();
}
