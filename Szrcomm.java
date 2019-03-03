import java.lang.Object;
import java.io.*;
import java.net.*;
import org.w3c.dom.*;
import java.util.*;

public class szrcomm {
    // constants variables declaretion
    public static final String SSN_ID_IND           = "SY";
    public static final String VISA_TYPE_IND           = "V2";
    public static final String FIRST_NAME_IND 	    = "02";
    public static final String MIDDLE_NAME1_IND     = "03";
    public static final String MIDDLE_NAME2_IND     = "04";
    public static final String LAST_NAME_IND        = "05";
    public static final String PREFERED_NAME_IND        = "18";
 
    public static final String Person_Qualify_Code  	= "1";      // 1	Person
    public static final String Person_Name_Type_Code  	= "02";     // 02	Current Legal
    public static final String Person_Self_Code		= "S2";     // S2  	Self
    public static final String Person_Emrg_Contact_Code = "51";     // 51	Emergency Contact Code
    public static final String Date_Time_Qualify_Code   = "D8";     // D8	Date Expressed in Format CCYYMMDD
    
    // DMG
    public static final String US_Citizenship_Code  	= "1";      // 1	U.S. Citizen
    public static final String NON_US_Citizen_Code   	= "5";      // 5	Alien
    
    // Address N3_840
    public static final String Address_Default_Type 	= "F";	    // F        Default Type for Current Address

    // Com  
    public static final String Phone_Qualify_Code  	= "TE";     // TE	Telephone
    public static final String Night_Phone_Code  	= "NP";     // NP	Night Telephone
    public static final String Email_Code           	= "EM";     // EM	Electronic Mail
    public static final String Fax_Code           	= "FX";     // FX	Facsimile

    // Major FOS_1280
    public static final String Study_Type_Code  	= "M";      // M	Major
    public static final String Study_Code_Set_Ind	= "81";	    // ZZ	Mutually Defined

    // Residency_Code_List_Ind
    public static final String Residency_Code_Ind  	= "RI";      // RI	Residency Indicator
    public static final String Date_Time_Code		= "196";    // 196	Start
    
    // NON-US Info
    public static final String Visa_Type		= "V2";	    // V2	Visa Type
    public static final String I_20_Inst		= "V4";	    // V4	I_20
    public static final String App_Question_Iden	= "AQ";     // AQ       Question Identifier

    // high School
    public static final String CM_Date_Format  = "CM";     // CM	Date in Format CCYYMM
    public static final String HigSchool_Type		= "HS";	    // HS	High School
    public static final String Institution_Code_Qualify	= "73";     // 78	The College Board and ACT 6 digit code list of secondary educational institutions
    
    // Test and score
    public static final String Test_Date_Format  	= "D8";     // CM	Date Expressed in Format CCYYMMDD
    public static final String Test_Score_Code		= "3";	    // 3	Standard Score

    // Prev College PCL
    public static final String Date_Time_Period		= "RD5";   // RD5	Range of Dates Expressed in Format CCYYMM-CCYYMM
    
    // Applicant Master Question
    public static final String Discipline_Code		= "D1";	   
    public static final String Violation_Code		= "C1";	   
    public static final String Military_Code		= "V5";	   
    public static final String Veteran_Code		= "V1";	   
    public static final String Single_Parent_Code	= "P1";	   
    public static final String Disalibity_Code		= "DB";	
    public static final String Housing_Pref_Code	= "H1";	   
    public static final String Resident_Code		= "RS";	
   
    // Extra Activity
    public static final String Activity_Type_Code	= "SA";   // SA	Student Activity Type Code

    public static final String str_devide 	    	= "|";
    public static final String str_2_devide       	= "||";
    public static final String str_3_devide     	= "|||";
    public static final String str_4_devide      	= "||||";
    public static final String str_5_devide	    	= "|||||";
    public static final String str_6_devide	    	= "||||||";
    public static final String str_7_devide     	= "|||||||";
    public static final String str_8_devide     	= "||||||||";
    public static final String str_9_devide      	= "|||||||||";
    public static final String str_10_devide      	= "||||||||||";
    public static final String str_11_devide      	= "|||||||||||";
    public static final String str_12_devide      	= "||||||||||||";
    public static final String str_13_devide      	= "|||||||||||||";
    public static final String str_14_devide      	= "||||||||||||||";
    public static final String str_15_devide      	= "|||||||||||||||";
    public static final String str_16_devide      	= "||||||||||||||||";
    public static final String str_17_devide      	= "|||||||||||||||||";
    public static final String str_18_devide      	= "||||||||||||||||||";
    public static final String str_19_devide      	= "|||||||||||||||||||";
    public static final String str_20_devide      	= "||||||||||||||||||||";
    public static final String str_22_devide      	= "||||||||||||||||||||||";
    public static final String str_31_devide      	= "|||||||||||||||||||||||||||||||";
    public static final String str_36_devide      	= "||||||||||||||||||||||||||||||||||||";




    

    static InputStream theStreamToParse = null;
    static XMLDocument theXMLDoc     = null;
    static String str_txt = null;
    static int head_count = 0;

  void DoParser(String str_input) throws Exception {
   
    //XMLDocument theXMLDoc     = null;
    XMLNode theNode = null;
    String str_output;
    NodeList nl;
    NodeList nl2;
    int matches;
    int matches2;
    int i;
    int j;
    int k;  
    FileOutputStream destination = null;
    str_output = get_output_name(str_input);
    File destination_file = new File(str_output);
    PrintStream p;

    destination = new FileOutputStream(destination_file);

    matches = 0;
    matches2 = 0;
    str_txt = "";
    // Create an oracle.xml.parser.v2.DOMParser to parse the document.
    DOMParser theParser = new DOMParser();
    theStreamToParse = szrcomm.class.getResourceAsStream(str_input);
    if (theStreamToParse == null) {
      System.out.println("XML not found in the CLASSPATH.");
    }
   //  theParser.setValidationMode(false);
    theParser.parse( theStreamToParse );
    theXMLDoc = theParser.getDocument();

   String ele_value = "";
   String str_attach = "";
   String str_admission = "/applications";
   // String str_app_master ="/applications/application";
   
   // define the string variable
   nl2 = theXMLDoc.selectNodes("/applications/application");
   matches2 = nl2.getLength();
      System.out.println("The nl2 is " + matches2);
   for (k= 0; k< matches2; k++){
 
   String str_app_master = "/applications/application"+"[position() = " + k+ "+1]";

   process_head();
   
   //ssn REF_280   for SSN
   String str_ref = "REF_280";
   process_id_no(str_ref, theXMLDoc, str_app_master);

   // process IN1
   process_name_relationship_self(theXMLDoc, str_app_master);
  
   //the roop
   process_name(theXMLDoc, str_app_master);
   
   //ssn REF_440  for SSN
   str_ref = "REF_440";
   process_id_no(str_ref, theXMLDoc, str_app_master);
 
   // process visa type
   process_visa_type(theXMLDoc, str_app_master);

   // process_demo 
   process_demographic(theXMLDoc, str_app_master); 
 
   // process_religion 
   // the religion has been moved backe from suplmental part 
   process_religion(theXMLDoc, str_app_master); 
 
   // process permenent address
   process_perm_address(theXMLDoc, str_app_master); 
   
   // process mailing address address
   process_current_address(theXMLDoc, str_app_master); 

   // process parent1 address
   process_parent1_address(theXMLDoc, str_app_master); 
   
   // process parent2 address address
   process_parent2_address(theXMLDoc, str_app_master); 
   
   // Applicant master communication
   process_communication(theXMLDoc, str_app_master);

   // Planned_major
   process_term(theXMLDoc, str_app_master);
  
   // Major
   process_planned_major(theXMLDoc, str_app_master); 
  
   // process ceeb code to decide coll or high
   process_school_info(theXMLDoc, str_app_master);
     
   // process IN1
   process_name_relationship_parent1(theXMLDoc, str_app_master);
   
   //the roop for first parent name
   process_parent1_name(theXMLDoc, str_app_master);
   
   // process ACT test scores
   process_ACT_test(theXMLDoc, str_app_master);
   
   // process ACT test2 scores
   // Common App 2010 update remove the test score 2
   //process_ACT_test2(theXMLDoc, str_app_master);
   
   // process SAT test1 scores
   process_SAT_test(theXMLDoc, str_app_master);

   // process SAT test1 scores
   process_NEW_SAT_test(theXMLDoc, str_app_master);
   
   // process SAT test2 scores
   // Common App 2010 update remove the test score 2
   //process_SAT_test2(theXMLDoc, str_app_master);

   // process TOEFL test scores
   process_TOEFL_iBT_test(theXMLDoc, str_app_master);
   
   // process TOEFL test scores
   process_TOEFL_Paper_test(theXMLDoc, str_app_master);

   // Process activity
   process_activity(theXMLDoc, str_app_master); 

   // Process activity
   process_tr_activity(theXMLDoc, str_app_master); 
  
   // Process special program and load them like activity to the interest table 
   process_special_program(theXMLDoc, str_app_master); 
  
   // process admit type question answer 
   process_admit_type(theXMLDoc, str_app_master);
   
   // process to construct race and new ethnicity in question answer 
   process_race_ethn(theXMLDoc, str_app_master);
  
   // process for english not the first language Y/N answer 
   process_english_firstlanguage(theXMLDoc, str_app_master);
  
   // process veratan status with  Y/N answer 
   process_veratan_status(theXMLDoc, str_app_master);
   
   // process Native EnrollmentNumber 
   process_native_enrollmentNo(theXMLDoc, str_app_master);
   
   // process alumni/legacy info 
   process_legacy_info(theXMLDoc, str_app_master);
  
   // process common app client ID info 
   process_client_id_info(theXMLDoc, str_app_master);

   // process common app mailing end date info 
   process_mailing_end_date(theXMLDoc, str_app_master);
   
   // process to capture the highschool name for 000000 ceeb code 
   process_none_matching_schoolname(theXMLDoc, str_app_master);

   // process parent question answer 
   process_parent1_questions(theXMLDoc, str_app_master);
   
   // process secont parent question answer 
   process_parent2_questions(theXMLDoc, str_app_master);

   // process first generation question answer 
   process_first_generation_question(theXMLDoc, str_app_master);
 } 
 
   p = new PrintStream(destination);
   p.println(str_txt);
   p.close();
}
 /********************************************************************************************/
 /* Processing the head tag                          */
 /*********************************************************************************************/
  void process_head(){
  
        Calendar cld_date;

        int int_year = 0;
         String str_year = null;
 	int int_month = 0;
 	 String str_month = null;
	int int_day = 0;
  	 String str_day = null;
        String head_str = "";
        cld_date = Calendar.getInstance();

        int_year = cld_date.get( Calendar.YEAR ) ;
        // Note: 0 = January
        int_month = cld_date.get( Calendar.MONTH ) + 1;
        int_day = cld_date.get( Calendar.DAY_OF_MONTH );

        str_year = ("" + int_year).substring(2,4);

	if (int_month < 10)
		str_month = "0" + int_month;
        else
		str_month = "" + int_month;
	if (int_day < 10)
		str_day = "0" + int_day;
        else
		str_day = "" + int_day;

        head_str = head_str + "HEAD"
                 + str_devide + str_year 
		 + str_month + str_day + "|189|COMMAPPUG|public_senderId|00|USF_COMMAPP|010913|0823|PS|||";
       
       if(head_count == 0){ 
                head_count = head_count + 1;
                str_txt = str_txt + head_str;
       }
       else
       		str_txt = str_txt + "\n" + head_str;
     
  }

  
/*********************************************************************************************************
********************************* Process name relationshiop **************************************
***************************************************************************************************/ 
 void process_name_relationship_self(XMLDocument theDoc, String str_relationship) throws Exception
  {
   String str_value = "";
   String str_attach = "";
	str_attach = "IN1_440"
        	    +  str_devide + Person_Qualify_Code
		    + str_devide + Person_Name_Type_Code
                    + str_devide + Person_Self_Code
		    + str_2_devide;
        str_value = str_devide + "18"+ str_devide ;
        
        if(!str_value.equals(str_2_devide)){
		str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
  }

  
/*********************************************************************************************************
********************************* Process name relationshiop **************************************
***************************************************************************************************/ 
 void process_name_relationship_parent1(XMLDocument theDoc, String str_relationship) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_par_type = "";
	str_attach = "IN1_440";
        
   	str_par_type = theDoc.valueOf(str_relationship + "/Parent_1_Type");
        if(str_par_type.equals("Mother")){
           str_value =  str_devide + Person_Qualify_Code
		    + str_devide + Person_Name_Type_Code
                    + str_devide + Person_Self_Code
		    + str_2_devide +str_devide + "32" + str_devide;
 
        }
        else if(str_par_type.equals("Father")){
           str_value =  str_devide + Person_Qualify_Code
		    + str_devide + Person_Name_Type_Code
                    + str_devide + Person_Self_Code
		    + str_2_devide +str_devide + "33" + str_devide;
 
        }
        else if(str_par_type.equals("Legal Guardian")){
           str_value =  str_devide + Person_Qualify_Code
		    + str_devide + Person_Name_Type_Code
                    + str_devide + Person_Self_Code
		    + str_2_devide +str_devide + "03" + str_devide;
 
        }
        
        if(!str_par_type.equals("")){
		str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
  }
/**********************************************************************************************/
  /* Process name                */
  /* IN2_440|02   First Name     */
  /* IN2_440|03   Middle Name    */
  /* IN2_440|05   Last Name      */
  /* IN2_440|18|Cat   Prefer First Name */
  /**********************************************************************************************/
  void process_name(XMLDocument theDoc, String str_name) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   int flag = 0;

   	 str_value = theDoc.valueOf(str_name + "/First_name");
        if(!str_value.equals("")){ 
   		str_attach = "IN2_440" 
			   + str_devide + FIRST_NAME_IND
			   + str_devide + str_value;
    
		attach_string_totxt(str_attach);
        }
        // middle is needed
   	 str_value = theDoc.valueOf(str_name + "/Middle_name");
        if(!str_value.equals("")){ 
   		str_attach = "IN2_440" 
			   + str_devide + MIDDLE_NAME1_IND
			   + str_devide + str_value;
  		attach_string_totxt(str_attach);
        } 

   	 str_value = theDoc.valueOf(str_name + "/Last_name");
        if(!str_value.equals("")){ 
   		str_attach = "IN2_440"
			   + str_devide + LAST_NAME_IND
			   + str_devide + str_value;
  		attach_string_totxt(str_attach);
        } 
        // prefered name 
   	str_value = theDoc.valueOf(str_name + "/Preferred_name");
         
        if(!str_value.equals("")){ 
   		str_attach = "IN2_440" 
			   + str_devide + PREFERED_NAME_IND
			   + str_devide + str_value;
  		attach_string_totxt(str_attach);
        } 
   }  

/**********************************************************************************************/
  /* Process parent name                */
  /* IN2_440|02   First Name     */
  /* IN2_440|03   Middle Name    */
  /* IN2_440|05   Last Name      */
  /**********************************************************************************************/
  void process_parent1_name(XMLDocument theDoc, String str_name) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   int flag = 0;

   	str_value = theDoc.valueOf(str_name + "/Parent_1_First_Name");
   
   		str_attach = "IN2_440" 
			   + str_devide + FIRST_NAME_IND
			   + str_devide;
    
