package com.guillot.mareu.service;

import com.guillot.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void createMeeting (Meeting meeting);

    void deleteMeeting (Meeting meeting);
}
