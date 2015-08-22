package com.limakilogram.www.bawang.util.api.user;

import com.limakilogram.www.bawang.util.api.RootResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walesadanto on 26/7/15.
 */
public class LoginResponseModel extends RootResponseModel {

    private List<LoginResponseData> data = new ArrayList<LoginResponseData>();

    public List<LoginResponseData> getData() {
        return data;
    }

    public void setData(List<LoginResponseData> data) {
        this.data = data;
    }

    public class LoginResponseData {
        private Integer id;
        private String auth;
        private String first_name;
        private String last_name;

        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The user_id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The auth
         */
        public String getAuth() {
            return auth;
        }

        /**
         *
         * @param auth
         * The auth
         */
        public void setAuth(String auth) {
            this.auth = auth;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }
}
