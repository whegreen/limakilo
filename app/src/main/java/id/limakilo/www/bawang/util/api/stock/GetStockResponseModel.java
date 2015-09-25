package id.limakilo.www.bawang.util.api.stock;

/**
 * Created by walesadanto on 22/8/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

public class GetStockResponseModel extends RootResponseModel{
    private List<GetStockResponseData> data = new ArrayList<GetStockResponseData>();

    public List<GetStockResponseData> getData() {
        return data;
    }

    public void setData(List<GetStockResponseData> data) {
        this.data = data;
    }

    public class GetStockResponseData {
        @SerializedName("stock_id")
        @Expose
        private Integer stockId;
        @SerializedName("stock_name")
        @Expose
        private String stockName;
        @SerializedName("stock_quantity")
        @Expose
        private Integer stockQuantity;
        @SerializedName("stock_price")
        @Expose
        private String stockPrice;
        @SerializedName("stock_commodity")
        @Expose
        private String stockCommodity;
        @SerializedName("stock_category")
        @Expose
        private String stockCategory;
        @SerializedName("seller_id")
        @Expose
        private Integer sellerId;
        @SerializedName("seller_name")
        @Expose
        private String sellerName;
        @SerializedName("seller_avatar_url")
        @Expose
        private String sellerAvatarUrl;
        @SerializedName("seller_reputation")
        @Expose
        private String sellerReputation;
        @SerializedName("seller_address")
        @Expose
        private String sellerAddress;
        @SerializedName("seller_city")
        @Expose
        private String sellerCity;
        @SerializedName("stock_total_order")
        @Expose
        private Integer stockTotalOrder;
        @SerializedName("stock_quota")
        @Expose
        private Integer stockQuota;
        @SerializedName("stock_ordered")
        @Expose
        private Object stockOrdered;

        /**
         * @return The stockId
         */
        public Integer getStockId() {
            return stockId;
        }

        /**
         * @param stockId The stock_id
         */
        public void setStockId(Integer stockId) {
            this.stockId = stockId;
        }

        /**
         * @return The stockName
         */
        public String getStockName() {
            return stockName;
        }

        /**
         * @param stockName The stock_name
         */
        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        /**
         * @return The stockQuantity
         */
        public Integer getStockQuantity() {
            return stockQuantity;
        }

        /**
         * @param stockQuantity The stock_quantity
         */
        public void setStockQuantity(Integer stockQuantity) {
            this.stockQuantity = stockQuantity;
        }

        /**
         * @return The stockPrice
         */
        public String getStockPrice() {
            return stockPrice;
        }

        /**
         * @param stockPrice The stock_price
         */
        public void setStockPrice(String stockPrice) {
            this.stockPrice = stockPrice;
        }

        /**
         * @return The stockCommodity
         */
        public String getStockCommodity() {
            return stockCommodity;
        }

        /**
         * @param stockCommodity The stock_commodity
         */
        public void setStockCommodity(String stockCommodity) {
            this.stockCommodity = stockCommodity;
        }

        /**
         * @return The stockCategory
         */
        public String getStockCategory() {
            return stockCategory;
        }

        /**
         * @param stockCategory The stock_category
         */
        public void setStockCategory(String stockCategory) {
            this.stockCategory = stockCategory;
        }

        /**
         * @return The sellerId
         */
        public Integer getSellerId() {
            return sellerId;
        }

        /**
         * @param sellerId The seller_id
         */
        public void setSellerId(Integer sellerId) {
            this.sellerId = sellerId;
        }

        /**
         * @return The sellerName
         */
        public String getSellerName() {
            return sellerName;
        }

        /**
         * @param sellerName The seller_name
         */
        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        /**
         * @return The sellerAvatarUrl
         */
        public String getSellerAvatarUrl() {
            return sellerAvatarUrl;
        }

        /**
         * @param sellerAvatarUrl The seller_avatar_url
         */
        public void setSellerAvatarUrl(String sellerAvatarUrl) {
            this.sellerAvatarUrl = sellerAvatarUrl;
        }

        /**
         * @return The sellerReputation
         */
        public String getSellerReputation() {
            return sellerReputation;
        }

        /**
         * @param sellerReputation The seller_reputation
         */
        public void setSellerReputation(String sellerReputation) {
            this.sellerReputation = sellerReputation;
        }

        /**
         * @return The sellerAddress
         */
        public String getSellerAddress() {
            return sellerAddress;
        }

        /**
         * @param sellerAddress The seller_address
         */
        public void setSellerAddress(String sellerAddress) {
            this.sellerAddress = sellerAddress;
        }

        /**
         * @return The sellerCity
         */
        public String getSellerCity() {
            return sellerCity;
        }

        /**
         * @param sellerCity The seller_city
         */
        public void setSellerCity(String sellerCity) {
            this.sellerCity = sellerCity;
        }

        /**
         * @return The stockTotalOrder
         */
        public Integer getStockTotalOrder() {
            return stockTotalOrder;
        }

        /**
         * @param stockTotalOrder The stock_total_order
         */
        public void setStockTotalOrder(Integer stockTotalOrder) {
            this.stockTotalOrder = stockTotalOrder;
        }

        /**
         * @return The stockQuota
         */
        public Integer getStockQuota() {
            return stockQuota;
        }

        /**
         * @param stockQuota The stock_quota
         */
        public void setStockQuota(Integer stockQuota) {
            this.stockQuota = stockQuota;
        }

        /**
         * @return The stockOrdered
         */
        public Object getStockOrdered() {
            return stockOrdered;
        }

        /**
         * @param stockOrdered The stock_ordered
         */
        public void setStockOrdered(Object stockOrdered) {
            this.stockOrdered = stockOrdered;
        }
    }
}
