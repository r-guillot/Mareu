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

import com.guillot.mareu.R;
import com.guillot.mareu.event.DeleteEvent;
import com.guillot.mareu.event.FilterDateEvent;
import com.guillot.mareu.event.FilterPlaceEvent;
import com.guillot.mareu.injection.DI;
import com.guillot.mareu.databinding.FragmentRecyclerviewMeetingBinding;

import com.guillot.mareu.service.MeetingApiService;
import com.guillot.mareu.controler.MyMeetingRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MeetingFragment extends Fragment {

    private FragmentRecyclerviewMeetingBinding binding;
    private MyMeetingRecyclerViewAdapter mAdapter;
    private MeetingApiService mApiService;

    public static MeetingFragment newInstance() {
        return new MeetingFragment();
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

    private void initList() {
        mAdapter = new MyMeetingRecyclerViewAdapter(mApiService.getMeetings(), getContext());
        binding.recyclerviewMeeting.setAdapter(mAdapter);
    }

    /**
     * receiving event bus for place filter
     *
     * @param event
     */
    @Subscribe
    public void onEvent(FilterPlaceEvent event) {
        mAdapter = new MyMeetingRecyclerViewAdapter(mApiService.getPlaceFiltered(event.filterText), getContext());
        if (mApiService.getPlaceFiltered(event.filterText).isEmpty()) {
            binding.recyclerviewMeeting.setVisibility(View.GONE);
            binding.emptyView.setVisibility(View.VISIBLE);
        } else {
            binding.recyclerviewMeeting.setVisibility(View.VISIBLE);
            binding.emptyView.setVisibility(View.GONE);
        }
        binding.recyclerviewMeeting.setAdapter(mAdapter);
    }


    /**
     * receiving event bus for date filter
     *
     * @param event
     */
    @Subscribe
    public void onEvent(FilterDateEvent event) {
        mAdapter = new MyMeetingRecyclerViewAdapter(mApiService.getDateFiltered(event.filterDate), getContext());
        if (mApiService.getDateFiltered(event.filterDate).isEmpty()) {
            binding.recyclerviewMeeting.setVisibility(View.GONE);
            binding.emptyView.setVisibility(View.VISIBLE);
        } else {
            binding.recyclerviewMeeting.setVisibility(View.VISIBLE);
            binding.emptyView.setVisibility(View.GONE);
        }
        binding.recyclerviewMeeting.setAdapter(mAdapter);
    }

    @Subscribe
    public void onDeleteEvent(DeleteEvent event) {
        mApiService.deleteMeeting(event.meeting);
        initList();
    }
}
