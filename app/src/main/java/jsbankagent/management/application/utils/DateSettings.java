package jsbankagent.management.application.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

public class DateSettings implements DatePickerDialog.OnDateSetListener {
    Context context;
    public DateSettings(Context context) {
        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

    }
}
