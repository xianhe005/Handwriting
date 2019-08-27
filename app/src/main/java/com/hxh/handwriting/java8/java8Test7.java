package com.hxh.handwriting.java8;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HXH at 2019/8/26
 * Comparator
 */
public class java8Test7 {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        Collections.sort(list);
        System.out.println(list);
        System.out.println("----------------");
        list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        Collections.sort(list, (o1, o2) -> o2.length() - o1.length());
        System.out.println(list);
        System.out.println("----------------");
        list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        Collections.sort(list, Comparator.comparingInt(String::length));
        //同上
        //Collections.sort(list, Comparator.comparingInt(value -> value.length()));
        System.out.println(list);
        System.out.println("----------------");
        list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        Collections.sort(list, Comparator.comparingInt(String::length).reversed());
        // 错误，类型推断不出
        //Collections.sort(list, Comparator.comparingInt(value -> value.length()).reversed());
        // 这样才对,显示指定
        //Collections.sort(list, Comparator.comparingInt((String value) -> value.length()).reversed());
        // 或者
        //Comparator<String> c = Comparator.comparingInt(value -> value.length());
        //Collections.sort(list, c.reversed());
        System.out.println(list);
        System.out.println("----------------");
        list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        list.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println(list);
        System.out.println("----------------");
        list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        list.sort(Comparator.comparingInt(String::length).reversed()
                .thenComparing(String.CASE_INSENSITIVE_ORDER));
        // 同上
        /*list.sort(Comparator.comparingInt(String::length).reversed()
                .thenComparing(String::compareTo));*/
        System.out.println(list);
        System.out.println("----------------");
        list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        list.sort(Comparator.comparingInt(String::length).reversed()
                .thenComparing(Comparator.comparing(String::toLowerCase)));
        System.out.println(list);
        System.out.println("----------------");
        list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        list.sort(Comparator.comparingInt(String::length).reversed()
                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        System.out.println(list);
        System.out.println("----------------");
        list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        Collections.sort(list, Comparator.comparingInt(String::length).reversed()//welcome, nihao, hello, wrold
                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())) //对上次比较compareTo为0的排序：wrold, nihao, hello
                .thenComparing(Comparator.reverseOrder()));// 上面的比较compareTo为0排序，没有为0的情况，所以结果和上面的一样
        System.out.println(list);


    }
}
