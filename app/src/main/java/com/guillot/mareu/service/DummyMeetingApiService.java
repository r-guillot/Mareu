package com.guillot.mareu.service;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.guillot.mareu.R;
import com.guillot.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetings = MeetingListService.generateMeetings();

    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    public List<Meeting> getDateFiltered(String filterDate) {
        List<Meeting> filteredDateList = new ArrayList<>();
        for (Meeting meeting : getMeetings()) {
            if (meeting.getDate().contains(filterDate)) {
                filteredDateList.add(meeting);
            }
        }
        return filteredDateList;
    }

    public List<Meeting> getPlaceFiltered(String filterText) {
        List<Meeting> filteredPlaceList = new ArrayList<>();
        for (Meeting meeting : getMeetings()) {
            if (filterText.contains("Réinitialiser les filtres")) {
                filteredPlaceList.add(meeting);
            } else if (meeting.getPlace().contains(filterText)) {
                filteredPlaceList.add(meeting);
            }
        }
        return filteredPlaceList;
    }

}

