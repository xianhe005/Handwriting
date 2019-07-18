package com.hxh.handwriting.java8;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by HXH at 2019/7/18
 * Predicate 谓语
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class Java8Test2 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Java8Test2 java8Test2 = new Java8Test2();
        java8Test2.conditionFilter(list, integer -> integer % 2 == 0);
        System.out.println("================================");

        java8Test2.conditionFilter(list, integer -> integer % 2 != 0);
        System.out.println("================================");

        java8Test2.conditionFilter(list, integer -> integer > 5);
        System.out.println("================================");

        java8Test2.conditionFilter(list, integer -> integer < 3);
        System.out.println("================================");

        java8Test2.conditionFilterAnd(list, integer -> integer > 5, integer -> integer % 2 == 0);
        System.out.println("================================");

        java8Test2.conditionFilterOr(list, integer -> integer > 5, integer -> integer < 3);
        System.out.println("================================");

        java8Test2.conditionFilterAndNegate(list, integer -> integer > 5, integer -> integer % 2 == 0);
        System.out.println("================================");

        System.out.println(Predicate.isEqual("test").test("test"));
        System.out.println(Predicate.isEqual("test").test("test2"));
        System.out.println(Predicate.isEqual(null).test("test2"));
        System.out.println(Predicate.isEqual(null).test(null));
    }

    private void conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer integer : list) {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private void conditionFilterAnd(List<Integer> list, Predicate<Integer> predicate,
                                    Predicate<Integer> predicate2) {
        for (Integer integer : list) {
            if (predicate.and(predicate2).test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private void conditionFilterOr(List<Integer> list, Predicate<Integer> predicate,
                                   Predicate<Integer> predicate2) {
        for (Integer integer : list) {
            if (predicate.or(predicate2).test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private void conditionFilterAndNegate(List<Integer> list, Predicate<Integer> predicate,
                                   Predicate<Integer> predicate2) {
        for (Integer integer : list) {
            if (predicate.and(predicate2).negate().test(integer)) {
                System.out.println(integer);
            }
        }
    }
}
