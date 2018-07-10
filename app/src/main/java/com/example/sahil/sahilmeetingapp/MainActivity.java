package com.example.sahil.sahilmeetingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*
IN THIS SAHIL MEETING APP MainActivity.java I HAVE DONE

Main activity consists of a title named “Schedule Meeting” and 2 buttons
 add and view in the middle of the screen.You can place any type of button and place it anywhere on the screen.

-	setContentView to main activity layout (mainActivity.xml) in onCreate function.
-	When ADD MEETING button is clicked you go to a new activity(AddActivity) which has 4 input fields
 and 2 buttons(as discussed before).
-	When VIEW SCHEDULE button is clicked you go to a new Activity(ViewActivity) which
shows all the meeting details fetched from database in a list.




 */
public class MainActivity extends AppCompatActivity {
    Button addButton,viewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*

My Main Activity (showing 2 options -add Meeting and View List of meetings)**********************


 */

        addButton=(Button)findViewById(R.id.addBtn);
        viewButton=(Button)findViewById(R.id.viewBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });





        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MainActivity.this,ViewActivity.class);
                startActivity(i);
            }
        });
    }
}
