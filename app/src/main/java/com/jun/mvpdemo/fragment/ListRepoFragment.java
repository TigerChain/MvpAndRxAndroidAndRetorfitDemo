package com.jun.mvpdemo.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.jun.mvpdemo.R;
import com.jun.mvpdemo.adapter.UserListAdapter;
import com.jun.mvpdemo.api.RetrofitUserService;
import com.jun.mvpdemo.model.Repo;
import com.jun.mvpdemo.presenter.IShowViewPresenter;
import com.jun.mvpdemo.presenter.ShowViewPresenterImpl;
import com.jun.mvpdemo.view.IMainView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by chenjunjun on 1/17/16.
 * 根据Adnroid最佳实践上说，让Activity只是管理Fragment，让Fragment去管理View
 */
@EFragment(R.layout.fragment_list_repo)
public class ListRepoFragment extends Fragment implements IMainView{

    @ViewById
    ListView lstview ;

    private UserListAdapter adapter ;

    private Subscription subscription ;

    private List<Repo> repoList = new ArrayList<>() ;

    private IShowViewPresenter ishowviewPresenter ;

    private ProgressDialog progressDialog ;


    @AfterViews
    void init(){
        adapter = new UserListAdapter(getActivity(),repoList) ;
        lstview.setAdapter(adapter);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ishowviewPresenter = new ShowViewPresenterImpl(this, new RetrofitUserService()) ;

        //按理说这里应该是一个subscription ,好让我能去释放，以免内存溢出
        subscription =  ishowviewPresenter.getUserListInfo("hckhanh") ;
    }


    @Override
    public void loadingSuccess(String msg) {

        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadingFaield(String msg) {

        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgress() {

        progressDialog = new ProgressDialog(getActivity()) ;
        progressDialog.setMessage("Loading...");
        progressDialog.show();


    }

    @Override
    public void hideProgress() {

        if(progressDialog !=null && progressDialog.isShowing()){
            progressDialog.dismiss();
            progressDialog = null ;
        }

    }

    @Override
    public void reloadListViewByRepos(List<Repo> repos) {
        repoList.addAll(repos) ;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }

    }
}
