package jsbankagent.management.application.fragments;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountInformationFragment extends Fragment {

    View accountinformationFragment;
    Spinner spinnertypeofcurrentAccount,spinnertypeofAccount,spinnerCurrency,spinnercountryresidentialStatus,
            spinneroperatingInstructions,spinnerchequebookRequired,spinneryeschequebookRequired,spinnerzakatApplicable;
    ImageView iv_menu;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button btn_next_step2;
    public AccountInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        accountinformationFragment = inflater.inflate(R.layout.fragment_account_information, container, false);

        //intiliaze the spinner
        spinnertypeofcurrentAccount = accountinformationFragment.findViewById(R.id.spinner_type_of_current_account);
        spinnertypeofAccount = accountinformationFragment.findViewById(R.id.spinner_type_of_account);
        spinnerCurrency = accountinformationFragment.findViewById(R.id.spinner_currency);
        spinnercountryresidentialStatus = accountinformationFragment.findViewById(R.id.spinner_country_residental_status);
        spinneroperatingInstructions = accountinformationFragment.findViewById(R.id.spinner_operating_instructions);
        spinnerchequebookRequired = accountinformationFragment.findViewById(R.id.spinner_cheque_book_required);
        spinneryeschequebookRequired = accountinformationFragment.findViewById(R.id.spinner_yes_cheque_book_required);
        spinnerzakatApplicable = accountinformationFragment.findViewById(R.id.spinner_zakat_applicable);
        btn_next_step2 = accountinformationFragment.findViewById(R.id.btn_next_step_2);

        iv_menu = accountinformationFragment.findViewById(R.id.imageviewMenu);
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
        btn_next_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    fragment = new PersonalInformationJointFragment();
                    fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    fragmentTransaction.commit();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }
        });

        //Type of current account Spinner
        ArrayAdapter<CharSequence> typeofcuurentaccountAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listtypeofcurrentAccount,android.R.layout.simple_spinner_item);
        typeofcuurentaccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertypeofcurrentAccount.setAdapter(typeofcuurentaccountAdapter);

        //Type of account Spinner
        ArrayAdapter<CharSequence> typeofaccountAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listtypeofAccount,android.R.layout.simple_spinner_item);
        typeofaccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertypeofAccount.setAdapter(typeofcuurentaccountAdapter);

        //Currency Spinner
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listCurrency,android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(typeofcuurentaccountAdapter);

        //Country residential Spinner
        ArrayAdapter<CharSequence> countryresidentialAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listcountryresidentialStatus,android.R.layout.simple_spinner_item);
        countryresidentialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercountryresidentialStatus.setAdapter(typeofcuurentaccountAdapter);

        //Operating instruction Spinner
        ArrayAdapter<CharSequence> operatinginstructionAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listoperationInstruction,android.R.layout.simple_spinner_item);
        operatinginstructionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneroperatingInstructions.setAdapter(typeofcuurentaccountAdapter);

        //Cheque book required Spinner
        ArrayAdapter<CharSequence> chequebookrequiredAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listchequebookRequired,android.R.layout.simple_spinner_item);
        chequebookrequiredAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerchequebookRequired.setAdapter(typeofcuurentaccountAdapter);

        //yes Cheque book required Spinner
        ArrayAdapter<CharSequence> yeschequebookRequiredAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listyestchequebookRequired,android.R.layout.simple_spinner_item);
        yeschequebookRequiredAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryeschequebookRequired.setAdapter(typeofcuurentaccountAdapter);

        //Zakat Applicable Spinner
        ArrayAdapter<CharSequence> zakatapplicableAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listzakatApplicable,android.R.layout.simple_spinner_item);
        zakatapplicableAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerzakatApplicable.setAdapter(typeofcuurentaccountAdapter);

        return accountinformationFragment;
    }

}
