package com.quickly.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quickly.R;
import com.quickly.adapter.AdapterRecyclerViewVideo;
import com.quickly.fragment.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {

    private RecyclerView mVideoRecyclerView;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_video_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        mVideoRecyclerView = getmRootView().findViewById(R.id.video_recyclerview);


        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 把所有的组件都当作 recyclerView 的子view 即可整合所有页面布局问题

       // mVideoRecyclerView.addView();

        mVideoRecyclerView.setAdapter(new AdapterRecyclerViewVideo(getActivity()));




    }


}
