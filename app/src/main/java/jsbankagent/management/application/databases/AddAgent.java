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
import jsbankagent.management.application.model.ApplicationPersonalInformation;
import jsbankagent.management.application.model.CitiesAndProvince;
import jsbankagent.management.application.model.PendingUploads;

/**
 * Created by Administrator on 1/6/2018.
 */

public class AddAgent {

    private static final int VERSION_DB = 4;

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
     * //TODO @{Creating the Table for New Account }
     */

    private static final String TABLE_APPLICATION_PERSONAL_INFO = "table_application_personal_info";
    private static final String Auto_Id = "Id";
    private static final int Num_Col_Auto_Id = 0;
    private static final String Courtesy_Title = "Courtesy_Title";
    private static final int Num_Col_Courtesy_Title = 1;
    private static final String Applicant_Name = "Applicant_Name";
    private static final int Num_Col_Applicant_Name = 2;
    private static final String CNIC_No = "CNIC_No";
    private static final int Num_Col_CNIC_No = 3;
    private static final String Expire_CNIC_No = "Expire_CNIC_No";
    private static final int Num_Col_Expire_CNIC_No = 4;
    private static final String Date_Of_Birth = "Date_Of_Birth";
    private static final int Num_Col_Date_Of_Birth = 5;
    private static final String Applicant_Gender = "Applicant_Gender";
    private static final int Num_Col_Applicant_Gender = 6;
    private static final String Applicant_Nationality = "Applicant_Nationality";
    private static final int Num_Col_Applicant_Nationality = 7;
    private static final String Applicant_NTN = "Applicant_NTN";
    private static final int Num_Col_Applicant_NTN = 8;
    private static final String Place_Of_Birth = "Place_Of_Birth";
    private static final int Num_Col_Place_Of_Birth = 9;
    private static final String Marital_Status = "Marital_Status";
    private static final int Num_Col_Marital_Status = 10;
    private static final String Applicant_Qualification = "Applicant_Qualification";
    private static final int Num_Col_Applicant_Qualification = 11;
    private static final String Applicant_Profession = "Applicant_Profession";
    private static final int Num_Col_Applicant_Profession = 12;
    private static final String Employer_Business = "Employer_Business";
    private static final int Num_Col_Employer_Business = 13;
    private static final String Nature_Of_Business = "Nature_Of_Business";
    private static final int Num_Col_Nature_Of_Business = 14;
    private static final String Applicant_Designation = "Applicant_Designation";
    private static final int Num_Col_Applicant_Designation = 15;
    private static final String Applicant_Mailing_Address = "Applicant_Mailing_Address";
    private static final int Num_Col_Applicant_Mailing_Address = 16;
    private static final String Applicant_Father_Name = "Applicant_Father_Name";
    private static final int Num_Col_Applicant_Father_Name = 17;
    private static final String Applicant_Citizen_Ship = "Applicant_Citizen_Ship";
    private static final int Num_Col_Applicant_Citizen_Ship = 18;
    private static final String Expiry_Date_Of_Visa = "Expiry_Date_Of_Visa";
    private static final int Num_Col_Expiry_Date_Of_Visa = 19;
    private static final String Residential_Address = "Residential_Address";
    private static final int Num_Col_Residential_Address = 20;
    private static final String Residential_Contact_Number = "Residential_Contact_Number";
    private static final int Num_Col_Residential_Contact_Number = 21;
    private static final String Applicant_Cell_No = "Applicant_Cell_No";
    private static final int Num_Col_Applicant_Cell_No = 22;
    private static final String Office_Business_Address = "Office_Business_Address";
    private static final int Num_Col_Office_Business_Address = 23;
    private static final String Office_Contact_Number = "Office_Contact_Number";
    private static final int Num_Col_Office_Contact_Number = 24;
    private static final String Telenor_Golden_Number = "Telenor_Golden_Number";
    private static final int Num_Col_Telenor_Golden_Number = 25;
    private static final String Applicant_Email = "Applicant_Email";
    private static final int Num_Col_Applicant_Email = 26;

    //Todo Application Account Information
    /**
     * @param AI Stands for Account Information
     */
    private static final String Applicant_AI_Title = "Applicant_AI_Title";
    private static final int Num_Col_Applicant_AI_Title = 27;
    private static final String Applicant_AI_Current_Type = "Applicant_AI_Current_Type";
    private static final int Num_Col_Applicant_AI_Current_Type = 28;
    private static final String Applicant_AI_Accoun_Type = "Applicant_AI_Accoun_Type";
    private static final int Num_Col_Applicant_AI_Accoun_Type = 29;
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

