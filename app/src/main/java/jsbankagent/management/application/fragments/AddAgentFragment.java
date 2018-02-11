package jsbankagent.management.application.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.crashlytics.android.Crashlytics;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


import io.fabric.sdk.android.Fabric;
import jsbankagent.management.application.R;
import jsbankagent.management.application.asynctasks.AddAgentAsyncTask;
import jsbankagent.management.application.databases.AddAgent;
import jsbankagent.management.application.dialogs.TurOnGpsDialog;
import jsbankagent.management.application.interfaces.PendingUploadsAsyncResponse;
import jsbankagent.management.application.model.AddAgents;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.AppUtils;
import jsbankagent.management.application.utils.DataHandler;
import jsbankagent.management.application.utils.WebServiceConstants;

import static jsbankagent.management.application.HomeActivity.drawer;
import static jsbankagent.management.application.fragments.PendingUploadsFragment.pendingformFalag;


public class AddAgentFragment extends Fragment implements PendingUploadsAsyncResponse {
    View addagentFragment;
    Button btn_capturePicture;
    public static ImageView imageView;
    private final int CAMERA_REQUEST = 420;
    public static EditText et_agentId, agentName, agentAddress, et_comments, agentProvince, pictureeditext;
    public static AutoCompleteTextView agentCity;
    Button btnformSubmit;
    public static String picturePath = "";
    String ba1;
    Uri selectedImage;
    private Uri fileUri;
    AddAgent addAgent;
    Toolbar toolbar;
    public static List<NameValuePair> mParams;
    ImageView iv_menu;
    String userId;
    int cityId = 0;
    int provinceId = 0;
    String cityName = "";
    String provinceName = "";
    private String userName = "";
    private String password = "";
    private String address = "";
    private String comments = "";
    double lng = 0.0;
    double lat = 0.0;
    private TurOnGpsDialog turOnGpsDialog;
    public LocationManager mLocationManager;
    Location lastLocation = null;
    private String provider;
    ArrayAdapter adapter = null;
    public AddAgentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/
    }

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addagentFragment = inflater.inflate(R.layout.fragment_add_agent, container, false);
        Fabric.with(getActivity(), new Crashlytics());
        /*lastLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);*/
        toolbar = addagentFragment.findViewById(R.id.topToolbar);
        iv_menu = addagentFragment.findViewById(R.id.imageviewMenu);
        addAgent = new AddAgent(getActivity());
        addAgent.open();
       /* btn_capturePicture = addagentFragment.findViewById(R.id.btn_takepicture);*/
        imageView = addagentFragment.findViewById(R.id.imagview);
        pictureeditext = addagentFragment.findViewById(R.id.pictureview);
        et_agentId = addagentFragment.findViewById(R.id.agent_id);
        agentName = addagentFragment.findViewById(R.id.agent_name);
        agentAddress = addagentFragment.findViewById(R.id.edittext_address);
        agentCity = addagentFragment.findViewById(R.id.edittext_city);
        agentProvince = addagentFragment.findViewById(R.id.edittext_province);
        et_comments = addagentFragment.findViewById(R.id.et_comments);
        btnformSubmit = addagentFragment.findViewById(R.id.btnsave);
        agentProvince.setEnabled(false);

        try {
            final String[] namaProvDB = addAgent.SelectAllDataCity();
            adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, namaProvDB);
            agentCity.setAdapter(adapter);
            agentCity.setThreshold(1);
            agentCity.dismissDropDown();

            agentCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    cityName = String.valueOf(parent.getItemAtPosition(position));
                    cityId = addAgent.getCityId(cityName);
                    provinceName = addAgent.getProvinceName(cityId);
                    if (provinceName != null & !provinceName.isEmpty()) {
                        agentProvince.setText(provinceName);
                        provinceId = addAgent.getProvinceId(provinceName);
                    }
               /* agentCity.setFocusableInTouchMode(true);
                agentCity.requestFocus();*/
                    Toast.makeText(getActivity(), cityName, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //todo
        turOnGpsDialog = new TurOnGpsDialog(getContext());

        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        provider = mLocationManager.getBestProvider(new Criteria(), false);
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
        }
        lastLocation = mLocationManager.getLastKnownLocation(provider);
        /*btn_capturePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                *//*cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);*//*
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        explain("You need to give mandatory permissions to continue. Do you want to go to app settings?");
                    } else {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                /*cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);*/
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        btnformSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkFields()) {
                    try {
                        if (AppUtils.checkIfGpsIsEnabled(getActivity())) {
                            submitForm();
                        } else {
                            try {
                                if (turOnGpsDialog != null && turOnGpsDialog.isShowing()) {
                                    return;
                                } else {
                                    turOnGpsDialog.show();

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } catch (OutOfMemoryError e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
        return addagentFragment;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK && data != null) {

            try {
                /*selectedImage = data.getData();*/

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                selectedImage = getImageUri(getActivity(), bitmap);
                if (selectedImage != null) {
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    imageView.setImageBitmap(bitmap);


                    Bitmap bm = bitmap;

                    int origWidth = bm.getWidth();
                    int origHeight = bm.getHeight();
                    final int destWidth = 150;//or the width you need
                    if (origWidth > destWidth) {
                        // picture is wider thanwe want it, we calculate its target height
                        int destHeight = origHeight / (origWidth / destWidth);
                        // we create an scaled bitmap so it reduces the image, not just trim it
                        Bitmap b2 = Bitmap.createScaledBitmap(bm, destWidth, destHeight, false);
                /*Bitmap bm = BitmapFactory.decodeResource(getResources(),  R.drawable.freeedrive_newlogo_t);*/
               /* imageView.setImageBitmap(bm);*/
                        Log.e("BitMap", ":" + bm);
            /*imageView.setImageBitmap(bm);*/
                /*ByteArrayOutputStream bao = new ByteArrayOutputStream();
                b2.compress(Bitmap.CompressFormat.JPEG, 100, bao);*/
                        File file = new File(picturePath);
                        FileOutputStream fOut;
                        try {
                            fOut = new FileOutputStream(file);
                            b2.compress(Bitmap.CompressFormat.PNG, 0, fOut);
                            fOut.flush();
                            fOut.close();
                            b2.recycle();
                            b2.recycle();
                            picturePath = file.getAbsolutePath();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    Log.e("Selected Image", ":" + selectedImage);
                } else {
                    Toast.makeText(getActivity(), "Application is not working properly on your device, please contact to JS Bank Headquarter ", Toast.LENGTH_LONG).show();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Application is not working properly on your device, please contact to JS Bank Headquarter", Toast.LENGTH_LONG).show();
        }
    }

    public void submitForm() {
        // Image location URL
        Log.e("path", "----------------" + picturePath);
        if (lat != 0.0 && lng != 0.0) {
            lat = 0.0;
            lng = 0.0;
        }

        lat = lastLocation.getLatitude();
        lng = lastLocation.getLongitude();
        Log.e("Latitude :" + lat, "Longitude :" + lng);
        AddAgents addAgents = new AddAgents();

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

            /*String newPath = picturePath;*/
                /*Bitmap bm = BitmapFactory.decodeResource(getResources(),  R.drawable.freeedrive_newlogo_t);*/
               /* imageView.setImageBitmap(bm);*/
            /*Log.e("BitMap", ":" + bm);*/
            /*imageView.setImageBitmap(bm);*/
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            Bitmap bitmapnew = BitmapFactory.decodeFile(picturePath);

            bitmapnew.compress(Bitmap.CompressFormat.PNG, 0, bao);
            byte[] ba = bao.toByteArray();
            Log.e("ByteArray", ":" + ba);
            ba1 = Base64.encodeToString(ba, Base64.DEFAULT);
            Log.e("base64", "-----" + picturePath);

            addAgents.setAgentId(et_agentId.getText().toString());
            addAgents.setAgentName(agentName.getText().toString());
            addAgents.setAgentAddress(agentAddress.getText().toString());
            addAgents.setCityId(cityId);
            addAgents.setProvinceId(provinceId);
            addAgents.setAgentComments(et_comments.getText().toString());
            addAgents.setImagePath(picturePath);
            addAgents.setLatitude(String.valueOf(lat));
            addAgents.setLongitude(String.valueOf(lng));
            int formId = addAgent.insertAgents(addAgents);
            DataHandler.updatePreferences(AppConstants.FORM_ID, formId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (isNetworkAvailable()) {
                mParams = new ArrayList<NameValuePair>();
                mParams.add(new BasicNameValuePair("agent_id", et_agentId.getText().toString().trim()));
                mParams.add(new BasicNameValuePair("agent_name", agentName.getText().toString().trim()));
                mParams.add(new BasicNameValuePair("agent_address", agentAddress.getText().toString().trim()));
                if (cityId != 0) {
                    String paramcityId = String.valueOf(cityId);
                    mParams.add(new BasicNameValuePair("agent_city", paramcityId));
                }
                if (provinceId != 0) {
                    String paramprovinceId = String.valueOf(provinceId);
                    mParams.add(new BasicNameValuePair("agent_province", paramprovinceId));
                }

                String latititude = String.valueOf(lat);
                String longitude = String.valueOf(lng);
                Log.e("latititude", latititude);
                Log.e("longitude", longitude);
                mParams.add(new BasicNameValuePair("lng", longitude));
                mParams.add(new BasicNameValuePair("lat", latititude));

                mParams.add(new BasicNameValuePair("comments", et_comments.getText().toString().trim()));
                mParams.add(new BasicNameValuePair("agent_key", "jsblthi5i58em0"));
                mParams.add(new BasicNameValuePair("image", ba1));
                mParams.add(new BasicNameValuePair("user_id", userId));
                Log.e("PARAMS>", "" + mParams);
                /*et_agentId.setText("");
                agentName.setText("");
                agentAddress.setText("");
                agentCity.setText("");
                agentProvince.setText("");
                et_comments.setText("");
                imageView.setImageDrawable(null);*/
                pendingformFalag = false;
                AddAgentAsyncTask addAgentAsyncTask = new AddAgentAsyncTask(getActivity(), WebServiceConstants.END_POINT_ADD_AGENT, mParams);
                addAgentAsyncTask.execute();

            } else {
                AlertDialog.Builder error_No_Internet = new AlertDialog.Builder(getActivity());
                error_No_Internet.setMessage(this.getResources().getString(R.string.error_No_Internet)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_agentId.setText("");
                        agentName.setText("");
                        agentAddress.setText("");
                        agentCity.setText("");
                        agentProvince.setText("");
                        et_comments.setText("");
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.camera_icon));
                        picturePath = "";
                        dialog.dismiss();
                    }
                });
                error_No_Internet.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lng = 0.0;
        lat = 0.0;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public boolean checkFields() {
        et_agentId.setError(null);
        agentName.setError(null);
        agentCity.setError(null);
        agentAddress.setError(null);
        et_comments.setError(null);

        boolean cancel = false;
        View focusView = null;
        userName = et_agentId.getText().toString().toLowerCase().trim().replaceAll("\\s+$", "");
        password = agentName.getText().toString().trim().replaceAll("\\s+$", "");
        address = agentAddress.getText().toString();
        comments = et_comments.getText().toString();

        try {
            if (TextUtils.isEmpty(userName)) {

                et_agentId.setError(getString(R.string.error_field_required));
                focusView = et_agentId;
                cancel = true;
            } else if (TextUtils.isEmpty(password)) {
                agentName.setError(getString(R.string.error_field_required));
                focusView = agentName;
                cancel = true;
            } else if (picturePath.isEmpty()) {
                Toast.makeText(getActivity(), "Cannot Proceed! Please Take the picture.", Toast.LENGTH_SHORT).show();
                pictureeditext.setError(getString(R.string.error_pictureemtpy));
                focusView = pictureeditext;
                cancel = true;
            } else if (TextUtils.isEmpty(address)) {
                agentAddress.setError(getString(R.string.error_field_required));
                focusView = agentAddress;
                cancel = true;
            } else if (TextUtils.isEmpty(comments)) {
                et_comments.setError(getString(R.string.error_field_required));
                focusView = et_comments;
                cancel = true;
            } else if (cityId == 0) {
                agentCity.setError(getString(R.string.error_invalidcity));
                focusView = agentCity;
                cancel = true;
                agentCity.setText("");
                //Toast.makeText(getActivity(), "Invalid City! Please select the City from the drop down list. If the desired city is not listed, please select the relevant District.", Toast.LENGTH_SHORT).show();
            }
            if (cancel) {

                focusView.requestFocus();

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return cancel;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        picturePath = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(picturePath);
    }



    /*public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }*/

    private void explain(String msg) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(getActivity());
        dialog.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:jsbankagent.management.application"));
                        startActivityForResult(intent, CAMERA_REQUEST);
                    }
                });
        dialog.show();
    }

    @Override
    public void processFinish(String output) {
        return;
    }
}
