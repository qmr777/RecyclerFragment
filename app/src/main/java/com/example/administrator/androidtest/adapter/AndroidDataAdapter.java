package com.example.administrator.androidtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.androidtest.Bean.AndroidDataBean;
import com.example.administrator.androidtest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qmr on 2016/9/1.
 *
 */
public class AndroidDataAdapter extends RecyclerView.Adapter<AndroidDataAdapter.AndroidDataVH> {

    LayoutInflater inflater;

    ItemClickListener itemClickListener;

    List<AndroidDataBean> androidDataBeen;

    public void setAndroidDataBeen(List<AndroidDataBean> list){
        androidDataBeen = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener l){
        this.itemClickListener = l;
    }

    @Override
    public AndroidDataVH onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater == null)
            inflater = LayoutInflater.from(parent.getContext());
        return new AndroidDataVH(inflater.inflate(R.layout.item_android_data,parent,false));
    }

    @Override
    public void onBindViewHolder(final AndroidDataVH holder, int position) {
        holder.tv_createDate.setText(androidDataBeen.get(position).getCreatedAt());
        holder.tv_publish_date.setText(androidDataBeen.get(position).getPublishedAt());
        holder.tv_title.setText(androidDataBeen.get(position).getDesc());
        holder.tv_url.setText(androidDataBeen.get(position).getUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null){
                    itemClickListener.onClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidDataBeen==null?0:androidDataBeen.size();
    }

    static class AndroidDataVH extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_create_date)
        TextView tv_createDate;
        @BindView(R.id.tv_publish_date)
        TextView tv_publish_date;
        @BindView(R.id.tv_url)
        TextView tv_url;

        public AndroidDataVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
