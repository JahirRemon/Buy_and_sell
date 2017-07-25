package com.example.mdjahirulislam.final_project_bitm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdsImageModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.FavouritePostModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.RegistrationModel;

import java.util.ArrayList;


/**
 * Created by Trainer on 3/29/2017.
 */

public class DatabaseSource {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private RegistrationModel userModel;
    private AdPostModel adPostModel;
//    private


    private static final String TAG = DatabaseSource.class.getSimpleName();


    public DatabaseSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void close() {
        sqLiteDatabase.close();
    }


    // -------------------------- Add SQLite --------------------------

    public boolean addUser(RegistrationModel users) {
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COL_USER_UNIQUE_ID, users.getUser_unique_id());
        values.put(DatabaseHelper.COL_USER_FULL_NAME, users.getUser_full_name());
        values.put(DatabaseHelper.COL_USER_MOBILE_NO, users.getUser_mobile_no());
        values.put(DatabaseHelper.COL_USER_EMAIL, users.getUser_email());
        values.put(DatabaseHelper.COL_USER_CREATED_AT, users.getCreated_at());


        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_USER_DETAILS, null, values);
        this.close();
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }



    public boolean addAdPost(AdPostModel postModel) {
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COL_AD_POST_UNIQUE_ID, postModel.getPost_unique_id());
        values.put(DatabaseHelper.COL_AD_POST_USER_UNIQUE_ID, postModel.getUser_unique_id());
        values.put(DatabaseHelper.COL_AD_POST_USER_FULL_NAME, postModel.getUser_name());
        values.put(DatabaseHelper.COL_AD_POST_USER_MOBILE_NO, postModel.getUser_mobile_no());
        values.put(DatabaseHelper.COL_AD_POST_IMAGE_URL, postModel.getPost_image_url());
        values.put(DatabaseHelper.COL_AD_POST_TITLE, postModel.getPost_title());
        values.put(DatabaseHelper.COL_AD_POST_PRICE, postModel.getPost_price());
        values.put(DatabaseHelper.COL_AD_POST_DETAILS, postModel.getPost_details());
        values.put(DatabaseHelper.COL_AD_POST_LOCATION, postModel.getPost_location());
        values.put(DatabaseHelper.COL_AD_POST_CONDITION, postModel.getPost_condition());
        values.put(DatabaseHelper.COL_AD_POST_CATEGORY, postModel.getPost_category());
        values.put(DatabaseHelper.COL_AD_POST_LATITUDE, postModel.getPost_latitude());
        values.put(DatabaseHelper.COL_AD_POST_LONGITUDE, postModel.getPost_longitude());
        values.put(DatabaseHelper.COL_AD_POST_CREATED_AT, postModel.getCreated_at());


        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_AD_POST, null, values);
        this.close();
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean addAdPostTemp(AdPostModel postModel) {
        this.open();
        ContentValues values = new ContentValues();

//        values.put(DatabaseHelper.COL_AD_POST_UNIQUE_ID, postModel.getPost_unique_id());
        values.put(DatabaseHelper.COL_TEMP_POST_USER_UNIQUE_ID, postModel.getUser_unique_id());
//        values.put(DatabaseHelper.COL_AD_POST_USER_FULL_NAME, postModel.getUser_name());
//        values.put(DatabaseHelper.COL_AD_POST_USER_MOBILE_NO, postModel.getUser_mobile_no());
        values.put(DatabaseHelper.COL_TEMP_POST_IMAGE_URL, postModel.getPost_image_url());
        values.put(DatabaseHelper.COL_TEMP_POST_TITLE, postModel.getPost_title());
        values.put(DatabaseHelper.COL_TEMP_POST_PRICE, postModel.getPost_price());
        values.put(DatabaseHelper.COL_TEMP_POST_DETAILS, postModel.getPost_details());
        values.put(DatabaseHelper.COL_TEMP_POST_LOCATION, postModel.getPost_location());
        values.put(DatabaseHelper.COL_TEMP_POST_CONDITION, postModel.getPost_condition());
        values.put(DatabaseHelper.COL_TEMP_POST_CATEGORY, postModel.getPost_category());
        values.put(DatabaseHelper.COL_TEMP_POST_LATITUDE, postModel.getPost_latitude());
        values.put(DatabaseHelper.COL_TEMP_POST_LONGITUDE, postModel.getPost_longitude());
//        values.put(DatabaseHelper.COL_AD_POST_CREATED_AT, postModel.getCreated_at());


        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_TEMP_POST, null, values);
        this.close();
        if (id > 0) {

            return true;
        } else {
            return false;
        }
    }


    public boolean addAdsImage(AdsImageModel allPostImage) {
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COL_ALL_ADS_IMAGE_UNIQUE_ID, allPostImage.getAd_image_unique_id());
        values.put(DatabaseHelper.COL_ALL_ADS_IMAGE_POST_UNIQUE_ID, allPostImage.getPost_unique_id());
        values.put(DatabaseHelper.COL_ALL_ADS_IMAGE_URL, allPostImage.getPost_image_url());
        values.put(DatabaseHelper.COL_ALL_ADS_IMAGE_CREATED_AT, allPostImage.getCreated_at());


        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_ALL_ADS_IMAGE, null, values);
        this.close();
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }



    public boolean addFavouriteAds(FavouritePostModel favouritePostModel) {
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COL_FAVOURITE_UNIQUE_ID, favouritePostModel.getFavourite_unique_id());
        values.put(DatabaseHelper.COL_FAVOURITE_USER_UNIQUE_ID, favouritePostModel.getUser_unique_id());
        values.put(DatabaseHelper.COL_FAVOURITE_POST_UNIQUE_ID, favouritePostModel.getPost_unique_id());
        values.put(DatabaseHelper.COL_FAVOURITE_STATUS, favouritePostModel.getFavourite_status());
        values.put(DatabaseHelper.COL_FAVOURITE_CREATED_AT, favouritePostModel.getCreated_at());


        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_FAVOURITE_ADS, null, values);
        this.close();
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }



    // -------------------------- get from SQLite --------------------------


    public ArrayList<RegistrationModel> getUserDetails(String userId) {
        ArrayList<RegistrationModel> registrationModelArrayList = new ArrayList<>();

        this.open();
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_USER_DETAILS, null, DatabaseHelper.COL_USER_UNIQUE_ID + " =? ", new String[]{userId}, null, null, null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {

            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_ID));
            String userUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_UNIQUE_ID));
            String fullName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_FULL_NAME));
            String mobile = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_MOBILE_NO));
            String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_EMAIL));
            String created = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_CREATED_AT));

            userModel = new RegistrationModel(userUniqueId, fullName, mobile, email, created);
            registrationModelArrayList.add(userModel);

            Log.d(TAG, "user name : " +registrationModelArrayList.get(0).getUser_full_name());
        }else {
            Log.d(TAG, "user details not found " );
        }
        cursor.close();
        this.close();

        return registrationModelArrayList;
    }


    public ArrayList<AdPostModel> getAllPost(String userId) {
        ArrayList<AdPostModel> adPostModelArrayList = new ArrayList<>();
        this.open();
        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_USER_DETAILS,null);*/

        String myOrder = DatabaseHelper.COL_AD_POST_ID+" DESC";

        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_AD_POST, null, null, null, null, null, myOrder, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int postId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_ID));
                String postUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_UNIQUE_ID));
                String userUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_UNIQUE_ID));
                String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_FULL_NAME));
                String userMobileNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_MOBILE_NO));
                String postImageUrl = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_IMAGE_URL));
                String postTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_TITLE));
                String postPrice = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_PRICE));
                String postDetails = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_DETAILS));
                String postLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LOCATION));
                String postCondition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CONDITION));
                String postCategory = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CATEGORY));
                String postLatitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LATITUDE));
                String postLongitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LONGITUDE));
                String postCreatedAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CREATED_AT));


