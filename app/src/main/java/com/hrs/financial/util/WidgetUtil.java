package com.hrs.financial.util;

import android.view.View;

public final  class WidgetUtil {
	
	public static void requestFocus(View view){
		if(view == null){
			return ;
		}
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
	}
	
	

}
