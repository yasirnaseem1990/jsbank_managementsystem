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
public class NextOfKinFragment extends Fragment {

    View nextofkinFragment;
    ImageView iv_menu;
    Button btn_next_step4;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Spinner spinnernextkincourtesyTitle;
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
                try{
                    fragment = new BusinessAccountInformationFragment();
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
            //SMS Alerts Spinner
            ArrayAdapter<CharSequence> nextofkinAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.courtesyTitle,android.R.layout.simple_spinner_item);
            nextofkinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnernextkincourtesyTitle.setAdapter(nextofkinAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return nextofkinFragment;
    }

}