        if(!str_value.equals("")){
		str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
        // middle is needed
   	str_value = theDoc.valueOf(str_name + "/Parent_1_Middle");
       
   		str_attach = "IN2_440" 
			   + str_devide + MIDDLE_NAME1_IND
			   + str_devide ;
       
        if(!str_value.equals("")){
		str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
   	str_value = theDoc.valueOf(str_name + "/Parent_1_Last_Name");
   	
   		str_attach = "IN2_440"
			   + str_devide + LAST_NAME_IND
			   + str_devide ;
        if(!str_value.equals("")){
		str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
        
   }  

/************************************************************************************************
  **********************      process ssn             ******************************************
  *************************************************************************************************/
   void process_id_no(String str_ref, XMLDocument theDoc, String str_id_no) throws Exception
  {
   String str_value = "";
   String str_attach = "";
        str_attach = str_ref;
   	str_value = str_devide + SSN_ID_IND + str_devide + remove_ssn_hyphen(theDoc.valueOf(str_id_no + "/SSN"))
		   + str_devide;
        str_attach = str_attach +  str_value + str_devide;
                // str_attach = str_attach +  str_value;
	         attach_string_totxt(str_attach);

  }

 /************************************************************************************************
  **********************      process visa type          ******************************************
  *************************   VISA_TYPE_IND      = "V2"    ***************************************
 *********** 1. international student blank visa type cross walk into Banner as F1 Visa.***********
  *************************************************************************************************/
   void process_visa_type(XMLDocument theDoc, String str_visa_type) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_visa = "";
   String str_citizen = "";
   String str_citizen_Status = "";


   str_citizen_Status = theDoc.valueOf(str_visa_type + "/Citizenship_status");

   if (str_citizen_Status.equals("Citizen"))
         // us citizen 1
         str_citizen = US_Citizenship_Code;
   else if (str_citizen_Status.equals("Dual_Citizen"))
         // us dual citizen 1
         str_citizen = US_Citizenship_Code;
   else if (str_citizen_Status.equals("Resident"))
         //U.S. Permanebt Resident 3 
         str_citizen = "3";

   else {
         str_citizen = "2";


         str_attach = "REF_440";
         str_visa = theDoc.valueOf(str_visa_type + "/Visa_type");
         if (str_visa.equals("") || str_visa.equals("N"))
             str_visa = "F1";

   	 str_value = str_devide + VISA_TYPE_IND + str_devide + str_visa	+ str_devide + "Visa Type";

         if (!str_visa.equals("")){

        	str_attach = str_attach +  str_value;
	        attach_string_totxt(str_attach);
                }
	}
  }
 /*********************************************************************************************/
  /* Process demorgraphic   DMG_440            */
  /* DMG_440|D8|19860218|F||W|1|US|||||||||||||| */
  /**********************************************************************************************/
void process_demographic(XMLDocument theDoc, String str_demo) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_citizen = "";
   String str_lang = "";
   String str_lang1 = "";
   String str_lang2 = "";
   String str_lang3 = "";
   String str_lang1prof = "";
   String str_lang2prof = "";
   String str_lang3prof = "";
   String str_AfricanAmerican = ""; 
   String str_AsianAmerican = ""; 
   String str_AsianIndian = ""; 
   String str_HispanicLatino= ""; 
   String str_MexicanAmerican = ""; 
   String str_NativeHawaiianPacific= ""; 
   String str_PuertoRican = ""; 
   String str_Caucasian = ""; 
   String str_NativeAmerAlaska = ""; 
   String str_Other= ""; 
   
   str_attach = "DMG_440"
		+ str_devide + Date_Time_Qualify_Code;     // D8

   str_value = process_birthdate( theDoc.valueOf(str_demo + "/Date_of_birth"));   // required fields
   str_attach = str_attach + str_devide + str_value;
  
   str_value = theDoc.valueOf(str_demo + "/Sex");
   if  (str_value.equals("F"))
         str_value = "F";
   else if  (str_value.equals("M"))
         str_value = "M";
   else  
         str_value = "U";
   str_attach = str_attach + str_devide + str_value;

   // no marital data
   str_attach = str_attach + str_devide;
   
   // ethnicity
  


   //Race or Ethnicity Code
   str_value = "Y";    
   str_attach = str_attach + str_devide + str_value;
   
   // get_Citizenship_Status_Code
   str_value = theDoc.valueOf(str_demo + "/Citizenship_status");
   if (str_value.equals("Citizen"))
         // us citizen 1
         str_citizen = US_Citizenship_Code;
   else if (str_value.equals("Dual_Citizen"))
         // us dual citizen 1
         str_citizen = US_Citizenship_Code;
   else if (str_value.equals("Resident"))
         //U.S. Permanebt Resident 3 
         str_citizen = "3";
   else 
         str_citizen = "2";

   str_attach = str_attach + str_devide + str_citizen;
   
   // DMG 07  country of citizen 
   // str_value = theDoc.valueOf(str_demo + "/DualCitizenshipCountry1");
    
   if(str_citizen.equals("1") )
          str_value = "USA";
   else
          str_value = theDoc.valueOf(str_demo + "/Citizenships");
          
   str_attach = str_attach + str_devide + country_transfer(str_value);
   // three |||
   str_attach = str_attach + str_3_devide;
   // DMG 11 country of birth
   //str_value = theDoc.valueOf(str_demo + "/BirthCity");
   //str_attach = str_attach + str_devide;
   //str_value = theDoc.valueOf(str_demo + "/BirthState");
   //str_attach = str_attach + str_value + str_devide;
   
   // DMG country of Birth
   str_value = country_transfer(theDoc.valueOf(str_demo + "/Birth_country"));
   str_attach = str_attach + str_value + str_4_devide;

   str_lang1 = theDoc.valueOf(str_demo + "/Language_1");
   str_lang2 = theDoc.valueOf(str_demo + "/Language_2");
   str_lang3 = theDoc.valueOf(str_demo + "/Language_3");
   str_lang1prof = theDoc.valueOf(str_demo + "/Language_1_proficiency");
   str_lang2prof = theDoc.valueOf(str_demo + "/Language_2_proficiency");
   str_lang3prof = theDoc.valueOf(str_demo + "/Language_3_proficiency");

     //if (!str_lang1.equals("") && (str_lang2.length()==0) && (str_lang3.length()==0) && str_lang1prof.indexOf("F")!= -1 ){
   
     if (!str_lang1.equals("") && str_lang2.equals("") && str_lang3.equals("") ){
     
         if (str_lang1prof.indexOf("F") != -1) 
               str_value = language_transfer(str_lang1); 
         else
               str_value = "XXX";
          
     }else if (!str_lang1.equals("") && !str_lang2.equals("") && str_lang3.equals("")){
  	   
           if ( str_lang1prof.indexOf("F") != -1 )  
               str_value = language_transfer(str_lang1 );
           else if (str_lang2prof.indexOf("F") != -1 )  
               str_value = language_transfer(str_lang2 );
           else 
                 str_value = "XXX";
         
     }else if (!str_lang1.equals("") && !str_lang2.equals("") && !str_lang3.equals("")){
           
  	   if ( str_lang1prof.indexOf("F") != -1 )  
               str_value = language_transfer(str_lang1 );
           else if (str_lang2prof.indexOf("F") != -1 )  
               str_value = language_transfer(str_lang2 );
           else if (str_lang3prof.indexOf("F")!= -1)  
               str_value = language_transfer(str_lang3 );
           else 
                 str_value = "XXX";

      }else 
                 str_value = "XXX";
 
   str_attach = str_attach + str_value;
   str_attach = str_attach + str_7_devide;
   attach_string_totxt(str_attach); 
  }   
   
 /*********************************************************************************************/
 /********************* Process Address III _440    */
 /*********************************************************************************************/

