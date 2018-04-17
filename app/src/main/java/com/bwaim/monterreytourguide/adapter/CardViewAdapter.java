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

package com.bwaim.monterreytourguide.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bwaim.monterreytourguide.R;
import com.bwaim.monterreytourguide.model.GenericObject;
import com.bwaim.monterreytourguide.ui.DetailsActivity;

import java.util.List;

/**
 * Created by Fabien Boismoreau on 14/04/2018.
 * <p>
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    public static final String ITEM_DATA = "ITEM_DATA";
    public static final String ITEM_TITLE = "ITEM_TITLE";

    /**
     * The list of objects
     */
    private List<GenericObject> mDatas;

    /**
     * The type of objects
     */
    private String mItemCategory;

    public CardViewAdapter(List<GenericObject> mDatas, String category) {
        this.mDatas = mDatas;
        this.mItemCategory = category;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * { #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary { View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = (RecyclerView) v.getParent();
                int itemPosition = recyclerView.getChildLayoutPosition(v);

                Intent detailsActivity = new Intent(v.getContext(), DetailsActivity.class);
                detailsActivity.putExtra(ITEM_DATA, mDatas.get(itemPosition));
                detailsActivity.putExtra(ITEM_TITLE, mItemCategory);
                v.getContext().startActivity(detailsActivity);
            }
        });

        return new ViewHolder(frameLayout);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override { #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GenericObject object = mDatas.get(position);
        holder.mImage.setImageResource(object.getImageResId());
        holder.mTitle.setText(object.getNameResId());
        holder.mSummary.setText(object.getSummaryResId());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        //public FrameLayout mFrameLayout;
        ImageView mImage;
        TextView mTitle;
        TextView mSummary;

        ViewHolder(FrameLayout v) {
            super(v);

            mImage = v.findViewById(R.id.photo_image_view);
            mTitle = v.findViewById(R.id.card_view_title);
            mSummary = v.findViewById(R.id.card_view_summary);
            //mFrameLayout = v;
        }
    }
}
