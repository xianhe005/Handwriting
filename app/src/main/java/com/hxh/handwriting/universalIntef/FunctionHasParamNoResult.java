package com.hxh.handwriting.universalIntef;

/**
 * Created by HXH at 2019/8/27
 * 有参无返回
 */
public abstract class FunctionHasParamNoResult<P> extends Function {

    public FunctionHasParamNoResult(String functionName) {
        super(functionName);
    }

    public abstract void function(P param);
}