   void process_religion(XMLDocument theDoc, String str_relg) throws Exception
   {
   String str_value = "";
   String str_attach = "";
   String str_default = "";
   String str_religion = "";

        str_attach = "III_440";
        //str_religion = theDoc.valueOf(str_relg + "/Religious_preference");
	str_religion = theDoc.valueOf(str_relg + "/Religious_preference");
         str_value = str_devide + religion_transfer(str_religion) 
		    + str_devide;
        if (!str_religion.equals("")){
        	str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
  } 

 /*********************************************************************************************/
 /********************* Process Address  N3_840    */
 /*********************************************************************************************/
   void process_perm_address(XMLDocument theDoc, String str_address) throws Exception
   {
   String str_value = "";
   String str_attach = "";
   String str_default = "";
   String str_address1 = "";
   String str_address2= "";
   String str_address3= "";
   String str_state = "";
   String str_city = "";
   String str_zip="";
   String str_country="";

        str_attach = "N3_840";
        str_address1 = theDoc.valueOf(str_address + "/Permanent_address_-_Address1");
        str_address2 = theDoc.valueOf(str_address + "/Permanent_address_-_Address2");
        str_address3 = theDoc.valueOf(str_address + "/Permanent_address_-_Address3");
        if(str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address3;
        else if(!str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address2 + "," + str_address3;
        else
              str_address2= str_address2;

        str_city = theDoc.valueOf(str_address + "/Permanent_address_-_City");
        str_state =  theDoc.valueOf(str_address + "/Permanent_address_-_State");
        str_zip = theDoc.valueOf(str_address + "/Permanent_address_-_Zip");
        str_country = theDoc.valueOf(str_address + "/Permanent_address_-_Country");

        if(str_state.equals(""))
		str_state = "";
                 
        if (str_address1.length()>75)
	        str_address1 = str_address1.substring(0, 75);  
        if (str_address2.length()>75)
	        str_address2 = str_address2.substring(0, 75);  

        if (str_city.length()>30)
	        str_city = str_city.substring(0, 30); 
        
        if (str_state.length()>3)
	        str_state = str_state.substring(0, 3); 

         str_value = str_devide + str_address1 
                  + str_devide + str_address2
   		  + str_devide + str_city
  	         + str_devide + str_state
   		  + str_devide + str_zip
   		  + str_devide + country_transfer(str_country)
        	  + str_devide + "p" 
		  + str_devide;
        if (!str_value.equals(str_8_devide) && !str_value.equals("|||||||p|")){
        	str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
  } 

 /*********************************************************************************************/
 /********************* Process current Address  N3_840    **************************************/
 /*********************************************************************************************/

   void process_current_address(XMLDocument theDoc, String str_address) throws Exception
   {
   String str_value = "";
   String str_attach = "";
   String str_default = "";
   String str_address1 = "";
   String str_address2= "";
   String str_address3= "";
   String str_state = "";
   String str_city = "";
   String str_zip="";
   String str_country="";

        str_attach = "N3_840";
        str_address1 = theDoc.valueOf(str_address + "/Current_address_-_Address1");
        str_address2 = theDoc.valueOf(str_address + "/Current_address_-_Address2");
        str_address3 = theDoc.valueOf(str_address + "/Current_address_-_Address3");
 	 if(str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address3;
        else if(!str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address2 + "," + str_address3;
        else
              str_address2= str_address2;

        str_city = theDoc.valueOf(str_address + "/Current_address_-_City ");
        str_state =  theDoc.valueOf(str_address + "/Current_address_-_State");
        str_zip = theDoc.valueOf(str_address + "/Current_address_-_Zip");
        str_country = theDoc.valueOf(str_address + "/Current_address_-_Country");

        if (str_address1.length()>75)
	        str_address1 = str_address1.substring(0, 75);  
        if (str_address2.length()>75)
	        str_address2 = str_address2.substring(0, 75);  

        if (str_city.length()>30)
	        str_city = str_city.substring(0, 30); 
        if (str_state.length()>3)
	        str_state = str_state.substring(0, 3); 

        str_value = str_devide + str_address1 
                  + str_devide + str_address2 
   		  + str_devide + str_city
  	         + str_devide + str_state
   		  + str_devide + str_zip
   		  + str_devide + country_transfer(str_country)
        	  + str_devide + "M" 
		  + str_devide;
        if (!str_value.equals(str_8_devide) && !str_value.equals("|||||||M|")){
        	str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
}

 /*********************************************************************************************/
 /********************* Process parent1 Address  N3_840    */
 /*********************************************************************************************/

   void process_parent1_address(XMLDocument theDoc, String str_address) throws Exception
   {
   String str_value = "";
   String str_attach = "";
   String str_default = "";
   String str_address1 = "";
   String str_address2= "";
   String str_address3= "";
   String str_city = "";
   String str_state = "";
   String str_sameAddress = "";
   String str_zip="";
   String str_country="";

       str_attach = "N3_840";
      str_sameAddress = theDoc.valueOf(str_address + "/Parent_1_address_same_or_not");
      if(str_sameAddress.equals("1")){ 
         str_address1 = theDoc.valueOf(str_address + "/Permanent_address_-_Address1");
         str_address2 = theDoc.valueOf(str_address + "/Permanent_address_-_Address2");
         str_address3 = theDoc.valueOf(str_address + "/Permanent_address_-_Address3");
  	  if(str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address3;
         else if(!str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address2 + "," + str_address3;
         else
              str_address2= str_address2;

         str_city = theDoc.valueOf(str_address + "/Permanent_address_-_City");
         str_state =  theDoc.valueOf(str_address + "/Permanent_address_-_State");
         str_zip =  theDoc.valueOf(str_address + "/Permanent_address_-_Zip"); 
         str_country = theDoc.valueOf(str_address + "/Permanent_address_-_Country");

         if(str_state.equals(""))
		str_state = "";
                 
         if (str_address1.length()>75)
	        str_address1 = str_address1.substring(0, 75);  
         if (str_address2.length()>75)
	        str_address2 = str_address2.substring(0, 75);  
         if (str_city.length()>30)
	        str_city = str_city.substring(0, 30);   
        if (str_state.length()>3)
	        str_state = str_state.substring(0, 3); 

          str_value = str_devide + str_address1 
                  + str_devide + str_address2
   		  + str_devide + str_city
  	         + str_devide + str_state
   		  + str_devide + str_zip
   		  + str_devide + country_transfer(str_country)
        	  + str_devide + "1" 
		  + str_devide;
        }
        else if(str_sameAddress.equals("0")){
          str_address1 = theDoc.valueOf(str_address + "/Parent_1_Address_-_Address1");
          str_address2 = theDoc.valueOf(str_address + "/Parent_1_Address_-_Address2");
          str_address3 = theDoc.valueOf(str_address + "/Parent_1_Address_-_Address3");

  	  if(str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address3;
         else if(!str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address2 + "," + str_address3;
         else
              str_address2= str_address2;

          str_city = theDoc.valueOf(str_address + "/Parent_1_Address_-_City");
          str_state =  theDoc.valueOf(str_address + "/Parent_1_Address_-_State");
          str_zip =  theDoc.valueOf(str_address + "/Parent_1_Address_-_Zip ");
          str_country = theDoc.valueOf(str_address + "/Parent_1_Address_-_Country");

          if(str_state.equals(""))
		str_state = "";
                 
          if (str_address1.length()>75)
	        str_address1 = str_address1.substring(0, 75);  
          if (str_address2.length()>75)
	        str_address2 = str_address2.substring(0, 75);  
          if (str_city.length()>30)
	        str_city = str_city.substring(0, 30);  
        if (str_state.length()>3)
	        str_state = str_state.substring(0, 3);

           str_value = str_devide + str_address1 
                  + str_devide + str_address2
   		  + str_devide + str_city
  	          + str_devide + str_state
   		  + str_devide + str_zip 
   		  + str_devide + country_transfer(str_country)
        	  + str_devide + "1" 
		  + str_devide;
        }
 
        if (!str_value.equals("") && !str_value.equals("|||||||1|")){
        	str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
  } 

 /*********************************************************************************************/
 /********************* Process parent2  Address  N3_840    */
 /*********************************************************************************************/

   void process_parent2_address(XMLDocument theDoc, String str_address) throws Exception
   {
   String str_value = "";
   String str_attach = "";
   String str_default = "";
   String str_address1 = "";
   String str_address2= "";
   String str_address3= "";
   String str_city = "";
   String str_state = "";
   String str_sameAddress = "";
   String str_zip="";
   String str_country="";

       str_attach = "N3_840";
      str_sameAddress = theDoc.valueOf(str_address + "/Parent_2_address_same_or_not");
      if(str_sameAddress.equals("1")){ 
         str_address1 = theDoc.valueOf(str_address + "/Permanent_address_-_Address1");
         str_address2 = theDoc.valueOf(str_address + "/Permanent_address_-_Address2");
         str_address3 = theDoc.valueOf(str_address + "/Permanent_address_-_Address3");

  	 if(str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address3;
        else if(!str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address2 + "," + str_address3;
        else
              str_address2= str_address2;

         str_city = theDoc.valueOf(str_address + "/Permanent_address_-_City");
         str_state =  theDoc.valueOf(str_address + "/Permanent_address_-_State");
         str_zip =  theDoc.valueOf(str_address + "/Permanent_address_-_Zip"); 
         str_country = theDoc.valueOf(str_address + "/Permanent_address_-_Country");

         if(str_state.equals(""))
		str_state = "";
                 
         if (str_address1.length()>75)
	        str_address1 = str_address1.substring(0, 75);  
         if (str_address2.length()>75)
	        str_address2 = str_address2.substring(0, 75);  
         if (str_city.length()>30)
	        str_city = str_city.substring(0, 30);   
        if (str_state.length()>3)
	        str_state = str_state.substring(0, 3);

          str_value = str_devide + str_address1 
                  + str_devide + str_address2
   		  + str_devide + str_city
  	         + str_devide + str_state
   		  + str_devide + str_zip
   		  + str_devide + country_transfer(str_country)
        	  + str_devide + "2" 
		  + str_devide;
        }
       else if(str_sameAddress.equals("0")){
          str_address1 = theDoc.valueOf(str_address + "/Parent_2_Address_-_Address1");
          str_address2 = theDoc.valueOf(str_address + "/Parent_2_Address_-_Address2");
          str_address3 = theDoc.valueOf(str_address + "/Parent_2_Address_-_Address3");

        if(str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address3;
        else if(!str_address2.equals("")&& !str_address3.equals(""))
		str_address2 = str_address2 + "," + str_address3;
        else
              str_address2= str_address2;

          str_city = theDoc.valueOf(str_address + "/Parent_2_Address_-_City");
          str_state =  theDoc.valueOf(str_address + "/Parent_2_Address_-_State");
          str_zip =  theDoc.valueOf(str_address + "/Parent_2_Address_-_Zip ");
          str_country = theDoc.valueOf(str_address + "/Parent_2_Address_-_Country");

          if(str_state.equals(""))
		str_state = "";
                 
          if (str_address1.length()>75)
	        str_address1 = str_address1.substring(0, 75);  
          if (str_address2.length()>75)
	        str_address2 = str_address2.substring(0, 75);  
          if (str_city.length()>30)
	        str_city = str_city.substring(0, 30);  
          if (str_state.length()>3)
	        str_state = str_state.substring(0, 3);

           str_value = str_devide + str_address1 
                  + str_devide + str_address2
   		  + str_devide + str_city
  	          + str_devide + str_state
   		  + str_devide + str_zip 
   		  + str_devide + country_transfer(str_country)
        	  + str_devide + "2" 
		  + str_devide;
        }

        if (!str_value.equals("") && !str_value.equals("|||||||2|")){
        	str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
  } 

 /**********************************************************************************************
  *  Process Communication                      *
  *  "TE";       Telephone                      *
  *  "NP";       Night Telephone                *
  *  "EM";       EM	Electronic Mail         *
  *  "FX";       FX	Facsimile               *
  *      AP	Alternate Telephone             *
  * 	AS	Answering Service               *
  * 	CP	Cellular Phone                  *
  * 	EX	Telephone Extension             *
  * 	HF	Home Facsimile Number           *
  * 	HP	Home Phone Number               *
  *    	IT	International Telephone         *
  * 	WP	Work Phone Number               *
  **********************************************************************************************/
 void process_communication(XMLDocument theDoc, String xpath) throws Exception
  {
   int matches;
   int i;
   String str_value = "";
   String str_attach = "";
   String str_prefer_type = "";
   String str_alt_type = "";
   String string_email = "";

   String str_xpath =  xpath;
        
               
       str_attach = "COM_960";
        str_prefer_type = phone_type_crosswalk(theDoc.valueOf(str_xpath + "/Preferred_phone"));
        str_value =  theDoc.valueOf(str_xpath + "/Preferred_phone_number");
        if (str_value != null && (!str_value.equals("")) ) 
           {  
                str_value = str_devide + str_prefer_type 
			  + str_devide + phone_number_cleanup(str_value);
   	      	str_attach = str_attach + str_value;
              	attach_string_totxt(str_attach);
           }

       str_attach = "COM_960";
        str_alt_type = "TE";
        str_value =  theDoc.valueOf(str_xpath + "/Alternate_phone_number");
        if (str_value != null && (!str_value.equals("")) ) 
           {  
                str_value = str_devide + str_alt_type 
			  + str_devide + phone_number_cleanup(str_value);
   	      	str_attach = str_attach + str_value;
              	attach_string_totxt(str_attach);
           }

      
        str_attach = "COM_960";
   	str_value =  theDoc.valueOf(str_xpath + "/Parent_1_Phone");
        if (str_value != null && (!str_value.equals("")) ) 
           {  
	        // P1 as parent1 phone	
                str_value = str_devide + "P1"
			  + str_devide + phone_number_cleanup(str_value);
   	      	str_attach = str_attach + str_value;
              	attach_string_totxt(str_attach);
           }
        
        str_attach = "COM_960";
   	str_value =  theDoc.valueOf(str_xpath + "/Parent_1_Phone");
        if (str_value != null && (!str_value.equals("")) ) 
           {  
	        // P1 as parent2 phone	
                str_value = str_devide + "P2"
			  + str_devide + phone_number_cleanup(str_value);
   	      	str_attach = str_attach + str_value;
              	attach_string_totxt(str_attach);
           }

       //Email
       str_attach = "COM_960";
   	str_value = theDoc.valueOf(str_xpath + "/Email_address");
        if (str_value != null && (!str_value.equals("")) ) 
           {  
		str_value = str_devide + Email_Code 
			  + str_devide + str_value;
   	      	str_attach = str_attach + str_value;
              	attach_string_totxt(str_attach);
           }
   	
  }   
   
 /*********************************************************************************************/
  /* Process term code            SSE_1280                                                     */
  /*********************************************************************************************/
  void process_term(XMLDocument theDoc, String str_term) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_level_code = "";
   String str_level_number = "";
   String str_stu_type = "";     

        str_stu_type= theDoc.valueOf(str_term + "/Student_Type");
        str_attach = "SSE_1280";
        // temperally using 2.4 for UG
       str_level_number = "2.4";
       if (str_stu_type.equals("TR")){
   		str_value =  theDoc.valueOf(str_term + "/Preferred_start_term_FromTr");
       }else {
   		str_value =  theDoc.valueOf(str_term + "/Preferred_start_term");
       }
       if (!str_value.equals(""))
   		str_value = str_devide + banner_termcode_to_year(str_value) 
		  + str_4_devide + str_level_number + str_4_devide;
       else
		str_value = str_devide + "20991001" 
		  + str_4_devide + str_level_number + str_4_devide;

	if(str_value != str_9_devide){
		str_attach = str_attach + str_value;
   		attach_string_totxt(str_attach);
	}
  } 


 /***********************************************************************************************/
 /* Processs planned major FOS_1280                    */
 /* Academic Field of Study Level or Type Code = "M"   */
 /* Study Code Set Indicator = "ZZ"                    */
 /***********************************************************************************************/
  void process_planned_major(XMLDocument theDoc, String str_planned_major) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_program = "";
   String str_major = "";
   String str_stu_type = "";     
        str_stu_type= theDoc.valueOf(str_planned_major + "/Student_Type");

        str_attach = "FOS_1280"
		   + str_devide + Study_Type_Code  
		   + str_devide + Study_Code_Set_Ind;
        if (str_stu_type.equals("TR")){
        	str_program =  theDoc.valueOf(str_planned_major+ "/Academic_Program_FromTr");
        }else{
              str_program =  theDoc.valueOf(str_planned_major+ "/Academic_Program");
        }
        //str_major = str_program;
        str_major = ay_major_capture(str_program.trim()); 
        str_value = str_devide + str_major
		  + str_2_devide;
	 if (!str_value.equals(str_3_devide)){           // this may not need
	   str_attach = str_attach + str_value;
	    attach_string_totxt(str_attach);
        }
  }
 
 /*****************************************************************************/
  /* process_ceeb code to decide whether highschool or college code  ************/
  /*****************************************************************************/
  void process_school_info(XMLDocument theDoc, String str_school) throws Exception
  {
   String str_ceeb = "";
   String str_stu_type;     
   int n=0;
            str_stu_type= theDoc.valueOf(str_school + "/Student_Type");
            if (str_stu_type.equals("FY")){
            	    process_fy_inst(theDoc, str_school);
            }
            else if(str_stu_type.equals("TR")){

                  process_tr_inst(theDoc, str_school);

            } 
}

 /*****************************************************************************/
  /* process_ceeb code to decide whether highschool or college code  ************/
  /*****************************************************************************/
  void process_fy_inst(XMLDocument theDoc, String str_school) throws Exception
  {
       process_first_highschool(theDoc, str_school);
       process_second_highschool(theDoc, str_school);
       process_third_highschool(theDoc, str_school);

 }

 /*****************************************************************************/
  /* process_ceeb code to decide whether highschool or college code  ************/
  /*****************************************************************************/
  void process_tr_inst(XMLDocument theDoc, String str_school) throws Exception
  {
       process_tr_first_college(theDoc, str_school);
       process_tr_other1_college(theDoc, str_school);
       process_tr_other2_college(theDoc, str_school);
       process_tr_other3_college(theDoc, str_school);
       process_tr_first_high_or_coll(theDoc, str_school);
       process_tr_second_high_or_coll(theDoc, str_school);
       process_tr_third_high_or_coll(theDoc, str_school);


 }

  /*****************************************************************************/
  /* process_highschool SST_1680, N1_1680, N3_1680 and SUM_1680     ************/
  /* SST_1680|B18|CM|201212||||||||||        	*/
  /* N1_1680|HS||73|030422|| 			*/
  /* SST_1680|B18|CM|201101||||||||||		*/
  /* N1_1680|HS||73|320626||			*/
  /* SST_1680|B18|CM|201105||||||||||		*/
  /* N1_1680|HS||73|331635||			*/
  /*****************************************************************************/
  void process_first_highschool(XMLDocument theDoc, String str_highschool) throws Exception
  {
   String str_empty = "";
   String str_value = "";
   String str_attach = "";
   String str_high_ceeb = "";    
   String str_high_end_date = "";  
   int n=0;
 
           //SST_1680
	    str_attach = "SST_1680";
            // use the stand high school type   B18	Standard high school diploma
           str_high_ceeb = theDoc.valueOf(str_highschool + "/School_lookup_-_CeebCode");
           str_high_end_date = theDoc.valueOf(str_highschool + "/Graduation_date");

	    str_value = str_devide + "B18"  
		   + str_devide + CM_Date_Format
                 + str_devide + format_mdy_date(str_high_end_date)
		   + str_10_devide;
            str_empty = str_devide +"B18" + str_devide + CM_Date_Format + str_11_devide;
       if (!str_high_ceeb.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
             
           // N1_1680
	     str_attach = "N1_1680";
            //str_high_ceeb = theDoc.valueOf(str_highschool + "/School_lookup_-_CeebCode");
            n=str_high_ceeb.length();
            if(n == 5) {
                   str_high_ceeb = "0"+str_high_ceeb;
            }
	       str_value = str_devide + HigSchool_Type
                   + str_devide 
		     + str_devide + Institution_Code_Qualify
                   + str_devide + str_high_ceeb 
                   + str_2_devide;  
      		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
        }
  }
  /*****************************************************************************/
  /* TR_Secondary_school_1_lookup_-_CeebCod */
  /* N1_1680|HS||73|331635||			*/
  /*****************************************************************************/
  void process_tr_first_high_or_coll(XMLDocument theDoc, String str_school) throws Exception
{
   String str_ceeb = "";     
   int n=0;
            
            str_ceeb = str_ceeb = theDoc.valueOf(str_school + "/TR_Secondary_school_1_lookup_-_CeebCode");
            n=str_ceeb.length();
            if(n == 4){
                  process_TR_Secondary_school_1_as_coll(theDoc, str_school);
            }
            else {

                  process_TR_Secondary_school_1_as_high(theDoc, str_school);

            } 

}

  /*****************************************************************************/
  /* TR_Secondary_school_2_lookup_-_CeebCod */
  /* N1_1680|HS||73|331635||			*/
  /*****************************************************************************/
  void process_tr_second_high_or_coll(XMLDocument theDoc, String str_school) throws Exception
{
   String str_ceeb = "";     
   int n=0;
            
            str_ceeb = str_ceeb = theDoc.valueOf(str_school + "/TR_Secondary_school_2_lookup_-_CeebCode");
            n=str_ceeb.length();
            if(n == 4){
                  process_TR_Secondary_school_2_as_coll(theDoc, str_school);
            }
            else {

                  process_TR_Secondary_school_2_as_high(theDoc, str_school);

            } 

}

  /*****************************************************************************/
  /* TR_Secondary_school_3_lookup_-_CeebCod */
  /* N1_1680|HS||73|331635||			*/
  /*****************************************************************************/
  void process_tr_third_high_or_coll(XMLDocument theDoc, String str_school) throws Exception
{
   String str_ceeb = "";     
   int n=0;
            
            str_ceeb = str_ceeb = theDoc.valueOf(str_school + "/TR_Secondary_school_3_lookup_-_CeebCode");
            n=str_ceeb.length();
            if(n == 4){
                  process_TR_Secondary_school_3_as_coll(theDoc, str_school);
            }
            else {

                  process_TR_Secondary_school_3_as_high(theDoc, str_school);

            } 

}
  /*****************************************************************************/
  /* process_highschool SST_1680, N1_1680, N3_1680 and SUM_1680     ************/
  /* SST_1680|B18|CM|201212||||||||||        	*/
  /* N1_1680|HS||73|030422|| 			*/
  /* SST_1680|B18|CM|201101||||||||||		*/
  /* N1_1680|HS||73|320626||			*/
  /* SST_1680|B18|CM|201105||||||||||		*/
  /* N1_1680|HS||73|331635||			*/
  /*****************************************************************************/
  void process_TR_Secondary_school_1_as_high(XMLDocument theDoc, String str_highschool) throws Exception
  {
   String str_empty = "";
   String str_value = "";
   String str_attach = "";
   String str_high_ceeb = "";  
   String str_high_end_date = "";  
   int n=0;  
        //SST_1680
	    str_attach = "SST_1680";
            // use the stand high school type   B18	Standard high school diploma
           str_high_ceeb = theDoc.valueOf(str_highschool + "/TR_Secondary_school_1_lookup_-_CeebCode");
           str_high_end_date = theDoc.valueOf(str_highschool + "/TR_Secondary_school_1_to_date");
     
	    str_value = str_devide + "B18"  
		   + str_devide + CM_Date_Format
                 + str_devide + format_mdy_date(str_high_end_date)
		   + str_10_devide;
            str_empty = str_devide +"B18" + str_devide + CM_Date_Format + str_11_devide;
       if (!str_high_ceeb.equals("")){
           if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
             }
          // N1_1680
	     str_attach = "N1_1680";
            //str_high_ceeb = theDoc.valueOf(str_highschool + "/TR_Secondary_school_1_lookup_-_CeebCode");
            n=str_high_ceeb.length();
            if(n == 5) {
                   str_high_ceeb = "0"+str_high_ceeb;
             }

	     str_value = str_devide + HigSchool_Type
                   + str_devide 
		   + str_devide + Institution_Code_Qualify
                   + str_devide + str_high_ceeb 
                   + str_2_devide;  
	
            str_empty = str_devide + HigSchool_Type + str_2_devide 
		   + Institution_Code_Qualify + str_3_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
	    }	
       }
}

  /*****************************************************************************/
  /* process_highschool SST_1680, N1_1680, N3_1680 and SUM_1680     ************/
  /*****************************************************************************/
  void process_second_highschool(XMLDocument theDoc, String str_highschool) throws Exception
  {
   String str_empty = "";
   String str_value = "";
   String str_attach = "";
   String str_high_ceeb = ""; 
   String str_high_end_date = "";   
   int n=0;     
        //SST_1680
	    str_attach = "SST_1680";
            // use the stand high school type   B18	Standard high school diploma
           str_high_ceeb = theDoc.valueOf(str_highschool + "/School_2_lookup_-_CeebCode");
           str_high_end_date = theDoc.valueOf(str_highschool + "/School_2_to_date");

	   str_value = str_devide + "B18"  
		   + str_devide + CM_Date_Format
                   + str_devide + format_month_year_date(str_high_end_date)
		   + str_10_devide;
            str_empty = str_devide +"B18" + str_devide + CM_Date_Format + str_11_devide;
       if (!str_high_ceeb.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
             
           // N1_1680
	    str_attach = "N1_1680";
            //str_high_ceeb = theDoc.valueOf(str_highschool + "/School_2_lookup_-_CeebCode");
            n=str_high_ceeb.length();
            if(n == 5) {
                   str_high_ceeb = "0"+str_high_ceeb;
            }
        
	     str_value = str_devide + HigSchool_Type
                   + str_devide 
		     + str_devide + Institution_Code_Qualify
                   + str_devide + str_high_ceeb 
                   + str_2_devide;  
            str_empty = str_devide + HigSchool_Type + str_2_devide 
		   + Institution_Code_Qualify + str_3_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
	    }	
       }
}

 /*****************************************************************************/
  /* process_highschool SST_1680, N1_1680, N3_1680 and SUM_1680     ************/
  /*****************************************************************************/
  void process_TR_Secondary_school_2_as_high(XMLDocument theDoc, String str_highschool) throws Exception
  {
   String str_empty = "";
   String str_value = "";
   String str_attach = "";
   String str_high_ceeb = "";    
   String str_high_end_date = "";   
   int n=0;  
        //SST_1680
	    str_attach = "SST_1680";
            // use the stand high school type   B18	Standard high school diploma
           str_high_ceeb = theDoc.valueOf(str_highschool + "/TR_Secondary_school_2_lookup_-_CeebCode");
           str_high_end_date = theDoc.valueOf(str_highschool + "/TR_Secondary_school_2_to_date");
	    str_value = str_devide + "B18"  
		   + str_devide + CM_Date_Format
                   + str_devide + format_month_year_date(str_high_end_date)
		   + str_10_devide;
            str_empty = str_devide +"B18" + str_devide + CM_Date_Format + str_11_devide;
        if (!str_high_ceeb.equals("")){
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            } 
        // N1_1680
	    str_attach = "N1_1680";
            //str_high_ceeb = theDoc.valueOf(str_highschool + "/TR_Secondary_school_2_lookup_-_CeebCode");
            n=str_high_ceeb.length();
            if(n == 5) {
                   str_high_ceeb = "0"+str_high_ceeb;
            }
	     str_value = str_devide + HigSchool_Type
                   + str_devide 
		   + str_devide + Institution_Code_Qualify
                   + str_devide + str_high_ceeb 
                   + str_2_devide;  

            str_empty = str_devide + HigSchool_Type + str_2_devide 
		   + Institution_Code_Qualify + str_3_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
	    }	
       }
}

  /*****************************************************************************/
  /* process_highschool SST_1680, N1_1680, N3_1680 and SUM_1680     ************/
  /*****************************************************************************/
  void process_third_highschool(XMLDocument theDoc, String str_highschool) throws Exception
  {
   String str_empty = "";
   String str_value = "";
   String str_attach = "";
   String str_high_ceeb = "";  
   String str_high_end_date = "";   
   int n = 0;
        //SST_1680
	    str_attach = "SST_1680";
            // use the stand high school type   B18	Standard high school diploma
           str_high_ceeb = theDoc.valueOf(str_highschool + "/School_3_lookup_-_CeebCode");
           str_high_end_date = format_month_year_date(theDoc.valueOf(str_highschool + "/School_3_to_date"));
	    str_value = str_devide + "B18"  
		   + str_devide + CM_Date_Format
                   + str_devide + str_high_end_date
		   + str_10_devide;
            str_empty = str_devide +"B18" + str_devide + CM_Date_Format + str_11_devide;
       if (!str_high_ceeb.equals("")){
            if (!str_high_end_date.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            } 
        // N1_1680
	     str_attach = "N1_1680";
            //str_high_ceeb = theDoc.valueOf(str_highschool + "/School_3_lookup_-_CeebCode");
            n=str_high_ceeb.length();
            if(n == 5) {
                   str_high_ceeb = "0"+str_high_ceeb;
            }  
	      str_value = str_devide + HigSchool_Type + str_2_devide 
                   + Institution_Code_Qualify
                   + str_devide + str_high_ceeb 
                   + str_2_devide;  
            str_empty = str_devide + HigSchool_Type + str_2_devide 
		     + Institution_Code_Qualify 
                   + str_3_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
	    }
       }	
}

 /*****************************************************************************/
  /* process_highschool SST_1680, N1_1680, N3_1680 and SUM_1680     ************/
  /*****************************************************************************/
  void process_TR_Secondary_school_3_as_high(XMLDocument theDoc, String str_highschool) throws Exception
  {
   String str_empty = "";
   String str_value = "";
   String str_attach = "";
   String str_high_ceeb = "";  
   String str_high_end_date = "";  
   int n=0;
        //SST_1680
	    str_attach = "SST_1680";
            // use the stand high school type   B18	Standard high school diploma
           str_high_ceeb = theDoc.valueOf(str_highschool + "/TR_Secondary_school_3_lookup_-_CeebCode");
           str_high_end_date = theDoc.valueOf(str_highschool + "/TR_Secondary_school_3_to_date");
	    str_value = str_devide + "B18"  
		   + str_devide + CM_Date_Format
                   + str_devide + format_month_year_date(str_high_end_date)
		   + str_10_devide;
            str_empty = str_devide +"B18" + str_devide + CM_Date_Format + str_11_devide;
       if (!str_high_ceeb.equals("")){
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            } 
        // N1_1680
	    str_attach = "N1_1680";
            //str_high_ceeb = theDoc.valueOf(str_highschool + "/TR_Secondary_school_3_lookup_-_CeebCode");
            n=str_high_ceeb.length();
            if(n == 5) {
                   str_high_ceeb = "0"+str_high_ceeb;
            }
            str_high_ceeb =  str_high_ceeb;
	     str_value = str_devide + HigSchool_Type
                   + str_devide 
		   + str_devide + Institution_Code_Qualify
                   + str_devide + str_high_ceeb 
                   + str_2_devide;  
            str_empty = str_devide + HigSchool_Type + str_2_devide 
		   + Institution_Code_Qualify + str_3_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
	    }
       }	
}


  /*****************************************************************************/
  /* Process PREV_INST  PCL_2200       */
  /*****************************************************************************/
  void  process_tr_first_college(XMLDocument theDoc, String str_prev_inst) throws Exception
  {
 String str_value = "";
   String str_attach = "";
   String str_empty_info = "";
   String str_date = "";
   String str_school_ceeb = "";

          // process first college record
          str_attach = "PCL_2200";

          str_school_ceeb = theDoc.valueOf(str_prev_inst + "/TR_School_lookup_-_CeebCode");
          str_value = str_devide + "73" + str_devide
                + "000" + str_school_ceeb
		+ str_devide + Date_Time_Period 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_Entry_date")) + "-"
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_Exit_date")) 
		+ str_devide + "ND" 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_Exit_date")) 
		+ str_devide
		+ str_devide 
		+ str_devide 
		+ str_devide
		+ str_devide 
		+ str_31_devide;
           
            str_empty_info = str_devide + "73" + str_devide +"000" + str_devide + Date_Time_Period + str_devide + "-" +str_devide + "ND" + str_6_devide
		+ str_31_devide;
       if (!str_school_ceeb.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
           
          // process first degree 
          str_attach = "SES_2400";
          str_date = str_devide
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_Entry_date")) 
		+ str_5_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_Entry_date")) 
		+ str_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_Exit_date")) 
		+ str_5_devide; 
            str_empty_info = str_14_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_date;
		attach_string_totxt(str_attach);
           } // end if

          // process first college degree
          str_attach = "DEG_2520";
          str_value = str_devide
		+ com_degree_cw(theDoc.valueOf(str_prev_inst + "/TR_Degree_type"))
		+ str_22_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
           } 
       }
  }
  /*****************************************************************************/
  /* Process PREV_INST  PCL_2200       */
  /* TR_Secondary_school_1_lookup could be high or college */
  /*****************************************************************************/

  void  process_TR_Secondary_school_1_as_coll(XMLDocument theDoc, String str_prev_inst) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_empty_info = "";
   String str_date = "";
   String str_school_ceeb = "";

          // process other first college record
          str_attach = "PCL_2200";
          str_school_ceeb = theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_1_lookup_-_CeebCode");
          str_value = str_devide + "73" + str_devide
                + "000" + str_school_ceeb
		+ str_devide + Date_Time_Period 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_1_from_date")) + "-"
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_1_to_date")) 
		+ str_devide + "ND" 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_1_to_date")) 
		+ str_devide
		+ str_devide 
		+ str_devide 
		+ str_devide
		+ str_devide 
		+ str_31_devide;
           
            str_empty_info = str_devide + "73" + str_devide +"000" + str_devide + Date_Time_Period + str_devide + "-" +str_devide + "ND" + str_6_devide
		+ str_31_devide;
     if (!str_school_ceeb.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);

          // process other first degree 
          str_attach = "SES_2400";
          str_date = str_devide
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_1_from_date")) 
		+ str_5_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_1_from_date")) 
		+ str_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_1_to_date")) 
		+ str_5_devide; 
            str_empty_info = str_14_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_date;
		attach_string_totxt(str_attach);
           } // end if
          // process other first college degree
          str_attach = "DEG_2520";
          str_value = str_devide
		+ "ND"
		+ str_22_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
           } 
       }
  }
  /*****************************************************************************/
  /* Process PREV_INST  PCL_2200       */
  /* TR_Secondary_school_1_lookup could be high or college */
  /*****************************************************************************/
  void  process_TR_Secondary_school_2_as_coll(XMLDocument theDoc, String str_prev_inst) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_empty_info = "";
   String str_date = "";
   String str_school_ceeb = "";