//                travelEventModel = new MedicalHistoryModel(id,dr_id,prescription,name,details,date);
                Log.d(TAG, "getAllPost ----- Post unique id-------" + postUniqueId);
                Log.d(TAG, "getAllPost ----- Post Name-------" + postTitle);

                adPostModel = new AdPostModel(
                        postUniqueId, userUniqueId, userName, userMobileNo, postImageUrl,
                        postTitle, postPrice, postDetails, postLocation,
                        postCondition, postCategory, postLatitude, postLongitude, postCreatedAt);

                adPostModelArrayList.add(adPostModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return adPostModelArrayList;

    }


    public ArrayList<AdPostModel> getMyAllPost(String userId) {
        ArrayList<AdPostModel> adPostModelArrayList = new ArrayList<>();
        this.open();
        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_USER_DETAILS,null);*/

        String myOrder = DatabaseHelper.COL_AD_POST_ID+" DESC";

//        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_AD_POST, null, null, null, null, null, myOrder, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_AD_POST+" WHERE "+DatabaseHelper.COL_AD_POST_USER_UNIQUE_ID+" = "+userId,null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int postId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_ID));
                String postUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_UNIQUE_ID));
                String userUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_UNIQUE_ID));
                String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_FULL_NAME));
                String userMobileNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_MOBILE_NO));
                String postImageUrl = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_IMAGE_URL));
                String postTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_TITLE));
                String postPrice = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_PRICE));
                String postDetails = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_DETAILS));
                String postLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LOCATION));
                String postCondition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CONDITION));
                String postCategory = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CATEGORY));
                String postLatitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LATITUDE));
                String postLongitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LONGITUDE));
                String postCreatedAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CREATED_AT));


