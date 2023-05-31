package com.example.evaluation;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "MY_COMPANY.DB";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE = "MEAL";

    private static final String KEY_ID = "id";
    private static final String STR_CATEGORY = "name";
    private static final String STR_CAT_THUMB = "logo";



//    private static final String STR_CAT_DES = "description";


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

//    public void  insert(String data){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelper.STR_CATEGORY_DES,data);
//        db.insert(DatabaseHelper.DATABASE_TABLE,null,contentValues);
//
//    }


    void insert(Categories categories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.STR_CATEGORY, categories.getStrCategory());
        values.put(DatabaseHelper.STR_CAT_THUMB, categories.getStrCategoryThumb());
        db.insert(DATABASE_TABLE,null, values);
        db.close();
    }





//    public List<PojoClass> getAllContacts() {
//        List<PojoClass> contactList = new ArrayList<>();
//        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                PojoClass contact = new PojoClass();
//                contact.setIdCategory(cursor.getString(0));
//                contact.setStrCategory(cursor.getString(1));
//                contact.setStrCategoryThumb(cursor.getString(2));
//                contact.setStrCategoryDescription(cursor.getString(2));
//                contactList.add(contact);
//            } while (cursor.moveToNext());
//        }
//        return contactList;
//    }







//
//    public Cursor getAllData() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM DATABASE_NAME", null);
//        return cursor;
//    }




//    Categories getData(String des) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(DATABASE_TABLE, new String[]{STR_CATEGORY_DES}, STR_CATEGORY_DES + "",
//                new String[]{des}, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Categories contact = new Categories((cursor.getString(0)));
//        return contact;
//
//    }


}