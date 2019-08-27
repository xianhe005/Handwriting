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

import java.util.Arrays;

/**
 * Created by HXH at 2019/8/27
 * tab3-有参数无返回值
 */
public class TabFragment3 extends FunctionFragment {

    public static final String FUNC_NAME = TabFragment3.class.getName() + "-HPNR";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        Button btn = v.findViewById(R.id.btn);
        btn.setText("有参数无返回值");
        btn.setOnClickListener(v1 ->
                FunctionManager.getInstance().invokeFunction(FUNC_NAME,
                        Arrays.asList("hello", "world")));
    }

    public void invoke(String s) {
        Toast.makeText(getActivity(), "Invoked by Activity:" + s, Toast.LENGTH_SHORT).show();
    }
}
