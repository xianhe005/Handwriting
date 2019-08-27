package com.hxh.handwriting.universalIntef.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hxh.handwriting.R;
import com.hxh.handwriting.universalIntef.FunctionManager;

/**
 * Created by HXH at 2019/8/27
 * tab1-无参数无返回值
 */
public class TabFragment1 extends FunctionFragment {

    public static final String FUNC_NAME = TabFragment1.class.getName() + "-NPNR";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        Button btn = v.findViewById(R.id.btn);
        btn.setText("无参数无返回值");
        btn.setOnClickListener(v1 ->
                FunctionManager.getInstance().invokeFunction(FUNC_NAME));
    }
}
