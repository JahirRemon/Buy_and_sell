package com.example.mdjahirulislam.final_project_bitm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.modelClass.RegistrationModel;
import com.example.mdjahirulislam.final_project_bitm.sharePreference.UserSharePreference;

import java.util.ArrayList;

public class MyAccountActivity extends AppCompatActivity {
    private static final String TAG = MyAccountActivity.class.getSimpleName();

    private DatabaseSource databaseSource;
    private UserSharePreference userSharePreference;
    private ArrayList<RegistrationModel> userDetailList;
    private String userUniqueID;


    private EditText myNameET;
    private EditText myMobileET;
    private EditText myEmailET;
    private TextView changePassTV;
    private LinearLayout passLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        myNameET = (EditText) findViewById(R.id.myNameET);
        myMobileET = (EditText) findViewById(R.id.myMobileET);
        myEmailET = (EditText) findViewById(R.id.myEmailET);
        changePassTV = (TextView) findViewById(R.id.changePassTV);
        passLayout = (LinearLayout) findViewById(R.id.passLayout);


        setSupportActionBar(toolbar);
        databaseSource = new DatabaseSource(this);
        userSharePreference = new UserSharePreference(this);
        userDetailList = new ArrayList<>();

        userUniqueID = userSharePreference.getUserId();

        userDetailList = databaseSource.getUserDetails(userUniqueID);
        if (userDetailList.size()>0){

            myNameET.setText(userDetailList.get(0).getUser_full_name());
            myMobileET.setText(userDetailList.get(0).getUser_mobile_no());
            myEmailET.setText(userDetailList.get(0).getUser_email());

        }else {
            Log.d(TAG,"user unique id not found");
        }

        changePassTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passLayout.setVisibility(View.VISIBLE);
            }
        });


    }
}
