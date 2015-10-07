package id.limakilo.www.bawang.ui.login.mvp;

import com.digits.sdk.android.DigitsSession;

/**
 * Created by walesadanto on 23/6/15.
 */
public interface LoginInteractor {
    public void callAsync(LoginListener.ListenerCaller caller, LoginListener.ListenerAction action);
    public void callAPILogin(String authentification, String appVersion);
    public void callAPIDigitLogin(DigitsSession digitsSession);
    public void callAPIFacebookLogin(String id, String firstName, String lastName, String email);

    public void callAPIGetUserData();
}

