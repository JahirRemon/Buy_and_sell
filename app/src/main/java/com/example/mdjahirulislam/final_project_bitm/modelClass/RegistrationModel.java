package com.example.mdjahirulislam.final_project_bitm.modelClass;

/**
 * Created by mdjahirulislam on 25/05/17.
 */

public class RegistrationModel {

    private String user_unique_id;
    private String user_full_name;
    private String user_mobile_no;
    private String user_email;
    private String password;
    private String created_at;


    public RegistrationModel(String user_full_name, String user_mobile_no, String user_email, String password) {
        this.user_full_name = user_full_name;
        this.user_mobile_no = user_mobile_no;
        this.user_email = user_email;
        this.password = password;
    }

    public RegistrationModel(String user_unique_id, String user_full_name, String user_mobile_no, String user_email, String created_at) {
        this.user_unique_id = user_unique_id;
        this.user_full_name = user_full_name;
        this.user_mobile_no = user_mobile_no;
        this.user_email = user_email;
        this.created_at = created_at;
    }

    public RegistrationModel(String user_email, String password) {
        this.user_email = user_email;
        this.password = password;
    }

    public String getUser_unique_id() {
        return user_unique_id;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getCreated_at() {
        return created_at;
    }
}
