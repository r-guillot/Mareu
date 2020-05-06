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

import com.guillot.mareu.event.DeleteEvent;
import com.guillot.mareu.event.FilterDateEvent;
import com.guillot.mareu.event.FilterPlaceEvent;
import com.guillot.mareu.injection.DI;
import com.guillot.mareu.databinding.FragmentRecyclerviewMeetingBinding;
import com.guillot.mareu.model.Meeting;
import com.guillot.mareu.service.MeetingApiService;
import com.guillot.mareu.controler.MyMeetingRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MeetingFragment extends Fragment {

    FragmentRecyclerviewMeetingBinding binding;
    private MyMeetingRecyclerViewAdapter mAdapter;
    private MeetingApiService mApiService;
    private List<Meeting> mMeetings;

    public static MeetingFragment newInstance () {
        MeetingFragment fragment = new MeetingFragment();
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
        return view;
    }

    public void initList() {
        mAdapter = new MyMeetingRecyclerViewAdapter(mApiService.getMeetings(),getContext());
        binding.recyclerviewMeeting.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }

    // receiving event bus for place filter
    @Subscribe
    public void onEvent(FilterPlaceEvent event) {
        mAdapter = new MyMeetingRecyclerViewAdapter(getPlaceFiltered(event.filterText),getContext());
        binding.recyclerviewMeeting.setAdapter(mAdapter);
    }

    private List<Meeting> getPlaceFiltered(String filterText) {
        List<Meeting> filteredPlaceList = new ArrayList<>();
        for (Meeting meeting : mApiService.getMeetings()) {
            if (meeting.getPlace().contains(filterText)) {
                filteredPlaceList.add(meeting);
            }
        }
        return filteredPlaceList;
    }

    // receiving event bus for date filter
    @Subscribe
    public void onEvent(FilterDateEvent event) {
        mAdapter = new MyMeetingRecyclerViewAdapter(getDateFiltered(event.filterDate),getContext());
        binding.recyclerviewMeeting.setAdapter(mAdapter);
    }

    private List<Meeting> getDateFiltered(String filterDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        List<Meeting> filteredDateList = new ArrayList<>();
        for (Meeting meeting : mApiService.getMeetings()) {
            if (meeting.getDate().contains(filterDate)) {
                filteredDateList.add(meeting);
            }
        }
        return filteredDateList;
    }

    // receiving event bus for delete meeting
    @Subscribe
    public void onDeleteEvent(DeleteEvent event) {
        mApiService.deleteMeeting(event.meeting);
        initList();
    }
}
