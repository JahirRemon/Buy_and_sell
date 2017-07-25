package com.example.mdjahirulislam.final_project_bitm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Trainer on 3/29/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sell";
    public static final int DATABASE_VERSION = 3;

    public static final String TABLE_USER_DETAILS = "user_registration_table";
    public static final String TABLE_AD_POST = "post_ad_table";
    public static final String TABLE_TEMP_POST = "temp_post_ad_table";
    public static final String TABLE_ALL_ADS_IMAGE = "all_ads_image_table";
    public static final String TABLE_FAVOURITE_ADS = "favourite_ads_table";


//    public static final String ORDER_BY = "COL_AD_POST_ID";



    // column of TABLE_USER_DETAILS

    public static final String COL_USER_ID = "user_id";
    public static final String COL_USER_UNIQUE_ID = "user_unique_id";
    public static final String COL_USER_FULL_NAME = "user_full_name";
    public static final String COL_USER_MOBILE_NO = "user_mobile_no";
    public static final String COL_USER_EMAIL = "user_email";
    public static final String COL_USER_CREATED_AT = "user_created_at";



    // column of TABLE_ADS_POST

    public static final String COL_AD_POST_ID = "post_id";
    public static final String COL_AD_POST_UNIQUE_ID = "post_unique_id";
    public static final String COL_AD_POST_USER_UNIQUE_ID = "user_unique_id";
    public static final String COL_AD_POST_USER_FULL_NAME = "user_full_name";
    public static final String COL_AD_POST_USER_MOBILE_NO = "user_mobile_no";
    public static final String COL_AD_POST_IMAGE_URL = "post_image_url";
    public static final String COL_AD_POST_TITLE = "post_title";
    public static final String COL_AD_POST_PRICE = "post_price";
    public static final String COL_AD_POST_DETAILS = "post_details";
    public static final String COL_AD_POST_LOCATION = "post_location";
    public static final String COL_AD_POST_CONDITION = "post_condition";
    public static final String COL_AD_POST_CATEGORY= "post_category";
    public static final String COL_AD_POST_LATITUDE= "post_latitude";
    public static final String COL_AD_POST_LONGITUDE= "post_longitude";
    public static final String COL_AD_POST_CREATED_AT = "created_at";



    // column of TABLE_TEMP_ADS_POST

    public static final String COL_TEMP_POST_ID = "post_id";
//    public static final String COL_TEMP_POST_UNIQUE_ID = "post_unique_id";
    public static final String COL_TEMP_POST_USER_UNIQUE_ID = "user_unique_id";
//    public static final String COL_TEMP_POST_USER_FULL_NAME = "user_full_name";
//    public static final String COL_TEMP_POST_USER_MOBILE_NO = "user_mobile_no";
    public static final String COL_TEMP_POST_IMAGE_URL = "post_image_url";
    public static final String COL_TEMP_POST_TITLE = "post_title";
    public static final String COL_TEMP_POST_PRICE = "post_price";
    public static final String COL_TEMP_POST_DETAILS = "post_details";
    public static final String COL_TEMP_POST_LOCATION = "post_location";
    public static final String COL_TEMP_POST_CONDITION = "post_condition";
    public static final String COL_TEMP_POST_CATEGORY= "post_category";
    public static final String COL_TEMP_POST_LATITUDE= "post_latitude";
    public static final String COL_TEMP_POST_LONGITUDE= "post_longitude";
