package com.example.administrator.androidtest.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.androidtest.R;
import com.example.administrator.androidtest.adapter.StringRvAdapter;
import com.example.administrator.androidtest.adapter.UserFeedbackRvAdapter;
import com.example.administrator.androidtest.view.CustomSrl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 尝试修改{@link android.support.v4.widget.SwipeRefreshLayout} 源码
 */

public class Aty4 extends AppCompatActivity {

    @BindView(R.id.srl)
    CustomSrl customSrl;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    StringRvAdapter adapter;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty4);
        ButterKnife.bind(this);
        initView();
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
            }
        });

    }
}
