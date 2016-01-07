package com.jun.mvpdemo.api;

import com.jun.mvpdemo.model.Repo;

import java.util.List;

import rx.Observable;

/**
 * Created by chenjunjun on 1/7/16.
 */
public interface IUserService {
    Observable<List<Repo>> fetchUsersByKeyword(String keyword);
}
