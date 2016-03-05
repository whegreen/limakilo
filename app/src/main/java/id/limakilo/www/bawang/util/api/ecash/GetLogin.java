package id.limakilo.www.bawang.util.api.ecash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 2/26/16.
 */
public class GetLogin  extends RootResponseModel {


        @SerializedName("msisdn")
        @Expose
        private String msisdn;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("token")
        @Expose
        private String token;

        /**
         *
         * @return
         * The msisdn
         */
        public String getMsisdn() {
            return msisdn;
        }

        /**
         *
         * @param msisdn
         * The msisdn
         */
        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }

        /**
         *
         * @return
         * The status
         */
        public String getStatus() {
            return status;
        }

        /**
         *
         * @param status
         * The status
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         *
         * @return
         * The token
         */
        public String getToken() {
            return token;
        }

        /**
         *
         * @param token
         * The token
         */
        public void setToken(String token) {
            this.token = token;
        }

}
