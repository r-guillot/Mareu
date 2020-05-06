package com.guillot.mareu.service;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.guillot.mareu.R;
import com.guillot.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetings = MeetingListService.generateMeetings();

    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    public void createMeeting (Meeting meeting) {
        mMeetings.add(meeting);
    }

    public void deleteMeeting (Meeting meeting) {
        mMeetings.remove(meeting);
    }


}

