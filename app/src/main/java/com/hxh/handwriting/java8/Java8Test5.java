package com.hxh.handwriting.java8;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by HXH at 2019/7/22
 * stream
 * 1.数据源，如List
 * 2.0个或多个中间操作
 * 3.终止操作
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class Java8Test5 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");

        Optional<String> reduce = list.stream().reduce(new BinaryOperator<String>() {
            @Override
            public String apply(String s, String s2) {
                return s + s2;
            }
        });
        System.out.println(reduce.orElse("else"));
        System.out.println("----------------------------");
        System.out.println(list.stream().reduce("Add ", (s, s2) -> s + s2));
        System.out.println("----------------------------");
        BiFunction<Integer, ? super String, Integer> a = new BiFunction<Integer, String, Integer>() {
            @Override
            public Integer apply(Integer integer, String s) {
                return integer + s.length();
            }
        };
        BinaryOperator<Integer> b = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        };
        System.out.println(list.stream().reduce(2, a, b));
        System.out.println(list.stream().reduce(2,
                (integer, s) -> integer + s.length(),
                (integer, integer2) -> integer + integer2));
        System.out.println("----------------------------");
        Stream<String> stream = Stream.of("hello", "world", "hello world");
        stream.collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("----------------------------");
        // 如果Stream被操作，再操作会抛如下异常
        // java.lang.IllegalStateException: stream has already been operated upon or closed
        // 流是一次性操作？
        stream = Stream.of("hello", "world", "hello world");
        stream.sorted(String::compareToIgnoreCase).collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("----------------------------");
        stream = Stream.of("hello", "world", "hello world");
        /*stream.filter(s -> s.length() <= 5).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });*/
        stream.filter(s -> s.length() <= 5).map(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println("----------------------------");
        stream = Stream.of("hello", "world", "hello world");
        stream.filter(Test::filter).forEach(System.out::println);
        System.out.println("----------------------------");
        stream = Stream.of("hello", "world", "hello world", "");
        stream.filter(String::isEmpty).map(s -> s + "isEmpty").forEach(System.out::println);
        System.out.println("----------------------------");
        stream = Stream.of("hello", "world", "hello world", "", null);
        stream.filter(s -> s == null || s.isEmpty()).map(s -> "isEmpty").forEach(System.out::println);
        System.out.println("----------------------------");
        stream = Stream.of("hello", "world", "hello world", "hw");
        /*stream.flatMap(new Function<String, Stream<Integer>>() {
            @Override
            public Stream<Integer> apply(String s) {
                return Stream.of(s.length());
            }
        }).forEach(System.out::println);*/
        stream.flatMap(s -> Stream.of(s.length()))
                //.sorted(Integer::compareTo)
                .sorted((aa, bb) -> -aa.compareTo(bb))
                .forEach(System.out::println);
        System.out.println("----------------------------");
        Stream.of("hello", "world", "hello world", "hw").flatMapToInt(s -> IntStream.of(s.length()))
                .sorted()
                .forEach(System.out::println);
        System.out.println("----------------------------");
        IntStream.range(3, 9).forEach(System.out::println);
        System.out.println("----------------------------");
        IntStream.rangeClosed(3, 9).forEach(System.out::println);
        System.out.println("----------------------------");
        System.out.println(IntStream.rangeClosed(3, 9).count());
        System.out.println(IntStream.rangeClosed(3, 9).sum());
        System.out.println(IntStream.rangeClosed(3, 9).average().orElse(0));
        System.out.println(IntStream.rangeClosed(3, 9).max().orElse(0));
        System.out.println(IntStream.rangeClosed(3, 9).min().orElse(0));
        System.out.println(IntStream.rangeClosed(3, 9).summaryStatistics());
        System.out.println("----------------------------");
        Map<String, Integer> collect = Stream.of("hello", "world", "hello world", "hw")
                .collect(new Supplier<Map<String, Integer>>() {
                    @Override
                    public Map<String, Integer> get() {
                        return new HashMap<>();
                    }
                }, new BiConsumer<Map<String, Integer>, String>() {

                    @Override
                    public void accept(Map<String, Integer> stringIntegerMap, String s) {
                        stringIntegerMap.put(s, s.length());
                    }
                }, new BiConsumer<Map<String, Integer>, Map<String, Integer>>() {

                    @Override
                    public void accept(Map<String, Integer> stringIntegerMap, Map<String, Integer> stringIntegerMap2) {
                        stringIntegerMap.putAll(stringIntegerMap2);
                    }
                });
        System.out.println(collect.toString());
        HashMap<Object, Object> map = Stream.of("hello", "world", "hello world", "hw")
                .collect(HashMap::new, Java8Test5::put, HashMap::putAll);
        System.out.println(map.toString());
        System.out.println("----------------------------");
        System.out.println(Arrays.asList(Stream.of("hello", "world", "hello world", "hw").toArray()));
        /*Stream.of("hello", "world", "hello world", "hw").toArray(new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        });*/
        String[] strings = Stream.of("hello", "world", "hello world", "hw").toArray(String[]::new);
        System.out.println(Arrays.asList(strings));

        Integer[] integers = Stream.of("hello", "world", "hello world", "hw")
                .map(String::length)
                .toArray(Integer[]::new);
        System.out.println(Arrays.asList(integers));
        System.out.println("----------------------------");
        list.stream().flatMapToInt(s -> IntStream.of(s.length()))
                .forEach(System.out::println);
        System.out.println("----------------------------");
        System.out.println(Stream.of("hello", "world", "hello world", "how")
                .allMatch(s -> s.contains("o")));
        System.out.println(Stream.of("hello", "world", "hello world", "how")
                .allMatch(s -> s.contains("h")));
        System.out.println(Stream.of("hello", "world", "hello world", "how")
                .anyMatch(s -> s.contains("e")));
        System.out.println(Stream.of("hello", "world", "hello world", "how")
                .anyMatch(s -> s.contains("z")));
        System.out.println(Stream.of("hello", "world", "hello world", "how")
                .noneMatch(String::isEmpty));
        System.out.println(Stream.of("", "world", "hello world", "how")
                .noneMatch(String::isEmpty));
    }

    private static void put(HashMap<Object, Object> hashMap, String s) {
        hashMap.put(s, s.length());
    }

    private static class Test {
        static boolean filter(String s) {
            return s.contains("h");
        }
    }
}
