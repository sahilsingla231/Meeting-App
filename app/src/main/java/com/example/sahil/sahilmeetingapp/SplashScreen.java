package com.example.sahil.sahilmeetingapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


/*

IN THIS SAHIL MEETING APP SplashScreen.java I HAVE DONE

Layout of Splash Screen will be provided, which will contain an image and some text
-	Create a SplashScreen.java Activity and setContentView as the Splash Screen Layout (SplashScreen.xml)
 in the onCreate function.
-	Create a new handler and set a time delay of 2 seconds which means 2000 milliseconds.
-	Inside the new handler create a function and set an intent which takes the flow of app
 from splash screen to main activity.Here is the code snippet for the same-


Basically the Splash screen stays for two seconds
and then the function in the handler fires an intent which takes the flow to main activity.


 */

public class SplashScreen extends AppCompatActivity {

    //Splash Screen (first screen)***********
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        },2000);    // FOR 2 SECOND On Mobile Screen**************
    }
}
