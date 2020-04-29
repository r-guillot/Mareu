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
import com.guillot.mareu.databinding.FragmentRecyclerviewMeetingBinding;
import com.guillot.mareu.service.MeetingApiService;
import com.guillot.mareu.service.Meeting_List;
import com.guillot.mareu.R;
import com.guillot.mareu.controler.MyMeetingRecyclerViewAdapter;
import com.guillot.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingFragment extends Fragment {

    FragmentRecyclerviewMeetingBinding binding;
    private MyMeetingRecyclerViewAdapter mAdapter;
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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecyclerviewMeetingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Context context = view.getContext();

        binding.recyclerviewMeeting.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new MyMeetingRecyclerViewAdapter(mApiService.getMeetings(),context);
        binding.recyclerviewMeeting.setAdapter(mAdapter);
        return view;
    }

    public void initList() {
        mAdapter.notifyDataSetChanged();
    }



//    public void filterRoom (String string) {
//        mApiService.filterMeetingRoom(string);
//    }

}
