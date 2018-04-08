package jsbankagent.management.application.asynctasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jsbankagent.management.application.R;
import jsbankagent.management.application.dialogs.ProgressBarDialog;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;
import jsbankagent.management.application.utils.WebServiceConstants;

/**
 * Created by Administrator on 1/3/2018.
 */

public class CnicFrontAsyncTask extends BaseAsyncTask {
    public static ProgressBarDialog progressBarDialogRegister;
    private String userId = "";
    private JSONObject jsonObject = null;
    public  List<NameValuePair> mParams;
    Activity mActivity;
    public CnicFrontAsyncTask(Context context, String route, List<NameValuePair> pp) {
        super(context, route, pp);

        progressBarDialogRegister = new ProgressBarDialog(context);
        progressBarDialogRegister.setTitle(context.getString(R.string.title_progress_dialog));
        progressBarDialogRegister.setMessage(context.getString(R.string.body_progress_dialog));
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
        if(s != null) {
            int intResponse = Integer.parseInt(s);
            Log.e("ResponseCode", "" + intResponse);
            Log.e("response", "" + response);
            Log.e("resultat", "" + resultat);
            try{
                progressBarDialogRegister.dismiss();
                progressBarDialogRegister = null;
            }catch (Exception exception){
                exception.printStackTrace();
            }

            switch (intResponse) {
                case 200 :
                    try{

                        if(resultat !=null && !resultat.isEmpty()){
                            JSONObject objnewAccount = new JSONObject(resultat);
                            userId = objnewAccount.getString("UserId");

                            String jsonObjNewAccount = DataHandler.getStringPreferences(AppConstants.PREFERENCE_APPLICANT_NEW_REGISTRATION);
                            Log.e("jsonObjNewAccount :", jsonObjNewAccount);
                            if (jsonObjNewAccount != null && !jsonObjNewAccount.isEmpty()) {

                                mParams = new ArrayList<NameValuePair>();
                                mParams.add(new BasicNameValuePair("id ", userId));
                                mParams.add(new BasicNameValuePair("account_images ", jsonObject.getString("cnic_back_image")));

                                CnicBackAsyncTask cnicBackAsyncTask = new CnicBackAsyncTask(context, WebServiceConstants.END_POINT_IMAGES,mParams);
                                cnicBackAsyncTask.execute();

                            }
                            
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 403:
                    try{
                        AlertDialog.Builder error_403 = new AlertDialog.Builder(context);
                        error_403.setMessage(context.getResources().getString(R.string.error_login_403)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        error_403.show();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }

                    break;
                case 500:
                    try{
                        AlertDialog.Builder error_500 = new AlertDialog.Builder(context);
                        error_500.setMessage(context.getResources().getString(R.string.error_rate_500)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        error_500.show();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }

                    break;
                // ecuador phone number exception,it has to surpass
                case 593:

                    break;
                //thanks now code is merged complete
            }
        }else {
            try{
                progressBarDialogRegister.dismiss();
                progressBarDialogRegister = null;
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
