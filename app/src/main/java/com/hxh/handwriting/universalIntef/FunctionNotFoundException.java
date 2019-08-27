package com.hxh.handwriting.universalIntef;

/**
 * Created by HXH at 2019/8/27
 * 方法未找到异常
 */
public class FunctionNotFoundException extends RuntimeException {
    public FunctionNotFoundException(String message) {
        super(message);
    }
}
