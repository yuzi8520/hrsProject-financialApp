package com.hrs.financial.payment;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hrs.financial.payment.bean.QueryResult;

/**
 * Created by Administrator on 2015/7/23.
 */
public class DetailHandler extends Handler {

    private DetailActivity activity;

    public DetailHandler(DetailActivity activity){
        this.activity = activity;
    }


    public void handleMessage(Message msg) {
        QueryResult result = (QueryResult)msg.obj;
        activity.hiddenProgressDialog();
        if(  ! result.isSuccess() ){
            Toast.makeText(activity.getApplicationContext(),result.getMsg(),Toast.LENGTH_SHORT).show();
            return;
        }
        activity.listView.setAdapter(new BillListAdapter(activity,result.getPaymentList()));
    }

}
