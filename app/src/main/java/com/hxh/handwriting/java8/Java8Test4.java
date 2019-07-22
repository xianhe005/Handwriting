package com.hxh.handwriting.java8;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by HXH at 2019/7/22
 * method reference
 * 1.类名::静态方法名
 * 2.类名::实例方法名(lambda方法的第一个参数做为实例方法调用者，后面的所有参数做为实例方法的参数)
 * 3.实例对象::实例方法名
 * 4.类名::构造方法
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class Java8Test4 {

    public static void main(String[] args) {
        List<Person> list = newList();
        /*list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Person.compareByName(o1, o2);
            }
        });*/
        // 1.类名::静态方法名
        list.sort(Person::compareByName);
        list.forEach(person -> System.out.println(person.getName()));
        System.out.println("--------------------");
        list = newList();
        //list.forEach(person -> System.out.println(person.getName()));
        /*list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.compareByName2(o2);
            }
        });*/
        // 2.类名::实例方法名
        list.sort(Person::compareByName2);
        list.forEach(person -> System.out.println(person.getName()));
        System.out.println("--------------------");
        list = newList();
        /*list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.compareByAge(o1, o2);
            }
        });*/
        // 3.实例对象::实例方法名
        Person p = new Person();
        list.sort(p::compareByAge);
        list.forEach(person -> System.out.println(person.getAge()));
        System.out.println("--------------------");
        list = newList();
        // 4.类名::构造方法
        Supplier<Person> supplier = Person::new;
        list.sort(supplier.get()::compareByAge);
        list.forEach(person -> System.out.println(person.getAge()));
    }

    private static List<Person> newList() {
        return Arrays.asList(
                new Person("zhangsan", 20),
                new Person("lisi", 14),
                new Person("wangwu", 23),
                new Person("zhaoliu", 29));
    }


    private static class Person {

        private String name;
        private int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public static int compareByName(Person p1, Person p2) {
            return p1.getName().compareTo(p2.getName());
        }

        public int compareByName2(Person p) {
            return this.getName().compareTo(p.getName());
        }

        public int compareByAge(Person p1, Person p2) {
            return p1.getAge() - p2.getAge();
        }
    }
}
