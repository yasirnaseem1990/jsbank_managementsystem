package jsbankagent.management.application.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;
import jsbankagent.management.application.R;
import jsbankagent.management.application.adapters.ArchiveAgentAdapter;
import jsbankagent.management.application.databases.AddAgent;
import jsbankagent.management.application.model.AddAgents;

import static jsbankagent.management.application.HomeActivity.drawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArchiveFragment extends Fragment {

    private ArrayList<AddAgents> agentsArrayList = new ArrayList<>();
    RecyclerView listViewAddAgent;
    private ArchiveAgentAdapter archiveAgentAdapter;
    LinearLayoutManager mLayoutManager;
    View archiveFragment;
    Toolbar toolbar;
    ImageView iv_menu;
    public ArchiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        archiveFragment =inflater.inflate(R.layout.fragment_archive, container, false);
        Fabric.with(getActivity(), new Crashlytics());
        toolbar = archiveFragment.findViewById(R.id.topToolbar);
        iv_menu = archiveFragment.findViewById(R.id.imageviewMenu);
        getPhoneSafetyRides();
        listViewAddAgent = (RecyclerView) archiveFragment.findViewById(R.id.listViewAddAgent);
        mLayoutManager = new LinearLayoutManager(getActivity());
        listViewAddAgent.setHasFixedSize(true);
        listViewAddAgent.setLayoutManager(mLayoutManager);
        listViewAddAgent.setItemAnimator(new DefaultItemAnimator());
        archiveAgentAdapter = new ArchiveAgentAdapter(agentsArrayList);
        listViewAddAgent.setAdapter(archiveAgentAdapter);
        archiveAgentAdapter.notifyDataSetChanged();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listViewAddAgent.getContext(),
                mLayoutManager.getOrientation());
        listViewAddAgent.addItemDecoration(dividerItemDecoration);

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

        return archiveFragment;
    }

    private void getPhoneSafetyRides() {
        AddAgent agent = new AddAgent(getActivity());
        agent.open();
        agentsArrayList = agent.allAgents();
        Log.e("agentsArrayList", "" + agentsArrayList);
    }

}
