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
public class DebitCardInformation extends Fragment {

    View debitcardFragment;
    Button btn_next_step6;
    Fragment fragment;
    ImageView iv_menu;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Spinner spinnerdebitcardRequest;
    public DebitCardInformation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        debitcardFragment = inflater.inflate(R.layout.fragment_debit_card_information, container, false);
        btn_next_step6 = debitcardFragment.findViewById(R.id.btn_next_step_6);
        spinnerdebitcardRequest = debitcardFragment.findViewById(R.id.spinner_debit_card_request);

        iv_menu = debitcardFragment.findViewById(R.id.imageviewMenu);
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
        btn_next_step6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    fragment = new SupplementaryCardFragment();
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
            //Debit card request Spinner
            ArrayAdapter<CharSequence> debitcardrequestAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.listdebitcardRequest,android.R.layout.simple_spinner_item);
            debitcardrequestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerdebitcardRequest.setAdapter(debitcardrequestAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return debitcardFragment;
    }

}
