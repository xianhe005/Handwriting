package com.hxh.handwriting.universalIntef;


/**
 * Created by HXH at 2019/8/27
 * 无参数有返回值
 */
public abstract class FunctionNoParamHasResult<R> extends Function {

    public FunctionNoParamHasResult(String functionName) {
        super(functionName);
    }

    public abstract R function();
}