          // process other first college record
          str_attach = "PCL_2200";
          str_school_ceeb = theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_2_lookup_-_CeebCode");
          str_value = str_devide + "73" + str_devide
                + "000" + str_school_ceeb
		+ str_devide + Date_Time_Period 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_2_from_date")) + "-"
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_2_to_date")) 
		+ str_devide + "ND" 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_2_to_date")) 
		+ str_devide
		+ str_devide 
		+ str_devide 
		+ str_devide
		+ str_devide 
		+ str_31_devide;
           
            str_empty_info = str_devide + "73" + str_devide +"000" + str_devide + Date_Time_Period + str_devide + "-" +str_devide + "ND" + str_6_devide
		+ str_31_devide;
     if (!str_school_ceeb.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);

          // process other first degree 
          str_attach = "SES_2400";
          str_date = str_devide
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_2_from_date")) 
		+ str_5_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_2_from_date")) 
		+ str_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_2_to_date")) 
		+ str_5_devide; 
            str_empty_info = str_14_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_date;
		attach_string_totxt(str_attach);
           } // end if
          // process other first college degree
          str_attach = "DEG_2520";
          str_value = str_devide
		+ "ND"
		+ str_22_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
           } 
       }
  }

  /*****************************************************************************/
  /* Process PREV_INST  PCL_2200       */
  /* TR_Secondary_school_1_lookup could be high or college */
  /*****************************************************************************/
  void  process_TR_Secondary_school_3_as_coll(XMLDocument theDoc, String str_prev_inst) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_empty_info = "";
   String str_date = "";
   String str_school_ceeb = "";

          // process other first college record
          str_attach = "PCL_2200";
          str_school_ceeb = theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_3_lookup_-_CeebCode");
          str_value = str_devide + "73" + str_devide
                + "000" + str_school_ceeb
		+ str_devide + Date_Time_Period 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_3_from_date")) + "-"
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_3_to_date")) 
		+ str_devide + "ND" 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_3_to_date")) 
		+ str_devide
		+ str_devide 
		+ str_devide 
		+ str_devide
		+ str_devide 
		+ str_31_devide;
           
            str_empty_info = str_devide + "73" + str_devide +"000" + str_devide + Date_Time_Period + str_devide + "-" +str_devide + "ND" + str_6_devide
		+ str_31_devide;
     if (!str_school_ceeb.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);

          // process other first degree 
          str_attach = "SES_2400";
          str_date = str_devide
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_3_from_date")) 
		+ str_5_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_3_from_date")) 
		+ str_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_Secondary_school_3_to_date")) 
		+ str_5_devide; 
            str_empty_info = str_14_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_date;
		attach_string_totxt(str_attach);
           } // end if
          // process other first college degree
          str_attach = "DEG_2520";
          str_value = str_devide
		+ "ND"
		+ str_22_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
           } 
       }
  }


  /*****************************************************************************/
  /* Process PREV_INST  PCL_2200       */
  /*****************************************************************************/
  void  process_tr_other1_college(XMLDocument theDoc, String str_prev_inst) throws Exception
  {
 String str_value = "";
   String str_attach = "";
   String str_empty_info = "";
   String str_date = "";
   String str_school_ceeb = "";

          // process other first college record
          str_attach = "PCL_2200";
          str_school_ceeb = theDoc.valueOf(str_prev_inst + "/TR_College_1_lookup_-_CeebCode");

          str_value = str_devide + "73" + str_devide
                + "000" + str_school_ceeb
		+ str_devide + Date_Time_Period 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_1_from_date")) + "-"
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_1_to_date")) 
		+ str_devide + "ND" 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_1_to_date")) 
		+ str_devide
		+ str_devide 
		+ str_devide 
		+ str_devide
		+ str_devide 
		+ str_31_devide;
           
            str_empty_info = str_devide + "73" + str_devide +"000" + str_devide + Date_Time_Period + str_devide + "-" +str_devide + "ND" + str_6_devide
		+ str_31_devide;
     if (!str_school_ceeb.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);

          // process other first degree 
          str_attach = "SES_2400";
          str_date = str_devide
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_1_from_date")) 
		+ str_5_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_1_from_date")) 
		+ str_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_1_to_date")) 
		+ str_5_devide; 
            str_empty_info = str_14_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_date;
		attach_string_totxt(str_attach);
           } // end if
          // process other first college degree
          str_attach = "DEG_2520";
          str_value = str_devide
		+ "ND"
		+ str_22_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
           } 
       }
  }

  /*****************************************************************************/
  /* Process PREV_INST  PCL_2200       */
  /*****************************************************************************/
  void  process_tr_other2_college(XMLDocument theDoc, String str_prev_inst) throws Exception
  {
 String str_value = "";
   String str_attach = "";
   String str_empty_info = "";
   String str_date = "";
   String str_school_ceeb = "";

          // process other second college record
          str_attach = "PCL_2200";
          str_school_ceeb = theDoc.valueOf(str_prev_inst + "/TR_College_2_lookup_-_CeebCode");
          str_value = str_devide + "73" + str_devide
                + "000" + str_school_ceeb
		+ str_devide + Date_Time_Period 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_2_from_date")) + "-"
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_2_to_date")) 
		+ str_devide + "ND" 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_2_to_date")) 
		+ str_devide
		+ str_devide 
		+ str_devide 
		+ str_devide
		+ str_devide 
		+ str_31_devide;
           
            str_empty_info = str_devide + "73" + str_devide +"000" + str_devide + Date_Time_Period + str_devide + "-" +str_devide + "ND" + str_6_devide
		+ str_31_devide;
       if (!str_school_ceeb.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);

          // process other second degree 
          str_attach = "SES_2400";
          str_date = str_devide
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_2_from_date")) 
		+ str_5_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_2_from_date")) 
		+ str_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_2_to_date")) 
		+ str_5_devide; 
            str_empty_info = str_14_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_date;
		attach_string_totxt(str_attach);
           } // end if
 	   // process other second college degree
          str_attach = "DEG_2520";
          str_value = str_devide
		+ "ND"
		+ str_22_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
           } 
       }
  }

  /*****************************************************************************/
  /* Process PREV_INST  PCL_2200       */
  /*****************************************************************************/
  void  process_tr_other3_college(XMLDocument theDoc, String str_prev_inst) throws Exception
  {
 String str_value = "";
   String str_attach = "";
   String str_empty_info = "";
   String str_date = "";
   String str_school_ceeb = "";

          // process other third college record
          str_attach = "PCL_2200";
          str_school_ceeb = theDoc.valueOf(str_prev_inst + "/TR_College_3_lookup_-_CeebCode");
          str_value = str_devide + "73" + str_devide
                + "000" + str_school_ceeb
		+ str_devide + Date_Time_Period 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_3_from_date")) + "-"
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_3_to_date")) 
		+ str_devide + "ND" 
                + str_devide + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_3_to_date")) 
		+ str_devide
		+ str_devide 
		+ str_devide 
		+ str_devide
		+ str_devide 
		+ str_31_devide;          
            str_empty_info = str_devide + "73" + str_devide +"000" + str_devide + Date_Time_Period + str_devide + "-" +str_devide + "ND" + str_6_devide
		+ str_31_devide;
           if (!str_school_ceeb.equals("")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
          // process other third degrss 
          str_attach = "SES_2400";
          str_date = str_devide
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_3_from_date")) 
		+ str_5_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_3_from_date")) 
		+ str_devide + "CM" + str_devide 
                + format_month_year_date(theDoc.valueOf(str_prev_inst + "/TR_College_3_to_date")) 
		+ str_5_devide; 
            str_empty_info = str_14_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_date;
		attach_string_totxt(str_attach);
           } // end if
          // process other third college degree
          str_attach = "DEG_2520";
          str_value = str_devide
		+ "ND"
		+ str_22_devide; 
           if (!str_date.equals("||||||CM||CM||||||")){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
           } 
       }
  }

    /*****************************************************************************/
  /************* Process   ACT, SAT and TOFEL test scores **********************/
  /**             TST_2040||SAT I|D8|20080401||||||||     **********************/
  /****           SBT_2080|S01||                        ************************/
  /********       SRE_2080||800                       **************************/
  /*****************************************************************************/
  void  process_ACT_test(XMLDocument theDoc, String str_test) throws Exception
  {
   String str_value = "";
   String str_value2 = "";
   String str_value3 = "";
   String str_attach = "";
   String str_attach2 = "";
   String str_attach3 = "";
   String str_a08 = "";
   String str_empty = "";
 
          // process TST for A01 English 
          str_attach = "TST_2040";
          str_value = str_2_devide + "ACT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/ACT_English_date")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"ACT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
  
          //  ACT01 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A01" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACT_English_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }

              
          // process TST for A02 Math 
          str_attach = "TST_2040";
          str_value = str_2_devide + "ACT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/ACT_Math_date")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"ACT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          
          //  ACT02 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A02" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACT_Math_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          // process TST for A03 Math 
          str_attach = "TST_2040";
          str_value = str_2_devide + "ACT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/ACT_Reading_date"))
                + str_8_devide;
 
            str_empty = str_2_devide +"ACT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          
          //  ACT03 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A03" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACT_Reading_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          // process TST for A04 Science Reasoning 
          str_attach = "TST_2040";
          str_value = str_2_devide + "ACT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/ACT_Science_date"))
                + str_8_devide;
 
            str_empty = str_2_devide +"ACT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          
          //  ACT04 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A04" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACT_Science_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          // process TST for A05 Composite 
          str_attach = "TST_2040";
          str_value = str_2_devide + "ACT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/ACT_Composite_date"))
                + str_8_devide;
 
            str_empty = str_2_devide +"ACT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          
          //  ACT05 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A05" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACT_Composite_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          // process TST for A08 Writing 
          str_attach = "TST_2040";
          str_value = str_2_devide + "ACT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/ACT_Writing_date"))
                + str_8_devide;
 
            str_empty = str_2_devide +"ACT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          
          //  ACT08 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A08" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080"; 
          str_a08 =  theDoc.valueOf(str_test + "/ACT_Writing_score");
          str_value3 = str_2_devide + str_a08;
       
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_2_devide + test_score_transfer(str_a08);
		attach_string_totxt(str_attach3);
            }
   }          

  /*****************************************************************************/
  /************* Process   ACT, SAT and TOFEL test scores **********************/
  /**             TST_2040||SAT I|D8|20080401||||||||     **********************/
  /****           SBT_2080|S01||                        ************************/
  /********       SRE_2080||800                       **************************/
  /*****************************************************************************/
  void  process_ACT_test2(XMLDocument theDoc, String str_test) throws Exception
  {
   String str_value = "";
   String str_value2 = "";
   String str_value3 = "";
   String str_attach = "";
   String str_attach2 = "";
   String str_attach3 = "";
   String str_a08 = "";
   String str_empty = "";
 
          // process TST 
          str_attach = "TST_2040";
          str_value = str_2_devide + "ACT Test2" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/ACTTestDate2")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"ACT Test2" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          //  ACT01 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A01" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACTEnglish2");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          //  ACT02 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A02" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACTMath2");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          //  ACT03 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A03" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACTReading2");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          //  ACT04 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A04" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACTScience2");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          //  ACT05 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A05" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/ACTComposite2");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          //  ACT08 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "A08" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_a08 = theDoc.valueOf(str_test + "/ACTWriting2");
          str_value3 = str_2_devide + str_a08; 
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_2_devide + test_score_transfer(str_a08);
		attach_string_totxt(str_attach3);
            }
   }          

  /*****************************************************************************/
  /************* Process  SAT test scores                 **********************/
  /**             TST_2040||SAT I|D8|20080401||||||||     **********************/
  /****           SBT_2080|S01||                        ************************/
  /********       SRE_2080||800                       **************************/
  /*****************************************************************************/
  void  process_SAT_test(XMLDocument theDoc, String str_test) throws Exception
  {
   String str_value = "";
   String str_value2 = "";
   String str_value3 = "";
   String str_attach = "";
   String str_attach2 = "";
   String str_attach3 = "";
   String str_empty = "";
 
          // process TST for S01 
          str_attach = "TST_2040";
          str_value = str_2_devide + "SAT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/SAT_CR_Date")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"SAT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          //  SAT01 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "S01" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/SAT_CR_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          // process TST For S02 Math 
          str_attach = "TST_2040";
          str_value = str_2_devide + "SAT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/SAT_Math_date")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"SAT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          
          //  SAT02 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "S02" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/SAT_Math_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          // process TST For S07 Wirting 
          str_attach = "TST_2040";
          str_value = str_2_devide + "SAT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/SAT_Writing_date")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"SAT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          
          //  SAT07 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "S07" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/SAT_Writing_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
   }          

 void  process_NEW_SAT_test(XMLDocument theDoc, String str_test) throws Exception
  {
   String str_value = "";
   String str_value2 = "";
   String str_value3 = "";
   String str_attach = "";
   String str_attach2 = "";
   String str_attach3 = "";
   String str_empty = "";
 
          // process TST for S11 NEW RW
          str_attach = "TST_2040";
          str_value = str_2_devide + "SAT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/SAT_NEW_RW_date")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"SAT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          //  SAT01 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "S11" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/SAT_NEW_RW_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          // process TST For S12 NEW Math 
          str_attach = "TST_2040";
          str_value = str_2_devide + "SAT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/SAT_NEW_Math_date")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"SAT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          
          //  SAT02 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "S12" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/SAT_NEW_Math_Score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          // process TST For S14 Essay 
          str_attach = "TST_2040";
          str_value = str_2_devide + "SAT Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/SAT_NEW_Essay_date")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"SAT Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          
          //  SAT07 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "S14" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/SAT_NEW_Essay_score");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
   }          

  

  /*****************************************************************************/
  /************* Process  SAT test scores                 **********************/
  /**             TST_2040||SAT I|D8|20080401||||||||     **********************/
  /****           SBT_2080|S01||                        ************************/
  /********       SRE_2080||800                       **************************/
  /*****************************************************************************/
  void  process_SAT_test2(XMLDocument theDoc, String str_test) throws Exception
  {
   String str_value = "";
   String str_value2 = "";
   String str_value3 = "";
   String str_attach = "";
   String str_attach2 = "";
   String str_attach3 = "";
   String str_empty = "";
 
          // process TST for S01
          str_attach = "TST_2040";
          str_value = str_2_devide + "SAT Test2" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/SATITestDate2")) 
                + str_8_devide;
 
            str_empty = str_2_devide +"SAT Test2" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
          //  SAT01 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "S01" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/SATIReading2");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          //  SAT02 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "S02" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/SATIMath2");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
          
          //  SAT07 SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "S07" + str_2_devide;

          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + theDoc.valueOf(str_test + "/SATIWriting2");
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }
   }          
 
  /*****************************************************************************/
  /************* Process  paper and iBT TOFEL test scores **********************/
  /**             TST_2040||TOEFL Test|CM|200112||||||||  **********************/
  /****           SBT_2080|TOFI||                       ************************/
  /********       SRE_2080||115                       **************************/
  /*****************************************************************************/
  void  process_TOEFL_iBT_test(XMLDocument theDoc, String str_test) throws Exception
  {
   String str_value = "";
   String str_value2 = "";
   String str_value3 = "";
   String str_attach = "";
   String str_attach2 = "";
   String str_attach3 = "";
   String str_test_type = "";
   String str_empty = "";
         
         // process TST2040 for TOEFL for toefl reading TFRS
          str_attach = "TST_2040";
          str_value = str_2_devide + "TOEFL Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/TOEFL_iBT_Reading_date")) 
                + str_8_devide;
           str_empty = str_2_devide +"TOEFL Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
  
          //  TOEFL SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "TFRS" + str_2_devide;

      
          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + toefl_score_validation(str_test_type, theDoc.valueOf(str_test + "/TOEFL_iBT_Reading_score"));
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }


         // process TST2040 for TOEFL for toefl reading TFSS
          str_attach = "TST_2040";
          str_value = str_2_devide + "TOEFL Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/TOEFL_iBT_speaking_date")) 
                + str_8_devide;
           str_empty = str_2_devide +"TOEFL Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
  
          //  TOEFL SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "TFSS" + str_2_devide;

      
          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + toefl_score_validation(str_test_type, theDoc.valueOf(str_test + "/TOEFL_iBT_speaking_score"));
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }

         // process TST2040 for TOEFL for toefl reading TFLS
          str_attach = "TST_2040";
          str_value = str_2_devide + "TOEFL Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/TOEFL_iBT_Listening_date")) 
                + str_8_devide;
           str_empty = str_2_devide +"TOEFL Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
  
          //  TOEFL SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "TFLS" + str_2_devide;

      
          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + toefl_score_validation(str_test_type, theDoc.valueOf(str_test + "/TOEFL_iBT_Listening_score"));
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }

         // process TST2040 for TOEFL for toefl reading TFWS
          str_attach = "TST_2040";
          str_value = str_2_devide + "TOEFL Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/TOEFL_iBT_Writing_date")) 
                + str_8_devide;
           str_empty = str_2_devide +"TOEFL Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
  
          //  TOEFL SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "TFWS" + str_2_devide;

      
          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + toefl_score_validation(str_test_type, theDoc.valueOf(str_test + "/TOEFL_iBT_Writing_score"));
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }

  }   


