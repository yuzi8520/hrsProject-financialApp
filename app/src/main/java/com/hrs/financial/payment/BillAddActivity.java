package com.hrs.financial.payment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.hrs.financial.R;
import com.hrs.financial.payment.bean.PaymentBean;
import com.hrs.financial.util.ApplicationContextParams;
import com.hrs.financial.util.WidgetUtil;

import org.springframework.util.StringUtils;

import java.util.Calendar;

public class BillAddActivity extends Activity {

    private static final String billTypeArr[] = {"生活(吃)","生活(用)","娱乐"};

    private EditText billTitle;
    private EditText billDate ;
    private EditText billTime;
    private EditText billMoney;
    private EditText billComment;
    private Spinner billType;
    private Button saveBT;
    private Button backBT;
    private BillAddHandler billHandler;
    private ProgressDialog dialog;

    public void hiddenProgressDialogAndShowMsg(String msg){
        if(dialog != null){
            dialog.dismiss();
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);
        billHandler = new BillAddHandler(this);
        billDate = (EditText)this.findViewById(R.id.bill_date_et);
        billTitle = (EditText)this.findViewById(R.id.bill_title_et);
        billTime = (EditText) this.findViewById(R.id.bill_date_et1);
        billMoney = (EditText)this.findViewById(R.id.bill_money_et);
        billComment = (EditText)this.findViewById(R.id.bill_comment_et);
        billType = (Spinner)this.findViewById(R.id.bill_type_sp);
        saveBT = (Button)this.findViewById(R.id.bill_add_bt);
        backBT = (Button)this.findViewById(R.id.bill_back_bt);

        billType.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_item,billTypeArr));

        billDate.setOnTouchListener(new BillDateTouchListener());
        billTime.setOnTouchListener(new BillDateTouchListener());
        saveBT.setOnClickListener(new BillBtListner());
        backBT.setOnClickListener(new BillBtListner());



    }


    private class BillBtListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.bill_back_bt){
                Intent intent = new Intent(BillAddActivity.this,DetailActivity.class);
                BillAddActivity.this.startActivity(intent);
            }

            if(v.getId() == R.id.bill_add_bt){
                String title = billTitle.getText().toString();
                String date = billDate.getText().toString();
                String time = billTime.getText().toString();
                String comment = billComment.getText().toString();
                String money = billMoney.getText().toString();
                String payTypeName = billType.getSelectedItem().toString();
                int payType = billType.getSelectedItemPosition()+1;
                if(StringUtils.isEmpty(title)){
                    Toast.makeText(BillAddActivity.this,"请输入账单标题!",Toast.LENGTH_SHORT).show();
                    WidgetUtil.requestFocus(billTime);
                    return;
                }
                if(StringUtils.isEmpty(date) || StringUtils.isEmpty(time)){
                    Toast.makeText(BillAddActivity.this,"请输入帐号单时间!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(StringUtils.isEmpty(money)){
                    Toast.makeText(BillAddActivity.this,"请输入金额!",Toast.LENGTH_SHORT).show();
                    WidgetUtil.requestFocus(billMoney);
                    return;
                }
                PaymentBean bean = new PaymentBean();
                bean.setPay_comment(comment);
                bean.setPay_date(date + " " + time);
                bean.setPay_money(Double.parseDouble(money));
                bean.setTitle(title);
                bean.setType(payType);
                bean.setType_name(payTypeName);
                bean.setUser_id(ApplicationContextParams.getUserBean().getId());
                dialog = ProgressDialog.show(BillAddActivity.this,"请稍等片刻","数据保存中..");
                new Thread(new BillAddThread(billHandler,bean)).start();

            }
        }
    }


    private class BillDateTouchListener implements  OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                Calendar currCalendar = Calendar.getInstance();
                Dialog dialog = null;
                if(v.getId() == R.id.bill_date_et){

                    dialog = new DatePickerDialog(BillAddActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                        billDate.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                                }
                            },
                            currCalendar.get(Calendar.YEAR),
                            currCalendar.get(Calendar.MONTH),
                            currCalendar.get(Calendar.DAY_OF_MONTH)
                    );
                }else if(v.getId() == R.id.bill_date_et1){
                    dialog = new TimePickerDialog(BillAddActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    billTime.setText(hourOfDay+":"+minute);
                                }
                            },
                        currCalendar.get(Calendar.HOUR_OF_DAY),
                        currCalendar.get(Calendar.MINUTE)
                            ,false
                    );
                }

                if( dialog != null){
                    dialog.show();
                }
            }
            return true;
        }
    }




}
