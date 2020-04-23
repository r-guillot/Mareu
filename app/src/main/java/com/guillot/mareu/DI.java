package com.guillot.mareu;

import com.guillot.mareu.model.Meeting;
import com.guillot.mareu.service.DummyMeetingApiService;
import com.guillot.mareu.service.MeetingApiService;

import java.util.List;

public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();

        public static MeetingApiService getMeetingApiService() {
            return service;
        }

}
