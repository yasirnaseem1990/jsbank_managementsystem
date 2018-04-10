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
import android.widget.RelativeLayout;
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
public class AccountInformationFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    View accountinformationFragment;
    Spinner spinnertypeofcurrentAccount,spinnertypeofAccount,spinnerCurrency,spinnercountryresidentialStatus,
            spinneroperatingInstructions,spinnerchequebookRequired,spinneryeschequebookRequired,spinnerzakatApplicable;
    ImageView iv_menu;
    Fragment fragment;
    TextView tv_yes_cheque_book_required;
    RelativeLayout yes_cheque_book_required_layout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button btn_next_step2;
        private String typeofcurrentAccount,typeofAccount,currency,countryresidentialStatus,operatingInstructions,chequebookRequired,yeschequebookRequired,
            zakatApplicable;
    EditText et_accountinfo_accountTitle;
    private String accountinfo_accountTitle;
    String getPreAccountInfo = "";
    JSONObject jsonObject = null;
    private String jointAccountNeed = "";

    public AccountInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        accountinformationFragment = inflater.inflate(R.layout.fragment_account_information, container, false);

        //intiliaze the spinner
        spinnertypeofcurrentAccount = accountinformationFragment.findViewById(R.id.spinner_type_of_current_account);
        spinnertypeofAccount = accountinformationFragment.findViewById(R.id.spinner_type_of_account);
        spinnerCurrency = accountinformationFragment.findViewById(R.id.spinner_currency);
        spinnercountryresidentialStatus = accountinformationFragment.findViewById(R.id.spinner_country_residental_status);
        spinneroperatingInstructions = accountinformationFragment.findViewById(R.id.spinner_operating_instructions);
        spinnerchequebookRequired = accountinformationFragment.findViewById(R.id.spinner_cheque_book_required);
        spinneryeschequebookRequired = accountinformationFragment.findViewById(R.id.spinner_yes_cheque_book_required);
        spinnerzakatApplicable = accountinformationFragment.findViewById(R.id.spinner_zakat_applicable);
        btn_next_step2 = accountinformationFragment.findViewById(R.id.btn_next_step_2);
        tv_yes_cheque_book_required = accountinformationFragment.findViewById(R.id.tv_yes_cheque_book_required);
        yes_cheque_book_required_layout = accountinformationFragment.findViewById(R.id.yes_cheque_book_required_layout);
        et_accountinfo_accountTitle = accountinformationFragment.findViewById(R.id.et_accountTitle);
        iv_menu = accountinformationFragment.findViewById(R.id.imageviewMenu);
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
        getPreAccountInfo = DataHandler.getStringPreferences(AppConstants.PREFERENCE_PRE_ACCOUNT_INFO);
        Log.e("getPreAccountInfo",getPreAccountInfo);
        try {
            jsonObject = new JSONObject(getPreAccountInfo);
            if (jsonObject !=null){
                jointAccountNeed = jsonObject.getString("pre_joint_account_need");
                Log.e("jointAccountNeed",jointAccountNeed);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        btn_next_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!checkFields()) {

                            AppConstants.registrationObject.put("account_info_name", et_accountinfo_accountTitle.getText().toString().trim());
                            AppConstants.registrationObject.put("account_info_type_current_account", typeofcurrentAccount);
                            AppConstants.registrationObject.put("account_info_typ_account", typeofAccount);
                            AppConstants.registrationObject.put("account_info_currency", currency);
                            AppConstants.registrationObject.put("account_info_country_residential_status", countryresidentialStatus);
                            AppConstants.registrationObject.put("account_info_operating_instructions", operatingInstructions);
                            AppConstants.registrationObject.put("account_info_chequebook_required", chequebookRequired);
                            AppConstants.registrationObject.put("account_info_no_chequebook_required", yeschequebookRequired);
                            AppConstants.registrationObject.put("account_info_zakat_applicable", zakatApplicable);


                        //TODO Check jsonObject Is null or not with PreAccount Information
                        if (jointAccountNeed.equalsIgnoreCase("No")){

                            AppConstants.registrationObject.put("personal_info_joint_name", "N/A");
                            AppConstants.registrationObject.put("personal_info_nara_joint_number", "0");
                            AppConstants.registrationObject.put("personal_info_nara_expiry_joint_number", "0");
                            AppConstants.registrationObject.put("personal_info_joint_dob", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_gender", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_expiry_date_visa", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_father_name", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_us_citizen", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_nationality", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_ntn_number", "0");
                            AppConstants.registrationObject.put("personal_info_joint_place_of_birth", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_marital_status", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_qualification", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_professtion", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_employer_name", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_nature_of_business", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_designation", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_residential_area", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_business_address", "N/A");
                            AppConstants.registrationObject.put("personal_info_joint_office_contact_number", "0");
                            AppConstants.registrationObject.put("personal_info_joint_residential_contact_number", "0");
                            AppConstants.registrationObject.put("personal_info_joint_cell_number", "0");

                            /*Toast.makeText(getActivity(), "Sorry I don't need Joint Account", Toast.LENGTH_SHORT).show();*/
                        }

                            if (jointAccountNeed.equalsIgnoreCase("No")){
                                fragment = new NextOfKinFragment();
                                fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frame_container, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                            else{
                                fragment = new PersonalInformationJointFragment();
                                fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frame_container, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                            DataHandler.updatePreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION,AppConstants.registrationObject.toString());
                    }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }
        });

        //Type of current account Spinner
        ArrayAdapter<CharSequence> typeofcuurentaccountAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listtypeofcurrentAccount,android.R.layout.simple_spinner_item);
        typeofcuurentaccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertypeofcurrentAccount.setAdapter(typeofcuurentaccountAdapter);

        //Type of account Spinner
        ArrayAdapter<CharSequence> typeofaccountAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listtypeofAccount,android.R.layout.simple_spinner_item);
        typeofaccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertypeofAccount.setAdapter(typeofaccountAdapter);

        //Currency Spinner
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listCurrency,android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(currencyAdapter);

        //Country residential Spinner
        ArrayAdapter<CharSequence> countryresidentialAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listcountryresidentialStatus,android.R.layout.simple_spinner_item);
        countryresidentialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercountryresidentialStatus.setAdapter(countryresidentialAdapter);

        //Operating instruction Spinner
        ArrayAdapter<CharSequence> operatinginstructionAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listoperationInstruction,android.R.layout.simple_spinner_item);
        operatinginstructionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneroperatingInstructions.setAdapter(operatinginstructionAdapter);

        //Cheque book required Spinner
        ArrayAdapter<CharSequence> chequebookrequiredAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listchequebookRequired,android.R.layout.simple_spinner_item);
        chequebookrequiredAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerchequebookRequired.setAdapter(chequebookrequiredAdapter);

        //yes Cheque book required Spinner
        ArrayAdapter<CharSequence> yeschequebookRequiredAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listyestchequebookRequired,android.R.layout.simple_spinner_item);
        yeschequebookRequiredAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryeschequebookRequired.setAdapter(yeschequebookRequiredAdapter);

        //Zakat Applicable Spinner
        ArrayAdapter<CharSequence> zakatapplicableAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listzakatApplicable,android.R.layout.simple_spinner_item);
        zakatapplicableAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerzakatApplicable.setAdapter(zakatapplicableAdapter);

        spinnertypeofcurrentAccount.setOnItemSelectedListener(this);
        spinnertypeofAccount.setOnItemSelectedListener(this);
        spinnerCurrency.setOnItemSelectedListener(this);
        spinnercountryresidentialStatus.setOnItemSelectedListener(this);
        spinneroperatingInstructions.setOnItemSelectedListener(this);
        spinnerchequebookRequired.setOnItemSelectedListener(this);
        spinneryeschequebookRequired.setOnItemSelectedListener(this);
        spinnerzakatApplicable.setOnItemSelectedListener(this);

        return accountinformationFragment;
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
                    case R.id.spinner_type_of_current_account:
                        typeofcurrentAccount = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_type_of_account:
                        typeofAccount = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_currency:
                        currency = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_country_residental_status:
                        countryresidentialStatus = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_operating_instructions:
                        operatingInstructions = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_cheque_book_required:
                        chequebookRequired = parent.getSelectedItem().toString();
                        if (chequebookRequired.equalsIgnoreCase("NO")){
                            yes_cheque_book_required_layout.setVisibility(View.GONE);
                            tv_yes_cheque_book_required.setVisibility(View.GONE);
                            chequebookRequired = "N/A";
                        }
                        if (chequebookRequired.equalsIgnoreCase("YES")){
                            yes_cheque_book_required_layout.setVisibility(View.VISIBLE);
                            tv_yes_cheque_book_required.setVisibility(View.VISIBLE);
                            chequebookRequired = parent.getSelectedItem().toString();
                        }
                        break;
                    case R.id.spinner_yes_cheque_book_required:
                        yeschequebookRequired = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_zakat_applicable:
                        zakatApplicable = parent.getSelectedItem().toString();
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


    public boolean checkFields() {

        et_accountinfo_accountTitle.setError(null);


        boolean cancel = false;
        View focusView = null;

        accountinfo_accountTitle = et_accountinfo_accountTitle.getText().toString().trim();

        try {
            if (TextUtils.isEmpty(accountinfo_accountTitle)) {

                et_accountinfo_accountTitle.setError(getString(R.string.error_field_required));
                focusView = et_accountinfo_accountTitle;
                cancel = true;
            } else if (TextUtils.isEmpty(typeofcurrentAccount)) {
                Toast toast =Toast.makeText(getActivity(), "Please select type of current account", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnertypeofcurrentAccount;
                cancel = true;
            }else if (TextUtils.isEmpty(typeofAccount)) {
               Toast toast =  Toast.makeText(getActivity(), "Please select type of account", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnertypeofAccount;
                cancel = true;
            }else if (TextUtils.isEmpty(currency)) {
                Toast toast =Toast.makeText(getActivity(), "Please select currency", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerCurrency;
                cancel = true;
            }else if (TextUtils.isEmpty(countryresidentialStatus)) {
                Toast toast =Toast.makeText(getActivity(), "Please select country residential status", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnercountryresidentialStatus;
                cancel = true;
            }else if (TextUtils.isEmpty(operatingInstructions)) {
                Toast toast =Toast.makeText(getActivity(), "Please select operating instruction", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinneroperatingInstructions;
                cancel = true;
            }else if (TextUtils.isEmpty(chequebookRequired)) {
                Toast toast =Toast.makeText(getActivity(), "Please select cheque book required", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerchequebookRequired;
                cancel = true;
            }else if (TextUtils.isEmpty(yeschequebookRequired)) {
                Toast toast =Toast.makeText(getActivity(), "Please select cheque book required", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinneryeschequebookRequired;
                cancel = true;
            }else if (TextUtils.isEmpty(zakatApplicable)) {
                Toast toast = Toast.makeText(getActivity(), "Please select zakat applicable", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerzakatApplicable;
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
