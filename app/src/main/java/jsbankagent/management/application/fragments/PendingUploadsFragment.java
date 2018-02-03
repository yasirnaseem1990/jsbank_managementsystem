package jsbankagent.management.application.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import jsbankagent.management.application.dialogs.ProgressBarDialog;
import java.util.concurrent.Executor;

import jsbankagent.management.application.R;
import jsbankagent.management.application.adapters.ArchiveAgentAdapter;
import jsbankagent.management.application.adapters.PendingUploadsAdapter;
import jsbankagent.management.application.asynctasks.AddAgentAsyncTask;
import jsbankagent.management.application.asynctasks.PendingUploadAsyncTask;
import jsbankagent.management.application.databases.AddAgent;
import jsbankagent.management.application.interfaces.PendingUploadsAsyncResponse;
import jsbankagent.management.application.model.AddAgents;
import jsbankagent.management.application.model.PendingUploads;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;
import jsbankagent.management.application.utils.WebServiceConstants;

import static jsbankagent.management.application.HomeActivity.drawer;
import static jsbankagent.management.application.asynctasks.PendingUploadAsyncTask.progressBarDialogRegister;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingUploadsFragment extends Fragment implements PendingUploadsAsyncResponse {


    private ArrayList<PendingUploads> pendingUploads = new ArrayList<>();
    public RecyclerView listViewPendingUploads;
    private PendingUploadsAdapter pendingUploadsAdapter;
    LinearLayoutManager mLayoutManager;
    View pendinguploadsFragment;
    Toolbar toolbar;
    ImageView iv_menu;
    Button btn_uploadingPending;
    String ba1;
    String userId;
    String base64 = "";
    String imagepath = "";
    String comments = "";
    int cityid;
    int provinceId;
    public static int autoagentId;
    String agentid = "";
    String agentname = "";
    String agentaddress = "";
    String latitude = "";
    String longitude = "";
    public static boolean pendingformFalag = false;
    public static List<PendingUploads> pendinglist;
    public static List<NameValuePair> mParams;
    public static int pendingRequest = 0;

    public PendingUploadsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pendinguploadsFragment = inflater.inflate(R.layout.fragment_pending_uploads, container, false);
        Fabric.with(getActivity(), new Crashlytics());
        iv_menu = pendinguploadsFragment.findViewById(R.id.imageviewMenu);
        btn_uploadingPending = pendinguploadsFragment.findViewById(R.id.btn_uploadPending);
        getPhoneSafetyRides();
        listViewPendingUploads = (RecyclerView) pendinguploadsFragment.findViewById(R.id.listViewPendingUploads);
        mLayoutManager = new LinearLayoutManager(getActivity());
        listViewPendingUploads.setHasFixedSize(true);
        listViewPendingUploads.setLayoutManager(mLayoutManager);
        listViewPendingUploads.setItemAnimator(new DefaultItemAnimator());
        pendingUploadsAdapter = new PendingUploadsAdapter(pendingUploads);
        listViewPendingUploads.setAdapter(pendingUploadsAdapter);
        pendingUploadsAdapter.notifyDataSetChanged();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listViewPendingUploads.getContext(),
                mLayoutManager.getOrientation());
        listViewPendingUploads.addItemDecoration(dividerItemDecoration);

        btn_uploadingPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jsonobprofileData = DataHandler.getStringPreferences(AppConstants.PROFILE_DATA);
                if (jsonobprofileData != null && !jsonobprofileData.isEmpty()) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonobprofileData);
                        userId = jsonObject.getString("Id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (isNetworkAvailable()) {
                        pendingdataUpload();
                        //pendingUploadsAdapter.notifyDataSetChanged();

                    } else {
                        AlertDialog.Builder error_No_Internet = new AlertDialog.Builder(getActivity());
                        error_No_Internet.setMessage(getActivity().getResources().getString(R.string.error_No_Internet_pending)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        error_No_Internet.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

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

        return pendinguploadsFragment;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void getPhoneSafetyRides() {
        AddAgent agent = new AddAgent(getActivity());
        agent.open();
        pendingUploads = agent.allPendingUploads();
        Log.e("pendingUploads", "" + pendingUploads);
    }

    public String convertBase64(String imagePath) {

    /*    Bitmap bm = BitmapFactory.decodeFile(imagePath);

        int origWidth = bm.getWidth();
        int origHeight = bm.getHeight();
        final int destWidth = 150;//or the width you need
        if (origWidth > destWidth) {
            // picture is wider than we want it, we calculate its target height
            int destHeight = origHeight / (origWidth / destWidth);
            // we create an scaled bitmap so it reduces the image, not just trim it
            Bitmap b2 = Bitmap.createScaledBitmap(bm, destWidth, destHeight, false);





                *//*Bitmap bm = BitmapFactory.decodeResource(getResources(),  R.drawable.freeedrive_newlogo_t);*//*
               *//* imageView.setImageBitmap(bm);*//*
            Log.e("BitMap", ":" + bm);*/
            /*imageView.setImageBitmap(bm);*/
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            Bitmap bitmapnew = BitmapFactory.decodeFile(imagePath);
            bitmapnew.compress(Bitmap.CompressFormat.PNG, 0, bao);
            byte[] ba = bao.toByteArray();
            Log.e("ByteArray", ":" + ba);
            ba1 = Base64.encodeToString(ba, Base64.DEFAULT);
            Log.e("base64", "-----" + imagePath);


        return ba1;
    }

    public void pendingdataUpload() {
        try {
            AddAgent agent = new AddAgent(getActivity());
            agent.open();
            pendingformFalag = true;
            pendinglist = agent.allPendingUploads();
            PendingUploads uploads;
            int pendinglistSize = pendinglist.size();
            if(pendinglistSize >0) {

                if(pendingRequest <= pendinglistSize -1) {


                    uploads = pendinglist.get(pendingRequest);


                    autoagentId = uploads.getAutoagentId();
                    agentid = uploads.getAgentId();
                    agentname = uploads.getAgentName();
                    agentaddress = uploads.getAgentAddress();
                    cityid = uploads.getCityId();
                    provinceId = uploads.getProvinceId();
                    comments = uploads.getAgentComments();
                    imagepath = uploads.getImagePath();
                    latitude = uploads.getLatitude();
                    longitude = uploads.getLongitude();
                    base64 = convertBase64(imagepath);

                    mParams = new ArrayList<NameValuePair>();

                    mParams.add(new BasicNameValuePair("agent_id", String.valueOf(agentid)));
                    mParams.add(new BasicNameValuePair("agent_name", agentname));
                    mParams.add(new BasicNameValuePair("agent_address", agentaddress));
                    if (cityid != 0) {
                        String paramcityId = String.valueOf(cityid);
                        mParams.add(new BasicNameValuePair("agent_city", paramcityId));
                    }
                    if (provinceId != 0) {
                        String paramprovinceId = String.valueOf(provinceId);
                        mParams.add(new BasicNameValuePair("agent_province", paramprovinceId));
                    }
                    mParams.add(new BasicNameValuePair("lng", longitude));
                    mParams.add(new BasicNameValuePair("lat", latitude));
                    mParams.add(new BasicNameValuePair("comments", comments));
                    mParams.add(new BasicNameValuePair("agent_key", "jsblthi5i58em0"));
                    mParams.add(new BasicNameValuePair("image", base64));
                    mParams.add(new BasicNameValuePair("user_id", userId));
                    Log.e("PARAMS>", "" + mParams);
                    PendingUploadAsyncTask pendingUploadAsyncTask = new PendingUploadAsyncTask(getActivity(), WebServiceConstants.END_POINT_ADD_AGENT, mParams, PendingUploadsFragment.this);
                    pendingUploadAsyncTask.execute();
                    Log.e("log", "Agent CityId:" + uploads.getCityId());
                    agent.close();
                }else{
                    pendingRequest = 0;
                    try {
                        progressBarDialogRegister.dismiss();
                        progressBarDialogRegister = null;
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }else{
                pendingRequest =0;
                try {
                    progressBarDialogRegister.dismiss();
                    progressBarDialogRegister = null;
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void processFinish(String output) {
        Log.e("OutPut", output);
        try {
            if(!output.equalsIgnoreCase("200")){
                pendingRequest = pendingRequest++;
                pendingdataUpload();
            }else{
                pendingdataUpload();
            }
            getPhoneSafetyRides();
            pendingUploadsAdapter = new PendingUploadsAdapter(pendingUploads);
            listViewPendingUploads.setAdapter(pendingUploadsAdapter);
            pendingUploadsAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
