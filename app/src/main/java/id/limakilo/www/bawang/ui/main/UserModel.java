package id.limakilo.www.bawang.ui.main;

import com.google.gson.Gson;

/**
 * Created by walesadanto on 13/9/15.
 */
public class UserModel {
    private String userId;
    private String userName;
    private String userAddress;
    private String userHandphone;
    private String userAvatarUrl;
    private String userBackgroundUrl;

//        new Gson().fromJson(json, UserModel.class);

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserHandphone() {
        return userHandphone;
    }

    public void setUserHandphone(String userHandphone) {
        this.userHandphone = userHandphone;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getUserBackgroundUrl() {
        return userBackgroundUrl;
    }

    public void setUserBackgroundUrl(String userBackgroundUrl) {
        this.userBackgroundUrl = userBackgroundUrl;
    }
}
