package jsbankagent.management.application.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jsbankagent.management.application.model.AddAgents;
import jsbankagent.management.application.model.CitiesAndProvince;
import jsbankagent.management.application.model.PendingUploads;

/**
 * Created by Administrator on 1/6/2018.
 */

public class AddAgent {

    private static final int VERSION_DB = 2;

    /**
     * Represents the database name
     */
    private static final String NOM_BDD = "jsbank_agent.db";
    /**
     * Represents the tabale name
     */
    private static final String Table_Add_Agents = "add_agents";


    /**
     * Represents column ID in the tabale
     */
    private static final String Col_Auto_Id = "Auto_Id";
    /**
     * Represents position column ID in the tabale
     */
    private static final int NUM_COL_ID = 0;
    /**
     * Represents column Agent Id in the tabale
     */
    private static final String Col_Agent_Id = "Agent_Id";
    /**
     * Represents position column Agent Id in the tabale
     */
    private static final int Num_Col_Agent_Id = 1;

    /**
     * Represents column Agent_Name in the tabale
     */
    private static final String Col_Agent_Name = "Agent_Name";
    /**
     * Represents position column Agent_Name in the tabale
     */
    private static final int Num_Col_Agent_Name = 2;

    /**
     * Represents column Col_City_Id in the tabale
     */
    private static final String Col_City_Id = "City_Id";
    /**
     * Represents position Col_City_Id in the tabale
     */
    private static final int Num_Col_City_Id = 3;

    /**
     * Represents column Address in the tabale
     */
    private static final String Col_Address = "Address";
    /**
     * Represents position column Address in the tabale
     */
    private static final int Num_Col_Address = 4;

    /**
     * Represents column Province_Id in the tabale
     */
    private static final String Col_Province_Id = "Province_Id";
    /**
     * Represents position column Province_Id in the tabale
     */
    private static final int Num_Col_Province_Id = 5;

    /**
     * Represents column Comments in the tabale
     */
    private static final String Col_Comments = "Comments";

    /**
     * Represents position column Comments in the tabale
     */
    private static final int Num_Col_Comments = 6;

    /**
     * Represents column Image_Path in the tabale
     */
    private static final String Col_Image_Path = "Image_Path";
    /**
     * Represents position column Image_Path in the tabale
     */
    private static final int Num_Col_Image_Path = 10;

    /**
     * Represents column Image_Path in the tabale
     */
    private static final String Col_Form_Status = "Form_Status";
    /**
     * Represents position column Image_Path in the tabale
     */
    private static final int Num_Col_Form_Status = 7;

    private static final String Col_Latitude = "Latitude";
    private static final int Num_Col_Latitude = 8;
    private static final String Col_Longitude = "Longitude";
    private static final int Num_Col_Longitude = 9;


    private static final String Table_Cities_And_Province = "table_cities_and_province";
    private static final String Col_City_ID = "City_Id";
    private static final String Col_City_Name = "City_Name";
    private static final String Col_Province_ID = "Province_Id";
    private static final String Col_Province_Name = "Province_Name";

    private static final String Table_New_Account = "table_new_account";
    private static final String Col_Auto_Account_Id = "Auto_Account_Id";

    /**
     * Represents the current database
     */
    private SQLiteDatabase bdd;
    /**
     * Represents the base sqlite
     */
    private MyBaseSQLite myBaseSQLite;

    /**
     * Represents the context : Activity
     */
    private Context context;

    /**
     * Class Constructor
     *
     * @param context(in), @Activity represents the activity
     */
    public AddAgent(Context context) {

        myBaseSQLite = new MyBaseSQLite(context, NOM_BDD, VERSION_DB);
        this.context = context;
    }
    /**
     * Allows to open database
     */
    public void open() {
        //on ouvre la BDD en écriture
        bdd = myBaseSQLite.getWritableDatabase();
    }
    /**
     * Allows to close database
     */
    public void close() {
        //on ferme l'accès à la BDD
        bdd.close();
    }
    /**
     * Returns an instance o fdatabase
     *
     * @return(out), @SQLite
     */
    public SQLiteDatabase getBDD() {
        return bdd;
    }

    /**
     * Allows to register a add agents in the database
     */
    public int insertAgents(AddAgents addAgents) {
        ContentValues contentValues = new ContentValues();


        contentValues.put(Col_Agent_Id, addAgents.getAgentId());
        contentValues.put(Col_Agent_Name, addAgents.getAgentName());
        contentValues.put(Col_City_Id, addAgents.getCityId());
        contentValues.put(Col_Address, addAgents.getAgentAddress());
        contentValues.put(Col_Province_Id, addAgents.getProvinceId());
        contentValues.put(Col_Comments, addAgents.getAgentComments());
        contentValues.put(Col_Image_Path, addAgents.getImagePath());
        contentValues.put(Col_Latitude, addAgents.getLatitude());
        contentValues.put(Col_Longitude, addAgents.getLongitude());
        addAgents.setFormStatus(false);
        contentValues.put(Col_Form_Status, addAgents.getFormStatus());
        int formId = (int) bdd.insert(Table_Add_Agents, null, contentValues);
        return formId;
    }

