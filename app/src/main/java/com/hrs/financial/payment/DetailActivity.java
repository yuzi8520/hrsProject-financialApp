package com.hrs.financial.payment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.hrs.financial.R;

/**
 * Created by hrs on 2015/7/21.
 */
public class DetailActivity extends Activity {

    ListView listView;
    private ProgressDialog progressDialog;
    private DetailHandler hander;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_bill_detail);
        listView = (ListView) findViewById(R.id.payment_list_view);
        hander = new DetailHandler(this);
        progressDialog = ProgressDialog.show(this,"请稍等片刻","账单明细加载中...");
        new Thread(new DetailThread(hander)).start();
    }


    public void hiddenProgressDialog( ){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE,Menu.FIRST+1,1,"添加账单").setIcon(android.R.drawable.ic_menu_add);
        menu.add(Menu.NONE,Menu.FIRST+2,2,"退出系统").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return true;
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
