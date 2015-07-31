package com.hrs.financial.payment.bean;

/**
 * Created by Administrator on 2015/7/23.
 */
public class QueryParams {



    private int user_id;
    private int page = 1;
    private String action ="query";

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
