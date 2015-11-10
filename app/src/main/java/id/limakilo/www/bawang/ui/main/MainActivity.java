/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package id.limakilo.www.bawang.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.facebook.appevents.AppEventsLogger;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.historyorder.HistoryOrderActivity;
import id.limakilo.www.bawang.ui.login.LoginActivity;
import id.limakilo.www.bawang.ui.main.campaignfragment.CampaignFragment;
import id.limakilo.www.bawang.ui.main.grosirfragment.GrosirFragment;
import id.limakilo.www.bawang.ui.main.stockfragment.StockFragment;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import id.limakilo.www.bawang.util.social.SupportkitKit;
import io.supportkit.ui.ConversationActivity;

/**
 * TODO
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.nav_username) TextView navUsername;
    @Bind(R.id.nav_email) TextView navEmail;
    @Bind(R.id.avatar_navheader) ImageView avatar;
    @Bind(R.id.cover_navheader) ImageView cover;
    @Bind(R.id.loading_bar) View loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(id.limakilo.www.bawang.R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(id.limakilo.www.bawang.R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(true);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        if (viewPager != null) {
            setupViewPager(viewPager);
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        TabLayout tabLayout = (TabLayout) findViewById(id.limakilo.www.bawang.R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        initNavigationProfile();

        SupportkitKit supportkitKit = new SupportkitKit();
        supportkitKit.setupUser(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.about_us:
                String url = "http://limakilo.id";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new StockFragment(), "Paket");
        adapter.addFragment(new GrosirFragment(), "Grosir");
        adapter.addFragment(new CampaignFragment(), "Campaign");
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        final Context context = getBaseContext();
                        final Handler handler = new Handler();
                        switch (menuItem.getItemId()) {
                            case id.limakilo.www.bawang.R.id.nav_home:
                                MainActivity.this.showLoadingBar();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        Intent intent1 = new Intent(context, HistoryOrderActivity.class);
                                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(intent1);
                                        MainActivity.this.hideLoadingBar();
                                    }
                                }, 300L);
                                break;
//                            case id.limakilo.www.bawang.R.id.nav_messages:
//                                Snackbar.make(findViewById(id.limakilo.www.bawang.R.id.drawer_layout), "fitur ini sedang dikembangkan",
//                                        Snackbar.LENGTH_LONG).show();
//                                break;
                            case id.limakilo.www.bawang.R.id.nav_feedback:
                                MainActivity.this.showLoadingBar();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        ConversationActivity.show(MainActivity.this);
                                        MainActivity.this.hideLoadingBar();
                                    }
                                }, 300L);
                                break;
                            case R.id.nav_logout:
                                MainActivity.this.showLoadingBar();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        handler.removeCallbacks(this);
                                        PreferencesManager.removeString(context, PreferencesManager.AUTH_TOKEN);
                                        Intent intent1 = new Intent(context, LoginActivity.class);
                                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(intent1);
                                        MainActivity.this.hideLoadingBar();

                                    }
                                }, 300L);
                                break;
                        }
                        return true;
                    }
                });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(id.limakilo.www.bawang.R.menu.menu_limakilo, menu);
        return true;
    }

    public void initNavigationProfile(){

        String handphone = PreferencesManager.getAsString(this, PreferencesManager.HANDPHONE);
        final String email = PreferencesManager.getAsString(this, PreferencesManager.EMAIL);
        String name = PreferencesManager.getAsString(this, PreferencesManager.FIRST_NAME)+" "+PreferencesManager.getAsString(this, PreferencesManager.LAST_NAME);

        String avatarUrl = PreferencesManager.getAsString(this, PreferencesManager.AVATAR_URL);
        String coverUrl = PreferencesManager.getAsString(this, PreferencesManager.COVER_URL);

        if (name != null){
            try{
                navUsername.setText(name);
                navEmail.setText(email);
            }
            catch (Exception e){
                Crashlytics.logException(e);
            }
        }

        if (avatarUrl != null && !avatarUrl.equals("")){
            try{
                Glide.with(getBaseContext())
                        .load(avatarUrl)
                        .fitCenter()
                        .into(avatar);
                if (coverUrl != null && !coverUrl.equals("")){
                    Glide.with(getBaseContext())
                            .load(coverUrl)
                            .centerCrop()
                            .into(cover);
                }
            }
            catch (Exception e){
                Glide.with(getBaseContext())
                        .load(id.limakilo.www.bawang.R.drawable.avatar_onion)
                        .fitCenter()
                        .into(avatar);
                Crashlytics.logException(e);
            }
        }

        String token = PreferencesManager.getAuthToken(this);
        final String phone = PreferencesManager.getAsString(this, PreferencesManager.HANDPHONE);

        APICallManager.getInstance(token).getUsers(new Callback<GetUserResponseModel>() {
            @Override
            public void success(Result<GetUserResponseModel> result) {
                String firstName = null;
                String lastName = null;
                try {
                    firstName = result.data.getData().get(0).getUserFirstName();
                    lastName = result.data.getData().get(0).getUserLastName();
                } catch (Exception e) {
                    Crashlytics.logException(e);
                }

                if (firstName != null) {
                    String name = firstName;
                    if (lastName != null) {
                        name = firstName + " " + lastName;
                    }
                    navUsername.setText(name);
                }
                if (phone != null) {
                    navEmail.setText(phone);
                }else{
                    navEmail.setText(email);
                }
            }

            @Override
            public void failure(TwitterException e) {

            }
        });
    }

    public void showLoadingBar(){
        loadingView.setVisibility(View.VISIBLE);
    }

    public void hideLoadingBar(){
        loadingView.setVisibility(View.GONE);
    }

}
