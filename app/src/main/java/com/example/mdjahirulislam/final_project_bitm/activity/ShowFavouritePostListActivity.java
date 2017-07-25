package com.example.mdjahirulislam.final_project_bitm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.adapter.PostCustomAdapter;
import com.example.mdjahirulislam.final_project_bitm.app.RecyclerTouchListener;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.modelClass.RegistrationModel;
import com.example.mdjahirulislam.final_project_bitm.sharePreference.UserSharePreference;

import java.util.ArrayList;

public class ShowFavouritePostListActivity extends AppCompatActivity {

    private static final String TAG = ShowPostListActivity.class.getSimpleName();

    private RecyclerView postListRV;
    private ArrayList<AdPostModel> adPostModelArrayList;
    private ArrayList<RegistrationModel> registrationModelArrayList;
    private DatabaseSource databaseSource;
    private PostCustomAdapter postCustomAdapter;
    private UserSharePreference userSharePreference;
    private String userUniqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_favourite_post_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        postListRV = (RecyclerView) findViewById(R.id.favPostListRV);
        adPostModelArrayList = new ArrayList<>();
        registrationModelArrayList = new ArrayList<>();
        databaseSource = new DatabaseSource(this);

        userSharePreference = new UserSharePreference(this);
        userUniqueId = userSharePreference.getUserId();
        Log.d(TAG, "User Unique Id : " + userUniqueId);


        registrationModelArrayList = databaseSource.getUserDetails(userUniqueId);



        adPostModelArrayList = databaseSource.getAllFavouritePost(userUniqueId);



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
                    Intent intent = new Intent(ShowFavouritePostListActivity.this,SwipeAdsDetailsActivity.class);
                    startActivity(intent);
                }
//                addEventExpense(eventId);

//
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
}
