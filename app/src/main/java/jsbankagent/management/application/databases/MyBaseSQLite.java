package jsbankagent.management.application.databases;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 1/6/2018.
 */

public class MyBaseSQLite extends SQLiteOpenHelper {

    /**
     * Represents an activity
     */
    private Context context;
    /**
     * Reepresents the table name
     */
    private static final String Table_Add_Agents = "add_agents";
    /**
     * Represents the column ID
     */
    private static final String Col_Auto_Id = "Auto_Id";
    /**
     * Represents the column Agent_Id
     */
    private static final String Col_Agent_Id = "Agent_Id";
    /**
     * Represents column Agent_Name in the tabale
     */
    private static final String Col_Agent_Name = "Agent_Name";
    /**
     * Represents column Col_City_Id in the tabale
     */
    private static final String Col_City_Id = "City_Id";
    /**
     * Represents column Address in the tabale
     */
    private static final String Col_Address = "Address";
    /**
     * Represents column Province_Id in the tabale
     */
    private static final String Col_Province_Id = "Province_Id";
    /**
     * Represents column Comments in the tabale
     */
    private static final String Col_Comments = "Comments";

    private static final String Col_Form_Status = "Form_Status";
    /**
     * Represents column Image_Path in the tabale
     */
    private static final String Col_Image_Path = "Image_Path";
    private static final String Col_Latitude = "Latitude";
    private static final String Col_Longitude = "Longitude";


    private static final String Table_Cities_And_Province = "table_cities_and_province";
    private static final String Col_Auto_City_Id = "Id";
    private static final String Col_City_ID = "City_Id";
    private static final String Col_City_Name = "City_Name";
    private static final String Col_Province_ID = "Province_Id";
    private static final String Col_Province_Name = "Province_Name";


    private static final String REQUEST_CREATE_RIDES_TABLE = "CREATE TABLE IF NOT EXISTS "
            +Table_Add_Agents+" ("+
            Col_Auto_Id +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Col_Agent_Id+" TEXT NOT NULL DEFAULT '0',"+
            Col_Agent_Name+" TEXT NOT NULL DEFAULT '0',"+
            Col_City_Id+" INTEGER DEFAULT '0',"+
            Col_Address+" TEXT NOT NULL DEFAULT '0',"+
            Col_Province_Id+" INTEGER DEFAULT '0',"+
            Col_Comments+" TEXT,"+
            Col_Form_Status+" INTEGER DEFAULT '0',"+
            Col_Latitude+" TEXT NOT NULL DEFAULT '0',"+
            Col_Longitude+" TEXT NOT NULL DEFAULT '0',"+
            Col_Image_Path+" TEXT NOT NULL DEFAULT '0');";

    private static final String REQUEST_CREATE_CITIES_AND_PROVINCE_TABLE = "CREATE TABLE IF NOT EXISTS "
            +Table_Cities_And_Province+" ("

            +Col_Auto_City_Id+" INTEGER PRIMARY KEY AUTOINCREMENT, "

            +Col_City_ID+" INTEGER,"

            +Col_City_Name+" TEXT,"

            +Col_Province_ID+" INTEGER,"

            + Col_Province_Name +" TEXT);";
    /**
     * Class constructor
     * @param context(in), @Activity represents activity
     * @param nameDB(in), @String is the database name
     * @param version(in), @Integer is the database version
     */
    public MyBaseSQLite(Context context, String nameDB, int version){
        super(context, nameDB, null, version);
        //Log.i("Adneom"," in constructor DB ");
        SQLiteDatabase db = getWritableDatabase();
        long size = new File(db.getPath()).length();
        Log.v("DB SIZE", "Percentage database space used: " + size);
    }
    /**
     * Allows to create the tables in database.
     * @param db(in), @SQLite represents the database instnace
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUEST_CREATE_RIDES_TABLE);
        db.execSQL(REQUEST_CREATE_CITIES_AND_PROVINCE_TABLE);

    }
    //call when we change version
    /**
     * Allows to re create a new database with a neww version. SQLiteOpenHelepr method basic.
     * @param db(in), @SQLite represents the instance database
     * @param oldVersion(in), @Integer represents the previous database version
     * @param newVersion(in), @Integer represents the new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.i("Adneom"," drop table ");

        Log.i("onUpgrade", "Update Schema to version: "+Integer.toString(oldVersion)+"->"+Integer.toString(newVersion));
        try{


            if(newVersion > oldVersion){
                /*db.execSQL("ALTER TABLE " + Table_Add_Agents + " ADD COLUMN " + Col_Form_Status + " INTEGER DEFAULT 0");*/

            }
        }catch (SQLException e){

        }



    }
}
