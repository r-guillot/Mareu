package com.guillot.mareu.event;

import com.guillot.mareu.model.Meeting;

public class DeleteEvent {

    public Meeting meeting;

    public DeleteEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}
