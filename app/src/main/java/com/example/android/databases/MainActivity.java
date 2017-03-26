package com.example.android.databases;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  DatabaseAdapter dbhelper;
    EditText userName,password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName=(EditText)findViewById(R.id.editUserName);
        password=(EditText)findViewById(R.id.editPassword);



        dbhelper=new DatabaseAdapter(this.getApplicationContext());


    }

    public void addUser(View view)
    {
        String user=userName.getText().toString();
        String pass=password.getText().toString();
        long id=DatabaseAdapter.insertData(user,pass);
        if (id<0)
        {
            Message.message(this,"Unsuccessful");
        }

        else
        {
            Message.message(this,"Successful");

        }

    }
}
