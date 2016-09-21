package com.example.administrator.androidtest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.androidtest.Bean.AndroidDataBean;
import com.example.administrator.androidtest.Bean.BaseBean;
import com.example.administrator.androidtest.R;
import com.example.administrator.androidtest.adapter.AndroidDataAdapter;
import com.example.administrator.androidtest.adapter.ItemClickListener;
import com.example.administrator.androidtest.network.AndroidService;
import com.example.administrator.androidtest.network.Network;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class Aty5 extends AppCompatActivity {

    @BindView(R.id.rv_android_data)
    RecyclerView rv;

    AndroidDataAdapter androidDataAdapter;
    List<AndroidDataBean> baseBeanList;
    int pageNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty5);
        ButterKnife.bind(this);
        initRecyclerView();
        initData();
    }

    void initRecyclerView(){
        rv.setLayoutManager(new LinearLayoutManager(this));
        androidDataAdapter = new AndroidDataAdapter();
        androidDataAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent i = new Intent(Aty5.this,WebViewActivity.class);
                i.putExtra(WebViewActivity.TAG_URL,baseBeanList.get(position).getUrl());
                startActivity(i);
            }
        });
        rv.setAdapter(androidDataAdapter);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(((LinearLayoutManager)rv.getLayoutManager()).findLastVisibleItemPosition() > androidDataAdapter.getItemCount()-2){
                    initData();
                }
            }
        });
    }

    void initData(){

        Subscriber<List<AndroidDataBean>> subscriber = new Subscriber<List<AndroidDataBean>>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<AndroidDataBean> androidDataBeen) {
                if(baseBeanList==null){
                    baseBeanList = androidDataBeen;
                } else {
                    baseBeanList.addAll(androidDataBeen);
                }
                androidDataAdapter.setAndroidDataBeen(baseBeanList);
            }
        };

        Network.getAndroidData(subscriber,pageNum++);

    }
}
