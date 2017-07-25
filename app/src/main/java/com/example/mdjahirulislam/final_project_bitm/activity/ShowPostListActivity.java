package com.example.mdjahirulislam.final_project_bitm.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
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
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.adapter.CustomExpandableListAdapter;
import com.example.mdjahirulislam.final_project_bitm.adapter.PostCustomAdapter;
import com.example.mdjahirulislam.final_project_bitm.app.AppConfig;
import com.example.mdjahirulislam.final_project_bitm.app.ExpandableCategoryListData;
import com.example.mdjahirulislam.final_project_bitm.app.ExpandableLocationList;
import com.example.mdjahirulislam.final_project_bitm.app.RecyclerTouchListener;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.interfaces.ConnectionApi;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdsImageModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.RegistrationModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.AddPostResponseModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.UpdateAdsPostResponseModel;
import com.example.mdjahirulislam.final_project_bitm.sharePreference.UserSharePreference;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowPostListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = ShowPostListActivity.class.getSimpleName();
    private ProgressDialog progressDialog;

    private UserSharePreference userSharePreference;
    private DatabaseSource databaseSource;
    private ConnectionApi connectionApi;
    private UpdateAdsPostResponseModel updateAdsPostResponseModel;

    private ArrayList<RegistrationModel> registrationModelArrayList;

    private ArrayList<AdPostModel> adPostModelArrayList;

    private PostCustomAdapter postCustomAdapter;


    private TextView userNameTV;
    private TextView userEmailTV;

    private String userUniqueId;
    private RecyclerView postListRV;
    private SwipeRefreshLayout swipeLayout;


    private String lastAdsPostUniqueID;

    private Button filterLocationBTN;
    private Button filterCategoryBTN;
    private Spinner filterSortSpinner;
    private EditText filterSearchET;

    private ArrayList<String> sortArrayList;
    private ArrayAdapter<String> sortAdapter;


    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private int lastExpandedPosition = -1;

    private String myCurrentLocation;

    private String mySelectedLocation="null";
    private String category="null";
    private String sort="null";
    private String searchText="null";

    private ArrayAdapter<String> searchAdapter;
    private ListView searchLV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        progressDialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        progressDialog.setCancelable(false);

        setSupportActionBar(toolbar);
        postListRV  = (RecyclerView) findViewById(R.id.postListRV);
        connectionApi = AppConfig.getRetrofit().create(ConnectionApi.class);

        filterSearchET = (EditText) findViewById(R.id.postListSearchET);
        filterSortSpinner = (Spinner) findViewById(R.id.postListSortSpinner);
        filterLocationBTN = (Button) findViewById(R.id.postListLocationBtn);
        filterCategoryBTN = (Button) findViewById(R.id.postListCategoryBtn);
        searchLV = (ListView) findViewById(R.id.suggestionSearchListLV);

        registrationModelArrayList = new ArrayList<>();
        adPostModelArrayList = new ArrayList<>();
        sortArrayList = new ArrayList<>();

        userSharePreference = new UserSharePreference(this);
        userUniqueId = userSharePreference.getUserId();
        Log.d(TAG, "User Unique Id : " + userUniqueId);

        databaseSource = new DatabaseSource(this);
        registrationModelArrayList = databaseSource.getUserDetails(userUniqueId);




        sortArrayList.add(0,"Sort by                            ");
        sortArrayList.add(1,"Price                              ");
        sortArrayList.add(2,"Date                               ");

//        sortAdapter = new ArrayAdapter<String>(this,R.layout.sort_spinner_text_design,R.id.sortSpinnerTV,sortArrayList);
        sortAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sortArrayList);
//        sortAdapter.setDropDownViewResource(R.layout.sort_spinner_text_design,R.id.sortSpinnerTV);
        filterSortSpinner.setAdapter(sortAdapter);
