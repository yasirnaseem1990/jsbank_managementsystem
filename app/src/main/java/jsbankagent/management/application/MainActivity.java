package jsbankagent.management.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;

public class MainActivity extends AppCompatActivity {

    private ImageView splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        setupActionBar();
        splashScreen = (ImageView) findViewById(R.id.img_splashScreen);
        splashScreen.setVisibility(View.VISIBLE);
        new DataHandler(MainActivity.this);
        Message msg = new Message();
        msg.what = -1;
        guiHandler.sendMessageDelayed(msg, 1000);
    }

    // Handler sets the navigation of which activity to show at which time
    private Handler guiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == -1) {

                String jsonobprofileData = DataHandler.getStringPreferences(AppConstants.PROFILE_DATA);
                Log.e("jsonobprofileData :", jsonobprofileData);
                if (jsonobprofileData != null && !jsonobprofileData.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        }
    };
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

}
