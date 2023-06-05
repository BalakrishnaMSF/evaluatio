package com.example.evaluation;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "MY_COMPANY.DB";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE = "MEAL";

    private static final String KEY_ID = "id";
    private static final String STR_CATEGORY = "name";
    private static final String STR_CAT_THUMB = "logo";

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DB_QUERY ="CREATE TABLE " + DATABASE_TABLE + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + STR_CATEGORY + " TEXT,"
                + STR_CAT_THUMB + " TEXT)";
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);

    }


    void insert(Categories categories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.STR_CATEGORY, categories.getStrCategory());
        values.put(DatabaseHelper.STR_CAT_THUMB, categories.getStrCategoryThumb());
        db.insert(DATABASE_TABLE,null, values);
        db.close();
    }

    public void remove(Categories categories) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("MEAL",categories.getIdCategory(),null);
        db.close();
    }


    public List<Categories> getAllContacts() {
        List<Categories> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Categories contact = new Categories(DatabaseHelper.STR_CATEGORY,DatabaseHelper.STR_CAT_THUMB);
                contact.setIdCategory(cursor.getString(0));
                contact.setStrCategory(cursor.getString(1));
                contact.setStrCategoryThumb(cursor.getString(2));
                contact.setStrCategoryDescription(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

}