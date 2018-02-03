package jsbankagent.management.application.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.KeyEvent;

@SuppressLint("HandlerLeak")
public class BaseAlertDialog extends AlertDialog {

	Context context;
	public BaseAlertDialog(Context context) {
		super(context);
		this.context = context;
	}
	
//	@Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        if(!hasFocus) {
//            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//            context.sendBroadcast(closeDialog);
//        }
//    }

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {


			dismiss();
		}
		return true;
	};
	
	public void setButton(int whichButton, int text,
			OnClickListener listener) {
//		dismissSystemDialog();
		super.setButton(whichButton, context.getString(text), listener);
	}
	
	public void setButton(int whichButton, String text, OnClickListener listener) {
//		dismissSystemDialog();
		super.setButton(whichButton, text, listener);
	}
	
	@Override
	public void show() {

		try{

		super.show();


//		dismissSystemDialog();
		getButton(BUTTON_POSITIVE).setTextSize(20);
		getButton(BUTTON_NEGATIVE).setTextSize(20);
		}catch (NullPointerException e){
			e.printStackTrace();
		}


	}
	
	@Override
	public void dismiss() {
		super.dismiss();
//		dismissSystemDialog();
	}
	
//	private void dismissSystemDialog() {
//		Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//        context.sendBroadcast(closeDialog);
//
//	}

}