//                travelEventModel = new MedicalHistoryModel(id,dr_id,prescription,name,details,date);
                Log.d(TAG, "getMyAllPost ----- Post unique id-------" + postUniqueId);
                Log.d(TAG, "getMyAllPost ----- Post Name-------" + postTitle);

                adPostModel = new AdPostModel(
                        postUniqueId, userUniqueId, userName, userMobileNo, postImageUrl,
                        postTitle, postPrice, postDetails, postLocation,
                        postCondition, postCategory, postLatitude, postLongitude, postCreatedAt);

                adPostModelArrayList.add(adPostModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return adPostModelArrayList;

    }






    public ArrayList<AdPostModel> getAllFavouritePost(String userId) {
        ArrayList<AdPostModel> adPostModelArrayList = new ArrayList<>();
        this.open();

        Cursor favCursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_FAVOURITE_ADS+" WHERE "+DatabaseHelper.COL_FAVOURITE_USER_UNIQUE_ID+" = "+userId+" ORDER BY "+DatabaseHelper.COL_FAVOURITE_ID+" DESC ;", null);

        favCursor.moveToFirst();
        if (favCursor != null && favCursor.getCount() > 0) {


            for (int j = 0; j < favCursor.getCount(); j++) {
                String favouritePostUniqueId = favCursor.getString(favCursor.getColumnIndex(DatabaseHelper.COL_FAVOURITE_POST_UNIQUE_ID));
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_AD_POST+" WHERE "+DatabaseHelper.COL_AD_POST_UNIQUE_ID+" = "+favouritePostUniqueId,null);
                cursor.moveToFirst();
                if (cursor != null && cursor.getCount() > 0){
                    for (int i = 0; i < cursor.getCount(); i++) {
                        int postId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_ID));
                        String postUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_UNIQUE_ID));
                        String userUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_UNIQUE_ID));
                        String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_FULL_NAME));
                        String userMobileNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_MOBILE_NO));
                        String postImageUrl = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_IMAGE_URL));
                        String postTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_TITLE));
                        String postPrice = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_PRICE));
                        String postDetails = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_DETAILS));
                        String postLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LOCATION));
                        String postCondition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CONDITION));
                        String postCategory = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CATEGORY));
                        String postLatitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LATITUDE));
                        String postLongitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LONGITUDE));
                        String postCreatedAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CREATED_AT));


