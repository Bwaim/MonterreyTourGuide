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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwaim.monterreytourguide.R;
import com.bwaim.monterreytourguide.adapter.CardViewAdapter;
import com.bwaim.monterreytourguide.model.GenericObject;

public class DetailsActivity extends AppCompatActivity {

    private GenericObject mObject;
    private String mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the information from the intent
        Intent intent = getIntent();
        mObject = (GenericObject) intent.getSerializableExtra(CardViewAdapter.ITEM_DATA);
        mCategory = intent.getStringExtra(CardViewAdapter.ITEM_TITLE);

        // Fill the View with the information
        ImageView image = findViewById(R.id.collapsing_image_IV);
        image.setImageResource(mObject.getImageResId());

        TextView titleTV = findViewById(R.id.title_TV);
        titleTV.setText(mObject.getNameResId());

        TextView summaryTV = findViewById(R.id.summary_TV);
        summaryTV.setText(mObject.getSummaryResId());

        // Set the toolbar
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout_CTL);
        collapsingToolbarLayout.setTitle(mCategory);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        final Toolbar toolbar = findViewById(R.id.toolbar_TB);
        setSupportActionBar(toolbar);

        // Add the back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        AppBarLayout appBarLayout = findViewById(R.id.app_bar_layout_ABL);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                // Set the home button transparent depending the offset
                int alpha = 255 -
                        (255 * (appBarLayout.getTotalScrollRange() - Math.abs(verticalOffset))
                                / appBarLayout.getTotalScrollRange());
                if (toolbar.getNavigationIcon() != null) {
                    toolbar.getNavigationIcon().setAlpha(alpha);
                }
            }
        });

    }
}
