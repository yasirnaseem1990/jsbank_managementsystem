package jsbankagent.management.application;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.crashlytics.android.Crashlytics;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.fabric.sdk.android.Fabric;
import jsbankagent.management.application.asynctasks.LoginAsyncTask;
import jsbankagent.management.application.databases.AddAgent;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.AppUtils;
import jsbankagent.management.application.utils.DataHandler;
import jsbankagent.management.application.utils.WebServiceConstants;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "tag";
    public int REQUEST_CODE = 007;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    EditText et_userName,et_Password;
    private String userName = "";
    private String password = "";
    Button btn_Login;
    public static Context mAppcontext;
    public static  List<NameValuePair> mParams;
    AddAgent addAgent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAppcontext = getApplicationContext();
        new DataHandler(mAppcontext);
        Fabric.with(this, new Crashlytics());
        setupActionBar();

        addAgent = new AddAgent(LoginActivity.this);
        addAgent.open();
        et_userName =  findViewById(R.id.et_username);
        et_Password =  findViewById(R.id.et_password);
        btn_Login =  findViewById(R.id.btn_login);
        if (checkAndRequestPermissions()) {
        }

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkFields()){
                    Login();
                }
            }
        });

        String db = DebugDB.getAddressLog();
        Log.e("db",db);
    }

    private void Login() {
        try{
            if(isNetworkAvailable()){
                mParams = new ArrayList<NameValuePair>();
                mParams.add(new BasicNameValuePair("user_name", et_userName.getText().toString().trim().toLowerCase()));
                mParams.add(new BasicNameValuePair("password", et_Password.getText().toString().trim()));
                JSONObject jsonObject = new JSONObject();
                if (jsonObject != null) {

                    try {
                        jsonObject.put("email", et_userName.getText().toString().trim().toLowerCase());
                        jsonObject.put("last_name", et_Password.getText().toString().trim());

                        DataHandler.updatePreferences(AppConstants.LOGIN_KEY, jsonObject.toString());

                        Log.e("PARAMS>", "" + mParams);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.e("PARAMS>", "" + mParams);
                LoginAsyncTask registerAsyncTask = new LoginAsyncTask(LoginActivity.this, WebServiceConstants.END_POINT_LOGIN, mParams,LoginActivity.this);
                registerAsyncTask.execute();

            }else {
                /*Intent intent =  new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);*/
                AlertDialog.Builder error_No_Internet = new AlertDialog.Builder(this);
                error_No_Internet.setMessage(this.getResources().getString(R.string.error_No_Internet)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                error_No_Internet.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // Method to request and check for permissions from  user.
    private boolean checkAndRequestPermissions() {
        try {
            int camerapermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            int permissionexternalStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            int permissioncoarseLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (camerapermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA);
            }
            if (permissionexternalStorage != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (permissioncoarseLocation != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add((Manifest.permission.ACCESS_COARSE_LOCATION));
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }

        }catch (Exception e){

        }
        return true;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.e(TAG, "Permission callBack Called");
        try{


            switch (requestCode) {
                case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                    Map<String, Integer> permission = new HashMap<>();
                    permission.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                    permission.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                    permission.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
                    permission.put(Manifest.permission.SEND_SMS, PackageManager.PERMISSION_GRANTED);
                    permission.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                    permission.put(Manifest.permission.CALL_PHONE,PackageManager.PERMISSION_GRANTED);
                    permission.put(Manifest.permission.READ_PHONE_STATE,PackageManager.PERMISSION_GRANTED);
                    if (grantResults.length > 0) {
                        for (int i = 0; i < permissions.length; i++)
                            permission.put(permissions[i], grantResults[i]);
                        //check for all permission
                        if (permission.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && permission.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && permission.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && permission.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ) {
                            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "Permission are not granted");
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA )) {
                                showDialogOK("We Need Camera Permission for Taking the Picture", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        switch (i) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                checkAndRequestPermissions();
                                                break;
//                                        case DialogInterface.BUTTON_NEGATIVE:
//                                            finish();
//                                            break;
                                        }
                                    }
                                });
                            }else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                                showDialogOK("We Need Location Permission for getting Latitude and Longitude through GPS", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        switch (i) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                checkAndRequestPermissions();
                                                break;
//                                        case DialogInterface.BUTTON_NEGATIVE:
//                                            finish();
                                        }
                                    }
                                });
                            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                showDialogOK("We Need Write External Storage Permission for Saving the Image", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        switch (i) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                checkAndRequestPermissions();
                                                break;
//                                        case DialogInterface.BUTTON_NEGATIVE:
//                                            finish();
                                        }
                                    }
                                });
                            }
                            else {
                                explain("You need to give mandatory permissions to continue. Do you want to go to app settings?");
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            Log.e(TAG, "onRequestPermissionsResult: ",e );
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && checkAndRequestPermissions()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            checkAndRequestPermissions();
            //Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", okListener)
                .create()
                .show();
    }

    private void explain(String msg) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        //  permissionsclass.requestPermission(type,code);
                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:jsbankagent.management.application"));
                        startActivityForResult(intent, REQUEST_CODE);
                        //startActivityForResult(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:com.studio.barefoot.freeedrivebeacononlyapp")));
                    }
                });
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                        finish();
//                    }
//                });
        dialog.show();
    }
    @SuppressLint("RestrictedApi")
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.custom_toolbarr);
            actionBar.setShowHideAnimationEnabled(true);
            //  setListenerForActionBarCustomView(actionBarView);
        }
    }

    // for the checking the field of the register forms
    public boolean checkFields() {
        et_userName.setError(null);
        et_Password.setError(null);

        boolean cancel = false;
        View focusView = null;
        userName = et_userName.getText().toString().toLowerCase().trim().replaceAll("\\s+$", "");
        password = et_Password.getText().toString().trim().replaceAll("\\s+$", "");

        if (TextUtils.isEmpty(userName)) {

            et_userName.setError(getString(R.string.error_field_required));
            focusView = et_userName;
            cancel = true;
        } else if (!AppUtils.emailValidator(userName)) {

            et_userName.setError(getString(R.string.valid_email));
            focusView = et_userName;
            cancel = true;
        } else if (TextUtils.isEmpty(password)){
            et_Password.setError(getString(R.string.error_field_required));
            focusView = et_Password;
            cancel = true;
        }
        if (cancel) {

            focusView.requestFocus();

        }
        return cancel;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
