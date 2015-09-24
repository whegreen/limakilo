package id.limakilo.www.bawang;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.Digits;
import com.facebook.FacebookSdk;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;
import io.supportkit.core.SupportKit;

/**
 * Created by walesadanto on 30/8/15.
 */
public class BawangApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "QzDqgpPWp2eicthl2PjsC6Naw";
    private static final String TWITTER_SECRET = "MfjqRGnHLG9TxR6BT3Raeqqn0chCvYe0i0JU86EHMBd1I5QaaA";
    private static final String SUPPORT_KIT_TOKEN = "e37bwzthf9w5jmmrab64m8jve";

        @Override
        public void onCreate() {
            super.onCreate();

            FacebookSdk.sdkInitialize(getApplicationContext());

            TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
            Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig), new Digits());

            SupportKit.init(this, SUPPORT_KIT_TOKEN);
        }

}