    public int updateFormStatus(int formstatus){
        int entry = -1;
        try{
            ContentValues values = new ContentValues();
            values.put(Col_Form_Status, 1);
            if (bdd != null) {
                entry = bdd.update(Table_Add_Agents, values, Col_Auto_Id + " = " + formstatus, null);
            }
            /* String strSQL = "Update " + Table_Add_Agents + " Set " + Col_Form_Status + " =  '" + 1 + "' WHERE " + Col_Agent_Id + " = '"  + formstatus + "'" ;
            Cursor cursor = bdd.rawQuery(strSQL, null);
            if (cursor !=null){
                if (cursor.moveToFirst()){
                    Integer.parseInt(cursor.getString(0));

                }
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return entry;
    }

    public long insertCitiesAndProvince(int cityid,String cityname,int provinceid,String provincename){
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_City_ID, cityid);
        contentValues.put(Col_City_Name, cityname);
        contentValues.put(Col_Province_ID, provinceid);
        contentValues.put(Col_Province_Name, provincename);
        return bdd.insert(Table_Cities_And_Province, null, contentValues);
    }


    public ArrayList<AddAgents> allAgents(){
        ArrayList<AddAgents> agentsArrayList = new ArrayList<>();

        try {
            String querySQL = "Select * From " + Table_Add_Agents + " Where " + Col_Form_Status + " = 1";
            Cursor cursor = bdd.rawQuery(querySQL,null);

            if (cursor.moveToFirst()) {

                do {
                    //getting the ride :

                    AddAgents addAgents = new AddAgents();
                    addAgents.setAutoagentId(Integer.parseInt(cursor.getString(NUM_COL_ID)));
                    addAgents.setAgentId(cursor.getString(Num_Col_Agent_Id));
                    addAgents.setAgentName(cursor.getString(Num_Col_Agent_Name));
                    addAgents.setCityId(Integer.parseInt(cursor.getString(Num_Col_City_Id)));
                    addAgents.setAgentAddress(cursor.getString(Num_Col_Address));
                    addAgents.setProvinceId(Integer.parseInt(cursor.getString(Num_Col_Province_Id)));
                    addAgents.setAgentComments(cursor.getString(Num_Col_Comments));
                    addAgents.setAgentComments(cursor.getString(Num_Col_Latitude));
                    addAgents.setAgentComments(cursor.getString(Num_Col_Longitude));
                    addAgents.setImagePath(cursor.getString(Num_Col_Image_Path));

                    /*int valSend = Integer.parseInt(cursor.getString(NUM_COL_SEND));
                    Boolean isSend = (valSend == 1) ? true : false;
                    ride.setSend(isSend);*/
                    agentsArrayList.add(addAgents);
                } while (cursor.moveToNext());
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return agentsArrayList;
    }


