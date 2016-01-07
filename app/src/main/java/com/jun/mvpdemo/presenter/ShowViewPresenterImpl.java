package com.jun.mvpdemo.presenter;

import com.jun.mvpdemo.api.IUserService;
import com.jun.mvpdemo.model.Repo;
import com.jun.mvpdemo.view.IMainView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author：JunJun
 * Description:
 */
public class ShowViewPresenterImpl implements  IShowViewPresenter {

    private IMainView iMainView;
    private IUserService userService;

    public ShowViewPresenterImpl(IMainView iMainView, IUserService userService){
        this.iMainView = iMainView;
        this.userService = userService;
    }

    @Override
    public void getUserListInfo(String userName) {
        iMainView.showProgress();

        userService.fetchUsersByKeyword(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        iMainView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iMainView.loadingFaield();
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        iMainView.loadingSuccess();
                        iMainView.reloadListViewByRepos(repos);
                    }
                });

    }
}
