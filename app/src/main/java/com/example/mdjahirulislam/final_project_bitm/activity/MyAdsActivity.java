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
import android.widget.TextView;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.adapter.PostCustomAdapter;
import com.example.mdjahirulislam.final_project_bitm.app.RecyclerTouchListener;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.sharePreference.UserSharePreference;

import java.util.ArrayList;

public class MyAdsActivity extends AppCompatActivity {

    private static final String TAG = MyAdsActivity.class.getSimpleName();

    private DatabaseSource databaseSource;
    private UserSharePreference userSharePreference;
    private ArrayList<AdPostModel> myPostLists;
    private PostCustomAdapter postCustomAdapter;

    private String userUniqueID;
    private RecyclerView myPostListRV;
    private TextView myAdsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myPostListRV = (RecyclerView) findViewById(R.id.myPostListRV);
        myAdsTV = (TextView) findViewById(R.id.myAdsTV);

        databaseSource = new DatabaseSource(this);
        userSharePreference = new UserSharePreference(this);
        myPostLists = new ArrayList<>();

        userUniqueID = userSharePreference.getUserId();

        myPostLists = databaseSource.getMyAllPost(userUniqueID);

        if (myPostLists.size()<0){
            myAdsTV.setText("You have no post");
            myAdsTV.setVisibility(View.VISIBLE);
            myAdsTV.setTextColor(R.color.colorPrimary);
            myAdsTV.setTextSize(24);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        myPostListRV.setLayoutManager(layoutManager);
        myPostListRV.setItemAnimator(new DefaultItemAnimator());

        postCustomAdapter  = new PostCustomAdapter(myPostLists);
        myPostListRV.setAdapter(postCustomAdapter);




        myPostListRV.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), myPostListRV, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                String postID = myPostLists.get(position).getPost_unique_id();
                Log.d(TAG,"position : "+position+" \npost unique id  : "+postID);



                boolean storePostID = userSharePreference.setPostUniqueId(postID);

                if (storePostID){
                    Intent intent = new Intent(MyAdsActivity.this,ShowPostDetailsActivity.class);
                    intent.putExtra("postUniqueId",postID);
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
