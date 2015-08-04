package com.hrs.financial.login;

public class UserBean {
	
	
	public UserBean (){}

	public UserBean(String userAccount ,String password){
		this.userAccount = userAccount ;
		this.password = password;
	}


	private Integer id ;

	private String userName;

	private String userAccount;

	private String password;



	public String getUserName() {
		return userName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		return "userAccount="+userAccount+",password="+password;
		
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
}