//                travelEventModel = new MedicalHistoryModel(id,dr_id,prescription,name,details,date);
                        Log.d(TAG, "get Fav Post ----- Post unique id-------" + postUniqueId);
                        Log.d(TAG, "get Fav Post ----- Post Name-------" + postTitle);

                        adPostModel = new AdPostModel(
                                postUniqueId, userUniqueId, userName, userMobileNo, postImageUrl,
                                postTitle, postPrice, postDetails, postLocation,
                                postCondition, postCategory, postLatitude, postLongitude, postCreatedAt);

                        adPostModelArrayList.add(adPostModel);
                        cursor.moveToNext();
                    }
                }else {
                    Log.d(TAG,"Fav post details not found postId ----> "+favouritePostUniqueId);
                }
                cursor.close();
                favCursor.moveToNext();
            }
        }else {
            Log.d(TAG,"Favourite post not found");
        }
        favCursor.close();
        this.close();
        return adPostModelArrayList;

    }








    public ArrayList<String> getSelectedPostImages(String adsPostUniqueId) {
        ArrayList<String> allAdsImageUrlList = new ArrayList<>();
        this.open();
        /*Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHelper.TABLE_USER_DETAILS,null);*/

        String myOrder = DatabaseHelper.COL_AD_POST_ID+" DESC";

//        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_AD_POST, null, null, null, null, null, myOrder, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_ALL_ADS_IMAGE+" WHERE "+DatabaseHelper.COL_ALL_ADS_IMAGE_POST_UNIQUE_ID+" LIKE "+adsPostUniqueId, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String adsImageUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ALL_ADS_IMAGE_UNIQUE_ID));
                String adsImagePostUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ALL_ADS_IMAGE_POST_UNIQUE_ID));
                String adsImageUrl = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ALL_ADS_IMAGE_URL));

                Log.d(TAG, "getSelected image unique id ------------>  " + adsImageUniqueId);
                Log.d(TAG, "getSelected image ads post unique id------------>  " + adsImagePostUniqueId);
                allAdsImageUrlList.add(adsImageUrl);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return allAdsImageUrlList;

    }


    public ArrayList<AdPostModel> getSelectedPostDetails(String adsPostUniqueId) {
        ArrayList<AdPostModel> adPostModelArrayList = new ArrayList<>();
        this.open();

//        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_AD_POST, null, null, null, null, null, myOrder, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_AD_POST+" WHERE "+DatabaseHelper.COL_AD_POST_UNIQUE_ID+" <= "+adsPostUniqueId+" ORDER BY "+DatabaseHelper.COL_AD_POST_UNIQUE_ID+" DESC;", null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int postId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_ID));
                String postUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_UNIQUE_ID));
                String userUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_UNIQUE_ID));
                String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_FULL_NAME));
                String userMobileNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_MOBILE_NO));
                String postImageUrl = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_IMAGE_URL));
                String postTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_TITLE));
                String postPrice = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_PRICE));
                String postDetails = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_DETAILS));
                String postLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LOCATION));
                String postCondition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CONDITION));
                String postCategory = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CATEGORY));
                String postLatitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LATITUDE));
                String postLongitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LONGITUDE));
                String postCreatedAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CREATED_AT));


