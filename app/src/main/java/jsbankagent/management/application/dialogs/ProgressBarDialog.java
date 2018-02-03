package jsbankagent.management.application.dialogs;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import jsbankagent.management.application.R;


public class ProgressBarDialog extends BaseAlertDialog implements OnClickListener {

	ProgressBar progressBar;
	Context context;
	TextView message;
	TextView noteTV;
	boolean isSuccess = true;
	private int width=500;

	public ProgressBarDialog(Context context) {
		super(context);
		this.context = context;

		LayoutInflater factory = LayoutInflater.from(context);
		final View progressBarView = factory.inflate(R.layout.dialog_progressbar, null);

		setView(progressBarView);
		
		progressBar = (ProgressBar) progressBarView.findViewById(R.id.progressBar);
		message = (TextView) progressBarView.findViewById(R.id.progressMessage);
		this.setCanceledOnTouchOutside(false);
		
	}

    public void setTitle(String title){
        super.setTitle(title);
    }

	@Override
	public void setMessage(CharSequence message) {
		this.message.setText(message);
	}
    public void setButton(int type,String text){
    	setButton(type,text, this);
    }
	public void hideProgressBar() {
		progressBar.setVisibility(View.INVISIBLE);
	}

	public void showProgressBar() {
		progressBar.setVisibility(View.VISIBLE);
	}
	
	public void setNoteMessage(String message) {
		noteTV.setVisibility(View.VISIBLE);
		noteTV.setText("*Note: " + message);		
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		
		switch (which) {		
			case BUTTON_POSITIVE:
				dismissManually();
				break;
			case BUTTON_NEGATIVE:	
				if(!isSuccess) {
					dismissManually();
//					((StartupActivity)context).finish();					
				}
				break;
			default:
				break;
		}
	}
	
	public void setWidth(int width){
		this.width = width;
	}

	@Override
	public void show() {
		super.show();
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	}


	@Override
	public void dismiss() {
		super.dismiss();
	}

	public void dismissManually() {
		super.dismiss();
	}
}
