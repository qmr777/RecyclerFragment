package com.example.administrator.androidtest.network;

import com.example.administrator.androidtest.Bean.AndroidDataBean;
import com.example.administrator.androidtest.Bean.BaseBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qmr on 2016/9/1.
 *
 */
public interface AndroidService {

    @GET("data/Android/10/{pages}")
    Observable<BaseBean<List<AndroidDataBean>>> getAndroidMsg(@Path("pages") int pages);

}
