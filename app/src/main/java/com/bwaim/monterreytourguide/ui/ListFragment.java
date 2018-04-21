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

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwaim.monterreytourguide.R;
import com.bwaim.monterreytourguide.adapter.CardViewAdapter;
import com.bwaim.monterreytourguide.model.GenericObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    public static final String DATA_KEY = "DATA_KEY";
    public static final String TYPE_KEY = "TYPE_KEY";

    private RecyclerView mRecylerView;
    private CardViewAdapter mCardViewAdapter;
    private OnListFragmentInteractionListener mListener;

    private ArrayList<GenericObject> mDatas;
    private String mType;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param data the data of the list.
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance(ArrayList<GenericObject> data, String type) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(DATA_KEY, data);
        args.putString(TYPE_KEY, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecylerView = view.findViewById(R.id.recycler_view_RV);

        mRecylerView.setHasFixedSize(true);

        mRecylerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        if (getArguments() != null) {
            mDatas = getArguments().getParcelableArrayList(DATA_KEY);
            mType = getArguments().getString(TYPE_KEY);
        }

        mCardViewAdapter = new CardViewAdapter(mDatas, mType);
        mRecylerView.setAdapter(mCardViewAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    interface OnListFragmentInteractionListener {
        // No information to pass from the fragment to the activity
    }
}