/*****************************************************************************/
  /************* Process  paper test scores **********************/
  /**             TST_2040||TOEFL Test|CM|200112||||||||  **********************/
  /****           SBT_2080|TOFI||                       ************************/
  /********       SRE_2080||115                       **************************/
  /*****************************************************************************/
  void  process_TOEFL_Paper_test(XMLDocument theDoc, String str_test) throws Exception
  {
   String str_value = "";
   String str_value2 = "";
   String str_value3 = "";
   String str_attach = "";
   String str_attach2 = "";
   String str_attach3 = "";
   String str_test_type = "";
   String str_empty = "";
         
         // process TST2040 for TOEFL for toefl reading TOPL
          str_attach = "TST_2040";
          str_value = str_2_devide + "TOEFL Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/TOEFL_Paper_Listening_date")) 
                + str_8_devide;
           str_empty = str_2_devide +"TOEFL Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
  
          //  TOEFL SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "TOPL" + str_2_devide;
     
          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + toefl_score_validation(str_test_type, theDoc.valueOf(str_test + "/TOEFL_Paper_Listening_score"));
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }


         // process TST2040 for TOEFL for toefl reading TOPR
          str_attach = "TST_2040";
          str_value = str_2_devide + "TOEFL Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/TOEFL_Paper_Reading_date")) 
                + str_8_devide;
           str_empty = str_2_devide +"TOEFL Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
  
          //  TOEFL SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "TOPR" + str_2_devide;
      
          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + toefl_score_validation(str_test_type, theDoc.valueOf(str_test + "/TOEFL_Paper_Reading_score"));
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }

         // process TST2040 for TOEFL for toefl reading TOPW
          str_attach = "TST_2040";
          str_value = str_2_devide + "TOEFL Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/TOEFL_Paper_Structure_date")) 
                + str_8_devide;
           str_empty = str_2_devide +"TOEFL Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
  
          //  TOEFL SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "TOPW" + str_2_devide;
      
          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + toefl_score_validation(str_test_type, theDoc.valueOf(str_test + "/TOEFL_Paper_Structure_score"));
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }

         // process TST2040 for TOEFL for toefl reading TOPC
          str_attach = "TST_2040";
          str_value = str_2_devide + "TOEFL Test" + str_devide
                + Test_Date_Format +str_devide + format_month_year_day(theDoc.valueOf(str_test + "/TOEFL_Paper_TWE_date")) 
                + str_8_devide;
           str_empty = str_2_devide +"TOEFL Test" + str_devide + Test_Date_Format + str_9_devide;
            if (!str_value.equals(str_empty)){
		str_attach = str_attach + str_value;
		attach_string_totxt(str_attach);
            }
  
          //  TOEFL SBT_2080
          str_attach2 = "SBT_2080";
          str_value2 = str_devide + "TOPC" + str_2_devide;
      
          // scores SRE_2080
          str_attach3 = "SRE_2080";
          str_value3 = str_2_devide + toefl_score_validation(str_test_type, theDoc.valueOf(str_test + "/TOEFL_Paper_TWE_score"));
 
            str_empty = str_2_devide;
            if (!str_value3.equals(str_empty)){
		str_attach2 = str_attach2 + str_value2;
		attach_string_totxt(str_attach2);
		
                str_attach3 = str_attach3 + str_value3;
		attach_string_totxt(str_attach3);
            }

  }                 

  /*****************************************************************************/
  /********  process activity ATV_1120|SA|C07|||||||| **************************/
  /*****************************************************************************/
  void  process_activity(XMLDocument theDoc, String str_act) throws Exception
  {
   String str_attach = "";
   String str_type1 = "";
   String str_type2 = "";
   String str_type3 = "";
   String str_type4 = "";
   String str_type5 = "";
   String str_type6 = "";
   String str_type7 = "";

   String str_value1 = "";
   String str_value2 = "";
   String str_value3 = "";
   String str_value4 = "";
   String str_value5 = "";
   String str_value6 = "";
   String str_value7 = "";
   String str_empty = "";

          // processATV_1120
          // ATV_1120|SA|VA||||||||

          str_type1 =  theDoc.valueOf(str_act+ "/Activity_1_type");
          str_value1 =  theDoc.valueOf(str_act+ "/Activity_1_name");
          str_type2 =  theDoc.valueOf(str_act+ "/Activity_2_type");
          str_value2 =  theDoc.valueOf(str_act+ "/Activity_2_name");
          str_type3 =  theDoc.valueOf(str_act+ "/Activity_3_type");
          str_value3 =  theDoc.valueOf(str_act+ "/Activity_3_name");
          str_type4 =  theDoc.valueOf(str_act+ "/Activity_4_type");
          str_value4 =  theDoc.valueOf(str_act+ "/Activity_4_name");
          str_type5 =  theDoc.valueOf(str_act+ "/Activity_5_type");
          str_value5 =  theDoc.valueOf(str_act+ "/Activity_5_name");
          str_type6 =  theDoc.valueOf(str_act+ "/Activity_6_type");
          str_value6 =  theDoc.valueOf(str_act+ "/Activity_6_name");
          str_type7 =  theDoc.valueOf(str_act+ "/Activity_7_type");
          str_value7 =  theDoc.valueOf(str_act+ "/Activity_7_name");

          str_empty = "";
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type1.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type1)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value1.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value1)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type2.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type2)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value2.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value2)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type3.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type3)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }  
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value3.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value3)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type4.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type4)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }  
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value4.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value4)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type5.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type5)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }  
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value5.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value5)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type6.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type6)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }  
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value6.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value6)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type7.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type7)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value7.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value7)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }
  } 
  

/*****************************************************************************/
  /********  process activity ATV_1120|SA|C07|||||||| **************************/
  /*****************************************************************************/
  void  process_tr_activity(XMLDocument theDoc, String str_act) throws Exception
  {
   String str_attach = "";
   String str_type1 = "";
   String str_type2 = "";
   String str_type3 = "";
   String str_type4 = "";
   String str_type5 = "";
   String str_type6 = "";
   String str_type7 = "";
   String str_type8 = "";

   String str_value1 = "";
   String str_value2 = "";
   String str_value3 = "";
   String str_value4 = "";
   String str_value5 = "";
   String str_value6 = "";
   String str_value7 = "";
   String str_value8 = "";

   String str_empty = "";

          // processATV_1120
          // ATV_1120|SA|VA||||||||

          str_type1 =  theDoc.valueOf(str_act+ "/TR_Activity_1_type");
          str_value1 =  theDoc.valueOf(str_act+ "/TR_Activity_1_name");
          str_type2 = theDoc.valueOf(str_act+ "/TR_Activity_2_type");
          str_value2 =  theDoc.valueOf(str_act+ "/TR_Activity_2_name");
          str_type3 =  theDoc.valueOf(str_act+ "/TR_Activity_3_type");
          str_value3 =  theDoc.valueOf(str_act+ "/TR_Activity_3_name");
          str_type4 =  theDoc.valueOf(str_act+ "/TR_Activity_4_type");
          str_value4 =  theDoc.valueOf(str_act+ "/TR_Activity_4_name");
          str_type5 =  theDoc.valueOf(str_act+ "/TR_Activity_5_type");
          str_value5 =  theDoc.valueOf(str_act+ "/TR_Activity_5_name");
          str_type6 =  theDoc.valueOf(str_act+ "/TR_Activity_6_type");
          str_value6 =  theDoc.valueOf(str_act+ "/TR_Activity_6_name");
          str_type7 =  theDoc.valueOf(str_act+ "/TR_Activity_7_type");
          str_value7 =  theDoc.valueOf(str_act+ "/TR_Activity_7_name");
          str_type8 =  theDoc.valueOf(str_act+ "/TR_Activity_8_type");
          str_value8 =  theDoc.valueOf(str_act+ "/TR_Activity_8_name");

          str_empty = "";

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type1.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type1)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value1.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value1)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type2.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type2)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value2.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value2)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type3.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type3)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }  
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value3.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value3)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type4.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type4)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }  
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value4.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value4)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type5.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type5)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }  
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value5.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value5)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type6.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type6)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }  
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value6.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value6)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type7.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type7)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value7.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value7)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

  	   str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_type8.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_type8)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }
          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value8.equals(str_empty)){
                  str_attach = str_attach + activity_transfer(str_value8)+ str_8_devide;
                  attach_string_totxt(str_attach);
          }

  } 


  /*****************************************************************************/
  /********  process special programs ******************************************/
  /********* and loaded as activity ATV_1120|SA|C07|||||||| ********************/
  /*****************************************************************************/
  void  process_special_program(XMLDocument theDoc, String str_program) throws Exception
  {
   String str_attach = "";
   String str_value = "";
   String str_empty = "";
   String[] str_Array = null;
   String str_out="";

          // processATV_1120
          // ATV_1120|SA|VA||||||||

          str_value =  theDoc.valueOf(str_program + "/Programs");

          str_empty = str_8_devide;

          str_attach = "ATV_1120" + str_devide + "SA" + str_devide;
          if (!str_value.equals("")){
                  str_Array =  str_value.split(",");
                  for(int i = 0; i< str_Array.length; i++){
                  str_out = str_attach + special_program_crosswalk(str_Array[i]) + str_8_devide;
                  attach_string_totxt(str_out);
                 }
          }

  } 

  /*************************************************************************************************/
  /*  processs question /answer   */
  /*************************************************************************************************/
  void process_admit_type(XMLDocument theDoc, String str_app_dataid) throws Exception
  {
   String str_empty = "";
   String str_value = "";
   String str_attach = "";
   String str_type = "";    
   String str_stu_ea = "";    
   String str_admt_type = "";
   String str_stu_type = "";
    
            // question answer 
            str_type = theDoc.valueOf(str_app_dataid+ "/Student_Type");
            str_stu_ea = theDoc.valueOf(str_app_dataid+ "/Admission_plan");
            if (str_type.equals("FY")) {
                   if(str_stu_ea.equals("EA")){
                     str_admt_type = "EL";
                     str_stu_type  = "F";
                   }
                   else if(str_stu_ea.equals("ED")){
                     str_admt_type = "ED";
                     str_stu_type  = "F";
                   }
                   else if(str_stu_ea.equals("RD")){
                     str_admt_type = "FR";
                     str_stu_type  = "F";
                   }
                   else{
 			str_admt_type = "FR";
                     str_stu_type  = "F";
                   }
            } 
            else if (str_type.equals("TR")) {
                     str_admt_type = "AT";
                     str_stu_type  = "T";
            }
            else{  
                     str_admt_type = "FR";
                     str_stu_type  = "F";
            }
	   
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "10"
                   + str_devide + "What is your Admit Type?" 
		   + str_2_devide + str_admt_type;
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	   
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "10"
                   + str_devide + "What is your Student Type?" 
		   + str_2_devide + str_stu_type;
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
           
  }

/************************************************************************************************/
/************************* New Ethnicity and Race code process *********************************/
/***** use race code and new ethnic code to decide the old ethnic code *************************/
/************************ using the questions and answers segment ******************************/ 

void process_race_ethn(XMLDocument theDoc, String str_demo) throws Exception
  {
   String str_value = "";
   String str_attach = "";

   String str_LatinoOptions = "";
   String str_LatinoOther = "";
   String str_NativeOptions = "";
   String str_NativeOther = "";
   String str_AsianOptions = "";
   String str_OtherEastAsia = "";
   String str_OtherIndianSubcontinent = "";
   String str_OtherSoutheastAsia = "";
   String str_BlackOptions = "";
   String str_BlackOther = "";
   String str_HawaiianOptions = "";
   String str_HawaiianOther = "";
   String str_WhiteOptions = "";
   String str_WhiteOther = "";
   
   String str_Latino_ethn = "";
   String str_member_group = "";
   String str_new_ethn = "";
   String str_Other= ""; 
   String str_race_8 = "";



   // ethnicity  -- now racical catagory
   str_Latino_ethn = theDoc.valueOf(str_demo + "/Hispanic_Latino");
          if(str_Latino_ethn.equals("Y"))
             str_new_ethn = "6";
          else if (str_Latino_ethn.equals("N"))
             str_new_ethn = "9";
          else
             str_new_ethn = "0";
   
   str_LatinoOptions = theDoc.valueOf(str_demo + "/Hispanic_Latino_background");
   if (!str_LatinoOptions.equals("")) 
       str_LatinoOptions = str_LatinoOptions + ",";
   str_LatinoOther = theDoc.valueOf(str_demo + "/Hispanic_Latino_other");
   if (!str_LatinoOther.equals("")) 
       str_LatinoOther = "XOH,";

   str_member_group = theDoc.valueOf(str_demo + "/Background");
   if (!str_member_group.equals("")) 
       str_member_group = str_member_group + ",";
 
   str_NativeOptions = theDoc.valueOf(str_demo + "/American_Indian_Background");
   if (!str_NativeOptions.equals("")) 
       str_NativeOptions = str_NativeOptions + ",";
   str_NativeOther = theDoc.valueOf(str_demo + "/American_Indian_other");
   if (!str_NativeOther.equals("")) 
       str_NativeOther = "XON,";
   
   str_AsianOptions = theDoc.valueOf(str_demo + "/Asian_background");
   if (!str_AsianOptions.equals("")) 
       str_AsianOptions = str_AsianOptions + ",";

   str_OtherEastAsia = theDoc.valueOf(str_demo + "/East_Asia_other");   
   if (!str_OtherEastAsia.equals("")) 
       str_OtherEastAsia = "XEA,";

   str_OtherIndianSubcontinent = theDoc.valueOf(str_demo + "/South_Asia_other");
   if (!str_OtherIndianSubcontinent.equals("")) 
       str_OtherIndianSubcontinent = "XIS,";

   str_OtherSoutheastAsia = theDoc.valueOf(str_demo + "/Southeast_Asia_other");
   if (!str_OtherSoutheastAsia.equals("")) 
       str_OtherSoutheastAsia = "XSA,";
   
   str_BlackOptions = theDoc.valueOf(str_demo + "/African_background");
   if (!str_BlackOptions.equals("")) 
       str_BlackOptions = str_BlackOptions + ",";
   str_BlackOther = theDoc.valueOf(str_demo + "/African_other");
   if (!str_BlackOther.equals("")) 
       str_BlackOther = "XOA,";
   
   str_HawaiianOptions = theDoc.valueOf(str_demo + "/Hawaiian_background");
   if (!str_HawaiianOptions.equals("")) 
       str_HawaiianOptions = str_HawaiianOptions + ",";
   str_HawaiianOther = theDoc.valueOf(str_demo + "/Hawaiian_other");
   if (!str_HawaiianOther.equals("")) 
       str_HawaiianOther = "XOP,";
  
   str_WhiteOptions = theDoc.valueOf(str_demo + "/White_background");
   if (!str_WhiteOptions.equals("")) 
       str_WhiteOptions = str_WhiteOptions + ",";
   str_WhiteOther = theDoc.valueOf(str_demo + "/White_other");
   if (!str_WhiteOther.equals("")) 
       str_WhiteOther = "XOW,";
        
   str_value = str_LatinoOptions + str_LatinoOther + str_member_group
             + str_NativeOptions + str_NativeOther
             + str_AsianOptions + str_OtherEastAsia + str_OtherIndianSubcontinent + str_OtherSoutheastAsia 
             + str_BlackOptions + str_BlackOther + str_HawaiianOptions + str_HawaiianOther
             + str_WhiteOptions + str_WhiteOther;

            // RQS_1600|AQ|41|The race code?||3,5,O,
            // RQS_1600|AQ|42|The new ethnicity code?||6 
          
          str_attach = "RQS_1600" + str_devide + "AQ"
                       + str_devide + "41" + str_devide + "The common app race code?" + str_2_devide;
          str_attach = str_attach + str_value;
          
          attach_string_totxt(str_attach);
	  
          
          str_attach = "RQS_1600" + str_devide + "AQ"
                       + str_devide + "42" + str_devide + "The common app new ethnicity code?" + str_2_devide;
          str_attach = str_attach + str_new_ethn;
          
          attach_string_totxt(str_attach);
        
  }



