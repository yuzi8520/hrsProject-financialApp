package com.hrs.financial.util;

import com.hrs.financial.login.UserBean;

/**
 * Created by Administrator on 2015/7/22.
 */
public final class ApplicationContextParams {

    private static UserBean userBean ;




    public static void setUserBean(UserBean userBean){
        ApplicationContextParams.userBean = userBean;
    }

    public static UserBean getUserBean(){
        return userBean;
    }



}
