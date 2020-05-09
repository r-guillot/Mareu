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
            new Meeting("01/07/2020", "14h00", "Réunion A", "Valorant", "cypher@gmail.com omen@hotmail.com breach@yahoo.fr"),
            new Meeting("17/09/2020", "8h00", "Réunion B", "Cyberpunk", "mike_pondsmith@gmail.com konrad@caramail.com")
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(MEETING_LIST);
    }
}
