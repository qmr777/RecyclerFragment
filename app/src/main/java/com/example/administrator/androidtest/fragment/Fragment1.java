package com.example.administrator.androidtest.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.androidtest.Bean.GankBeautyBean;
import com.example.administrator.androidtest.LoadData;
import com.example.administrator.androidtest.R;
import com.example.administrator.androidtest.network.BeautyService;
import com.example.administrator.androidtest.network.Network;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Fragment1 extends Fragment implements LoadData{

    private static final String TAG = "Fragment1";

    View contentView;

    int s = 0;
    boolean edited;

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.iv)
    ImageView imageView;

    public Fragment1() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_fragment1, container, false);
            ButterKnife.bind(this,contentView);
        }
        Log.i(TAG, "onCreateView: " + hashCode());
        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Network.GetBeauty().create(BeautyService.class)
                .getBeauties(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GankBeautyBean>() {
                    @Override
                    public void call(GankBeautyBean gankBeautyBean) {
                        tv.setText(gankBeautyBean.getResults().get(0).getCreatedAt());
                        Log.i(TAG, "call: " + gankBeautyBean.getResults().get(0).getCreatedAt());
                        Log.i(TAG, "call: 重新加载！");
                        Glide.with(Fragment1.this)
                                .load(gankBeautyBean.getResults().get(0).getUrl())
                                .into(imageView);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }

    @Override
    public void load(int s) {
        if(this.s != s) {
            this.s = s;
        }
        else {
            Log.i(TAG, "load: 数据相同未修改");
        }
    }
}
