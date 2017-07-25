package com.example.mdjahirulislam.final_project_bitm.pojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 13/06/17.
 */

public class FavouriteResponseModel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
    @SerializedName("favourite_ads_exists")
    @Expose
    private Boolean favouriteAdsExists;
    @SerializedName("favourite")
    @Expose
    private List<Favourite> favourite = null;

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

    public Boolean getFavouriteAdsExists() {
        return favouriteAdsExists;
    }

    public void setFavouriteAdsExists(Boolean favouriteAdsExists) {
        this.favouriteAdsExists = favouriteAdsExists;
    }

    public List<Favourite> getFavourite() {
        return favourite;
    }

    public void setFavourite(List<Favourite> favourite) {
        this.favourite = favourite;
    }


    public static class Favourite {

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
