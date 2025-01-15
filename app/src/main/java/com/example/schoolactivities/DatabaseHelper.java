package com.example.schoolactivities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;



// could have definitely made this much more simple, but decided to go for a straight forward approach

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }
    public Context context;
    public static final String DATABASE_NAME = "School.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "upit_teachers";
    public static final String COLUMN_ID= "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PASS = "password";
    public static final String TABLE_NAME2 = "upit_students";
    public static final String COLUMN_ID2= "id";
    public static final String COLUMN_EMAIL2 = "student_email";
    public static final String COLUMN_FIRSTNAME2 = "student_firstName";
    public static final String COLUMN_LASTNAME2 = "student_lastName";
    public static final String COLUMN_PHONE2 = "student_phone";
    public static final String COLUMN_PASS2 = "student_password";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, " +
                        COLUMN_PASS + " TEXT NOT NULL, " +
                        COLUMN_FIRSTNAME + " TEXT, " +
                        COLUMN_LASTNAME + " TEXT, " +
                        COLUMN_PHONE + " TEXT);";
        db.execSQL(query);

        String query2 = " CREATE TABLE " + TABLE_NAME2 + " (" +
                COLUMN_ID2  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL2 + " TEXT NOT NULL UNIQUE, " +
                COLUMN_PASS2 + " TEXT NOT NULL, " +
                COLUMN_FIRSTNAME2 + " TEXT, " +
                COLUMN_LASTNAME2 + " TEXT, " +
                COLUMN_PHONE2 + " TEXT);";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }


    public Boolean insertData(String email, String password, String firstName, String lastName, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASS, password);
        contentValues.put(COLUMN_FIRSTNAME, firstName);
        contentValues.put(COLUMN_LASTNAME, lastName);
        contentValues.put(COLUMN_PHONE, phone);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1) return false;
        else return true;
    }

    public Boolean insertDataStudent(String email, String password, String firstName, String lastName, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL2, email);
        contentValues.put(COLUMN_PASS2, password);
        contentValues.put(COLUMN_FIRSTNAME2, firstName);
        contentValues.put(COLUMN_LASTNAME2 , lastName);
        contentValues.put(COLUMN_PHONE2, phone);
        long result = db.insert(TABLE_NAME2, null, contentValues);
        if(result==-1) return false;
        else return true;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                                    TABLE_NAME + " WHERE " +
                                    COLUMN_EMAIL + " = ?", new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }

    public Boolean checkPassword(String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME + " WHERE " +
                COLUMN_PASS + " = ?", new String[] {password});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }

    public Boolean checkEStudent(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME2 + " WHERE " +
                COLUMN_EMAIL2 + " = ?", new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }

    public Boolean checkPStudent(String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME2 + " WHERE " +
                COLUMN_PASS2 + " = ?", new String[] {password});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }

    // supposed to be used for the register activity to verify if phone number already exists

    public Boolean checkPhone(String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME + " WHERE " +
                COLUMN_PHONE +
                " = ?", new String[] {phone});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }

    // same here but for the student

    public Boolean checkPhoneStudent(String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME2 + " WHERE " +
                COLUMN_PHONE2 +
                " = ?", new String[] {phone});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }
    public String getName(String firstName){
        return COLUMN_FIRSTNAME;
    }
}
