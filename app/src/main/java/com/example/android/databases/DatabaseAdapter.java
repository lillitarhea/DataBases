package com.example.android.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;

import java.security.MessageDigest;

/**
 * Created by Lill_Rhea on 23/03/2017.
 */
public class DatabaseAdapter  {

    static DatabaseHelper helper;

    public DatabaseAdapter(Context context)
    {
        helper=new DatabaseHelper(context);

    }

    static class DatabaseHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "vivzdatabase";
        private static final String TABLE_NAME = "VIVZTABLE";
        private static final int DATABASE_VERSION = 2;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " + PASSWORD + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS"+ TABLE_NAME;
        private Context context;




        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                com.example.android.databases.Message.message(context, "OnCreate is called");
            } catch (SQLException e) {
                com.example.android.databases.Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                com.example.android.databases.Message.message(context, "OnUpgrade is called");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                com.example.android.databases.Message.message(context, "" + e);
            }
        }

        public static long insertData(String user, String pass) {

            SQLiteDatabase db=helper.getWritableDatabase();

            ContentValues contentValues=new ContentValues();
            contentValues.put(DatabaseHelper.NAME,user);
            contentValues.put(DatabaseHelper.PASSWORD, pass);
            long id=db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
            return id;
        }
    }

}

