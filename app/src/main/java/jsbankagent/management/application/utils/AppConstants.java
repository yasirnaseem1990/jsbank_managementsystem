package jsbankagent.management.application.utils;

import org.json.JSONObject;

/**
 * Created by Administrator on 1/3/2018.
 */

public class AppConstants {
    public final static String REG_EMIAL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";;
    public static final String LOGIN_KEY ="LOGIN_KEY";
    public static final String PROFILE_DATA = "PROFILE_DATA";
    public static final String FORM_ID = "FORM_ID";
    public static final String NEW_ACCOUNT_ID = "NEW_ACCOUNT_ID";
    public static JSONObject registrationObject = null;
    public static JSONObject preaccountinfoObject = null;
    public static final String PREFERENCE_APPLICANT_NEW_REGISTRATION = "PREFERENCE_APPLICANT_NEW_REGISTRATION";
    public static final String PREFERENCE_PRE_ACCOUNT_INFO = "PREFERENCE_PRE_ACCOUNT_INFO";

}
