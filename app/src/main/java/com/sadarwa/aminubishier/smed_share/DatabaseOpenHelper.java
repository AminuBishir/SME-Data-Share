package com.sadarwa.aminubishier.smed_share;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * This class will handle the database operations
 * Created by Aminu Bishier on 2/30/2018.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "user.db";
    private static String TABLE_NAME = "login";
    private static String COL1 = "regCode";
    private static String COL2 = "Password";
    private static String COL3 = "company_name";
    private static String company_name;
    private static String student_name;

    //create the database via DatabaseOpenHelper constructor
    public DatabaseOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    //query for creating the database table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +"(regCode TEXT, Password TEXT, company_name TEXT)");

    }

    //create the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

db.execSQL("DROP TABLE IF EXIST" +DATABASE_NAME);
         onCreate(db);
    }

    //Method for inserting data into the database
    public boolean insertData(String regCode, String password, String company_name){
        SQLiteDatabase putData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,regCode);
        contentValues.put(COL2,password);
        contentValues.put(COL3,company_name);

        long result = putData.insert(TABLE_NAME,null,contentValues);
        //return false if data is not inserted
        if(result==-1)
           return false;

        //return true if the data is inserted
        else
            return true;
    }
    //Method for returning company_name of the user from the database
    public String getCompany_name(){
        SQLiteDatabase getData = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " +TABLE_NAME;
        Cursor cursor = getData.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
             company_name = cursor.getString(cursor.getColumnIndex(COL3));
            return company_name;
        }
        else
            return null;
    }

    //Method to retrieve the name of the user/student form the database
    public String getStudentName(){
        SQLiteDatabase getData = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " +TABLE_NAME;
        Cursor cursor = getData.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            student_name = cursor.getString(cursor.getColumnIndex(COL3));
            return student_name;
        }
        else
            return " ";
    }
}
