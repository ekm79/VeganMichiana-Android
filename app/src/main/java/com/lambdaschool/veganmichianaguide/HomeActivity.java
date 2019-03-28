package com.lambdaschool.veganmichianaguide;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Trace;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lambdaschool.veganmichianaguide.activities.CategoriesFragment;
import com.lambdaschool.veganmichianaguide.activities.DetailFragment;
import com.lambdaschool.veganmichianaguide.activities.ListFragment;
import com.lambdaschool.veganmichianaguide.activities.PhoneDetailActivity;
import com.lambdaschool.veganmichianaguide.activities.UpdateItemFragment;
import com.lambdaschool.veganmichianaguide.models.ApiObject;


public class HomeActivity extends AppCompatActivity implements
        ListFragment.OnListFragmentInteractionListener,
        CategoriesFragment.OnCategoryFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener {


    Context context;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.i("Restaurants", "APP CREATED");
        context = this;

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Toast.makeText(context, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                menuItem.setChecked(!menuItem.isChecked());

                switch (menuItem.getItemId()) {
                    case R.id.nav_restaurants:
                        onFragmentInteraction(ListFragment.CATEGORY_RESTAURANT);
                        break;
                    case R.id.nav_menu_items:
                        onFragmentInteraction(ListFragment.CATEGORY_MENU_ITEM);
                        break;
                    case R.id.nav_products:
                        onFragmentInteraction(ListFragment.CATEGORY_PRODUCT);
                        break;
                    case R.id.nav_stores:
                        onFragmentInteraction(ListFragment.CATEGORY_STORE);
                        break;
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });

        Trace.beginSection("Adding Fragment");
        Fragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ListFragment.ARG_CATEGORY, ListFragment.CATEGORY_RESTAURANT);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_holder, fragment)
                .commit();
        Trace.endSection();
    }


    @Override
    public void onListFragmentInteraction(ApiObject item, View sharedView) {
        Log.i("Restaurant", "onListFragmentInteraction called");
        if (getResources().getBoolean(R.bool.is_tablet)) {
            // fragment_holder_detail
            final DetailFragment detailFragment = DetailFragment.newInstance(item);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder_detail, detailFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
        } else {
            Intent intent = new Intent(context, PhoneDetailActivity.class);
            intent.putExtra(ApiObject.API_ITEM_INTENT_TAG, item);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setEnterTransition(new Fade());
                final ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        sharedView,
                        ViewCompat.getTransitionName(sharedView));
//                        sharedView.getTransitionName());
                startActivity(intent, activityOptions.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }


    @Override
    public void onFragmentInteraction(int category) {
        Fragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ListFragment.ARG_CATEGORY, category);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_holder, fragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(ApiObject item) {
        final UpdateItemFragment updateFragment = UpdateItemFragment.newInstance(item);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder_detail, updateFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
    }

//    @Override
//    public void onListFragmentInteraction(ApiObject item) {

//    }

}
