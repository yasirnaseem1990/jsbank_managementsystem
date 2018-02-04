package jsbankagent.management.application.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import jsbankagent.management.application.R;

import static jsbankagent.management.application.HomeActivity.drawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupplementaryCardFragment extends Fragment implements View.OnClickListener {

    View supplementarycardFragment;
    Button btn_next_step7;
    Fragment fragment;
    ImageView iv_menu;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Spinner spinnerrequestsupplementaryCard;
    private DatePickerDialog dateofbirthDatePickerDialog;
    private DatePickerDialog expirydateofVisaPickerDialog;
    public String expirydateofvisa;
    public String dateofbirth;
    EditText et_supplementarycardDOB,et_supplementarycardvisaexpiryDate;
    private SimpleDateFormat dateFormatter;
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
        et_supplementarycardDOB = (EditText) supplementarycardFragment.findViewById(R.id.et_supplementary_card_dob);
        et_supplementarycardDOB.setInputType(InputType.TYPE_NULL);
        et_supplementarycardDOB.requestFocus();

        et_supplementarycardvisaexpiryDate =  supplementarycardFragment.findViewById(R.id.et_supplementary_card_expiry_cnic_date);
        et_supplementarycardvisaexpiryDate.setInputType(InputType.TYPE_NULL);
        btn_next_step7 = supplementarycardFragment.findViewById(R.id.btn_next_step_7);
        iv_menu = supplementarycardFragment.findViewById(R.id.imageviewMenu);
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
                    fragment = new EBankingFragment();
                    fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.commit();
                }catch (NullPointerException e){
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
        return supplementarycardFragment;
    }

    private void setDateTimeField() {
        et_supplementarycardDOB.setOnClickListener(this);
        et_supplementarycardvisaexpiryDate.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat();
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
}
