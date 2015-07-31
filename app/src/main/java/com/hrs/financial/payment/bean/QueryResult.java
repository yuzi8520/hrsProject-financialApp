package com.hrs.financial.payment.bean;

import com.hrs.financial.util.bean.ResultBean;

import java.util.List;

/**
 * Created by Administrator on 2015/7/26.
 */
public class QueryResult  extends ResultBean {

    public QueryResult(boolean success,String msg){
       super(success,msg);
    }


    private int count;

    private int curPage;

    private int pageSize;




    private List<PaymentBean> paymentList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public List<PaymentBean> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<PaymentBean> paymentList) {
        this.paymentList = paymentList;
    }
}
