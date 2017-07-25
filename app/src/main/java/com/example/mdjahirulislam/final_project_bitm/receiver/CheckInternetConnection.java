package com.example.mdjahirulislam.final_project_bitm.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.activity.AddPostActivity;
import com.example.mdjahirulislam.final_project_bitm.activity.ShowPostDetailsActivity;
import com.example.mdjahirulislam.final_project_bitm.activity.ShowPostListActivity;
import com.example.mdjahirulislam.final_project_bitm.app.AppConfig;
import com.example.mdjahirulislam.final_project_bitm.app.NetworkUtil;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.interfaces.ConnectionApi;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdsImageModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.AddPostResponseModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInternetConnection extends BroadcastReceiver {

    private static final String TAG = CheckInternetConnection.class.getSimpleName();

    private ConnectionApi connectionApi;
    private DatabaseSource databaseSource;
    private ArrayList<AdPostModel> tempPostArrayList = new ArrayList<>();
    private ArrayList<String> filePaths = new ArrayList<String>();
    private AddPostResponseModel addPostResponseModel;

    private Context context;

    private int postId;
    private int i = 0;


    private static boolean firstConnect = true;


    private static final int MY_NOTIFICATION_ID=1;
    NotificationManager notificationManager;
    Notification myNotification;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        String status = NetworkUtil.getConnectivityStatusString(context);

        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        databaseSource = new DatabaseSource(context);
        connectionApi = AppConfig.getRetrofit().create(ConnectionApi.class);
        Integer netStatus = NetworkUtil.getConnectivityStatus(context);
        Log.d(TAG,"Temp net status :  ------------> "+netStatus +" counter = ");
        if (netStatus == 1 || netStatus == 2){
            tempPostArrayList = databaseSource.getTempPost();
            if (tempPostArrayList.size()>0) {
                postId = tempPostArrayList.get(0).getPost_id();
                Log.d(TAG, "Temp post id ----------> " + postId);


                uploadFile(tempPostArrayList);

//                firstConnect = false;




            }else{
                Log.d(TAG, "No Temp post and size ----------> " + tempPostArrayList.size());
            }
            if (firstConnect)
                firstConnect = false;
        }else {
            Log.d(TAG, "No internet connection -----");

            firstConnect = true;
        }

    }



    private void uploadFile(ArrayList<AdPostModel> tempPostArrayList) {

        for (int i = 0; i < tempPostArrayList.size(); i++) {

            filePaths.clear();

            RequestBody user_unique_id = RequestBody.create(MultipartBody.FORM,tempPostArrayList.get(i).getUser_unique_id());
            RequestBody post_title = RequestBody.create(MultipartBody.FORM,tempPostArrayList.get(i).getPost_title());
            RequestBody post_price = RequestBody.create(MultipartBody.FORM,tempPostArrayList.get(i).getPost_price());
            RequestBody post_details = RequestBody.create(MultipartBody.FORM,tempPostArrayList.get(i).getPost_details());
            RequestBody post_location = RequestBody.create(MultipartBody.FORM,tempPostArrayList.get(i).getPost_location());
            RequestBody post_condition = RequestBody.create(MultipartBody.FORM,tempPostArrayList.get(i).getPost_condition());
            RequestBody post_category = RequestBody.create(MultipartBody.FORM,tempPostArrayList.get(i).getPost_category());
            RequestBody post_latitude= RequestBody.create(MultipartBody.FORM,tempPostArrayList.get(i).getPost_latitude());
            RequestBody post_longitude = RequestBody.create(MultipartBody.FORM,tempPostArrayList.get(i).getPost_longitude());
            String imageUrl = tempPostArrayList.get(i).getPost_image_url();
            filePaths.add(imageUrl);


            File file;
            List<MultipartBody.Part> parts = new ArrayList<>();
            parts.clear();
            for (int j =0; j< filePaths.size(); j++ ){
                file = new File(filePaths.get(j));
                Log.d("TEmp "+TAG,file.toString()+"    SIZE : "+filePaths.size());
                RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image[]",file.getName(),requestBody);
                parts.add(i,fileToUpload);

            }


            Call<AddPostResponseModel> call = connectionApi.uploadPhotoWithText(user_unique_id,post_title,
                    post_price,post_details,post_location,post_condition,
                    post_category,post_latitude,post_longitude,parts);


            call.enqueue(new Callback<AddPostResponseModel>() {
                @Override
                public void onResponse(Call<AddPostResponseModel> call, Response<AddPostResponseModel> response) {
                    if (response.code()==200){
                        addPostResponseModel = response.body();
                        boolean error = addPostResponseModel.getError();
                        if (!error){
                            AdPostModel adPostModel;
                            Log.d(TAG,"Successful "+addPostResponseModel.getErrorMsg());

                            String postUniqueId = addPostResponseModel.getPostUniqueId();
                            String postedUserUniqueId = addPostResponseModel.getUserUniqueId();
                            String userName = addPostResponseModel.getUserName();
                            String userMobileNo = addPostResponseModel.getUserMobileNo();
                            String postImageUrl = addPostResponseModel.getPostImageUrl();
                            String postTitle = addPostResponseModel.getPostTitle();
                            String postPrice = addPostResponseModel.getPostPrice();
                            String postDetails = addPostResponseModel.getPostDetails();
                            String postLocation = addPostResponseModel.getPostLocation();
                            String postCondition = addPostResponseModel.getPostCondition();
                            String postCategory = addPostResponseModel.getPostCategory();
                            String postLatitude = addPostResponseModel.getPostLatitude();
                            String postLongitude = addPostResponseModel.getPostLongitude();
                            String postCreatedAt = addPostResponseModel.getCreatedAt();
                            String postUpdatedAt = addPostResponseModel.getCreatedAt();

                            adPostModel = new AdPostModel(postUniqueId,postedUserUniqueId,userName,userMobileNo,
                                    postImageUrl,postTitle,postPrice,postDetails,postLocation,postCondition,
                                    postCategory,postLatitude,postLongitude,postCreatedAt);

                            boolean postStatus = databaseSource.addAdPost(adPostModel);


                            boolean adsImageExists = addPostResponseModel.getAdImageExists();
                            if (adsImageExists){

                                AdsImageModel allAdsPostImageModel;
                                List<AddPostResponseModel.AdImage> allAdsImagesList = new ArrayList<>();


                                allAdsImagesList = addPostResponseModel.getAdImage();

                                if (allAdsImagesList.size()>0){
                                    for (int i = 0; i < allAdsImagesList.size(); i++) {
                                        String allAdsImageUniqueId = allAdsImagesList.get(i).getAdImageUniqueId();
                                        String allAdsImagePostUniqueId = allAdsImagesList.get(i).getPostUniqueId();
                                        String allAdsImageUrl = allAdsImagesList.get(i).getPostImageUrl();
                                        String allAdsImageCreatedAt = allAdsImagesList.get(i).getCreatedAt();

                                        allAdsPostImageModel = new AdsImageModel(allAdsImageUniqueId,allAdsImagePostUniqueId,allAdsImageUrl,allAdsImageCreatedAt);
                                        boolean storeImageStatus = databaseSource.addAdsImage(allAdsPostImageModel);
                                        if (storeImageStatus){
                                            Log.d(TAG,"Successful store Ads image  ----  "+storeImageStatus);
                                            Log.d(TAG,"test adsImage Model  ----  \nallAdsImageUniqueId "+allAdsImageUniqueId+
                                                    "\nallAdsImagePostUniqueId "+allAdsImagePostUniqueId+"\nallAdsImageUrl "+allAdsImageUrl);
                                        }else {
                                            Log.d(TAG,"Successful store Ads image ----  "+storeImageStatus);
                                        }
                                    }

                                }



                            }else {
                                Log.d(TAG,"No Ad Post Image Found and post image response message :  ----  "+addPostResponseModel.getAdImageMsg());
                                Toast.makeText(context, "No Ad Post Image Found and post image response message :  ----  "+addPostResponseModel.getAdImageMsg(), Toast.LENGTH_SHORT).show();

                            }


                            if (postStatus){

                                Intent myIntent = new Intent(context, ShowPostDetailsActivity.class);
                                myIntent.putExtra("postUniqueId",postUniqueId);
                                Log.d(TAG,"my intent postUniqueId "+postUniqueId);
                                PendingIntent pendingIntent = PendingIntent.getActivity(
                                        context,
                                        0,
                                        myIntent,
                                        Intent.FLAG_ACTIVITY_NEW_TASK);

                                myNotification = new NotificationCompat.Builder(context)
                                        .setContentTitle("Buy&Sell")
                                        .setContentText("your post is uploaded Successful")
                                        .setTicker("Notification!")
                                        .setWhen(System.currentTimeMillis())
                                        .setContentIntent(pendingIntent)
                                        .setDefaults(Notification.DEFAULT_SOUND)
                                        .setAutoCancel(true)
                                        .setSmallIcon(R.drawable.no_image)
                                        .build();

                                notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                                notificationManager.notify(MY_NOTIFICATION_ID, myNotification);


                                Log.d(TAG,"TEMP post insert SQLite and postStatus : --- "+postStatus);
                                Toast.makeText(context,"TEMP Ads Post Successful :) "+postStatus, Toast.LENGTH_SHORT).show();

                                boolean deleteRow = databaseSource.deleteTempAdPostTableRow(postId);

                                if (deleteRow){
                                    Log.d(TAG,"TEMP post id delete from SQLite and DeleteStatus : --- "+deleteRow);
                                }else {
                                    Log.d(TAG,"TEMP post id is NOT delete from SQLite and DeleteStatus : --- "+deleteRow);
                                }

//                                Intent intent = new Intent(context,ShowPostListActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(intent);
//                                finish();

                            }

                        }else {
                            Log.d(TAG,"Not Successful : "+addPostResponseModel.getErrorMsg());
                        }


                    }else {
                        Log.d(TAG,"Successful Error code : "+response.code());
                    }
                }

                @Override
                public void onFailure(Call<AddPostResponseModel> call, Throwable t) {

                    Log.d(TAG,"TEMP post uplosd Failed "+t.toString());

                }
            });



        }



    }

}
