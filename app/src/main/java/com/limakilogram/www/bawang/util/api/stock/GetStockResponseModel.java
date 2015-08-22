package com.limakilogram.www.bawang.util.api.stock;

/**
 * Created by walesadanto on 22/8/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetStockResponseModel {
    private List<GetStockResponseData> data = new ArrayList<GetStockResponseData>();

    public List<GetStockResponseData> getData() {
        return data;
    }

    public void setData(List<GetStockResponseData> data) {
        this.data = data;
    }

    public class GetStockResponseData {
        @Expose
        private Integer id;
        @Expose
        private Integer stock;
        @Expose
        private String price;
        @Expose
        private String description;
        @SerializedName("create_date")
        @Expose
        private String createDate;
        @Expose
        private String commodity;
        @Expose
        private String category;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("ava_url")
        @Expose
        private String avaUrl;

        /**
         * @return The id
         */
        public Integer getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * @return The stock
         */
        public Integer getStock() {
            return stock;
        }

        /**
         * @param stock The stock
         */
        public void setStock(Integer stock) {
            this.stock = stock;
        }

        /**
         * @return The price
         */
        public String getPrice() {
            return price;
        }

        /**
         * @param price The price
         */
        public void setPrice(String price) {
            this.price = price;
        }

        /**
         * @return The description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description The description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * @return The createDate
         */
        public String getCreateDate() {
            return createDate;
        }

        /**
         * @param createDate The create_date
         */
        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        /**
         * @return The commodity
         */
        public String getCommodity() {
            return commodity;
        }

        /**
         * @param commodity The commodity
         */
        public void setCommodity(String commodity) {
            this.commodity = commodity;
        }

        /**
         * @return The category
         */
        public String getCategory() {
            return category;
        }

        /**
         * @param category The category
         */
        public void setCategory(String category) {
            this.category = category;
        }

        /**
         * @return The firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * @param firstName The first_name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * @return The lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * @param lastName The last_name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         * @return The avaUrl
         */
        public String getAvaUrl() {
            return avaUrl;
        }

        /**
         * @param avaUrl The ava_url
         */
        public void setAvaUrl(String avaUrl) {
            this.avaUrl = avaUrl;
        }
    }
}
