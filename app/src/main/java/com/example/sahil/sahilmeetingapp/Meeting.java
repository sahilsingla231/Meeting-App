package com.example.sahil.sahilmeetingapp;

public class Meeting {

    private String topic;
    private String duration;
    private String date;
    private String time;


    /*
    IN THIS SAHIL MEETING APP  Meeting.java I HAVE DONE

    ●	This java class will describe the data elements present in one list item.
●	For this app topic,duration,date and time are data elements.
●	Also use setter and getter methods for ease in modify and access variable value.




     */
    public Meeting(String topic, String duration, String date, String time){

        this.topic=topic;
        this.duration=duration;
        this.date=date;
        this.time=time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
