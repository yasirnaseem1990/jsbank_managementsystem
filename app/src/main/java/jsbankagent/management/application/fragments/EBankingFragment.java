package jsbankagent.management.application.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jsbankagent.management.application.R;
import jsbankagent.management.application.asynctasks.NewAccountAsyncTask;
import jsbankagent.management.application.databases.AddAgent;
import jsbankagent.management.application.model.ApplicationPersonalInformation;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;
import jsbankagent.management.application.utils.WebServiceConstants;

import static jsbankagent.management.application.HomeActivity.drawer;


public class EBankingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    /* private OnFragmentInteractionListener mListener;*/
    View ebankingFragment;
    Button btn_next_step8;
    ImageView iv_menu;
    Spinner spinnersmsAlerts, spinnerinternetBanking, spinnermobileBanking, spinnereStatement, spinnerFrequency;
    String smsAlerts, internetBanking, mobileBanking, eStatement, Frequency;
    public List<NameValuePair> mParams;
    private JSONObject jsonObject = null;

    public EBankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ebankingFragment = inflater.inflate(R.layout.fragment_ebanking, container, false);

        spinnersmsAlerts = ebankingFragment.findViewById(R.id.spinner_e_banking_sms_alert);
        spinnerinternetBanking = ebankingFragment.findViewById(R.id.spinner_e_banking_internet_banking);
        spinnermobileBanking = ebankingFragment.findViewById(R.id.spinner_e_banking_mobile_banking);
        spinnereStatement = ebankingFragment.findViewById(R.id.spinner_e_banking_e_statement);
        spinnerFrequency = ebankingFragment.findViewById(R.id.spinner_e_banking_frequency);
        btn_next_step8 = ebankingFragment.findViewById(R.id.btn_next_step_8);
        iv_menu = ebankingFragment.findViewById(R.id.imageviewMenu);
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

        btn_next_step8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!checkFields()) {

                        AppConstants.registrationObject.put("e_banking_sms_alerts", smsAlerts);
                        AppConstants.registrationObject.put("e_banking_internet_banking", internetBanking);
                        AppConstants.registrationObject.put("e_banking_mobile_banking", mobileBanking);
                        AppConstants.registrationObject.put("e_banking_e_statement", eStatement);
                        AppConstants.registrationObject.put("e_banking_frequency", Frequency);


                        DataHandler.updatePreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION, AppConstants.registrationObject.toString());


                        //TODO Retreaving the New Account Object values
                        String insertNewAccount = DataHandler.getStringPreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION);
                        Log.e("jsonObjNewAccount :", insertNewAccount);
                        if (insertNewAccount != null && !insertNewAccount.isEmpty()) {

                            jsonObject = new JSONObject(insertNewAccount);
                        //TODO Create Database class object and open database objece for insert the values
                        AddAgent addNewAccount = new AddAgent(getActivity());
                        addNewAccount.open();

                        //TODO Create a Object of Model Class for set all the values in model
                        ApplicationPersonalInformation information = new ApplicationPersonalInformation();

                        information.setCourtesyTitle(jsonObject.getString("personal_info_courtesy_title"));
                        information.setApplicantName(jsonObject.getString("personal_info_name"));
                        information.setCnicNo(jsonObject.getString("personal_info_cnic"));
                        information.setExpirecnicNo(jsonObject.getString("personal_info_expiry_cnic"));
                        information.setDob(jsonObject.getString("personal_info_dob"));
                        information.setApplicantGender(jsonObject.getString("personal_info_gender"));
                        information.setApplicantNationality(jsonObject.getString("personal_info_nationality"));
                        information.setApplicantNTN(jsonObject.getString("personal_info_ntn_number"));
                        information.setPlaceofBirth(jsonObject.getString("personal_info_placeofbirth"));
                        information.setMaritalStatus(jsonObject.getString("personal_info_marital_status"));
                        information.setApplicantQualification(jsonObject.getString("personal_info_qualification"));
                        information.setApplicantProfession(jsonObject.getString("personal_info_profession"));
                        information.setEmployerbuisnessName(jsonObject.getString("personal_info_employee_business"));
                        information.setNatureofBusiness(jsonObject.getString("personal_info_nature_business"));
                        information.setApplicantDesignation(jsonObject.getString("personal_info_designation"));
                        information.setApplicantmailingAddress(jsonObject.getString("personal_info_mailainAddress"));
                        information.setApplicantfatherName(jsonObject.getString("personal_info_father_name"));
                        information.setApplicantCitizenship(jsonObject.getString("personal_info_uscitizen"));
                        information.setExpirydateofVisa(jsonObject.getString("personal_info_visa_expiry_date"));
                        information.setResidentialAddress(jsonObject.getString("personal_info_residential_address"));
                        information.setResidentialcontactNumber(jsonObject.getString("personal_info_residential_contact_no"));
                        information.setCellNumber(jsonObject.getString("personal_info_contact_no"));
                        information.setOfficebuisnessAddress(jsonObject.getString("personal_info_office_address"));
                        information.setOfficecontactNumber(jsonObject.getString("personal_info_office_number"));
                        information.setTelenorgoldenNumber(jsonObject.getString("personal_info_telenor_golden_no"));
                        information.setApplicantEmail(jsonObject.getString("personal_info_email"));
                        information.setApplicantaccountTitle(jsonObject.getString("account_info_name"));
                        information.setApplicantaccountcurrentType(jsonObject.getString("account_info_type_current_account"));
                        information.setApplicantaccountType(jsonObject.getString("account_info_typ_account"));
                        information.setApplicantCurrency(jsonObject.getString("account_info_currency"));
                        information.setApplicantcountryresidentialStatus(jsonObject.getString("account_info_country_residential_status"));
                        information.setApplicantoperatingInstruction(jsonObject.getString("account_info_operating_instructions"));
                        information.setApplicantchequebookRequired(jsonObject.getString("account_info_chequebook_required"));
                        information.setApplicantnoofchequebokRequired(jsonObject.getString("account_info_no_chequebook_required"));
                        information.setApplicantzakatApplicable(jsonObject.getString("account_info_zakat_applicable"));
                        information.setJointapplicantName(jsonObject.getString("personal_info_joint_name"));
                        information.setJointapplicantCnic(jsonObject.getString("personal_info_nara_joint_number"));
                        information.setJointapplicantexpireCnic(jsonObject.getString("personal_info_nara_expiry_joint_number"));
                        information.setJointapplicantDob(jsonObject.getString("personal_info_joint_dob"));
                        information.setJointapplicantGender(jsonObject.getString("personal_info_joint_gender"));
                        information.setJointapplicantexpirydateofVisa(jsonObject.getString("personal_info_joint_expiry_date_visa"));
                        information.setJointapplicantfatherName(jsonObject.getString("personal_info_joint_father_name"));
                        information.setJointapplicantucCitizen(jsonObject.getString("personal_info_joint_us_citizen"));
                        information.setApplicantNationality(jsonObject.getString("personal_info_joint_nationality"));
                        information.setJointapplicantntnNumber(jsonObject.getString("personal_info_joint_ntn_number"));
                        information.setJointapplicantplaceofBirth(jsonObject.getString("personal_info_joint_place_of_birth"));
                        information.setJointapplicantmaritalStatus(jsonObject.getString("personal_info_joint_marital_status"));
                        information.setJointapplicantQualification(jsonObject.getString("personal_info_joint_qualification"));
                        information.setJointapplicantProfession(jsonObject.getString("personal_info_joint_professtion"));
                        information.setJointapplicantemoployerName(jsonObject.getString("personal_info_joint_employer_name"));
                        information.setJointapplicantnatureofBusiness(jsonObject.getString("personal_info_joint_nature_of_business"));
                        information.setJointapplicantDesignation(jsonObject.getString("personal_info_joint_designation"));
                        information.setJointapplicantresidentialAddress(jsonObject.getString("personal_info_joint_residential_area"));
                        information.setJointapplicantbusinessAddress(jsonObject.getString("personal_info_joint_business_address"));
                        information.setJointapplicantofficecontactNumber(jsonObject.getString("personal_info_joint_office_contact_number"));
                        information.setJointapplicantresidentialcontactNumber(jsonObject.getString("personal_info_joint_residential_contact_number"));
                        information.setJointapplicantcellNumber(jsonObject.getString("personal_info_joint_cell_number"));
                        information.setApplicantnokcourtesyTitle(jsonObject.getString("next_kin_courtesy_title"));
                        information.setApplicantnokName(jsonObject.getString("next_kin_name"));
                        information.setApplicantnokRelationship(jsonObject.getString("next_kin_relationship"));
                        information.setApplicantnokCnic(jsonObject.getString("next_kin_nara_number"));
                        information.setApplicantnokmailingAddress(jsonObject.getString("next_kin_mailing_address"));
                        information.setApplicantnokcontactNumber(jsonObject.getString("next_kin_contact_number"));
                        information.setApplicantnokcellNumber(jsonObject.getString("next_kin_cell_number"));
                        information.setApplicantbaibusinessName(jsonObject.getString("business_account_info_name"));
                        information.setApplicantbainatureofBuisness(jsonObject.getString("business_account_info_nature_of_business"));
                        information.setApplicantbairegistrationNumber(jsonObject.getString("business_account_info_registration_number"));
                        information.setApplicantbaintnNumber(jsonObject.getString("business_account_info_ntn_number"));
                        information.setApplicantbaidateofRegistration(jsonObject.getString("business_account_date_of_registration"));
                        information.setApplicantbaiplaceofRegistration(jsonObject.getString("business_account_info_place_of_registration"));
                        information.setApplicantbaiofficeAddress(jsonObject.getString("business_account_info_office_address"));
                        information.setApplicantbaicontactNumber(jsonObject.getString("business_account_info_contact_number"));
                        information.setApplicantbaifaxNumber(jsonObject.getString("business_account_info_fax_number"));
                        information.setApplicantdcrequestfordepitCard(jsonObject.getString("debit_card_debit_card_request"));
                        information.setApplicantdcnameofdebitCard(jsonObject.getString("debit_card_name_on_debit_card"));
                        information.setApplicantdcmaidenName(jsonObject.getString("debit_card_mother_name"));
                        information.setApplicantscrequestsupplementaryCard(jsonObject.getString("supplementary_card_request"));
                        information.setApplicantscfullnameofplaceHolder(jsonObject.getString("personal_info_name"));
                        information.setApplicantscDob(jsonObject.getString("supplementary_card_dobs"));
                        information.setApplicantscRelationship(jsonObject.getString("supplementary_card_relationship"));
                        information.setApplicantscCnic(jsonObject.getString("supplementary_card_cnic_number"));
                        information.setApplicantexpiryCnic(jsonObject.getString("supplementary_card_visa_expiry_date"));
                        information.setApplicantscmaidenName(jsonObject.getString("supplementary_card_mother_name"));
                        information.setApplicantscnameofsupplementaryCard(jsonObject.getString("supplementary_card_name_on_supplementary_card"));
                        information.setEbankingsmsAlerts(jsonObject.getString("e_banking_sms_alerts"));
                        information.setEbankinginternetBanking(jsonObject.getString("e_banking_internet_banking"));
                        information.setEbankingmobileBanking(jsonObject.getString("e_banking_mobile_banking"));
                        information.setEbankingeStatement(jsonObject.getString("e_banking_e_statement"));
                        information.setEbankingFrequency(jsonObject.getString("e_banking_frequency"));

                        //TODO insert the value
                        addNewAccount.insertNewAccount(information);
                    }

                        //TODO Retreaving the New Account Object values
                        String jsonObjNewAccount = DataHandler.getStringPreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION);
                        Log.e("jsonObjNewAccount :", jsonObjNewAccount);
                        if (jsonObjNewAccount != null && !jsonObjNewAccount.isEmpty()) {
                            try {
                                if (isNetworkAvailable()) {
                                    jsonObject = new JSONObject(jsonObjNewAccount);

                                    mParams = new ArrayList<NameValuePair>();
                                    mParams.add(new BasicNameValuePair("courtesyTitle", jsonObject.getString("personal_info_courtesy_title")));
                                    mParams.add(new BasicNameValuePair("applicantName", jsonObject.getString("personal_info_name")));
                                    mParams.add(new BasicNameValuePair("cnicNo", jsonObject.getString("personal_info_cnic")));
                                    mParams.add(new BasicNameValuePair("expirecnicNo", jsonObject.getString("personal_info_expiry_cnic")));
                                    mParams.add(new BasicNameValuePair("dob", jsonObject.getString("personal_info_dob")));
                                    mParams.add(new BasicNameValuePair("applicantGender", jsonObject.getString("personal_info_gender")));
                                    mParams.add(new BasicNameValuePair("applicantNationality", jsonObject.getString("personal_info_nationality")));
                                    mParams.add(new BasicNameValuePair("applicantNTN", jsonObject.getString("personal_info_ntn_number")));
                                    mParams.add(new BasicNameValuePair("placeofBirth", jsonObject.getString("personal_info_placeofbirth")));
                                    mParams.add(new BasicNameValuePair("maritalStatus", jsonObject.getString("personal_info_marital_status")));
                                    mParams.add(new BasicNameValuePair("applicantQualification", jsonObject.getString("personal_info_qualification")));
                                    mParams.add(new BasicNameValuePair("applicantProfession", jsonObject.getString("personal_info_profession")));
                                    mParams.add(new BasicNameValuePair("employerbuisnessName", jsonObject.getString("personal_info_employee_business")));
                                    mParams.add(new BasicNameValuePair("natureofBusiness", jsonObject.getString("personal_info_nature_business")));
                                    mParams.add(new BasicNameValuePair("applicantDesignation", jsonObject.getString("personal_info_designation")));
                                    mParams.add(new BasicNameValuePair("applicantmailingAddress", jsonObject.getString("personal_info_mailainAddress")));
                                    mParams.add(new BasicNameValuePair("applicantfatherName", jsonObject.getString("personal_info_father_name")));
                                    mParams.add(new BasicNameValuePair("applicantCitizenship", jsonObject.getString("personal_info_uscitizen")));
                                    mParams.add(new BasicNameValuePair("expirydateofVisa", jsonObject.getString("personal_info_visa_expiry_date")));
                                    mParams.add(new BasicNameValuePair("residentialAddress", jsonObject.getString("personal_info_residential_address")));
                                    mParams.add(new BasicNameValuePair("residentialcontactNumber", jsonObject.getString("personal_info_residential_contact_no")));
                                    mParams.add(new BasicNameValuePair("cellNumber", jsonObject.getString("personal_info_contact_no")));
                                    mParams.add(new BasicNameValuePair("officebuisnessAddress", jsonObject.getString("personal_info_office_address")));
                                    mParams.add(new BasicNameValuePair("officecontactNumber", jsonObject.getString("personal_info_office_number")));
                                    mParams.add(new BasicNameValuePair("telenorgoldenNumber", jsonObject.getString("personal_info_telenor_golden_no")));
                                    mParams.add(new BasicNameValuePair("applicantEmail", jsonObject.getString("personal_info_email")));


                                    mParams.add(new BasicNameValuePair("applicantaccountTitle", jsonObject.getString("account_info_name")));
                                    mParams.add(new BasicNameValuePair("applicantaccountcurrentType", jsonObject.getString("account_info_type_current_account")));
                                    mParams.add(new BasicNameValuePair("applicantaccountType", jsonObject.getString("account_info_typ_account")));
                                    mParams.add(new BasicNameValuePair("applicantCurrency", jsonObject.getString("account_info_currency")));
                                    mParams.add(new BasicNameValuePair("applicantcountryresidentialStatus", jsonObject.getString("account_info_country_residential_status")));
                                    mParams.add(new BasicNameValuePair("applicantoperatingInstruction", jsonObject.getString("account_info_operating_instructions")));
                                    mParams.add(new BasicNameValuePair("applicantchequebookRequired", jsonObject.getString("account_info_chequebook_required")));
                                    mParams.add(new BasicNameValuePair("applicantnoofchequebokRequired", jsonObject.getString("account_info_no_chequebook_required")));
                                    mParams.add(new BasicNameValuePair("applicantzakatApplicable", jsonObject.getString("account_info_zakat_applicable")));


                                    mParams.add(new BasicNameValuePair("jointapplicantName", jsonObject.getString("personal_info_joint_name")));
                                    mParams.add(new BasicNameValuePair("jointapplicantCnic", jsonObject.getString("personal_info_nara_joint_number")));
                                    mParams.add(new BasicNameValuePair("jointapplicantexpireCnic", jsonObject.getString("personal_info_nara_expiry_joint_number")));
                                    mParams.add(new BasicNameValuePair("jointapplicantDob", jsonObject.getString("personal_info_joint_dob")));
                                    mParams.add(new BasicNameValuePair("jointapplicantGender", jsonObject.getString("personal_info_joint_gender")));
                                    mParams.add(new BasicNameValuePair("jointapplicantexpirydateofVisa", jsonObject.getString("personal_info_joint_expiry_date_visa")));
                                    mParams.add(new BasicNameValuePair("jointapplicantfatherName", jsonObject.getString("personal_info_joint_father_name")));
                                    mParams.add(new BasicNameValuePair("jointapplicantucCitizen", jsonObject.getString("personal_info_joint_us_citizen")));
                                    mParams.add(new BasicNameValuePair("jointapplicantNationality", jsonObject.getString("personal_info_joint_nationality")));
                                    mParams.add(new BasicNameValuePair("jointapplicantntnNumber", jsonObject.getString("personal_info_joint_ntn_number")));
                                    mParams.add(new BasicNameValuePair("jointapplicantplaceofBirth", jsonObject.getString("personal_info_joint_place_of_birth")));
                                    mParams.add(new BasicNameValuePair("jointapplicantmaritalStatus", jsonObject.getString("personal_info_joint_marital_status")));
                                    mParams.add(new BasicNameValuePair("jointapplicantQualification", jsonObject.getString("personal_info_joint_qualification")));
                                    mParams.add(new BasicNameValuePair("jointapplicantProfession", jsonObject.getString("personal_info_joint_professtion")));
                                    mParams.add(new BasicNameValuePair("jointapplicantemoployerName", jsonObject.getString("personal_info_joint_employer_name")));
                                    mParams.add(new BasicNameValuePair("jointapplicantnatureofBusiness", jsonObject.getString("personal_info_joint_nature_of_business")));
                                    mParams.add(new BasicNameValuePair("jointapplicantDesignation", jsonObject.getString("personal_info_joint_designation")));
                                    mParams.add(new BasicNameValuePair("jointapplicantresidentialAddress", jsonObject.getString("personal_info_joint_residential_area")));
                                    mParams.add(new BasicNameValuePair("jointapplicantbusinessAddress", jsonObject.getString("personal_info_joint_business_address")));
                                    mParams.add(new BasicNameValuePair("jointapplicantofficecontactNumber", jsonObject.getString("personal_info_joint_office_contact_number")));
                                    mParams.add(new BasicNameValuePair("jointapplicantresidentialcontactNumber", jsonObject.getString("personal_info_joint_residential_contact_number")));
                                    mParams.add(new BasicNameValuePair("jointapplicantcellNumber", jsonObject.getString("personal_info_joint_cell_number")));


                                    mParams.add(new BasicNameValuePair("applicantnokcourtesyTitle", jsonObject.getString("next_kin_courtesy_title")));
                                    mParams.add(new BasicNameValuePair("applicantnokName", jsonObject.getString("next_kin_name")));
                                    mParams.add(new BasicNameValuePair("applicantnokRelationship", jsonObject.getString("next_kin_relationship")));
                                    mParams.add(new BasicNameValuePair("applicantnokCnic", jsonObject.getString("next_kin_nara_number")));
                                    mParams.add(new BasicNameValuePair("applicantnokmailingAddress", jsonObject.getString("next_kin_mailing_address")));
                                    mParams.add(new BasicNameValuePair("applicantnokcontactNumber", jsonObject.getString("next_kin_contact_number")));
                                    mParams.add(new BasicNameValuePair("applicantnokcellNumber", jsonObject.getString("next_kin_cell_number")));


                                    mParams.add(new BasicNameValuePair("applicantbaibusinessName", jsonObject.getString("business_account_info_name")));
                                    mParams.add(new BasicNameValuePair("applicantbainatureofBuisness", jsonObject.getString("business_account_info_nature_of_business")));
                                    mParams.add(new BasicNameValuePair("applicantbairegistrationNumber", jsonObject.getString("business_account_info_registration_number")));
                                    mParams.add(new BasicNameValuePair("applicantbaintnNumber", jsonObject.getString("business_account_info_ntn_number")));
                                    mParams.add(new BasicNameValuePair("applicantbaidateofRegistration", jsonObject.getString("business_account_date_of_registration")));
                                    mParams.add(new BasicNameValuePair("applicantbaiplaceofRegistration", jsonObject.getString("business_account_info_place_of_registration")));
                                    mParams.add(new BasicNameValuePair("applicantbaiofficeAddress", jsonObject.getString("business_account_info_office_address")));
                                    mParams.add(new BasicNameValuePair("applicantbaicontactNumber", jsonObject.getString("business_account_info_contact_number")));
                                    mParams.add(new BasicNameValuePair("applicantbaifaxNumber", jsonObject.getString("business_account_info_fax_number")));


                                    mParams.add(new BasicNameValuePair("applicantdcrequestfordepitCard", jsonObject.getString("debit_card_debit_card_request")));
                                    mParams.add(new BasicNameValuePair("applicantdcnameofdebitCard", jsonObject.getString("debit_card_name_on_debit_card")));
                                    mParams.add(new BasicNameValuePair("applicantdcmaidenName", jsonObject.getString("debit_card_mother_name")));


                                    mParams.add(new BasicNameValuePair("applicantscrequestsupplementaryCard", jsonObject.getString("supplementary_card_request")));
                                    mParams.add(new BasicNameValuePair("applicantscfullnameofplaceHolder", jsonObject.getString("personal_info_name")));
                                    mParams.add(new BasicNameValuePair("applicantscDob", jsonObject.getString("supplementary_card_dobs")));
                                    mParams.add(new BasicNameValuePair("applicantscRelationship", jsonObject.getString("supplementary_card_relationship")));
                                    mParams.add(new BasicNameValuePair("applicantscCnic", jsonObject.getString("supplementary_card_cnic_number")));
                                    mParams.add(new BasicNameValuePair("applicantexpiryCnic", jsonObject.getString("supplementary_card_visa_expiry_date")));
                                    mParams.add(new BasicNameValuePair("applicantscmaidenName", jsonObject.getString("supplementary_card_mother_name")));
                                    mParams.add(new BasicNameValuePair("applicantscnameofsupplementaryCard", jsonObject.getString("supplementary_card_name_on_supplementary_card")));


                                    mParams.add(new BasicNameValuePair("ebankingsmsAlerts", jsonObject.getString("e_banking_sms_alerts")));
                                    mParams.add(new BasicNameValuePair("ebankinginternetBanking", jsonObject.getString("e_banking_internet_banking")));
                                    mParams.add(new BasicNameValuePair("ebankingmobileBanking", jsonObject.getString("e_banking_mobile_banking")));
                                    mParams.add(new BasicNameValuePair("ebankingeStatement", jsonObject.getString("e_banking_e_statement")));
                                    mParams.add(new BasicNameValuePair("ebankingFrequency", jsonObject.getString("e_banking_frequency")));

                                    Log.e("mParams",":"+mParams);
                                    NewAccountAsyncTask accountAsyncTask = new NewAccountAsyncTask(getActivity(), WebServiceConstants.END_POINT_NEW_REGISTRATION, mParams);
                                    accountAsyncTask.execute();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        Toast.makeText(getActivity(), "Form Saved", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            //SMS Alerts Spinner
            ArrayAdapter<CharSequence> smsalertAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listsmsAlerts, android.R.layout.simple_spinner_item);
            smsalertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnersmsAlerts.setAdapter(smsalertAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //Internet Banking Spinner
            ArrayAdapter<CharSequence> internetbankingAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listinternetBanking, android.R.layout.simple_spinner_item);
            internetbankingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerinternetBanking.setAdapter(internetbankingAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //Mobile Banking Spinner
            ArrayAdapter<CharSequence> mobilebankingAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listmobileBanking, android.R.layout.simple_spinner_item);
            mobilebankingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnermobileBanking.setAdapter(mobilebankingAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //E- Statement Spinner
            ArrayAdapter<CharSequence> estatementAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listestatement, android.R.layout.simple_spinner_item);
            estatementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnereStatement.setAdapter(estatementAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //Frequency Spinner
            ArrayAdapter<CharSequence> frequencyAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listfrequency, android.R.layout.simple_spinner_item);
            frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerFrequency.setAdapter(frequencyAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        spinnersmsAlerts.setOnItemSelectedListener(this);
        spinnerinternetBanking.setOnItemSelectedListener(this);
        spinnermobileBanking.setOnItemSelectedListener(this);
        spinnereStatement.setOnItemSelectedListener(this);
        spinnerFrequency.setOnItemSelectedListener(this);

        return ebankingFragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
      /*  if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        /* mListener = null;*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView) view;
        try {
            if (position == 0) {
                tv.setTextColor(Color.GRAY);
            } else {
                tv.setTextColor(Color.BLACK);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            if (position > 0) {
                switch (parent.getId()) {
                    case R.id.spinner_e_banking_sms_alert:
                        smsAlerts = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_e_banking_internet_banking:
                        internetBanking = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_e_banking_mobile_banking:
                        mobileBanking = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_e_banking_e_statement:
                        eStatement = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_e_banking_frequency:
                        Frequency = parent.getSelectedItem().toString();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Todo Check Form Fields
    public boolean checkFields() {

        boolean cancel = false;
        View focusView = null;

        try {
            if (TextUtils.isEmpty(smsAlerts)) {
                Toast toast = Toast.makeText(getActivity(), "Please select SMS alert", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnersmsAlerts;
                cancel = true;
            } else if (TextUtils.isEmpty(internetBanking)) {
                Toast toast = Toast.makeText(getActivity(), "Please select internet banking", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerinternetBanking;
                cancel = true;
            } else if (TextUtils.isEmpty(mobileBanking)) {
                Toast toast = Toast.makeText(getActivity(), "Please select mobile internet banking", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnermobileBanking;
                cancel = true;
            } else if (TextUtils.isEmpty(eStatement)) {
                Toast toast = Toast.makeText(getActivity(), "Please select E-statement", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnereStatement;
                cancel = true;
            } else if (TextUtils.isEmpty(Frequency)) {
                Toast toast = Toast.makeText(getActivity(), "Please select frequency", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerFrequency;
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

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
