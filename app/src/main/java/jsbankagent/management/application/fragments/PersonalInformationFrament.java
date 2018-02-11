package jsbankagent.management.application.fragments;


import android.app.DatePickerDialog;
import android.graphics.Color;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.View.OnClickListener;
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
public class PersonalInformationFrament extends Fragment implements OnClickListener, AdapterView.OnItemSelectedListener {
    View personalinformatinoFragment;
    ImageView iv_menu;
    Button btnNext;
    Spinner spinnercourtesyTitle, spinnerGender, spinnermaritalStatus, spinnerQaulification, spinnerProfession,
            spinnermailingAddress, spinnerusCitizen;
    EditText et_personal_info_name, et_personal_info_cnic, et_personal_info_expiry_cnic, et_personal_info_dob, et_personal_info_nationality,
            et_personal_info_ntn_number, et_personal_info_place_birth, et_personal_info_employee_business, et_personal_info_nature_business,
            et_personal_info_designation, et_personal_info_father_husband_name, et_personal_info_visa_expiry_date, et_personal_info_residential_address,
            et_personal_info_residential_contact_no, et_personal_info_contact_no, et_personal_info_office_address, et_personal_info_office_number,
            et_personal_info_telenor_golden_no, et_personal_info_email;
    private String personal_info_name, personal_info_cnic, personal_info_expiry_cnic, personal_info_dob, personal_info_nationality, personal_info_ntn_number,
            personal_info_place_birth, personal_info_employee_business, personal_info_nature_business, personal_info_designation, personal_info_father_husband_name,
            personal_info_visa_expiry_date, personal_info_residential_address, personal_info_residential_contact_no, personal_info_office_address,
            personal_info_office_number, personal_info_telenor_golden_no, personal_info_email, personal_info_contact_no;
    private EditText dateofBirth, expirydateofVisa;
    private DatePickerDialog dateofbirthDatePickerDialog;
    private DatePickerDialog expirydateofVisaPickerDialog;
    public String expirydateofvisa;
    public String dateofbirth;
    private SimpleDateFormat dateFormatter;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String courtesyTitle, gender, maritalStatus, qualification, profession, mailainAddress, usCitizen;

    public PersonalInformationFrament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        personalinformatinoFragment = inflater.inflate(R.layout.fragment_personal_information_frament, container, false);
        spinnercourtesyTitle = personalinformatinoFragment.findViewById(R.id.spinner_courtesy_title);
        spinnerGender = personalinformatinoFragment.findViewById(R.id.spinner_gender);
        spinnermaritalStatus = personalinformatinoFragment.findViewById(R.id.spinner_maritalstatus);
        spinnerQaulification = personalinformatinoFragment.findViewById(R.id.spinner_qualification);
        spinnerProfession = personalinformatinoFragment.findViewById(R.id.spinner_profession);
        spinnermailingAddress = personalinformatinoFragment.findViewById(R.id.spinner_mailain_address);
        spinnerusCitizen = personalinformatinoFragment.findViewById(R.id.spinner_us_citizen);

        et_personal_info_name = (EditText) personalinformatinoFragment.findViewById(R.id.et_name);
        et_personal_info_cnic = (EditText) personalinformatinoFragment.findViewById(R.id.et_cnic_passport);
        et_personal_info_expiry_cnic = (EditText) personalinformatinoFragment.findViewById(R.id.et_expiry_cnic_passport);
        et_personal_info_dob = (EditText) personalinformatinoFragment.findViewById(R.id.et_dateofbirth);
        et_personal_info_nationality = (EditText) personalinformatinoFragment.findViewById(R.id.et_nationality);
        et_personal_info_ntn_number = (EditText) personalinformatinoFragment.findViewById(R.id.et_ntn_number);
        et_personal_info_place_birth = (EditText) personalinformatinoFragment.findViewById(R.id.et_placeofbirth);
        et_personal_info_employee_business = (EditText) personalinformatinoFragment.findViewById(R.id.et_employer_business);
        et_personal_info_nature_business = (EditText) personalinformatinoFragment.findViewById(R.id.et_nature_of_business);
        et_personal_info_designation = (EditText) personalinformatinoFragment.findViewById(R.id.et_designation);
        et_personal_info_father_husband_name = (EditText) personalinformatinoFragment.findViewById(R.id.et_father_husband_name);
        et_personal_info_visa_expiry_date = (EditText) personalinformatinoFragment.findViewById(R.id.et_expiry_date_visa);
        et_personal_info_residential_address = (EditText) personalinformatinoFragment.findViewById(R.id.et_residential_address);
        et_personal_info_residential_contact_no = (EditText) personalinformatinoFragment.findViewById(R.id.et_residential_contact_no);
        et_personal_info_contact_no = (EditText) personalinformatinoFragment.findViewById(R.id.et_cell_number);
        et_personal_info_office_address = (EditText) personalinformatinoFragment.findViewById(R.id.et_office_business_address);
        et_personal_info_office_number = (EditText) personalinformatinoFragment.findViewById(R.id.et_office_contact_number);
        et_personal_info_telenor_golden_no = (EditText) personalinformatinoFragment.findViewById(R.id.et_telenor_golden_number);
        et_personal_info_email = (EditText) personalinformatinoFragment.findViewById(R.id.et_email_address);


