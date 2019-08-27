package com.hxh.handwriting.universalIntef.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hxh.handwriting.R;
import com.hxh.handwriting.universalIntef.FunctionManager;

/**
 * Created by HXH at 2019/8/27
 * tab2-无参数有返回值
 */
public class TabFragment2 extends FunctionFragment {

    public static final String FUNC_NAME = TabFragment2.class.getName() + "-NPHR";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        Button btn = v.findViewById(R.id.btn);
        btn.setText("无参数有返回值");
        btn.setOnClickListener(v1 -> {
            Integer call = FunctionManager.getInstance()
                    .invokeFunction(FUNC_NAME, Integer.class);
            Toast.makeText(getActivity(), "来自Activity的值:" + call, Toast.LENGTH_SHORT).show();
        });
    }
}
