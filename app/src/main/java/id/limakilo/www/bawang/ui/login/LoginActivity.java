package id.limakilo.www.bawang.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.digits.sdk.android.AuthCallback;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

import id.limakilo.www.bawang.R;

public class LoginActivity extends FragmentActivity {

    private String TAG = "LoginActivity";
    private CallbackManager callbackManager;
    private AuthCallback twitterAuthCallback;

    public MaterialDialog dialogWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //facebook stuffs
        callbackManager = CallbackManager.Factory.create();
        //set layout to fragment
        setContentView(R.layout.activity_login);
        initDialogWebview();
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

    // facebook stuff
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void initDialogWebview(){
        boolean wrapInScrollView = true;
        dialogWebview = new MaterialDialog.Builder(this)
//                .title("Konfirmasi Pembayaran")
                .customView(R.layout.dialog_webview_main, wrapInScrollView)
                .positiveText("Update")
                .negativeText("Nanti")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {

                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.hide();
                    }
                })
                .build();
    }

    public void showDialogWebview(){
        WebView webView = (WebView) dialogWebview.getCustomView().findViewById(R.id.dialog_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://update.limakilo.id/android");
        dialogWebview.show();
    }
}
