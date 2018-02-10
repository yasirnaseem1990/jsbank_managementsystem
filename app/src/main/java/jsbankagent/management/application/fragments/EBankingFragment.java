package jsbankagent.management.application.fragments;

import android.graphics.Color;
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

import jsbankagent.management.application.R;

import static jsbankagent.management.application.HomeActivity.drawer;


public class EBankingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

   /* private OnFragmentInteractionListener mListener;*/
    View ebankingFragment;
    Button btn_next_step8;
    ImageView iv_menu;
    Spinner spinnersmsAlerts,spinnerinternetBanking,spinnermobileBanking,spinnereStatement,spinnerFrequency;
    String smsAlerts,internetBanking,mobileBanking,eStatement,Frequency;
    public EBankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
                try{
                    if (!checkFields()){
                        Toast.makeText(getActivity(), "Form Saved", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        try{
            //SMS Alerts Spinner
            ArrayAdapter<CharSequence> smsalertAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listsmsAlerts,android.R.layout.simple_spinner_item);
            smsalertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnersmsAlerts.setAdapter(smsalertAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            //Internet Banking Spinner
            ArrayAdapter<CharSequence> internetbankingAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listinternetBanking,android.R.layout.simple_spinner_item);
            internetbankingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerinternetBanking.setAdapter(internetbankingAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            //Mobile Banking Spinner
            ArrayAdapter<CharSequence> mobilebankingAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listmobileBanking,android.R.layout.simple_spinner_item);
            mobilebankingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnermobileBanking.setAdapter(mobilebankingAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            //E- Statement Spinner
            ArrayAdapter<CharSequence> estatementAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listestatement,android.R.layout.simple_spinner_item);
            estatementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnereStatement.setAdapter(estatementAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            //Frequency Spinner
            ArrayAdapter<CharSequence> frequencyAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listfrequency,android.R.layout.simple_spinner_item);
            frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerFrequency.setAdapter(frequencyAdapter);
        }catch (Exception e){
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
        }catch (Exception e){
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
                Toast toast =Toast.makeText(getActivity(), "Please select SMS alert", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnersmsAlerts;
                cancel = true;
            } else if (TextUtils.isEmpty(internetBanking)) {
                Toast toast =Toast.makeText(getActivity(), "Please select internet banking", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerinternetBanking;
                cancel = true;
            }else if (TextUtils.isEmpty(mobileBanking)) {
                Toast toast =Toast.makeText(getActivity(), "Please select mobile internet banking", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnermobileBanking;
                cancel = true;
            }else if (TextUtils.isEmpty(eStatement)) {
                Toast toast =Toast.makeText(getActivity(), "Please select E-statement", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnereStatement;
                cancel = true;
            }else if (TextUtils.isEmpty(Frequency)) {
                Toast toast =Toast.makeText(getActivity(), "Please select frequency", Toast.LENGTH_SHORT);
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
