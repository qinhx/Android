package com.sysu.mypro2;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gavin on 2017/12/27.
 */

public interface FoodService {
    @GET("search?num=20&appkey=4d8f989a78e01bc099e147f362fb16ee")
    rx.Observable<AllThings>getUser(@Query("keyword") String name);
}
