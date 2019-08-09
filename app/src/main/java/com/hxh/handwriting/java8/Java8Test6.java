package com.hxh.handwriting.java8;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by HXH at 2019/8/5
 * stream:
 * 短路逻辑
 * flatMap
 * distinct
 * sort map
 * groupingBy
 * partitioningBy
 * joining
 * 组函数
 *
 */
public class Java8Test6 {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        //先要limit
        //Stream.iterate(1, v -> v + 2).forEach(System.out::println);
        //Stream.iterate(1, v -> v + 2).filter(v->v>10).forEach(System.out::println);
        Stream.iterate(1, v -> v + 2).limit(10).forEach(System.out::println);

        System.out.println("===========limit============");
        Stream.iterate(1, v -> v + 2).limit(10).filter(integer -> integer > 10)
                .skip(2).limit(2).forEach(System.out::println);

        System.out.println("===========findFirst============");
        List<String> list = Arrays.asList("hello", "world", "hello world");
        //list.stream().peek(System.out::println).count();
        list.stream().map(s -> {
            // attention:这里遵循短路逻辑
            System.out.println(s);
            return s.length();
        }).filter(integer -> integer == 5).findFirst().ifPresent(System.out::println);
        list = Arrays.asList("hello3", "world", "hello world");
        list.stream().map(s -> {
            // attention:这里遵循短路逻辑
            System.out.println(s);
            return s.length();
        }).filter(integer -> integer == 5).findFirst().ifPresent(System.out::println);
        list = Arrays.asList("hello", "world", "hello world");
        list.stream().map(s -> {
            // attention:这里遵循短路逻辑
            System.out.println(s);
            return s.length();
        }).filter(integer -> integer == 5).limit(2).forEach(System.out::println);

        System.out.println("===========flatMap & distinct============");
        list = Arrays.asList("hello world", "world welcome hello", "hello world");
        list.stream().map(s -> s.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);

        System.out.println("===========collect groupingBy============");
        List<Student> list2 = Arrays.asList(
                new Student("zhangsan", 100, 20),
                new Student("lisi", 90, 21),
                new Student("zhangsan", 84, 22),
                new Student("wangwu", 90, 23),
                new Student("lisi", 60, 24),
                new Student("zhangsan", 60, 25),
                new Student("zhangsan", 60, 26));

        Map<String, List<Student>> map = list2.stream().collect(Collectors.groupingBy(student -> student.name));
        System.out.println(map);

        // sort map
        Set<Map.Entry<String, List<Student>>> set = map.entrySet();
        ArrayList<Map.Entry<String, List<Student>>> list3 = new ArrayList<>(set);
        Collections.sort(list3, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));
        list3.forEach(e -> System.out.println(e.getKey() + "," + e.getValue()));

        Map<String, List<Student>> map2 = list2.stream().filter(student -> student.score > 60)
                .collect(Collectors.groupingBy(student -> student.name));
        System.out.println(map2);

        Map<Integer, List<Student>> map3 = list2.stream().collect(Collectors.groupingBy(student -> student.score));
        System.out.println(map3);

        System.out.println("===========collect groupingBy & counting============");
        Map<String, Long> map4 = list2.stream().collect(Collectors.groupingBy(student -> student.name, Collectors.counting()));
        System.out.println(map4);

        System.out.println("===========collect groupingBy & averaging============");
        Map<String, Double> map5 = list2.stream().collect(Collectors.groupingBy(student -> student.name, Collectors.averagingDouble(st -> st.score)));
        System.out.println(map5);

        System.out.println("===========collect groupingBy & inner groupingBy============");
        Map<String, Map<Integer, List<Student>>> map6 = list2.stream().collect(Collectors.groupingBy(student -> student.name,
                Collectors.groupingBy(student -> student.score)));
        System.out.println(map6);

        System.out.println("===========collect groupingBy & 自定义============");
        Map<String, List<Student>> map7 = list2.stream()
                .collect(Collectors.groupingBy(student -> student.score >= 90 ? "优秀" : (student.score >= 70 ? "良" : "差")));
        System.out.println(map7);

        System.out.println("===========collect partitioningBy");
        Map<Boolean, List<Student>> map8 = list2.stream().collect(Collectors.partitioningBy(student -> student.score > 60));
        System.out.println(map8);

        System.out.println("===========collect partitioningBy & inner partitioningBy============");
        Map<Boolean, Map<Boolean, List<Student>>> map9 = list2.stream()
                .collect(Collectors.partitioningBy(student -> student.score > 60,
                        Collectors.partitioningBy(student -> student.name.contains("s"))));
        System.out.println(map9);

        System.out.println("===========joining============");
        String s = list2.stream().map(student -> student.name).collect(Collectors.joining());
        System.out.println(s);
        String s2 = list2.stream().map(student -> student.name).collect(Collectors.joining(","));
        System.out.println(s2);
        String s3 = list2.stream().map(student -> {
            System.out.println(student.name);
            return student.name;
        }).distinct().collect(Collectors.joining(",", " ===start===", " ===end==="));
        System.out.println(s3);

        System.out.println("===========组函数============");
        System.out.println("总数1：" + list2.stream().count());
        System.out.println("总数2：" + list2.stream().collect(Collectors.counting()));

        System.out.println("平均值：" + list2.stream().collect(Collectors.averagingDouble(student -> student.score)));

        list2.stream().map(student -> student.score)
                .collect(Collectors.maxBy(Integer::compareTo)).ifPresent(v -> System.out.println("最大值1：" + v));
        list2.stream().map(student -> student.score)
                .max(Integer::compareTo).ifPresent(v-> System.out.println("最大值2：" + v));

        list2.stream().map(student -> student.score)
                .collect(Collectors.minBy(Integer::compareTo)).ifPresent(v -> System.out.println("最小值1：" + v));
        list2.stream().map(student -> student.score)
                .min(Integer::compareTo).ifPresent(v-> System.out.println("最小值2：" + v));

        Double collectSum = list2.stream().collect(Collectors.summingDouble(student -> student.score));
        double reduceSum = list2.stream().mapToDouble(student -> student.score).reduce(0, (x, y) -> x + y);
        System.out.println("总和1：" + collectSum);
        System.out.println("总和2：" + reduceSum);

    }

    private static class Student {
        private String name;
        private int score;
        private int age;

        public Student(String name, int score, int age) {
            this.name = name;
            this.score = score;
            this.age = age;
        }

        @Override
        public String toString() {
            return "{" + name + "," + score + "," + age + "}";
        }
    }
}
