package com.hrs.financial.payment;

import android.os.Message;

import com.hrs.financial.httpclient.HttpClientUtil;
import com.hrs.financial.payment.bean.QueryParams;
import com.hrs.financial.payment.bean.QueryResult;
import com.hrs.financial.util.ApplicationContextParams;

/**
 * Created by Administrator on 2015/7/23.
 */
public class DetailThread implements Runnable {
    private DetailHandler handler;
    public static final String URL = "http://192.168.130.223:8080/mvc/payment";
    private QueryParams params;
    public DetailThread(DetailHandler handler){
        this.handler = handler;
    }

    public DetailThread(DetailHandler handler, QueryParams params){
        this.handler = handler;
        this.params = params;
    }


    @Override
    public void run() {
        if(params == null ) {
            params = new QueryParams();
            params.setUser_id(ApplicationContextParams.getUserBean().getId());
        }

        QueryResult result = HttpClientUtil.doPost(URL,params, QueryResult.class);
        if(result == null  ){
            result = new QueryResult(false,"操作失败,无法连接服务器,请稍后在尝试!");
        }
        Message message = handler.obtainMessage(1,result);
        handler.sendMessage(message);
    }
}
