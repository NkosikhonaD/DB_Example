package com.example.databaseandroid;

import android.content.ContentValues;
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

public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText surnameEditText;
    EditText studentIdEditx;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        values.put(StudentDataBaseContract.StudentEntry.COLUMN_NAME,nameEditText.getText().toString());
        values.put(StudentDataBaseContract.StudentEntry.COLUMN_SURNAME,surnameEditText.getText().toString());
        values.put(StudentDataBaseContract.StudentEntry.COLUMN_STUDENT_ID,studentIdEditx.getText().toString());

        long row = db.insert(StudentDataBaseContract.StudentEntry.TABLE_NAME,null,values);

        Snackbar.make(view, "Data inserted in database in row number "+row, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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
