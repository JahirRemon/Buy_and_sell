package com.example.mdjahirulislam.final_project_bitm.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.app.AppConfig;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.databinding.ActivityLoginBinding;
import com.example.mdjahirulislam.final_project_bitm.interfaces.ConnectionApi;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdsImageModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.FavouritePostModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.RegistrationModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.LoginResponseModel;
import com.example.mdjahirulislam.final_project_bitm.sharePreference.UserSharePreference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private ActivityLoginBinding loginBinding;
    private ConnectionApi connectionApi;
    private LoginResponseModel loginResponseModel;
    private UserSharePreference userSharePreference;
    private DatabaseSource databaseSource;

    private ProgressDialog progressDialog;
//    private Ap

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        connectionApi = AppConfig.getRetrofit().create(ConnectionApi.class);
        userSharePreference = new UserSharePreference(this);
        databaseSource = new DatabaseSource(this);

        progressDialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        progressDialog.setCancelable(false);


        if (!userSharePreference.getUserId().equals("null")){
            Intent intent = new Intent(LoginActivity.this,ShowPostListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }



    }



    public void goToSignUpActivity(View view) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void goToShowPostListActivity(View view) {



        String email = loginBinding.loginEmailET.getText().toString().trim();
        String password = loginBinding.loginPasswordET.getText().toString().trim();

        if (email.isEmpty()){
            loginBinding.loginEmailET.setError("Required Field");
        }else if (password.isEmpty()){
            loginBinding.loginPasswordET.setError("Required Field");
        }else {

            progressDialog.setMessage("Login ...");
            showDialog();

            RegistrationModel registrationModel = new RegistrationModel(email,password);
            final Call<LoginResponseModel> loginResponseModelCall = connectionApi.logInUser(registrationModel);
            loginResponseModelCall.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                    if (response.code() == 200){
                        loginResponseModel = response.body();
                        boolean error = loginResponseModel.getError();
                        Log.d(TAG,"Error : "+error);
                        if (!error){


                            String userUniqueId = loginResponseModel.getUser().getUserUniqueId();
                            String fullName = loginResponseModel.getUser().getUserFullName();
                            String mobile = loginResponseModel.getUser().getUserMobileNo();
                            String email = loginResponseModel.getUser().getUserEmail();
                            String createdAt = loginResponseModel.getUser().getCreatedAt();

                            RegistrationModel newUserInfo = new RegistrationModel(userUniqueId,fullName,mobile,email,createdAt);

                            boolean status = databaseSource.addUser(newUserInfo);

                            if (status){
                                userSharePreference.setUserId(userUniqueId);
                                Intent intent = new Intent(LoginActivity.this,ShowPostListActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Log.d(TAG,"user info not store in SQLite ");
                                Toast.makeText(LoginActivity.this, "user info not store in SQLite ", Toast.LENGTH_SHORT).show();

                            }

                            boolean postExists = loginResponseModel.getPostExists();

                            if (postExists){
                                AdPostModel adPostModel;
                                List<LoginResponseModel.Post> getPostResponseArrayList = new ArrayList<>();

                                getPostResponseArrayList = loginResponseModel.getPost();


                                if (getPostResponseArrayList.size()>0){
                                    for (int i = 0; i<getPostResponseArrayList.size(); i++){
                                        String postUniqueId = getPostResponseArrayList.get(i).getPostUniqueId();
                                        String postedUserUniqueId = getPostResponseArrayList.get(i).getUserUniqueId();
                                        String userName = getPostResponseArrayList.get(i).getUserName();
                                        String userMobileNo = getPostResponseArrayList.get(i).getUserMobileNo();
                                        String postImageUrl = getPostResponseArrayList.get(i).getPostImageUrl();
                                        String postTitle = getPostResponseArrayList.get(i).getPostTitle();
                                        String postPrice = getPostResponseArrayList.get(i).getPostPrice();
                                        String postDetails = getPostResponseArrayList.get(i).getPostDetails();
                                        String postLocation = getPostResponseArrayList.get(i).getPostLocation();
                                        String postCondition = getPostResponseArrayList.get(i).getPostCondition();
                                        String postCategory = getPostResponseArrayList.get(i).getPostCategory();
                                        String postLatitude = getPostResponseArrayList.get(i).getPostLatitude();
                                        String postLongitude = getPostResponseArrayList.get(i).getPostLongitude();
                                        String postCreatedAt = getPostResponseArrayList.get(i).getCreatedAt();
                                        String postUpdatedAt = getPostResponseArrayList.get(i).getCreatedAt();

                                        adPostModel = new AdPostModel(postUniqueId,postedUserUniqueId,userName,userMobileNo,
                                                postImageUrl,postTitle,postPrice,postDetails,
                                                postLocation,postCondition,postCategory,postLatitude,postLongitude,postCreatedAt);
                                        boolean postStatus = databaseSource.addAdPost(adPostModel);
                                        if (postStatus){


                                            Log.d(TAG,"post insert SQLite and postStatus : --- "+postStatus);
//                                            Toast.makeText(LoginActivity.this,"post insert SQLite and postStatus : --- "+postStatus, Toast.LENGTH_SHORT).show();

                                        }

                                    }

                                }else {
                                    Log.d(TAG,"post not get from response : post size ---- "+getPostResponseArrayList.size());
                                    Toast.makeText(LoginActivity.this,"post not get from response : post size ---- "+getPostResponseArrayList.size(), Toast.LENGTH_SHORT).show();

                                }


                            }else {
                                Log.d(TAG,"No Ad Post Found and post message :  ----  "+loginResponseModel.getPostMsg());
                                Toast.makeText(LoginActivity.this, "No Ad Post Found and post message :  ----  "+loginResponseModel.getPostMsg(), Toast.LENGTH_SHORT).show();
                            }

                            boolean adsImageExists = loginResponseModel.getAdImageExists();
                            if (adsImageExists){

                                AdsImageModel allAdsPostImageModel;
                                List<LoginResponseModel.AdImage> allAdsImagesList = new ArrayList<>();


                                allAdsImagesList = loginResponseModel.getAdImage();

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
                                        }else {
                                            Log.d(TAG,"Successful store Ads image ----  "+storeImageStatus);
                                        }
                                    }

                                }
                            }else {
                                Log.d(TAG,"No Ad Post Image Found and post image response message :  ----  "+loginResponseModel.getAdImageMsg());
                                Toast.makeText(LoginActivity.this, "No Ad Post Image Found and post image response message :  ----  "+loginResponseModel.getAdImageMsg(), Toast.LENGTH_SHORT).show();

                            }

                            boolean favouriteAdsExists = loginResponseModel.getFavouriteAdsExists();
                            if (favouriteAdsExists){
                                FavouritePostModel favouritePostModel;
                                boolean addFavouriteAds;

                                List<LoginResponseModel.AdFavourite> allFavouriteAdsList = loginResponseModel.getAdFavourite();
                                if (allFavouriteAdsList.size()>0){
                                    for (int i = 0; i < allFavouriteAdsList.size(); i++) {
                                        String favouriteUniqueId = allFavouriteAdsList.get(i).getFavouriteUniqueId();
                                        String favUserUniqueId = allFavouriteAdsList.get(i).getUserUniqueId();
                                        String favPostUniqueId = allFavouriteAdsList.get(i).getPostUniqueId();
                                        String favouriteStatus = allFavouriteAdsList.get(i).getFavouriteStatus();
                                        String favCreatedAt = allFavouriteAdsList.get(i).getCreatedAt();

                                        favouritePostModel = new FavouritePostModel(favouriteUniqueId,favUserUniqueId,favPostUniqueId,favouriteStatus,favCreatedAt);

                                        addFavouriteAds = databaseSource.addFavouriteAds(favouritePostModel);
                                    }
                                }

                            }else {
                                Log.d(TAG,"No Favourite Post Found .response message :  ----  "+loginResponseModel.getFavouriteAdsMsg());
                                Toast.makeText(LoginActivity.this, "No Favourite Post Found . response message :  ----  "+loginResponseModel.getFavouriteAdsMsg(), Toast.LENGTH_SHORT).show();

                            }

                            hideDialog();
                        }else {
                            String error_msg = loginResponseModel.getErrorMsg();
                            Log.d(TAG,"Login response error message : "+error_msg);
                            Toast.makeText(LoginActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Log.d(TAG,"Server Error");
                        Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                    Log.d(TAG,"No Internet Connection ---- "+t.toString());
                    Toast.makeText(LoginActivity.this, "May be you have No Internet Connection ---- "+t.toString(), Toast.LENGTH_SHORT).show();

                    hideDialog();
                }
            });



        }
//        startActivity(new Intent(this,ShowPostListActivity.class));
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
