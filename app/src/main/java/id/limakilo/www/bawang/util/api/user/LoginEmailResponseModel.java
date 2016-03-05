package id.limakilo.www.bawang.util.api.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 26/7/15.
 */
public class LoginEmailResponseModel extends RootResponseModel {

    @SerializedName("data")
    @Expose
    private List<LoginEmailResponseData> data = new ArrayList<LoginEmailResponseData>();

    /**
     *
     * @return
     * The data
     */
    public List<LoginEmailResponseData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<LoginEmailResponseData> data) {
        this.data = data;
    }

    public class LoginEmailResponseData {
        @SerializedName("usr_id")
        @Expose
        private Integer usrId;
        @SerializedName("usr_username")
        @Expose
        private String usrUsername;
        @SerializedName("usr_password")
        @Expose
        private String usrPassword;
        @SerializedName("usr_first_name")
        @Expose
        private String usrFirstName;
        @SerializedName("usr_last_name")
        @Expose
        private String usrLastName;
        @SerializedName("usr_email")
        @Expose
        private String usrEmail;
        @SerializedName("usr_image")
        @Expose
        private String usrImage;
        @SerializedName("usr_phone_number")
        @Expose
        private String usrPhoneNumber;
        @SerializedName("usr_last_update")
        @Expose
        private String usrLastUpdate;
        @SerializedName("usr_create_date")
        @Expose
        private String usrCreateDate;

        /**
         * @return The usrId
         */
        public Integer getUsrId() {
            return usrId;
        }

        /**
         * @param usrId The usr_id
         */
        public void setUsrId(Integer usrId) {
            this.usrId = usrId;
        }

        /**
         * @return The usrUsername
         */
        public String getUsrUsername() {
            return usrUsername;
        }

        /**
         * @param usrUsername The usr_username
         */
        public void setUsrUsername(String usrUsername) {
            this.usrUsername = usrUsername;
        }

        /**
         * @return The usrPassword
         */
        public String getUsrPassword() {
            return usrPassword;
        }

        /**
         * @param usrPassword The usr_password
         */
        public void setUsrPassword(String usrPassword) {
            this.usrPassword = usrPassword;
        }

        /**
         * @return The usrFirstName
         */
        public String getUsrFirstName() {
            return usrFirstName;
        }

        /**
         * @param usrFirstName The usr_first_name
         */
        public void setUsrFirstName(String usrFirstName) {
            this.usrFirstName = usrFirstName;
        }

        /**
         * @return The usrLastName
         */
        public String getUsrLastName() {
            return usrLastName;
        }

        /**
         * @param usrLastName The usr_last_name
         */
        public void setUsrLastName(String usrLastName) {
            this.usrLastName = usrLastName;
        }

        /**
         * @return The usrEmail
         */
        public String getUsrEmail() {
            return usrEmail;
        }

        /**
         * @param usrEmail The usr_email
         */
        public void setUsrEmail(String usrEmail) {
            this.usrEmail = usrEmail;
        }

        /**
         * @return The usrImage
         */
        public String getUsrImage() {
            return usrImage;
        }

        /**
         * @param usrImage The usr_image
         */
        public void setUsrImage(String usrImage) {
            this.usrImage = usrImage;
        }

        /**
         * @return The usrPhoneNumber
         */
        public String getUsrPhoneNumber() {
            return usrPhoneNumber;
        }

        /**
         * @param usrPhoneNumber The usr_phone_number
         */
        public void setUsrPhoneNumber(String usrPhoneNumber) {
            this.usrPhoneNumber = usrPhoneNumber;
        }

        /**
         * @return The usrLastUpdate
         */
        public String getUsrLastUpdate() {
            return usrLastUpdate;
        }

        /**
         * @param usrLastUpdate The usr_last_update
         */
        public void setUsrLastUpdate(String usrLastUpdate) {
            this.usrLastUpdate = usrLastUpdate;
        }

        /**
         * @return The usrCreateDate
         */
        public String getUsrCreateDate() {
            return usrCreateDate;
        }

        /**
         * @param usrCreateDate The usr_create_date
         */
        public void setUsrCreateDate(String usrCreateDate) {
            this.usrCreateDate = usrCreateDate;
        }
    }
    }