//        filterSortSpinner.setDropDownVerticalOffset(100);
        filterSortSpinner.setDropDownHorizontalOffset(100);
        filterSortSpinner.setGravity(Gravity.CENTER);

        filterSortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView)view).setText("");

                sort = filterSortSpinner.getSelectedItem().toString();
                Log.d(TAG,"Sort by : "+sort.trim()+".");

                adPostModelArrayList = databaseSource.getFilterAds(mySelectedLocation,category,sort.trim(),searchText);

                if (adPostModelArrayList.size()>0){
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowPostListActivity.this);
                    postListRV.setLayoutManager(layoutManager);
                    postListRV.setItemAnimator(new DefaultItemAnimator());

                    postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
                    postListRV.setAdapter(postCustomAdapter);
                }else {
                    Log.d(TAG, "Ops No Result Found search list size : "+adPostModelArrayList.size());
                    Toast.makeText(ShowPostListActivity.this, "Ops No Result Found", Toast.LENGTH_SHORT).show();
                    postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
                    postListRV.setAdapter(postCustomAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                sort = "Date";
            }
        });


        adPostModelArrayList = databaseSource.getAllPost(userUniqueId);
        lastAdsPostUniqueID = adPostModelArrayList.get(0).getPost_unique_id();



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        postListRV.setLayoutManager(layoutManager);
        postListRV.setItemAnimator(new DefaultItemAnimator());

        postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
        postListRV.setAdapter(postCustomAdapter);




        postListRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), postListRV, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                String postID = adPostModelArrayList.get(position).getPost_unique_id();
                Log.d(TAG,"position : "+position+" \npost unique id  : "+postID);



                boolean storePostID = userSharePreference.setPostUniqueId(postID);

                if (storePostID){
                    Intent intent = new Intent(ShowPostListActivity.this,SwipeAdsDetailsActivity.class);
                    startActivity(intent);
                }
//                addEventExpense(eventId);

//
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.show_post_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.show_post_nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        userNameTV = (TextView) view.findViewById(R.id.navUserNameTV);
        userEmailTV = (TextView) view.findViewById(R.id.navUserEmailTV);

        if (registrationModelArrayList.size() > 0) {
            userNameTV.setText(registrationModelArrayList.get(0).getUser_full_name());
            userEmailTV.setText(registrationModelArrayList.get(0).getUser_email());
            Toast.makeText(this, "user name :"+registrationModelArrayList.get(0).getUser_full_name(), Toast.LENGTH_SHORT).show();
        }



        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);



        String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
                "iPhone 4S", "Samsung Galaxy Note 800",
                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};



        searchAdapter = new ArrayAdapter<String>(this, R.layout.sort_spinner_text_design, R.id.sortSpinnerTV, products);
        searchLV.setAdapter(searchAdapter);

//        filterSearchET.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                ShowPostListActivity.this.searchAdapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                searchLV.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////                searchLV.setVisibility(View.GONE);
//
//            }
//        });


        filterSearchET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
