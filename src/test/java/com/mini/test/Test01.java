package com.mini.test;

import org.junit.jupiter.api.Test;

public class Test01 {
    @Test
    public void test01() {
        Integer a = 100;
        Integer b = 100;

        Integer c = 200;
        Integer d = 200;
        System.out.println(a == b);
        System.out.println(c.equals( d));
    }
}
