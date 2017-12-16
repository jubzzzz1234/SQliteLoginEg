package com.example.jubin.sqlitelogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jubin on 29/8/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="details.db";
    private static final String TABLE_NAME="details";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_PASS="password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE="create table details (id integer primary key not null , "+
            "name text not null , password text not null);";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;

    }
    public void insertDetails(Details d)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from details";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,d.getName());
        values.put(COLUMN_PASS,d.getPassword());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public String searchpass(String name)
    {
        db=this.getReadableDatabase();
        String query="select name, password from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        String usernm,userpass;
        userpass="not found";
        if (cursor.moveToFirst())
        {
            do{
                usernm=cursor.getString(0);

                if(usernm.equals(name))
                {
                    userpass=cursor.getString(1);
                    break;

                }
            }
            while(cursor.moveToNext());
        }
        return userpass;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
