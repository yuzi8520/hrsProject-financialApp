package com.hrs.financial.payment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hrs.financial.R;
import com.hrs.financial.payment.bean.PaymentBean;

import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Administrator on 2015/7/23.
 */
public class BillListAdapter extends BaseAdapter{


    private List<PaymentBean> itemList;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public BillListAdapter(Activity activity,List<PaymentBean> itemList){
        Assert.notNull(activity,"activity argument can not be null!");
        Assert.notNull(itemList,"itemList arugment can not be null!");
        this.activity = activity;
        this.itemList = itemList;
        this.layoutInflater = LayoutInflater.from(activity);

    }

    public List<PaymentBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<PaymentBean> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BillListItemComponent itemComponent = null;
        if(convertView == null){
            convertView =  layoutInflater.inflate(R.layout.list_payment,null);
            itemComponent = new BillListItemComponent();
            itemComponent.setPaymentImage((ImageView)convertView.findViewById(R.id.payment_q_type_iv));
            itemComponent.setPaymentDate((TextView) convertView.findViewById(R.id.payment_q_date_tv));
            itemComponent.setPaymentMoney((TextView) convertView.findViewById(R.id.payment_q_money_tv));
            itemComponent.setPaymentTitle((TextView) convertView.findViewById(R.id.payment_q_title_tv));
            convertView.setTag(itemComponent);
        }
        itemComponent = (BillListItemComponent)convertView.getTag();
        PaymentBean bean = itemList.get(position);
        itemComponent.getPaymentDate().setText(bean.getPay_date());
        itemComponent.getPaymentMoney().setText(bean.getPay_money()+"￥");
        itemComponent.getPaymentTitle().setText(bean.getTitle());

        return convertView;
    }

}
