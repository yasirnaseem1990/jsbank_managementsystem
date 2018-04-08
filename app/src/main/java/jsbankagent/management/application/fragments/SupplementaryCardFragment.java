package jsbankagent.management.application.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplementaryCardFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener{

    View supplementarycardFragment;
    Button btn_next_step7;
    Fragment fragment;
    ImageView iv_menu;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Spinner spinnerrequestsupplementaryCard;
    String requestsupplementaryCard;
    private DatePickerDialog dateofbirthDatePickerDialog;
    private DatePickerDialog expirydateofVisaPickerDialog;
    public String expirydateofvisa;
    public String dateofbirth;
    EditText et_supplementarycardDOB,et_supplementary_card_full_name,et_supplementary_card_relationship,et_supplementary_card_cnic_number,et_supplementarycardvisaexpiryDate,
            et_supplementary_card_mother_name,et_supplementary_card_name_supplementary;
    String supplementarycardDOB,supplementary_card_full_name,supplementary_card_cnic_number,supplementarycardvisaexpiryDate,supplementary_card_mother_name,
            supplementary_card_name_supplementary;
    private SimpleDateFormat dateFormatter;
    private String getPreAccountInfo = "";
    private String ebankingNeed = "";
    private JSONObject jsonObject = null;
    public SupplementaryCardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        supplementarycardFragment = inflater.inflate(R.layout.fragment_supplementary_card, container, false);

        spinnerrequestsupplementaryCard = supplementarycardFragment.findViewById(R.id.spinner_supplementary_card_request);
        et_supplementary_card_full_name = (EditText) supplementarycardFragment.findViewById(R.id.et_supplementary_card_full_name);
        et_supplementary_card_relationship = (EditText) supplementarycardFragment.findViewById(R.id.et_supplementary_card_relationship);
        et_supplementary_card_cnic_number = (EditText) supplementarycardFragment.findViewById(R.id.et_supplementary_card_cnic_number);
        et_supplementary_card_mother_name = (EditText) supplementarycardFragment.findViewById(R.id.et_supplementary_card_mother_name);
        et_supplementary_card_name_supplementary = (EditText) supplementarycardFragment.findViewById(R.id.et_supplementary_card_name_supplementary);

        et_supplementarycardDOB = (EditText) supplementarycardFragment.findViewById(R.id.et_supplementary_card_dob);
        et_supplementarycardDOB.setInputType(InputType.TYPE_NULL);
        et_supplementarycardDOB.requestFocus();

        et_supplementarycardvisaexpiryDate = (EditText) supplementarycardFragment.findViewById(R.id.et_supplementary_card_expiry_cnic_date);
        et_supplementarycardvisaexpiryDate.setInputType(InputType.TYPE_NULL);
        btn_next_step7 = supplementarycardFragment.findViewById(R.id.btn_next_step_7);
        iv_menu = supplementarycardFragment.findViewById(R.id.imageviewMenu);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        getPreAccountInfo = DataHandler.getStringPreferences(AppConstants.PREFERENCE_PRE_ACCOUNT_INFO);
        Log.e("getPreAccountInfo",getPreAccountInfo);
        try {
            jsonObject = new JSONObject(getPreAccountInfo);
            if (jsonObject != null){
                ebankingNeed= jsonObject.getString("pre_ebanking_need");
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

        btn_next_step7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (!checkFields()){

                        if (ebankingNeed.equalsIgnoreCase("No")){

                            AppConstants.registrationObject.put("e_banking_sms_alerts", "N/A");
                            AppConstants.registrationObject.put("e_banking_internet_banking", "N/A");
                            AppConstants.registrationObject.put("e_banking_mobile_banking", "N/A");
                            AppConstants.registrationObject.put("e_banking_e_statement", "N/A");
                            AppConstants.registrationObject.put("e_banking_frequency", "N/A");

                            Toast.makeText(getActivity(), "Sorry i don't need E-Banking ", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "Form Saved", Toast.LENGTH_SHORT).show();
                        }else {

                            AppConstants.registrationObject.put("supplementary_card_request", requestsupplementaryCard);
                            AppConstants.registrationObject.put("supplementary_card_full_name", et_supplementary_card_full_name.getText().toString().trim());
                            AppConstants.registrationObject.put("supplementary_card_dobs", et_supplementarycardDOB.getText().toString().trim());
                            AppConstants.registrationObject.put("supplementary_card_relationship", et_supplementary_card_relationship.getText().toString().trim());
                            AppConstants.registrationObject.put("supplementary_card_cnic_number", et_supplementary_card_cnic_number.getText().toString().trim());
                            AppConstants.registrationObject.put("supplementary_card_visa_expiry_date", et_supplementarycardvisaexpiryDate.getText().toString().trim());
                            AppConstants.registrationObject.put("supplementary_card_mother_name", et_supplementary_card_mother_name.getText().toString().trim());
                            AppConstants.registrationObject.put("supplementary_card_name_on_supplementary_card", et_supplementary_card_name_supplementary.getText().toString().trim());


                            fragment = new EBankingFragment();
                            fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frame_container, fragment);
                            fragmentTransaction.commit();
                        }
                        DataHandler.updatePreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION, AppConstants.registrationObject.toString());


                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        try{
            //Request for supplementary card Spinner
            ArrayAdapter<CharSequence> requestsupplementaryAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listsupplementaryCard,android.R.layout.simple_spinner_item);
            requestsupplementaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerrequestsupplementaryCard.setAdapter(requestsupplementaryAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }


        setDateTimeField();
        spinnerrequestsupplementaryCard.setOnItemSelectedListener(this);
        return supplementarycardFragment;
    }

    private void setDateTimeField() {
        et_supplementarycardDOB.setOnClickListener(this);
        et_supplementarycardvisaexpiryDate.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        dateofbirthDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                et_supplementarycardDOB.setText(dateFormatter.format(newDate.getTime()));
                dateofbirth = et_supplementarycardDOB.getText().toString();
                Log.e("dateofbirth",dateofbirth);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        expirydateofVisaPickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                et_supplementarycardvisaexpiryDate.setText(dateFormatter.format(newDate.getTime()));
                expirydateofvisa = et_supplementarycardvisaexpiryDate.getText().toString();
                Log.e("expirydateofvisa",expirydateofvisa);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View v) {
        if(v == et_supplementarycardDOB){
            dateofbirthDatePickerDialog.show();
        }else if (v == et_supplementarycardvisaexpiryDate){

            expirydateofVisaPickerDialog.show();
        }

    }

    //Todo Check Form Fields For Supplementary Card Fragment
    public boolean checkFields() {

        et_supplementarycardDOB.setError(null);
        et_supplementary_card_full_name.setError(null);
        et_supplementary_card_cnic_number.setError(null);
        et_supplementarycardvisaexpiryDate.setError(null);
        et_supplementary_card_mother_name.setError(null);
        et_supplementary_card_name_supplementary.setError(null);

        boolean cancel = false;
        View focusView = null;

        supplementarycardDOB = et_supplementarycardDOB.getText().toString().trim();
        supplementary_card_full_name = et_supplementary_card_full_name.getText().toString().trim();
        supplementary_card_cnic_number = et_supplementary_card_cnic_number.getText().toString().trim();
        supplementarycardvisaexpiryDate = et_supplementarycardvisaexpiryDate.getText().toString().trim();
        supplementary_card_mother_name = et_supplementary_card_mother_name.getText().toString().trim();
        supplementary_card_name_supplementary = et_supplementary_card_name_supplementary.getText().toString().trim();


        try {
            if (TextUtils.isEmpty(requestsupplementaryCard)) {
                Toast toast =Toast.makeText(getActivity(), "Please select courtesy title", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerrequestsupplementaryCard;
                cancel = true;
            } else if (TextUtils.isEmpty(supplementary_card_full_name)) {
                et_supplementary_card_full_name.setError(getString(R.string.error_field_required));
                focusView = et_supplementary_card_full_name;
                cancel = true;
            } else if (TextUtils.isEmpty(supplementarycardDOB)) {

                et_supplementarycardDOB.setError(getString(R.string.error_field_required));
                focusView = et_supplementarycardDOB;
                cancel = true;
            } else if (TextUtils.isEmpty(supplementary_card_cnic_number)) {
                et_supplementary_card_cnic_number.setError(getString(R.string.error_field_required));
                focusView = et_supplementary_card_cnic_number;
                cancel = true;
            } else if (TextUtils.isEmpty(supplementarycardvisaexpiryDate)) {
                et_supplementarycardvisaexpiryDate.setError(getString(R.string.error_field_required));
                focusView = et_supplementarycardvisaexpiryDate;
                cancel = true;
            } else if (TextUtils.isEmpty(supplementary_card_mother_name)) {
                et_supplementary_card_mother_name.setError(getString(R.string.error_field_required));
                focusView = et_supplementary_card_mother_name;
                cancel = true;
            }else if (TextUtils.isEmpty(supplementary_card_name_supplementary)) {
                et_supplementary_card_name_supplementary.setError(getString(R.string.error_field_required));
                focusView = et_supplementary_card_name_supplementary;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView) view;
        try{
            if (position == 0) {
                tv.setTextColor(Color.GRAY);
            } else {
                tv.setTextColor(Color.BLACK);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        try{
            if (position > 0){
                switch (parent.getId()){
                    case R.id.spinner_supplementary_card_request:
                        requestsupplementaryCard = parent.getSelectedItem().toString();
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
