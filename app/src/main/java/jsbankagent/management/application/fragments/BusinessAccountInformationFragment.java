package jsbankagent.management.application.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jsbankagent.management.application.R;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;

import static jsbankagent.management.application.HomeActivity.drawer;
import static jsbankagent.management.application.utils.AppUtils.britishFormateNew;

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
    EditText et_business_account_info_name, et_business_account_info_nature_of_business, et_business_account_info_registration_number,
            et_business_account_info_ntn_number, et_businessdateofRegistraiton, et_business_account_info_place_of_registration,
            et_business_account_info_office_address, et_business_account_info_contact_number, et_business_account_info_fax_number;
    String business_account_info_name, business_account_info_nature_of_business, business_account_info_registration_number,
            business_account_info_ntn_number, businessdateofRegistraiton, business_account_info_place_of_registration,
            business_account_info_office_address, business_account_info_contact_number, business_account_info_fax_number;
    private SimpleDateFormat dateFormatter;
    private String getPreAccountInfo = "";
    private String debitCardNeed = "";
    private JSONObject jsonObject = null;

    public BusinessAccountInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        businessinfoFragment = inflater.inflate(R.layout.fragment_business_account_information, container, false);

        //Todo Edit Text initiliaze
        et_business_account_info_name = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_name);
        et_business_account_info_nature_of_business = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_nature_of_business);
        et_business_account_info_registration_number = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_registration_number);
        et_business_account_info_ntn_number = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_ntn_number);
        et_business_account_info_place_of_registration = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_place_of_registration);
        et_business_account_info_office_address = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_office_address);
        et_business_account_info_contact_number = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_contact_number);
        et_business_account_info_fax_number = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_fax_number);
        et_businessdateofRegistraiton = (EditText) businessinfoFragment.findViewById(R.id.et_business_account_info_date_of_registration);

        et_businessdateofRegistraiton.setInputType(InputType.TYPE_NULL);
        btn_next_step5 = businessinfoFragment.findViewById(R.id.btn_next_step_5);
        iv_menu = businessinfoFragment.findViewById(R.id.imageviewMenu);

        getPreAccountInfo = DataHandler.getStringPreferences(AppConstants.PREFERENCE_PRE_ACCOUNT_INFO);
        Log.e("getPreAccountInfo", getPreAccountInfo);
        try {
            jsonObject = new JSONObject(getPreAccountInfo);
            if (jsonObject != null) {
                debitCardNeed = jsonObject.getString("pre_debit_card_need");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                    if (!checkFields()) {


                            AppConstants.registrationObject.put("business_account_info_name", et_business_account_info_name.getText().toString().trim());
                            AppConstants.registrationObject.put("business_account_info_nature_of_business", et_business_account_info_nature_of_business.getText().toString().trim());
                            AppConstants.registrationObject.put("business_account_info_registration_number", et_business_account_info_registration_number.getText().toString().trim());
                            AppConstants.registrationObject.put("business_account_info_ntn_number", et_business_account_info_ntn_number.getText().toString().trim());
                            AppConstants.registrationObject.put("business_account_date_of_registration", et_businessdateofRegistraiton.getText().toString().trim());
                            AppConstants.registrationObject.put("business_account_info_place_of_registration", et_business_account_info_place_of_registration.getText().toString().trim());
                            AppConstants.registrationObject.put("business_account_info_office_address", et_business_account_info_office_address.getText().toString().trim());
                            AppConstants.registrationObject.put("business_account_info_contact_number", et_business_account_info_contact_number.getText().toString().trim());
                            AppConstants.registrationObject.put("business_account_info_fax_number", et_business_account_info_fax_number.getText().toString().trim());


                        if (debitCardNeed.equalsIgnoreCase("No")) {

                            AppConstants.registrationObject.put("debit_card_debit_card_request", "N/A");
                            AppConstants.registrationObject.put("debit_card_name_on_debit_card", "N/A");
                            AppConstants.registrationObject.put("debit_card_mother_name", "N/A");

                            /*Toast.makeText(getActivity(), "Sorry I don't need debit card", Toast.LENGTH_SHORT).show();*/
                        }
                            if (debitCardNeed.equalsIgnoreCase("No")){

                                fragment = new SupplementaryCardFragment();
                                fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frame_container, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }else{
                                fragment = new DebitCardInformation();
                                fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frame_container, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }


                        DataHandler.updatePreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION, AppConstants.registrationObject.toString());

                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
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
                SimpleDateFormat newFormat = new SimpleDateFormat(britishFormateNew);
                dateofregistration = newFormat.format(newDate.getTime());
                et_businessdateofRegistraiton.setText(dateofregistration);
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


    //Todo Check Form Fields Business Account information fragment
    public boolean checkFields() {

        et_business_account_info_name.setError(null);
        et_business_account_info_nature_of_business.setError(null);
        et_business_account_info_registration_number.setError(null);
        et_business_account_info_ntn_number.setError(null);
        et_businessdateofRegistraiton.setError(null);
        et_business_account_info_place_of_registration.setError(null);
        et_business_account_info_office_address.setError(null);
        et_business_account_info_contact_number.setError(null);
        et_business_account_info_fax_number.setError(null);

        boolean cancel = false;
        View focusView = null;

        business_account_info_name = et_business_account_info_name.getText().toString().trim();
        business_account_info_nature_of_business = et_business_account_info_nature_of_business.getText().toString().trim();
        business_account_info_registration_number = et_business_account_info_registration_number.getText().toString().trim();
        business_account_info_ntn_number = et_business_account_info_ntn_number.getText().toString().trim();
        businessdateofRegistraiton = et_businessdateofRegistraiton.getText().toString().trim();
        business_account_info_place_of_registration = et_business_account_info_place_of_registration.getText().toString().trim();
        business_account_info_office_address = et_business_account_info_office_address.getText().toString().trim();
        business_account_info_contact_number = et_business_account_info_contact_number.getText().toString().trim();
        business_account_info_fax_number = et_business_account_info_fax_number.getText().toString().trim();


        try {
            if (TextUtils.isEmpty(business_account_info_name)) {
                et_business_account_info_name.setError(getString(R.string.error_field_required));
                focusView = et_business_account_info_name;
                cancel = true;
            } else if (TextUtils.isEmpty(business_account_info_nature_of_business)) {

                et_business_account_info_nature_of_business.setError(getString(R.string.error_field_required));
                focusView = et_business_account_info_nature_of_business;
                cancel = true;
            } else if (TextUtils.isEmpty(business_account_info_registration_number)) {
                et_business_account_info_registration_number.setError(getString(R.string.error_field_required));
                focusView = et_business_account_info_registration_number;
                cancel = true;
            } else if (TextUtils.isEmpty(business_account_info_ntn_number)) {
                et_business_account_info_ntn_number.setError(getString(R.string.error_field_required));
                focusView = et_business_account_info_ntn_number;
                cancel = true;
            } else if (TextUtils.isEmpty(businessdateofRegistraiton)) {
                et_businessdateofRegistraiton.setError(getString(R.string.error_field_required));
                focusView = et_businessdateofRegistraiton;
                cancel = true;
            } else if (TextUtils.isEmpty(business_account_info_place_of_registration)) {
                et_business_account_info_place_of_registration.setError(getString(R.string.error_field_required));
                focusView = et_business_account_info_place_of_registration;
                cancel = true;
            } else if (TextUtils.isEmpty(business_account_info_office_address)) {
                et_business_account_info_office_address.setError(getString(R.string.error_field_required));
                focusView = et_business_account_info_office_address;
                cancel = true;
            } else if (TextUtils.isEmpty(business_account_info_contact_number)) {
                et_business_account_info_contact_number.setError(getString(R.string.error_field_required));
                focusView = et_business_account_info_contact_number;
                cancel = true;
            } else if (TextUtils.isEmpty(business_account_info_fax_number)) {
                et_business_account_info_fax_number.setError(getString(R.string.error_field_required));
                focusView = et_business_account_info_fax_number;
                cancel = true;
            }
            if (cancel) {

                focusView.requestFocus();

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return cancel;
    }
}


