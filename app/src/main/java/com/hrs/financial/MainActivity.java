package com.hrs.financial;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hrs.financial.login.LoginHandler;
import com.hrs.financial.login.LoginThread;
import com.hrs.financial.login.UserBean;
import com.hrs.financial.util.WidgetUtil;

public class MainActivity extends Activity {
	
	
	private EditText username;
	private EditText pwd ;
	private Button loginBT ; 
	private ProgressDialog loginDialog;
	private LoginHandler loginHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText)findViewById(R.id.login_username_et);
		pwd = (EditText)findViewById(R.id.login_password_et);
		loginBT = (Button)findViewById(R.id.login_btn);		
		loginBT.setOnClickListener(new LoginBtOnClickListener());
		loginHandler = new LoginHandler(this);
	}
	
	
	private class LoginBtOnClickListener implements  OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String userAccount = username.getText().toString().trim();
			String password = pwd.getText().toString().trim();
			if(StringUtils.isBlank(userAccount)){
				Toast.makeText(MainActivity.this.getApplicationContext(),"请输入需登陆的账号!", Toast.LENGTH_SHORT).show();
				WidgetUtil.requestFocus(username);
				return;
			}
			if(StringUtils.isBlank(password)){
				Toast.makeText(MainActivity.this.getApplicationContext(),"请输入需登陆的密码!", Toast.LENGTH_SHORT).show();
				WidgetUtil.requestFocus(pwd);
				return ;
			}	
			loginDialog = ProgressDialog.show(MainActivity.this, "请稍等片刻", "正在登陆中..");
			UserBean userBean = new UserBean(userAccount,password);
			new Thread(new LoginThread(userBean,loginHandler)).start();
		}
	}
	
	
	public void hiddenProgressDialog(){
		if(loginDialog != null ){
			loginDialog.dismiss();
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}


	
	
}
