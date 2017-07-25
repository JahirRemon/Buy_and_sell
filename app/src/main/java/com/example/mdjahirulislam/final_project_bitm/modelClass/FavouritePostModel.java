package com.example.mdjahirulislam.final_project_bitm.modelClass;

/**
 * Created by mdjahirulislam on 13/06/17.
 */

public class FavouritePostModel {
    private String favourite_unique_id;
    private String user_unique_id;
    private String post_unique_id;
    private String favourite_status;
    private String created_at;
    private String updated_at;

    public FavouritePostModel(String favourite_unique_id, String user_unique_id, String post_unique_id, String favourite_status, String created_at) {
        this.favourite_unique_id = favourite_unique_id;
        this.user_unique_id = user_unique_id;
        this.post_unique_id = post_unique_id;
        this.favourite_status = favourite_status;
        this.created_at = created_at;
    }

    public String getFavourite_unique_id() {
        return favourite_unique_id;
    }

    public String getUser_unique_id() {
        return user_unique_id;
    }

    public String getPost_unique_id() {
        return post_unique_id;
    }

    public String getFavourite_status() {
        return favourite_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
