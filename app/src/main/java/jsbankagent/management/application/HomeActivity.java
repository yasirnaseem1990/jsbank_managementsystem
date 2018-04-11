package jsbankagent.management.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.amitshekhar.DebugDB;
import com.crashlytics.android.Crashlytics;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.Fabric;
import jsbankagent.management.application.fragments.AddAgentFragment;
import jsbankagent.management.application.fragments.ArchiveFragment;
import jsbankagent.management.application.fragments.PendingUploadsFragment;
import jsbankagent.management.application.fragments.PersonalInformationFrament;
import jsbankagent.management.application.fragments.PreAccountInfoFragment;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv_agentMangement,tv_systemApplicaton,tv_personName,tv_personDesignation;
    String text = "";
    String text_two = "";
    Toolbar toolbar;
    ImageView iv_menu;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public static DrawerLayout drawer;
    CircleImageView circleImageView;
    Button btn_addAgent,btn_newAccount;
    public LocationManager mLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Fabric.with(this, new Crashlytics());
        setupActionBar();

        new DataHandler(HomeActivity.this);
        tv_agentMangement = findViewById(R.id.txt_agent);
        tv_systemApplicaton = findViewById(R.id.txt_system);
        text = "<font color=#004890>AGENT</font> <font color=#f47b1f>MANAGEMENT</font>";
        text_two = "<font color=#004890>SYSTEM</font> <font color=#f47b1f>APPLICATION</font>";
        tv_agentMangement.setTextSize((float) 30.0);
        tv_agentMangement.setText(Html.fromHtml(text));
        tv_systemApplicaton.setTextSize((float) 30.0);
        tv_systemApplicaton.setText(Html.fromHtml(text_two));
        toolbar = findViewById(R.id.topToolbar);
        iv_menu = findViewById(R.id.imageviewMenu);
        btn_addAgent = findViewById(R.id.btn_add_agent);
        btn_newAccount = findViewById(R.id.btn_newRegistration);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView =  navigationView.getHeaderView(0);
        circleImageView = headerView.findViewById(R.id.profile_image);
        tv_personName = headerView.findViewById(R.id.textview_name);
        tv_personDesignation = headerView.findViewById(R.id.textview_designation);
        navigationView.setNavigationItemSelectedListener(this);
        String db = DebugDB.getAddressLog();
        Log.e("db",db);
        btn_addAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AddAgentFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, fragment);
                // fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        String jsonobprofileData = DataHandler.getStringPreferences(AppConstants.PROFILE_DATA);
        Log.e("jsonobprofileData :", jsonobprofileData);
        if(jsonobprofileData !=null && !jsonobprofileData.isEmpty()){
            try{
                try {
                    JSONObject jsonObject = new JSONObject(jsonobprofileData);
                    String agentName = jsonObject.getString("name");
                    String agentDesignation = jsonObject.getString("designation");
                    String agentpictureUrl = jsonObject.getString("imagePath");
                    Log.e("agentpictureUrl",agentpictureUrl);
                    tv_personName.setText(agentName);
                    tv_personDesignation.setText(agentDesignation);
                    Picasso.with(HomeActivity.this).load(agentpictureUrl).fit().into(circleImageView);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

       drawer = findViewById(R.id.drawer_layout);
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
        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/
        btn_newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    fragment = new PreAccountInfoFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                    /*fragmentTransaction.addToBackStack(null);*/
                    fragmentTransaction.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        moveTaskToBack(true);
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_agent) {

            //Find fragment by tag
            /*Fragment fragment = new AddAgentFragment();*/
            fragment = new AddAgentFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            /*fragmentTransaction.addToBackStack(null);*/
            fragmentTransaction.commit();
            /*Intent intent = new Intent(HomeActivity.this,AddAgentActivity.class);
            startActivity(intent);*/
            // Handle the camera action
        } else if (id == R.id.nav_new_registration){

            fragment = new PreAccountInfoFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            /*fragmentTransaction.addToBackStack(null);*/
            fragmentTransaction.commit();
        }else if (id == R.id.nav_pending_uploads) {

            fragment = new PendingUploadsFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_archive) {
            fragment = new ArchiveFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

           /* Intent intent = new Intent(HomeActivity.this,ArchiveActivity.class);
            startActivity(intent);*/
        } else if (id == R.id.nav_logout) {
            DataHandler.deletePreference(AppConstants.PROFILE_DATA);
            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}
