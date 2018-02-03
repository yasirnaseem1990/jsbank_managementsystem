package jsbankagent.management.application.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import jsbankagent.management.application.utils.WebServiceConstants;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 1/6/2018.
 */

public class BaseGetAsyncTask extends AsyncTask<Void,Void,String> {

    /**
     * Represents the response from Server
     */
    protected String response;

    /**
     * Represnts the JSON from server
     */
    protected String resultat;

    /**
     * Represent the basic URL
     */
    protected String mUrl = WebServiceConstants.WEBSERVICE_URL_PREFIX;

    /**
     * Represents the Activity which will execute the request.
     */
    protected Context context;

    /**
     * Represents the parameters list to execute the request
     */
    protected List<NameValuePair> mParams;

    /**
     * Represent the URL Connection object allows to execute an url
     */
    /**
     * Represents the account token
     */
    private String token;


    protected HttpURLConnection urlConnection;

    private Boolean var;
    /**
     * The class constructor
     * @param context(in),@Activity represent the activity
     * @param route(in), @String repressent the route of Api
     * @param pp(in), @List represents the parameters list
     */
    public BaseGetAsyncTask(Context context, String route){
        this.context = context;
        mUrl += "/"+route;
    }




    /**
     * AsyncTask method basic calls before a request
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    /**
     * AsyncTask method basic calls during a request
     */
    @Override
    protected String doInBackground(Void... params) {

        String response = null;
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);

            //longInfo(response);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        StringBuilder sb = null;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            sb = new StringBuilder();

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception e){

        }
        return sb.toString();
    }
    public static void longInfo(String str) {
        try{
            if(str.length() > 4000) {
                Log.e("ResponseJson", str.substring(0, 4000));
                longInfo(str.substring(4000));
            } else{
                Log.e("ResponseJsonElse", str);}
        }catch (Exception e){

        }

    }


    /**
     * AsyncTask method basic calls after a request
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    /**
     * Allows to the parameters to support UTF-8
     * @param params(in), @List represent the parameters list
     * @return(out), the paraemters in String
     */
    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        try{

            boolean first = true;

            for (NameValuePair pair : params)
            {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            }
        }catch (Exception e){

        }
        return result.toString();
    }

}

