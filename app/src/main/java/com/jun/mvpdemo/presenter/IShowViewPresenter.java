package com.jun.mvpdemo.presenter;

import rx.Subscription;

/**
 * Author：JunJun
 * Description:
 */
public interface IShowViewPresenter {

   /**取得用户列表**/
    Subscription getUserListInfo(String name) ;

}