//                travelEventModel = new MedicalHistoryModel(id,dr_id,prescription,name,details,date);
                Log.d(TAG, "getAllPost ----- Post unique id-------" + postUniqueId);
                Log.d(TAG, "getAllPost ----- Post Name-------" + postTitle);

                adPostModel = new AdPostModel(
                        postUniqueId, userUniqueId, userName, userMobileNo, postImageUrl,
                        postTitle, postPrice, postDetails, postLocation,
                        postCondition, postCategory, postLatitude, postLongitude, postCreatedAt);

                adPostModelArrayList.add(adPostModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return adPostModelArrayList;
    }






    public ArrayList<AdPostModel> getFilterAds(String location, String category, String sort, String search) {
        ArrayList<AdPostModel> adPostModelArrayList = new ArrayList<>();
        this.open();
        Log.d(TAG,"In Location btn (Category) : "+category);
        Log.d(TAG,"In Location btn (location) : "+location);
        Log.d(TAG,"In Location btn (sort) : "+sort);
        Log.d(TAG,"In Location btn (search) : "+search);


        String SQL = "SELECT * FROM " + DatabaseHelper.TABLE_AD_POST;
        String mySql = null;

        if (!location.equals("null")){
            if (!category.equals("null") && !sort.equals("null")){
                mySql = SQL +" WHERE " + DatabaseHelper.COL_AD_POST_LOCATION + " = '" + location + "' AND "
                        + DatabaseHelper.COL_AD_POST_CATEGORY+" = '"+category+"' ORDER BY " + DatabaseHelper.COL_AD_POST_PRICE + " ASC;";
            }else if (!category.equals("null")){
                mySql = SQL +" WHERE " + DatabaseHelper.COL_AD_POST_LOCATION + " = '" + location + "' AND "
                        + DatabaseHelper.COL_AD_POST_CATEGORY+" = '"+category+"' ORDER BY " + DatabaseHelper.COL_AD_POST_UNIQUE_ID + " DESC;";
            }else {
                mySql = SQL +" WHERE " + DatabaseHelper.COL_AD_POST_LOCATION + " = '" + location + "' ORDER BY " + DatabaseHelper.COL_AD_POST_UNIQUE_ID + " DESC;";
            }
        }else if (!category.equals("null")){
            if (!sort.equals("null") && sort.equals("Price")){
                mySql = SQL +" WHERE " + DatabaseHelper.COL_AD_POST_CATEGORY + " = '" + category + "' ORDER BY " + DatabaseHelper.COL_AD_POST_PRICE + " ASC;";

            }else {
                mySql = SQL +" WHERE " + DatabaseHelper.COL_AD_POST_CATEGORY + " = '" + category + "' ORDER BY " + DatabaseHelper.COL_AD_POST_UNIQUE_ID + " DESC;";

            }
        }else if (!search.equals("null") && !search.isEmpty()){
            mySql = SQL +" WHERE " + DatabaseHelper.COL_AD_POST_TITLE + " LIKE '%" + search + "%' ORDER BY " + DatabaseHelper.COL_AD_POST_UNIQUE_ID + " DESC;";
//            mySql = SQL +" WHERE '%" + search + "%' IN  ORDER BY " + DatabaseHelper.COL_AD_POST_UNIQUE_ID + " DESC;";
        }else if (!sort.equals("null") && sort.equals("Price")){
                mySql = SQL +" ORDER BY " + DatabaseHelper.COL_AD_POST_PRICE + " ASC;";
        }else {
                mySql = SQL +" ORDER BY " + DatabaseHelper.COL_AD_POST_UNIQUE_ID + " DESC;";
        }


        Log.d(TAG,mySql);
        Cursor cursor = sqLiteDatabase.rawQuery(mySql,null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int postId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_ID));
                String postUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_UNIQUE_ID));
                String userUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_UNIQUE_ID));
                String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_FULL_NAME));
                String userMobileNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_USER_MOBILE_NO));
                String postImageUrl = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_IMAGE_URL));
                String postTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_TITLE));
                String postPrice = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_PRICE));
                String postDetails = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_DETAILS));
                String postLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LOCATION));
                String postCondition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CONDITION));
                String postCategory = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CATEGORY));
                String postLatitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LATITUDE));
                String postLongitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_LONGITUDE));
                String postCreatedAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AD_POST_CREATED_AT));


//                travelEventModel = new MedicalHistoryModel(id,dr_id,prescription,name,details,date);
                Log.d(TAG, " Filter Ads ----- Post unique id-------" + postUniqueId);
