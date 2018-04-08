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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

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
public class PersonalInformationJointFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    View personalinfojointFragment;
    ImageView iv_menu;
    Spinner spinnerpersonaljointGender, spinnerpersonaljointusCitizen, spinnerpersonaljointmaritalStatus, spinnerpersonaljointQualification,
            spinnerpersonaljointProfession;
    String personaljointGender, personaljointusCitizen, personaljointmaritalStatus, personaljointQualification, personalfjointProfesstion;
    EditText et_personal_info_joint_name, et_personal_info_nara_joint_number, et_personal_info_joint_expiry_nara_number,
            et_personal_info_joint_dob, et_personal_info_joint_expiry_date_visa, et_personal_info_joint_expiry_father_name,
            et_personal_info_joint_nationality, et_personal_info_joint_ntn_number, et_personal_info_joint_place_of_birth,
            et_personal_info_joint_employer_name, et_personal_info_joint_nature_of_business, et_personal_info_joint_designation,
            et_personal_info_joint_residential_area, et_personal_info_joint_business_address, et_personal_info_joint_office_contact_number,
            et_personal_info_joint_residential_contact_number, et_personal_info_joint_cell_number;

    String personal_info_joint_name, personal_info_nara_joint_number, personal_info_joint_expiry_nara_number, personal_info_joint_dob,
            personal_info_joint_expiry_date_visa, personal_info_joint_expiry_father_name, personal_info_joint_nationality,
            personal_info_joint_ntn_number, personal_info_joint_place_of_birth, personal_info_joint_employer_name,
            personal_info_joint_nature_of_business, personal_info_joint_designation, personal_info_joint_residential_area,
            personal_info_joint_business_address, personal_info_joint_office_contact_number, personal_info_joint_residential_contact_number,
            personal_info_joint_cell_number;
    Button btn_next_step_3;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private EditText dateofBirth, expirydateofVisa;
    private DatePickerDialog dateofbirthDatePickerDialog;
    private DatePickerDialog expirydateofVisaPickerDialog;
    public String expirydateofvisa;
    public String dateofbirth;
    private SimpleDateFormat dateFormatter;

    public PersonalInformationJointFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        personalinfojointFragment = inflater.inflate(R.layout.fragment_personal_information_joint, container, false);

        spinnerpersonaljointGender = personalinfojointFragment.findViewById(R.id.spinner_personal_info_gender);
        spinnerpersonaljointusCitizen = personalinfojointFragment.findViewById(R.id.spinner_personal_info_us_citizen);
        spinnerpersonaljointmaritalStatus = personalinfojointFragment.findViewById(R.id.spinner_personal_info_marital_status);
        spinnerpersonaljointQualification = personalinfojointFragment.findViewById(R.id.spinner_personal_info_qualification);
        spinnerpersonaljointProfession = personalinfojointFragment.findViewById(R.id.spinner_personal_info_profession);
        btn_next_step_3 = (Button) personalinfojointFragment.findViewById(R.id.btn_next_step_3);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        //Todo Intiliaze the EditText
        et_personal_info_joint_name = personalinfojointFragment.findViewById(R.id.et_personal_info_name);
        et_personal_info_nara_joint_number = personalinfojointFragment.findViewById(R.id.et_personal_info_nara_number);
        et_personal_info_joint_expiry_nara_number = personalinfojointFragment.findViewById(R.id.et_personal_info_expiry_nara_number);
        et_personal_info_joint_dob = personalinfojointFragment.findViewById(R.id.et_personal_info_dob);
        et_personal_info_joint_expiry_date_visa = personalinfojointFragment.findViewById(R.id.et_personal_info_expiry_date_visa);
        et_personal_info_joint_expiry_father_name = personalinfojointFragment.findViewById(R.id.et_personal_info_expiry_father_name);
        et_personal_info_joint_nationality = personalinfojointFragment.findViewById(R.id.et_personal_info_nationality);
        et_personal_info_joint_ntn_number = personalinfojointFragment.findViewById(R.id.et_personal_info_ntn_number);
        et_personal_info_joint_place_of_birth = personalinfojointFragment.findViewById(R.id.et_personal_info_place_of_birth);
        et_personal_info_joint_employer_name = personalinfojointFragment.findViewById(R.id.et_personal_info_employer_name);
        et_personal_info_joint_nature_of_business = personalinfojointFragment.findViewById(R.id.et_personal_info_nature_of_business);
        et_personal_info_joint_designation = personalinfojointFragment.findViewById(R.id.et_personal_info_designation);
        et_personal_info_joint_residential_area = personalinfojointFragment.findViewById(R.id.et_personal_info_residential_area);
        et_personal_info_joint_business_address = personalinfojointFragment.findViewById(R.id.et_personal_info_business_address);
        et_personal_info_joint_office_contact_number = personalinfojointFragment.findViewById(R.id.et_personal_info_office_contact_number);
        et_personal_info_joint_residential_contact_number = personalinfojointFragment.findViewById(R.id.et_personal_info_residential_contact_number);
        et_personal_info_joint_cell_number = personalinfojointFragment.findViewById(R.id.et_personal_info_cell_number);

        dateofBirth = (EditText) personalinfojointFragment.findViewById(R.id.et_personal_info_dob);
        dateofBirth.setInputType(InputType.TYPE_NULL);
        dateofBirth.requestFocus();

        expirydateofVisa = (EditText) personalinfojointFragment.findViewById(R.id.et_personal_info_expiry_date_visa);
        expirydateofVisa.setInputType(InputType.TYPE_NULL);

        iv_menu = personalinfojointFragment.findViewById(R.id.imageviewMenu);
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
        btn_next_step_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!checkFields()) {


                        AppConstants.registrationObject.put("personal_info_joint_name", et_personal_info_joint_name.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_nara_joint_number", et_personal_info_nara_joint_number.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_nara_expiry_joint_number", et_personal_info_joint_expiry_nara_number.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_dob", et_personal_info_joint_dob.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_gender", personaljointGender);
                        AppConstants.registrationObject.put("personal_info_joint_expiry_date_visa", et_personal_info_joint_expiry_date_visa.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_father_name", et_personal_info_joint_expiry_father_name.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_us_citizen", personaljointusCitizen);
                        AppConstants.registrationObject.put("personal_info_joint_nationality", et_personal_info_joint_nationality.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_ntn_number", et_personal_info_joint_ntn_number.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_place_of_birth", et_personal_info_joint_place_of_birth.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_marital_status", personaljointmaritalStatus);
                        AppConstants.registrationObject.put("personal_info_joint_qualification", personaljointQualification);
                        AppConstants.registrationObject.put("personal_info_joint_professtion", personalfjointProfesstion);
                        AppConstants.registrationObject.put("personal_info_joint_employer_name", et_personal_info_joint_employer_name.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_nature_of_business", et_personal_info_joint_nature_of_business.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_designation", et_personal_info_joint_designation.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_residential_area", et_personal_info_joint_residential_area.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_business_address", et_personal_info_joint_business_address.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_office_contact_number", et_personal_info_joint_office_contact_number.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_residential_contact_number", et_personal_info_joint_residential_contact_number.getText().toString().trim());
                        AppConstants.registrationObject.put("personal_info_joint_cell_number", et_personal_info_joint_cell_number.getText().toString().trim());

                        fragment = new NextOfKinFragment();
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, fragment);
                        fragmentTransaction.commit();
                        DataHandler.updatePreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION, AppConstants.registrationObject.toString());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        //Gender Spinner
        ArrayAdapter<CharSequence> personalinfojointgenderAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listGender, android.R.layout.simple_spinner_item);
        personalinfojointgenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointGender.setAdapter(personalinfojointgenderAdapter);

        //us citizen
        ArrayAdapter<CharSequence> personalinfojointuscitizenAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listusCitizen, android.R.layout.simple_spinner_item);
        personalinfojointuscitizenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointusCitizen.setAdapter(personalinfojointuscitizenAdapter);

        //martial status Spinner
        ArrayAdapter<CharSequence> personalinfojointmaritalstatusAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listmaritalStatus, android.R.layout.simple_spinner_item);
        personalinfojointmaritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointmaritalStatus.setAdapter(personalinfojointmaritalstatusAdapter);

        //qualification Spinner
        ArrayAdapter<CharSequence> personalinfojointqualificationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listQualification, android.R.layout.simple_spinner_item);
        personalinfojointqualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointQualification.setAdapter(personalinfojointqualificationAdapter);

        //qualification Spinner
        ArrayAdapter<CharSequence> personalinfojointprofessionAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listProfession, android.R.layout.simple_spinner_item);
        personalinfojointprofessionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointProfession.setAdapter(personalinfojointprofessionAdapter);

        setDateTimeField();

        spinnerpersonaljointGender.setOnItemSelectedListener(this);
        spinnerpersonaljointusCitizen.setOnItemSelectedListener(this);
        spinnerpersonaljointmaritalStatus.setOnItemSelectedListener(this);
        spinnerpersonaljointQualification.setOnItemSelectedListener(this);
        spinnerpersonaljointProfession.setOnItemSelectedListener(this);

        return personalinfojointFragment;
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

    //Todo Check Form Fields
    public boolean checkFields() {

        et_personal_info_joint_name.setError(null);
        et_personal_info_nara_joint_number.setError(null);
        et_personal_info_joint_expiry_nara_number.setError(null);
        et_personal_info_joint_dob.setError(null);
        et_personal_info_joint_expiry_date_visa.setError(null);
        et_personal_info_joint_expiry_father_name.setError(null);
        et_personal_info_joint_nationality.setError(null);
        et_personal_info_joint_ntn_number.setError(null);
        et_personal_info_joint_place_of_birth.setError(null);
        et_personal_info_joint_employer_name.setError(null);
        et_personal_info_joint_nature_of_business.setError(null);
        et_personal_info_joint_designation.setError(null);
        et_personal_info_joint_residential_area.setError(null);
        et_personal_info_joint_business_address.setError(null);
        et_personal_info_joint_office_contact_number.setError(null);
        et_personal_info_joint_residential_contact_number.setError(null);
        et_personal_info_joint_cell_number.setError(null);


        boolean cancel = false;
        View focusView = null;

        personal_info_joint_name = et_personal_info_joint_name.getText().toString().trim();
        personal_info_nara_joint_number = et_personal_info_nara_joint_number.getText().toString().trim();
        personal_info_joint_expiry_nara_number = et_personal_info_joint_expiry_nara_number.getText().toString().trim();
        personal_info_joint_dob = et_personal_info_joint_dob.getText().toString().trim();
        personal_info_joint_expiry_date_visa = et_personal_info_joint_expiry_date_visa.getText().toString().trim();
        personal_info_joint_expiry_father_name = et_personal_info_joint_expiry_father_name.getText().toString().trim();
        personal_info_joint_nationality = et_personal_info_joint_nationality.getText().toString().trim();
        personal_info_joint_ntn_number = et_personal_info_joint_ntn_number.getText().toString().trim();
        personal_info_joint_place_of_birth = et_personal_info_joint_place_of_birth.getText().toString().trim();
        personal_info_joint_employer_name = et_personal_info_joint_employer_name.getText().toString().trim();
        personal_info_joint_nature_of_business = et_personal_info_joint_nature_of_business.getText().toString().trim();
        personal_info_joint_designation = et_personal_info_joint_designation.getText().toString().trim();
        personal_info_joint_residential_area = et_personal_info_joint_residential_area.getText().toString().trim();
        personal_info_joint_business_address = et_personal_info_joint_business_address.getText().toString().trim();
        personal_info_joint_office_contact_number = et_personal_info_joint_office_contact_number.getText().toString().trim();
        personal_info_joint_residential_contact_number = et_personal_info_joint_residential_contact_number.getText().toString().trim();
        personal_info_joint_cell_number = et_personal_info_joint_cell_number.getText().toString().trim();

        try {
            if (TextUtils.isEmpty(personal_info_joint_name)) {

                et_personal_info_joint_name.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_name;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_nara_joint_number)) {
                et_personal_info_nara_joint_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_nara_joint_number;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_expiry_nara_number)) {
                et_personal_info_joint_expiry_nara_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_expiry_nara_number;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_dob)) {
                et_personal_info_joint_dob.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_dob;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_expiry_date_visa)) {
                et_personal_info_joint_expiry_date_visa.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_expiry_date_visa;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_expiry_father_name)) {
                et_personal_info_joint_expiry_father_name.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_expiry_father_name;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_nationality)) {
                et_personal_info_joint_nationality.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_nationality;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_ntn_number)) {
                et_personal_info_joint_ntn_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_ntn_number;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_place_of_birth)) {
                et_personal_info_joint_place_of_birth.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_place_of_birth;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_employer_name)) {
                et_personal_info_joint_employer_name.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_employer_name;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_nature_of_business)) {
                et_personal_info_joint_nature_of_business.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_nature_of_business;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_designation)) {
                et_personal_info_joint_designation.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_designation;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_residential_area)) {
                et_personal_info_joint_residential_area.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_residential_area;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_business_address)) {
                et_personal_info_joint_business_address.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_business_address;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_office_contact_number)) {
                et_personal_info_joint_office_contact_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_office_contact_number;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_residential_contact_number)) {
                et_personal_info_joint_residential_contact_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_residential_contact_number;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_joint_cell_number)) {
                et_personal_info_joint_cell_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_joint_cell_number;
                cancel = true;
            } else if (TextUtils.isEmpty(personaljointGender)) {
                Toast toast = Toast.makeText(getActivity(), "Please select your gender", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerpersonaljointGender;
                cancel = true;
            } else if (TextUtils.isEmpty(personaljointusCitizen)) {
                Toast toast = Toast.makeText(getActivity(), "Please select US Citizen", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerpersonaljointusCitizen;
                cancel = true;
            } else if (TextUtils.isEmpty(personaljointmaritalStatus)) {
                Toast toast = Toast.makeText(getActivity(), "Please select your marital status", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerpersonaljointmaritalStatus;
                cancel = true;
            } else if (TextUtils.isEmpty(personaljointQualification)) {
                Toast toast = Toast.makeText(getActivity(), "Please select your qualification", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerpersonaljointQualification;
                cancel = true;
            } else if (TextUtils.isEmpty(personalfjointProfesstion)) {
                Toast toast = Toast.makeText(getActivity(), "Please select your profession", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerpersonaljointProfession;
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
                    case R.id.spinner_personal_info_gender:
                        personaljointGender = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_personal_info_us_citizen:
                        personaljointusCitizen = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_personal_info_marital_status:
                        personaljointmaritalStatus = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_personal_info_qualification:
                        personaljointQualification = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_personal_info_profession:
                        personalfjointProfesstion = parent.getSelectedItem().toString();
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
}
