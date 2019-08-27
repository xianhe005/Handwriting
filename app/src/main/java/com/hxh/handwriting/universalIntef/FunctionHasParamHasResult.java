package com.hxh.handwriting.universalIntef;

/**
 * Created by HXH at 2019/8/27
 * 有参有返回
 */
public abstract class FunctionHasParamHasResult<R, P> extends Function {

    public FunctionHasParamHasResult(String functionName) {
        super(functionName);
    }

    public abstract R function(P param);
}
