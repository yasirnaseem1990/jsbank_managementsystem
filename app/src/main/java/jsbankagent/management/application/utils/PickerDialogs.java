package jsbankagent.management.application.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class PickerDialogs extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        DateSettings dateSettings = new DateSettings(getActivity());
        Calendar calendar = Calendar.getInstance();
        int year =  calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(getActivity(),dateSettings,year,month,day);
        return datePickerDialog;
    }
}
