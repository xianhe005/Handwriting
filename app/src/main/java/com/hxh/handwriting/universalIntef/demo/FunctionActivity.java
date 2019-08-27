package com.hxh.handwriting.universalIntef.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hxh.handwriting.universalIntef.Function;
import com.hxh.handwriting.universalIntef.FunctionHasParamHasResult;
import com.hxh.handwriting.universalIntef.FunctionHasParamNoResult;
import com.hxh.handwriting.universalIntef.FunctionManager;
import com.hxh.handwriting.universalIntef.FunctionNoParamHasResult;
import com.hxh.handwriting.universalIntef.FunctionNoParamNoResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HXH at 2019/8/27
 * Function Base Activity
 */
public abstract class FunctionActivity extends AppCompatActivity {
    private List<Function> mFunctions = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void addFunction(FunctionNoParamNoResult function) {
        FunctionManager.getInstance().addFunction(function);
        mFunctions.add(function);
    }

    protected void addFunction(FunctionNoParamHasResult function) {
        FunctionManager.getInstance().addFunction(function);
        mFunctions.add(function);
    }

    protected void addFunction(FunctionHasParamNoResult function) {
        FunctionManager.getInstance().addFunction(function);
        mFunctions.add(function);
    }

    protected void addFunction(FunctionHasParamHasResult function) {
        FunctionManager.getInstance().addFunction(function);
        mFunctions.add(function);
    }

    @Override
    protected void onDestroy() {
        for (Function function : mFunctions) {
            if (function instanceof FunctionNoParamNoResult) {
                FunctionManager.getInstance().removeFunction((FunctionNoParamNoResult) function);
            } else if (function instanceof FunctionNoParamHasResult) {
                FunctionManager.getInstance().removeFunction((FunctionNoParamHasResult) function);
            } else if (function instanceof FunctionHasParamNoResult) {
                FunctionManager.getInstance().removeFunction((FunctionHasParamNoResult) function);
            } else if (function instanceof FunctionHasParamHasResult) {
                FunctionManager.getInstance().removeFunction((FunctionHasParamHasResult) function);
            }
        }
        mFunctions.clear();
        mFunctions = null;
        super.onDestroy();
    }
}
