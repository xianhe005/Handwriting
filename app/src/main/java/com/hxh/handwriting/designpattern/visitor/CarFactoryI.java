package com.hxh.handwriting.designpattern.visitor;

/**
 * Created by HXH at 2019/6/10
 * 被访问的接口
 */
public interface CarFactoryI {
    void info();

    void accept(VisitorI visitorI);
}
