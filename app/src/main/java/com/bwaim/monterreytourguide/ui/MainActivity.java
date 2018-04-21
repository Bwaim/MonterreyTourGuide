/*
 *    Copyright 2018 Fabien Boismoreau
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.bwaim.monterreytourguide.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;

import com.bwaim.monterreytourguide.R;
import com.bwaim.monterreytourguide.model.GenericObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnListFragmentInteractionListener {

    public static final SparseArray<ArrayList<GenericObject>> DATAS = new SparseArray<>();

    static {
        ArrayList<GenericObject> ITEMS_CULTURES = new ArrayList<>();
        GenericObject culture1 = new GenericObject(R.drawable.catedral_metropolitana
                , R.string.cultureTitle1, R.string.cultureSummary1);

        ITEMS_CULTURES.add(culture1);

        ArrayList<GenericObject> ITEMS_RESTAURANTS = new ArrayList<>();
        GenericObject restaurant1 = new GenericObject(R.drawable.losgenerales
                , R.string.restaurantTitle1, R.string.restaurantSummary1);
        ITEMS_RESTAURANTS.add(restaurant1);
        ITEMS_RESTAURANTS.add(restaurant1);
        ITEMS_RESTAURANTS.add(restaurant1);

        DATAS.put(R.id.cultures, ITEMS_CULTURES);
        DATAS.put(R.id.restaurants, ITEMS_RESTAURANTS);
        DATAS.put(R.id.entertainments, new ArrayList<GenericObject>());
        DATAS.put(R.id.cinemas, new ArrayList<GenericObject>());
    }

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the actionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_action_dehaze);
        }

        NavigationView navigationView = findViewById(R.id.nav_view);

        // Select the first item as displayed
        MenuItem firstMenuItem = navigationView.getMenu().getItem(0);
        firstMenuItem.setChecked(true);

        ListFragment fragment = ListFragment.newInstance(DATAS.get(firstMenuItem.getItemId())
                , firstMenuItem.getTitle().toString());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment).commit();

        mDrawerLayout = findViewById(R.id.drawer_layout);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        ListFragment fragment = ListFragment.newInstance(
                                DATAS.get(menuItem.getItemId()), menuItem.getTitle().toString());

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, fragment).commit();

                        return true;
                    }
                });
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
