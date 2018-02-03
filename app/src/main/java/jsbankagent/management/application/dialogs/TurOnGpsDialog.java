package jsbankagent.management.application.dialogs;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import jsbankagent.management.application.R;


/**
 * Created by mcs on 12/28/2016.
 */

public class TurOnGpsDialog extends BaseAlertDialog implements DialogInterface.OnClickListener {
   TextView tv_uuid;
    ContentResolver resolver;


    public TurOnGpsDialog(Context context) {
        super(context);

        LayoutInflater factory = LayoutInflater.from(context);
        final View progressBarView = factory.inflate(R.layout.custom_dialog_uuid, null);

        setView(progressBarView);
        this.context = context;
        tv_uuid = (TextView) progressBarView.findViewById(R.id.tv_uuid);
        setButton(BUTTON_POSITIVE, context.getString(R.string.action_send), this);
        setButton(BUTTON_NEGATIVE, context.getString(R.string.act_cancel), this);

        setTitle(context.getString(R.string.title_gps_enable));
        resolver = context.getContentResolver();

            tv_uuid.setText(context.getString(R.string.message_gps_enable));
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which){
            case BUTTON_POSITIVE:
                dismiss();
                Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(myIntent);

                break;
            case BUTTON_NEGATIVE:
                dialog.dismiss();
                dialog.cancel();
                dismiss();
        }
    }
    @Override
    public void dismiss() {
        super.dismiss();
    }
}
