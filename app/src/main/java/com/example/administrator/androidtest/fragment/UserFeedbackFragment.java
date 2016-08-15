package com.example.administrator.androidtest.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.androidtest.Bean.FeedbackBean;
import com.example.administrator.androidtest.R;
import com.example.administrator.androidtest.adapter.CurrentPageNum;
import com.example.administrator.androidtest.adapter.ItemClickListener;
import com.example.administrator.androidtest.adapter.UserFeedbackRvAdapter;
import com.example.administrator.androidtest.network.FakeAPI;
import com.example.administrator.androidtest.utils.GetJsonFromUrl;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * 用户反馈fragment
 */
public class UserFeedbackFragment extends Fragment {

    private static final String TAG = "UserFeedbackFragment";

    List<FeedbackBean.ResultBean> resultBeenList1;
    List<FeedbackBean.ResultBean> resultBeenList2;

    Gson gson = new Gson();

    View contentView;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    UserFeedbackRvAdapter adapter;

    public UserFeedbackFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(contentView == null){
            contentView = inflater.inflate(R.layout.fragment_user_feedback, container, false);
            ButterKnife.bind(this,contentView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter = new UserFeedbackRvAdapter());
            adapter.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(int position) {
                    ((CurrentPageNum)getActivity()).setPage(1,resultBeenList1.get(position).getDataID());
                }
            });
        }

        if(getArguments().getInt("lvl",1) == 1){
            Log.i(TAG, "onCreateView: 1");
            //adapter.setData(new Gson().fromJson(FakeAPI.GetLvl1(), FeedbackBean.class).getResult());
            loadData();
        } else if(getArguments().getInt("lvl",1) == 2){
            int dataID = getArguments().getInt("dataID",0);
            Log.i(TAG, "onCreateView: 2");
            adapter.setData(new Gson().fromJson(FakeAPI.GetLvl2(0), FeedbackBean.class).getResult());
        }
        return contentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        adapter.setData(null);
    }

    public void loadData(){//获取一级列表的方法
        if(resultBeenList1 == null) {
            Log.i(TAG, "loadData: list为空");
            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    Map<String, String> map = new HashMap<>();
                    map.put("KindType", String.valueOf(1));
                    subscriber.onNext(GetJsonFromUrl.GetString("http://123.234.82.23/flyapp/FeedBack/GetKindData.ashx", map, TAG));
                }
            })
                    .map(new Func1<String, List<FeedbackBean.ResultBean>>() {
                        @Override
                        public List<FeedbackBean.ResultBean> call(String s) {
                            Log.i(TAG, "call: " +s);
                            resultBeenList1 = gson.fromJson(s, FeedbackBean.class).getResult();
                            return resultBeenList1;
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<FeedbackBean.ResultBean>>() {
                        @Override
                        public void call(List<FeedbackBean.ResultBean> resultBeen) {
                            adapter.setData(resultBeen);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                            //ToastUtils.toastShort(FeedbackListActivity.this,R.string.err_network);
                        }
                    });
        }
        else {
            Log.i(TAG, "loadData: list不为空");
            adapter.setData(resultBeenList1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    public void loadData(final int dataID){//获取二级列表的方法
        Log.i(TAG, "loadData: 二级列表");
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Map<String,String> map = new HashMap<>();
                map.put("KindType",String.valueOf(2));
                map.put("DataID",dataID+"");
                subscriber.onNext(GetJsonFromUrl.GetString("http://123.234.82.23/flyapp/FeedBack/GetKindData.ashx",map,TAG));
                //subscriber.onNext(FakeAPI.GetLvl2(dataID));
            }
        })
                .map(new Func1<String, FeedbackBean>() {
                    @Override
                    public FeedbackBean call(String s) {
                        return gson.fromJson(s,FeedbackBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FeedbackBean>() {
                    @Override
                    public void call(FeedbackBean feedbackBean) {
                        if("400".equals(feedbackBean.getCode())){
/*                            Intent intent = new Intent(FeedbackListActivity.this,FeedbackDetailActivity.class);
                            intent.putExtra("QueText",queText);
                            intent.putExtra("SceneText",sceneText);
                            startActivity(intent);*/
                            Toast.makeText(getActivity(),"400",Toast.LENGTH_SHORT).show();
                        } else if("200".equals(feedbackBean.getCode())) {
                            Toast.makeText(getActivity(),"200",Toast.LENGTH_SHORT).show();
                            adapter.setData(feedbackBean.getResult());

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }



}
