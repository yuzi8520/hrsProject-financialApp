package com.hrs.financial.payment;

import android.os.Message;

import com.hrs.financial.httpclient.HttpClientUtil;
import com.hrs.financial.payment.bean.PaymentBean;
import com.hrs.financial.util.bean.ResultBean;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/2.
 */
public class BillAddThread implements Runnable{



    private BillAddHandler handler ;
    private PaymentBean paymentBean;

    public BillAddThread(BillAddHandler handler,PaymentBean paymentBean){
        this.handler = handler;
        this.paymentBean = paymentBean;
    }

    @Override
    public void run() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action","add"));
        ResultBean bean = HttpClientUtil.doPost(DetailThread.URL, paymentBean,
                ResultBean.class, params);
        if(bean == null ){
            bean = new ResultBean(false,"新增失败，访问服务器异常!");
        }
       Message msg =  handler.obtainMessage(1,bean);
        handler.sendMessage(msg);
    }
}
