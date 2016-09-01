package com.example.administrator.androidtest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Aty5 extends AppCompatActivity {

    @BindView(R.id.rv_android_data)
    RecyclerView rv;

    AndroidDataAdapter androidDataAdapter;
    List<AndroidDataBean> baseBeanList;

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
    }

    void initData(){

        Subscriber<List<AndroidDataBean>> subscriber = new Subscriber<List<AndroidDataBean>>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(List<AndroidDataBean> androidDataBeen) {
                androidDataAdapter.setAndroidDataBeen(androidDataBeen);
            }
        };

        Network.getAndroidData(subscriber,1);

    }
}
