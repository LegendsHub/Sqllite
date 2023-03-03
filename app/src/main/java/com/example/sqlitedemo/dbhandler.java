package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;

public class dbhandler extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASE_NAME="BoolLibrary.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="my_library";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_TITLE="book_title";
    public static final String COLUMN_AUTHOR="book_author";
    public static final String COLUMN_PAGES="book_pages";
    public dbhandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String query="CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_TITLE+" TEXT, "+COLUMN_AUTHOR+" TEXT, "+COLUMN_PAGES+" INTEGER); ";
    db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    void addBook(String title,String author,Long pages){
        SQLiteDatabase sql=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_AUTHOR,author);
        cv.put(COLUMN_PAGES,pages);
        long r=sql.insert(TABLE_NAME,null,cv);
        if(r==-1){
            Toast.makeText(context, "failed...", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAll(){
        String q=" SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase sq=this.getReadableDatabase();
        Cursor cursor=sq.rawQuery(q, null);
        return cursor;
    }

    public void updatedata(String title,String pt) {
        SQLiteDatabase sql=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TITLE,title);
        long r=sql.update(TABLE_NAME,cv,"book_title=?",new String[]{pt});
        if(r==-1){
            Toast.makeText(context, "failed...", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    public void deletedata(String pt){
        SQLiteDatabase sql=this.getWritableDatabase();
        long r=sql.delete(TABLE_NAME,"book_title=?",new String[]{pt});
        if(r==-1){
            Toast.makeText(context, "failed...", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteall() {
        SQLiteDatabase sql=this.getWritableDatabase();
        sql.execSQL(" DELETE FROM "+ TABLE_NAME);

    }
}
