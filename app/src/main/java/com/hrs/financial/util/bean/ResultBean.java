package com.hrs.financial.util.bean;

public class ResultBean {




	private boolean success;
	private String msg;
	private Object tag;

	public ResultBean(){

	}

	public ResultBean(boolean success,String msg){
		this.success = success;
		this.msg = msg;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}
}
