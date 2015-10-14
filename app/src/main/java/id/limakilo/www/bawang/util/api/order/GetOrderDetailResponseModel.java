package id.limakilo.www.bawang.util.api.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 23/8/15.
 */
public class GetOrderDetailResponseModel extends RootResponseModel{
    private List<GetOrderDetailResponseData> data = new ArrayList<GetOrderDetailResponseData>();

    public List<GetOrderDetailResponseData> getData() {
        return data;
    }

    public void setData(List<GetOrderDetailResponseData> data) {
        this.data = data;
    }
    public class GetOrderDetailResponseData {

        @SerializedName("seller_id")
        @Expose
        private Integer sellerId;
        @SerializedName("seller_name")
        @Expose
        private String sellerName;
        @SerializedName("seller_city")
        @Expose
        private String sellerCity;
        @SerializedName("seller_bank_name")
        @Expose
        private String sellerBankName;
        @SerializedName("seller_bank_account")
        @Expose
        private String sellerBankAccount;
        @SerializedName("seller_bank_account_name")
        @Expose
        private String sellerBankAccountName;
        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @SerializedName("order_status")
        @Expose
        private String orderStatus;
        @SerializedName("order_quantity")
        @Expose
        private String orderQuantity;
        @SerializedName("order_amount")
        @Expose
        private String orderAmount;
        @SerializedName("order_address")
        @Expose
        private String orderAddress;
        @SerializedName("order_city")
        @Expose
        private String orderCity;
        @SerializedName("order_payment_code")
        @Expose
        private String orderPaymentCode;
        @SerializedName("order_shipment_eta")
        @Expose
        private String orderShipmentEta;
        @SerializedName("order_shipment_name")
        @Expose
        private String orderShipmentName;
        @SerializedName("order_shipment_cost")
        @Expose
        private String orderShipmentCost;
        @SerializedName("order_date")
        @Expose
        private String orderDate;
        @SerializedName("stock_id")
        @Expose
        private Integer stockId;
        @SerializedName("stock_name")
        @Expose
        private String stockName;
        @SerializedName("stock_quantity")
        @Expose
        private Float stockQuantity;
        @SerializedName("stock_price")
        @Expose
        private String stockPrice;
        @SerializedName("stock_total_order")
        @Expose
        private Integer stockTotalOrder;
        @SerializedName("stock_quota")
        @Expose
        private Integer stockQuota;
        @SerializedName("stock_available")
        @Expose
        private Integer stockAvailable;
        @SerializedName("stock_ordered")
        @Expose
        private Integer stockOrdered;


