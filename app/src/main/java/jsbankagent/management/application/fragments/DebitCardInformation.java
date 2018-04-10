package jsbankagent.management.application.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import jsbankagent.management.application.R;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;

import static jsbankagent.management.application.HomeActivity.drawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class DebitCardInformation extends Fragment implements AdapterView.OnItemSelectedListener{

    View debitcardFragment;
    Button btn_next_step6;
    Fragment fragment;
    ImageView iv_menu;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Spinner spinnerdebitcardRequest;
    String debitcardRequest;
    EditText et_debit_card_name_on_debit_card,et_debit_card_mother_name;
    String debit_card_name_on_debit_card,debit_card_mother_name;
    private String getPreAccountInfo = "";
    private String supplementarycardNeed = "";
    private JSONObject jsonObject = null;
    public DebitCardInformation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        debitcardFragment = inflater.inflate(R.layout.fragment_debit_card_information, container, false);
        btn_next_step6 = debitcardFragment.findViewById(R.id.btn_next_step_6);
        et_debit_card_name_on_debit_card = debitcardFragment.findViewById(R.id.et_debit_card_name_on_debit_card);
        et_debit_card_mother_name = debitcardFragment.findViewById(R.id.et_debit_card_mother_name);

        spinnerdebitcardRequest = debitcardFragment.findViewById(R.id.spinner_debit_card_request);

        //TODO Check for Supplementary Card Object
        getPreAccountInfo = DataHandler.getStringPreferences(AppConstants.PREFERENCE_PRE_ACCOUNT_INFO);
        Log.e("getPreAccountInfo",getPreAccountInfo);
        try {
            jsonObject = new JSONObject(getPreAccountInfo);
            if (jsonObject != null){
                supplementarycardNeed = jsonObject.getString("pre_supplementary_card_need");
                Log.e("supplementarycardNeed",supplementarycardNeed);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        iv_menu = debitcardFragment.findViewById(R.id.imageviewMenu);
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
        btn_next_step6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (!checkFields()){

                        if (supplementarycardNeed.equalsIgnoreCase("No")){

                            AppConstants.registrationObject.put("supplementary_card_request", "N/A");
                            AppConstants.registrationObject.put("supplementary_card_full_name", "N/A");
                            AppConstants.registrationObject.put("supplementary_card_dobs", "N/A");
                            AppConstants.registrationObject.put("supplementary_card_relationship", "N/A");
                            AppConstants.registrationObject.put("supplementary_card_cnic_number", "N/A");
                            AppConstants.registrationObject.put("supplementary_card_visa_expiry_date", "N/A");
                            AppConstants.registrationObject.put("supplementary_card_mother_name", "N/A");
                            AppConstants.registrationObject.put("supplementary_card_name_on_supplementary_card", "N/A");

                            /*Toast.makeText(getActivity(), "Sorry i don't need supplementary debit card", Toast.LENGTH_SHORT).show();*/
                        }


                            AppConstants.registrationObject.put("debit_card_debit_card_request", debitcardRequest);
                            AppConstants.registrationObject.put("debit_card_name_on_debit_card", et_debit_card_name_on_debit_card.getText().toString().trim());
                            AppConstants.registrationObject.put("debit_card_mother_name", et_debit_card_mother_name.getText().toString().trim());

                            if (supplementarycardNeed.equalsIgnoreCase("No")){
                                Fragment fragment = new EBankingFragment();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frame_container, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                            else{
                                fragment = new SupplementaryCardFragment();
                                fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frame_container, fragment);
                                fragmentTransaction.addToBackStack(null);
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
            //Debit card request Spinner
            ArrayAdapter<CharSequence> debitcardrequestAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listdebitcardRequest,android.R.layout.simple_spinner_item);
            debitcardrequestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerdebitcardRequest.setAdapter(debitcardrequestAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        spinnerdebitcardRequest.setOnItemSelectedListener(this);
        return debitcardFragment;
    }

    //Todo Check Form Fields
    public boolean checkFields() {

        et_debit_card_name_on_debit_card.setError(null);
        et_debit_card_mother_name.setError(null);


        boolean cancel = false;
        View focusView = null;

        debit_card_name_on_debit_card = et_debit_card_name_on_debit_card.getText().toString().trim();
        debit_card_mother_name = et_debit_card_mother_name.getText().toString().trim();


        try {
            if (TextUtils.isEmpty(debitcardRequest)) {
                Toast toast =Toast.makeText(getActivity(), "Please select courtesy title", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerdebitcardRequest;
                cancel = true;
            }
            else if (TextUtils.isEmpty(debit_card_name_on_debit_card)) {

                et_debit_card_name_on_debit_card.setError(getString(R.string.error_field_required));
                focusView = et_debit_card_name_on_debit_card;
                cancel = true;
            } else if (TextUtils.isEmpty(debit_card_mother_name)) {
                et_debit_card_mother_name.setError(getString(R.string.error_field_required));
                focusView = et_debit_card_mother_name;
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
                    case R.id.spinner_debit_card_request:
                        debitcardRequest = parent.getSelectedItem().toString();
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
