package id.limakilo.www.bawang.util.api.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 13/9/15.
 */
public class GetUserResponseModel extends RootResponseModel {

    private List<GetUserResponseData> data = new ArrayList<GetUserResponseData>();

    public List<GetUserResponseData> getData() {
        return data;
    }

    public void setData(List<GetUserResponseData> data) {
        this.data = data;
    }

    public class GetUserResponseData {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
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
        private String userCoverUrl;
        @SerializedName("user_address")
        @Expose
        private String userAddress;
        @SerializedName("user_email")
        @Expose
        private String userEmail;
        @SerializedName("user_phone")
        @Expose
        private String userPhone;

        /**
         *
         * @return
         * The userId
         */
        public Integer getUserId() {
            return userId;
        }

        /**
         *
         * @param userId
         * The user_id
         */
        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        /**
         *
         * @return
         * The userFirstName
         */
        public String getUserFirstName() {
            return userFirstName;
        }

        /**
         *
         * @param userFirstName
         * The user_first_name
         */
        public void setUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
        }

        /**
         *
         * @return
         * The userLastName
         */
        public String getUserLastName() {
            return userLastName;
        }

        /**
         *
         * @param userLastName
         * The user_last_name
         */
        public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

        /**
         *
         * @return
         * The userAvatarUrl
         */
        public String getUserAvatarUrl() {
            return userAvatarUrl;
        }

        /**
         *
         * @param userAvatarUrl
         * The user_avatar_url
         */
        public void setUserAvatarUrl(String userAvatarUrl) {
            this.userAvatarUrl = userAvatarUrl;
        }

        /**
         *
         * @return
         * The userCoverUrl
         */
        public String getUserCoverUrl() {
            return userCoverUrl;
        }

        /**
         *
         * @param userCoverUrl
         * The user_cover_url
         */
        public void setUserCoverUrl(String userCoverUrl) {
            this.userCoverUrl = userCoverUrl;
        }

        /**
         *
         * @return
         * The userAddress
         */
        public String getUserAddress() {
            return userAddress;
        }

        /**
         *
         * @param userAddress
         * The user_address
         */
        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        /**
         *
         * @return
         * The userEmail
         */
        public String getUserEmail() {
            return userEmail;
        }

        /**
         *
         * @param userEmail
         * The user_email
         */
        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        /**
         *
         * @return
         * The userPhone
         */
        public String getUserPhone() {
            return userPhone;
        }

        /**
         *
         * @param userPhone
         * The user_phone
         */
        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

    }
}
