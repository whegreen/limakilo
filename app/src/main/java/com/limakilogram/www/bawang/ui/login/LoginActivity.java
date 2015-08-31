package com.limakilogram.www.bawang.ui.login;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.limakilogram.www.bawang.R;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends FragmentActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "QzDqgpPWp2eicthl2PjsC6Naw";
    private static final String TWITTER_SECRET = "MfjqRGnHLG9TxR6BT3Raeqqn0chCvYe0i0JU86EHMBd1I5QaaA";

    private String TAG = "LoginActivity";
    private CallbackManager callbackManager;

    private AuthCallback twitterAuthCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        twitter and crashlytic stuffs
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), new Digits());

        //facebook stuffs
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        //set layout to fragment
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!FacebookSdk.isInitialized()){
            FacebookSdk.sdkInitialize(getApplicationContext());
            if (getCallbackManager() == null){
                callbackManager = CallbackManager.Factory.create();
            }
        }
    }

    public AuthCallback getTwitterAuthCallback() {
        return twitterAuthCallback;
    }

    public void setTwitterAuthCallback(AuthCallback twitterAuthCallback) {
        this.twitterAuthCallback = twitterAuthCallback;
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

}
