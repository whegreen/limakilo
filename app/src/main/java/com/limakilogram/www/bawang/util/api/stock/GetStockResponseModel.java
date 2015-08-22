package com.limakilogram.www.bawang.util.api.stock;

/**
 * Created by walesadanto on 22/8/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStockResponseModel {


    public class Datum {

        @SerializedName("sto_id")
        @Expose
        private Integer stoId;
        @SerializedName("sto_user_id")
        @Expose
        private Integer stoUserId;
        @SerializedName("sto_com_id")
        @Expose
        private Integer stoComId;
        @SerializedName("sto_cat_id")
        @Expose
        private Integer stoCatId;
        @SerializedName("sto_quantity")
        @Expose
        private Integer stoQuantity;
        @SerializedName("sto_price")
        @Expose
        private String stoPrice;
        @SerializedName("sto_expired_date")
        @Expose
        private String stoExpiredDate;
        @SerializedName("sto_description")
        @Expose
        private String stoDescription;
        @SerializedName("sto_create_date")
        @Expose
        private String stoCreateDate;
        @SerializedName("com_id")
        @Expose
        private Integer comId;
        @SerializedName("com_name")
        @Expose
        private String comName;
        @SerializedName("cat_id")
        @Expose
        private Integer catId;
        @SerializedName("cat_com_id")
        @Expose
        private Integer catComId;
        @SerializedName("cat_name")
        @Expose
        private String catName;

        /**
         * @return The stoId
         */
        public Integer getStoId() {
            return stoId;
        }

        /**
         * @param stoId The sto_id
         */
        public void setStoId(Integer stoId) {
            this.stoId = stoId;
        }

        /**
         * @return The stoUserId
         */
        public Integer getStoUserId() {
            return stoUserId;
        }

        /**
         * @param stoUserId The sto_user_id
         */
        public void setStoUserId(Integer stoUserId) {
            this.stoUserId = stoUserId;
        }

        /**
         * @return The stoComId
         */
        public Integer getStoComId() {
            return stoComId;
        }

        /**
         * @param stoComId The sto_com_id
         */
        public void setStoComId(Integer stoComId) {
            this.stoComId = stoComId;
        }

        /**
         * @return The stoCatId
         */
        public Integer getStoCatId() {
            return stoCatId;
        }

        /**
         * @param stoCatId The sto_cat_id
         */
        public void setStoCatId(Integer stoCatId) {
            this.stoCatId = stoCatId;
        }

        /**
         * @return The stoQuantity
         */
        public Integer getStoQuantity() {
            return stoQuantity;
        }

        /**
         * @param stoQuantity The sto_quantity
         */
        public void setStoQuantity(Integer stoQuantity) {
            this.stoQuantity = stoQuantity;
        }

        /**
         * @return The stoPrice
         */
        public String getStoPrice() {
            return stoPrice;
        }

        /**
         * @param stoPrice The sto_price
         */
        public void setStoPrice(String stoPrice) {
            this.stoPrice = stoPrice;
        }

        /**
         * @return The stoExpiredDate
         */
        public String getStoExpiredDate() {
            return stoExpiredDate;
        }

        /**
         * @param stoExpiredDate The sto_expired_date
         */
        public void setStoExpiredDate(String stoExpiredDate) {
            this.stoExpiredDate = stoExpiredDate;
        }

        /**
         * @return The stoDescription
         */
        public String getStoDescription() {
            return stoDescription;
        }

        /**
         * @param stoDescription The sto_description
         */
        public void setStoDescription(String stoDescription) {
            this.stoDescription = stoDescription;
        }

        /**
         * @return The stoCreateDate
         */
        public String getStoCreateDate() {
            return stoCreateDate;
        }

        /**
         * @param stoCreateDate The sto_create_date
         */
        public void setStoCreateDate(String stoCreateDate) {
            this.stoCreateDate = stoCreateDate;
        }

        /**
         * @return The comId
         */
        public Integer getComId() {
            return comId;
        }

        /**
         * @param comId The com_id
         */
        public void setComId(Integer comId) {
            this.comId = comId;
        }

        /**
         * @return The comName
         */
        public String getComName() {
            return comName;
        }

        /**
         * @param comName The com_name
         */
        public void setComName(String comName) {
            this.comName = comName;
        }

        /**
         * @return The catId
         */
        public Integer getCatId() {
            return catId;
        }

        /**
         * @param catId The cat_id
         */
        public void setCatId(Integer catId) {
            this.catId = catId;
        }

        /**
         * @return The catComId
         */
        public Integer getCatComId() {
            return catComId;
        }

        /**
         * @param catComId The cat_com_id
         */
        public void setCatComId(Integer catComId) {
            this.catComId = catComId;
        }

        /**
         * @return The catName
         */
        public String getCatName() {
            return catName;
        }

        /**
         * @param catName The cat_name
         */
        public void setCatName(String catName) {
            this.catName = catName;
        }
    }

}
