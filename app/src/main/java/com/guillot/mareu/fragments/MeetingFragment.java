package com.guillot.mareu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guillot.mareu.DI;
import com.guillot.mareu.service.MeetingApiService;
import com.guillot.mareu.service.Meeting_List;
import com.guillot.mareu.R;
import com.guillot.mareu.controler.MyMeetingRecyclerViewAdapter;
import com.guillot.mareu.model.Meeting;

import java.util.List;

public class MeetingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MyMeetingRecyclerViewAdapter mAdapter;
    private List<Meeting> mMeetings;
    private MeetingApiService mApiService;

    public static MeetingFragment newInstance () {
        MeetingFragment fragment = new MeetingFragment();
//        fragment.filterRoom(string);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getMeetingApiService();
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview_meeting, container, false);
        Context context = view.getContext();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//        mAdapter = new MyMeetingRecyclerViewAdapter(mApiService.getMeetings(),context);
//        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public void initList() {
        mMeetings = mApiService.getMeetings();

        mRecyclerView.setAdapter(new MyMeetingRecyclerViewAdapter(mMeetings, getContext()));
    }

    public void filterRoom (String string) {
        mApiService.filterMeetingRoom(string);
    }

}
