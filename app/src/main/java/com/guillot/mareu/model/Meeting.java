package com.guillot.mareu.model;

public class Meeting {

    private String hour;

    private String date;

    private String place;

    private String topic;

    private String participant;

    public Meeting(String date, String hour, String place, String topic, String participant) {
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.topic = topic;
        this.participant = participant;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

}
