package com.example.administrator.androidtest.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.androidtest.R;
import com.example.administrator.androidtest.fragment.Fragment1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Aty1 extends AppCompatActivity {

    List<Fragment1> fragment1List = new ArrayList<>();

    @BindView(R.id.vp) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty1);
        ButterKnife.bind(this);

        for(int i = 0; i<5 ;i++)
            fragment1List.add(new Fragment1());

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment1 f1 = fragment1List.get(position%5);
                f1.load(position);
                return f1;
            }

            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }
        });
    }


}
