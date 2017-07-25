package com.example.mdjahirulislam.final_project_bitm.adapter;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.app.AppConfig;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.interfaces.ConnectionApi;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.FavouritePostModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.FavouriteResponseModel;
import com.google.android.gms.maps.model.LatLng;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mdjahirulislam on 08/06/17.
 */

public class SwipeAdsDetailsAdapter extends PagerAdapter{

    private static final String TAG = SwipeAdsDetailsAdapter.class.getSimpleName();

//    private ImageView favouriteIV;

    private ConnectionApi connectionApi;
    private DatabaseSource databaseSource;

    private ArrayList<AdPostModel> adsDetailsArrayList;
    private ArrayList<String> imageList;
    private LayoutInflater inflater;
    private Context context;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private FavouriteResponseModel favouriteResponseModel;


//    public SwipeAdsDetailsAdapter(Context context,ArrayList<AdPostModel> adsDetailsArrayList,ArrayList<String> imageList) {
//        this.context = context;
//        this.adsDetailsArrayList =adsDetailsArrayList;
//        this.imageList = imageList;
//        inflater = LayoutInflater.from(context);
//        Log.d(TAG,"image url :  "+adsDetailsArrayList.get(0).getPost_title());
//    }

    public SwipeAdsDetailsAdapter(Context context, ArrayList<AdPostModel> adsDetailsArrayList) {
        this.context = context;
        this.adsDetailsArrayList =adsDetailsArrayList;
        inflater = LayoutInflater.from(context);
        Log.d(TAG,"post title  :  "+adsDetailsArrayList.get(0).getPost_title());
        databaseSource = new DatabaseSource(context);
        connectionApi = AppConfig.getRetrofit().create(ConnectionApi.class);

    }

