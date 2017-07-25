package com.example.mdjahirulislam.final_project_bitm.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.adapter.SwipeAdsDetailsAdapter;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.example.mdjahirulislam.final_project_bitm.sharePreference.UserSharePreference;

import java.util.ArrayList;

public class SwipeAdsDetailsActivity extends AppCompatActivity {

    private static final String TAG = ShowPostDetailsActivity.class.getSimpleName();

    private String adsUniqueID;
    private ViewPager viewPager;
    private DatabaseSource databaseSource;
    private UserSharePreference userSharePreference;
    private ArrayList<AdPostModel> adPostModelArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_ads_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseSource = new DatabaseSource(this);
        userSharePreference = new UserSharePreference(this);
        adsUniqueID = userSharePreference.getPostUniqueId();

        Log.d(TAG,"Swipe details ads unique id ------> "+adsUniqueID);

        adPostModelArrayList = databaseSource.getSelectedPostDetails(adsUniqueID);
//        adPostModelArrayList = databaseSource.getAllPost(adsUniqueID);
        viewPager = (ViewPager) findViewById(R.id.adsDetailsPager);
        viewPager.setAdapter(new SwipeAdsDetailsAdapter(this,adPostModelArrayList));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.show_post_list, menu);
//        return true;
//    }
//
//
//        @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_swipe_details_favourite) {
//
//            Toast.makeText(this, " from activity Add Favourite", Toast.LENGTH_SHORT).show();
//
//            return true;
//        }else if (id == R.id.action_swipe_details_share){
//            Toast.makeText(this, "from activity Click on share", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
