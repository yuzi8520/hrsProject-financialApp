package com.hrs.financial.payment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.hrs.financial.R;
import com.hrs.financial.payment.bean.QueryParams;
import com.hrs.financial.payment.bean.QueryResult;
import com.hrs.financial.util.ApplicationContextParams;

/**
 * Created by hrs on 2015/7/21.
 */
public class DetailActivity extends Activity {

    ListView listView;
    private ProgressDialog progressDialog;
    private DetailHandler hander;
    private Button nextBT;
    private Button preBT ;
    private int currPage;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_bill_detail);
        listView = (ListView) findViewById(R.id.payment_list_view);
        nextBT = (Button)findViewById(R.id.page_nex);
        preBT = (Button)findViewById(R.id.page_pre);
        preBT.setOnClickListener(new PageTrunListener());
        nextBT.setOnClickListener(new PageTrunListener());
        hander = new DetailHandler(this);
        progressDialog = ProgressDialog.show(this,"请稍等片刻","账单明细加载中...");
        new Thread(new DetailThread(hander)).start();
    }

            private class PageTrunListener implements View.OnClickListener {


                @Override
                public void onClick(View v) {
                    QueryParams params = new QueryParams();
                    params.setUser_id(ApplicationContextParams.getUserBean().getId());

                    if(v.getId() == R.id.page_nex){
                        params.setPage(currPage+1);
                        // new Thread(new DetailThread(hander,params)).start();
                    }
                    if(v.getId() == R.id.page_pre){
                        params.setPage(currPage-1);
                    }
                    progressDialog = ProgressDialog.show(DetailActivity.this,"请稍等片刻","账单明细加载中...");
                    new Thread(new DetailThread(hander,params)).start();
        }
    }

    public void hiddenProgressDialog( ){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        Intent addBillIntent = new Intent();
        addBillIntent.setClass(this,BillAddActivity.class);
        menu.add(Menu.NONE,Menu.FIRST+1,1,"添加账单").setIcon(android.R.drawable.ic_menu_add)
        .setIntent(addBillIntent);

        menu.add(Menu.NONE,Menu.FIRST+2,2,"退出系统").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return true;
    }


    public void afterLoadListSuccess(QueryResult result){
        currPage = result.getCurPage();
        preBT.setVisibility(View.VISIBLE);
        nextBT.setVisibility(View.VISIBLE);
        System.err.println("currPage="+currPage+",&count="+result.getCount()+"&pageSize="+
        result.getPageSize()+"&pageCount="+result.getPageCount());
        if(result.getCurPage()  <=1){
            System.out.println("隐藏前页按钮!");
            preBT.setVisibility(View.INVISIBLE);
        }
        if(result.getCurPage()>=result.getPageCount()){
            System.out.println("隐藏后页按钮!");
            nextBT.setVisibility(View.INVISIBLE);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case Menu.FIRST+1:
                break;
            case Menu.FIRST+2:
                finish();
                break;
        }
        return false;
    }




}
