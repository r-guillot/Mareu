package com.guillot.mareu.service;

import com.guillot.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Meeting_List {

    public static List<Meeting> MEETING_LIST = Arrays.asList(
            new Meeting(new Date(24-12-2020),"00h00", "Réunion A", "Noêl","pere-noel@polenord.go mere-noel@polenord.go"),
            new Meeting(new Date(11-05-2020),"8h00", "Réunion B", "Déconfinement", "macron@elysee.com edouard@elysee.com")
    );

    static List<Meeting> generateMeetings () { return new ArrayList<>(MEETING_LIST);
    }
}
