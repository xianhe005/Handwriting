package com.hxh.handwriting.javase;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by HXH at 2019/8/28
 * TODO
 */
public class ReflectTypeTest {

    public static void main(String[] args) {
        TypeTest<String, Integer> typeTest = new TypeTest<String, Integer>() {};
        Type[] types = getClassParamTypes(typeTest);
        if (types != null) {
            for (Type type : types) {
                System.out.println(type instanceof Class<?>);
                System.out.println(type);
            }
        }
        System.out.println("----------------------------------");
        Type type = getClassType(typeTest);
        System.out.println(type);

    }

    private abstract static class TypeTest<T, R> implements TypeTestListener<T> {
    }

    public interface TypeTestListener<R> {

    }

    public static Type[] getClassParamTypes(Object obj) {
        // 父类泛型
        Type type = obj.getClass().getGenericSuperclass();
        if (type != null) {
            return ((ParameterizedType) type).getActualTypeArguments();
        }
        return null;
    }

    // 获取声明泛型的类/接口
    public static Type getClassType(Object obj) {
        // 父类泛型
        Type type = obj.getClass().getGenericSuperclass();
        if (type != null) {
            return ((ParameterizedType) type).getRawType();
            // 获取泛型的拥有者，“拥有者”表示的含义--内部类的“父类”
            //return ((ParameterizedType) type).getOwnerType();
        }
        return null;
    }
}
