package jsbankagent.management.application.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;



import org.apache.http.NameValuePair;

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
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import jsbankagent.management.application.utils.WebServiceConstants;


/**
 * Represents the Asynctask class basic, Login and Register Asynctask inherits from this class.
 * Created by gtshilombowanticale on 30-08-16.
 */
public class BaseAsyncTask extends AsyncTask<List<NameValuePair>,Void,String> {

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
    public BaseAsyncTask(Context context, String route){
        this.context = context;
        mUrl += "/"+route;
    }

    public BaseAsyncTask(Context context, String route, List<NameValuePair> pp){
        this.context = context;
        mParams = new ArrayList<NameValuePair>();
        mParams = pp;
        mUrl += "/"+route;


    }
    public BaseAsyncTask(Context context, String route, List<NameValuePair> pp, Boolean var){
        this.context = context;
        mParams = new ArrayList<NameValuePair>();
        mParams = pp;
        mUrl += "/"+route;
        var = var;

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
    protected String doInBackground(List<NameValuePair>... params) {

        //Log.i("Adneom"," list from child is "+mParams+" -*-*-*");
        URL url = null;
        try {
            //Log.i("Adneom","url is "+mUrl);
            url = new URL(mUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Authorization", token);
            urlConnection.setConnectTimeout(15000);
           if (mParams!=null){
               OutputStream os = urlConnection.getOutputStream();
               BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
               writer.write(getQuery(mParams));
               writer.flush();
               writer.close();
               os.close();
           }


            urlConnection.connect();

            int responsecode = urlConnection.getResponseCode();
            urlConnection.getResponseMessage();
            Log.e("getResponseMessage",urlConnection.getResponseMessage());
//            Toast.makeText(context,urlConnection.getResponseMessage(),Toast.LENGTH_LONG);
           // Toast.makeText(context,urlConnection.getResponseCode(),Toast.LENGTH_LONG);

            InputStream inputStream;
            // get stream
            if (responsecode < HttpURLConnection.HTTP_BAD_REQUEST) {
                Log.i("Adneom", "(BaseAsyncTask) Response code from base is " + responsecode + " (OK)");
                inputStream = urlConnection.getInputStream();
            } else {
                inputStream = urlConnection.getErrorStream();
                Log.i("Adneom","(BaseAsyncTask) Response code from base is "+responsecode+" (NOT OK)");
            }
            response = String.valueOf(responsecode);

            // parse stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp, response = "";
            while ((temp = bufferedReader.readLine()) != null) {
                response += temp;
            }

            resultat = response;
            Log.e("Adneom", "(BaseAsyncTask) response is " + resultat + " ** ");

        }catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("MalformedURLException",""+e);
        } catch (UnsupportedEncodingException e) {
            Log.e("EncodingExcept",""+e);
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("IOException",""+e);
            e.printStackTrace();
        }catch (Exception e){
            Log.e("Exception",""+e);
            e.printStackTrace();
        }
        //longInfo(response);
        return response;
    }
    public static void longInfo(String str) {
        if(str.length() > 4000) {
            Log.e("ResponseJson", str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.e("ResponseJsonElse", str);
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
