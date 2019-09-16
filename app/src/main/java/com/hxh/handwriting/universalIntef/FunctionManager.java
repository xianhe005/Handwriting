package com.hxh.handwriting.universalIntef;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HXH at 2019/8/27
 * 万能接口管理器
 */
public class FunctionManager {

    private static final FunctionManager INSTANCE = new FunctionManager();

    private Map<String, List<FunctionNoParamNoResult>> mNoParamNoResultMap = new HashMap<>();
    private Map<String, List<FunctionNoParamHasResult>> mNoParamHasResultMap = new HashMap<>();
    private Map<String, List<FunctionHasParamNoResult>> mHasParamNoResultMap = new HashMap<>();
    private Map<String, List<FunctionHasParamHasResult>> mHasParamHasResultMap = new HashMap<>();

    private FunctionManager() {
    }

    public static FunctionManager getInstance() {
        return INSTANCE;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 将四种类型的方法添加到FunctionManager;
    ///////////////////////////////////////////////////////////////////////////
    /**
     * 添加没有参数，有返回值的方法
     */
    public synchronized void addFunction(FunctionNoParamHasResult function) {
        List<FunctionNoParamHasResult> list = mNoParamHasResultMap.get(function.functionName);
        if (list == null) {
            list = new ArrayList<>();
            mNoParamHasResultMap.put(function.functionName, list);
        }
        list.add(function);
    }

    /**
     * 添加没有参数，没有返回值的方法
     */
    public synchronized void addFunction(FunctionNoParamNoResult function) {
        List<FunctionNoParamNoResult> list = mNoParamNoResultMap.get(function.functionName);
        if (list == null) {
            list = new ArrayList<>();
            mNoParamNoResultMap.put(function.functionName, list);
        }
        list.add(function);
    }

    /**
     * 添加有参数，没有返回值的方法
     */
    public synchronized void addFunction(FunctionHasParamNoResult function) {
        List<FunctionHasParamNoResult> list = mHasParamNoResultMap.get(function.functionName);
        if (list == null) {
            list = new ArrayList<>();
            mHasParamNoResultMap.put(function.functionName, list);
        }
        list.add(function);
    }

    /**
     * 添加有参数，有返回值的方法
     */
    public synchronized void addFunction(FunctionHasParamHasResult function) {
        List<FunctionHasParamHasResult> list = mHasParamHasResultMap.get(function.functionName);
        if (list == null) {
            list = new ArrayList<>();
            mHasParamHasResultMap.put(function.functionName, list);
        }
        list.add(function);
    }

    //执行没有参数，没有返回值的方法
    public void invokeFunction(String functionName) {
        if (TextUtils.isEmpty(functionName)) {
            return;
        }
        List<FunctionNoParamNoResult> list = mNoParamNoResultMap.get(functionName);
        if (list != null) {
            for (FunctionNoParamNoResult f : list) {
                if (f != null) {
                    f.function();
                } else {
                    throw new FunctionNotFoundException("方法不存在");
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 四种类型方法的执行
    ///////////////////////////////////////////////////////////////////////////
    //执行没有参数，有返回值的方法
    public <T> T invokeFunction(String functionName, Class<T> t) {
        if (TextUtils.isEmpty(functionName)) {
            return null;
        }
        List<FunctionNoParamHasResult> list = mNoParamHasResultMap.get(functionName);
        T result = null;
        if (list != null) {
            for (FunctionNoParamHasResult f : list) {
                if (f != null) {
                    if (result == null) {
                        //cast就是转换
                        result = t.cast(f.function());
                    } else {
                        f.function();
                    }
                } else {
                    throw new FunctionNotFoundException("方法不存在");
                }
            }
        }
        return result;
    }

    //执行有参数，没有返回值的方法
    @SuppressWarnings("unchecked")
    public <P> void invokeFunction(String functionName, P p) {
        if (TextUtils.isEmpty(functionName)) {
            return;
        }
        List<FunctionHasParamNoResult> list = mHasParamNoResultMap.get(functionName);
        if (list != null) {
            for (FunctionHasParamNoResult f : list) {
                if (f != null) {
                    f.function(p);
                } else {
                    throw new FunctionNotFoundException("方法不存在");
                }
            }
        }
    }

    //执行有参数，有返回值的方法
    @SuppressWarnings("unchecked")
    public <R, P> R invokeFunction(String functionName, P p, Class<R> t) {
        if (TextUtils.isEmpty(functionName)) {
            return null;
        }
        List<FunctionHasParamHasResult> list = mHasParamHasResultMap.get(functionName);
        R result = null;
        if (list != null) {
            for (FunctionHasParamHasResult f : list) {
                if (f != null) {
                    if (result == null) {
                        //cast就是转换
                        result = t.cast(f.function(p));
                    } else {
                        f.function(p);
                    }
                } else {
                    throw new FunctionNotFoundException("方法不存在");
                }
            }
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 将四种类型的方法移除从FunctionManager
    ///////////////////////////////////////////////////////////////////////////
    /**
     * 移除没有参数，有返回值的方法
     */
    public synchronized void removeFunction(FunctionNoParamHasResult function) {
        List<FunctionNoParamHasResult> list = mNoParamHasResultMap.get(function.functionName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (function == list.get(i)) {
                    list.remove(i);
                    i--;
                }
            }
            if (list.isEmpty()) {
                mNoParamHasResultMap.remove(function.functionName);
            }
        }
    }

    /**
     * 移除没有参数，没有返回值的方法
     */
    public synchronized void removeFunction(FunctionNoParamNoResult function) {
        List<FunctionNoParamNoResult> list = mNoParamNoResultMap.get(function.functionName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (function == list.get(i)) {
                    list.remove(i);
                    i--;
                }
            }
            if (list.isEmpty()) {
                mNoParamNoResultMap.remove(function.functionName);
            }
        }
    }

    /**
     * 移除有参数，没有返回值的方法
     */
    public synchronized void removeFunction(FunctionHasParamNoResult function) {
        List<FunctionHasParamNoResult> list = mHasParamNoResultMap.get(function.functionName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (function == list.get(i)) {
                    list.remove(i);
                    i--;
                }
            }
            if (list.isEmpty()) {
                mHasParamNoResultMap.remove(function.functionName);
            }
        }
    }

    /**
     * 移除有参数，有返回值的方法
     */
    public synchronized void removeFunction(FunctionHasParamHasResult function) {
        List<FunctionHasParamHasResult> list = mHasParamHasResultMap.get(function.functionName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (function == list.get(i)) {
                    list.remove(i);
                    i--;
                }
            }
            if (list.isEmpty()) {
                mHasParamHasResultMap.remove(function.functionName);
            }
        }
    }
}
