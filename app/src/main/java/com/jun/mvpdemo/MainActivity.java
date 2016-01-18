package com.jun.mvpdemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jun.mvpdemo.fragment.ListRepoFragment;
import com.jun.mvpdemo.fragment.ListRepoFragment_;

import org.androidannotations.annotations.EActivity;

/****
 * 这里的Activity只是用来管理Fragment的 它用来
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity  {

    private ListRepoFragment listRepoFragment ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listRepoFragment = new ListRepoFragment_() ;
        getSupportFragmentManager().beginTransaction().add(R.id.containl,listRepoFragment).show(listRepoFragment).commit() ;
    }

}
