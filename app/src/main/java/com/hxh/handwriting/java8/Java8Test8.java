package com.hxh.handwriting.java8;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by HXH at 2019/8/26
 * user-defined Collector
 */
@TargetApi(Build.VERSION_CODES.N)
public class Java8Test8 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "wrold", "welcome");
        Set<String> set = list.stream().collect(new MySetCollector<>());
        System.out.println(set);
    }

    private static class MySetCollector<T> implements Collector<T, Set<T>, Set<T>> {

        @Override
        public Supplier<Set<T>> supplier() {
            System.out.println("supplier invoked!");
            return HashSet::new;
        }

        @Override
        public BiConsumer<Set<T>, T> accumulator() {
            System.out.println("accumulator invoked!");
            return Set::add;
            //这样是不对的
            //return HashSet::add;
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
        public Function<Set<T>, Set<T>> finisher() {
            System.out.println("finisher invoked!");
            //return t -> t;
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            System.out.println("characteristics invoked!");
            return Collections.unmodifiableSet(
                    EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
        }
    }
}