        AppConstants.registrationObject = new JSONObject();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        //Intiliaze the Button
        btnNext = (Button) personalinformatinoFragment.findViewById(R.id.btn_next);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.courtesyTitle, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercourtesyTitle.setAdapter(adapter);

        iv_menu = personalinformatinoFragment.findViewById(R.id.imageviewMenu);
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

        //Gender Spinner
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listGender, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);


        //Marital Status  Spinner
        ArrayAdapter<CharSequence> maritalstatusAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listmaritalStatus, android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermaritalStatus.setAdapter(maritalstatusAdapter);

        //Qualification  Spinner
        ArrayAdapter<CharSequence> qualificationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listQualification, android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQaulification.setAdapter(qualificationAdapter);

        //Profession  Spinner
        ArrayAdapter<CharSequence> professionAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listProfession, android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfession.setAdapter(professionAdapter);

        //Mailing Address  Spinner
        ArrayAdapter<CharSequence> designationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listmailingAddress, android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermailingAddress.setAdapter(designationAdapter);

        //Us Citizen   Spinner
        ArrayAdapter<CharSequence> uscitizenAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listusCitizen, android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerusCitizen.setAdapter(uscitizenAdapter);

        findViewsById();

        setDateTimeField();


        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!checkFields()) {
                        AppConstants.registrationObject.put("personal_info_name", et_personal_info_name.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_cnic", et_personal_info_cnic.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_expiry_cnic", et_personal_info_expiry_cnic.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_dob", et_personal_info_dob.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_nationality", et_personal_info_nationality.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_ntn_number", et_personal_info_ntn_number.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_placeofbirth", et_personal_info_place_birth.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_employee_business", et_personal_info_employee_business.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_nature_business", et_personal_info_nature_business.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_designation", et_personal_info_designation.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_father_name", et_personal_info_father_husband_name.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_visa_expiry_date", et_personal_info_visa_expiry_date.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_residential_address", et_personal_info_residential_address.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_residential_contact_no", et_personal_info_residential_contact_no.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_contact_no", et_personal_info_contact_no.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_office_address", et_personal_info_office_address.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_office_number", et_personal_info_office_number.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_telenor_golden_no", et_personal_info_telenor_golden_no.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_email", et_personal_info_email.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_courtesy_title", courtesyTitle);
                        AppConstants.registrationObject.put("personal_info_gender", gender);
                        AppConstants.registrationObject.put("personal_info_marital_status", maritalStatus);
                        AppConstants.registrationObject.put("personal_info_qualification", qualification);
                        AppConstants.registrationObject.put("personal_info_profession", profession);
                        AppConstants.registrationObject.put("personal_info_mailainAddress", mailainAddress);
                        AppConstants.registrationObject.put("personal_info_uscitizen", usCitizen);
                        fragment = new AccountInformationFragment();
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, fragment);
                        fragmentTransaction.commit();
                        DataHandler.updatePreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION,AppConstants.registrationObject.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        spinnercourtesyTitle.setOnItemSelectedListener(this);
        spinnerGender.setOnItemSelectedListener(this);
        spinnermaritalStatus.setOnItemSelectedListener(this);
        spinnerQaulification.setOnItemSelectedListener(this);
        spinnerProfession.setOnItemSelectedListener(this);
        spinnermailingAddress.setOnItemSelectedListener(this);
        spinnerusCitizen.setOnItemSelectedListener(this);
        return personalinformatinoFragment;


    }

    private void findViewsById() {
        dateofBirth = (EditText) personalinformatinoFragment.findViewById(R.id.et_dateofbirth);
        dateofBirth.setInputType(InputType.TYPE_NULL);
        dateofBirth.requestFocus();

        expirydateofVisa = (EditText) personalinformatinoFragment.findViewById(R.id.et_expiry_date_visa);
        expirydateofVisa.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        dateofBirth.setOnClickListener(this);
        expirydateofVisa.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        dateofbirthDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateofBirth.setText(dateFormatter.format(newDate.getTime()));
                dateofbirth = dateofBirth.getText().toString();
                Log.e("dateofbirth", dateofbirth);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        expirydateofVisaPickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                expirydateofVisa.setText(dateFormatter.format(newDate.getTime()));
                expirydateofvisa = expirydateofVisa.getText().toString();
                Log.e("expirydateofvisa", expirydateofvisa);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View v) {
        if (v == dateofBirth) {
            dateofbirthDatePickerDialog.show();
        } else if (v == expirydateofVisa) {

            expirydateofVisaPickerDialog.show();
        }

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

        try {
            if (position > 0) {
                switch (parent.getId()) {
                    case R.id.spinner_courtesy_title:
                        courtesyTitle = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_gender:
                        gender = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_maritalstatus:
                        maritalStatus = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_qualification:
                        qualification = parent.getSelectedItem().toString();
                    case R.id.spinner_profession:
                        profession = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_mailain_address:
                        mailainAddress = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_us_citizen:
                        usCitizen = parent.getSelectedItem().toString();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean checkFields() {

        et_personal_info_name.setError(null);
        et_personal_info_cnic.setError(null);
        et_personal_info_expiry_cnic.setError(null);
        et_personal_info_dob.setError(null);
        et_personal_info_nationality.setError(null);
        et_personal_info_ntn_number.setError(null);
        et_personal_info_place_birth.setError(null);
        et_personal_info_employee_business.setError(null);
        et_personal_info_nature_business.setError(null);
        et_personal_info_designation.setError(null);
        et_personal_info_father_husband_name.setError(null);
        et_personal_info_visa_expiry_date.setError(null);
        et_personal_info_residential_address.setError(null);
        et_personal_info_residential_contact_no.setError(null);
        et_personal_info_contact_no.setError(null);
        et_personal_info_office_address.setError(null);
        et_personal_info_office_number.setError(null);
        et_personal_info_telenor_golden_no.setError(null);
        et_personal_info_email.setError(null);


        boolean cancel = false;
        View focusView = null;

        personal_info_name = et_personal_info_name.getText().toString().trim();
        personal_info_cnic = et_personal_info_cnic.getText().toString().trim();
        personal_info_expiry_cnic = et_personal_info_expiry_cnic.getText().toString().trim();
        personal_info_dob = et_personal_info_dob.getText().toString().trim();
        personal_info_nationality = et_personal_info_nationality.getText().toString().trim();
        personal_info_ntn_number = et_personal_info_ntn_number.getText().toString().trim();
        personal_info_place_birth = et_personal_info_place_birth.getText().toString().trim();
        personal_info_employee_business = et_personal_info_employee_business.getText().toString().trim();
        personal_info_nature_business = et_personal_info_nature_business.getText().toString().trim();
        personal_info_designation = et_personal_info_designation.getText().toString().trim();
        personal_info_father_husband_name = et_personal_info_father_husband_name.getText().toString().trim();
        personal_info_visa_expiry_date = et_personal_info_visa_expiry_date.getText().toString().trim();
        personal_info_residential_address = et_personal_info_residential_address.getText().toString().trim();
        personal_info_residential_contact_no = et_personal_info_residential_contact_no.getText().toString().trim();
        personal_info_contact_no = et_personal_info_contact_no.getText().toString().trim();
        personal_info_office_address = et_personal_info_office_address.getText().toString().trim();
        personal_info_office_number = et_personal_info_office_number.getText().toString().trim();
        personal_info_telenor_golden_no = et_personal_info_telenor_golden_no.getText().toString().trim();
        personal_info_email = et_personal_info_email.getText().toString().trim();

        try {
            if (TextUtils.isEmpty(personal_info_name)) {

                et_personal_info_name.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_name;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_cnic)) {
                et_personal_info_cnic.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_cnic;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_expiry_cnic)) {
                et_personal_info_expiry_cnic.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_expiry_cnic;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_dob)) {
                et_personal_info_dob.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_dob;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_nationality)) {
                et_personal_info_nationality.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_nationality;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_ntn_number)) {
                et_personal_info_ntn_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_ntn_number;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_place_birth)) {
                et_personal_info_place_birth.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_place_birth;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_employee_business)) {
                et_personal_info_employee_business.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_employee_business;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_nature_business)) {
                et_personal_info_nature_business.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_nature_business;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_designation)) {
                et_personal_info_designation.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_designation;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_father_husband_name)) {
                et_personal_info_father_husband_name.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_father_husband_name;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_visa_expiry_date)) {
                et_personal_info_visa_expiry_date.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_visa_expiry_date;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_residential_address)) {
                et_personal_info_residential_address.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_residential_address;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_residential_contact_no)) {
                et_personal_info_residential_contact_no.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_residential_contact_no;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_contact_no)) {
                et_personal_info_contact_no.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_contact_no;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_office_address)) {
                et_personal_info_office_address.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_office_address;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_office_number)) {
                et_personal_info_office_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_office_number;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_telenor_golden_no)) {
                et_personal_info_telenor_golden_no.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_telenor_golden_no;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_email)) {
                et_personal_info_email.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_email;
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
    public void onNothingSelected(AdapterView<?> parent) {

    }
}