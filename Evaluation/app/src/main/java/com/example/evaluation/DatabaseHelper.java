package com.example.evaluation;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "MY_COMPANY.DB";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "MEAL";
    static final String STR_CATEGORY_DES = "strCategoryDescription";

    private static final String CREATE_DB_QUERY = "CREATE TABLE "+ DATABASE_TABLE +"("
            + STR_CATEGORY_DES + " TEXT "+")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);

    }

    public void  insert(String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.STR_CATEGORY_DES,data);
        db.insert(DatabaseHelper.DATABASE_TABLE,null,contentValues);

    }


    Categories getData(String des) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE, new String[]{STR_CATEGORY_DES}, STR_CATEGORY_DES + "",
                new String[]{des}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Categories contact = new Categories((cursor.getString(0)));
        return contact;

    }

}

