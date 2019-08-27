package com.hxh.handwriting.universalIntef.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hxh.handwriting.R;
import com.hxh.handwriting.universalIntef.FunctionManager;
import com.hxh.handwriting.universalIntef.FunctionNoParamNoResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HXH at 2019/8/27
 * tab4-有参数有返回值
 */
public class TabFragment4 extends FunctionFragment {

    public static final String FUNC_NAME = TabFragment4.class.getName() + "-HPHR";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab4, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        Button btn = v.findViewById(R.id.btn);
        btn.setText("有参数有返回值");
        btn.setOnClickListener(v1 -> {
            Map<String, Integer> map = new HashMap<String, Integer>() {{
                put("hello", 10);
                put("world", 20);
            }};
            String call = FunctionManager.getInstance()
                    .invokeFunction(FUNC_NAME, map, String.class);
            Toast.makeText(getActivity(), "来自Activity的值:" + call, Toast.LENGTH_SHORT).show();
        });

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.fl, new TabFragment4_1())
                .commit();

        addFunction(new FunctionNoParamNoResult(TabFragment4_1.FUNC_NAME) {
            @Override
            public void function() {
                Log.i("hxh", "function: from f4_1");
                Map<String, Integer> map = new HashMap<String, Integer>() {{
                    put("hello2", 20);
                    put("world2", 30);
                }};
                String call = FunctionManager.getInstance()
                        .invokeFunction(FUNC_NAME, map, String.class);
                Toast.makeText(getActivity(), "来自Activity的值:" + call, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
