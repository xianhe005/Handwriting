package com.hxh.handwriting.java8;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;

/**
 * Created by HXH at 2019/7/17
 * forEach、map、filter
 */
public class Java8Test1 {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        Person p1 = new Person("zhangsan", 18);
        Person p2 = new Person("lisi", 28);
        Person p3 = new Person("wangwu", 20);
        List<Person> list = Arrays.asList(p1, p2, p3);

        // 遍历
        list.forEach(person -> System.out.println(person));

        System.out.println("========================");

        list.forEach(System.out::println);

        System.out.println("========================");

        // 变换
        list.stream().map(person -> person.username.length()).forEach(System.out::println);

        System.out.println("========================");

        // 筛选过滤
        list.stream().filter(person -> person.age >= 20).forEach(System.out::println);

        System.out.println("========================");

        // 筛选过滤 + 变换
        list.stream().filter(person -> person.age >= 20)
                .map(person -> person.username.length()).forEach(System.out::println);
    }

    private static class Person {
        private String username;
        private int age;

        public Person(String username, int age) {
            this.username = username;
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
