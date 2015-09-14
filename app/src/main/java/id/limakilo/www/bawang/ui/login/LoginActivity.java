package id.limakilo.www.bawang.ui.login;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import id.limakilo.www.bawang.R;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends FragmentActivity {

    private String TAG = "LoginActivity";
    private CallbackManager callbackManager;

    private AuthCallback twitterAuthCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //facebook stuffs
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
