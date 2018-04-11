package jsbankagent.management.application.fragments;


import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jsbankagent.management.application.R;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;
import jsbankagent.management.application.utils.PickerDialogs;

import static android.app.Activity.RESULT_OK;
import static jsbankagent.management.application.HomeActivity.drawer;
import static jsbankagent.management.application.utils.AppUtils.britishFormateNew;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInformationFrament extends Fragment implements OnClickListener, AdapterView.OnItemSelectedListener {
    View personalinformatinoFragment;
    ImageView iv_menu;
    Button btnNext;
    TextView tv_expiry_date_visa;
    ImageView btn_person_image, btn_cnic_front, btn_cnic_back;
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
    private final int PERSON_IMAGE_CAMERA_REQUEST = 420;
    private final int CNIC_FRONT_IMAGE_CAMERA_REQUEST = 421;
    private final int CNIC_BACK_IMAGE_CAMERA_REQUEST = 422;
    private final int CAMERA_REQUEST = 123;

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private String courtesyTitle = "", gender = "", maritalStatus = "", qualification = "", profession = "", mailainAddress = "", usCitizen = "";
    private String newaccountpicturePath = "";
    private Uri selectedImage;
    private String base64personImage = "";
    private String base64cnicfrontImage = "";
    private String base64cnicbackImage = "";

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

        tv_expiry_date_visa = personalinformatinoFragment.findViewById(R.id.tv_expiry_date_visa);
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


        dateofBirth = personalinformatinoFragment.findViewById(R.id.et_dateofbirth);
        dateofBirth.setInputType(InputType.TYPE_NULL);
        dateofBirth.requestFocus();

        expirydateofVisa = personalinformatinoFragment.findViewById(R.id.et_expiry_date_visa);
        expirydateofVisa.setInputType(InputType.TYPE_NULL);

        //Intiliaze the Button
        btn_person_image = (ImageView) personalinformatinoFragment.findViewById(R.id.btn_person_image);
        btn_cnic_front = (ImageView) personalinformatinoFragment.findViewById(R.id.btn_cnic_front);
        btn_cnic_back = (ImageView) personalinformatinoFragment.findViewById(R.id.btn_cnic_back);

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
                R.array.listQualification, R.layout.spinner_textview_with_padding);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQaulification.setAdapter(qualificationAdapter);

        //Profession  Spinner
        ArrayAdapter<CharSequence> professionAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listProfession, R.layout.spinner_textview_with_padding);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfession.setAdapter(professionAdapter);

        //Mailing Address  Spinner
        ArrayAdapter<CharSequence> designationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listmailingAddress, R.layout.spinner_textview_with_padding);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermailingAddress.setAdapter(designationAdapter);

        //Us Citizen   Spinner
        ArrayAdapter<CharSequence> uscitizenAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listusCitizen, R.layout.spinner_textview_with_padding);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerusCitizen.setAdapter(uscitizenAdapter);

        setDateTimeField();


        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!checkFields()) {
                        AppConstants.registrationObject.put("personal_info_courtesy_title", courtesyTitle);
                        AppConstants.registrationObject.put("personal_info_name", personal_info_name);
                        AppConstants.registrationObject.put("personal_info_cnic", personal_info_cnic);
                        AppConstants.registrationObject.put("personal_info_expiry_cnic", personal_info_expiry_cnic);
                        AppConstants.registrationObject.put("personal_info_dob",personal_info_dob);
                        AppConstants.registrationObject.put("personal_info_gender", gender);
                        AppConstants.registrationObject.put("personal_info_nationality", personal_info_nationality);
                        AppConstants.registrationObject.put("personal_info_ntn_number", personal_info_ntn_number);
                        AppConstants.registrationObject.put("personal_info_placeofbirth", personal_info_place_birth);
                        AppConstants.registrationObject.put("personal_info_marital_status", maritalStatus);
                        AppConstants.registrationObject.put("personal_info_qualification", qualification);
                        AppConstants.registrationObject.put("personal_info_profession", profession);
                        AppConstants.registrationObject.put("personal_info_employee_business", personal_info_employee_business);
                        AppConstants.registrationObject.put("personal_info_nature_business", personal_info_nature_business);
                        AppConstants.registrationObject.put("personal_info_designation", personal_info_designation);
                        AppConstants.registrationObject.put("personal_info_mailainAddress", mailainAddress);
                        AppConstants.registrationObject.put("personal_info_father_name", personal_info_father_husband_name);
                        AppConstants.registrationObject.put("personal_info_uscitizen", usCitizen);
                        AppConstants.registrationObject.put("personal_info_visa_expiry_date", personal_info_visa_expiry_date);
                        AppConstants.registrationObject.put("personal_info_residential_address", personal_info_residential_address);
                        AppConstants.registrationObject.put("personal_info_residential_contact_no", personal_info_residential_contact_no);
                        AppConstants.registrationObject.put("personal_info_contact_no", personal_info_contact_no);
                        AppConstants.registrationObject.put("personal_info_office_address", personal_info_office_address);
                        AppConstants.registrationObject.put("personal_info_office_number", personal_info_office_number);
                        AppConstants.registrationObject.put("personal_info_telenor_golden_no", personal_info_telenor_golden_no);
                        AppConstants.registrationObject.put("personal_info_email", personal_info_email);

                        //TODO Saving BASE64 of three images in AppConstants Registration Object JsonObject
                        AppConstants.registrationObject.put("person_image", base64personImage);
                        AppConstants.registrationObject.put("cnic_front_image", base64cnicfrontImage);
                        AppConstants.registrationObject.put("cnic_back_image", base64cnicbackImage);

                        fragment = new AccountInformationFragment();
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        DataHandler.updatePreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION, AppConstants.registrationObject.toString());
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

        //TODO Listener for Camera Open
        btn_person_image.setOnClickListener(this);
        btn_cnic_front.setOnClickListener(this);
        btn_cnic_back.setOnClickListener(this);


        return personalinformatinoFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 420:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        btn_person_image.setImageBitmap(bitmap);
                        //TODO Calling the CompressFunction which will compress the image and then return the Picture Path
                        String personimagePath = CompressImage(getActivity(), bitmap);
                        base64personImage = changeImageToBase64(personimagePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 421:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        btn_cnic_front.setImageBitmap(bitmap);
                        //TODO Calling the CompressFunction which will compress the image and then return the Picture Path
                        String cnicfrontimagePath = CompressImage(getActivity(), bitmap);
                        base64cnicfrontImage = changeImageToBase64(cnicfrontimagePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 422:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        btn_cnic_back.setImageBitmap(bitmap);
                        //TODO Calling the CompressFunction which will compress the image and then return the Picture Path
                        String cnicbackimagePath = CompressImage(getActivity(), bitmap);
                        base64cnicbackImage = changeImageToBase64(cnicbackimagePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    private void setDateTimeField() {
        dateofBirth.setOnClickListener(this);
        expirydateofVisa.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        dateofbirthDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                dateofbirth = dateFormatter.format(newDate.getTime());

                SimpleDateFormat newFormat = new SimpleDateFormat(britishFormateNew);
                dateofbirth = newFormat.format(newDate.getTime());

                dateofBirth.setText(dateofbirth);
                dateofbirth = dateofBirth.getText().toString();
                Log.e("dateofbirth", dateofbirth);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        expirydateofVisaPickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat newFormat = new SimpleDateFormat(britishFormateNew);
                expirydateofvisa = newFormat.format(newDate.getTime());
                expirydateofVisa.setText(expirydateofvisa);
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
        switch (v.getId()) {
            case R.id.btn_person_image:
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        explain("You need to give mandatory permissions to continue. Do you want to go to app settings?");
                    } else {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        /*cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);*/
                        startActivityForResult(cameraIntent, PERSON_IMAGE_CAMERA_REQUEST);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_cnic_front:
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        explain("You need to give mandatory permissions to continue. Do you want to go to app settings?");
                    } else {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        /*cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);*/
                        startActivityForResult(cameraIntent, CNIC_FRONT_IMAGE_CAMERA_REQUEST);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_cnic_back:
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        explain("You need to give mandatory permissions to continue. Do you want to go to app settings?");
                    } else {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        /*cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);*/
                        startActivityForResult(cameraIntent, CNIC_BACK_IMAGE_CAMERA_REQUEST);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }

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
                        break;
                    case R.id.spinner_profession:
                        profession = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_mailain_address:
                        mailainAddress = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_us_citizen:
                        usCitizen = parent.getSelectedItem().toString();
                        if (usCitizen.equalsIgnoreCase("NO")) {
                            expirydateofVisa.setVisibility(View.GONE);
                            tv_expiry_date_visa.setVisibility(View.GONE);
                        }
                        if (usCitizen.equalsIgnoreCase("YES")) {
                            expirydateofVisa.setVisibility(View.VISIBLE);
                            tv_expiry_date_visa.setVisibility(View.VISIBLE);
                            usCitizen = parent.getSelectedItem().toString();
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean checkFields() {

        et_personal_info_name.setError(null);
        et_personal_info_cnic.setError(null);
        et_personal_info_expiry_cnic.setError(null);
        et_personal_info_dob.setError(null);
        et_personal_info_nationality.setError(null);
        /*et_personal_info_ntn_number.setError(null);*/
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
        personal_info_ntn_number = !et_personal_info_ntn_number.getText().toString().equals("") ? et_personal_info_ntn_number.getText().toString().trim() : "N/A";

        personal_info_place_birth = et_personal_info_place_birth.getText().toString().trim();
        personal_info_employee_business = et_personal_info_employee_business.getText().toString().trim();
        personal_info_nature_business = et_personal_info_nature_business.getText().toString().trim();
        personal_info_designation = et_personal_info_designation.getText().toString().trim();
        personal_info_father_husband_name = et_personal_info_father_husband_name.getText().toString().trim();
        try{
            if (usCitizen.equalsIgnoreCase("NO")) {
                usCitizen = "NO";
                personal_info_visa_expiry_date = "N/A";
            } else {
                personal_info_visa_expiry_date = et_personal_info_visa_expiry_date.getText().toString().trim();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }


        personal_info_residential_address = et_personal_info_residential_address.getText().toString().trim();
        personal_info_residential_contact_no = !et_personal_info_residential_contact_no.getText().toString().equals("") ? et_personal_info_residential_contact_no.getText().toString().trim() : "N/A";

        personal_info_contact_no = et_personal_info_contact_no.getText().toString().trim();
        personal_info_office_address = et_personal_info_office_address.getText().toString().trim();
        personal_info_office_number = !et_personal_info_office_number.getText().toString().equals("") ? et_personal_info_office_number.getText().toString().trim() : "N/A";

        personal_info_telenor_golden_no = et_personal_info_telenor_golden_no.getText().toString().trim();
        personal_info_email = !et_personal_info_email.getText().toString().equals("") ? et_personal_info_email.getText().toString().trim() : "N/A";
        try {
            if (TextUtils.isEmpty(courtesyTitle)){
                Toast toast = Toast.makeText(getActivity(), "Please select your courtesy title", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnercourtesyTitle;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_name)) {

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
            } else if (TextUtils.isEmpty(gender)){
                Toast toast = Toast.makeText(getActivity(), "Please select your gender", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerGender;
                cancel = true;
            }else if (TextUtils.isEmpty(personal_info_nationality)) {
                et_personal_info_nationality.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_nationality;
                cancel = true;
            } /*else if (TextUtils.isEmpty(personal_info_ntn_number)) {
                et_personal_info_ntn_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_ntn_number;
                cancel = true;
            }*/ else if (TextUtils.isEmpty(personal_info_place_birth)) {
                et_personal_info_place_birth.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_place_birth;
                cancel = true;
            } else if (TextUtils.isEmpty(maritalStatus)){
                Toast toast = Toast.makeText(getActivity(), "Please select your marital status", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnermaritalStatus;
                cancel = true;
            } else if (TextUtils.isEmpty(qualification)){
                Toast toast = Toast.makeText(getActivity(), "Please select your qualification", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerQaulification;
                cancel = true;
            } else if (TextUtils.isEmpty(profession)){
                Toast toast = Toast.makeText(getActivity(), "Please select your profession", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerProfession;
                cancel = true;
            } else if (TextUtils.isEmpty(mailainAddress)){
                Toast toast = Toast.makeText(getActivity(), "Please select your mailing address", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnermailingAddress;
                cancel = true;
            } else if (TextUtils.isEmpty(usCitizen)){
                Toast toast = Toast.makeText(getActivity(), "Please select your citizenship", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerusCitizen;
                cancel = true;
            }else if (TextUtils.isEmpty(personal_info_employee_business)) {
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
            } /*else if (TextUtils.isEmpty(personal_info_residential_contact_no)) {
                et_personal_info_residential_contact_no.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_residential_contact_no;
                cancel = true;
            }*/ else if (TextUtils.isEmpty(personal_info_contact_no)) {
                et_personal_info_contact_no.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_contact_no;
                cancel = true;
            } else if (TextUtils.isEmpty(personal_info_office_address)) {
                et_personal_info_office_address.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_office_address;
                cancel = true;
            } /*else if (TextUtils.isEmpty(personal_info_office_number)) {
                et_personal_info_office_number.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_office_number;
                cancel = true;
            }*/ else if (TextUtils.isEmpty(personal_info_telenor_golden_no)) {
                et_personal_info_telenor_golden_no.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_telenor_golden_no;
                cancel = true;
            } /*else if (TextUtils.isEmpty(personal_info_email)) {
                et_personal_info_email.setError(getString(R.string.error_field_required));
                focusView = et_personal_info_email;
                cancel = true;
            }*/ else if (TextUtils.isEmpty(base64personImage)) {
                Toast toast = Toast.makeText(getActivity(), "Please take your image", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = btn_person_image;
                cancel = true;
            } else if (TextUtils.isEmpty(base64cnicfrontImage)) {
                Toast toast = Toast.makeText(getActivity(), "Please take the picture of your CNIC front side", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = btn_cnic_front;
                cancel = true;
            } else if (TextUtils.isEmpty(base64cnicbackImage)) {
                Toast toast = Toast.makeText(getActivity(), "Please take the picture of your CNIC back side", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = btn_cnic_back;
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

    private void explain(String msg) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(getActivity());
        dialog.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:jsbankagent.management.application"));
                        startActivityForResult(intent, CAMERA_REQUEST);
                    }
                });
        dialog.show();
    }

    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        newaccountpicturePath = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(newaccountpicturePath);
    }

    private String CompressImage(Context context, Bitmap bitmap) {
        String picturePath = "";
        try {
            selectedImage = getImageUri(context, bitmap);
            if (selectedImage != null) {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();

                Bitmap bm = bitmap;

                int origWidth = bm.getWidth();
                int origHeight = bm.getHeight();
                final int destWidth = 150;//or the width you need
                if (origWidth > destWidth) {
                    // picture is wider thanwe want it, we calculate its target height
                    int destHeight = origHeight / (origWidth / destWidth);
                    // we create an scaled bitmap so it reduces the image, not just trim it
                    Bitmap b2 = Bitmap.createScaledBitmap(bm, destWidth, destHeight, false);
                    Log.e("BitMap", ":" + bm);
                    File file = new File(picturePath);
                    FileOutputStream fOut;
                    try {
                        fOut = new FileOutputStream(file);
                        b2.compress(Bitmap.CompressFormat.PNG, 0, fOut);
                        fOut.flush();
                        fOut.close();
                        b2.recycle();
                        b2.recycle();
                        picturePath = file.getAbsolutePath();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Log.e("Selected Image", ":" + selectedImage);
            } else {
                Toast.makeText(getActivity(), "Application is not working properly on your device, please contact to JS Bank Headquarter ", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return picturePath;
    }

    private String changeImageToBase64(String imagePath) {
        String base64 = "";

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        Bitmap bitmapnew = BitmapFactory.decodeFile(imagePath);

        bitmapnew.compress(Bitmap.CompressFormat.PNG, 0, bao);
        byte[] ba = bao.toByteArray();
        Log.e("ByteArray", ":" + ba);
        base64 = Base64.encodeToString(ba, Base64.DEFAULT);
        Log.e("base64", "-----" + imagePath);

        return base64;
    }
}