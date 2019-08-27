package com.hxh.handwriting.java8;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by HXH at 2019/8/26
 * user-defined Collector2
 */
@TargetApi(Build.VERSION_CODES.N)
public class Java8Test9 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "wrold", "welcome", "a", "b", "hello");
        Set<String> set = new HashSet<>(list);
        System.out.println(set);
        Map<String, String> map = set.stream().collect(new MySetCollector2<>());
        System.out.println(map);
        System.out.println("-----------------------");
        for (int i = 0; i < 100; i++) {
            Map<String, String> map2 = set.parallelStream().collect(new MySetCollector2<>());
            System.out.println(map2);
        }
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    private static class MySetCollector2<T> implements Collector<T, Set<T>, Map<T, T>> {

        @Override
        public Supplier<Set<T>> supplier() {
            System.out.println("supplier invoked!");
            return HashSet::new;
        }

        @Override
        public BiConsumer<Set<T>, T> accumulator() {
            System.out.println("accumulator invoked!");
            return (ts, t) -> {
                System.out.println("accumulator:" + Thread.currentThread().getName());
                // characteristics包含Characteristics.CONCURRENT时:
                // 1、表示并行，只有一个中间容器
                // 2、不能在这里对中间容器进行额外的操作，比如toString,否则会出现并发操作异常
                //  java.util.ConcurrentModificationException: java.util.ConcurrentModificationException
                // 3、因为只有一个容器，combiner不会被执行
                // System.out.println("ts:" + ts);
                ts.add(t);
            };
            //return Set::add;
        }

        @Override
        public BinaryOperator<Set<T>> combiner() {
            System.out.println("combiner invoked!");
            return (ts, ts2) -> {
                ts.addAll(ts2);
                return ts;
            };
        }

        @Override
        public Function<Set<T>, Map<T, T>> finisher() {
            System.out.println("finisher invoked!");
            return set -> {
                Map<T, T> map = new HashMap<>();
                set.forEach(item -> map.put(item, item));
                return map;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            System.out.println("characteristics invoked!");
            return Collections.unmodifiableSet(
                    EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));
        }
    }
}
