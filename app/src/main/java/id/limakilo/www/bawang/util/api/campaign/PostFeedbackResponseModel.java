package id.limakilo.www.bawang.util.api.campaign;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 11/9/15.
 */
public class PostFeedbackResponseModel extends RootResponseModel{

    @SerializedName("Error")
    @Expose
    private Boolean Error;
    @SerializedName("Message")
    @Expose
    private String Message;
    @SerializedName("data")
    @Expose
    private List<PostFeedbackResponseData> data = new ArrayList<PostFeedbackResponseData>();

    /**
     *
     * @return
     * The Error
     */
    public Boolean getError() {
        return Error;
    }

    /**
     *
     * @param Error
     * The Error
     */
    public void setError(Boolean Error) {
        this.Error = Error;
    }

    /**
     *
     * @return
     * The Message
     */
    public String getMessage() {
        return Message;
    }

    /**
     *
     * @param Message
     * The Message
     */
    public void setMessage(String Message) {
        this.Message = Message;
    }

    /**
     *
     * @return
     * The data
     */
    public List<PostFeedbackResponseData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<PostFeedbackResponseData> data) {
        this.data = data;
    }

    public class PostFeedbackResponseData {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("date")
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
         * The message
         */
        public String getMessage() {
            return message;
        }

        /**
         *
         * @param message
         * The message
         */
        public void setMessage(String message) {
            this.message = message;
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
