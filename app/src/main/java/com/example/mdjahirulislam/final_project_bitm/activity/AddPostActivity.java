package com.example.mdjahirulislam.final_project_bitm.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.adapter.CustomAdapterMultiImage;
import com.example.mdjahirulislam.final_project_bitm.adapter.CustomExpandableListAdapter;
import com.example.mdjahirulislam.final_project_bitm.app.AppConfig;
import com.example.mdjahirulislam.final_project_bitm.app.ExpandableCategoryListData;
import com.example.mdjahirulislam.final_project_bitm.app.ExpandableLocationList;
import com.example.mdjahirulislam.final_project_bitm.app.NetworkUtil;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.interfaces.ConnectionApi;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdsImageModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.Spacecraft;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.AddPostResponseModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.LoginResponseModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.RegistrationResponseModel;
import com.example.mdjahirulislam.final_project_bitm.sharePreference.UserSharePreference;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private static final String TAG = AddPostActivity.class.getSimpleName();

    private ConnectionApi connectionApi;
    private ProgressDialog progressDialog;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;


    private TextView userNameTV;
    private TextView userEmailTV;
    private Spinner conditionSpinner;
    private LinearLayout postDetailsLayout;

    private int lastExpandedPosition = -1;
    private ArrayList<String> conditionArrayList;
    private ArrayAdapter<String> conditionSpinnerAdapter;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Geocoder geocoder;
    private List<Address> addressList;

    private AddPostResponseModel addPostResponseModel;


    private Button selectCategoryBtn;
    private Button selectLocationBtn;
    private GridView gv;
    private ImageView selectedImageIV;
    private EditText adTitleET;
    private EditText adDetailsET;
    private EditText adPriceET;


    private UserSharePreference userSharePreference;
    private DatabaseSource databaseSource;

    private String userUniqueId;
    private String category;
    private String myCurrentLocation;
    private String mySelectedLocation;
    private ArrayList<String> filePaths = new ArrayList<String>();
    private String filePath;
    private String condition;
    private String adTitle;
    private String adDetails;
    private String adPrice;
    private Double currentLatitude;

    private Double currentLongitude;
    private Double latitude;
    private Double longitude;

    private int internetStatus;

    private ArrayList<AdPostModel> tempAdPostModelArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        progressDialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        progressDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        selectCategoryBtn = (Button) findViewById(R.id.selectCategoryBtn);
        selectLocationBtn = (Button) findViewById(R.id.addPostLocationBtn);
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        adTitleET = (EditText) findViewById(R.id.adTitleET);
        adDetailsET = (EditText) findViewById(R.id.adDetailsET);
        adPriceET = (EditText) findViewById(R.id.adPriceET);

        tempAdPostModelArray = new ArrayList<>();

        internetStatus = NetworkUtil.getConnectivityStatus(this);
        Log.d(TAG,"Internet Status ----------->  "+internetStatus);

//        locationSpinner = (Spinner) findViewById(R.id.addPostLocationSpinner);
        postDetailsLayout = (LinearLayout) findViewById(R.id.postDetailsLayout);
        gv = (GridView) findViewById(R.id.gv);
        selectedImageIV = (ImageView) findViewById(R.id.selectedImageIV);

        connectionApi = AppConfig.getRetrofit().create(ConnectionApi.class);

        databaseSource = new DatabaseSource(this);

        userSharePreference = new UserSharePreference(this);
        userUniqueId = userSharePreference.getUserId();
        Log.d(TAG,"user unique id : "+userUniqueId);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        geocoder = new Geocoder(this, Locale.getDefault());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        userNameTV = (TextView) view.findViewById(R.id.navUserNameTV);
        userEmailTV = (TextView) view.findViewById(R.id.navUserEmailTV);

        conditionArrayList = new ArrayList<>();
        conditionArrayList.add(0, "Used");
        conditionArrayList.add(0, "New");

        conditionSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, conditionArrayList);
        conditionSpinner.setAdapter(conditionSpinnerAdapter);

        conditionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                condition = conditionSpinner.getSelectedItem().toString();
                Log.d(TAG, "Product Condition (onItemSelected) : " + condition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                condition = null;
                Log.d(TAG, "Product Condition (onNothingSelected) : " + condition);
            }
        });


