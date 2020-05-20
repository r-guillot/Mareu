package com.guillot.mareu.service;

import com.guillot.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MeetingListService {

    public static List<Meeting> MEETING_LIST = Arrays.asList(
            new Meeting("01/07/2020", "14h00", "Réunion A", "Peach", "maxime@lamzone.com alex@lamzone.com"),
            new Meeting("17/09/2020", "16h00", "Réunion B", "Mario", "paul@lamzone.com viviane@lamzone.com"),
            new Meeting("25/06/2020", "19h00", "Réunion C", "Luigi", "amadine@lamzone.com luc@lamzone.com")
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(MEETING_LIST);
    }
}
