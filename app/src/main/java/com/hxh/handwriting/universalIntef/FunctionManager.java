package com.hxh.handwriting.universalIntef;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HXH at 2019/8/27
 * 万能接口管理器
 */
public class FunctionManager {

    private static final FunctionManager INSTANCE = new FunctionManager();

    private Map<String, FunctionNoParamNoResult> mNoParamNoResultMap = new HashMap<>();
    private Map<String, FunctionNoParamHasResult> mNoParamHasResultMap = new HashMap<>();
    private Map<String, FunctionHasParamNoResult> mHasParamNoResultMap = new HashMap<>();
    private Map<String, FunctionHasParamHasResult> mHasParamHasResultMap = new HashMap<>();

    private FunctionManager() {
    }

    public static FunctionManager getInstance() {
        return INSTANCE;
    }

    //将四种类型的方法添加到FunctionManager;

    /**
     * 添加没有参数，有返回值的方法
     */
    public void addFunction(FunctionNoParamHasResult function) {
        mNoParamHasResultMap.put(function.functionName, function);
    }

    /**
     * 添加没有参数，没有返回值的方法
     */
    public void addFunction(FunctionNoParamNoResult function) {
        mNoParamNoResultMap.put(function.functionName, function);
    }

    /**
     * 添加有参数，没有返回值的方法
     */
    public void addFunction(FunctionHasParamNoResult function) {
        mHasParamNoResultMap.put(function.functionName, function);
    }

    /**
     * 添加有参数，有返回值的方法
     */
    public void addFunction(FunctionHasParamHasResult function) {
        mHasParamHasResultMap.put(function.functionName, function);
    }

    //执行没有参数，没有返回值的方法
    public void invokeFunction(String functionName) {
        if (TextUtils.isEmpty(functionName)) {
            return;
        }
        if (mNoParamNoResultMap != null) {
            FunctionNoParamNoResult f = mNoParamNoResultMap.get(functionName);
            if (f != null) {
                f.function();
            } else {
                throw new FunctionNotFoundException("方法不存在");
            }
        }
    }

    //执行没有参数，有返回值的方法
    public <T> T invokeFunction(String functionName, Class<T> t) {
        if (TextUtils.isEmpty(functionName)) {
            return null;
        }
        if (mNoParamHasResultMap != null) {
            FunctionNoParamHasResult f = mNoParamHasResultMap.get(functionName);
            if (f != null) {
                //cast就是转换
                return t.cast(f.function());
            } else {
                throw new FunctionNotFoundException("方法不存在");
            }
        }
        return null;
    }

    //执行有参数，没有返回值的方法
    @SuppressWarnings("unchecked")
    public <P> void invokeFunction(String functionName, P p) {
        if (TextUtils.isEmpty(functionName)) {
            return;
        }
        if (mHasParamNoResultMap != null) {
            FunctionHasParamNoResult f = mHasParamNoResultMap.get(functionName);
            if (f != null) {
                f.function(p);
            } else {
                throw new FunctionNotFoundException("方法不存在");
            }
        }
    }

    //执行有参数，有返回值的方法
    @SuppressWarnings("unchecked")
    public <R, P> R invokeFunction(String functionName, P p, Class<R> t) {
        if (TextUtils.isEmpty(functionName)) {
            return null;
        }
        if (mHasParamHasResultMap != null) {
            FunctionHasParamHasResult f = mHasParamHasResultMap.get(functionName);
            if (f != null) {
                return t.cast(f.function(p));
            } else {
                throw new FunctionNotFoundException("方法不存在");
            }
        }
        return null;
    }

    //将四种类型的方法移除从FunctionManager;

    /**
     * 添加没有参数，有返回值的方法
     */
    public void removeFunction(FunctionNoParamHasResult function) {
        mNoParamHasResultMap.remove(function.functionName);
    }

    /**
     * 添加没有参数，没有返回值的方法
     */
    public void removeFunction(FunctionNoParamNoResult function) {
        mNoParamNoResultMap.remove(function.functionName);
    }

    /**
     * 添加有参数，没有返回值的方法
     */
    public void removeFunction(FunctionHasParamNoResult function) {
        mHasParamNoResultMap.remove(function.functionName);
    }

    /**
     * 添加有参数，有返回值的方法
     */
    public void removeFunction(FunctionHasParamHasResult function) {
        mHasParamHasResultMap.remove(function.functionName);
    }
}