//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onPause() {
        googleApiClient.disconnect();
        super.onPause();
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
        getMenuInflater().inflate(R.menu.add_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logOut) {
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
            Toast.makeText(this, "add post menu", Toast.LENGTH_SHORT).show();
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


    public void selectCategory(View view) {


        LayoutInflater inflater = LayoutInflater.from(AddPostActivity.this);
        View dialog = inflater.inflate(R.layout.expendable_list_view, null);

        expandableListView = (ExpandableListView) dialog.findViewById(R.id.expandableCategoryListView);
        expandableListDetail = ExpandableCategoryListData.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());

        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {


                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }


        });


        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });


        final AlertDialog.Builder builder = new AlertDialog.Builder(AddPostActivity.this);
        builder.setView(dialog);
        final AlertDialog customAlertDialog = builder.create();
        customAlertDialog.setTitle("Select Category");
        customAlertDialog.show();

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition)
                        + " -> " + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                selectCategoryBtn.setText(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition));
                category = selectCategoryBtn.getText().toString().trim();
                Log.d(TAG,"Category : "+category);

                if (!category.isEmpty()) {
                    selectLocationBtn.setVisibility(View.VISIBLE);
                }
                customAlertDialog.hide();
                return true;
            }
        });


    }

//    public String findMyLocation(){
//
//        String currentLocation = "Under work";
//        Toast.makeText(this, "current location "+currentLocation, Toast.LENGTH_SHORT).show();
//        return currentLocation;
//    }

    public void goToSelectPhoto(View view) {

        filePaths.clear();

        FilePickerBuilder.getInstance().setMaxCount(3)
                .setSelectedFiles(filePaths)
                .setActivityTheme(R.style.AppTheme)
                .pickPhoto(AddPostActivity.this);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE:

                if (resultCode == RESULT_OK && data != null) {

//                    Log.d("remon "+TAG,data.getClipData().getItemAt(0).toString());

                    filePaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
//                    filePath = data.getStringExtra(FilePickerConst.KEY_SELECTED_PHOTOS);

//                    Log.d(TAG,"file path : "+filePaths.get(0).toString());
                    Log.d(TAG,"file path : "+filePath);
                    Spacecraft s;
                    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

                    try {
                        for (String path : filePaths) {
                            s = new Spacecraft();
                            s.setName(path.substring(path.lastIndexOf("/") + 1));

                            s.setUri(Uri.fromFile(new File(path)));
//                            uri = s.getUri();

                            Log.d("Main Activity : ", "select image Array String for gallery : " + path);
//                            Log.d("Main Activity : ","select image Array String for gallery : "+uri);

                            spacecrafts.add(s);
                        }

                        gv.setVisibility(View.VISIBLE);

                        selectedImageIV.setVisibility(View.GONE);
                        gv.setNumColumns(spacecrafts.size());
                        if (spacecrafts.size() == 0) {
                            selectedImageIV.setVisibility(View.VISIBLE);
                            Toast.makeText(this, "add post on activity result", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(this, "else add post on activity result", Toast.LENGTH_SHORT).show();
                        }
                        gv.setAdapter(new CustomAdapterMultiImage(this, spacecrafts));
                        Toast.makeText(AddPostActivity.this, "Total = " + String.valueOf(spacecrafts.size()), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = LocationRequest.create()
                .setInterval(1000 * 60 * 5).setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        currentLatitude = location.getLatitude();
        Log.d(TAG,"Latitude : "+currentLatitude);
        currentLongitude = location.getLongitude();
        Log.d(TAG,"Longitude : "+currentLongitude);

        try {
            addressList = geocoder.getFromLocation(currentLatitude, currentLongitude, 3);
            myCurrentLocation = addressList.get(0).getAddressLine(0);
            Log.d(TAG,"My Current Location : "+ myCurrentLocation);
//            addressList = geocoder.getFromLocation(22.782526,90.244058,3);

            if (!this.myCurrentLocation.isEmpty()) {
                int i = 0;
                for (Address addressLists : addressList) {
                    String city = addressLists.getAdminArea();
                    String getAddressLine = addressLists.getAddressLine(i);
                    String getLocality = addressLists.getLocality();
                    String getSubLocality = addressLists.getSubLocality();
                    String getPostalCode = addressLists.getPostalCode();
                    Log.d(TAG, "getLocality-----------" + getLocality + "\ngetSubLocality------------ "
                            + getSubLocality + "\ngetAddressLine-------------" + getAddressLine + "\ncity----------" + city + "\ngetPostalCode----------" + getPostalCode);

                    i++;
                }
//            Log.d(TAG,"Selected Location Is cityname : "+location+"\n a ------ "+a+"\n b ------ "+b
//                    +"\n c ------ "+c+"\n d ------ "+d+"\n e ------ "+e+"\n f ------ "+f+"\n g ------ "+g+"\n h ------ "+h+"\n result ------ "+result);
                Toast.makeText(this, "Location Store Successful  Name is location: " + this.myCurrentLocation, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public void selectLocation(View view) {


        LayoutInflater inflater = LayoutInflater.from(AddPostActivity.this);
        View dialog = inflater.inflate(R.layout.expendable_list_view, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddPostActivity.this);
        builder.setView(dialog);
        final AlertDialog customAlertDialog = builder.create();
        customAlertDialog.setTitle("Select Location");

        expandableListView = (ExpandableListView) dialog.findViewById(R.id.expandableCategoryListView);
        expandableListDetail = ExpandableLocationList.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());

        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {


//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }


        });


        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();

            }
        });



//        customAlertDialog.setCustomTitle();
        customAlertDialog.show();

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
//                Toast.makeText(getApplicationContext(), expandableListTitle.get(groupPosition)
//                        + " -> " + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();

                mySelectedLocation = expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition);

                if (mySelectedLocation.equals("Find My Location")) {

                    mySelectedLocation = myCurrentLocation;
                    latitude = currentLatitude;
                    longitude = currentLongitude;
                    selectLocationBtn.setText(AddPostActivity.this.mySelectedLocation);
                    postDetailsLayout.setVisibility(View.VISIBLE);
                    Log.d(TAG, "Selected Location 1 Is : " + myCurrentLocation+"\nlat------> "+latitude+"\nlon------> "+longitude);
                } else if (!mySelectedLocation.isEmpty()) {
                    selectLocationBtn.setText(expandableListDetail.get(
                            expandableListTitle.get(groupPosition)).get(
                            childPosition));


                    mySelectedLocation = selectLocationBtn.getText().toString().trim();
                    postDetailsLayout.setVisibility(View.VISIBLE);
                    latitude = 0.0;
                    longitude = 0.0;
                    Log.d(TAG, "Selected Location 2 Is : " + mySelectedLocation+"\nlat------> "+latitude+"\nlon------> "+longitude);
                }
                customAlertDialog.hide();
                return true;
            }
        });

    }


    public void postAd(View view) {


        adTitle = adTitleET.getText().toString().trim();
        Log.d(TAG,"Title : "+adTitle);
        adDetails = adDetailsET.getText().toString().trim();
        Log.d(TAG,"Details : "+adDetails);
        adPrice = adPriceET.getText().toString().trim();
        Log.d(TAG,"Price : "+adPrice);

        if (!userUniqueId.isEmpty() && !category.isEmpty() && !mySelectedLocation.isEmpty() && filePaths.size()!=0 && !condition.isEmpty()
                && !adTitle.isEmpty() && !adDetails.isEmpty() && !adPrice.isEmpty() && latitude!= null && longitude != null){
            Log.d("Ad Post Button click 1 -> "+TAG,"userUniqueId "+userUniqueId+"\ncategory "+category+"\nmyCurrentLocation "+ mySelectedLocation +"\nfilePath "+
                    filePaths.get(0)+"\ncondition "+condition+"\nadTitle "+adTitle+"\nadDetails "+adDetails+"\nadPrice "+adPrice+"\ncurrentLatitude "+currentLatitude+"\ncurrentLongitude "+currentLongitude);

            progressDialog.setMessage("Posting ...");
            showDialog();

            uploadFile();


        }else {
            Log.d("Ad Post Button click 2 -> "+TAG,"userUniqueId "+userUniqueId+"\ncategory "+category+"\nmyCurrentLocation "+ mySelectedLocation +"\nfilePath "+
                    filePaths.get(0)+"\ncondition "+condition+"\nadTitle "+adTitle+"\nadDetails "+adDetails+"\nadPrice "+adPrice);

            latitude = 0.0;
            longitude= 0.0;
            uploadFile();
        }


    }


    private void uploadFile() {


        RequestBody user_unique_id = RequestBody.create(MultipartBody.FORM,userUniqueId);
        RequestBody post_title = RequestBody.create(MultipartBody.FORM,adTitle);
        RequestBody post_price = RequestBody.create(MultipartBody.FORM,adPrice);
        RequestBody post_details = RequestBody.create(MultipartBody.FORM,adDetails);
        RequestBody post_location = RequestBody.create(MultipartBody.FORM,mySelectedLocation);
        RequestBody post_condition = RequestBody.create(MultipartBody.FORM,condition);
        RequestBody post_category = RequestBody.create(MultipartBody.FORM,category);
        RequestBody post_latitude= RequestBody.create(MultipartBody.FORM, String.valueOf(latitude));
        RequestBody post_longitude = RequestBody.create(MultipartBody.FORM, String.valueOf(longitude));

        File file;
        List<MultipartBody.Part> parts = new ArrayList<>();
        parts.clear();
        for (int i =0; i< filePaths.size(); i++ ){
            file = new File(filePaths.get(i));
            Log.d("Remon "+TAG,file.toString()+"    SIZE : "+filePaths.size());
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image[]",file.getName(),requestBody);
            parts.add(i,fileToUpload);

        }

        Call<AddPostResponseModel> call = connectionApi.uploadPhotoWithText(user_unique_id,post_title,
                post_price,post_details,post_location,post_condition,
                post_category,post_latitude,post_longitude,parts);

//        Call<RegistrationResponseModel> call = connectionApi.uploadPhotoWithText(parts);

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
                            Toast.makeText(AddPostActivity.this, "No Ad Post Image Found and post image response message :  ----  "+addPostResponseModel.getAdImageMsg(), Toast.LENGTH_SHORT).show();

                        }


                        if (postStatus){

                            Log.d(TAG,"post insert SQLite and postStatus : --- "+postStatus);
                           Toast.makeText(AddPostActivity.this,"Ads Post Successful :) "+postStatus, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddPostActivity.this,ShowPostListActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();

                        }

                    }else {
                        Log.d(TAG,"Not Successful : "+addPostResponseModel.getErrorMsg());
                    }


                }else {
                    Log.d(TAG,"Successful Error code : "+response.code());
                }

               hideDialog();
            }

            @Override
            public void onFailure(Call<AddPostResponseModel> call, Throwable t) {
                hideDialog();
                Log.d(TAG,"May be No Internet Connection \n"+t.toString());
                if (internetStatus == 0){

                    AdPostModel tempAdPostModel = new AdPostModel(userUniqueId,filePaths.get(0),adTitle,adPrice,
                            adDetails,mySelectedLocation,condition,category,String.valueOf(currentLatitude),String.valueOf(currentLongitude));

//                    tempAdPostModelArray.add(tempAdPostModel);
                    boolean tempStorePost = databaseSource.addAdPostTemp(tempAdPostModel);
                    if (tempStorePost){
                        Log.d(TAG,"Post store temporary : "+tempStorePost);
                        Toast.makeText(AddPostActivity.this, "Post store temporary", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddPostActivity.this,ShowPostListActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }

                }
            }
        });


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