/**********************************************************************************************/
  /* Process Englishi is/or not the first language **********************************************/
  /**********************************************************************************************/
  void process_english_firstlanguage(XMLDocument theDoc, String str_input) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_lang1= "";
   String str_lang2= "";
   String str_lang3= "";
   String str_lang1_level= "";
   String str_lang2_level= "";
   String str_lang3_level= "";
    
           str_attach = "RQS_1600";
    
           str_attach = "RQS_1600";
   	   str_lang1 = theDoc.valueOf(str_input + "/Language_1");
   	   str_lang2 = theDoc.valueOf(str_input + "/Language_2");
   	   str_lang3 = theDoc.valueOf(str_input + "/Language_3");
   	   str_lang1_level = theDoc.valueOf(str_input + "/Language_1_proficiency");
   	   str_lang2_level = theDoc.valueOf(str_input + "/Language_2_proficiency");
   	   str_lang3_level = theDoc.valueOf(str_input + "/Language_3_proficiency");
         
        if ((str_lang1.equals("eng") && str_lang1_level.indexOf("F")!= -1)
            || (str_lang2.equals("eng") && str_lang2_level.indexOf("F")!= -1)
            || (str_lang3.equals("eng") && str_lang3_level.indexOf("F")!= -1)
                   ){
	   str_value = str_devide + "AQ"  
		   + str_devide + "43"
                   + str_devide + "English first language?" 
		   + str_2_devide + "Y";
        }else{
	   str_value = str_devide + "AQ"  
		   + str_devide + "43"
                   + str_devide + "English first language?" 
		   + str_2_devide + "N";
        } 
        str_attach = str_attach + str_value;
        attach_string_totxt(str_attach);
        
 }

  /**********************************************************************************************/
  /* Process veretan status                        **********************************************/
  /**********************************************************************************************/
  void process_veratan_status(XMLDocument theDoc, String str_input) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_veretan= "";
    
           str_attach = "RQS_1600";
   	   str_veretan = theDoc.valueOf(str_input + "/USVeteran");
        
        if (str_veretan.equals("None")){
	   str_veretan = "N";
        }else if (str_veretan.equals("")){
	   str_veretan = "N";
        }else{
	   str_veretan = str_veretan;
        }

        if (!str_veretan.equals("N")){
	   	str_value = str_devide + "AQ"  
		   + str_devide + "44"
                   + str_devide + "The common app veteran status?" 
		   + str_2_devide + str_veretan;
        	str_attach = str_attach + str_value;
        	attach_string_totxt(str_attach);
        }     
 }
  /**********************************************************************************************/
  /* Process native America register number        **********************************************/
  /**********************************************************************************************/
  void process_native_enrollmentNo(XMLDocument theDoc, String str_input) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_enrollNo= "";
    
           str_attach = "RQS_1600";
   	   str_enrollNo = theDoc.valueOf(str_input + "/EnrollmentNumber");
        
        if (!str_enrollNo.equals("")){
	   str_value = str_devide + "AQ"  
		   + str_devide + "45"
                   + str_devide + "Native EnrollmentNumber?" 
		   + str_2_devide + str_enrollNo;
        str_attach = str_attach + str_value;
        attach_string_totxt(str_attach);
       } 
 }
  /**********************************************************************************************/
  /* Process com app legacy/alumni info            **********************************************/
  /**********************************************************************************************/
  void process_legacy_info(XMLDocument theDoc, String str_input) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_pa1_type = "";
   String str_pa2_type = "";
   String str_sb1_type = "";
   String str_sb2_type = "";
   String str_sb3_type = "";

   String str_pa1_ceeb = "";
   String str_pa2_ceeb = "";
   String str_sb1_ceeb = "";
   String str_sb2_ceeb = "";
   String str_sb3_ceeb = "";

   String str_legacy="";
    
        str_attach = "RQS_1600";
   	   str_pa1_type = theDoc.valueOf(str_input + "/Parent1Type");
          str_pa1_ceeb = theDoc.valueOf(str_input + "/Parent1CEEB");
   	   str_pa2_type = theDoc.valueOf(str_input + "/Parent2Type");
          str_pa2_ceeb = theDoc.valueOf(str_input + "/Parent2CEEB");
   	   str_sb1_type = theDoc.valueOf(str_input + "/Sibling1Relationship");
          str_sb1_ceeb = theDoc.valueOf(str_input + "/Sibling1CEEB");
   	   str_sb2_type = theDoc.valueOf(str_input + "/Sibling2Relationship");
          str_sb2_ceeb = theDoc.valueOf(str_input + "/Sibling2CEEB");
   	   str_sb3_type = theDoc.valueOf(str_input + "/Sibling3Relationship");
          str_sb3_ceeb = theDoc.valueOf(str_input + "/Sibling3CEEB");

          if (str_pa1_ceeb.equals("4850")||str_pa2_ceeb.equals("4850")
                        ||str_sb1_ceeb.equals("4850")||str_sb2_ceeb.equals("4850")||str_sb3_ceeb.equals("4850")) {
		   if (!str_pa1_ceeb.equals("4850"))
			 str_pa1_type = "NULL";
		   if (!str_pa2_ceeb.equals("4850"))
			 str_pa2_type = "NULL";
		   if (!str_sb1_ceeb.equals("4850"))
			 str_sb1_type = "NULL";
		   if (!str_sb2_ceeb.equals("4850"))
			 str_sb2_type = "NULL";
		   if (!str_sb3_ceeb.equals("4850"))
			 str_sb3_type = "NULL";
		System.out.println("The legacy is " + str_legacy );

                str_legacy = str_pa1_type + "," + str_pa2_type + "," +
                             str_sb1_type + "," + str_sb2_type + "," + str_sb3_type + ",";

              System.out.println("The legacy is " + str_legacy );
          }

        if (!str_legacy.equals("")){
	   str_value = str_devide + "AQ"  
		   + str_devide + "46"
                   + str_devide + "The common app legacy info?" 
		   + str_2_devide + str_legacy;
        str_attach = str_attach + str_value;
        attach_string_totxt(str_attach);
       } 
 }
  
  /**********************************************************************************************/
  /* Process com app client id info                **********************************************/
  /**********************************************************************************************/
  void process_client_id_info(XMLDocument theDoc, String str_input) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_client_id="";
    
        str_attach = "RQS_1600";
   	   str_client_id = theDoc.valueOf(str_input + "/Common_App_ID");
        if (!str_client_id.equals("")){ 
	   str_value = str_devide + "AQ"  
		   + str_devide + "47"
                   + str_devide + "The common app client ID?" 
		   + str_2_devide + str_client_id;
        str_attach = str_attach + str_value;
        attach_string_totxt(str_attach);
       } 
 }
  
  /**********************************************************************************************/
  /* Process com app mailing end date info         **********************************************/
  /**********************************************************************************************/
  void process_mailing_end_date(XMLDocument theDoc, String str_input) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_end_date="";
    
        str_attach = "RQS_1600";
   	   str_end_date = theDoc.valueOf(str_input + "/CurrAddressToDate");
        if (!str_end_date.equals("")){ 
	   str_value = str_devide + "AQ"  
		   + str_devide + "48"
                   + str_devide + "The common app mailing end date?" 
		   + str_2_devide + str_end_date;
        str_attach = str_attach + str_value;
        attach_string_totxt(str_attach);
       } 
 }

  /**********************************************************************************************/
  /* Capture the high school name with code 000000  **********************************************/
  /* store the school name into saaquan             **********************************************/
  /**********************************************************************************************/
  void process_none_matching_schoolname(XMLDocument theDoc, String str_input) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_ceeb = "";
   String str_schoolname="";
    
        str_attach = "RQS_1600";
          str_ceeb = theDoc.valueOf(str_input + "/CEEBCode");
   	   str_schoolname = theDoc.valueOf(str_input + "/SecSchoolName");

        if (str_ceeb.equals("000000") && !str_schoolname.equals("")){ 
	   str_value = str_devide + "AQ"  
		   + str_devide + "49"
                   + str_devide + "The none matching highschool name?" 
		   + str_2_devide + str_schoolname;
        str_attach = str_attach + str_value;
        attach_string_totxt(str_attach);
       } 
 }

