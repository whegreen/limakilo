package com.limakilogram.www.bawang.util.api.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walesadanto on 23/8/15.
 */
public class GetOrderDetailResponseModel {
    private List<GetOrderDetailResponseData> data = new ArrayList<GetOrderDetailResponseData>();

    public List<GetOrderDetailResponseData> getData() {
        return data;
    }

    public void setData(List<GetOrderDetailResponseData> data) {
        this.data = data;
    }
    public class GetOrderDetailResponseData {
        @Expose
        private Integer id;
        @SerializedName("commodity_id")
        @Expose
        private Integer commodityId;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("stock_id")
        @Expose
        private Integer stockId;
        @Expose
        private String quantity;
        @Expose
        private String price;
        @Expose
        private String status;
        @Expose
        private Object type;
        @Expose
        private String date;

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
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The commodityId
         */
        public Integer getCommodityId() {
            return commodityId;
        }

        /**
         *
         * @param commodityId
         * The commodity_id
         */
        public void setCommodityId(Integer commodityId) {
            this.commodityId = commodityId;
        }

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
         * The stockId
         */
        public Integer getStockId() {
            return stockId;
        }

        /**
         *
         * @param stockId
         * The stock_id
         */
        public void setStockId(Integer stockId) {
            this.stockId = stockId;
        }

        /**
         *
         * @return
         * The quantity
         */
        public String getQuantity() {
            return quantity;
        }

        /**
         *
         * @param quantity
         * The quantity
         */
        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        /**
         *
         * @return
         * The price
         */
        public String getPrice() {
            return price;
        }

        /**
         *
         * @param price
         * The price
         */
        public void setPrice(String price) {
            this.price = price;
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
         * The type
         */
        public Object getType() {
            return type;
        }

        /**
         *
         * @param type
         * The type
         */
        public void setType(Object type) {
            this.type = type;
        }

        /**
         *
         * @return
         * The date
         */
        public String getDate() {
            return date;
        }

        /**
         *
         * @param date
         * The date
         */
        public void setDate(String date) {
            this.date = date;
        }

    }
}
