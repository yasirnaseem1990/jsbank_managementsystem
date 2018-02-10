package jsbankagent.management.application.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jsbankagent.management.application.R;

import static jsbankagent.management.application.HomeActivity.drawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessAccountInformationFragment extends Fragment implements View.OnClickListener {

    View businessinfoFragment;
    ImageView iv_menu;
    Button btn_next_step5;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private DatePickerDialog dateofregistrationDatePickerDialog;
    public String dateofregistration;
    EditText et_businessdateofRegistraiton;
    private SimpleDateFormat dateFormatter;

    public BusinessAccountInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        businessinfoFragment = inflater.inflate(R.layout.fragment_business_account_information, container, false);
        et_businessdateofRegistraiton = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_date_of_registration);
        et_businessdateofRegistraiton.setInputType(InputType.TYPE_NULL);
        btn_next_step5 = businessinfoFragment.findViewById(R.id.btn_next_step_5);
        iv_menu = businessinfoFragment.findViewById(R.id.imageviewMenu);
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                        drawer.openDrawer(Gravity.LEFT);
                    } else
                        drawer.closeDrawers();
                } catch (Exception e) {
                    Log.e("Exception Menu Drawer", "" + e);
                }
            }
        });

        btn_next_step5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    fragment = new DebitCardInformation();
                    fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.commit();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
        setDateTimeField();
        return businessinfoFragment;
    }

    private void setDateTimeField() {
        et_businessdateofRegistraiton.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Calendar newCalendar = Calendar.getInstance();
        dateofregistrationDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                et_businessdateofRegistraiton.setText(dateFormatter.format(newDate.getTime()));
                dateofregistration = et_businessdateofRegistraiton.getText().toString();
                Log.e("dateofbirth", dateofregistration);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View v) {
        if (v == et_businessdateofRegistraiton) {
            dateofregistrationDatePickerDialog.show();
        }
    }
}


