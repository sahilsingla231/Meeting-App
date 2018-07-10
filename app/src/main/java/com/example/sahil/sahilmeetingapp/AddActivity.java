package com.example.sahil.sahilmeetingapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar; //*****************************************************************


/*
IN THIS SAHIL MEETING APP AddActivity.Java I HAVE DONE
-setContentView to main activity layout (add_activity.xml) in onCreate function.
-Make connection with SQLite using the SQLiteDatabase and SQLiteOpenHelper classes.
  Define the database version, table name, names of all columns apply constraints like primary key as per your need.
  In our code, DBHelper.java handles all these work. You can also split the code into three parts
  i.e. MeetingProvider.java, MeetingContract.java, MeetingDbHelper.java.
-Make a function insert which accepts parameters and add as data into database by using
getWritableDatabase class and ContentValues use to object of ContentValues to put values in columns.
Here is the sample code Snippet-





 */
public class AddActivity extends AppCompatActivity {


    DBHelper dbHelper;
    EditText topicET,durationET;
    TextView dateTV,timeTV;
    DatePickerDialog.OnDateSetListener dateSetListener;
    TimePickerDialog timePickerDialog;
    Button alarm,addMeeting;
    int year,month,day;

    int currentHour,currentMin,hour,min;
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        /*
       My Add Activity (having all 4 input fields, set reminder button nad add button)**********************

         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_main);
        dbHelper= new DBHelper(this);
        topicET = (EditText) findViewById(R.id.topicET);
        dateTV = (TextView) findViewById(R.id.dateTV);
        timeTV=(TextView)findViewById(R.id.timeTV);

        durationET=(EditText)findViewById(R.id.durationET);

        alarm=(Button)findViewById(R.id.alarmBtn);
        addMeeting=(Button)findViewById(R.id.addMeetingBtn);

        dateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                 year = cal.get(Calendar.YEAR);
                 month = cal.get(Calendar.MONTH);
                 day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        timeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar=Calendar.getInstance();
                currentHour=calendar.get(Calendar.HOUR_OF_DAY);
                currentMin=calendar.get(Calendar.MINUTE);



                timePickerDialog = new TimePickerDialog(AddActivity.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfday, int minOfday) {


                        timeTV.setText(hourOfday + ":" + minOfday);
                        hour = hourOfday;
                        min = minOfday;

                    }
                },currentHour,currentMin,true);
                timePickerDialog.show();
            }

        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = day + "/" + month + "/" + year;
                dateTV.setText(date);
            }
        };


        /*
        IN THIS SAHIL MEETING APP ALARM INTEGRATION I HAVE DONE
        -	You can also use Alarm Manager. And to get the Alarm we will use Broadcast receiver.
-	Functioning - Basically the alarm function takes the time in milliseconds.
Example: if we set the alarm of 6:30 pm and we set this alarm on 6:15pm the it takes the milliseconds as parameters
that after how much milliseconds the alarm is to activated.
So we got to convert the values to milliseconds before passing in the Alarm Manager functions.
Alarm Integration

- In the AddActivity when we click on Set Reminder the following should be done.
-For this purpose we will use Intents and to set the alarm we will use the action AlarmClock.ACTION_SET_ALARM
-Set the time to the time entered by user.






         */
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Using phone inbuilt alarm so not able to set there as per date in my app...
                // as per quidelines intent used was this only.

                Intent intent=new Intent(AlarmClock.ACTION_SET_ALARM);

                intent.putExtra(AlarmClock.EXTRA_HOUR,hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,min);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE,"MEETING TIME!!!");

                 startActivity(intent);
                Toast.makeText(AddActivity.this, "Reminder set for "+dateTV.getText().toString()+" time "+timeTV.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t=topicET.getText().toString();
                String du=durationET.getText().toString();
                String d=dateTV.getText().toString();
                String time=timeTV.getText().toString();

                if(t.equals("")||du.equals("")||d.equals("")||time.equals("")){

                    Toast.makeText(AddActivity.this, "Please fill all details!! :-)", Toast.LENGTH_SHORT).show();
                }else{

                    Boolean insert = dbHelper.checkTime(t,du);

                    if(insert == true){

                        Boolean insertData=dbHelper.insert(t,du,d,time);
                        if(insertData==true){
                            Toast.makeText(AddActivity.this, "Successfully added! :-)", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(AddActivity.this,ViewActivity.class);
                            i.putExtra("topic",t);
                            i.putExtra("duration",du);
                            i.putExtra("date",d);
                            i.putExtra("time",time);
                            startActivity(i);
                        }else{
                            Toast.makeText(AddActivity.this, "Couldn't add. :-(", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(AddActivity.this, "Time already exists, So choose some other time", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}
