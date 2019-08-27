package com.hxh.handwriting.universalIntef.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hxh.handwriting.R;
import com.hxh.handwriting.universalIntef.FunctionHasParamHasResult;
import com.hxh.handwriting.universalIntef.FunctionHasParamNoResult;
import com.hxh.handwriting.universalIntef.FunctionNoParamHasResult;
import com.hxh.handwriting.universalIntef.FunctionNoParamNoResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by HXH at 2019/8/27
 * 测试
 */
public class MainFuncActivity extends FunctionActivity {

    private SparseArray<Fragment> mFragments = new SparseArray<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_func);
        ViewPager viewPager = findViewById(R.id.viewPager);
        RadioGroup rg = findViewById(R.id.rg);

        rg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb1) {
                viewPager.setCurrentItem(0);
            } else if (checkedId == R.id.rb2) {
                viewPager.setCurrentItem(1);
            } else if (checkedId == R.id.rb3) {
                viewPager.setCurrentItem(2);
            } else if (checkedId == R.id.rb4) {
                viewPager.setCurrentItem(3);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    rg.check(R.id.rb1);
                } else if (position == 1) {
                    rg.check(R.id.rb2);
                } else if (position == 2) {
                    rg.check(R.id.rb3);
                } else if (position == 3) {
                    rg.check(R.id.rb4);
                }
            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new TabFragment1();
                    case 1:
                        return new TabFragment2();
                    case 2:
                        return new TabFragment3();
                }
                return new TabFragment4();
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }

            ///////////////////////////////////////////////////////////////////////////
            // 应以这样的方式来持有fragment的引用 start
            ///////////////////////////////////////////////////////////////////////////
            @Override
            public @NonNull
            Object instantiateItem(@NonNull ViewGroup container, int position) {
                Fragment f = (Fragment) super.instantiateItem(container, position);
                mFragments.put(position, f);
                return f;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
                mFragments.remove(position);
            }
            ///////////////////////////////////////////////////////////////////////////
            // 应以这样的方式来持有fragment的引用 end
            ///////////////////////////////////////////////////////////////////////////
        });

        addFuncs();
    }

    private void addFuncs() {
        // 无参数无返回
        addFunction(new FunctionNoParamNoResult(TabFragment1.FUNC_NAME) {
            @Override
            public void function() {
                Log.i("hxh", "function: 无参数无返回 invoked!");
                Toast.makeText(MainFuncActivity.this, TabFragment1.FUNC_NAME, Toast.LENGTH_SHORT).show();
            }
        });
        // 无参数有返回
        addFunction(new FunctionNoParamHasResult(TabFragment2.FUNC_NAME) {
            @Override
            public Integer function() {
                Log.i("hxh", "function: 无参数有返回 invoked!");
                return 111;
            }
        });
        // 有参数无返回
        addFunction(new FunctionHasParamNoResult<List<String>>(TabFragment3.FUNC_NAME) {
            @Override
            public void function(List<String> param) {
                Log.i("hxh", "function: 有参数无返回 invoked!");
                Log.i("hxh", param.toString());
            }
        });
        // 有参数有返回
        addFunction(new FunctionHasParamHasResult<String, Map<String, Integer>>(TabFragment4.FUNC_NAME) {
            @Override
            public String function(Map<String, Integer> param) {
                Log.i("hxh", "function: 有参数有返回 invoked!");
                Log.i("hxh", param.toString());
                StringBuilder sb = new StringBuilder();
                Set<String> set = param.keySet();
                for (String s : set) {
                    sb.append(s).append(param.get(s));
                }
                try {
                    TabFragment3 f = (TabFragment3) mFragments.get(2);
                    if (f != null) {
                        f.invoke("f4->Activity->f3");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return sb.toString();
            }
        });
    }
}
