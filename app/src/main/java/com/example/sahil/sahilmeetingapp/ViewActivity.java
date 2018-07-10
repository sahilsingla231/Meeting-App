package com.example.sahil.sahilmeetingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    ListView listView;

    MeetingAdapter arrayAdapter;
    ArrayList<Meeting> arrayList;

/*

  My View Activity (displays the list of meetings with its details)*************************

 */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);

        listView=(ListView)findViewById(R.id.list);
        Intent i=getIntent();
        String topic=getIntent().getStringExtra("topic");
        String duration=getIntent().getStringExtra("duration");
        String date=getIntent().getStringExtra("duration");
        String time=getIntent().getStringExtra("time");

        viewMeeting();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();

                return true;

            }
        });
    }



    void viewMeeting(){

        arrayList=new ArrayList<Meeting>();

        try{


            SQLiteDatabase db=this.openOrCreateDatabase("mDB",MODE_PRIVATE,null);
            Cursor c=db.rawQuery("select * from meeting",null);
            int topicIndex=c.getColumnIndex("topic");
            int durationIndex=c.getColumnIndex("duration");
            int dateIndex=c.getColumnIndex("date");
            int timeIndex=c.getColumnIndex("time");
            c.moveToFirst();

            while (c!=null){

                arrayList.add(new Meeting(c.getString(topicIndex),c.getString(durationIndex),c.getString(dateIndex),c.getString(timeIndex)));
                c.moveToNext();
            }



        }
        catch (Exception e){
            //To catch exceptions of any type***********
            System.out.println(e.getMessage());
        }



        finally {
            //always run*******************

            arrayAdapter=new MeetingAdapter(this,R.layout.adapter_main,arrayList);
            listView.setAdapter(arrayAdapter);
        }
    }
}
