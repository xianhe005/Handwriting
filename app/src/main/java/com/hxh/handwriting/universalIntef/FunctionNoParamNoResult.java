package com.hxh.handwriting.universalIntef;

/**
 * Created by HXH at 2019/8/27
 * 无参数无返回值
 */
public abstract class FunctionNoParamNoResult extends Function {

    public FunctionNoParamNoResult(String functionName) {
        super(functionName);
    }

    public abstract void function();
}
