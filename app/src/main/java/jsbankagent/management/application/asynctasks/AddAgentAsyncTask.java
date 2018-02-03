package jsbankagent.management.application.asynctasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.BaseAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.List;

import jsbankagent.management.application.HomeActivity;
import jsbankagent.management.application.R;
import jsbankagent.management.application.databases.AddAgent;
import jsbankagent.management.application.dialogs.ProgressBarDialog;
import jsbankagent.management.application.fragments.AddAgentFragment;
import jsbankagent.management.application.interfaces.PendingUploadsAsyncResponse;
import jsbankagent.management.application.utils.AppConstants;
import jsbankagent.management.application.utils.DataHandler;


import static jsbankagent.management.application.fragments.PendingUploadsFragment.autoagentId;
/*import static jsbankagent.management.application.fragments.PendingUploadsFragment.listViewPendingUploads;*/
/*import static jsbankagent.management.application.fragments.PendingUploadsFragment.pendingUploadsAdapter;*/
import static jsbankagent.management.application.fragments.PendingUploadsFragment.pendingformFalag;
import static jsbankagent.management.application.fragments.PendingUploadsFragment.pendinglist;

/**
 * Created by Administrator on 1/5/2018.
 */

public class AddAgentAsyncTask extends BaseAsyncTask {
    public static ProgressBarDialog progressBarDialogRegister;

    public AddAgentAsyncTask(Context context, String route, List<NameValuePair> pp) {
        super(context, route, pp);
        /*this.asyncResponse = (PendingUploadsAsyncResponse) context;*/
        progressBarDialogRegister = new ProgressBarDialog(context);
        progressBarDialogRegister.setTitle(context.getString(R.string.title_progress_dialog));
        progressBarDialogRegister.setMessage(context.getString(R.string.form_submitting));
        progressBarDialogRegister.show();
    }

   /* public AddAgentAsyncTask(Context context, String route, List<NameValuePair> pp) {
        super(context, route, pp);

        *//*this.asyncResponse = (PendingUploadsAsyncResponse) context;*//*
        progressBarDialogRegister = new ProgressBarDialog(context);
        progressBarDialogRegister.setTitle(context.getString(R.string.title_progress_dialog));
        progressBarDialogRegister.setMessage(context.getString(R.string.form_submitting));
        progressBarDialogRegister.show();
    }*/

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
            /*asyncResponse.processFinish(s);*/
            try {
                progressBarDialogRegister.dismiss();
                progressBarDialogRegister = null;
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            switch (intResponse) {
                case 200:
                    try {

                        if (s != null && !s.isEmpty()) {
                            AddAgent addAgent = new AddAgent(context);
                            addAgent.open();
                            /*if(pendingformFalag){
                                pendingformFalag = false;
                                if(autoagentId != 0){
                                    addAgent.updateFormStatus(autoagentId);
                                    pendingformFalag = true;
                                    pendinglist.remove(0);
                                  *//*  try{
                                        listViewPendingUploads.getAdapter().notifyDataSetChanged();
                                        //pendingUploadsAdapter.notifyDataSetChanged();
                                    }catch (Exception e){

                                    }*//*

                                }
                            }*/
                           /* else{
                            int formId = DataHandler.getIntPreferences(AppConstants.FORM_ID);
                            if (formId != 0) {
                                addAgent.updateFormStatus(formId);
                            }

                            }*/
                            int formId = DataHandler.getIntPreferences(AppConstants.FORM_ID);
                            if (formId != 0) {
                                addAgent.updateFormStatus(formId);
                            }
                            try {
                                /*if(pendingformFalag){
                                   return;
                                }*/

                                AlertDialog.Builder error_403 = new AlertDialog.Builder(context);
                                error_403.setMessage(context.getResources().getString(R.string.error_login_200)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try{
                                            AddAgentFragment.et_agentId.setText("");
                                            AddAgentFragment.agentName.setText("");
                                            AddAgentFragment.agentAddress.setText("");
                                            AddAgentFragment.agentCity.setText("");
                                            AddAgentFragment.agentProvince.setText("");
                                            AddAgentFragment.et_comments.setText("");
                                            AddAgentFragment.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.camera_icon));
                                            dialog.dismiss();
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }

                                    }
                                });
                                error_403.show();

                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            /*Intent intent = new Intent(context, HomeActivity.class);
                            context.startActivity(intent);*/

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
                                try{
                                    AddAgentFragment.et_agentId.setText("");
                                    AddAgentFragment.agentName.setText("");
                                    AddAgentFragment.agentAddress.setText("");
                                    AddAgentFragment.agentCity.setText("");
                                    AddAgentFragment.agentProvince.setText("");
                                    AddAgentFragment.et_comments.setText("");
                                    AddAgentFragment.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.camera_icon));
                                    dialog.dismiss();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        error_404.show();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    break;
                case 504:
                    try {
                        /*if(pendingformFalag){
                            try {
                                progressBarDialogRegister.dismiss();
                                progressBarDialogRegister = null;
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }*/

                            AlertDialog.Builder error_504 = new AlertDialog.Builder(context);
                            error_504.setMessage(context.getResources().getString(R.string.error_login_504)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try{
                                        AddAgentFragment.et_agentId.setText("");
                                        AddAgentFragment.agentName.setText("");
                                        AddAgentFragment.agentAddress.setText("");
                                        AddAgentFragment.agentCity.setText("");
                                        AddAgentFragment.agentProvince.setText("");
                                        AddAgentFragment.et_comments.setText("");
                                        AddAgentFragment.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.camera_icon));
                                        dialog.dismiss();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                            error_504.show();

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
}