//    public static final String COL_TEMP_POST_CREATED_AT = "created_at";



    // column of TABLE_ALL_ADS_IMAGE

    public static final String COL_ALL_ADS_IMAGE_ID = "ads_image_id";
    public static final String COL_ALL_ADS_IMAGE_UNIQUE_ID = "ads_image_unique_id";
    public static final String COL_ALL_ADS_IMAGE_POST_UNIQUE_ID = "post_unique_id";
    public static final String COL_ALL_ADS_IMAGE_URL = "ads_image_url";
    public static final String COL_ALL_ADS_IMAGE_CREATED_AT = "created_at";



    // column of TABLE_FAVOURITE_ADS

    public static final String COL_FAVOURITE_ID = "favourite_id";
    public static final String COL_FAVOURITE_UNIQUE_ID = "favourite_unique_id";
    public static final String COL_FAVOURITE_USER_UNIQUE_ID = "user_unique_id";
    public static final String COL_FAVOURITE_POST_UNIQUE_ID = "post_unique_id";
    public static final String COL_FAVOURITE_STATUS = "favourite_status";
    public static final String COL_FAVOURITE_CREATED_AT = "created_at";






    /*public static final String CREATE_MOVIE_TABLE1 = "create table tbl_movie(tbl_id integer primary key, tbl_name text, tbl_year text);";*/

    public static final String CREATE_USER_REGISTRATION_TABLE = "create table "+ TABLE_USER_DETAILS +"("+
            COL_USER_ID +" integer primary key, "+
            COL_USER_UNIQUE_ID +" integer, "+
            COL_USER_FULL_NAME +" text, "+
            COL_USER_MOBILE_NO +" text, "+
            COL_USER_EMAIL +" text, "+
            COL_USER_CREATED_AT +" text);";

    public static final String CREATE_AD_POST_TABLE = "create table "+ TABLE_AD_POST +"("+
            COL_AD_POST_ID +" integer primary key, "+
            COL_AD_POST_UNIQUE_ID +" integer, "+
            COL_AD_POST_USER_UNIQUE_ID +" integer, "+
            COL_AD_POST_USER_FULL_NAME +" text, "+
            COL_AD_POST_USER_MOBILE_NO +" text, "+
            COL_AD_POST_IMAGE_URL +" text, "+
            COL_AD_POST_TITLE +" text, "+
            COL_AD_POST_PRICE +" integer, "+
            COL_AD_POST_DETAILS +" text, "+
            COL_AD_POST_LOCATION +" text, "+
            COL_AD_POST_CONDITION +" text, "+
            COL_AD_POST_CATEGORY +" text, "+
            COL_AD_POST_LATITUDE +" text, "+
            COL_AD_POST_LONGITUDE +" text, "+
            COL_AD_POST_CREATED_AT +" text);";

    public static final String CREATE_TEMP_POST_TABLE = "create table "+ TABLE_TEMP_POST +"("+
            COL_TEMP_POST_ID +" integer primary key, "+
//            COL_TEMP_POST_UNIQUE_ID +" integer, "+
            COL_TEMP_POST_USER_UNIQUE_ID +" integer, "+
//            COL_TEMP_POST_USER_FULL_NAME +" text, "+
//            COL_TEMP_POST_USER_MOBILE_NO +" text, "+
            COL_TEMP_POST_IMAGE_URL +" text, "+
            COL_TEMP_POST_TITLE +" text, "+
            COL_TEMP_POST_PRICE +" text, "+
            COL_TEMP_POST_DETAILS +" text, "+
            COL_TEMP_POST_LOCATION +" text, "+
            COL_TEMP_POST_CONDITION +" text, "+
            COL_TEMP_POST_CATEGORY +" text, "+
            COL_TEMP_POST_LATITUDE+" text, "+
            COL_TEMP_POST_LONGITUDE +" text);";
//            COL_TEMP_POST_CREATED_AT +" text);";



    public static final String CREATE_ALL_ADS_IMAGES_TABLE = "create table "+ TABLE_ALL_ADS_IMAGE +"("+
            COL_ALL_ADS_IMAGE_ID +" integer primary key, "+
            COL_ALL_ADS_IMAGE_UNIQUE_ID +" integer, "+
            COL_ALL_ADS_IMAGE_POST_UNIQUE_ID +" integer, "+
            COL_ALL_ADS_IMAGE_URL +" text, "+
            COL_ALL_ADS_IMAGE_CREATED_AT +" text);";

    public static final String CREATE_FAVOURITE_ADS_TABLE = "create table "+ TABLE_FAVOURITE_ADS +"("+
            COL_FAVOURITE_ID +" integer primary key, "+
            COL_FAVOURITE_UNIQUE_ID +" integer, "+
            COL_FAVOURITE_USER_UNIQUE_ID +" integer, "+
            COL_FAVOURITE_POST_UNIQUE_ID +" integer, "+
            COL_FAVOURITE_STATUS +" text, "+
            COL_FAVOURITE_CREATED_AT +" text);";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_REGISTRATION_TABLE);
        db.execSQL(CREATE_AD_POST_TABLE);
        db.execSQL(CREATE_TEMP_POST_TABLE);
        db.execSQL(CREATE_ALL_ADS_IMAGES_TABLE);
        db.execSQL(CREATE_FAVOURITE_ADS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE_USER_DETAILS);
        db.execSQL("drop table if exists "+ TABLE_AD_POST);
        db.execSQL("drop table if exists "+ TABLE_TEMP_POST);
        db.execSQL("drop table if exists "+ TABLE_ALL_ADS_IMAGE);
        db.execSQL("drop table if exists "+ TABLE_FAVOURITE_ADS);
        onCreate(db);
    }
}
