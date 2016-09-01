package com.example.administrator.androidtest.activity;

import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.androidtest.R;
import com.example.administrator.androidtest.adapter.StringRvAdapter;
import com.example.administrator.androidtest.adapter.UserFeedbackRvAdapter;
import com.example.administrator.androidtest.utils.SharePreferenceUtils;
import com.example.administrator.androidtest.view.Anim;
import com.example.administrator.androidtest.view.CustomSrl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 尝试修改{@link android.support.v4.widget.SwipeRefreshLayout} 源码
 */

public class Aty4 extends AppCompatActivity {

    StringRvAdapter adapter;
    Handler handler = new Handler();
    //RelativeLayout.LayoutParams params;
    Anim anim;

    @BindView(R.id.srl)
    CustomSrl customSrl;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    float y;
    float basey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setThemeRes(SharePreferenceUtils.getInstance().getMode());
        setContentView(R.layout.activity_aty4);
        //anim = (Anim) findViewById(R.id.anim);
        //params = new RelativeLayout.LayoutParams(anim.getLayoutParams());
        //anim.setLayoutParams();
        ButterKnife.bind(this);
        initView();
    }

    void setThemeRes(boolean b){
        int t = b?R.style.AppTheme:R.style.AppNightTheme;
        super.setTheme(t);
        SharePreferenceUtils.getInstance()
                .setDayNightMode(!SharePreferenceUtils.getInstance().getMode());
    }

/*    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                y = event.getY();
                basey = anim.getHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                float h = anim.getY();
                int yy = params.height;
                yy += event.getY() - y;
                params.height = yy;
                anim.setLayoutParams(params);
                break;
            case MotionEvent.ACTION_UP:
                y = event.getY();
                break;
        }
        return true;
    }*/

    void resetTheme(){
        TypedValue bgc = new TypedValue();
        getTheme().resolveAttribute(R.attr.baseBackground,bgc,true);
    }

    void initView(){
        adapter = new StringRvAdapter();
        final List<String> stringList = new ArrayList<>();
        for(int i = 0;i<30;i++){
            stringList.add("String position = " + i);
        }
        adapter.setData(stringList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        customSrl.setOnRefreshListener(new CustomSrl.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stringList.add("String position = " + stringList.size());
                        adapter.notifyDataSetChanged();
                        customSrl.setRefreshing(false);
                    }
                },3000);
                SharePreferenceUtils.getInstance()
                        .setDayNightMode(!SharePreferenceUtils.getInstance().getMode());
            }
        });

    }
}
