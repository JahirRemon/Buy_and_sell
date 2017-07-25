package com.example.mdjahirulislam.final_project_bitm.pojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 26/05/17.
 */

public class LoginResponseModel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("post_exists")
    @Expose
    private Boolean postExists;
    @SerializedName("post_msg")
    @Expose
    private String postMsg;
    @SerializedName("post")
    @Expose
    private List<Post> post = null;
    @SerializedName("ad_image_exists")
    @Expose
    private Boolean adImageExists;
    @SerializedName("ad_image_msg")
    @Expose
    private String adImageMsg;
    @SerializedName("ad_image")
    @Expose
    private List<AdImage> adImage = null;
    @SerializedName("favourite_ads_exists")
    @Expose
    private Boolean favouriteAdsExists;
    @SerializedName("favourite_ads_msg")
    @Expose
    private String favouriteAdsMsg;
    @SerializedName("ad_favourite")
    @Expose
    private List<AdFavourite> adFavourite = null;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getPostExists() {
        return postExists;
    }

    public void setPostExists(Boolean postExists) {
        this.postExists = postExists;
    }

    public String getPostMsg() {
        return postMsg;
    }

    public void setPostMsg(String postMsg) {
        this.postMsg = postMsg;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public Boolean getAdImageExists() {
        return adImageExists;
    }

    public void setAdImageExists(Boolean adImageExists) {
        this.adImageExists = adImageExists;
    }

    public String getAdImageMsg() {
        return adImageMsg;
    }

    public void setAdImageMsg(String adImageMsg) {
        this.adImageMsg = adImageMsg;
    }

    public List<AdImage> getAdImage() {
        return adImage;
    }

    public void setAdImage(List<AdImage> adImage) {
        this.adImage = adImage;
    }

    public Boolean getFavouriteAdsExists() {
        return favouriteAdsExists;
    }

    public void setFavouriteAdsExists(Boolean favouriteAdsExists) {
        this.favouriteAdsExists = favouriteAdsExists;
    }

    public String getFavouriteAdsMsg() {
        return favouriteAdsMsg;
    }

    public void setFavouriteAdsMsg(String favouriteAdsMsg) {
        this.favouriteAdsMsg = favouriteAdsMsg;
    }

    public List<AdFavourite> getAdFavourite() {
        return adFavourite;
    }

    public void setAdFavourite(List<AdFavourite> adFavourite) {
        this.adFavourite = adFavourite;
    }

    public static class User {

        @SerializedName("user_unique_id")
        @Expose
        private String userUniqueId;
        @SerializedName("user_full_name")
        @Expose
        private String userFullName;
        @SerializedName("user_mobile_no")
        @Expose
        private String userMobileNo;
        @SerializedName("user_email")
        @Expose
        private String userEmail;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private Object updatedAt;

        public String getUserUniqueId() {
            return userUniqueId;
        }

        public void setUserUniqueId(String userUniqueId) {
            this.userUniqueId = userUniqueId;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public void setUserFullName(String userFullName) {
            this.userFullName = userFullName;
        }

        public String getUserMobileNo() {
            return userMobileNo;
        }

        public void setUserMobileNo(String userMobileNo) {
            this.userMobileNo = userMobileNo;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
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


    public static class Post {

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

    public static class AdImage {

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


    public static class AdFavourite {

        @SerializedName("favourite_unique_id")
        @Expose
        private String favouriteUniqueId;
        @SerializedName("user_unique_id")
        @Expose
        private String userUniqueId;
        @SerializedName("post_unique_id")
        @Expose
        private String postUniqueId;
        @SerializedName("favourite_status")
        @Expose
        private String favouriteStatus;
        @SerializedName("created_at")
        @Expose
        private String createdAt;

        public String getFavouriteUniqueId() {
            return favouriteUniqueId;
        }

        public void setFavouriteUniqueId(String favouriteUniqueId) {
            this.favouriteUniqueId = favouriteUniqueId;
        }

        public String getUserUniqueId() {
            return userUniqueId;
        }

        public void setUserUniqueId(String userUniqueId) {
            this.userUniqueId = userUniqueId;
        }

        public String getPostUniqueId() {
            return postUniqueId;
        }

        public void setPostUniqueId(String postUniqueId) {
            this.postUniqueId = postUniqueId;
        }

        public String getFavouriteStatus() {
            return favouriteStatus;
        }

        public void setFavouriteStatus(String favouriteStatus) {
            this.favouriteStatus = favouriteStatus;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

    }

}
