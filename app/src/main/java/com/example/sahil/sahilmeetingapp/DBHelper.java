package com.example.sahil.sahilmeetingapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//USED SQLITE STORAGE IN SAHIL MEETING APP FOR DATABASE****
public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context){

        super(context,"mDB",null,1); //***************************DATABASE
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table if not exists meeting (topic text,duration text,date text,time text primary key)");
            //CREATE QUERY******************

    }

    @Override

    //UPGRADE QUERY***********
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("drop table if exists meeting");
    }

    public boolean insert(String topic,String duration,String date,String time){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("topic",topic);
        contentValues.put("duration",duration);
        contentValues.put("date",date);
        contentValues.put("time",time);
        long ins = db.insert("meeting",null,contentValues);

        if(ins == -1){
            return false;
        }else {
            return true;
        }

    }

    public boolean checkTime(String time,String duration){

        SQLiteDatabase db=this.getReadableDatabase();
        //SELECT QUERY*********************
        Cursor c= db.rawQuery("select * from meeting where time=? and duration=?",new String[]{time,duration});

        if(c.getCount()>0){
            return false;
        }else {
            return true;
        }
    }

    public boolean UserPass(String username,String password){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from job where user=? and password=?",new String[] {username,password});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
}
