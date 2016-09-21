package com.example.administrator.androidtest.network;

import android.accounts.NetworkErrorException;

import com.example.administrator.androidtest.Bean.AndroidDataBean;
import com.example.administrator.androidtest.Bean.BaseBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/9.
 *
 */
public class Network {

    public static final String BASE_URL = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    public static final String BASE_API_URL = "http://gank.io/api/";

    static OkHttpClient.Builder clientBuilder;

    public static Retrofit GetBeauty(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static void getAndroidData(final Subscriber<List<AndroidDataBean>> subscriber, int page){
        getBaseRetrofit().create(AndroidService.class).getAndroidMsg(page)
                .map(new Func1<BaseBean<List<AndroidDataBean>>, List<AndroidDataBean>>() {
                    @Override
                    public List<AndroidDataBean> call(BaseBean<List<AndroidDataBean>> listBaseBean) {
                        if(listBaseBean==null || listBaseBean.error) {
                            subscriber.onError(new NetworkErrorException());
                            return null;
                        }
                        else
                            return listBaseBean.results;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Retrofit getBaseRetrofit(){

        if(clientBuilder == null) {
            clientBuilder = new OkHttpClient.Builder();
            clientBuilder.readTimeout(10, TimeUnit.SECONDS);
        }

        return new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
