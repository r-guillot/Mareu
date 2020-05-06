package com.guillot.mareu;

import com.guillot.mareu.injection.DI;
import com.guillot.mareu.model.Meeting;
import com.guillot.mareu.service.MeetingApiService;
import com.guillot.mareu.service.MeetingListService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewMeetingApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
      List<Meeting> meetings = service.getMeetings();
      List<Meeting> expectedMeeting = MeetingListService.MEETING_LIST;
      assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }

    @Test
    public void createMeetingWithSuccess() {
        service.getMeetings().clear();
        Meeting meeting = new Meeting(
                "24/01/2021", "11h00", "Réunion I", "Viper", "phoenix@gmail.com sage@hotmail.fr raze@outlook.com"
        );
        service.createMeeting(meeting);
        assertTrue(service.getMeetings().contains(meeting));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }
}