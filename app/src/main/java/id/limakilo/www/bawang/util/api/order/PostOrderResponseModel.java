package id.limakilo.www.bawang.util.api.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 23/8/15.
 */
public class PostOrderResponseModel extends RootResponseModel{
    private List<PostOrderResponseData> data = new ArrayList<PostOrderResponseData>();

    public List<PostOrderResponseData> getData() {
        return data;
    }

    public void setData(List<PostOrderResponseData> data) {
        this.data = data;
    }
    public class PostOrderResponseData{

        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @SerializedName("order_quantity")
        @Expose
        private String orderQuantity;
        @SerializedName("order_amount")
        @Expose
        private String orderAmount;
        @SerializedName("order_status")
        @Expose
        private String orderStatus;
        @SerializedName("order_timestamp")
        @Expose
        private String orderTimestamp;
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

        /**
         *
         * @return
         * The orderId
         */
        public Integer getOrderId() {
            return orderId;
        }

        /**
         *
         * @param orderId
         * The order_id
         */
        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        /**
         *
         * @return
         * The orderQuantity
         */
        public String getOrderQuantity() {
            return orderQuantity;
        }

        /**
         *
         * @param orderQuantity
         * The order_quantity
         */
        public void setOrderQuantity(String orderQuantity) {
            this.orderQuantity = orderQuantity;
        }

        /**
         *
         * @return
         * The orderAmount
         */
        public String getOrderAmount() {
            return orderAmount;
        }

        /**
         *
         * @param orderAmount
         * The order_amount
         */
        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        /**
         *
         * @return
         * The orderStatus
         */
        public String getOrderStatus() {
            return orderStatus;
        }

        /**
         *
         * @param orderStatus
         * The order_status
         */
        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        /**
         *
         * @return
         * The orderTimestamp
         */
        public String getOrderTimestamp() {
            return orderTimestamp;
        }

        /**
         *
         * @param orderTimestamp
         * The order_timestamp
         */
        public void setOrderTimestamp(String orderTimestamp) {
            this.orderTimestamp = orderTimestamp;
        }

        /**
         *
         * @return
         * The orderPaymentCode
         */
        public String getOrderPaymentCode() {
            return orderPaymentCode;
        }

        /**
         *
         * @param orderPaymentCode
         * The order_payment_code
         */
        public void setOrderPaymentCode(String orderPaymentCode) {
            this.orderPaymentCode = orderPaymentCode;
        }

        /**
         *
         * @return
         * The orderShipmentEta
         */
        public String getOrderShipmentEta() {
            return orderShipmentEta;
        }

        /**
         *
         * @param orderShipmentEta
         * The order_shipment_eta
         */
        public void setOrderShipmentEta(String orderShipmentEta) {
            this.orderShipmentEta = orderShipmentEta;
        }

        /**
         *
         * @return
         * The orderShipmentName
         */
        public String getOrderShipmentName() {
            return orderShipmentName;
        }

        /**
         *
         * @param orderShipmentName
         * The order_shipment_name
         */
        public void setOrderShipmentName(String orderShipmentName) {
            this.orderShipmentName = orderShipmentName;
        }

        /**
         *
         * @return
         * The orderShipmentCost
         */
        public String getOrderShipmentCost() {
            return orderShipmentCost;
        }

        /**
         *
         * @param orderShipmentCost
         * The order_shipment_cost
         */
        public void setOrderShipmentCost(String orderShipmentCost) {
            this.orderShipmentCost = orderShipmentCost;
        }

    }
}
