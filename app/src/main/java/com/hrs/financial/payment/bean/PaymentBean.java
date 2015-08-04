package com.hrs.financial.payment.bean;

/**
 * Created by Administrator on 2015/7/23.
 */
public class PaymentBean {


    private Integer id ;

    private Integer user_id;

    private String pay_date ;

    private String title;

    private Double  pay_money;

    private String pay_comment;

    private Integer type;

    private String 	type_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPay_money() {
        return pay_money;
    }

    public void setPay_money(Double pay_money) {
        this.pay_money = pay_money;
    }

    public String getPay_comment() {
        return pay_comment;
    }

    public void setPay_comment(String pay_comment) {
        this.pay_comment = pay_comment;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

}
