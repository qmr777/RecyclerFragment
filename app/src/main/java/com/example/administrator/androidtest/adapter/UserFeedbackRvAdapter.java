package com.example.administrator.androidtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.androidtest.Bean.FeedbackBean;
import com.example.administrator.androidtest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/10.
 * 用户反馈 列表项
 */
public class UserFeedbackRvAdapter extends RecyclerView.Adapter<UserFeedbackRvAdapter.FeedbackHolder>{

    LayoutInflater inflater;
    List<FeedbackBean.ResultBean> resultBeen;

    ItemClickListener itemClickListener;


    @Override
    public FeedbackHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater == null)
            inflater = LayoutInflater.from(parent.getContext());
        return new FeedbackHolder(inflater.inflate(R.layout.item_textview,parent,false));
    }

    @Override
    public void onBindViewHolder(final FeedbackHolder holder, final int position) {
        holder.textView.setText(resultBeen.get(position).getDataText());
        if(itemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return resultBeen==null?0:resultBeen.size();
    }

    public void setData(List<FeedbackBean.ResultBean> list){
        this.resultBeen = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    protected class FeedbackHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_content)
        TextView textView;

        public FeedbackHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
