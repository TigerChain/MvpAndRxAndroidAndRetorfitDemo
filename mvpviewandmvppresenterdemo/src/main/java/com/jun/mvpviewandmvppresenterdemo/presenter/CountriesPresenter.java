package com.jun.mvpviewandmvppresenterdemo.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.jun.mvpviewandmvppresenterdemo.view.CountriesView;

/**
 * Created by chenjunjun on 1/9/16.
 */
public abstract class CountriesPresenter extends MvpBasePresenter<CountriesView>  {


    public abstract void loadCountries(final boolean pullToRefresh);


}
