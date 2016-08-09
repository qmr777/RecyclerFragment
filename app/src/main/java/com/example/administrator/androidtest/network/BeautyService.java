package com.example.administrator.androidtest.network;

import com.example.administrator.androidtest.Bean.GankBeautyBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/9.
 *
 */
public interface BeautyService {
    @GET("1/{pageNum}")
    Observable<GankBeautyBean> getBeauties(@Path("pageNum") int pageNum);
}
