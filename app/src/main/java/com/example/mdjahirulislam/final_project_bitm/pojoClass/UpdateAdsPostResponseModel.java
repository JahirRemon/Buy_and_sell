package com.example.mdjahirulislam.final_project_bitm.pojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 09/06/17.
 */

public class UpdateAdsPostResponseModel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
    @SerializedName("update_ads")
    @Expose
    private List<UpdateAd> updateAds = null;
    @SerializedName("update_ad_image_exists")
    @Expose
    private Boolean updateAdImageExists;
    @SerializedName("update_ad_image_msg")
    @Expose
    private String updateAdImageMsg;
    @SerializedName("update_ad_image")
    @Expose
    private List<UpdateAdImage> updateAdImage = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<UpdateAd> getUpdateAds() {
        return updateAds;
    }

    public void setUpdateAds(List<UpdateAd> updateAds) {
        this.updateAds = updateAds;
    }

    public Boolean getUpdateAdImageExists() {
        return updateAdImageExists;
    }

    public void setUpdateAdImageExists(Boolean updateAdImageExists) {
        this.updateAdImageExists = updateAdImageExists;
    }

    public String getUpdateAdImageMsg() {
        return updateAdImageMsg;
    }

    public void setUpdateAdImageMsg(String updateAdImageMsg) {
        this.updateAdImageMsg = updateAdImageMsg;
    }

    public List<UpdateAdImage> getUpdateAdImage() {
        return updateAdImage;
    }

    public void setUpdateAdImage(List<UpdateAdImage> updateAdImage) {
        this.updateAdImage = updateAdImage;
    }

    public static class UpdateAd {

        @SerializedName("post_unique_id")
        @Expose
        private String postUniqueId;
        @SerializedName("user_unique_id")
        @Expose
        private String userUniqueId;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("user_mobile_no")
        @Expose
        private String userMobileNo;
        @SerializedName("post_image_url")
        @Expose
        private String postImageUrl;
        @SerializedName("post_title")
        @Expose
        private String postTitle;
        @SerializedName("post_price")
        @Expose
        private String postPrice;
        @SerializedName("post_details")
        @Expose
        private String postDetails;
        @SerializedName("post_location")
        @Expose
        private String postLocation;
        @SerializedName("post_condition")
        @Expose
        private String postCondition;
        @SerializedName("post_category")
        @Expose
        private String postCategory;
        @SerializedName("post_latitude")
        @Expose
        private String postLatitude;
        @SerializedName("post_longitude")
        @Expose
        private String postLongitude;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private Object updatedAt;

        public String getPostUniqueId() {
            return postUniqueId;
        }

        public void setPostUniqueId(String postUniqueId) {
            this.postUniqueId = postUniqueId;
        }

        public String getUserUniqueId() {
            return userUniqueId;
        }

        public void setUserUniqueId(String userUniqueId) {
            this.userUniqueId = userUniqueId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserMobileNo() {
            return userMobileNo;
        }

        public void setUserMobileNo(String userMobileNo) {
            this.userMobileNo = userMobileNo;
        }

        public String getPostImageUrl() {
            return postImageUrl;
        }

        public void setPostImageUrl(String postImageUrl) {
            this.postImageUrl = postImageUrl;
        }

        public String getPostTitle() {
            return postTitle;
        }

        public void setPostTitle(String postTitle) {
            this.postTitle = postTitle;
        }

        public String getPostPrice() {
            return postPrice;
        }

        public void setPostPrice(String postPrice) {
            this.postPrice = postPrice;
        }

        public String getPostDetails() {
            return postDetails;
        }

        public void setPostDetails(String postDetails) {
            this.postDetails = postDetails;
        }

        public String getPostLocation() {
            return postLocation;
        }

        public void setPostLocation(String postLocation) {
            this.postLocation = postLocation;
        }

        public String getPostCondition() {
            return postCondition;
        }

        public void setPostCondition(String postCondition) {
            this.postCondition = postCondition;
        }

        public String getPostCategory() {
            return postCategory;
        }

        public void setPostCategory(String postCategory) {
            this.postCategory = postCategory;
        }

        public String getPostLatitude() {
            return postLatitude;
        }

        public void setPostLatitude(String postLatitude) {
            this.postLatitude = postLatitude;
        }

        public String getPostLongitude() {
            return postLongitude;
        }

        public void setPostLongitude(String postLongitude) {
            this.postLongitude = postLongitude;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

    public static class UpdateAdImage {

        @SerializedName("ad_image_unique_id")
        @Expose
        private String adImageUniqueId;
        @SerializedName("post_unique_id")
        @Expose
        private String postUniqueId;
        @SerializedName("post_image_url")
        @Expose
        private String postImageUrl;
        @SerializedName("created_at")
        @Expose
        private String createdAt;

        public String getAdImageUniqueId() {
            return adImageUniqueId;
        }

        public void setAdImageUniqueId(String adImageUniqueId) {
            this.adImageUniqueId = adImageUniqueId;
        }

        public String getPostUniqueId() {
            return postUniqueId;
        }

        public void setPostUniqueId(String postUniqueId) {
            this.postUniqueId = postUniqueId;
        }

        public String getPostImageUrl() {
            return postImageUrl;
        }

        public void setPostImageUrl(String postImageUrl) {
            this.postImageUrl = postImageUrl;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

    }
}
