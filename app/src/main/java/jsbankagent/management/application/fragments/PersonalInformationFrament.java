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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.View.OnClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jsbankagent.management.application.R;
import jsbankagent.management.application.utils.AppConstants;

import static jsbankagent.management.application.HomeActivity.drawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInformationFrament extends Fragment implements OnClickListener {
    View personalinformatinoFragment;
    ImageView iv_menu;
    Button btnNext;
    Spinner spinnercourtesyTitle, spinnerGender, spinnermaritalStatus, spinnerQaulification, spinnerProfession,
            spinnermailingAddress,spinnerusCitizen;
    private EditText dateofBirth,expirydateofVisa;
    private DatePickerDialog dateofbirthDatePickerDialog;
    private DatePickerDialog expirydateofVisaPickerDialog;
    public String expirydateofvisa;
    public String dateofbirth;
    private SimpleDateFormat dateFormatter;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

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
                R.array.listGender,android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);


        //Marital Status  Spinner
        ArrayAdapter<CharSequence> maritalstatusAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listmaritalStatus,android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermaritalStatus.setAdapter(maritalstatusAdapter);

        //Qualification  Spinner
        ArrayAdapter<CharSequence> qualificationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listQualification,android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQaulification.setAdapter(qualificationAdapter);

        //Profession  Spinner
        ArrayAdapter<CharSequence> professionAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listProfession,android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfession.setAdapter(professionAdapter);

        //Mailing Address  Spinner
        ArrayAdapter<CharSequence> designationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listmailingAddress,android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermailingAddress.setAdapter(designationAdapter);

        //Us Citizen   Spinner
        ArrayAdapter<CharSequence> uscitizenAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listusCitizen,android.R.layout.simple_spinner_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerusCitizen.setAdapter(uscitizenAdapter);

        findViewsById();

        setDateTimeField();


        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AppConstants.registrationObject.put("date_of_birth",dateofbirth);
                    AppConstants.registrationObject.put("expiry_date_of_visa",expirydateofvisa);

                    fragment = new AccountInformationFragment();
                    fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
                Log.e("dateofbirth",dateofbirth);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        expirydateofVisaPickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                expirydateofVisa.setText(dateFormatter.format(newDate.getTime()));
                expirydateofvisa = expirydateofVisa.getText().toString();
                Log.e("expirydateofvisa",expirydateofvisa);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View v) {
        if(v == dateofBirth){
            dateofbirthDatePickerDialog.show();
        }else if (v == expirydateofVisa){

            expirydateofVisaPickerDialog.show();
        }

    }
}
