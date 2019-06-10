package com.hxh.handwriting.designpattern.visitor;

/**
 * Created by HXH at 2019/6/10
 * 实现一个访问者
 */
public class Visitor implements VisitorI {
    @Override
    public void visit(BWMFactory bwmFactory) {
        System.out.println("调查宝马工厂");
        bwmFactory.info();
    }

    @Override
    public void visit(BenzFactory benzFactory) {
        System.out.println("调查奔驰工厂");
        benzFactory.info();
    }
}
