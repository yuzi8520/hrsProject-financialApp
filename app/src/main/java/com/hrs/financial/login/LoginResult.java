package com.hrs.financial.login;

import com.hrs.financial.util.bean.ResultBean;

/**
 * Created by Administrator on 2015/8/1.
 */
public class LoginResult extends ResultBean {

    public LoginResult(){}

    public LoginResult(boolean success,String msg ){
        super(success,msg);
    }

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
