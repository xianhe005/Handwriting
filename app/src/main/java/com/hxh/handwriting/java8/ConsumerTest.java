package com.hxh.handwriting.java8;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.function.Consumer;

/**
 * Created by HXH at 2019/9/3
 * Consumer
 */
public class ConsumerTest {

    @TargetApi(Build.VERSION_CODES.N)
    public static void main(String[] args) {

        Consumer<String> f = System.out::println;

        Consumer<String> f2 = n -> System.out.println(n + "-F2");

        f.accept("a");
        System.out.println("--------------");

        //执行完F后再执行F2的Accept方法
        f.andThen(f2).accept("b");
        System.out.println("--------------");
        //连续执行的Accept方法
        f.andThen(f).andThen(f).andThen(f2).andThen(f).accept("c");
    }
}
