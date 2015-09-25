package id.limakilo.www.bawang.ui.login.mvp;

import android.os.Bundle;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerAction;
import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerCaller;
import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerCaller.DIGIT;
import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerCaller.FACEBOOK;
import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerResult.FAILURE;
import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerResult.SUCCESS;

/**
 * Created by walesadanto on 23/6/15.
 */
public class LoginInteractorImpl implements LoginInteractor  {

    public LoginListener listener;
    public AuthCallback digitCallback;

    public LoginInteractorImpl(LoginListener loginListener){
        this.listener = loginListener;
    }

    public AuthCallback getDigitCallback() {
        return new AuthCallback() {
            @Override
            public void success(DigitsSession digitsSession, String s) {
                listener.onCallback(DIGIT, SUCCESS, digitsSession);
            }

            @Override
            public void failure(DigitsException e) {
                listener.onCallback(DIGIT, FAILURE, e);
            }
        };
    }

    @Override
    public void callAsync(ListenerCaller caller, ListenerAction action) {
        switch (caller) {
            case FACEBOOK:
                callAsyncFacebook(action);
                break;
            case DIGIT:

                break;
            case LATER :

                break;
            default:
                break;
        }
    }

    public void callAsyncFacebook(ListenerAction action){
        switch (action) {
            case FACEBOOK_AUTHORIZATION:
                callFacebookAuthorization();
                break;
            default:
                break;
        }
    }

    public void callFacebookAuthorization(){
        Bundle params = new Bundle();
        params.putString("fields", "first_name,last_name");
        /* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
//                        presenter.showLoginState(LoginView.State.LOADING, LoginView.StateAction.FACEBOOK);
                        listener.onCallback(FACEBOOK, SUCCESS, response);
                    }
                }
        ).executeAsync();
    }
}
