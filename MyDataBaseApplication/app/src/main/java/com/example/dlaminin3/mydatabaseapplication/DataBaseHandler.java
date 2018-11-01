package com.example.dlaminin3.mydatabaseapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


// https://www.javatpoint.com/android-sqlite-tutorial
public class DataBaseHandler extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DataBaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
    void addContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PH_NO,contact.getPhone());

        db.insert(TABLE_CONTACTS,null,values);
        //insert
        db.close();

    }
    // getting a single pcntact based on id passed as parameter.

    Contact getContact(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,KEY_NAME,KEY_PH_NO},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        Contact contact;
        if(cursor!=null)
        {
            cursor.moveToFirst();
            String key = cursor.getString(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            contact = new Contact(Integer.parseInt(key),name,phone);

        }
        else
        {
            contact = new Contact(1,"defaltname","deaultNumber");

        }
        return contact;

    }


}
