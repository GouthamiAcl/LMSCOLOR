package com.sky.workbook;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.google.android.material.navigation.NavigationView;
import com.sky.workbook.Activity.LearningTracksActivity;
import com.sky.workbook.Activity.LoginActivity;
import com.sky.workbook.Activity.SignupActivity;
import com.sky.workbook.Adapter.HomeViewPagerAdapter;
import com.sky.workbook.Fragment.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private HomeFragment currentFragment;
    private HomeViewPagerAdapter adapter;
    private AHBottomNavigationAdapter navigationAdapter;

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar toolbar;
    boolean doubleBackToExitPressedOnce=false;

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private boolean useMenuResource = true;

    private Handler handler = new Handler();

    // UI
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        initUI();
    }


    /**
     * Init UI
     */
    private void initUI() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setAccentColor(Color.parseColor("#D12B39"));

        viewPager = findViewById(R.id.view_pager);

            navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_5);
            navigationAdapter.setupWithBottomNavigation(bottomNavigation);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationview) ;


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                // Learning track
                if(menuItem.getItemId() == R.id.learing_track)
                {
                    startActivity(new Intent(MainActivity.this, LearningTracksActivity.class));
                }

                // LOGOUT
                if(menuItem.getItemId() == R.id.logout)
                {
                    Toast.makeText(MainActivity.this, getString(R.string.drawer_logout), Toast.LENGTH_SHORT).show();
                }

                // Settings
                if(menuItem.getItemId() == R.id.settings)
                {
                    Toast.makeText(MainActivity.this, getString(R.string.drawer_settings), Toast.LENGTH_SHORT).show();
                }

                // Feedback
                if(menuItem.getItemId() == R.id.feedback)
                {
                    Toast.makeText(MainActivity.this, getString(R.string.drawer_help), Toast.LENGTH_SHORT).show();
                }

                // Rate App
                if (menuItem.getItemId() == R.id.rateApp) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +getPackageName())));
                }

                // Share App
                if (menuItem.getItemId() == R.id.shareApp) {
                    String appname=getString(R.string.app_name);
                    String ExternalString= getString(R.string.String);
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, appname+"\n"+ ExternalString + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }

                // About
                if(menuItem.getItemId() == R.id.about)
                {
                    Toast.makeText(MainActivity.this, getString(R.string.drawer_about), Toast.LENGTH_SHORT).show();
                }

                // Contact
                if(menuItem.getItemId() == R.id.contact)
                {
                    Toast.makeText(MainActivity.this, getString(R.string.drawer_contact), Toast.LENGTH_SHORT).show();
                }

                return false;
            }

        });

        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();


       // bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position)
                {
                    case 0:
                        setToolbarTitle("Welcome to "+getString(R.string.app_name));
                        break;
                    case 1:
                        setToolbarTitle(getString(R.string.menu_2));
                        break;
                    case 2:
                        setToolbarTitle(getString(R.string.menu_3));
                        break;
                    case 3:
                        setToolbarTitle(getString(R.string.menu_4));
                        break;
                    case 4:
                        setToolbarTitle(getString(R.string.menu_5));
                        break;
                }

                if (currentFragment == null) {
                    currentFragment = adapter.getCurrentFragment();
                }

                if (wasSelected) {
                    currentFragment.refresh();
                    return true;
                }

                if (currentFragment != null) {
                    currentFragment.willBeHidden();
                }

                viewPager.setCurrentItem(position, false);

                if (currentFragment == null) {
                    return true;
                }

                currentFragment = adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();

                if (position == 1) {
                    bottomNavigation.setNotification("", 1);
                }

                return true;
            }
        });

		/*
		bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
			@Override public void onPositionChange(int y) {
				Log.d("MainActivity", "BottomNavigation Position: " + y);
			}
		});
		*/

        viewPager.setOffscreenPageLimit(4);
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Setting custom colors for notification
                AHNotification notification = new AHNotification.Builder()
                        .setText("2")
                        .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_back))
                        .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_text))
                        .build();
                bottomNavigation.setNotification(notification, 1);
            }
        }, 1000);

        //bottomNavigation.setDefaultBackgroundResource(R.drawable.bottom_navigation_background);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ic_cart:
                Toast.makeText(MainActivity.this, "Cart", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.ic_notification:
                Toast.makeText(MainActivity.this, "Notification", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed()
    {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        if(mDrawerLayout.isDrawerOpen(Gravity.START))
        {
            mDrawerLayout.closeDrawers();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}