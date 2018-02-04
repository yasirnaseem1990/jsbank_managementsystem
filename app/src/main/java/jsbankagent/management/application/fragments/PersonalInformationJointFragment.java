package jsbankagent.management.application.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import jsbankagent.management.application.R;

import static jsbankagent.management.application.HomeActivity.drawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInformationJointFragment extends Fragment {

    View personalinfojointFragment;
    ImageView iv_menu;
    Spinner spinnerpersonaljointGender,spinnerpersonaljointusCitizen,spinnerpersonaljointmaritalStatus,spinnerpersonaljointQualification,
            spinnerpersonaljointProfession;

    public PersonalInformationJointFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        personalinfojointFragment = inflater.inflate(R.layout.fragment_personal_information_joint, container, false);

        spinnerpersonaljointGender = personalinfojointFragment.findViewById(R.id.spinner_personal_info_gender);
        spinnerpersonaljointusCitizen = personalinfojointFragment.findViewById(R.id.spinner_personal_info_us_citizen);
        spinnerpersonaljointmaritalStatus = personalinfojointFragment.findViewById(R.id.spinner_personal_info_marital_status);
        spinnerpersonaljointQualification = personalinfojointFragment.findViewById(R.id.spinner_personal_info_qualification);
        spinnerpersonaljointProfession = personalinfojointFragment.findViewById(R.id.spinner_personal_info_profession);




        iv_menu = personalinfojointFragment.findViewById(R.id.imageviewMenu);
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
        ArrayAdapter<CharSequence> personalinfojointgenderAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listGender,android.R.layout.simple_spinner_item);
        personalinfojointgenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointGender.setAdapter(personalinfojointgenderAdapter);

        //us citizen
        ArrayAdapter<CharSequence> personalinfojointuscitizenAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listusCitizen,android.R.layout.simple_spinner_item);
        personalinfojointuscitizenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointusCitizen.setAdapter(personalinfojointuscitizenAdapter);

        //martial status Spinner
        ArrayAdapter<CharSequence> personalinfojointmaritalstatusAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listmaritalStatus,android.R.layout.simple_spinner_item);
        personalinfojointmaritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointmaritalStatus.setAdapter(personalinfojointmaritalstatusAdapter);

        //qualification Spinner
        ArrayAdapter<CharSequence> personalinfojointqualificationAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listQualification,android.R.layout.simple_spinner_item);
        personalinfojointqualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointQualification.setAdapter(personalinfojointqualificationAdapter);

        //qualification Spinner
        ArrayAdapter<CharSequence> personalinfojointprofessionAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.listProfession,android.R.layout.simple_spinner_item);
        personalinfojointprofessionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonaljointProfession.setAdapter(personalinfojointprofessionAdapter);


        return personalinfojointFragment;
    }

}
