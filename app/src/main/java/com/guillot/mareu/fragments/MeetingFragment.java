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

import com.guillot.mareu.Meeting_List;
import com.guillot.mareu.R;
import com.guillot.mareu.controler.MyMeetingRecyclerViewAdapter;
import com.guillot.mareu.model.Meeting;

import java.util.List;

public class MeetingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MyMeetingRecyclerViewAdapter mAdapter;
    private List<Meeting> mMeetings;

    public static MeetingFragment newInstance () {
        MeetingFragment fragment = new MeetingFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMeetings = Meeting_List.mMeetingList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview_meeting, container, false);
        Context context = view.getContext();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new MyMeetingRecyclerViewAdapter(mMeetings,context);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
