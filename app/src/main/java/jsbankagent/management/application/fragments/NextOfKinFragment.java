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

import jsbankagent.management.application.R;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;

import static jsbankagent.management.application.HomeActivity.drawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class NextOfKinFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    View nextofkinFragment;
    ImageView iv_menu;
    Button btn_next_step4;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Spinner spinnernextkincourtesyTitle;
    String nextkincourtesyTitle;
    EditText et_next_of_kin_name, et_next_of_kin_relationship, et_next_of_kin_nara_number, et_next_of_kin_mailing_address,
            et_next_of_kin_contact_number, et_next_of_kin_cell_number;
    String next_of_kin_name, next_of_kin_relationship, next_of_kin_nara_number, next_of_kin_mailing_address, next_of_kin_contact_number,
            next_of_kin_cell_number;

    public NextOfKinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        nextofkinFragment = inflater.inflate(R.layout.fragment_next_of_kin, container, false);
        btn_next_step4 = nextofkinFragment.findViewById(R.id.btn_next_step_4);
        spinnernextkincourtesyTitle = nextofkinFragment.findViewById(R.id.spinner_next_of_kin_courtesy_title);

        //Todo EditText Intiliaze
        et_next_of_kin_name = nextofkinFragment.findViewById(R.id.et_next_of_kin_name);
        et_next_of_kin_relationship = nextofkinFragment.findViewById(R.id.et_next_of_kin_relationship);
        et_next_of_kin_nara_number = nextofkinFragment.findViewById(R.id.et_next_of_kin_nara_number);
        et_next_of_kin_mailing_address = nextofkinFragment.findViewById(R.id.et_next_of_kin_mailing_address);
        et_next_of_kin_contact_number = nextofkinFragment.findViewById(R.id.et_next_of_kin_contact_number);
        et_next_of_kin_cell_number = nextofkinFragment.findViewById(R.id.et_next_of_kin_cell_number);

        iv_menu = nextofkinFragment.findViewById(R.id.imageviewMenu);
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

        btn_next_step4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!checkFields()) {

                        AppConstants.registrationObject.put("next_kin_courtesy_title", nextkincourtesyTitle);
                        AppConstants.registrationObject.put("next_kin_name", et_next_of_kin_name.getText().toString().trim());
                        AppConstants.registrationObject.put("next_kin_relationship", et_next_of_kin_relationship.getText().toString().trim());
                        AppConstants.registrationObject.put("next_kin_nara_number", et_next_of_kin_nara_number.getText().toString().trim());
                        AppConstants.registrationObject.put("next_kin_mailing_address", et_next_of_kin_mailing_address.getText().toString().trim());
                        AppConstants.registrationObject.put("next_kin_contact_number", next_of_kin_contact_number);
                        AppConstants.registrationObject.put("next_kin_cell_number", et_next_of_kin_cell_number.getText().toString().trim());
                        fragment = new BusinessAccountInformationFragment();
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

        try {
            //SMS Alerts Spinner
            ArrayAdapter<CharSequence> nextofkinAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.courtesyTitle, android.R.layout.simple_spinner_item);
            nextofkinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnernextkincourtesyTitle.setAdapter(nextofkinAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        spinnernextkincourtesyTitle.setOnItemSelectedListener(this);
        return nextofkinFragment;
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
                    case R.id.spinner_next_of_kin_courtesy_title:
                        nextkincourtesyTitle = parent.getSelectedItem().toString();
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

        et_next_of_kin_name.setError(null);
        et_next_of_kin_relationship.setError(null);
        et_next_of_kin_nara_number.setError(null);
        et_next_of_kin_mailing_address.setError(null);
        et_next_of_kin_contact_number.setError(null);
        et_next_of_kin_cell_number.setError(null);

        boolean cancel = false;
        View focusView = null;

        next_of_kin_name = et_next_of_kin_name.getText().toString().trim();
        next_of_kin_relationship = et_next_of_kin_relationship.getText().toString().trim();
        next_of_kin_nara_number = et_next_of_kin_nara_number.getText().toString().trim();
        next_of_kin_mailing_address = et_next_of_kin_mailing_address.getText().toString().trim();
        next_of_kin_contact_number = !et_next_of_kin_contact_number.getText().toString().equals("") ? et_next_of_kin_contact_number.getText().toString().trim() : "N/A";
        next_of_kin_cell_number = et_next_of_kin_cell_number.getText().toString().trim();


        try {
            if (TextUtils.isEmpty(nextkincourtesyTitle)) {
                Toast toast = Toast.makeText(getActivity(), "Please select courtesy title", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnernextkincourtesyTitle;
                cancel = true;
            } else if (TextUtils.isEmpty(next_of_kin_name)) {

                et_next_of_kin_name.setError(getString(R.string.error_field_required));
                focusView = et_next_of_kin_name;
                cancel = true;
            } else if (TextUtils.isEmpty(next_of_kin_relationship)) {
                et_next_of_kin_relationship.setError(getString(R.string.error_field_required));
                focusView = et_next_of_kin_relationship;
                cancel = true;
            } else if (TextUtils.isEmpty(next_of_kin_nara_number)) {
                et_next_of_kin_nara_number.setError(getString(R.string.error_field_required));
                focusView = et_next_of_kin_nara_number;
                cancel = true;
            } else if (TextUtils.isEmpty(next_of_kin_mailing_address)) {
                et_next_of_kin_mailing_address.setError(getString(R.string.error_field_required));
                focusView = et_next_of_kin_mailing_address;
                cancel = true;
            } /*else if (TextUtils.isEmpty(next_of_kin_contact_number)) {
                et_next_of_kin_contact_number.setError(getString(R.string.error_field_required));
                focusView = et_next_of_kin_contact_number;
                cancel = true;
            }*/ else if (TextUtils.isEmpty(next_of_kin_cell_number)) {
                et_next_of_kin_cell_number.setError(getString(R.string.error_field_required));
                focusView = et_next_of_kin_cell_number;
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
