package com.hrs.financial.payment;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.hrs.financial.util.bean.ResultBean;

/**
 * Created by Administrator on 2015/8/2.
 */
public class BillAddHandler extends Handler {

    private BillAddActivity activity;

    public BillAddHandler(BillAddActivity activity){
        this.activity = activity;
    }

    public void handleMessage(Message msg) {
        ResultBean bean =(ResultBean)msg.obj;

        activity.hiddenProgressDialogAndShowMsg(bean.getMsg());

        if(bean.isSuccess()){
            Intent intent =new Intent(activity,DetailActivity.class);
            activity.startActivity(intent);
        }
    }

}
