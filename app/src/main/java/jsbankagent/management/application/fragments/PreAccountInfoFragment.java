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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import jsbankagent.management.application.R;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;

import static jsbankagent.management.application.HomeActivity.drawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreAccountInfoFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    View preAccountInfoFragment;
    ImageView iv_menu;
    Spinner spinnertypeofcurrentAccount,spinnerjointaccountNeed,spinnerdebitcardNeed,spinnersupplementarycardNeed,spinnerebankingNeed;
    private String typeofcurrentAccount,jointaccountneed,debitcardneed,supplementarycardneed,ebankingneed;
    Button btnNext;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public PreAccountInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        preAccountInfoFragment = inflater.inflate(R.layout.fragment_pre_account_info, container, false);
        AppConstants.preaccountinfoObject = new JSONObject();
        spinnertypeofcurrentAccount = preAccountInfoFragment.findViewById(R.id.spinner_type_of_current_account);
        spinnerjointaccountNeed = preAccountInfoFragment.findViewById(R.id.spinner_joint_account_need);
        spinnerdebitcardNeed = preAccountInfoFragment.findViewById(R.id.spinner_debit_card_needs);
        spinnersupplementarycardNeed = preAccountInfoFragment.findViewById(R.id.spinner_supplementary_card);
        spinnerebankingNeed = preAccountInfoFragment.findViewById(R.id.spinner_e_banking_need);
        btnNext = (Button) preAccountInfoFragment.findViewById(R.id.btn_next);
        iv_menu = preAccountInfoFragment.findViewById(R.id.imageviewMenu);
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
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkFields()){
                    try{
                        AppConstants.preaccountinfoObject.put("pre_type_of_current_account",typeofcurrentAccount);
                        AppConstants.preaccountinfoObject.put("pre_joint_account_need",jointaccountneed);
                        AppConstants.preaccountinfoObject.put("pre_debit_card_need",debitcardneed);
                        AppConstants.preaccountinfoObject.put("pre_supplementary_card_need",supplementarycardneed);
                        AppConstants.preaccountinfoObject.put("pre_ebanking_need",ebankingneed);

                        fragment = new PersonalInformationFrament();
                        fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        //TODO Saving Pre Account Information
                        DataHandler.updatePreferences(AppConstants.PREFERENCE_PRE_ACCOUNT_INFO,AppConstants.preaccountinfoObject.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        //Type of current account Spinner
        ArrayAdapter<CharSequence> typeofcuurentaccountAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listtypeofcurrentAccount,android.R.layout.simple_spinner_item);
        typeofcuurentaccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertypeofcurrentAccount.setAdapter(typeofcuurentaccountAdapter);

        //Type of current account Spinner
        ArrayAdapter<CharSequence> jointaccountNeed = ArrayAdapter.createFromResource(getActivity(),
                R.array.jointaccountNeed,android.R.layout.simple_spinner_item);
        jointaccountNeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerjointaccountNeed.setAdapter(jointaccountNeed);

        //Type of current account Spinner
        ArrayAdapter<CharSequence> debitcardNeed = ArrayAdapter.createFromResource(getActivity(),
                R.array.debitcardNeed,android.R.layout.simple_spinner_item);
        debitcardNeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdebitcardNeed.setAdapter(debitcardNeed);

        //Type of current account Spinner
        ArrayAdapter<CharSequence> supplementarycardNeed = ArrayAdapter.createFromResource(getActivity(),
                R.array.listsupplementaryCard,android.R.layout.simple_spinner_item);
        supplementarycardNeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersupplementarycardNeed.setAdapter(supplementarycardNeed);

        //Type of current account Spinner
        ArrayAdapter<CharSequence> ebankingNeed = ArrayAdapter.createFromResource(getActivity(),
                R.array.listinternetBanking,android.R.layout.simple_spinner_item);
        ebankingNeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerebankingNeed.setAdapter(ebankingNeed);

        spinnertypeofcurrentAccount.setOnItemSelectedListener(this);
        spinnerjointaccountNeed.setOnItemSelectedListener(this);
        spinnerdebitcardNeed.setOnItemSelectedListener(this);
        spinnersupplementarycardNeed.setOnItemSelectedListener(this);
        spinnerebankingNeed.setOnItemSelectedListener(this);

        return preAccountInfoFragment;
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
                    case R.id.spinner_joint_account_need:
                        jointaccountneed = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_debit_card_needs:
                        debitcardneed = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_supplementary_card:
                        supplementarycardneed = parent.getSelectedItem().toString();
                        break;
                    case R.id.spinner_e_banking_need:
                        ebankingneed = parent.getSelectedItem().toString();
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
        boolean cancel = false;
        View focusView = null;

        try {
            if (TextUtils.isEmpty(typeofcurrentAccount)) {
                Toast toast =Toast.makeText(getActivity(), "Please select type of current account", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnertypeofcurrentAccount;
                cancel = true;
            }else if (TextUtils.isEmpty(jointaccountneed)){
                Toast toast =Toast.makeText(getActivity(), "Please select type of current account", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerjointaccountNeed;
                cancel = true;
            }else if (TextUtils.isEmpty(debitcardneed)){
                Toast toast =Toast.makeText(getActivity(), "Please select do you need joint account", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerdebitcardNeed;
                cancel = true;
            }else if (TextUtils.isEmpty(supplementarycardneed)){
                Toast toast =Toast.makeText(getActivity(), "Please select do you need debit card", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnersupplementarycardNeed;
                cancel = true;
            }else if (TextUtils.isEmpty(ebankingneed)){
                Toast toast =Toast.makeText(getActivity(), "Please select do you nee e-banking", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                focusView = spinnerebankingNeed;
                cancel = true;
            }

            if (cancel) {

                focusView.requestFocus();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return cancel;
    }
}
