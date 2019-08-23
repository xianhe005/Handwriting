package com.hxh.handwriting.javase;

import java.lang.reflect.Field;

/**
 * Created by HXH at 2019/8/14
 * 反射测试
 */
public class ReflectTest {

    public static void main(String[] args) throws IllegalAccessException {
        Field[] fields = C.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            System.out.println(field.getType());
            System.out.println(field.getType().isAssignableFrom(String.class));
            System.out.println(field.getInt(field.getName()));
        }
    }

    private static class C {
        public static final int TEST = 1;
        public static final int TEST2 = 2;
        public static final String TEST3 = "3";
    }
}
