package com.example.mdjahirulislam.final_project_bitm.modelClass;

/**
 * Created by mdjahirulislam on 08/06/17.
 */

public class AdsImageModel {

    private String ad_image_unique_id;
    private String post_unique_id;
    private String post_image_url;
    private String created_at;

    public AdsImageModel(String ad_image_unique_id, String post_unique_id, String post_image_url, String created_at) {
        this.ad_image_unique_id = ad_image_unique_id;
        this.post_unique_id = post_unique_id;
        this.post_image_url = post_image_url;
        this.created_at = created_at;
    }

    public String getAd_image_unique_id() {
        return ad_image_unique_id;
    }

    public String getPost_unique_id() {
        return post_unique_id;
    }

    public String getPost_image_url() {
        return post_image_url;
    }

    public String getCreated_at() {
        return created_at;
    }
}
