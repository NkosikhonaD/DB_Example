package com.example.databaseandroid;

import android.provider.BaseColumns;

public final class StudentDataBaseContract
{
    private StudentDataBaseContract(){}

    public static class StudentEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "studenttable";
        public static final String COLUMN_NAME="Name";
        public static final String COLUMN_SURNAME="Surname";
        public static final String COLUMN_STUDENT_ID="StudentID";

    }

}
