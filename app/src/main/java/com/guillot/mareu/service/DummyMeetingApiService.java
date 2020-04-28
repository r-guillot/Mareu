package com.guillot.mareu.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.view.View;

import com.guillot.mareu.controler.MainActivity;
import com.guillot.mareu.controler.MyMeetingRecyclerViewAdapter;
import com.guillot.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetings = Meeting_List.generateMeetings();

    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    public void createMeeting (Meeting meeting) {
        mMeetings.add(meeting);
    }

    public List<Meeting> filterMeetingRoom (String string) {
        List<Meeting> filterList = new ArrayList<>();
        for (Meeting m : getMeetings()) {
           if (m.getPlace().equalsIgnoreCase(string)) {
               filterList.add(m);
           }
        }
        return filterList;
    }

}

