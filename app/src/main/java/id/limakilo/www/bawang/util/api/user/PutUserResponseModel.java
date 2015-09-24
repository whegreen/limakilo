package id.limakilo.www.bawang.util.api.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 13/9/15.
 */
public class PutUserResponseModel extends RootResponseModel {

    private List<PutUserResponseData> data = new ArrayList<PutUserResponseData>();

    public List<PutUserResponseData> getData() {
        return data;
    }

    public void setData(List<PutUserResponseData> data) {
        this.data = data;
    }

    public class PutUserResponseData {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("user_last_update")
        @Expose
        private String userLastUpdate;
        @SerializedName("user_first_name")
        @Expose
        private String userFirstName;
        @SerializedName("user_last_name")
        @Expose
        private String userLastName;
        @SerializedName("user_avatar_url")
        @Expose
        private String userAvatarUrl;
        @SerializedName("user_cover_url")
        @Expose
        private Object userCoverUrl;
        @SerializedName("user_address")
        @Expose
        private String userAddress;
        @SerializedName("user_email")
        @Expose
        private String userEmail;
        @SerializedName("user_phone")
        @Expose
        private String userPhone;
        @SerializedName("user_city")
        @Expose
        private String userCity;

        /**
         * @return The userId
         */
        public Integer getUserId() {
            return userId;
        }

        /**
         * @param userId The user_id
         */
        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        /**
         * @return The userLastUpdate
         */
        public String getUserLastUpdate() {
            return userLastUpdate;
        }

        /**
         * @param userLastUpdate The user_last_update
         */
        public void setUserLastUpdate(String userLastUpdate) {
            this.userLastUpdate = userLastUpdate;
        }

        /**
         * @return The userFirstName
         */
        public String getUserFirstName() {
            return userFirstName;
        }

        /**
         * @param userFirstName The user_first_name
         */
        public void setUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
        }

        /**
         * @return The userLastName
         */
        public String getUserLastName() {
            return userLastName;
        }

        /**
         * @param userLastName The user_last_name
         */
        public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

        /**
         * @return The userAvatarUrl
         */
        public String getUserAvatarUrl() {
            return userAvatarUrl;
        }

        /**
         * @param userAvatarUrl The user_avatar_url
         */
        public void setUserAvatarUrl(String userAvatarUrl) {
            this.userAvatarUrl = userAvatarUrl;
        }

        /**
         * @return The userCoverUrl
         */
        public Object getUserCoverUrl() {
            return userCoverUrl;
        }

        /**
         * @param userCoverUrl The user_cover_url
         */
        public void setUserCoverUrl(Object userCoverUrl) {
            this.userCoverUrl = userCoverUrl;
        }

        /**
         * @return The userAddress
         */
        public String getUserAddress() {
            return userAddress;
        }

        /**
         * @param userAddress The user_address
         */
        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        /**
         * @return The userEmail
         */
        public String getUserEmail() {
            return userEmail;
        }

        /**
         * @param userEmail The user_email
         */
        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        /**
         * @return The userPhone
         */
        public String getUserPhone() {
            return userPhone;
        }

        /**
         * @param userPhone The user_phone
         */
        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        /**
         * @return The userCity
         */
        public String getUserCity() {
            return userCity;
        }

        /**
         * @param userCity The user_city
         */
        public void setUserCity(String userCity) {
            this.userCity = userCity;
        }
    }
}
