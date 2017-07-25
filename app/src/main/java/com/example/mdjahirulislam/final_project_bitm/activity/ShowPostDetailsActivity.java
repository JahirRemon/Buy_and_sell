package com.example.mdjahirulislam.final_project_bitm.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.adapter.SlidingImageAdapter;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.sharePreference.UserSharePreference;
import com.google.android.gms.maps.model.LatLng;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShowPostDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = ShowPostDetailsActivity.class.getSimpleName();

//    Image Slider
private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {
            R.drawable.add_post,
            R.drawable.no_image,
            R.drawable.delete_icon,
            R.drawable.delete_icon_small};
    private ArrayList<String> imagesArray = new ArrayList<String>();
    private ArrayList<AdPostModel> adPostModel = new ArrayList<>();


    private DatabaseSource databaseSource;
    private UserSharePreference userSharePreference;
    private String postUniqueId;

    private TextView adsTitleTV;
    private TextView adsPriceTV;
    private TextView adsPostedUserNameTV;
    private TextView adsDetailsTV;
    private TextView adsLocationTV;
    private TextView adsConditionTV;
    private TextView adsCategoryTV;
    private TextView adsPostedTimeTV;
    private Button callBTN;
    private Button smsBTN;


    private String adsTitle;
    private String adsPrice;
    private String adsUserName;
    private String adsUserMobile;
    private String adsDetails;
    private String adsLocation;
    private String adsCondition;
    private String adsCategory;
    private String adsPostedTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        adsTitleTV = (TextView) findViewById(R.id.showAdsTitleTV);
        adsPriceTV = (TextView) findViewById(R.id.showAdsPriceTV);
        adsPostedUserNameTV = (TextView) findViewById(R.id.showAdsUserNameTV);
        adsDetailsTV = (TextView) findViewById(R.id.showAdsDetailsTV);
        adsLocationTV = (TextView) findViewById(R.id.showAdsLocationTV);
        adsConditionTV = (TextView) findViewById(R.id.showAdsConditionTV);
        adsCategoryTV = (TextView) findViewById(R.id.showAdsCategoryTV);
        adsPostedTimeTV = (TextView) findViewById(R.id.showAdsPostTimeTV);
        callBTN = (Button) findViewById(R.id.callBTN);
        smsBTN = (Button) findViewById(R.id.smsBTN);


        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_post_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void init() {

        databaseSource = new DatabaseSource(this);
        userSharePreference = new UserSharePreference(this);
//        postUniqueId = userSharePreference.getPostUniqueId();
        postUniqueId = getIntent().getStringExtra("postUniqueId");

        Log.d(TAG,"post unique id "+postUniqueId);
        imagesArray = databaseSource.getSelectedPostImages(postUniqueId);


        Log.d(TAG,"post image array size  ------>   "+imagesArray.size());

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImageAdapter(ShowPostDetailsActivity.this,imagesArray));


//        CirclePageIndicator work and auto slide show.

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);



        NUM_PAGES =IMAGES.length;



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



        adPostModel = databaseSource.getSelectedPostDetails(postUniqueId);

        if (adPostModel.size()>0){
            adsTitle = adPostModel.get(0).getPost_title().toString().trim();
            adsPrice = adPostModel.get(0).getPost_price().toString().trim();
            adsUserName = adPostModel.get(0).getUser_name().toString().trim();
            adsUserMobile = adPostModel.get(0).getUser_mobile_no().toString().trim();
            adsDetails = adPostModel.get(0).getPost_details().toString().trim();
            adsLocation = adPostModel.get(0).getPost_location().toString().trim();
            adsCondition = adPostModel.get(0).getPost_condition().toString().trim();
            adsCategory = adPostModel.get(0).getPost_category().toString().trim();
            adsPostedTime = adPostModel.get(0).getCreated_at().toString().trim();

            adsTitleTV.setText(adsTitle);
            adsPriceTV.setText(adsPrice);
            adsPostedUserNameTV.setText("For sale by "+adsUserName);
            adsDetailsTV.setText(adsDetails);
            adsLocationTV.setText(adsLocation);
            adsConditionTV.setText(adsCondition);
            adsCategoryTV.setText(adsCategory);
            adsPostedTimeTV.setText(adsPostedTime);


        }else {
            Log.d(TAG,"Post Details Not Found");
        }

        if (!adsUserMobile.isEmpty()){

            Log.d(TAG,"user mobile no "+adsUserMobile);
            callBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+adsUserMobile));


                    if (ActivityCompat.checkSelfPermission(ShowPostDetailsActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(callIntent);

                }
            });

            smsBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.putExtra("sms_body", "Write Your Message");
                    sendIntent.putExtra("address", adsUserMobile);
                    sendIntent.setType("vnd.android-dir/mms-sms");

                    if (ActivityCompat.checkSelfPermission(ShowPostDetailsActivity.this,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(sendIntent);
                }
            });


        }else {
            Log.d(TAG,"User mobile number not available");
        }









    }

    public void goToLocationMap(View view) {

        double latitude = 23.784223;
        double longitude = 90.353346;
        String address = "Madaripur";
        LatLng geoPoint = getLocationFromAddress(ShowPostDetailsActivity.this,address);
        Log.d(TAG,"lat ------ "+geoPoint.latitude+"\nlong --------- "+geoPoint.longitude);
        String label = "ABC Label";
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(intent);
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
}
