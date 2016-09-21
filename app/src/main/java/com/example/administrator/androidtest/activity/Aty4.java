package com.example.administrator.androidtest.activity;

import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
    List<String> stringList;

    @BindView(R.id.srl)
    CustomSrl customSrl;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    float y;
    float basey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty4);
        ButterKnife.bind(this);
        initView();
        customSrl.setEnabled(false);
    }

    void setThemeRes(boolean b){
        int t = b?R.style.AppTheme:R.style.AppNightTheme;
        super.setTheme(t);
        SharePreferenceUtils.getInstance()
                .setDayNightMode(!SharePreferenceUtils.getInstance().getMode());
    }

    void resetTheme(){
        TypedValue bgc = new TypedValue();
        getTheme().resolveAttribute(R.attr.baseBackground,bgc,true);
    }

    void initView(){
        adapter = new StringRvAdapter();
        stringList = new ArrayList<>();
        for(int i = 0;i<30;i++){
            stringList.add("String position = " + i);
        }
        adapter.setData(stringList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.START|ItemTouchHelper.END);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                String temp = stringList.get(target.getAdapterPosition());
                stringList.set(target.getAdapterPosition(),stringList.get(viewHolder.getAdapterPosition()));
                stringList.set(viewHolder.getAdapterPosition(),temp);
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                stringList.remove(pos);
                adapter.notifyItemRemoved(pos);
            }
        });

        helper.attachToRecyclerView(recyclerView);
/*
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
        });*/

    }
}