//                Log.d(TAG, " Filter Ads ----- Post Name-------" + postTitle);
//                Log.d(TAG, " Filter Ads ----- Post Location-------" + postLocation);
                Log.d(TAG, " Filter Ads ----- Post Price-------" + postPrice);

                adPostModel = new AdPostModel(
                        postUniqueId, userUniqueId, userName, userMobileNo, postImageUrl,
                        postTitle, postPrice, postDetails, postLocation,
                        postCondition, postCategory, postLatitude, postLongitude, postCreatedAt);

                adPostModelArrayList.add(adPostModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return adPostModelArrayList;
    }





    public ArrayList<AdPostModel> getTempPost() {
        ArrayList<AdPostModel> adTempPostModelArrayList = new ArrayList<>();
        this.open();

//        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_AD_POST, null, null, null, null, null, myOrder, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_TEMP_POST+" ORDER BY "+DatabaseHelper.COL_TEMP_POST_ID+" DESC;", null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int postId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_ID));
//                String postUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_UNIQUE_ID));
                String userUniqueId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_USER_UNIQUE_ID));
//                String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_USER_FULL_NAME));
//                String userMobileNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_USER_MOBILE_NO));
                String postImageUrl = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_IMAGE_URL));
                String postTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_TITLE));
                String postPrice = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_PRICE));
                String postDetails = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_DETAILS));
                String postLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_LOCATION));
                String postCondition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_CONDITION));
                String postCategory = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_CATEGORY));
                String postLatitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_LATITUDE));
                String postLongitude = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_LONGITUDE));
//                String postCreatedAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TEMP_POST_CREATED_AT));


//                travelEventModel = new MedicalHistoryModel(id,dr_id,prescription,name,details,date);
                Log.d(TAG, "getAllPost ----- Post id-------" + postId);
                Log.d(TAG, "getAllPost ----- Post Name-------" + postTitle);

                adPostModel = new AdPostModel(postId, userUniqueId, postImageUrl,
                        postTitle, postPrice, postDetails, postLocation,
                        postCondition, postCategory, postLatitude, postLongitude);

                adTempPostModelArrayList.add(adPostModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return adTempPostModelArrayList;
    }






    public boolean getSelectedFavouritePost(String adsPostUniqueId) {
        boolean adsExistsOnFavouriteTable;
        this.open();

//        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_AD_POST, null, null, null, null, null, myOrder, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_FAVOURITE_ADS+" WHERE "+DatabaseHelper.COL_FAVOURITE_POST_UNIQUE_ID+" = "+adsPostUniqueId+";", null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            adsExistsOnFavouriteTable = true;
        }else {
            adsExistsOnFavouriteTable = false;
        }
        cursor.close();
        this.close();
        Log.d(TAG,"Check favouritr post ----------> "+adsExistsOnFavouriteTable);
        return adsExistsOnFavouriteTable;
    }





    public boolean deleteFavouriteAdPostTableRow(String postUniqueId){
        this.open();
        int deleteId = sqLiteDatabase.delete(databaseHelper.TABLE_FAVOURITE_ADS,DatabaseHelper.COL_FAVOURITE_POST_UNIQUE_ID+" = ?",new String[] {postUniqueId});

        if (deleteId>0){
            return true;
        }else {
            return false;
        }

    }







    public boolean deleteTempAdPostTableRow(int rowId){
        this.open();
        int deleteId = sqLiteDatabase.delete(databaseHelper.TABLE_TEMP_POST,DatabaseHelper.COL_TEMP_POST_ID+" = ?",new String[] {Integer.toString(rowId)});

        if (deleteId>0){
            return true;
        }else {
            return false;
        }

    }





    public void whenLogoutUser() {
        this.open();
        sqLiteDatabase.delete(DatabaseHelper.TABLE_USER_DETAILS, null, null);
        sqLiteDatabase.delete(DatabaseHelper.TABLE_AD_POST, null, null);
        sqLiteDatabase.delete(DatabaseHelper.TABLE_ALL_ADS_IMAGE, null, null);
        sqLiteDatabase.delete(DatabaseHelper.TABLE_TEMP_POST, null, null);
        sqLiteDatabase.delete(DatabaseHelper.TABLE_FAVOURITE_ADS, null, null);

        this.close();
        Log.d(TAG, "Deleted all Table info from SQLite");
    }


}
