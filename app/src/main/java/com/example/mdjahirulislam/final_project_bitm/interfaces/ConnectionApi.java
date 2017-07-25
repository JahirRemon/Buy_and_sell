package com.example.mdjahirulislam.final_project_bitm.interfaces;

import com.example.mdjahirulislam.final_project_bitm.modelClass.RegistrationModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.AddPostResponseModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.FavouriteResponseModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.LoginResponseModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.RegistrationResponseModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.UpdateAdsPostResponseModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by mdjahirulislam on 27/04/17.
 */

public interface ConnectionApi {
    @POST("SellApp/user_registration.php")
    Call<RegistrationResponseModel> getRegistrationUser(@Body RegistrationModel registrationModel);

    @POST("SellApp/user_login.php")
    Call<LoginResponseModel> logInUser(@Body RegistrationModel registrationModel);


    @Multipart
    @POST("SellApp/ad_post.php")
    Call<AddPostResponseModel> uploadPhotoWithText(
            @Part("user_unique_id") RequestBody user_unique_id,
            @Part("post_title") RequestBody post_title,
            @Part("post_price") RequestBody post_price,
            @Part("post_details") RequestBody post_details,
            @Part("post_location") RequestBody post_location,
            @Part("post_condition") RequestBody post_condition,
            @Part("post_category") RequestBody post_category,
            @Part("post_latitude") RequestBody post_latitude,
            @Part("post_longitude") RequestBody post_longitude,
            @Part List<MultipartBody.Part> image


    );


    @Multipart
    @POST("SellApp/update_ad_post.php")
    Call<UpdateAdsPostResponseModel> updateAds(@Part("last_post_unique_id") RequestBody last_post_unique_id);


    @Multipart
    @POST("SellApp/favourite.php")
    Call<FavouriteResponseModel> favouriteAds(
            @Part("user_unique_id") RequestBody user_unique_id,
            @Part("post_unique_id") RequestBody post_unique_id,
            @Part("favourite_status") RequestBody favourite_status
            );


//
//    @POST("TourApp/create_travel_event.php")
//    Call<AddTravelEventResponseModel> addTravelEvent(@Body TravelEventModel travelEventModel);
//

//    @Multipart
//    @POST("TourApp/add_moment.php")
//    Call<MomentResponseModel> addMomentWithTravelUniqueID(@Part("travel_event_unique_id") RequestBody travel_event_unique_id);
//
//
//
//
//    @Multipart
//    @POST("TourApp/add_expense_multipart.php")
//    Call<ExpenseResponseModel> uploadPhotoWithText(
//            @Part("travel_event_unique_id") RequestBody travel_event_unique_id,
//            @Part("expense_details") RequestBody expense_details,
//            @Part("expense_amount") RequestBody expense_amount,
//            @Part("expense_date") RequestBody expense_date,
//            @Part MultipartBody.Part image
//
//    );
//
//
//    @Multipart
//    @POST("TourApp/add_moment.php")
//    Call<MomentResponseModel> addMomentWithPhoto(
//            @Part("travel_event_unique_id") RequestBody travel_event_unique_id,
//            @Part("moment_details") RequestBody moment_details,
//            @Part MultipartBody.Part moment_image
//
//    );
//
//
////
//    @GET()
//    Call<WeatherModelResponse> getWeatherResponse(@Url String urlString);

}