//                    performSearch();
                    Log.d(TAG,"Call search action");
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onRefresh() {

        Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Call swipe refresh");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateAdsPost(lastAdsPostUniqueID);
                swipeLayout.setRefreshing(false);
            }
        }, 3000);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.show_post_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_post_list, menu);
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
            logout();
            return true;
        }else if (id == R.id.action_resetAll){
            finish();
            startActivity(getIntent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.post_list_nav_allAds) {
            // Handle the camera action
        } else if (id == R.id.post_list_nav_favAds) {
            Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(ShowPostListActivity.this,ShowFavouritePostListActivity.class));

        } else if (id == R.id.post_list_nav_myAccount) {
            startActivity(new Intent(ShowPostListActivity.this,MyAccountActivity.class));

        } else if (id == R.id.post_list_nav_my_ads) {
            startActivity(new Intent(ShowPostListActivity.this,MyAdsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.show_post_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void logout() {

        progressDialog.setMessage("Logout ...");
        showDialog();

        boolean status = userSharePreference.resetUserId();
        if (status){

            databaseSource.whenLogoutUser();

            Intent intent = new Intent(ShowPostListActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            hideDialog();

        }else {
            hideDialog();
            Toast.makeText(this, "Logout not successfully", Toast.LENGTH_SHORT).show();
        }


    }

    public void goToAddPostActivity(View view) {
        Intent intent = new Intent(ShowPostListActivity.this,AddPostActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        finish();
    }




    private void updateAdsPost(String postUniqueID) {



        RequestBody last_post_unique_id = RequestBody.create(MultipartBody.FORM,postUniqueID);

        Call<UpdateAdsPostResponseModel> call = connectionApi.updateAds(last_post_unique_id);

//        Call<RegistrationResponseModel> call = connectionApi.uploadPhotoWithText(parts);

        call.enqueue(new Callback<UpdateAdsPostResponseModel>() {
            @Override
            public void onResponse(Call<UpdateAdsPostResponseModel> call, Response<UpdateAdsPostResponseModel> response) {
                if (response.code()==200){
                    updateAdsPostResponseModel = response.body();
                    boolean error = updateAdsPostResponseModel.getError();
                    if (!error){
                        AdPostModel adPostModel;
                        boolean updatePostStatus = false;
                        boolean storeImageStatus = false;
                        Log.d(TAG,"Successful "+updateAdsPostResponseModel.getErrorMsg());


                        List<UpdateAdsPostResponseModel.UpdateAd> updateAdList = updateAdsPostResponseModel.getUpdateAds();


                        for (int i = 0; i < updateAdList.size(); i++) {
                            String postUniqueId = updateAdList.get(i).getPostUniqueId();
                            String postedUserUniqueId = updateAdList.get(i).getUserUniqueId();
                            String userName = updateAdList.get(i).getUserName();
                            String userMobileNo = updateAdList.get(i).getUserMobileNo();
                            String postImageUrl = updateAdList.get(i).getPostImageUrl();
                            String postTitle = updateAdList.get(i).getPostTitle();
                            String postPrice = updateAdList.get(i).getPostPrice();
                            String postDetails = updateAdList.get(i).getPostDetails();
                            String postLocation = updateAdList.get(i).getPostLocation();
                            String postCondition = updateAdList.get(i).getPostCondition();
                            String postCategory = updateAdList.get(i).getPostCategory();
                            String postLatitude = updateAdList.get(i).getPostLatitude();
                            String postLongitude = updateAdList.get(i).getPostLongitude();
                            String postCreatedAt = updateAdList.get(i).getCreatedAt();
                            String postUpdatedAt = updateAdList.get(i).getCreatedAt();

                            adPostModel = new AdPostModel(postUniqueId,postedUserUniqueId,userName,userMobileNo,
                                    postImageUrl,postTitle,postPrice,postDetails,
                                    postLocation,postCondition,postCategory,postLatitude,postLongitude,postCreatedAt);
                            updatePostStatus = databaseSource.addAdPost(adPostModel);
                            Log.d(TAG,"Update post insert "+updatePostStatus);
//                            if (updatePostStatus){
//                                adPostModelArrayList.add(adPostModel);
//                            }
                        }




                        boolean updateAdsImageExists = updateAdsPostResponseModel.getUpdateAdImageExists();
                        if (updateAdsImageExists){

                            AdsImageModel allAdsPostImageModel;
                            List<UpdateAdsPostResponseModel.UpdateAdImage> updateAdsImagesList = updateAdsPostResponseModel.getUpdateAdImage();

                            if (updateAdsImagesList.size()>0){
                                for (int i = 0; i < updateAdsImagesList.size(); i++) {
                                    String allAdsImageUniqueId = updateAdsImagesList.get(i).getAdImageUniqueId();
                                    String allAdsImagePostUniqueId = updateAdsImagesList.get(i).getPostUniqueId();
                                    String allAdsImageUrl = updateAdsImagesList.get(i).getPostImageUrl();
                                    String allAdsImageCreatedAt = updateAdsImagesList.get(i).getCreatedAt();

                                    allAdsPostImageModel = new AdsImageModel(allAdsImageUniqueId,allAdsImagePostUniqueId,allAdsImageUrl,allAdsImageCreatedAt);
                                    storeImageStatus = databaseSource.addAdsImage(allAdsPostImageModel);
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
                            Log.d(TAG,"No Ad Post Image Found and post image response message :  ----  "+updateAdsPostResponseModel.getUpdateAdImageMsg());
                            Toast.makeText(ShowPostListActivity.this, "No Ad Post Image Found and post image response message :  ----  "+updateAdsPostResponseModel.getUpdateAdImageMsg(), Toast.LENGTH_SHORT).show();

                        }


                        if (updatePostStatus && storeImageStatus){

                            Log.d(TAG,"post insert SQLite and postStatus : --- "+updatePostStatus);
                            Toast.makeText(ShowPostListActivity.this,"Ads Post Successful :) "+updatePostStatus, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(ShowPostListActivity.this,ShowPostListActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                            finish();

//                            postList=db.getPostFromPostTable(groupIdString);
//                            customAdapterPost = new CustomAdapterPost(postList);
//                            customAdapterPost.notifyDataSetChanged();
//                            postRecyclerView.setAdapter(customAdapterPost);

                            adPostModelArrayList = databaseSource.getAllPost(userUniqueId);
                            postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
                            postCustomAdapter.notifyDataSetChanged();
                            postListRV.setAdapter(postCustomAdapter);

                            lastAdsPostUniqueID = adPostModelArrayList.get(0).getPost_unique_id();
                            Log.d(TAG,"After refresh last post id ------------> "+lastAdsPostUniqueID);

                        }

                    }else {
                        Log.d(TAG,"Not Successful : "+updateAdsPostResponseModel.getErrorMsg());
                        Toast.makeText(ShowPostListActivity.this,"No Update Available", Toast.LENGTH_SHORT).show();

                    }


                }else {
                    Log.d(TAG,"Successful Error code : "+response.code());
                }
            }

            @Override
            public void onFailure(Call<UpdateAdsPostResponseModel> call, Throwable t) {

                Log.d(TAG,"Failed"+t.toString());
            }
        });


    }


    public void filterLocation(View view) {

        LayoutInflater inflater = LayoutInflater.from(ShowPostListActivity.this);
        View dialog = inflater.inflate(R.layout.expendable_list_view, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(ShowPostListActivity.this);
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
                    filterLocationBTN.setText(mySelectedLocation);

                    Log.d(TAG, "Selected Location 1 Is : " + myCurrentLocation);
                } else if (!mySelectedLocation.isEmpty()) {
                    filterLocationBTN.setText(expandableListDetail.get(
                            expandableListTitle.get(groupPosition)).get(
                            childPosition));

                    mySelectedLocation = filterLocationBTN.getText().toString().trim();


                    Log.d(TAG,"In Location btn (Category) : "+category);
                    Log.d(TAG,"In Location btn (Location) : "+mySelectedLocation);
                    Log.d(TAG,"In Location btn (Sort) : "+sort);

                    adPostModelArrayList = databaseSource.getFilterAds(mySelectedLocation,category,sort.trim(),searchText);

                    if (adPostModelArrayList.size()>0){
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowPostListActivity.this);
                        postListRV.setLayoutManager(layoutManager);
                        postListRV.setItemAnimator(new DefaultItemAnimator());

                        postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
                        postListRV.setAdapter(postCustomAdapter);
                    }else {
                        Log.d(TAG, "Ops No Result Found search list size : "+adPostModelArrayList.size() + myCurrentLocation);
                        Toast.makeText(ShowPostListActivity.this, "Ops No Result Found", Toast.LENGTH_SHORT).show();
                        postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
                        postListRV.setAdapter(postCustomAdapter);
                    }





                    Log.d(TAG, "Selected Location 2 Is : " + myCurrentLocation);
                }
                customAlertDialog.hide();
                return true;
            }
        });

//        customAlertDialog.hide();
    }


    public void filterCategory(View view) {

        LayoutInflater inflater = LayoutInflater.from(ShowPostListActivity.this);
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


        final AlertDialog.Builder builder = new AlertDialog.Builder(ShowPostListActivity.this);
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
                filterCategoryBTN.setText(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition));
                category = filterCategoryBTN.getText().toString().trim();

                Log.d(TAG,"Category : "+category);

                adPostModelArrayList = databaseSource.getFilterAds(mySelectedLocation,category,sort.trim(),searchText);

                if (adPostModelArrayList.size()>0){
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowPostListActivity.this);
                    postListRV.setLayoutManager(layoutManager);
                    postListRV.setItemAnimator(new DefaultItemAnimator());

                    postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
                    postListRV.setAdapter(postCustomAdapter);
                }else {
                    Log.d(TAG, "Ops No Result Found search list size : "+adPostModelArrayList.size() + myCurrentLocation);
                    Toast.makeText(ShowPostListActivity.this, "Ops No Result Found", Toast.LENGTH_SHORT).show();
                    postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
                    postListRV.setAdapter(postCustomAdapter);
                }

                customAlertDialog.hide();
                return true;
            }
        });
    }

    public void filterByCustomSearch(View view) {
        searchText = filterSearchET.getText().toString().trim();

        Log.d(TAG,"searchText : "+searchText);

        adPostModelArrayList = databaseSource.getFilterAds(mySelectedLocation,category,sort,searchText);

        if (adPostModelArrayList.size()>0){
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowPostListActivity.this);
            postListRV.setLayoutManager(layoutManager);
            postListRV.setItemAnimator(new DefaultItemAnimator());

            postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
            postListRV.setAdapter(postCustomAdapter);
        }else {
            Log.d(TAG, "Ops No Result Found search list size : "+adPostModelArrayList.size() + myCurrentLocation);
            Toast.makeText(ShowPostListActivity.this, "Ops No Result Found", Toast.LENGTH_SHORT).show();
            postCustomAdapter  = new PostCustomAdapter(adPostModelArrayList);
            postListRV.setAdapter(postCustomAdapter);
        }


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