/**********************************************************************************************/
/**********************************************************************************************/
  /* Process parent name                */
  /**********************************************************************************************/
  void process_parent1_questions(XMLDocument theDoc, String str_name) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_parent_type="";
   String str_same_addr="";
   String str_first_name="";
   String str_last_name="";
   String str_mi="";

   String str_employer="";
   String str_title="";
   String str_degree="";
   String str_education_level="";
   String str_email="";

   	str_parent_type = theDoc.valueOf(str_name + "/Parent_1_Type");
   	str_same_addr = theDoc.valueOf(str_name + "/Parent_1_address_same_or_not");
   	str_last_name = theDoc.valueOf(str_name + "/Parent_1_Last_Name");
   	str_first_name = theDoc.valueOf(str_name + "/Parent_1_First_Name");
   	str_mi = theDoc.valueOf(str_name + "/Parent_1_Middle");

   	str_employer = theDoc.valueOf(str_name + "/Parent_1_Employer");
   	str_title = theDoc.valueOf(str_name + "/Parent_1_Position");
   	str_education_level = theDoc.valueOf(str_name + "/Parent_1_Education_level");
        str_degree = theDoc.valueOf(str_name + "/Parent_1_college_1_Degree_1");
   	str_email = theDoc.valueOf(str_name + "/Parent_1_Email");
    
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "51"
                   + str_devide + "Parent1 Type?" 
		   + str_2_devide + str_parent_type;

        if(!str_parent_type.equals("")&& !str_parent_type.equals("Unknown")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	 }   
        
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "52"
                   + str_devide + "Parent1 Same Address?" 
		   + str_2_devide + str_same_addr;
        if(!str_same_addr.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	 }   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "53"
                   + str_devide + "Parent1 Last name?" 
		   + str_2_devide + str_last_name;
        if(!str_last_name.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "54"
                   + str_devide + "Parent1 First name?" 
		   + str_2_devide + str_first_name;
        if(!str_first_name.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "55"
                   + str_devide + "Parent1 Middle name?" 
		   + str_2_devide + str_mi;
        if(!str_mi.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "56"
                   + str_devide + "Parent1 Employer?" 
		   + str_2_devide + str_employer;
        if(!str_employer.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "57"
                   + str_devide + "Parent1 Title?" 
		   + str_2_devide + str_title;
        if(!str_title.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
        
        if(str_education_level.equals("")){
	   str_education_level = "NULL"; 
	}   
	  str_value = str_devide + "AQ"  
		   + str_devide + "58"
                   + str_devide + "Parent1 Education Level?" 
		   + str_2_devide + str_education_level;
        if(!str_education_level.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
       // added the degree
        str_attach = "RQS_1600";
        if(str_degree.equals("")){
	   str_degree = "NULL"; 
	}   
	  str_value = str_devide + "AQ"  
		   + str_devide + "588"
                   + str_devide + "Parent1 Degree?" 
		   + str_2_devide + str_degree;
        if(!str_degree.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   

           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "59"
                   + str_devide + "Parent1 Email?" 
		   + str_2_devide + str_email;
        if(!str_email.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
}

/**********************************************************************************************/
  /* Process parent name                */
/**********************************************************************************************/
  void process_parent2_questions(XMLDocument theDoc, String str_name) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_parent_type="";
   String str_same_addr="";
   String str_first_name="";
   String str_last_name="";
   String str_mi="";

   String str_employer="";
   String str_title="";
   String str_degree="";
   String str_education_level="";
   String str_email="";

   	str_parent_type = theDoc.valueOf(str_name + "/Parent_2_Type");
   	str_same_addr = theDoc.valueOf(str_name + "/Parent_2_address_same_or_not");
   	str_last_name = theDoc.valueOf(str_name + "/Parent_2_Last_Name");
   	str_first_name = theDoc.valueOf(str_name + "/Parent_2_First_Name");
   	str_mi = theDoc.valueOf(str_name + "/Parent_2_Middle");
   	str_employer = theDoc.valueOf(str_name + "/Parent_2_Employer");
   	str_title = theDoc.valueOf(str_name + "/Parent_2_Position");
   	str_education_level = theDoc.valueOf(str_name + "/Parent_2_Education_level");
        str_degree = theDoc.valueOf(str_name + "/Parent_2_college_1_Degree_1");
   	str_email = theDoc.valueOf(str_name + "/Parent_2_Email");
        
	   str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "61"
                   + str_devide + "Parent2 Type?" 
		   + str_2_devide + str_parent_type;
        if(!str_parent_type.equals("")&& !str_parent_type.equals("Unknown")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
        
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "62"
                   + str_devide + "Parent2 Same Address?" 
		   + str_2_devide + str_same_addr;
        if(!str_same_addr.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "63"
                   + str_devide + "Parent2 Last name?" 
		   + str_2_devide + str_last_name;
        if(!str_last_name.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "64"
                   + str_devide + "Parent2 First name?" 
		   + str_2_devide + str_first_name;
        if(!str_first_name.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "65"
                   + str_devide + "Parent2 Middle name?" 
		   + str_2_devide + str_mi;
        if(!str_mi.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "66"
                   + str_devide + "Parent2 Employer?" 
		   + str_2_devide + str_employer;
        if(!str_employer.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "67"
                   + str_devide + "Parent2 Title?" 
		   + str_2_devide + str_title;
        if(!str_title.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
        str_attach = "RQS_1600";
        if(str_education_level.equals("")){
	   str_education_level = "NULL"; 
	}   
	   str_value = str_devide + "AQ"  
		   + str_devide + "68"
                   + str_devide + "Parent2 Education Level?" 
		   + str_2_devide + str_education_level;
        if(!str_education_level.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
        
       // for degree   

        str_attach = "RQS_1600";
        if(str_degree.equals("")){
	   str_degree = "NULL"; 
	}   
	   str_value = str_devide + "AQ"  
		   + str_devide + "688"
                   + str_devide + "Parent2 Degree?" 
		   + str_2_devide + str_degree;
        if(!str_degree.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
           
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "69"
                   + str_devide + "Parent2 Email?" 
		   + str_2_devide + str_email;
        if(!str_email.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
}

/**********************************************************************************************/
  /* Process first generation question              */
/**********************************************************************************************/
  void process_first_generation_question(XMLDocument theDoc, String str_first_generation) throws Exception
  {
   String str_value = "";
   String str_attach = "";
   String str_first = "";
   String str_p1_levl = "";
   String str_p2_levl = "";

       //str_p1_levl = theDoc.valueOf(str_first_generation + "/Parent_1_Education_level");
       //str_p2_levl = theDoc.valueOf(str_first_generation + "/Parent_2_Education_level");

   	str_first = theDoc.valueOf(str_first_generation + "/First_Gen");
       //if(str_p1_levl.equals("College")||str_p2_levl.equals("College")||str_p1_levl.equals("Grade_school")||str_p2_levl.equals("Grade_school") ){
       if(str_first.equals("Y")){
         str_first = "Y";
       }
       else{
         str_first = "N";
       }
           str_attach = "RQS_1600";
	   str_value = str_devide + "AQ"  
		   + str_devide + "70"
                   + str_devide + "First Generation?" 
		   + str_2_devide + str_first;
        if(!str_first.equals("")){
           str_attach = str_attach + str_value;
	   attach_string_totxt(str_attach);
	}   
}

/*************************************************************************************************/
  /* input name:  filr or file.xml   */
  /* output name: file.in            */
  /*************************************************************************************************/
  String get_Citizenship_Status_Code(XMLDocument theDoc, String str_app_dataid) throws Exception
  {
   String str_status_code =  "";
   String str_value = "";
   String str_id= "";
   str_id = theDoc.valueOf(str_app_dataid + "[position() = 14]/@id");
   str_value = theDoc.valueOf(str_app_dataid + "[position() = 14]/value"); 
   if (str_value.equals("Y"))
		str_status_code = US_Citizenship_Code;
   	else
  		str_status_code = NON_US_Citizen_Code;
   	return str_status_code;
  }

  /************************************************************************************************
  **********************     Some Untility functions     ******************************************
  *************************************************************************************************/
  void attach_string_totxt(String str_attach)
  {
	str_txt = str_txt + "\n" + str_attach;
  }

  /*************************************************************************************************/

  /*************************************************************************************************/
  /* input name:  filr or file.xml   */
  /* output name: file.in            */
  /*************************************************************************************************/
  String get_output_name(String str_input)
  {
      int idx;
      String str_output=null;
      idx = 0;
      idx = str_input.indexOf(".");
         
      try {
            if (idx != -1)
              	str_output = str_input.substring(0, idx) + ".in";
            else
		str_output = str_input + ".in";
      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile for get_output_name");
      }
      return str_output;
  }

    /*************************************************************************************************/
  /*********************  porcess to remove the hyphen - for the telephone  ************************/ 
  /*************************************************************************************************/
  String remove_phone_hyphen(String str_input)
  {
      int idx;
      int str_len= 0;
      String str_output=null;
      String str_temp="";
      idx = 0;
     
      str_len = str_input.length(); 
      idx = str_input.indexOf("-");
         
      try {
            while(idx != -1){
                 str_temp = str_input.substring(0,idx) + str_input.substring(idx + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx = str_input.indexOf("-");
            }
      str_output = str_input; 
      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile for remove_phone_hyphen");
      }
      return str_output;
  }

  /*************************************************************************************************/
  /*********************  transfer the religion desc to Banner religion code                ********/ 
  /*************** the Religious Preference from CA4  **********************************************/
  /*************************************************************************************************/
  String religion_transfer(String str_input)
  {
      String str_out="";
   if (!str_input.equals("")) {
      if (str_input.equals("EPI")) 
            str_out = "EP";
      else if (str_input.equals("BAP")) 
            str_out = "PR";
      else if (str_input.equals("BAH")) 
            str_out = "BH";
      else if (str_input.equals("BUD")) 
            str_out = "BU";
      else if (str_input.equals("CDI")) 
            str_out = "CD";
      else if (str_input.equals("CAT")) 
            str_out = "RC";
      else if (str_input.equals("CHO")) 
            str_out = "CE";
      else if (str_input.equals("CSC")) 
            str_out = "CS";
      else if (str_input.equals("CCH")) 
            str_out = "PR";
      else if (str_input.equals("CWM")) 
            str_out = "OT";
      else if (str_input.equals("CON")) 
            str_out = "BU";
      else if (str_input.equals("CGR")) 
            str_out = "CO";
      else if (str_input.equals("QUA")) 
            str_out = "QU";
      else if (str_input.equals("EPI")) 
            str_out = "EP";
      else if (str_input.equals("JEH")) 
            str_out = "JW";
      else if (str_input.equals("LDS")) 
            str_out = "MO";
      else if (str_input.equals("LUT")) 
            str_out = "PR";
      else if (str_input.equals("MET")) 
            str_out = "ME";
      else if (str_input.equals("MVN")) 
            str_out = "MR";
      else if (str_input.equals("ORT")) 
            str_out = "OR";
      else if (str_input.equals("PEN")) 
            str_out = "PR";
      else if (str_input.equals("PRB")) 
            str_out = "PR";
      else if (str_input.equals("REF")) 
            str_out = "RE";
      else if (str_input.equals("RAS")) 
            str_out = "OT";
      else if (str_input.equals("SDA")) 
            str_out = "SD";
      else if (str_input.equals("TAO")) 
            str_out = "BU";
      else if (str_input.equals("COT")) 
            str_out = "OT";
      else if (str_input.equals("HIN")) 
            str_out = "HI";
      else if (str_input.equals("JAI")) 
            str_out = "JA";
      else if (str_input.equals("HBR")) 
            str_out = "JE";
      else if (str_input.equals("MUS")) 
            str_out = "MS";
      else if (str_input.equals("SEI")) 
            str_out = "SE";
      else if (str_input.equals("SIK")) 
            str_out = "SI";
      else if (str_input.equals("TEN")) 
            str_out = "TE";
      else if (str_input.equals("UNI")) 
            str_out = "UN";
      else if (str_input.equals("PAG")) 
            str_out = "PG";
      else if (str_input.equals("YAZ")) 
            str_out = "YZ";
      else if (str_input.equals("NON")) 
            str_out = "NR";
      else if (str_input.equals("OTH")) 
            str_out = "OT";
      else
            str_out = "OT";
     }
     else
           str_out = "00";
      return str_out;
  }

  /*************************************************************************************************/
  /*********************  transfer the commonapp lanugage code to Banner code               ********/ 
  /*************************************************************************************************/
  String language_transfer(String str_input)
  {
      String str_out="";
      if (str_input.equals("eng"))
            str_out = "ENG";
      else if (str_input.equals("sqi"))
            str_out = "ALB";
      else if (str_input.equals("ara")) 
            str_out = "ARA";
      else if (str_input.equals("hye")) 
            str_out = "ARM";
      else if (str_input.equals("asm")) 
            str_out = "ASM";
      else if (str_input.equals("aze")) 
            str_out = "AZE";
      else if (str_input.equals("bel")) 
            str_out = "BEL";
      else if (str_input.equals("ben")) 
            str_out = "BEN";
      else if (str_input.equals("bul")) 
            str_out = "BUL";
      else if (str_input.equals("cat")) 
            str_out = "CAT";
      else if (str_input.equals("zho")) 
            str_out = "CHI";
      else if (str_input.equals("scr")) 
            str_out = "SCR";
      else if (str_input.equals("ces")) 
            str_out = "CZE";
      else if (str_input.equals("dan")) 
            str_out = "DAN";
      else if (str_input.equals("nld")) 
            str_out = "DUT";
      else if (str_input.equals("est")) 
            str_out = "EST";
      else if (str_input.equals("fin")) 
            str_out = "FIN";
      else if (str_input.equals("fra")) 
            str_out = "FRN";
      else if (str_input.equals("kat")) 
            str_out = "GEO";
      else if (str_input.equals("deu")) 
            str_out = "GRM";
      else if (str_input.equals("ell")) 
            str_out = "GRK";
      else if (str_input.equals("guj")) 
            str_out = "GUJ";
      else if (str_input.equals("heb")) 
            str_out = "HEB";
      else if (str_input.equals("hin")) 
            str_out = "HND";
      else if (str_input.equals("hun")) 
            str_out = "HUN";
      else if (str_input.equals("ice")) 
            str_out = "ICE";
      else if (str_input.equals("ind")) 
            str_out = "IND";
      else if (str_input.equals("ita")) 
            str_out = "ITL";
      else if (str_input.equals("jpn")) 
            str_out = "JPN";
      else if (str_input.equals("jav")) 
            str_out = "JAV";
      else if (str_input.equals("kor")) 
            str_out = "KOR";
      else if (str_input.equals("lav")) 
            str_out = "LAV";
      else if (str_input.equals("lit")) 
            str_out = "LIT";
      else if (str_input.equals("mac")) 
            str_out = "MAC";
      else if (str_input.equals("may")) 
            str_out = "XXX";
      else if (str_input.equals("mal")) 
            str_out = "MAL";
      else if (str_input.equals("MAR")) 
            str_out = "MAR";
      else if (str_input.equals("mol")) 
            str_out = "MOL";
      else if (str_input.equals("nep")) 
            str_out = "NEP";
      else if (str_input.equals("nor")) 
            str_out = "NOR";
      else if (str_input.equals("xxx")) 
            str_out = "XXX";
      else if (str_input.equals("per")) 
            str_out = "PER";
      else if (str_input.equals("pol")) 
            str_out = "POL";
      else if (str_input.equals("por")) 
            str_out = "POR";
      else if (str_input.equals("ron")) 
            str_out = "RUM";
      else if (str_input.equals("rus")) 
            str_out = "RUS";
      else if (str_input.equals("hbs")) 
            str_out = "SCC";
      else if (str_input.equals("slk")) 
            str_out = "SLO";
      else if (str_input.equals("slv")) 
            str_out = "SLV";
      else if (str_input.equals("spa")) 
            str_out = "SPN";
      else if (str_input.equals("swe")) 
            str_out = "SWD";
      else if (str_input.equals("tgl")) 
            str_out = "TAG";
      else if (str_input.equals("tam")) 
            str_out = "TAM";
      else if (str_input.equals("tat")) 
            str_out = "TAT";
      else if (str_input.equals("tel")) 
            str_out = "TEL";
      else if (str_input.equals("tha")) 
            str_out = "THA";
      else if (str_input.equals("tur")) 
            str_out = "TUR";
      else if (str_input.equals("ukr")) 
            str_out = "UKR";
      else if (str_input.equals("urd")) 
            str_out = "URD";
      else if (str_input.equals("vie")) 
            str_out = "VIE";
      else
            str_out = "XXX";
      return str_out;
  }
  /*************************************************************************************************/
  /*********************  porcess to transfer the A08 test score from one char to two chars ********/ 
  /************************* to meet the stvtest validation                  ***********************/
  /*************************************************************************************************/
  String test_score_transfer(String str_input)
  {
      int idx;
      int str_len= 0;
      String str_output=null;
      String str_temp="";
      idx = 0;
     
      str_len = str_input.length(); 
         
      try {
             if (str_len == 1){
                 str_input = "0" + str_input; 
              }
      str_output = str_input; 
      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile for test_score_trander");
      }
      return str_output;
  }

  /*************************************************************************************************/
  /*********************  transfer common app activity code to banner interests code        ********/
  /*************************************************************************************************/
  String activity_transfer(String str_input)
  {
      String str_out="";

      //remove trailing white space from a string
      str_input = str_input.replaceAll("\\s+$", "");
      if (str_input.equals("ART"))
            str_out = "AH";
      else if (str_input.equals("Athletics_Club"))
            str_out = "AA";
      else if (str_input.equals("Athletics_Varsity"))
            str_out = "AB";
      else if (str_input.equals("Career"))
            str_out = "AI";
      else if (str_input.equals("Volunteer"))
            str_out = "AG";
      else if (str_input.equals("Computer"))
            str_out = "CS";
      else if (str_input.equals("Cultural"))
            str_out = "HU";
      else if (str_input.equals("Dance"))
            str_out = "AJ";
      else if (str_input.equals("Debate"))
            str_out = "A5";
      else if (str_input.equals("Environmental"))
            str_out = "AK";
      else if (str_input.equals("Family"))
            str_out = "ZF";
      else if (str_input.equals("Foreign_Exchange"))
            str_out = "AL";
      else if (str_input.equals("Foreign_Lang"))
            str_out = "AL";
      else if (str_input.equals("Journalism"))
            str_out = "A4";
      else if (str_input.equals("Jr_ROTC"))
            str_out = "AN";
      else if (str_input.equals("LGBT"))
            str_out = "AF";
      else if (str_input.equals("Instrumental"))
            str_out = "A1";
      else if (str_input.equals("Vocal"))
            str_out = "A2";
      else if (str_input.equals("Religious"))
            str_out = "A8";
      else if (str_input.equals("School_Spirit"))
            str_out = "PA";
      else if (str_input.equals("Science_Math"))
            str_out = "SC";
      else if (str_input.equals("Politics"))
            str_out = "SG";
      else if (str_input.equals("Drama"))
            str_out = "A7";
      else if (str_input.equals("Work"))
            str_out = "EM";
      else if (str_input.equals("Other_Club"))
            str_out = "A6";
      else if (str_input.equals("Archery"))
            str_out = "AA";
      else if (str_input.equals("Badminton"))
            str_out = "AA";
      else if (str_input.equals("Baseball"))
            str_out = "AA";
      else if (str_input.equals("Basketball"))
            str_out = "AA";
      else if (str_input.equals("Bowling"))
            str_out = "AA";
      else if (str_input.equals("Boxing"))
            str_out = "AA";
      else if (str_input.equals("Cheerleading"))
            str_out = "AA";
      else if (str_input.equals("Cricket"))
            str_out = "AA";
      else if (str_input.equals("Cross_country"))
            str_out = "AA";
      else if (str_input.equals("Diving"))
            str_out = "AA";
      else if (str_input.equals("Equestrian"))
            str_out = "AA";
      else if (str_input.equals("Fencing"))
            str_out = "AA";
      else if (str_input.equals("Field_Hockey"))
            str_out = "AA";
      else if (str_input.equals("Football"))
            str_out = "AA";
      else if (str_input.equals("Non_tackle_football"))
            str_out = "AA";
      else if (str_input.equals("Gymnastics"))
            str_out = "AA";
      else if (str_input.equals("Handball"))
            str_out = "AA";
      else if (str_input.equals("Ice_Hockey"))
            str_out = "AA";
      else if (str_input.equals("Indoor_Track"))
            str_out = "AA";
      else if (str_input.equals("Judo"))
            str_out = "AA";
      else if (str_input.equals("Lacrosse"))
            str_out = "AA";
      else if (str_input.equals("Outdoor_Track"))
            str_out = "AA";
      else if (str_input.equals("Racquetball"))
            str_out = "AA";
      else if (str_input.equals("Rifle"))
            str_out = "AA";
      else if (str_input.equals("Rodeo"))
            str_out = "AA";
      else if (str_input.equals("Rowing"))
            str_out = "AA";
      else if (str_input.equals("Rugby"))
            str_out = "AA";
      else if (str_input.equals("Sailing"))
            str_out = "AA";
      else if (str_input.equals("Skiing"))
            str_out = "AA";
      else if (str_input.equals("Skin_Diving"))
            str_out = "AA";
      else if (str_input.equals("Soccer"))
            str_out = "AA";
      else if (str_input.equals("Softball"))
            str_out = "AA";
      else if (str_input.equals("Squash"))
            str_out = "AA";
      else if (str_input.equals("Swim"))
            str_out = "AA";
      else if (str_input.equals("Sync_Swim"))
            str_out = "AA";
      else if (str_input.equals("Table_Tennis"))
            str_out = "AA";
      else if (str_input.equals("Tennis"))
            str_out = "AA";
      else if (str_input.equals("Track_field"))
            str_out = "AA";
      else if (str_input.equals("Triathalon"))
            str_out = "AA";
      else if (str_input.equals("Volleyball"))
            str_out = "AA";
      else if (str_input.equals("Water polo"))
            str_out = "AA";
      else if (str_input.equals("Weight_lifting"))
            str_out = "AA";
      else if (str_input.equals("Wrestling"))
            str_out = "AA";
      else if (str_input.equals("OTH"))
            str_out = "AA";
      else
            str_out = "OT";
      return str_out;
  }
 
  /*************************************************************************************************/
  /*********************  porcess to remove the hyphen - for the high school end date   ************/ 
  /************************  RD5 format CCYYMM                                        **************/
  /*************************************************************************************************/
  String format_month_year_date(String str_input)
  {
      int idx=0;
      int ln= 0;
      String str_output=null;
      String str_year=null;
      String str_mon=null;
      String str_day=null;

      idx = str_input.indexOf("/");
      ln = str_input.length();   
      
      try {
            if (idx != -1){
                str_mon = str_input.substring(0,idx);
                str_year= str_input.substring(idx+4, ln); 
                if (ln == 10) {
                     str_mon = str_input.substring(0,idx);
			str_year= str_input.substring(idx+4, ln); 
              	str_output = str_year + str_mon;
                }else if (ln == 7) {
                     str_mon = str_input.substring(0,idx);
			str_year= str_input.substring(idx+1,ln); 
              	str_output = str_year + str_mon;
                }
            }else
              	str_output = str_input; 
      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile for format_month_eary_day");
      }
      
      return str_output;
  }
    
  /*************************************************************************************************/
  /*********************  porcess to remove the hyphen - for the high school end date   ************/ 
  /*************************************************************************************************/
  String format_month_year_day(String str_input)
  {
      int idx=0;
      int ln= 0;
      String str_output=null;
      String str_year=null;
      String str_mon=null;
      String str_day=null;

      idx = str_input.indexOf("/");
      ln = str_input.length();   
      
      try {
            if (idx != -1){
                str_mon = str_input.substring(0,idx);
                str_year= str_input.substring(idx+4, ln); 
                if (ln == 10) 
              		str_output = str_input.substring(idx+4,ln) + str_input.substring(0,idx) + "02";
                else if (ln == 7) 
              		str_output = str_input.substring(idx+1,ln) + str_input.substring(0,idx) + "02";
            }else
              	str_output = str_input; 
      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile for format_month_eary_day");
      }
      
      return str_output;
  }

  /*************************************************************************************************/
  /*********************  porcess to term code to year   ******************************************/
  /*************************************************************************************************/
  String banner_termcode_to_year(String str_input)
  {
      String str_output=null;
      String str_year; 
      String str_term;

      try{ 
      	str_year = str_input.substring(0, 4);
      	//str_year = "2009";
      	// str_term = "30"; 
      	str_term = str_input.substring(4, 6);
 
            	if (str_term.equals("40"))
              	str_output = str_year + "1001";  
                //20 + str_input.substring(0, idx_F) + 1001 
            	else if (str_term.equals("20"))
              	str_output = str_year + "0301"; 
                 //20 + str_input.substring(0, idx_S) + "0301"; 
            	else if (str_term.equals("30"))
              	str_output = str_year + "0701"; 
                 //20 + str_input.substring(0, idx_M) + "0701"; 
            	else
              	str_output = str_year + str_term; 

      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile");
      }
      return str_output;      
  }
  /*************************************************************************************************/
  /*********************  crosswalk the special program witht the defined code          ************/ 
  /*************************************************************************************************/
  String special_program_crosswalk(String str_input)
  {
      int ln= 0;
      String str_output=null;
           if (str_input.equals("4+3 Law"))
		str_output = "ZA";
	    else if(str_input.equals("Military Science"))
		str_output = "ZK";
	    else if(str_input.equals("Davies Forum"))
		str_output = "ZB";
	    else if(str_input.equals("Study Abroad"))
		str_output = "SA";
	    else if(str_input.equals("3+2 Engineering"))
		str_output = "ZM";
	    else if(str_input.equals("St. Ignatius Institute"))
		str_output = "ZO";
	    else if(str_input.equals("New Venture Center"))
		str_output = "ZL";
	    else if(str_input.equals("Leo T. McCarthy Public Service"))
		str_output = "ZI";
	    else if(str_input.equals("Dual Degree in Teacher Preparation"))
		str_output = "ZP";
	    else if(str_input.substring(0,4).equals("Mart"))
		str_output = "ZJ";
	    else if(str_input.equals("Family Business Center"))
		str_output = "ZF";
	    else if(str_input.equals("Pre-Professional Health Studies"))
		str_output = "ZN";
	    else if(str_input.equals("Honors in the Humanities"))
		str_output = "ZH";
	    else if(str_input.equals("Freshman Seminars"))
		str_output = "ZG";
	    else
		str_output = "";
      return str_output;
  }
  
  /*************************************************************************************************/
  /*********************  function to change toefl score to char for validation         ************/ 
  /*************************************************************************************************/
  String toefl_score_validation(String str_test_type, String str_input)
  {
      int ln= 0;
      String str_output=null;
      ln = str_input.length();
          if (str_test_type.equals("IELTS")){
	    	if(ln == 1)
			str_output =  str_input + ".0";
	    	else
			str_output = str_input;
          }
          else
          {
           	if (ln == 2)
			str_output = "0" + str_input;
	    	else if(ln == 1)
			str_output = "00" + str_input;
	    	else
			str_output = str_input;
           }
      return str_output;
  }
  /*************************************************************************************************/
  /*********************  porcess to remove the hyphen - for the high school end date   ************/ 
  /*************************************************************************************************/
  String format_mdy_date(String str_input)
  {
      int idx=0;
      int idx1 = 0;
      int ln= 0;
      int ln1 = 0;
      String str_output=null;
      idx = str_input.indexOf("/");
      ln = str_input.length();   
      try {
            if (idx != -1){
            
              	str_output = str_input.substring(idx+1,ln) + str_input.substring(0,idx) ;
                idx1 = str_output.indexOf("/");
                ln1 = str_output.length();   
                if(idx1 != -1)
                      str_output = str_output.substring(idx1+1, ln1);
            } 
            else
              	str_output = str_input; 
      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile for format_dmy_date");
      }
      return str_output;
  }


  /*************************************************************************************************/
  /*********************  porcess to remove the hyphen - for the SSN        ************************/ 
  /*************************************************************************************************/
  String remove_ssn_hyphen(String str_input)
  {
      int idx;
      int str_len= 0;
      String str_output=null;
      String str_temp="";
      idx = 0;
     
      str_len = str_input.length(); 
      idx = str_input.indexOf("-");
         
      try {
            while(idx != -1){
                 str_temp = str_input.substring(0,idx) + str_input.substring(idx + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx = str_input.indexOf("-");
            }
      str_output = str_input; 
      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile for remove_ssn_hyphen");
      }
      return str_output;
  }
  /*************************************************************************************************/
  /*********************  porcess to remove the backslash - for birthdate      *********************/ 
  /*************************************************************************************************/
  String process_birthdate(String str_input)
  {
      int idx;
      int str_len= 0;
      int str_year;
      int str_mondate;
      String str_output=null;
      String str_temp="";
      idx = 0;
     
      str_len = str_input.length(); 
      idx = str_input.indexOf("/");
         
      try {
            while(idx != -1){
                 str_temp = str_input.substring(0,idx) + str_input.substring(idx + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx = str_input.indexOf("/");
            }
      str_input = str_input.substring(4,8) + str_input.substring(0,4);
      str_output = str_input; 
      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile for birthdate");
      }
      return str_output;
  }


  /*************************************************************************************************/
  /*****************  get the stvmajr major code from combined AY program **************************/
  /*************************************************************************************************/
  String ay_major_capture(String str_input)
  {
      String str_output=null;
      int idx;
      int str_len= 0;
      int cnt;
      String str_level = "";
      String str_tmp = "";
      String str_major = "";

      cnt = 0;
      idx = 0;
      str_len = str_input.length(); 
      idx = str_input.indexOf("-");
      try{ 
	  while((idx != -1)&(cnt <1)) {
                 str_tmp = str_input.substring(idx + 1, str_len);
                 str_input = str_tmp;
                 str_len = str_input.length(); 
                 cnt = cnt + 1;
                 if (cnt == 1){
                         idx = str_input.indexOf("-");
                         if (idx != -1)
                         	str_major = str_input.substring(0, idx);
                         else
                            str_major = str_input;
                 }
          }

      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile in major");
      }
      return str_major;      
  }  
  /*************************************************************************************************/
  /***********************************    country code transfer function    ************************/
  /*************************************************************************************************/
 String country_transfer(String str_input){
  String str_output = "";
    if (str_input.equals("USA"))
         str_output = "US";
    else if (str_input.equals("AFG"))
         str_output = "AF";
    else if (str_input.equals("ALA"))
         str_output = "ALA";
    else if (str_input.equals("ALB"))
         str_output = "AL";
    else if (str_input.equals("DZA"))
         str_output = "AG";
    else if (str_input.equals("ASM"))
         str_output = "AQ";
    else if (str_input.equals("AND"))
         str_output = "AN";
    else if (str_input.equals("AGO"))
         str_output = "AO";
    else if (str_input.equals("AIA"))
         str_output = "AV";
    else if (str_input.equals("ATA"))
         str_output = "AY";
    else if (str_input.equals("ATG"))
         str_output = "AC";
    else if (str_input.equals("ARG"))
         str_output = "AR";
    else if (str_input.equals("ARM"))
         str_output = "AM";
    else if (str_input.equals("ABW"))
         str_output = "AA";
    else if (str_input.equals("AUS"))
         str_output = "AS";
    else if (str_input.equals("AUT"))
         str_output = "AU";
    else if (str_input.equals("AZE"))
         str_output = "AJ";
    else if (str_input.equals("BHS"))
         str_output = "BF";
    else if (str_input.equals("BHR"))
         str_output = "BA";
    else if (str_input.equals("BGD"))
         str_output = "BG";
    else if (str_input.equals("BRB"))
         str_output = "BB";
    else if (str_input.equals("BLR"))
         str_output = "BO";
    else if (str_input.equals("BEL"))
         str_output = "BE";
    else if (str_input.equals("BLZ"))
         str_output = "BH";
    else if (str_input.equals("BEN"))
         str_output = "BN";
    else if (str_input.equals("204"))
         str_output = "";
    else if (str_input.equals("BMU"))
         str_output = "BD";
    else if (str_input.equals("BTN"))
         str_output = "BT";
    else if (str_input.equals("BOL"))
         str_output = "BL";
    else if (str_input.equals("BIH"))
         str_output = "BK";
    else if (str_input.equals("BWA"))
         str_output = "BC";
    else if (str_input.equals("BVT"))
         str_output = "BV";
    else if (str_input.equals("BRA"))
         str_output = "BR";
    else if (str_input.equals("IOT"))
         str_output = "IO";
    else if (str_input.equals("BRN"))
         str_output = "BX";
    else if (str_input.equals("BGR"))
         str_output = "BU";
    else if (str_input.equals("BFA"))
         str_output = "UV";
    else if (str_input.equals("BDI"))
         str_output = "BY";
    else if (str_input.equals("KHM"))
         str_output = "CB";
    else if (str_input.equals("CAN"))
         str_output = "CA";
    else if (str_input.equals("CMR"))
         str_output = "CM";
    else if (str_input.equals("CPV"))
         str_output = "CV";
    else if (str_input.equals("CYM"))
         str_output = "CJ";
    else if (str_input.equals("CAF"))
         str_output = "CT";
    else if (str_input.equals("TCD"))
         str_output = "CD";
    else if (str_input.equals("CHL"))
         str_output = "CI";
    else if (str_input.equals("CHN"))
         str_output = "CH";
    else if (str_input.equals("CXR"))
         str_output = "KT";
    else if (str_input.equals("CCK"))
         str_output = "CK";
    else if (str_input.equals("COL"))
         str_output = "CO";
    else if (str_input.equals("COM"))
         str_output = "CN";
    else if (str_input.equals("COG"))
         str_output = "CG";
    else if (str_input.equals("GOD"))
         str_output = "CF";
    else if (str_input.equals("COK"))
         str_output = "CW";
    else if (str_input.equals("CRI"))
         str_output = "CS";
    else if (str_input.equals("CIV"))
         str_output = "IV";
    else if (str_input.equals("HRV"))
         str_output = "HR";
    else if (str_input.equals("CUB"))
         str_output = "CU";
    else if (str_input.equals("CYP"))
         str_output = "CY";
    else if (str_input.equals("CZE"))
         str_output = "EZ";
    else if (str_input.equals("DNK"))
         str_output = "DA";
    else if (str_input.equals("DJI"))
         str_output = "DJ";
    else if (str_input.equals("DMA"))
         str_output = "DO";
    else if (str_input.equals("DOM"))
         str_output = "DR";
    else if (str_input.equals("ECU"))
         str_output = "EC";
    else if (str_input.equals("EGY"))
         str_output = "EG";
    else if (str_input.equals("SLV"))
         str_output = "ES";
    else if (str_input.equals("GNQ"))
         str_output = "EK";
    else if (str_input.equals("ERI"))
         str_output = "ER";
    else if (str_input.equals("EST"))
         str_output = "EN";
    else if (str_input.equals("ETH"))
         str_output = "ET";
    else if (str_input.equals("FRO"))
         str_output = "FO";
    else if (str_input.equals("FLK"))
         str_output = "FK";
    else if (str_input.equals("FJI"))
         str_output = "FJ";
    else if (str_input.equals("FIN"))
         str_output = "FI";
    else if (str_input.equals("FRA"))
         str_output = "FR";
    else if (str_input.equals("GUF"))
         str_output = "FG";
    else if (str_input.equals("PYF"))
         str_output = "FP";
    else if (str_input.equals("ATF"))
         str_output = "TF";
    else if (str_input.equals("GAB"))
         str_output = "GB";
    else if (str_input.equals("GMB"))
         str_output = "GA";
    else if (str_input.equals("GEO"))
         str_output = "GG";
    else if (str_input.equals("DEU"))
         str_output = "GM";
    else if (str_input.equals("GHA"))
         str_output = "GH";
    else if (str_input.equals("GIB"))
         str_output = "GI";
    else if (str_input.equals("GRC"))
         str_output = "GR";
    else if (str_input.equals("GRL"))
         str_output = "GL";
    else if (str_input.equals("GRD"))
         str_output = "GJ";
    else if (str_input.equals("GLP"))
         str_output = "GP";
    else if (str_input.equals("GTM"))
         str_output = "GT";
    else if (str_input.equals("GGY"))
         str_output = "GK";
    else if (str_input.equals("GIN"))
         str_output = "GY";
    else if (str_input.equals("GNB"))
         str_output = "PU";
    else if (str_input.equals("GUY"))
         str_output = "GY";
    else if (str_input.equals("HTI"))
         str_output = "HA";
    else if (str_input.equals("HMD"))
         str_output = "HM";
    else if (str_input.equals("VAT"))
         str_output = "VA";
    else if (str_input.equals("HND"))
         str_output = "HO";
    else if (str_input.equals("HKG"))
         str_output = "HK";
    else if (str_input.equals("HUN"))
         str_output = "HU";
    else if (str_input.equals("ISL"))
         str_output = "IC";
    else if (str_input.equals("IND"))
         str_output = "IN";
    else if (str_input.equals("IDN"))
         str_output = "ID";
    else if (str_input.equals("IRN"))
         str_output = "IR";
    else if (str_input.equals("IRQ"))
         str_output = "IZ";
    else if (str_input.equals("IRL"))
         str_output = "EI";
    else if (str_input.equals("IMN"))
         str_output = "IM";
    else if (str_input.equals("ISR"))
         str_output = "IS";
    else if (str_input.equals("ITA"))
         str_output = "IT";
    else if (str_input.equals("JAM"))
         str_output = "JM";
    else if (str_input.equals("JPN"))
         str_output = "JA";
    else if (str_input.equals("JEY"))
         str_output = "JE";
    else if (str_input.equals("JOR"))
         str_output = "JO";
    else if (str_input.equals("KAZ"))
         str_output = "KZ";
    else if (str_input.equals("KEN"))
         str_output = "KE";
    else if (str_input.equals("KIR"))
         str_output = "KR";
    else if (str_input.equals("PRK"))
         str_output = "KN";
    else if (str_input.equals("KOR"))
         str_output = "KS";
    else if (str_input.equals("KWT"))
         str_output = "KU";
    else if (str_input.equals("KGZ"))
         str_output = "KG";
    else if (str_input.equals("LAO"))
         str_output = "LA";
    else if (str_input.equals("LVA"))
         str_output = "LG";
    else if (str_input.equals("LBN"))
         str_output = "LE";
    else if (str_input.equals("LSO"))
         str_output = "LT";
    else if (str_input.equals("LBR"))
         str_output = "LI";
   else if (str_input.equals("LBY"))
         str_output = "LY";
    else if (str_input.equals("LIE"))
         str_output = "LS";
    else if (str_input.equals("LTU"))
         str_output = "LH";
    else if (str_input.equals("LUX"))
         str_output = "LU";
    else if (str_input.equals("MAC"))
         str_output = "MC";
    else if (str_input.equals("MKD"))
         str_output = "MK";
    else if (str_input.equals("MDG"))
         str_output = "MA";
    else if (str_input.equals("MVI"))
         str_output = "MI";
    else if (str_input.equals("MYS"))
         str_output = "MY";
    else if (str_input.equals("MDV"))
         str_output = "MV";
    else if (str_input.equals("MLI"))
         str_output = "ML";
    else if (str_input.equals("MLT"))
         str_output = "MT";
    else if (str_input.equals("MHL"))
         str_output = "RM";
    else if (str_input.equals("MTQ"))
         str_output = "MB";
    else if (str_input.equals("MRT"))
         str_output = "MR";
   else if (str_input.equals("MUS"))
         str_output = "MP";
    else if (str_input.equals("MYT"))
         str_output = "MF";
    else if (str_input.equals("MEX"))
         str_output = "MX";
    else if (str_input.equals("MDA"))
         str_output = "MD";
    else if (str_input.equals("MCO"))
         str_output = "MN";
    else if (str_input.equals("MNG"))
         str_output = "MG";
    else if (str_input.equals("MNE"))
         str_output = "MJ";
    else if (str_input.equals("MSR"))
         str_output = "MH";
    else if (str_input.equals("MAR"))
         str_output = "MO";
    else if (str_input.equals("MOZ"))
         str_output = "MZ";
    else if (str_input.equals("MMR"))
         str_output = "BM";
    else if (str_input.equals("NAM"))
         str_output = "WA";
    else if (str_input.equals("NRU"))
         str_output = "NR";
    else if (str_input.equals("NPL"))
         str_output = "NP";
    else if (str_input.equals("NLD"))
         str_output = "NL";
    else if (str_input.equals("ANT"))
         str_output = "NT";
    else if (str_input.equals("NCL"))
         str_output = "NC";
    else if (str_input.equals("NZL"))
         str_output = "NZ";
    else if (str_input.equals("NIC"))
         str_output = "NU";
    else if (str_input.equals("NER"))
         str_output = "NG";
    else if (str_input.equals("NGA"))
         str_output = "NI";
    else if (str_input.equals("NIU"))
         str_output = "NE";
    else if (str_input.equals("NFK"))
         str_output = "NF";
    else if (str_input.equals("MNP"))
         str_output = "CQ";
    else if (str_input.equals("NOR"))
         str_output = "NO";
    else if (str_input.equals("OMN"))
         str_output = "MU";
    else if (str_input.equals("PAK"))
         str_output = "PK";
    else if (str_input.equals("PLW"))
         str_output = "PL";
    else if (str_input.equals("PSE"))
         str_output = "PS";
    else if (str_input.equals("PAN"))
         str_output = "PM";
    else if (str_input.equals("PNG"))
         str_output = "PP";
    else if (str_input.equals("PRY"))
         str_output = "PA";
    else if (str_input.equals("PER"))
         str_output = "PE";
   else if (str_input.equals("PHL"))
         str_output = "RP";
    else if (str_input.equals("PCN"))
         str_output = "PC";
    else if (str_input.equals("POL"))
         str_output = "PL";
    else if (str_input.equals("PRT"))
         str_output = "PO";
    else if (str_input.equals("QAT"))
         str_output = "QA";
    else if (str_input.equals("REU"))
         str_output = "RE";
    else if (str_input.equals("ROU"))
         str_output = "RO";
    else if (str_input.equals("RUS"))
         str_output = "RS";
    else if (str_input.equals("RWA"))
         str_output = "RW";
    else if (str_input.equals("SHN"))
         str_output = "SH";
    else if (str_input.equals("KNA"))
         str_output = "SC";
    else if (str_input.equals("LCA"))
         str_output = "ST";
    else if (str_input.equals("SPM"))
         str_output = "SB";
    else if (str_input.equals("VCT"))
         str_output = "VC";
    else if (str_input.equals("BLM"))
         str_output = "TB";
    else if (str_input.equals("MAF"))
         str_output = "RN";
    else if (str_input.equals("WSM"))
         str_output = "WS";
    else if (str_input.equals("SMR"))
         str_output = "SM";
    else if (str_input.equals("STP"))
         str_output = "TP";
    else if (str_input.equals("SAU"))
         str_output = "SA";
    else if (str_input.equals("SEN"))
         str_output = "SG";
    else if (str_input.equals("SRB"))
         str_output = "RB";
    else if (str_input.equals("SYC"))
         str_output = "SE";
    else if (str_input.equals("SLE"))
         str_output = "SL";
    else if (str_input.equals("SGP"))
         str_output = "SN";
    else if (str_input.equals("SVK"))
         str_output = "LO";
    else if (str_input.equals("SVN"))
         str_output = "SI";
    else if (str_input.equals("SLB"))
         str_output = "BP";
    else if (str_input.equals("SOM"))
         str_output = "SO";
    else if (str_input.equals("ZAF"))
         str_output = "SF";
    else if (str_input.equals("SGS"))
         str_output = "SX";
    else if (str_input.equals("ESP"))
         str_output = "SP";
    else if (str_input.equals("LKA"))
         str_output = "CE";
    else if (str_input.equals("SDN"))
         str_output = "OD";
    else if (str_input.equals("SUR"))
         str_output = "NS";
    else if (str_input.equals("SJM"))
         str_output = "SV";
    else if (str_input.equals("SWZ"))
         str_output = "WZ";
    else if (str_input.equals("SWE"))
         str_output = "SW";
    else if (str_input.equals("CHE"))
         str_output = "SZ";
    else if (str_input.equals("SYR"))
         str_output = "SY";
    else if (str_input.equals("TWN"))
         str_output = "TW";
    else if (str_input.equals("TJK"))
         str_output = "TI";
    else if (str_input.equals("TZA"))
         str_output = "TZ";
   else if (str_input.equals("THA"))
         str_output = "TH";
    else if (str_input.equals("TLS"))
         str_output = "TL";
    else if (str_input.equals("TGO"))
         str_output = "TG";
    else if (str_input.equals("TKL"))
         str_output = "TK";
    else if (str_input.equals("TON"))
         str_output = "TN";
    else if (str_input.equals("TTO"))
         str_output = "TD";
    else if (str_input.equals("TUN"))
         str_output = "TS";
    else if (str_input.equals("TUR"))
         str_output = "TU";
    else if (str_input.equals("TKM"))
         str_output = "TX";
    else if (str_input.equals("TCA"))
         str_output = "TK";
    else if (str_input.equals("TUV"))
         str_output = "TV";
    else if (str_input.equals("UGA"))
         str_output = "UG";
    else if (str_input.equals("UKR"))
         str_output = "UP";
    else if (str_input.equals("ARE"))
         str_output = "AE";
    else if (str_input.equals("GBR"))
         str_output = "UK";
    else if (str_input.equals("UMI"))
         str_output = "UM";
    else if (str_input.equals("URY"))
         str_output = "UY";
    else if (str_input.equals("UZB"))
         str_output = "AG";
    else if (str_input.equals("VUT"))
         str_output = "NH";
    else if (str_input.equals("VEN"))
         str_output = "VE";
    else if (str_input.equals("VNM"))
         str_output = "VM";
    else if (str_input.equals("VGB"))
         str_output = "VE";
    else if (str_input.equals("WLF"))
         str_output = "WF";
    else if (str_input.equals("ESH"))
         str_output = "WI";

    else if (str_input.equals("YEM"))
         str_output = "YM";
    else if (str_input.equals("ZMB"))
         str_output = "ZA";
    else if (str_input.equals("ZWE"))
         str_output = "ZI";
    else 
         str_output = "";
      return str_output;
 }

  /*************************************************************************************************/
  /*********************  closswalk commomapp  TOEFL value to Banner value              ************/ 
  /*************************************************************************************************/
  String phone_type_crosswalk(String str_input)
  {
      int ln= 0;
      String str_output=null;
           if (str_input.equals("Mobile"))
		str_output = "CP";
	    else if(str_input.equals("Home"))
		str_output = "HP";
	    else
		str_output = "HP" ;
      return str_output;
  }
  /*************************************************************************************************/
  /*********************  closswalk commomapp  college degree based on CA4 values       ************/ 
  /*************************************************************************************************/
  String com_degree_cw(String str_input)
  {
      int ln= 0;
      String str_output=null;
           if (str_input.equals("Associates"))
		str_output = "AS";
	    else if(str_input.equals("Bachelors"))
		str_output = "BS";
	    else if(str_input.equals("Masters"))
		str_output = "MS";
	    else if(str_input.equals("MBA"))
		str_output = "MBA";
	    else if(str_input.equals("JD"))
		str_output = "NS";
	    else if(str_input.equals("Phd"))
		str_output = "PHD";
	    else
		str_output = "ND" ;
      return str_output;
  }
  /*************************************************************************************************/
  /*********************  porcess to remove the hyphen - for the telephone  ************************/ 
  /*****************  <Preferred_phone_number>+1.215-278-3598</Preferred_phone_number>  ************/
  /*************************************************************************************************/
  String phone_number_cleanup(String str_input)
  {
      int idx;
      int idx1;
      int idx2;
      int idx3;
      int idx4; // remove the empty space sign
      int idx5; // remove the + sign
      int idx6; // remove the / sign
      int idx7; // remove the / sign
      int str_len= 0;
      int str_len1= 0;
      int str_len2= 0;
      String str_output=null;
      String str_temp="";
      String str_first_three="";

      idx = 0;   //remove '-'sign
      idx1 = 0;  //remove '('sign
      idx2 = 0;  //remove ')'sign
      idx3 = 0;  //remove the comma.
      idx4 = 0;  //remove empty space 
      idx5 = 0;  //remove the + sign 
      idx6 = 0;  //remove the / sing
      idx7 = 0;  //remove the first 1 sign
         
      try {

            str_first_three = str_input.substring(0,3);
            if(str_first_three.equals("+1.")) {
                str_input = str_input.substring(3);
            }
            str_len = str_input.length(); 
            idx = str_input.indexOf("-");
            while(idx != -1){
                 str_temp = str_input.substring(0,idx) + str_input.substring(idx + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx = str_input.indexOf("-");
            }

            str_len = str_input.length(); 
            idx1 = str_input.indexOf("(");
            while(idx1 != -1){
                 str_temp = str_input.substring(0,idx1) + str_input.substring(idx1 + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx1 = str_input.indexOf("(");
            }

            str_len = str_input.length(); 
            idx2 = str_input.indexOf(")");
            while(idx2 != -1){
                 str_temp = str_input.substring(0,idx2) + str_input.substring(idx2 + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx2 = str_input.indexOf("(");
            }

   	     str_len = str_input.length(); 
            idx3 = str_input.indexOf(".");
            while(idx3 != -1){
                 str_temp = str_input.substring(0,idx3) + str_input.substring(idx3 + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx3 = str_input.indexOf(".");
            }

   	     str_len = str_input.length(); 
            idx4 = str_input.indexOf(" ");
            while(idx4 != -1){
                 str_temp = str_input.substring(0,idx4) + str_input.substring(idx4 + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx4 = str_input.indexOf(" ");
            }

   	     str_len = str_input.length(); 
            idx5 = str_input.indexOf("+");
             while(idx5 != -1){
                 str_temp = str_input.substring(0,idx5) + str_input.substring(idx5 + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx5 = str_input.indexOf("+");
            }

   	     str_len = str_input.length(); 
            idx6 = str_input.indexOf("/");
             while(idx6 != -1){
                 str_temp = str_input.substring(0,idx6) + str_input.substring(idx6 + 1, str_len);
                 str_input = str_temp;
      		 str_len = str_input.length(); 
      		 idx6 = str_input.indexOf("/");
            }
            str_output = str_input; 
      }catch (StringIndexOutOfBoundsException ex){
    	    System.err.println("can't process the inoutfile for phone_number_cleanup and other char");
      }
      return str_output;
  }
  /*************************************************************************************************/
  /***********************************    main function           **********************************/
  /*************************************************************************************************/
   public static void main( String[] arg ) throws Exception {
      String str_input;

      szrcomm xmlparser = new szrcomm();
      str_input = arg[0];
      xmlparser.DoParser(str_input);
 }
}