    @Override
    public int getCount() {
        return adsDetailsArrayList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View swipeAdsDetailsLayout = inflater.inflate(R.layout.single_post_details_design, view, false);

        assert swipeAdsDetailsLayout != null;

        final CirclePageIndicator indicator = (CirclePageIndicator) swipeAdsDetailsLayout.findViewById(R.id.swipeIndicator);
        final ViewPager mPager = (ViewPager) swipeAdsDetailsLayout.findViewById(R.id.swipePager);
        TextView adsTitleTV = (TextView) swipeAdsDetailsLayout.findViewById(R.id.showSwipeAdsTitleTV);
        TextView adsPriceTV = (TextView) swipeAdsDetailsLayout.findViewById(R.id.showSwipeAdsPriceTV);
        TextView adsPostedUserNameTV = (TextView) swipeAdsDetailsLayout.findViewById(R.id.showSwipeAdsUserNameTV);
        TextView adsDetailsTV = (TextView) swipeAdsDetailsLayout.findViewById(R.id.showSwipeAdsDetailsTV);
        TextView adsLocationTV = (TextView) swipeAdsDetailsLayout.findViewById(R.id.showSwipeAdsLocationTV);
        TextView adsConditionTV = (TextView) swipeAdsDetailsLayout.findViewById(R.id.showSwipeAdsConditionTV);
        TextView adsCategoryTV = (TextView) swipeAdsDetailsLayout.findViewById(R.id.showSwipeAdsCategoryTV);
        TextView adsPostedTimeTV = (TextView) swipeAdsDetailsLayout.findViewById(R.id.showSwipeAdsPostTimeTV);
        Button callBTN = (Button) swipeAdsDetailsLayout.findViewById(R.id.callSwipeBTN);
        Button smsBTN = (Button) swipeAdsDetailsLayout.findViewById(R.id.smsSwipeBTN);
        Button locationMapBTN = (Button) swipeAdsDetailsLayout.findViewById(R.id.showSwipeAdsLocationMapBTN);

        ImageView shareIV = (ImageView) swipeAdsDetailsLayout.findViewById(R.id.shareIV);
        final ImageView favouriteIV = (ImageView) swipeAdsDetailsLayout.findViewById(R.id.favouriteIV);

        String postId = adsDetailsArrayList.get(position).getPost_unique_id();
        Log.d(TAG,"post id ---->  "+postId);
        Log.d(TAG,"position ---->  "+position);

        imageList = databaseSource.getSelectedPostImages(postId);

//        imageView.setImageResource(IMAGES.get(position));

//        String imageUrl = adsDetailsArrayList.get(position);

        Log.d(TAG+"  post title from swipe ads details adapter  ",adsDetailsArrayList.get(position).getPost_title());
//        Context context  = imageView.getContext();
//        Uri imageUri = Uri.parse(imageUrl);
//        Picasso.with(context).load(imageUri)
//                .resize(400, 300)
//                .into(imageView);

        adsTitleTV.setText(adsDetailsArrayList.get(position).getPost_title().toString());
        adsPriceTV.setText("Tk "+adsDetailsArrayList.get(position).getPost_price().toString());
        adsPostedUserNameTV.setText("For sale by "+adsDetailsArrayList.get(position).getUser_name().toString());
        adsDetailsTV.setText(adsDetailsArrayList.get(position).getPost_details().toString());
        adsLocationTV.setText(adsDetailsArrayList.get(position).getPost_location().toString());
        adsConditionTV.setText(adsDetailsArrayList.get(position).getPost_condition().toString());
        adsCategoryTV.setText(adsDetailsArrayList.get(position).getPost_category().toString());
        adsPostedTimeTV.setText(adsDetailsArrayList.get(position).getCreated_at().toString());




        final String userName = adsPostedUserNameTV.getText().toString().trim();
        final String adsUserMobile =  adsDetailsArrayList.get(position).getUser_mobile_no();
        final double latitude = Double.parseDouble(adsDetailsArrayList.get(position).getPost_latitude());
        final double longitude = Double.parseDouble(adsDetailsArrayList.get(position).getPost_longitude());
        final String location = adsDetailsArrayList.get(position).getPost_location();



        final String userUniqueId = adsDetailsArrayList.get(position).getUser_unique_id().toString();
        final String postUniqueId = adsDetailsArrayList.get(position).getPost_unique_id().toString();
        Log.d(TAG,"post id ---->  "+postUniqueId);

        final boolean[] checkFavouriteStatus = {databaseSource.getSelectedFavouritePost(postUniqueId)};

        Log.d(TAG,"checkFavouriteStatus --------> "+ checkFavouriteStatus[0]);
        if (checkFavouriteStatus[0]){
            favouriteIV.setImageResource(R.drawable.ic_favorite_black_24dp);
        }else {
            favouriteIV.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }


        favouriteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"BTN Click post unique id ---->  "+postUniqueId);
                if (!checkFavouriteStatus[0]){
                    Log.d(TAG,"BTN Click post status id (may be false)---->  "+checkFavouriteStatus[0]);

//                    setFavourite(userUniqueId,postUniqueId,checkFavouriteStatus,favouriteIV);





                    RequestBody user_unique_id = RequestBody.create(MultipartBody.FORM,userUniqueId);
                    RequestBody post_unique_id = RequestBody.create(MultipartBody.FORM,postUniqueId);
                    RequestBody favourite_status = RequestBody.create(MultipartBody.FORM,String.valueOf(checkFavouriteStatus[0]));

                    Call<FavouriteResponseModel> call = connectionApi.favouriteAds(user_unique_id,post_unique_id,favourite_status);

                    call.enqueue(new Callback<FavouriteResponseModel>() {
                        @Override
                        public void onResponse(Call<FavouriteResponseModel> call, Response<FavouriteResponseModel> response) {
                            if (response.code()==200){

                                favouriteResponseModel = response.body();
                                boolean error = favouriteResponseModel.getError();
                                if (!error) {
//                        AdPostModel adPostModel;
                                    Log.d(TAG, "Successful " + favouriteResponseModel.getErrorMsg());

                                    boolean favouriteAdsExists = favouriteResponseModel.getFavouriteAdsExists();
                                    if (favouriteAdsExists){

                                        FavouritePostModel favouritePostModel;
                                        boolean addFavouriteAds;
                                        List<FavouriteResponseModel.Favourite> getFavouritePostList = favouriteResponseModel.getFavourite();

                                        if (getFavouritePostList.size()>0)
                                        {
//                                for (int i = 0; i < getFavouritePostList.size(); i++) {

                                            String favouriteUniqueId = getFavouritePostList.get(0).getFavouriteUniqueId();
                                            String userUniqueId = getFavouritePostList.get(0).getUserUniqueId();
                                            String postId = getFavouritePostList.get(0).getPostUniqueId();
                                            String favouriteStatus = getFavouritePostList.get(0).getFavouriteStatus();
                                            String createdAt = getFavouritePostList.get(0).getCreatedAt();

                                            favouritePostModel = new FavouritePostModel(favouriteUniqueId,userUniqueId,postId,favouriteStatus,createdAt);

                                            addFavouriteAds = databaseSource.addFavouriteAds(favouritePostModel);
                                            if (addFavouriteAds){

                                                Log.d(TAG,"Favourite post added ------> "+addFavouriteAds);


                                                if (postId.equals(postUniqueId)){
                                                    Log.d(TAG,"Favourite post status ------> "+favouriteStatus);
                                                    favouriteIV.setImageResource(R.drawable.ic_favorite_black_24dp);
                                                    checkFavouriteStatus[0] = true;
                                                }else {
                                                    Log.d(TAG,"Favourite post status ------> "+favouriteStatus);
                                                    favouriteIV.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                                                }


                                            }else {
                                                Log.d(TAG,"Favourite post added ------> "+addFavouriteAds);
                                            }
//                                }

                                        }else {
                                            Log.d(TAG,"Favourite post getFavouritePostList.size() ------> "+getFavouritePostList.size());
                                            favouriteIV.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                                        }


                                    }else {
                                        Log.d(TAG,"No favourite post exists"+favouriteResponseModel.getFavouriteAdsExists());
                                    }
                                }else {
                                    Log.d(TAG,"Error ---->  true and \nmsg ----->"+favouriteResponseModel.getErrorMsg());
                                }

                            }else {
                                Log.d(TAG,"Server Error code : "+response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<FavouriteResponseModel> call, Throwable t) {

                            Log.d(TAG,"May be you have no internet connection and error is ------>  "+t.toString());
                        }
                    });











                }else {
//                    setFavourite(userUniqueId,postUniqueId, checkFavouriteStatus[0],favouriteIV);

                    Log.d(TAG,"BTN  Click post status (may be true) ---->  "+checkFavouriteStatus[0]);


                    RequestBody user_unique_id = RequestBody.create(MultipartBody.FORM,userUniqueId);
                    RequestBody post_unique_id = RequestBody.create(MultipartBody.FORM,postUniqueId);
                    RequestBody favourite_status = RequestBody.create(MultipartBody.FORM,String.valueOf(checkFavouriteStatus));

                    Call<FavouriteResponseModel> call = connectionApi.favouriteAds(user_unique_id,post_unique_id,favourite_status);

                    call.enqueue(new Callback<FavouriteResponseModel>() {
                        @Override
                        public void onResponse(Call<FavouriteResponseModel> call, Response<FavouriteResponseModel> response) {
                            if (response.code()==200){

                                favouriteResponseModel = response.body();
                                boolean error = favouriteResponseModel.getError();
                                if (!error) {
                                    Log.d(TAG,"successful msg ---->  "+favouriteResponseModel.getErrorMsg());
                                    Toast.makeText(context, ""+favouriteResponseModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                    favouriteIV.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                                    checkFavouriteStatus[0] = false;

                                    databaseSource.deleteFavouriteAdPostTableRow(postUniqueId);

                                }else {
                                    Log.d(TAG,"Error ---->  true and \nmsg ----->"+favouriteResponseModel.getErrorMsg());
                                }

                            }else {
                                Log.d(TAG,"Server Error code : "+response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<FavouriteResponseModel> call, Throwable t) {

                            Log.d(TAG,"May be you have no internet connection and error is ------>  "+t.toString());
                        }
                    });






                }

            }
        });

        if (!adsUserMobile.isEmpty()){

            Log.d(TAG,"user mobile no "+adsUserMobile+"\n------------------------------------------------------------------");
            callBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+adsUserMobile));


                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    context.startActivity(callIntent);

                }
            });

            smsBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                    sendIntent.setData(Uri.parse("sms:"+adsUserMobile));
                    sendIntent.putExtra("sms_body", "DUMMY TEXT");

                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        context.startActivity(sendIntent);
                    }catch (ActivityNotFoundException e){
                        Log.d(TAG,"SMS not sent by this mobile number");
                    }

                }
            });


        }else {
            Log.d(TAG,"User mobile number not available");
        }


            locationMapBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d(TAG,"Lat & long --------> "+latitude+"  ,  "+longitude+"  \nLocation ---------> "+location);

                    if (latitude!=0 && longitude!=0){

                        String label = "User : "+userName+" \nLocation : near by "+location;
                        String uriBegin = "geo:" + latitude + "," + longitude;
                        String query = latitude + "," + longitude + "(" + label + ")";
                        String encodedQuery = Uri.encode(query);
                        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                        Uri uri = Uri.parse(uriString);
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);

                    }else {

                        LatLng geoPoint = getLocationFromAddress(context,location);

                        Log.d(TAG,"lat ------ "+geoPoint.latitude+"\nlong --------- "+geoPoint.longitude);
                        String label = "User : "+userName+" \nLocation : near by "+location;
                        String uriBegin = "geo:" + geoPoint.latitude + "," + geoPoint.longitude;
                        String query = geoPoint.latitude + "," + geoPoint.longitude + "(" + label + ")";
                        String encodedQuery = Uri.encode(query);
                        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                        Uri uri = Uri.parse(uriString);
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);

                    }
                }
            });






        if (imageList.size()>0) {
            mPager.setAdapter(new SlidingImageAdapter(context, imageList));





            indicator.setViewPager(mPager);

            final float density = context.getResources().getDisplayMetrics().density;

            indicator.setRadius(5 * density);



            NUM_PAGES =imageList.size();



            // Auto start of viewpager
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    mPager.setCurrentItem(currentPage++, true);
                }
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 5000, 5000);

            // Pager listener over indicator
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;

                }

                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int pos) {

                }
            });









        }else {
            Log.d(TAG,"image list size "+imageList.size());
        }
        view.addView(swipeAdsDetailsLayout, 0);

        return swipeAdsDetailsLayout;
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    public void setFavourite(String userUniqueId, final String postId, final boolean checkStatus, final ImageView favouriteIV){



        RequestBody user_unique_id = RequestBody.create(MultipartBody.FORM,userUniqueId);
        RequestBody post_unique_id = RequestBody.create(MultipartBody.FORM,postId);
        RequestBody favourite_status = RequestBody.create(MultipartBody.FORM,String.valueOf(checkStatus));

        Call<FavouriteResponseModel> call = connectionApi.favouriteAds(user_unique_id,post_unique_id,favourite_status);

        call.enqueue(new Callback<FavouriteResponseModel>() {
            @Override
            public void onResponse(Call<FavouriteResponseModel> call, Response<FavouriteResponseModel> response) {
                if (response.code()==200){

                    favouriteResponseModel = response.body();
                    boolean error = favouriteResponseModel.getError();
                    if (!error) {
//                        AdPostModel adPostModel;
                        Log.d(TAG, "Successful " + favouriteResponseModel.getErrorMsg());

                        boolean favouriteAdsExists = favouriteResponseModel.getFavouriteAdsExists();
                        if (favouriteAdsExists){

                            FavouritePostModel favouritePostModel;
                            boolean addFavouriteAds;
                            List<FavouriteResponseModel.Favourite> getFavouritePostList = favouriteResponseModel.getFavourite();

                            if (getFavouritePostList.size()>0)
                            {
//                                for (int i = 0; i < getFavouritePostList.size(); i++) {

                                    String favouriteUniqueId = getFavouritePostList.get(0).getFavouriteUniqueId();
                                    String userUniqueId = getFavouritePostList.get(0).getUserUniqueId();
                                    String postUniqueId = getFavouritePostList.get(0).getPostUniqueId();
                                    String favouriteStatus = getFavouritePostList.get(0).getFavouriteStatus();
                                    String createdAt = getFavouritePostList.get(0).getCreatedAt();

                                    favouritePostModel = new FavouritePostModel(favouriteUniqueId,userUniqueId,postUniqueId,favouriteStatus,createdAt);

                                    addFavouriteAds = databaseSource.addFavouriteAds(favouritePostModel);
                                    if (addFavouriteAds){

                                        Log.d(TAG,"Favourite post added ------> "+addFavouriteAds);


                                        if (postUniqueId.equals(postId)){
                                            Log.d(TAG,"Favourite post status ------> "+favouriteStatus);
                                            favouriteIV.setImageResource(R.drawable.ic_favorite_black_24dp);
//                                            checkFavouriteStatus = true;
                                        }else {
                                            Log.d(TAG,"Favourite post status ------> "+favouriteStatus);
                                            favouriteIV.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                                        }


                                    }else {
                                        Log.d(TAG,"Favourite post added ------> "+addFavouriteAds);
                                    }
//                                }

                            }else {
                                Log.d(TAG,"Favourite post getFavouritePostList.size() ------> "+getFavouritePostList.size());
                                favouriteIV.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                            }


                        }else {
                            Log.d(TAG,"No favourite post exists"+favouriteResponseModel.getFavouriteAdsExists());
                        }
                    }else {
                        Log.d(TAG,"Error ---->  true and \nmsg ----->"+favouriteResponseModel.getErrorMsg());
                    }

                }else {
                    Log.d(TAG,"Server Error code : "+response.code());
                }
            }

            @Override
            public void onFailure(Call<FavouriteResponseModel> call, Throwable t) {

                Log.d(TAG,"May be you have no internet connection and error is ------>  "+t.toString());
            }
        });

    }


}
