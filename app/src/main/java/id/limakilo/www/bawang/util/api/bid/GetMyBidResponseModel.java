package id.limakilo.www.bawang.util.api.bid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walesadanto on 23/8/15.
 */
public class GetMyBidResponseModel {
    private List<GetMyBidResponseData> data = new ArrayList<GetMyBidResponseData>();

    public List<GetMyBidResponseData> getData() {
        return data;
    }

    public void setData(List<GetMyBidResponseData> data) {
        this.data = data;
    }
    public class GetMyBidResponseData{
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("order_id")
        @Expose
        private Integer orderId;
        @Expose
        private String date;
        @Expose
        private Integer status;

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

        /**
         *
         * @return
         * The status
         */
        public Integer getStatus() {
            return status;
        }

        /**
         *
         * @param status
         * The status
         */
        public void setStatus(Integer status) {
            this.status = status;
        }

    }
}
