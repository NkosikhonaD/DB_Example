package com.example.dlaminin3.mydatabaseapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView txtname = findViewById(R.id.textView);
        TextView txtphone= findViewById(R.id.textView2);
        final DataBaseHandler db = new DataBaseHandler(this);
        final EditText name = findViewById(R.id.editText);
        final EditText phone = findViewById(R.id.editText2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    db.addContact(new Contact(name.getText().toString(),phone.getText().toString()));
                    Snackbar.make(view, "Contact succesfully created in database", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                catch (Exception ex){
                    Snackbar.make(view, "An error occured could not insert contact", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                }



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
