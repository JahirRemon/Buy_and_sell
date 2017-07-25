package com.example.mdjahirulislam.final_project_bitm.modelClass;

/**
 * Created by mdjahirulislam on 27/05/17.
 */

public class AdPostModel {

    private int post_id;
    private String post_unique_id;
    private String user_unique_id;
    private String user_name;
    private String user_mobile_no;
    private String post_image_url;
    private String post_title;
    private String post_price;
    private String post_details;
    private String post_location;
    private String post_condition;
    private String post_category;
    private String post_latitude;
    private String post_longitude;
    private String created_at;


    public AdPostModel(String user_unique_id, String post_image_url, String post_title, String post_price,
                       String post_details, String post_location, String post_condition,
                       String post_category, String post_latitude, String post_longitude) {
        this.user_unique_id = user_unique_id;
        this.post_image_url = post_image_url;
        this.post_title = post_title;
        this.post_price = post_price;
        this.post_details = post_details;
        this.post_location = post_location;
        this.post_condition = post_condition;
        this.post_category = post_category;
        this.post_latitude = post_latitude;
        this.post_longitude = post_longitude;
    }

    public AdPostModel(int post_id, String user_unique_id, String post_image_url, String post_title,
                       String post_price, String post_details, String post_location, String post_condition,
                       String post_category, String post_latitude, String post_longitude) {
        this.post_id = post_id;
        this.user_unique_id = user_unique_id;
        this.post_image_url = post_image_url;
        this.post_title = post_title;
        this.post_price = post_price;
        this.post_details = post_details;
        this.post_location = post_location;
        this.post_condition = post_condition;
        this.post_category = post_category;
        this.post_latitude = post_latitude;
        this.post_longitude = post_longitude;
    }

    public AdPostModel(String post_unique_id, String user_unique_id,
                       String user_name, String user_mobile_no,
                       String post_image_url, String post_title,
                       String post_price, String post_details,
                       String post_location, String post_condition,
                       String post_category, String post_latitude,
                       String post_longitude, String created_at) {
        this.post_unique_id = post_unique_id;
        this.user_unique_id = user_unique_id;
        this.user_name = user_name;
        this.user_mobile_no = user_mobile_no;
        this.post_image_url = post_image_url;
        this.post_title = post_title;
        this.post_price = post_price;
        this.post_details = post_details;
        this.post_location = post_location;
        this.post_condition = post_condition;
        this.post_category = post_category;
        this.post_latitude = post_latitude;
        this.post_longitude = post_longitude;
        this.created_at = created_at;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getPost_unique_id() {
        return post_unique_id;
    }

    public String getUser_unique_id() {
        return user_unique_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public String getPost_image_url() {
        return post_image_url;
    }

    public String getPost_title() {
        return post_title;
    }

    public String getPost_price() {
        return post_price;
    }

    public String getPost_details() {
        return post_details;
    }

    public String getPost_location() {
        return post_location;
    }

    public String getPost_condition() {
        return post_condition;
    }

    public String getPost_category() {
        return post_category;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getPost_latitude() {
        return post_latitude;
    }

    public String getPost_longitude() {
        return post_longitude;
    }
}