        public void setData(GetOrderResponseModel.GetOrderResponseData data){
            setOrderId(data.getOrderId());
            setOrderAddress(data.getOrderAddress());
            setOrderAmount(data.getOrderAmount().toString());
            setOrderCity(data.getOrderCity());
            setOrderDate(data.getOrderDate());
            setOrderPaymentCode(data.getOrderPaymentCode());
            setOrderQuantity(data.getOrderQuantity());
            setOrderShipmentCost(data.getOrderShipmentCost());
            setOrderShipmentEta(data.getOrderShipmentEta());
            setOrderShipmentName(data.getOrderShipmentName());
            setOrderStatus(data.getOrderStatus());
            setSellerCity(data.getSellerCity());
            setSellerId(data.getSellerId());
            setSellerName(data.getSellerName());
            setStockName(data.getStockName());
            setStockOrdered(data.getStockOrdered());
            setStockPrice(data.getStockPrice());
            setStockQuantity(data.getStockQuantity());
            setStockQuota(data.getStockQuota());
            setStockId(data.getStockId());
            setStockAvailable(data.getStockAvailable());
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
         * @return The sellerBankName
         */
        public String getSellerBankName() {
            return sellerBankName;
        }

        /**
         * @param sellerBankName The seller_bank_name
         */
        public void setSellerBankName(String sellerBankName) {
            this.sellerBankName = sellerBankName;
        }

        /**
         * @return The sellerBankAccount
         */
        public String getSellerBankAccount() {
            return sellerBankAccount;
        }

        /**
         * @param sellerBankAccount The seller_bank_account
         */
        public void setSellerBankAccount(String sellerBankAccount) {
            this.sellerBankAccount = sellerBankAccount;
        }

        /**
         * @return The sellerBankAccountName
         */
        public String getSellerBankAccountName() {
            return sellerBankAccountName;
        }

        /**
         * @param sellerBankAccountName The seller_bank_account_name
         */
        public void setSellerBankAccountName(String sellerBankAccountName) {
            this.sellerBankAccountName = sellerBankAccountName;
        }

        /**
         * @return The orderId
         */
        public Integer getOrderId() {
            return orderId;
        }

        /**
         * @param orderId The order_id
         */
        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        /**
         * @return The orderStatus
         */
        public String getOrderStatus() {
            return orderStatus;
        }

        /**
         * @param orderStatus The order_status
         */
        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        /**
         * @return The orderQuantity
         */
        public String getOrderQuantity() {
            return orderQuantity;
        }

        /**
         * @param orderQuantity The order_quantity
         */
        public void setOrderQuantity(String orderQuantity) {
            this.orderQuantity = orderQuantity;
        }

        /**
         * @return The orderAmount
         */
        public String getOrderAmount() {
            return orderAmount;
        }

        /**
         * @param orderAmount The order_amount
         */
        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        /**
         * @return The orderAddress
         */
        public String getOrderAddress() {
            return orderAddress;
        }

        /**
         * @param orderAddress The order_address
         */
        public void setOrderAddress(String orderAddress) {
            this.orderAddress = orderAddress;
        }

        /**
         * @return The orderCity
         */
        public String getOrderCity() {
            return orderCity;
        }

        /**
         * @param orderCity The order_city
         */
        public void setOrderCity(String orderCity) {
            this.orderCity = orderCity;
        }

        /**
         * @return The orderPaymentCode
         */
        public String getOrderPaymentCode() {
            return orderPaymentCode;
        }

        /**
         * @param orderPaymentCode The order_payment_code
         */
        public void setOrderPaymentCode(String orderPaymentCode) {
            this.orderPaymentCode = orderPaymentCode;
        }

        /**
         * @return The orderShipmentEta
         */
        public String getOrderShipmentEta() {
            return orderShipmentEta;
        }

        /**
         * @param orderShipmentEta The order_shipment_eta
         */
        public void setOrderShipmentEta(String orderShipmentEta) {
            this.orderShipmentEta = orderShipmentEta;
        }

        /**
         * @return The orderShipmentName
         */
        public String getOrderShipmentName() {
            return orderShipmentName;
        }

        /**
         * @param orderShipmentName The order_shipment_name
         */
        public void setOrderShipmentName(String orderShipmentName) {
            this.orderShipmentName = orderShipmentName;
        }

        /**
         * @return The orderShipmentCost
         */
        public String getOrderShipmentCost() {
            return orderShipmentCost;
        }

        /**
         * @param orderShipmentCost The order_shipment_cost
         */
        public void setOrderShipmentCost(String orderShipmentCost) {
            this.orderShipmentCost = orderShipmentCost;
        }

        /**
         * @return The orderDate
         */
        public String getOrderDate() {
            return orderDate;
        }

        /**
         * @param orderDate The order_date
         */
        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

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
        public Float getStockQuantity() {
            return stockQuantity;
        }

        /**
         * @param stockQuantity The stock_quantity
         */
        public void setStockQuantity(Float stockQuantity) {
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
         * @return The stockAvailable
         */
        public Integer getStockAvailable() {
            return stockAvailable;
        }

        /**
         * @param stockAvailable The stock_available
         */
        public void setStockAvailable(Integer stockAvailable) {
            this.stockAvailable = stockAvailable;
        }

        /**
         * @return The stockOrdered
         */
        public Integer getStockOrdered() {
            return stockOrdered;
        }

        /**
         * @param stockOrdered The stock_ordered
         */
        public void setStockOrdered(Integer stockOrdered) {
            this.stockOrdered = stockOrdered;
        }
    }
}
