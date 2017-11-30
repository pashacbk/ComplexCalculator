package com.interntask.calculator;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class CalculatorActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        ViewPager pager = (ViewPager) findViewById(R.id.buttons_container);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }
}
