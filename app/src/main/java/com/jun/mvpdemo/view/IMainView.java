package com.jun.mvpdemo.view;

import com.jun.mvpdemo.model.Repo;

import java.util.List;

/**
 * Author：JunJun
 * Description:
 */
public interface IMainView {


    void loadingSuccess(String msg) ;
    void loadingFaield(String msg) ;

    void showProgress() ;
    void hideProgress() ;

    void reloadListViewByRepos(List<Repo> repos) ;

}
