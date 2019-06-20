package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HXH at 2019/6/19
 * LeetCode第121号问题：买卖股票的最佳时机
 */
public class MaxProfit {

    @SuppressWarnings("unchecked")
    public static void test() {
        //int[] prices = {7, 1, 5, 3, 6, 4};
        //int[] prices = {1, 2, 3, 4, 5};
        int[] prices = {1, 2, 3, 6, 5};
        //int[] prices = {7, 6, 4, 3, 1};
        int[] v = maxProfit(prices);
        if (v[0] == -1) {
            System.out.println("没有交易完成, 所以最大利润为 0");
        } else {
            System.out.println("在第" +
                    (v[0] + 1) +
                    "天（股票价格 = " +
                    prices[v[0]] +
                    "）的时候买入，在第" +
                    (v[1] + 1) +
                    "天（股票价格 = " +
                    prices[v[1]] +
                    "）的时候卖出，最大利润 = " +
                    prices[v[1]] +
                    " - " +
                    prices[v[0]] +
                    " = " +
                    (prices[v[1]] - prices[v[0]]));
        }

        System.out.println("==============================================");
        //int[] prices = {7, 6, 4, 3, 1};
        Object[] v2 = maxProfit2(prices);
        if (v2[0] == null) {
            System.out.println("没有交易完成, 所以最大利润为 0");
        } else {
            List<Integer> buyIndexs = (List<Integer>) v2[0];
            List<Integer> sellIndexs = (List<Integer>) v2[1];
            int sells = (int) v2[2];
            for (int i = 0; i < buyIndexs.size(); i++) {
                System.out.println("在第" +
                        (buyIndexs.get(i) + 1) +
                        "天（股票价格 = " +
                        prices[buyIndexs.get(i)] +
                        "）的时候买入，在第" +
                        (sellIndexs.get(i) + 1) +
                        "天（股票价格 = " +
                        prices[sellIndexs.get(i)] +
                        "）的时候卖出，这笔交易所能获得利润 = " +
                        prices[sellIndexs.get(i)] +
                        " - " +
                        prices[buyIndexs.get(i)] +
                        " = " +
                        (prices[sellIndexs.get(i)] - prices[buyIndexs.get(i)]));
            }
            System.out.println("最大利润 = " + sells);
        }
        System.out.println("==============================================");
        prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int profit3 = maxProfit3(prices);
        System.out.println(profit3);
    }

    //买卖股票的最佳时机
    private static int[] maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return new int[]{-1, -1, 0};
        }
        int buy = prices[0];
        int sell = 0;
        int buyIndex = 0;
        int sellIndex = -1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
                buyIndex = i;
            }
            if (prices[i] > buy && prices[i] > sell) {
                sell = prices[i] - buy;
                sellIndex = i;
            }
        }
        if (sellIndex > 0) {
            return new int[]{buyIndex, sellIndex, sell};
        }
        return new int[]{-1, -1, 0};
    }

    // 买卖股票的最佳时机 II
    private static Object[] maxProfit2(int[] prices) {
        List<Integer> buyIndexs = new ArrayList<>();
        List<Integer> sellIndexs = new ArrayList<>();
        if (prices.length <= 1) {
            return new Object[]{null, null, 0};
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit = profit + prices[i] - prices[i - 1];
                buyIndexs.add(i - 1);
                sellIndexs.add(i);
            }
        }
        if (buyIndexs.size() > 0 && buyIndexs.size() == sellIndexs.size()) {
            return new Object[]{buyIndexs, sellIndexs, profit};
        }
        return new Object[]{null, null, 0};
    }

    // 买卖股票的最佳时机 III 很难理解。。。
    private static int maxProfit3(int[] prices) {
        int fstBuy = Integer.MAX_VALUE, fstSell = 0;
        int secBuy = Integer.MAX_VALUE, secSell = 0;
        for (int price : prices) {
            if (price < fstBuy) {
                fstBuy = price;
            }
            if (price - fstBuy > fstSell) {
                fstSell = price - fstBuy;
            }
            if (price - fstSell < secBuy) {
                secBuy = price - fstSell;
            }
            if (price - secBuy > secSell) {
                secSell = price - secBuy;
            }

            //fstBuy = Math.max(fstBuy, -price);
            //fstSell = Math.max(fstSell, fstBuy +price);
            //secBuy = Math.max(secBuy, fstSell -  price);
            //secSell = Math.max(secSell, secBuy +  price);
        }
        return secSell;

    }
}
