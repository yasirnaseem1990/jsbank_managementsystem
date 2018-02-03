package jsbankagent.management.application.utils;

import android.content.Context;
import android.location.LocationManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.LOCATION_SERVICE;
import static jsbankagent.management.application.LoginActivity.mAppcontext;


/**
 * Created by Administrator on 1/3/2018.
 */

public class AppUtils {

    public static Boolean gpsenabled = false;
    public static boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = AppConstants.REG_EMIAL;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static  boolean checkIfGpsIsEnabled(Context context) throws Exception {
        LocationManager locManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        boolean gps_enabled = false;
        if (locManager != null) {
            gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            //Log.i("Adneom","gps : "+gps_enabled);

            AppUtils.gpsenabled = true;
            if (!gps_enabled) {
                AppUtils.gpsenabled = false;
            }
        }
        return gps_enabled;
    }

}