    public ArrayList<PendingUploads> allPendingUploads(){
        ArrayList<PendingUploads> agentsArrayList = new ArrayList<>();

        try {
            String querySQL = "Select * From " + Table_Add_Agents + " Where " + Col_Form_Status + " = 0";
            Cursor cursor = bdd.rawQuery(querySQL, null);

            if (cursor.moveToFirst()) {

                do {
                    //getting the ride :

                    PendingUploads addAgents = new PendingUploads();
                    addAgents.setAutoagentId(Integer.parseInt(cursor.getString(NUM_COL_ID)));
                    addAgents.setAgentId(cursor.getString(Num_Col_Agent_Id));
                    addAgents.setAgentName(cursor.getString(Num_Col_Agent_Name));
                    addAgents.setCityId(Integer.parseInt(cursor.getString(Num_Col_City_Id)));
                    addAgents.setAgentAddress(cursor.getString(Num_Col_Address));
                    addAgents.setProvinceId(Integer.parseInt(cursor.getString(Num_Col_Province_Id)));
                    addAgents.setAgentComments(cursor.getString(Num_Col_Comments));
                    addAgents.setLatitude(cursor.getString(Num_Col_Latitude));
                    addAgents.setLongitude(cursor.getString(Num_Col_Longitude));
                    addAgents.setImagePath(cursor.getString(Num_Col_Image_Path));

                    /*int valSend = Integer.parseInt(cursor.getString(NUM_COL_SEND));
                    Boolean isSend = (valSend == 1) ? true : false;
                    ride.setSend(isSend);*/
                    agentsArrayList.add(addAgents);
                } while (cursor.moveToNext());
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return agentsArrayList;
    }

    public int getProvinceId(String provinceName){
        int provinceId = 0;
        try{
            //String strSQL = "SELECT City_Id FROM table_cities_and_province WHERE City_Name = " + cityname;
            String strSQL = "SELECT " + Col_Province_Id + " FROM " + Table_Cities_And_Province + " WHERE " + Col_Province_Name + " = '"  + provinceName + "'" ;
            Cursor cursor = bdd.rawQuery(strSQL, null);
            if (cursor !=null){
                if (cursor.moveToFirst()){
                    provinceId = Integer.parseInt(cursor.getString(0));
                    Log.e("provinceId",":"+provinceId);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return provinceId;
    }

    public int getCityId(String cityname){
        int cityId = 0;
        try{
            //String strSQL = "SELECT City_Id FROM table_cities_and_province WHERE City_Name = " + cityname;
            String strSQL = "SELECT " + Col_City_ID + " FROM " + Table_Cities_And_Province + " WHERE " + Col_City_Name + " = '"  + cityname + "'" ;
            Cursor cursor = bdd.rawQuery(strSQL, null);
            if (cursor !=null){
                if (cursor.moveToFirst()){
                    cityId = Integer.parseInt(cursor.getString(0));
                    Log.e("cityId",":"+cityId);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cityId;
    }
    public String getProvinceName(int cityid){
        String provinceName = "";
        try{
            String strSQL = "SELECT " + Col_Province_Name + " FROM " + Table_Cities_And_Province + " WHERE " + Col_City_ID + " = '" + cityid + "'";
            Cursor cursor = bdd.rawQuery(strSQL, null);
            if (cursor !=null){
                if(cursor.moveToFirst()){
                    provinceName = cursor.getString(0);
                    Log.e("provinceName",provinceName);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return provinceName;
    }

    public String[] SelectAllDataCity() {
        try {
            String arrData[] = null;


            String strSQL = "Select " + Col_City_Name +  " From " + Table_Cities_And_Province;
            Cursor cursor = bdd.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];
                    int i = 0;
                    do {
                        arrData[i] = cursor.getString(0);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;
        } catch (Exception e) {
            return null;
        }
    }

    public int getAllCity() {

        int count = 0;
        try {

            String sqlcheckCount = "Select count (Id) From table_cities_and_province";
            Cursor cursor = bdd.rawQuery(sqlcheckCount,null);

            if (cursor !=null){
                if (cursor.moveToFirst()){
                    count = Integer.parseInt(cursor.getString(0));
                    Log.e("count",":"+count);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public JSONArray getAllAgents() {
        JSONArray listRides = null;

        try {
            int n = 1;
            Cursor cursor = bdd.query(Table_Add_Agents, new String[]{Col_Auto_Id,
                    Col_Agent_Id, Col_Agent_Name, Col_City_Id, Col_Address, Col_Province_Id,
                    Col_Comments, Col_Image_Path}, null, null, null, null, null);

            int countbad = 0;
            if (cursor.moveToFirst()) {
                listRides = new JSONArray();
                do {
                    //getting the ride :

                    AddAgents addAgents = new AddAgents();
                    addAgents.setAutoagentId(Integer.parseInt(cursor.getString(NUM_COL_ID)));
                    addAgents.setAgentId(cursor.getString(Num_Col_Agent_Id));
                    addAgents.setAgentName(cursor.getString(Num_Col_Agent_Name));
                    addAgents.setCityId(Integer.parseInt(cursor.getString(Num_Col_City_Id)));
                    addAgents.setAgentAddress(cursor.getString(Num_Col_Address));
                    addAgents.setProvinceId(Integer.parseInt(cursor.getString(Num_Col_Province_Id)));
                    addAgents.setAgentComments(cursor.getString(Num_Col_Comments));
                    addAgents.setImagePath(cursor.getString(Num_Col_Image_Path));

                    /*int valSend = Integer.parseInt(cursor.getString(NUM_COL_SEND));
                    Boolean isSend = (valSend == 1) ? true : false;
                    ride.setSend(isSend);*/
                    JSONObject obj = new JSONObject();


                    obj.put("remote_id", addAgents.getAutoagentId());
                    obj.put("departure_time", addAgents.getAgentId());
                    obj.put("arrival_time", addAgents.getAgentName());
                    obj.put("time_elapsed", addAgents.getCityId());
                    obj.put("departure_location", addAgents.getAgentAddress());
                    obj.put("arrival_location", addAgents.getProvinceId());
                    obj.put("score", addAgents.getAgentComments());
                    obj.put("driver_id", addAgents.getImagePath());

                    /*obj.put("send", ride.getSend());*/
                    listRides.put(obj);

           /*         obj.put("ID_MAIN_RIDE",cursor.getString(18));
                    obj.put("AREA_TYPE",cursor.getString(19));
                    obj.put("START_LAT_LNG",cursor.getString(20));
                    obj.put("END_LAT_LNG",cursor.getString(21));
                    obj.put("CURRENT_TIME",cursor.getString(22));
                    obj.put("CURRENT_SPEED",cursor.getString(23));
                    obj .put("IS_SENT",cursor.getString(24));*/

//
                    //if for sync, departure and arrival are not empty and not already save
                    /*if (!addAgents.getDeparture_location().equals("") && !ride.getArrival_location().equals("") && !ride.getSend()) {
                        listRides.put(obj);

                    } else {
                        //display on menu activity :
                        listRides.put(obj);
                    }*/

 /*               if (cursor.isLast()){

                    try {
                        JSONObject obj1 =new JSONObject();
                        obj1.put("subData",getAllSubRidesForSyncing().toString());
                        listRides.put(obj1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }*/
                } while (cursor.moveToNext());


            }
        }catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listRides;
}
}
