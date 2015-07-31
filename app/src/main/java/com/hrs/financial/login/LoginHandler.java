package com.hrs.financial.login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hrs.financial.MainActivity;
import com.hrs.financial.payment.DetailActivity;
import com.hrs.financial.util.ApplicationContextParams;
import com.hrs.financial.util.bean.ResultBean;

import org.apache.commons.lang.math.NumberUtils;

public class LoginHandler extends Handler{
	
	private MainActivity loginActivity;
	
	public LoginHandler(MainActivity activity){
		super();
		this.loginActivity = activity;
	}
	
	
	public void handleMessage(Message msg){
		ResultBean result = (ResultBean) msg.obj;
		loginActivity.hiddenProgressDialog();
		Toast.makeText(loginActivity.getApplicationContext(), result.getMsg(), Toast.LENGTH_SHORT).show();
		if(result.isSuccess()) {
			ApplicationContextParams.getUserBean()
					.setId(NumberUtils.toInt(String.valueOf(result.getTag()),0));

			Intent intent = new Intent(loginActivity, DetailActivity.class);
			loginActivity.startActivity(intent);
		}
	}

}
