package com.example.sahil.sahilmeetingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;



/*

  IN THIS SAHIL MEETING APP  MeetingAdapter.java I HAVE DONE

●	This is an adapter class extent by ArrayAdapter(Pass data type as TodoItem).
public class MeetingAdapter extends ArrayAdapter<Meeting>

●	Receive the passed Meeting List through constructor.
     public MeetingAdapter(@NonNull Context context, int resource, @NonNull List<Meeting> arrayList

●	Override the getview method. You can take hint from following code snippet.

●	Make an object of type Meeting and get the current item into this object by the help of getItem(position) method.
●	Make two more objects of type TextView for title and description. Use the following code to understand.





 */

public class MeetingAdapter extends ArrayAdapter<Meeting> {

    private Context mycontext;
    int myResource;

    public MeetingAdapter(@NonNull Context context, int resource, @NonNull List<Meeting> arrayList) {
        super(context, resource,arrayList);
        mycontext = context;
        myResource = resource;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String topic = getItem(position).getTopic();
        String date = getItem(position).getDate();

        String duration = getItem(position).getDuration();
        String time=getItem(position).getTime();

        Meeting meeting=new Meeting(topic,duration,date,time);

        LayoutInflater layoutInflater=LayoutInflater.from(mycontext);

        convertView = layoutInflater.inflate(myResource,parent,false);


        TextView topicMeeting=(TextView)convertView.findViewById(R.id.tv1);
        TextView dateMeeting=(TextView)convertView.findViewById(R.id.tv2);
        TextView timeMeeting=(TextView)convertView.findViewById(R.id.tv3);
        TextView durationMeeting=(TextView)convertView.findViewById(R.id.tv4);

        dateMeeting.setText(date);
        topicMeeting.setText(topic);
        timeMeeting.setText(time);
        durationMeeting.setText(duration);

        return convertView;




    }


}
