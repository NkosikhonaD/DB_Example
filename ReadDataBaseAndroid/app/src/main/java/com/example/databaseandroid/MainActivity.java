package com.example.databaseandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.databaseandroid.StudentDataBaseContract.StudentEntry;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText surnameEditText;
    EditText studentIdEditx;

    static final String EXTRA_MESSAGE = "com.example.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nameEditText = findViewById(R.id.studentName);
        surnameEditText=findViewById(R.id.studentSurname);
        studentIdEditx = findViewById(R.id.studentNum);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String message = getData();
                //Snackbar.make(view, "Starting an intent"+message, Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this,readdata.class);

                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void insertData(View view)
    {
        DBHelper dbHelper = new DBHelper(getBaseContext());  //creates database by calling onCreate method DBHelper
        SQLiteDatabase db = dbHelper.getWritableDatabase(); //makes database writable

        ContentValues values = new ContentValues();
        values.put(StudentEntry.COLUMN_NAME,nameEditText.getText().toString());
        values.put(StudentEntry.COLUMN_SURNAME,surnameEditText.getText().toString());
        values.put(StudentEntry.COLUMN_STUDENT_ID,studentIdEditx.getText().toString());

        long row = db.insert(StudentEntry.TABLE_NAME,null,values);

        Snackbar.make(view, "Data inserted in database in row number "+row, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public String getData()
    {

        DBHelper dbHelper = new DBHelper(getBaseContext());  //creates database by calling onCreate method DBHelper
        SQLiteDatabase db = dbHelper.getReadableDatabase(); //makes database writable
        String[] columns ={StudentEntry.COLUMN_STUDENT_ID,StudentEntry.COLUMN_SURNAME};

        Cursor cursor = db.query(StudentEntry.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer mybuffer = new StringBuffer();
        while(cursor.moveToNext())
        {
            String studentID = cursor.getString(cursor.getColumnIndex(StudentEntry.COLUMN_STUDENT_ID));
            String surname = cursor.getString(cursor.getColumnIndex(StudentEntry.COLUMN_SURNAME));

            mybuffer.append(studentID +" " +surname+"\n");


        }
        return mybuffer.toString();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
