package id.limakilo.www.bawang.ui.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.login.LoginActivity;

/**
 * Created by martinluter on 11/5/15.
 */
public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                check(SplashScreenActivity.this);
            }
        }, SPLASH_TIME_OUT);

    }

    public void check(Context ctx) {
        Intent i;
        i = new Intent(SplashScreenActivity.this, LoginActivity.class);
        // close this activity
        startActivity(i);
        finish();
    }
}