package com.guillot.mareu.model;

public class Meeting {

 private double hour;

 private int date;

 private String place;

 private String topic;

 private String participant;

    public Meeting(int date, double hour, String place, String topic, String participant) {
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.topic = topic;
        this.participant = participant;
    }


    public void meetingInfo (int date, double hour, String place, String topic) {
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.topic = topic;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
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
