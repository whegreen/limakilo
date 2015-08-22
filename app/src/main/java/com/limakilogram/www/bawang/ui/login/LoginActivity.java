package com.limakilogram.www.bawang.ui.login;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

//import com.crashlytics.android.Crashlytics;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.limakilogram.www.bawang.R;

//import io.fabric.sdk.android.Fabric;

public class LoginActivity extends FragmentActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Fabric.with(this, new Crashlytics());

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

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

}
