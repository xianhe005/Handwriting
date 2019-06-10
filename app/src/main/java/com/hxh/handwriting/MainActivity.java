package com.hxh.handwriting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hxh.handwriting.designpattern.visitor.TestVisitor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test1();
    }

    private void test1() {
        TestVisitor.test();
    }
}
