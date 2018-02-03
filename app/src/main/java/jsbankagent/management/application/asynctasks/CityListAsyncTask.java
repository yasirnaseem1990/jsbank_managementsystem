package jsbankagent.management.application.asynctasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import jsbankagent.management.application.R;
import jsbankagent.management.application.databases.AddAgent;
import jsbankagent.management.application.dialogs.ProgressBarDialog;
import jsbankagent.management.application.model.CitiesAndProvince;

/**
 * Created by Administrator on 1/6/2018.
 */

public class CityListAsyncTask extends BaseAsyncTask {
    public static ProgressBarDialog progressBarDialogRegister;
    JSONArray jsonArray;

    public CityListAsyncTask(Context context, String route) {
        super(context, route);
        progressBarDialogRegister = new ProgressBarDialog(context);
        progressBarDialogRegister.setTitle(context.getString(R.string.title_progress_dialog));
        progressBarDialogRegister.setMessage(context.getString(R.string.form_submitting));
        progressBarDialogRegister.show();
    }


    /**
     * AsyncTask method basic calls during a request, calls the parent's method.
     */
    protected String doInBackground(String... params) {

        return "";
    }

    /**
     * AsyncTask method basic calls after a request.
     */
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            int intResponse = Integer.parseInt(s);
            Log.e("ResponseCode", "" + intResponse);
            Log.e("response", "" + response);
            Log.e("resultat", "" + resultat);
            JSONObject objAccount= new JSONObject();
            try {
                progressBarDialogRegister.dismiss();
                progressBarDialogRegister = null;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            AddAgent tmp = new AddAgent(context);


            switch (intResponse) {
                case 200:
                    try {

                        if (resultat != null && !resultat.isEmpty()) {
                            jsonArray = new JSONArray(resultat);
                            if (jsonArray.length() > 0) {
                                tmp.open();
                                for(int i=0;i<=jsonArray.length();i++){

                                    CitiesAndProvince citiesAndProvince = new CitiesAndProvince();

                                    objAccount = jsonArray.getJSONObject(i);
                                    citiesAndProvince.setCityId(objAccount.getInt("CityId"));
                                    citiesAndProvince.setCityName(objAccount.getString("CityName"));
                                    citiesAndProvince.setProvinceId(objAccount.getInt("ProvinceId"));
                                    citiesAndProvince.setProvinceName(objAccount.getString("ProvinceName"));

                                    InsertCitiesAndProvince(objAccount.getInt("CityId"), objAccount.getString("CityName"), objAccount.getInt("ProvinceId"), objAccount.getString("ProvinceName"));
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 404:
                    try {
                        AlertDialog.Builder error_404 = new AlertDialog.Builder(context);
                        error_404.setMessage(context.getResources().getString(R.string.error_login_404)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        error_404.show();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    break;
                case 500:
                    try {
                        AlertDialog.Builder error_500 = new AlertDialog.Builder(context);
                        error_500.setMessage(context.getResources().getString(R.string.error_rate_500)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        error_500.show();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    break;
                // ecuador phone number exception,it has to surpass
                case 593:

                    break;
                //thanks now code is merged complete
            }
        } else {
            try {
                progressBarDialogRegister.dismiss();
                progressBarDialogRegister = null;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void InsertCitiesAndProvince(int city_id, String city_name, int province_id, String province_name) {
        AddAgent tmp = new AddAgent(context);
        tmp.open();
        tmp.insertCitiesAndProvince(city_id,city_name,province_id,province_name);
        tmp.close();
    }
}

