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
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.facebook.appevents.AppEventsLogger;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.historyorder.HistoryOrderActivity;
import id.limakilo.www.bawang.ui.main.grosirfragment.GrosirFragment;
import id.limakilo.www.bawang.ui.main.stockfragment.StockFragment;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import id.limakilo.www.bawang.util.social.SupportkitKit;
import io.supportkit.ui.ConversationActivity;

/**
 * TODO
 */
public class MainActivity extends AppCompatActivity {
//    implements } ContactsListFragment.OnContactsInteractionListener{

    private DrawerLayout mDrawerLayout;
    private TextView navUsername;
    private TextView navEmail;


    // True if this activity instance is a search result view (used on pre-HC devices that load
    // search results in a separate instance of the activity rather than loading results in-line
    // as the query is typed.
    private boolean isSearchResultView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(id.limakilo.www.bawang.R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(id.limakilo.www.bawang.R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(id.limakilo.www.bawang.R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(id.limakilo.www.bawang.R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(id.limakilo.www.bawang.R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        ViewPager viewPager = (ViewPager) findViewById(id.limakilo.www.bawang.R.id.viewpager);
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

        // Check if this activity instance has been triggered as a result of a search query. This
        // will only happen on pre-HC OS versions as from HC onward search is carried out using
        // an ActionBar SearchView which carries out the search in-line without loading a new
        // Activity.
//        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
//
//            // Fetch query from intent and notify the fragment that it should display search
//            // results instead of all contacts.
//            String searchQuery = getIntent().getStringExtra(SearchManager.QUERY);
//            ContactsListFragment mContactsListFragment = (ContactsListFragment)
//                    getSupportFragmentManager().findFragmentById(R.id.contact_list);
//
//            // This flag notes that the Activity is doing a search, and so the result will be
//            // search results rather than all contacts. This prevents the Activity and Fragment
//            // from trying to a search on search results.
//            isSearchResultView = true;
//            mContactsListFragment.setSearchQuery(searchQuery);
//
//            // Set special title for search results
//            String title = getString(R.string.contacts_list_search_results_title, searchQuery);
//            setTitle(title);
//        }

        navUsername = (TextView) findViewById(R.id.nav_username);
        navEmail = (TextView) findViewById(R.id.nav_email);

        initNavigationProfile();

        ImageView avatar = (ImageView) findViewById(id.limakilo.www.bawang.R.id.avatar_navheader);
        Glide.with(getBaseContext())
                .load(id.limakilo.www.bawang.R.drawable.avatar_onion)
                .fitCenter()
                .into(avatar);

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
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        Context context = getApplicationContext();

                        switch (menuItem.getItemId()) {
                            case id.limakilo.www.bawang.R.id.nav_home:
                                Intent intent1 = new Intent(context, HistoryOrderActivity.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent1);
                                break;
                            case id.limakilo.www.bawang.R.id.nav_messages:
                                Snackbar.make(findViewById(id.limakilo.www.bawang.R.id.drawer_layout), "this feature will be available soon",
                                        Snackbar.LENGTH_LONG).show();
                                break;
                            case id.limakilo.www.bawang.R.id.nav_feedback:
                                ConversationActivity.show(MainActivity.this);
                                break;
                        }
                        return true;
                    }
                });
    }

//    @Override
//    public void onContactSelected(Uri contactUri) {
//        Toast.makeText(this, "oncontactseleceted", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onSelectionCleared() {
//        Toast.makeText(this, "onselecetioncleared", Toast.LENGTH_SHORT).show();
//    }

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
    public boolean onSearchRequested() {
        // Don't allow another search if this activity instance is already showing
        // search results. Only used pre-HC.
        return !isSearchResultView && super.onSearchRequested();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(id.limakilo.www.bawang.R.menu.menu_limakilo, menu);
        return true;
    }


    public void initNavigationProfile(){

        String handphone = PreferencesManager.getAsString(this, PreferencesManager.HANDPHONE);
        String name = PreferencesManager.getAsString(this, PreferencesManager.NAME);

        if (name != null){
            try{
                navUsername.setText(name);
                navEmail.setText(handphone);
            }
            catch (Exception e){
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
                try{
                    firstName = result.data.getData().get(0).getUserFirstName();
                    lastName = result.data.getData().get(0).getUserLastName();
                }
                catch (Exception e){
                    Crashlytics.logException(e);
                }

                if (firstName != null){
                    String name = firstName;
                    if (lastName != null){
                        name = firstName +" "+lastName;
                    }
                    navUsername.setText(name);
                }
                if (phone != null){
                    navEmail.setText(phone);
                }
            }

            @Override
            public void failure(TwitterException e) {

            }
        });
    }


}
