package com.hxh.handwriting;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.hxh.handwriting.designpattern.visitor.TestVisitor;
import com.hxh.handwriting.thread.MultiThreadHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test1();

        //List<String> list = Arrays.asList("hello", "world", "hello world");

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.show();
        long start = System.currentTimeMillis();
        System.out.println("start:" + start);
        /*MultiThreadHelper.runInThread(() -> {
            System.out.println("thread1:" + Thread.currentThread().getName());
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                list.add(UUID.randomUUID().toString());
            }
            return list.stream().map(String::length).reduce(0, (x, y) -> x + y);
        }).apply(integer -> {
            System.out.println("thread2:" + Thread.currentThread().getName());
            System.out.println("用时:" + (System.currentTimeMillis() - start));
            System.out.println("integer:" + integer);
            dialog.dismiss();
        });*/


        MultiThreadHelper<Integer> helper = MultiThreadHelper.runInThread(() -> {
            System.out.println("thread1:" + Thread.currentThread().getName());
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 300000; i++) {
                list.add(UUID.randomUUID().toString());
            }
            return list.stream().map(String::length).reduce(0, (x, y) -> x + y);
        });
        helper.apply(integer -> {
            System.out.println("thread2:" + Thread.currentThread().getName());
            System.out.println("用时:" + (System.currentTimeMillis() - start));
            System.out.println("integer:" + integer);
            dialog.dismiss();
        });
        dialog.setOnCancelListener(dialog1 -> helper.stopOff());
    }

    private void test1() {
        TestVisitor.test();
    }


}
