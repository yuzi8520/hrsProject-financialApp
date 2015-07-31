package com.hrs.financial.payment;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/7/23.
 */
public class BillListItemComponent {

    private ImageView paymentImage;
    private TextView  paymentMoney;
    private TextView  paymentDate;
    private TextView  paymentTitle;



    public ImageView getPaymentImage() {
        return paymentImage;
    }

    public void setPaymentImage(ImageView paymentImage) {
        this.paymentImage = paymentImage;
    }

    public TextView getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(TextView paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public TextView getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(TextView paymentDate) {
        this.paymentDate = paymentDate;
    }

    public TextView getPaymentTitle() {
        return paymentTitle;
    }

    public void setPaymentTitle(TextView paymentTitle) {
        this.paymentTitle = paymentTitle;
    }
}
