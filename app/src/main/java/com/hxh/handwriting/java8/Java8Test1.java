package com.hxh.handwriting.java8;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by HXH at 2019/7/17
 * forEach、map、filter
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class Java8Test1 {

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

        // before and then:通过Consumer对象c2调用andThen方法传入Consumer对象c3,
        // 在andThen方法体中新创建Consumer对象c1,最终list.forEach遍历c1,在c1.accept方法中，
        // 先调用c1的外部类对象c2的accept方法,再调用对象c3的accept方法,从而实现链式调用
        // 注意观察日志中的c1,c2,c3对象地址
        Consumer<Person> c1;
        Consumer<Person> c2;
        Consumer<Person> c3;
        list.forEach(c1 = (c2 = new Consumer<Person>() {//对象c2
            @Override
            public void accept(Person person) {
                System.out.println("before:" + person + " this:" + this);
            }

            // for innerclass invoke
            void accept2(Person person) {
                accept(person);
            }

            @Override
            public Consumer<Person> andThen(Consumer<? super Person> after) {
                Objects.requireNonNull(after);
                System.out.println("andThen c2 this:" + this);
                return new Consumer<Person>() {
                    @Override
                    public void accept(Person t) {
                        System.out.println("andThen c1 this:" + this);
                        accept2(t);
                        after.accept(t);
                    }
                };
            }
        }).andThen((c3 = new Consumer<Person>() {//对象c3
            @Override
            public void accept(Person person) {
                System.out.println("andThen c3:" + person + " this:" + this);
            }
        })));
        System.out.println("c1:" + c1);
        System.out.println("c2:" + c2);
        System.out.println("c3:" + c3);

        System.out.println("========================");

        // forEach andThen 简化
        list.forEach(((Consumer<Person>) person -> System.out.println("before:" + person))
                .andThen(person -> System.out.println("andThen:" + person)));

        System.out.println("========================");

        // 关于new 构造方法{} 生成的匿名内部类的插曲
        MyConsumer mc1 = new MyConsumer();
        MyConsumer mc2 = new MyConsumer() {
        };
        System.out.println(mc1);//MyConsumer的实例
        System.out.println(mc1.getClass());//class com.hxh.handwriting.java8.Java8Test1$MyConsumer
        System.out.println(mc2);//MyConsumer匿名内部类实例
        System.out.println(mc2.getClass());//class com.hxh.handwriting.java8.Java8Test1$3
        System.out.println(mc2.getClass().getSuperclass());//class com.hxh.handwriting.java8.Java8Test1$MyConsumer

        System.out.println("========================");

        // forEach andThen 实例化版
        list.forEach(new MyConsumer().addThen(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println("my andThen:" + person + " this:" + this);
            }
        }));

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

    private static class MyConsumer implements Consumer<Person> {

        @Override
        public void accept(Person t) {
            System.out.println("my before:" + t + " this:" + this);
        }

        Consumer<Person> addThen(Consumer<Person> after) {
            return new Consumer<Person>() {
                @Override
                public void accept(Person t) {
                    MyConsumer.this.accept(t);
                    after.accept(t);
                }
            };
        }
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
