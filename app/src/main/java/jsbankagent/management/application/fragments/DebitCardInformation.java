package jsbankagent.management.application.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jsbankagent.management.application.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DebitCardInformation extends Fragment {


    public DebitCardInformation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debit_card_information, container, false);
    }

}
