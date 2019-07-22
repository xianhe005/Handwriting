package com.hxh.handwriting.java8;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by HXH at 2019/7/22
 * Supplier、Optional
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class Java8Test3 {

    private int age;

    private Java8Test3() {
        System.out.println("constructor");
    }

    private Java8Test3(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        /*Supplier<Java8Test3> supplier = new Supplier<Java8Test3>() {
            @Override
            public Java8Test3 get() {
                return new Java8Test3();
            }
        };*/
        // 方法引用：类名::构造方法
        Supplier<Java8Test3> supplier = Java8Test3::new;
        //调用get()方法，此时会调用对象的构造方法，即获得到真正对象
        System.out.println(supplier.get());
        System.out.println("-----------------------");
        //每次get都会调用构造方法，即获取的对象不同
        System.out.println(supplier.get());
        // 带参数的只能用lambda表达式
        Supplier<Java8Test3> supplier2 = () -> new Java8Test3(3);

        System.out.println("Optional-----------------------");
        Optional<String> optional = Optional.empty();
        // Exception in thread "main" java.util.NoSuchElementException: No value present
        //System.out.println(optional.get());

        /*if (optional.isPresent()) {
            System.out.println(optional.get());
        }*/
        // 上面的用下面的替换
        //optional.ifPresent(s -> System.out.println(s));
        // 上面的lambda表达式可以用方法引用,引用对象(实际对象)::方法名
        optional.ifPresent(System.out::println);
        System.out.println("----------------------");
        optional = Optional.of("hello");
        optional.ifPresent(System.out::println);
        System.out.println("----------------------");
        optional = Optional.ofNullable(null);
        optional.ifPresent(System.out::println);
        System.out.println("----------------------");
        optional = Optional.ofNullable("world");
        optional.ifPresent(System.out::println);
        System.out.println("----------------------");
        optional = Optional.ofNullable(null);
        System.out.println(optional.orElse("hello world"));
        System.out.println(optional.orElseGet(() -> "hello"));
        optional = Optional.ofNullable("test");
        System.out.println(optional.orElse("hello world"));
        System.out.println(optional.orElseGet(() -> "hello"));
        //optional.filter(s -> Objects.isNull(s));
        System.out.println("----------------------");
        optional = Optional.ofNullable(null);
        optional.filter(Objects::isNull).ifPresent(System.out::println);
        optional.filter(s -> !Objects.isNull(s)).ifPresent(System.out::println);
        System.out.println("----------------------");
        optional = Optional.ofNullable("hello");
        //optional.filter(Objects::isNull).ifPresent(System.out::println);//无输出
        optional.filter(s -> !Objects.isNull(s)).ifPresent(System.out::println);
        optional.filter(s -> s.contains("h")).ifPresent(System.out::println);
        optional.filter(s -> s.contains("a")).ifPresent(System.out::println);//无输出
        System.out.println("----------------------");
        optional = Optional.ofNullable("hello");
        /*optional.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return null;
            }
        });*/
        //optional.map(s -> s.length());
        optional.map(String::length).ifPresent(System.out::println);
        optional.map(String::length).filter(integer -> integer > 5).ifPresent(System.out::println);//无输出
        optional.map(String::length).filter(integer -> integer < 5).ifPresent(System.out::println);//无输出
        optional.map(String::length).filter(integer -> integer == 5).ifPresent(System.out::println);
        System.out.println("----------------------");
        optional = Optional.ofNullable("hello");
        /*optional.flatMap(new Function<String, Optional<Integer>>() {
            @Override
            public Optional<Integer> apply(String s) {
                return Optional.empty();
            }
        });*/
        optional.flatMap(s -> Optional.ofNullable(s.length() == 5)).ifPresent(System.out::println);
        optional.flatMap(s -> Optional.ofNullable(s.length() != 5)).ifPresent(System.out::println);
        optional.flatMap(s -> Optional.ofNullable(null)).ifPresent(System.out::println);//无输出
        //optional.flatMap(s -> null).ifPresent(System.out::println);//抛异常
    }
}
