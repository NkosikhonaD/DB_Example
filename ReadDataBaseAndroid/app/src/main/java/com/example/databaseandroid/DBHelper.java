package com.example.databaseandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.databaseandroid.StudentDataBaseContract.StudentEntry;


public class DBHelper extends SQLiteOpenHelper
{
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+StudentEntry.TABLE_NAME+ " ("+
            StudentEntry._ID+ " INTEGER PRIMARY KEY,"+StudentEntry.COLUMN_NAME+ " TEXT,"+StudentEntry.COLUMN_SURNAME+" TEXT,"+StudentEntry.COLUMN_STUDENT_ID+" TEXT)";

    private static final String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS "+StudentEntry.TABLE_NAME;
    public static final int DATABSE_VERSION=1;
    public static final String DATABASE_NAME = "StudentDataBase.db";


    public DBHelper(Context context)

    {
        super(context,DATABASE_NAME,null,DATABSE_VERSION);
    }


    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        onUpgrade(db,oldVersion,newVersion); //check this out
    }

}
