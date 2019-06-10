package com.hxh.handwriting.designpattern.visitor;

/**
 * Created by HXH at 2019/6/10
 * 访问者接口
 */
public interface VisitorI {
    void visit(BWMFactory bwmFactory);

    void visit(BenzFactory benzFactory);
}
