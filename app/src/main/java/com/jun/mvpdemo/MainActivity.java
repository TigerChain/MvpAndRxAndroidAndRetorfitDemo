package com.jun.mvpdemo;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jun.mvpdemo.adapter.UserListAdapter;
import com.jun.mvpdemo.api.RetrofitUserService;
import com.jun.mvpdemo.model.Repo;
import com.jun.mvpdemo.presenter.IShowViewPresenter;
import com.jun.mvpdemo.presenter.ShowViewPresenterImpl;
import com.jun.mvpdemo.view.IMainView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements IMainView {

    @ViewById
    ListView lstview ;

    private UserListAdapter adapter ;

    /**和Observable被观察者是一回事**/
    private Subscription subscription ;

    private List<Repo> repoList = new ArrayList<>() ;

    private IShowViewPresenter ishowviewPresenter ;

    private ProgressDialog progressDialog ;


    @AfterViews
    void setData(){
        adapter = new UserListAdapter(MainActivity.this,repoList) ;
        lstview.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ishowviewPresenter = new ShowViewPresenterImpl(this, new RetrofitUserService()) ;


        //按理说这里应该是一个subscription ,好让我能去释放，以免内存溢出
        ishowviewPresenter.getUserListInfo("hckhanh") ;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy() ;
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }

    }

    @Override
    public void loadingSuccess() {

    }

    @Override
    public void loadingFaield() {

    }

    @Override
    public void showProgress() {

        progressDialog = new ProgressDialog(this) ;
        progressDialog.setMessage("Loading...");
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        if(null != progressDialog){
            progressDialog.dismiss();
            progressDialog = null ;
        }

    }

    @Override
    public void reloadListViewByRepos(List<Repo> repos) {
        repoList.addAll(repos) ;
        adapter.notifyDataSetChanged();
    }
}
