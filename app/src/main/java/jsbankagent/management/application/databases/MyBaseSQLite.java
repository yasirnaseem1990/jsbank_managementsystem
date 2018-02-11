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


    //Todo Creating Table for Application Personal Information
    private static final String TABLE_APPLICATION_PERSONAL_INFO = "table_application_personal_info";
    private static final String Auto_Id = "Id";
    private static final String Courtesy_Title = "Courtesy_Title";
    private static final String Applicant_Name = "Applicant_Name";
    private static final String CNIC_No = "CNIC_No";
    private static final String Expire_CNIC_No = "Expire_CNIC_No";
    private static final String Date_Of_Birth = "Date_Of_Birth";
    private static final String Applicant_Gender = "Applicant_Gender";
    private static final String Applicant_Nationality = "Applicant_Nationality";
    private static final String Applicant_NTN = "Applicant_NTN";
    private static final String Place_Of_Birth = "Place_Of_Birth";
    private static final String Marital_Status = "Marital_Status";
    private static final String Applicant_Qualification = "Applicant_Qualification";
    private static final String Applicant_Profession = "Applicant_Profession";
    private static final String Employer_Business = "Employer_Business";
    private static final String Nature_Of_Business = "Nature_Of_Business";
    private static final String Applicant_Designation = "Applicant_Designation";
    private static final String Applicant_Mailing_Address = "Applicant_Mailing_Address";
    private static final String Applicant_Father_Name = "Applicant_Father_Name";
    private static final String Applicant_Citizen_Ship = "Applicant_Citizen_Ship";
    private static final String Expiry_Date_Of_Visa = "Expiry_Date_Of_Visa";
    private static final String Residential_Address = "Residential_Address";
    private static final String Residential_Contact_Number = "Residential_Contact_Number";
    private static final String Applicant_Cell_No = "Applicant_Cell_No";
    private static final String Office_Business_Address = "Office_Business_Address";
    private static final String Office_Contact_Number = "Office_Contact_Number";
    private static final String Telenor_Golden_Number = "Telenor_Golden_Number";
    private static final String Applicant_Email = "Applicant_Email";

    //Todo Application Account Information
    /**
     * @param AI Stands for Account Information
     */
    private static final String Applicant_AI_Title = "Applicant_AI_Title";
    private static final String Applicant_AI_Current_Type = "Applicant_AI_Current_Type";
    private static final String Applicant_AI_Accoun_Type = "Applicant_AI_Accoun_Type";
    private static final String Applicant_AI_Currency = "Applicant_AI_Currency";
    private static final String Applicant_AI_Country_Residential_Status = "Applicant_AI_Country_Residential_Status";
    private static final String Applicant_AI_Operating_Instruction = "Applicant_AI_Operating_Instruction";
    private static final String Applicant_AI_Cheque_Book_Required = "Applicant_AI_Cheque_Book_Required";
    private static final String Applicant_AI_No_Of_Cheque_Book_Required = "Applicant_AI_No_Of_Cheque_Book_Required";
    private static final String Applicant_AI_Zakat_Applicable = "Applicant_AI_Zakat_Applicable";

    //Todo Application Personal Joint Information
    /**
     * @param PJI Stands for Personal Joint Information
     * @param RI  Stands for Residential Address
     */
    private static final String Applicant_PJI_Name = "Applicant_PJI_Name";
    private static final String Applicant_PJI_CNIC = "Applicant_PJI_CNIC";
    private static final String Applicant_PJI_Expire_CNIC = "Applicant_PJI_Expire_CNIC";
    private static final String Applicant_PJI_Date_Of_Birth = "Applicant_PJI_Date_Of_Birth";
    private static final String Applicant_PJI_Gender = "Applicant_PJI_Gender";
    private static final String Applicant_PJI_Expiry_Date_Of_Visa = "Applicant_PJI_Expiry_Date_Of_Visa";
    private static final String Applicant_PJI_Father_Name = "Applicant_PJI_Father_Name";
    private static final String Applicant_PJI_US_Citizen_Ship = "Applicant_PJI_US_Citizen_Ship";
    private static final String Applicant_PJI_Nationality = "Applicant_PJI_Nationality";
    private static final String Applicant_PJI_NTN_Number = "Applicant_PJI_NTN_Number";
    private static final String Applicant_PJI_Place_Of_Birth = "Applicant_PJI_Place_Of_Birth";
    private static final String Applicant_PJI_Marital_Status = "Applicant_PJI_Marital_Status";
    private static final String Applicant_PJI_Qualification = "Applicant_PJI_Qualification";
    private static final String Applicant_PJI_Profession = "Applicant_PJI_Profession";
    private static final String Applicant_PJI_Employer_Name = "Applicant_PJI_Employer_Name";
    private static final String Applicant_PJI_Nature_Of_Business = "Applicant_PJI_Nature_Of_Business";
    private static final String Applicant_PJI_Designation = "Applicant_PJI_Designation";
    private static final String Applicant_PJI_R_I = "Applicant_Residential_Address";
    private static final String Applicant_PJI_Business_Address = "Applicant_PJI_Business_Address";
    private static final String Applicant_PJI_Office_Contact_Number = "Applicant_PJI_Office_Contact_Number";
    private static final String Applicant_PJI_R_I_Contact_Number = "Applicant_PJI_Residential_Address_Contact_Number";
    private static final String Applicant_PJI_Cell_Number = "Applicant_PJI_Cell_Number";

    //Todo Application Next of kin Information
    /**
     * @param NOK Stands for Next of Kin Information
     */
    private static final String Applicant_NOK_Courtesy_Title = "Applicant_NOK_Courtesy_Title";
    private static final String Applicant_NOK_Name = "Applicant_NOK_Name";
    private static final String Applicant_NOK_RelationShip = "Applicant_NOK_RelationShip";
    private static final String Applicant_NOK_CNIC = "Applicant_NOK_CNIC";
    private static final String Applicant_NOK_Mailing_Address = "Applicant_NOK_Mailing_Address";
    private static final String Applicant_NOK_Contact_Number = "Applicant_NOK_Contact_Number";
    private static final String Applicant_NOK_Cell_Number = "Applicant_NOK_Cell_Number";

    //Todo Application Business Account Information
    /**
     * @param BAI Stands for Business Account Information
     */
    private static final String Applicant_BAI_Business_Name = "Applicant_BAI_Business_Name";
    private static final String Applicant_BAI_Nature_Of_Business = "Applicant_BAI_Nature_Of_Business";
    private static final String Applicant_BAI_Registration_No = "Applicant_BAI_Registration_No";
    private static final String Applicant_BAI_NTN_No = "Applicant_BAI_NTN_No";
    private static final String Applicant_BAI_Date_Of_Registration = "Applicant_BAI_Date_Of_Registration";
    private static final String Applicant_BAI_Place_Of_Birth = "Applicant_BAI_Place_Of_Birth";
    private static final String Applicant_BAI_Office_Address = "Applicant_BAI_Office_Address";
    private static final String Applicant_BAI_Contact_Number = "Applicant_BAI_Contact_Number";
    private static final String Applicant_BAI_Fax_Number = "Applicant_BAI_Fax_Number";

    //Todo Application Debit Card Information
    /**
     * @param DCI Stands for Debit Card Information
     */
    private static final String Applicant_DCI_Request_For_Debit_Card = "Applicant_DCI_Request_For_Debit_Card";
    private static final String Applicant_DCI_Name_On_Debit_Card = "Applicant_DCI_Name_On_Debit_Card";
    private static final String Applicant_DCI_Maiden_Name = "Applicant_DCI_Maiden_Name";

    //Todo Application Supplementary Card Information
    /**
     * @param SCI Stands for Supplementary Card Information
     */
    private static final String Applicant_SCI_Request_SCI = "Applicant_SCI_Request_SCI";
    private static final String Applicant_SCI_Full_Name_Of_Place_Holder = "Applicant_SCI_Full_Name_Of_Place_Holder";
    private static final String Applicant_SCI_Date_Of_Birth = "Applicant_SCI_Date_Of_Birth";
    private static final String Applicant_SCI_Relationship = "Applicant_SCI_Relationship";
    private static final String Applicant_SCI_CNIC = "Applicant_SCI_CNIC";
    private static final String Applicant_SCI_Expire_CNIC = "Applicant_SCI_Expire_CNIC";
    private static final String Applicant_SCI_Maiden_Name = "Applicant_SCI_Maiden_Name";
    private static final String Applicant_SCI_Name_Of_SCI = "Applicant_SCI_Name_Of_SCI";

    //Todo Application E-Banking Information
    private static final String EBanking_SMS_Alerts = "EBanking_SMS_Alerts";
    private static final String EBanking_Internet_Banking = "EBanking_Internet_Banking";
    private static final String EBanking_Mobile_Banking = "EBanking_Mobile_Banking";
    private static final String EBanking_EStatement = "EBanking_EStatement";
    private static final String EBanking_Frequency = "EBanking_Frequency";



    private static final String REQUEST_CREATE_APPLICATION_INFORMATION_TABLE ="CREATE TABLE IF NOT EXISTS "
            +TABLE_APPLICATION_PERSONAL_INFO+" ("+
            Auto_Id +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Courtesy_Title+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Name+" TEXT NOT NULL DEFAULT '0',"+
            CNIC_No+" TEXT NOT NULL DEFAULT '0',"+
            Expire_CNIC_No+" TEXT NOT NULL DEFAULT '0',"+
            Date_Of_Birth+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Gender+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Nationality+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_NTN+" TEXT NOT NULL DEFAULT '0',"+
            Place_Of_Birth+" TEXT NOT NULL DEFAULT '0',"+
            Marital_Status+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Qualification+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Profession+" TEXT NOT NULL DEFAULT '0',"+
            Employer_Business+" TEXT NOT NULL DEFAULT '0',"+
            Nature_Of_Business+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Designation+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Mailing_Address+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Father_Name+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Citizen_Ship+" TEXT NOT NULL DEFAULT '0',"+
            Expiry_Date_Of_Visa+" TEXT NOT NULL DEFAULT '0',"+
            Residential_Address+" TEXT NOT NULL DEFAULT '0',"+
            Residential_Contact_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Cell_No+" TEXT NOT NULL DEFAULT '0',"+
            Office_Business_Address+" TEXT NOT NULL DEFAULT '0',"+
            Office_Contact_Number+" TEXT NOT NULL DEFAULT '0',"+
            Telenor_Golden_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_Email+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_AI_Title+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_AI_Current_Type+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_AI_Accoun_Type+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_AI_Currency+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_AI_Country_Residential_Status+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_AI_Operating_Instruction+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_AI_Cheque_Book_Required+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_AI_No_Of_Cheque_Book_Required+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_AI_Zakat_Applicable+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Name+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_CNIC+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Expire_CNIC+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Date_Of_Birth+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Gender+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Expiry_Date_Of_Visa+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Father_Name+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_US_Citizen_Ship+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Nationality+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_NTN_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Place_Of_Birth+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Marital_Status+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Qualification+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Profession+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Employer_Name+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Nature_Of_Business+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Designation+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_R_I+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Business_Address+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Office_Contact_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_R_I_Contact_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_PJI_Cell_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_NOK_Courtesy_Title+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_NOK_Name+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_NOK_RelationShip+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_NOK_CNIC+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_NOK_Mailing_Address+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_NOK_Contact_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_NOK_Cell_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_BAI_Business_Name+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_BAI_Nature_Of_Business+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_BAI_Registration_No+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_BAI_NTN_No+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_BAI_Date_Of_Registration+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_BAI_Place_Of_Birth+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_BAI_Office_Address+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_BAI_Contact_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_BAI_Fax_Number+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_DCI_Request_For_Debit_Card+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_DCI_Name_On_Debit_Card+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_DCI_Maiden_Name+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_SCI_Request_SCI+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_SCI_Full_Name_Of_Place_Holder+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_SCI_Date_Of_Birth+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_SCI_Relationship+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_SCI_CNIC+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_SCI_Expire_CNIC+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_SCI_Maiden_Name+" TEXT NOT NULL DEFAULT '0',"+
            Applicant_SCI_Name_Of_SCI+" TEXT NOT NULL DEFAULT '0',"+
            EBanking_SMS_Alerts+" TEXT NOT NULL DEFAULT '0',"+
            EBanking_Internet_Banking+" TEXT NOT NULL DEFAULT '0',"+
            EBanking_Mobile_Banking+" TEXT NOT NULL DEFAULT '0',"+
            EBanking_EStatement+" TEXT NOT NULL DEFAULT '0',"+
            EBanking_Frequency+" TEXT NOT NULL DEFAULT '0');";



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
        db.execSQL(REQUEST_CREATE_APPLICATION_INFORMATION_TABLE);

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
                db.execSQL(REQUEST_CREATE_APPLICATION_INFORMATION_TABLE);
                /*db.execSQL("ALTER TABLE " + Table_Add_Agents + " ADD COLUMN " + Col_Form_Status + " INTEGER DEFAULT 0");*/

            }
        }catch (SQLException e){

        }



    }
}
