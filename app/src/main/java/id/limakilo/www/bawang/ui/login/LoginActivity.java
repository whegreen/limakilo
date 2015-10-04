package id.limakilo.www.bawang.ui.login;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.digits.sdk.android.AuthCallback;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

import id.limakilo.www.bawang.R;

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

    //twitter stuffs
    public AuthCallback getTwitterAuthCallback() {
        return twitterAuthCallback;
    }

    public void setTwitterAuthCallback(AuthCallback twitterAuthCallback) {
        this.twitterAuthCallback = twitterAuthCallback;
    }

    //facebook stuffs
    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

}
