package com.example.mdjahirulislam.final_project_bitm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.mdjahirulislam.final_project_bitm.activity.LoginActivity;
import com.example.mdjahirulislam.final_project_bitm.activity.SignUpActivity;
import com.example.mdjahirulislam.final_project_bitm.app.AppConfig;
import com.example.mdjahirulislam.final_project_bitm.interfaces.ConnectionApi;
import com.example.mdjahirulislam.final_project_bitm.modelClass.RegistrationModel;
import com.example.mdjahirulislam.final_project_bitm.pojoClass.RegistrationResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpService extends Service {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    public static final int REGISTRATION = 1;
    public static final int LOGIN = 2;
    private RegistrationModel registrationModel;
    private RegistrationResponseModel registrationResponseModel;
    private ConnectionApi connectionApi;

//    Handler messengerHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case REGISTRATION:
//                    sayHello();
//            }
//        }
//    };


    @Override
    public void onCreate() {
        super.onCreate();
        connectionApi = AppConfig.getRetrofit().create(ConnectionApi.class);
        Toast.makeText(this, "SignUp service Start", Toast.LENGTH_SHORT).show();

    }

    class MessageHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            int msgType = msg.what;

            switch (msgType){
                case REGISTRATION:

                    registrationModel = new RegistrationModel(
                            msg.getData().getString("name"),
                            msg.getData().getString("mobile"),
                            msg.getData().getString("email"),
                            msg.getData().getString("pass"));
                    boolean status = Registration(registrationModel);


                    if (!status){
                        Toast.makeText(SignUpService.this, "Registration Successful. ", Toast.LENGTH_SHORT).show();

//                        ArrayList<RegistrationModel> registrationModelArrayList = new ArrayList<>();
//                        registrationModelArrayList.add(registrationModel);
                        Message respMsg = Message.obtain(null,REGISTRATION);
                        Bundle respBundle = new Bundle();
//                        respBundle.putSerializable("response",registrationModelArrayList);
                        respBundle.putBoolean("response",status);
                        respMsg.setData(respBundle);
                        try {
                            msg.replyTo.send(respMsg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }else {
                        Toast.makeText(SignUpService.this, "Registration Not Successful. ", Toast.LENGTH_SHORT).show();

                    }
                    break;
                case LOGIN:
                    Toast.makeText(SignUpService.this, "Login Service", Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    }

    Messenger messenger = new Messenger(new MessageHandler());

    public SignUpService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
       return messenger.getBinder();
    }


//    private void sayHello(RegistrationModel registrationModel) {
//        Toast.makeText(SignUpService.this, "Hello All, Bye All!", Toast.LENGTH_SHORT).show();
//    }

    private boolean Registration (RegistrationModel registrationModel){
        final boolean[] status = new boolean[1];
//        registrationModel = new RegistrationModel(userFullName,userMobile,userEmail,userPassword);

            Call<RegistrationResponseModel> registrationResponseModelCall = connectionApi.getRegistrationUser(registrationModel);

            registrationResponseModelCall.enqueue(new Callback<RegistrationResponseModel>() {
                @Override
                public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {

                    Log.d(TAG,"Response Code : "+response.code());
                    Log.d(TAG,"Response Code : "+response.body().getErrorMsg());
                    if (response.code() == 200){
                        registrationResponseModel = response.body();
                        boolean error = registrationResponseModel.getError();
                        Log.d(TAG,"Error : "+error);
                        if (!error){
                            Intent intent = new Intent(SignUpService.this,LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            status[0] = true;
                        }else {
                            String error_msg = registrationResponseModel.getErrorMsg();
                            Toast.makeText(SignUpService.this, error_msg, Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(SignUpService.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {

                    status[0] = false;
                    Log.e(TAG,t.toString());
                    Toast.makeText(SignUpService.this, "Response Not Found", Toast.LENGTH_SHORT).show();
                }



            });
        return status[0];
    }
}
