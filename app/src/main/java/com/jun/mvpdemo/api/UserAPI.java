package com.jun.mvpdemo.api;

import com.jun.mvpdemo.model.Repo;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by chenjunjun on 1/7/16.
 */
public interface UserAPI {

    @GET("/users/{user}/repos")
    Observable<List<Repo>> listRepositories(@Path("user") String user);
}
