package com.hxh.handwriting.designpattern.visitor;

/**
 * Created by HXH at 2019/6/10
 * 被访问接口的实现
 */
public class BenzFactory implements CarFactoryI {
    @Override
    public void info() {
        System.out.println("奔驰工厂销量不错");
    }

    @Override
    public void accept(VisitorI visitorI) {
        visitorI.visit(this);
    }
}
