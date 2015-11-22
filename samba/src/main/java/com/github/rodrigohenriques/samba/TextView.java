package com.github.rodrigohenriques.samba;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface TextView {
    int value() default 0;
}
