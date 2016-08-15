package com.example.administrator.androidtest.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.androidtest.R;
import com.example.administrator.androidtest.adapter.CurrentPageNum;
import com.example.administrator.androidtest.fragment.UserFeedbackFragment;

import java.lang.reflect.Array;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Aty2 extends AppCompatActivity implements CurrentPageNum{

    @BindView(R.id.vp)
    ViewPager viewPager;

    UserFeedbackFragment[] userFeedbackFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty2);
        ButterKnife.bind(this);
        userFeedbackFragments = new UserFeedbackFragment[]
                {new UserFeedbackFragment(), new UserFeedbackFragment()};
        initViewPager();
    }

    void initViewPager(){

        viewPager.setOffscreenPageLimit(0);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("lvl",1);
                    userFeedbackFragments[position].setArguments(bundle);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("lvl",2);
                    userFeedbackFragments[position].setArguments(bundle);
                }
                return userFeedbackFragments[position];
            }

            @Override
            public int getCount() {
                return userFeedbackFragments.length;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 1)
            viewPager.setCurrentItem(0,true);
        else
            super.onBackPressed();
    }

    @Override
    public void setPage(int page,int dataID) {
        if(viewPager!=null){
            if(page>viewPager.getAdapter().getCount()+1)
                viewPager.setCurrentItem(viewPager.getAdapter().getCount(),true);
            else {
                Bundle bundle = new Bundle();
                bundle.putInt("dataID",dataID);
                bundle.putInt("lvl",2);
                userFeedbackFragments[page].loadData(dataID);
                viewPager.setCurrentItem(page, true);
            }
        }
    }
}
