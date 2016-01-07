package com.jun.mvpdemo.api;

import com.jun.mvpdemo.model.Repo;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by chenjunjun on 1/7/16.
 */
public class RetrofitUserService implements IUserService {
    @Override
    public Observable<List<Repo>> fetchUsersByKeyword(String keyword) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);
        return userAPI.listRepositories(keyword);
    }
}
