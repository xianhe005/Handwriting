package com.hxh.handwriting.designpattern.visitor;

/**
 * Created by HXH at 2019/6/10
 * 访问者模式测试
 */
public class TestVisitor {

    public static void test() {
        VisitorI visitor = new Visitor();
        BWMFactory bwmFactory = new BWMFactory();
        bwmFactory.accept(visitor);
        BenzFactory benzFactory = new BenzFactory();
        benzFactory.accept(visitor);
    }
}
