package com.hxh.handwriting.java8;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created by HXH at 2019/9/3
 * Function
 */
@TargetApi(Build.VERSION_CODES.N)
public class FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> f = s -> ++s;
        Function<Integer, Integer> g = s -> s * 2;

        /*
         * 下面表示在执行F时，先执行G，并且执行F时使用G的输出当作输入。
         * 相当于以下代码：
         * Integer a = g.apply(1);
         * System.out.println(f.apply(a));
         */
        System.out.println(f.compose(g).apply(1));
        System.out.println("-------------");

        /*
         * 表示执行F的Apply后使用其返回的值当作输入再执行G的Apply；
         * 相当于以下代码
         * Integer a = f.apply(1);
         * System.out.println(g.apply(a));
         */
        System.out.println(f.andThen(g).apply(2));
        System.out.println("-------------");

        /*
         * identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
         */
        System.out.println(f.andThen(Function.identity()).apply(3));
        System.out.println(Function.identity().apply("b"));
        System.out.println("-------------");

        MyFunction<String, String> myFunction = s -> s + "a";
        MyFunction<Integer, String> before = integer -> "size:" + integer;
        MyFunction<String, Integer> after = String::length;

        System.out.println(myFunction.beforeAndThen(before, after).apply(10));
        //上面的等价于下面官方的写法,上面仅供学习实现
        System.out.println(myFunction.compose(before).andThen(after).apply(10));

    }


    public interface MyFunction<T, R> extends Function<T, R> {

        default <V, M> MyFunction<V, M> beforeAndThen(MyFunction<? super V, ? extends T> before,
                                                      MyFunction<? super R, ? extends M> after) {
            Objects.requireNonNull(before);
            Objects.requireNonNull(after);

            return (V v) -> {
                T t = before.apply(v);
                R r = apply(t);
                return after.apply(r);
            };
        }
    }
}
