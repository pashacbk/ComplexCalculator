package com.interntask.calculator;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by manga on 30.11.2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    public MyPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);

    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch(position){
            case 0:
                FirstFragment f1 =   new FirstFragment();
                return f1;
            case 1:
              SecondFragment f2 =   new SecondFragment();
                return f2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
