package jsbankagent.management.application.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import jsbankagent.management.application.R;

import static jsbankagent.management.application.HomeActivity.drawer;


public class EBankingFragment extends Fragment {

   /* private OnFragmentInteractionListener mListener;*/
    View ebankingFragment;
    Button btn_next_step8;
    ImageView iv_menu;
    Spinner spinnersmsAlerts,spinnerinternetBanking,spinnermobileBanking,spinnereStatement,spinnerFrequency;
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