    //TODO METHOD FOR INSERT A VALUE OF NEW ACCOUNT
    public int insertNewAccount(ApplicationPersonalInformation personalInformation){
        ContentValues contentValues = new ContentValues();

        contentValues.put(Courtesy_Title, personalInformation.getCourtesyTitle());
        contentValues.put(Applicant_Name, personalInformation.getApplicantName());
        contentValues.put(CNIC_No, personalInformation.getCnicNo());
        contentValues.put(Expire_CNIC_No, personalInformation.getExpirecnicNo());
        contentValues.put(Date_Of_Birth, personalInformation.getDob());
        contentValues.put(Applicant_Gender, personalInformation.getApplicantGender());
        contentValues.put(Applicant_Nationality, personalInformation.getApplicantNationality());
        contentValues.put(Applicant_NTN, personalInformation.getApplicantNTN());
        contentValues.put(Place_Of_Birth, personalInformation.getPlaceofBirth());
        contentValues.put(Marital_Status, personalInformation.getMaritalStatus());
        contentValues.put(Applicant_Qualification, personalInformation.getApplicantQualification());
        contentValues.put(Applicant_Profession, personalInformation.getApplicantProfession());
        contentValues.put(Employer_Business, personalInformation.getEmployerbuisnessName());
        contentValues.put(Nature_Of_Business, personalInformation.getNatureofBusiness());
        contentValues.put(Applicant_Designation, personalInformation.getApplicantDesignation());
        contentValues.put(Applicant_Mailing_Address, personalInformation.getApplicantmailingAddress());
        contentValues.put(Applicant_Father_Name, personalInformation.getApplicantfatherName());
        contentValues.put(Applicant_Citizen_Ship, personalInformation.getApplicantCitizenship());
        contentValues.put(Expiry_Date_Of_Visa, personalInformation.getExpirydateofVisa());
        contentValues.put(Residential_Address, personalInformation.getResidentialAddress());
        contentValues.put(Residential_Contact_Number, personalInformation.getResidentialcontactNumber());
        contentValues.put(Applicant_Cell_No, personalInformation.getCellNumber());
        contentValues.put(Office_Business_Address, personalInformation.getOfficebuisnessAddress());
        contentValues.put(Office_Contact_Number, personalInformation.getOfficecontactNumber());
        contentValues.put(Telenor_Golden_Number, personalInformation.getTelenorgoldenNumber());
        contentValues.put(Applicant_Email, personalInformation.getApplicantEmail());
        contentValues.put(Applicant_AI_Title, personalInformation.getApplicantaccountTitle());
        contentValues.put(Applicant_AI_Current_Type, personalInformation.getApplicantaccountcurrentType());
        contentValues.put(Applicant_AI_Accoun_Type, personalInformation.getApplicantaccountType());
        contentValues.put(Applicant_AI_Currency, personalInformation.getApplicantCurrency());
        contentValues.put(Applicant_AI_Country_Residential_Status, personalInformation.getApplicantcountryresidentialStatus());
        contentValues.put(Applicant_AI_Operating_Instruction, personalInformation.getApplicantoperatingInstruction());
        contentValues.put(Applicant_AI_Cheque_Book_Required, personalInformation.getApplicantchequebookRequired());
        contentValues.put(Applicant_AI_No_Of_Cheque_Book_Required, personalInformation.getApplicantnoofchequebokRequired());
        contentValues.put(Applicant_AI_Zakat_Applicable, personalInformation.getApplicantzakatApplicable());
        contentValues.put(Applicant_PJI_Name, personalInformation.getJointapplicantName());
        contentValues.put(Applicant_PJI_CNIC, personalInformation.getJointapplicantCnic());
        contentValues.put(Applicant_PJI_Expire_CNIC, personalInformation.getJointapplicantexpireCnic());
        contentValues.put(Applicant_PJI_Date_Of_Birth, personalInformation.getJointapplicantDob());
        contentValues.put(Applicant_PJI_Gender, personalInformation.getJointapplicantGender());
        contentValues.put(Applicant_PJI_Expiry_Date_Of_Visa, personalInformation.getJointapplicantexpirydateofVisa());
        contentValues.put(Applicant_PJI_Father_Name, personalInformation.getJointapplicantfatherName());
        contentValues.put(Applicant_PJI_US_Citizen_Ship, personalInformation.getJointapplicantucCitizen());
        contentValues.put(Applicant_PJI_Nationality, personalInformation.getApplicantNationality());
        contentValues.put(Applicant_PJI_NTN_Number, personalInformation.getJointapplicantntnNumber());
        contentValues.put(Applicant_PJI_Place_Of_Birth, personalInformation.getJointapplicantplaceofBirth());
        contentValues.put(Applicant_PJI_Marital_Status, personalInformation.getJointapplicantmaritalStatus());
        contentValues.put(Applicant_PJI_Qualification, personalInformation.getJointapplicantQualification());
        contentValues.put(Applicant_PJI_Profession, personalInformation.getJointapplicantProfession());
        contentValues.put(Applicant_PJI_Employer_Name, personalInformation.getJointapplicantemoployerName());
        contentValues.put(Applicant_PJI_Nature_Of_Business, personalInformation.getJointapplicantnatureofBusiness());
        contentValues.put(Applicant_PJI_Designation, personalInformation.getJointapplicantDesignation());
        contentValues.put(Applicant_PJI_R_I, personalInformation.getJointapplicantresidentialAddress());
        contentValues.put(Applicant_PJI_Business_Address, personalInformation.getJointapplicantbusinessAddress());
        contentValues.put(Applicant_PJI_Office_Contact_Number, personalInformation.getJointapplicantofficecontactNumber());
        contentValues.put(Applicant_PJI_R_I_Contact_Number, personalInformation.getJointapplicantresidentialcontactNumber());
        contentValues.put(Applicant_PJI_Cell_Number, personalInformation.getJointapplicantcellNumber());
        contentValues.put(Applicant_NOK_Courtesy_Title, personalInformation.getApplicantnokcourtesyTitle());
        contentValues.put(Applicant_NOK_Name, personalInformation.getApplicantnokName());
        contentValues.put(Applicant_NOK_RelationShip, personalInformation.getApplicantnokRelationship());
        contentValues.put(Applicant_NOK_CNIC, personalInformation.getApplicantnokCnic());
        contentValues.put(Applicant_NOK_Mailing_Address, personalInformation.getApplicantnokmailingAddress());
        contentValues.put(Applicant_NOK_Contact_Number, personalInformation.getApplicantnokcontactNumber());
        contentValues.put(Applicant_NOK_Cell_Number, personalInformation.getApplicantnokcellNumber());
        contentValues.put(Applicant_BAI_Business_Name, personalInformation.getApplicantbaibusinessName());
        contentValues.put(Applicant_BAI_Nature_Of_Business, personalInformation.getApplicantbainatureofBuisness());
        contentValues.put(Applicant_BAI_Registration_No, personalInformation.getApplicantbairegistrationNumber());
        contentValues.put(Applicant_BAI_NTN_No, personalInformation.getApplicantbaintnNumber());
        contentValues.put(Applicant_BAI_Date_Of_Registration, personalInformation.getApplicantbaidateofRegistration());
        contentValues.put(Applicant_BAI_Place_Of_Birth, personalInformation.getApplicantbaiplaceofRegistration());
        contentValues.put(Applicant_BAI_Office_Address, personalInformation.getApplicantbaiofficeAddress());
        contentValues.put(Applicant_BAI_Contact_Number, personalInformation.getApplicantbaicontactNumber());
        contentValues.put(Applicant_BAI_Fax_Number, personalInformation.getApplicantbaifaxNumber());
        contentValues.put(Applicant_DCI_Request_For_Debit_Card, personalInformation.getApplicantdcrequestfordepitCard());
        contentValues.put(Applicant_DCI_Name_On_Debit_Card, personalInformation.getApplicantdcnameofdebitCard());
        contentValues.put(Applicant_DCI_Maiden_Name, personalInformation.getApplicantdcmaidenName());
        contentValues.put(Applicant_SCI_Request_SCI, personalInformation.getApplicantscrequestsupplementaryCard());
        contentValues.put(Applicant_SCI_Full_Name_Of_Place_Holder, personalInformation.getApplicantscfullnameofplaceHolder());
        contentValues.put(Applicant_SCI_Date_Of_Birth, personalInformation.getApplicantscDob());
        contentValues.put(Applicant_SCI_Relationship, personalInformation.getApplicantscRelationship());
        contentValues.put(Applicant_SCI_CNIC, personalInformation.getApplicantscCnic());
        contentValues.put(Applicant_SCI_Expire_CNIC, personalInformation.getApplicantexpiryCnic());
        contentValues.put(Applicant_SCI_Maiden_Name, personalInformation.getApplicantscmaidenName());
        contentValues.put(Applicant_SCI_Name_Of_SCI, personalInformation.getApplicantscnameofsupplementaryCard());
        contentValues.put(EBanking_SMS_Alerts, personalInformation.getEbankingsmsAlerts());
        contentValues.put(EBanking_Internet_Banking, personalInformation.getEbankinginternetBanking());
        contentValues.put(EBanking_Mobile_Banking, personalInformation.getEbankingmobileBanking());
        contentValues.put(EBanking_EStatement, personalInformation.getEbankingeStatement());
        contentValues.put(EBanking_Frequency, personalInformation.getEbankingFrequency());

        int newaccountId = (int) bdd.insert(TABLE_APPLICATION_PERSONAL_INFO, null, contentValues);
        return newaccountId;
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
