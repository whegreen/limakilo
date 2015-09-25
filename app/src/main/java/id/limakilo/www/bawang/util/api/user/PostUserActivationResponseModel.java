package id.limakilo.www.bawang.util.api.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 13/9/15.
 */
public class PostUserActivationResponseModel extends RootResponseModel {

    private List<PostUserActivationResponseData> data = new ArrayList<PostUserActivationResponseData>();

    public List<PostUserActivationResponseData> getData() {
        return data;
    }

    public void setData(List<PostUserActivationResponseData> data) {
        this.data = data;
    }

    public class PostUserActivationResponseData {
        @SerializedName("activated")
        @Expose
        private Boolean activated;

        /**
         *
         * @return
         * The activated
         */
        public Boolean getActivated() {
            return activated;
        }

        /**
         *
         * @param activated
         * The activated
         */
        public void setActivated(Boolean activated) {
            this.activated = activated;
        }

    }
}
