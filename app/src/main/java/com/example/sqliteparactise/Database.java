package com.example.sqliteparactise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String DataBaseName = "MyDataBase.db" ;
    public static final String tableName = "Information";
    public static final String table_id = "Id";
    public static final String table_name = "Name";
    public static final String table_age = "Age";
    public static final String table_gender = "Gender";
    public static final String table_activity = "Activity";
    public static final String table_course = "Course";
    public static final String table_society = "Society";


    public Database(@Nullable Context context) {
        super(context, DataBaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL("create table Information " + "(Id integer primary key, Name text , Age text , Gender text , Course text , Activity text  , Society text)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Information ");
          onCreate(sqLiteDatabase);
    }

    public boolean insertValues(String name , String age , String gender , String course , String activity , String society ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name" , name);
        contentValues.put("Age" ,age);
        contentValues.put("Gender" , gender);
        contentValues.put("Course" , course);
        contentValues.put("Activity" , activity);
        contentValues.put("Society" , society);
        sqLiteDatabase.insert("Information" , null , contentValues);
        return true;
    }

    public Cursor getData(int value){
         SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
         Cursor res = sqLiteDatabase.rawQuery("select * from Information where id="+value+"",null);
         return res;
    }

    public boolean UpdateInformation(Integer id , String name ,String age , String gender , String course , String activity , String society){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name" , name);
        contentValues.put("Age" ,age);
        contentValues.put("Gender" , gender);
        contentValues.put("Course" , course);
        contentValues.put("Activity" , activity);
        contentValues.put("Society" , society);
        sqLiteDatabase.update("Information" , contentValues , "id = ? " , new String[] {Integer.toString(id)});
        return true;
    }

    public Integer deleteInformation(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("Information" , " id = ? " , new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllInformation(){
        ArrayList<String> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Information " ,null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            arrayList.add(cursor.getString(cursor.getColumnIndex(table_name)));
            cursor.moveToNext();
        }
       return arrayList;
    }
}
