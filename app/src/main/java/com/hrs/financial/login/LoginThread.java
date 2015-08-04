package com.hrs.financial.login;

import android.os.Message;

import com.hrs.financial.httpclient.HttpClientUtil;

public class LoginThread implements Runnable{
	
	private static final String postURL = "http://192.168.130.223:8080/mvc/login";
	
	
	private UserBean userBean;
	private LoginHandler mainHandler;
	
	public LoginThread(UserBean userBean,LoginHandler mainHandler){
		this.userBean = userBean;
		this.mainHandler = mainHandler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		LoginResult result = HttpClientUtil.doPost(postURL, userBean, LoginResult.class);
		if(result == null ){
			result = new LoginResult(false,"登陆失败,无法连接服务器,请稍后在尝试!");
		}
		Message msg = mainHandler.obtainMessage(1, result);
		mainHandler.sendMessage(msg);
	}

}
