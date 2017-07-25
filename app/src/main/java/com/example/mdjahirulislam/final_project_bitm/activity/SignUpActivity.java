package com.example.mdjahirulislam.final_project_bitm.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.app.AppConfig;
import com.example.mdjahirulislam.final_project_bitm.database.DatabaseSource;
import com.example.mdjahirulislam.final_project_bitm.databinding.ActivitySignUpBinding;
import com.example.mdjahirulislam.final_project_bitm.interfaces.ConnectionApi;
import com.example.mdjahirulislam.final_project_bitm.modelClass.RegistrationModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.RegistrationResponseModel;
import com.example.mdjahirulislam.final_project_bitm.service.SignUpService;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private ActivitySignUpBinding signUpBinding;
    private ConnectionApi connectionApi;

    private DatabaseSource databaseSource;
    private RegistrationModel registrationModel;
    private RegistrationResponseModel registrationResponseModel;




    private String userFullName;
    private String userMobile;
    private String userEmail;
    private String userPassword;

    private boolean connected;
    private Messenger messengerService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            connected = true;
            messengerService = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        signUpBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);



        connectionApi = AppConfig.getRetrofit().create(ConnectionApi.class);

    }

    public void userRegistration(View view) {

        userFullName = signUpBinding.userFullNameET.getText().toString().trim();
        userMobile = signUpBinding.userMobileET.getText().toString().trim();
        userEmail = signUpBinding.userEmailET.getText().toString().trim();
        userPassword = signUpBinding.userPasswordET.getText().toString().trim();


        if (userFullName.isEmpty()){
            signUpBinding.userFullNameET.setError("Please Enter Your Full Name");
        }else if (userMobile.isEmpty()){
            signUpBinding.userMobileET.setError("Please Enter Your Mobile Number");
        }else if (userEmail.isEmpty()){
            signUpBinding.userEmailET.setError("Please Enter Your Email");
        }else if (userPassword.isEmpty()){
            signUpBinding.userPasswordET.setError("Please Enter Your Password");
        }else {




            Message msg = Message.obtain(null,SignUpService.REGISTRATION);
            msg.replyTo = new Messenger(new ResponseHandler());

            Bundle bundle = new Bundle();
            bundle.putString("name",userFullName);
            bundle.putString("mobile",userMobile);
            bundle.putString("email",userEmail);
            bundle.putString("pass",userPassword);
            msg.setData(bundle);

            try {
                messengerService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

//            registrationModel = new RegistrationModel(userFullName,userMobile,userEmail,userPassword);
//
//            Call<RegistrationResponseModel> registrationResponseModelCall = connectionApi.getRegistrationUser(registrationModel);
//
//            registrationResponseModelCall.enqueue(new Callback<RegistrationResponseModel>() {
//                @Override
//                public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
//
//                    Log.d(TAG,"Response Code : "+response.code());
//                    Log.d(TAG,"Response Code : "+response.body().getErrorMsg());
//                    if (response.code() == 200){
//                        registrationResponseModel = response.body();
//                        boolean error = registrationResponseModel.getError();
//                        Log.d(TAG,"Error : "+error);
//                        if (!error){
//                            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
//                            startActivity(intent);
//                        }else {
//                            String error_msg = registrationResponseModel.getErrorMsg();
//                            Toast.makeText(SignUpActivity.this, error_msg, Toast.LENGTH_SHORT).show();
//                        }
//
//                    }else{
//                        Toast.makeText(SignUpActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
//
//                    Log.e(TAG,t.toString());
//                    Toast.makeText(SignUpActivity.this, "Response Not Found", Toast.LENGTH_SHORT).show();
//                }
//
//
//            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, SignUpService.class), connection,
                BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    class ResponseHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            ArrayList<RegistrationModel> registrationModelArrayList = new ArrayList<>();

            switch (msg.what) {
                case SignUpService.REGISTRATION:
                    boolean status = msg.getData().getBoolean("response");
//                    registrationModelArrayList = (ArrayList<RegistrationModel>) msg.getData().getSerializable("response");
                    if (status) {
                        Intent intent = new Intent(SignUpActivity.this,ShowPostListActivity.class);
                        Toast.makeText(SignUpActivity.this, "welcome to show post activity", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SignUpActivity.this, "error occur !! ", Toast.LENGTH_SHORT).show();

                    }
//                case REGISTRATION:
//                    sayHello();
            }
        }
    }

}
