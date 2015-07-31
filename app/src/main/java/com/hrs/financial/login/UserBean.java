package com.hrs.financial.login;

public class UserBean {
	
	
	public UserBean (){}

	public UserBean(String userName ,String password){
		this.userName = userName ;
		this.password = password;
	}


	private Integer id ;

	private String userName;

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
		return "userName="+userName+",password="+password;
		
	}
	

}
