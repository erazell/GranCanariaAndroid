package com.example.janusz.finalapp_v1.SQLiteNotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by janusz on 11.04.2018.
 */

public class DbNotes extends SQLiteOpenHelper {

    public static final String DB_NOTES = "mynote_db";
    public static final int DB_VERSION = 2;

    public static final String KEY_ID = "id";
    public static final String KEY_NOTE = "name";
    public static final String KEY_DATE = "phone";
    public static final String TABLE_NOTE = "contacts";

    public DbNotes(Context context)
    {
        super(context, DB_NOTES, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table =
                "CREATE TABLE " + TABLE_NOTE + "("
                        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOTE + " TEXT,"
                        + KEY_DATE + " TEXT" + ")";
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String delete_query = "DROP Table " + TABLE_NOTE + " IF EXITS";
        db.execSQL(delete_query);
        onCreate(db);
    }


    public void addContact(Notes contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NOTE, contact.getNote());
        contentValues.put(KEY_DATE,contact.getDate());

        db.insert(TABLE_NOTE,null, contentValues);
        db.close();
    }


    public ArrayList<Notes> getAllContacts() {
        ArrayList<Notes> contacts = new ArrayList<>();

        String select_query = "select * from " + TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
//        cursor.moveToPosition(0);

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NOTE));
                String phone = cursor.getString(cursor.getColumnIndex(KEY_DATE));
                Notes contact = new Notes(id , name, phone);

                contacts.add(contact);

            } while (cursor.moveToNext());}

        return contacts;
    }


//get contact by id from database
    public Notes getContactById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select_query = "select * from " + TABLE_NOTE + " where id=" + id;

        Cursor cursor = db.rawQuery(select_query, null);

        Notes contact = null;
        if (cursor.moveToFirst()) {

            int id_contact = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NOTE));
            String phone = cursor.getString(cursor.getColumnIndex(KEY_DATE));
            contact = new Notes(id, name, phone);
        }
        return contact;
    }
  // get contact from database (2)
  public Notes getContactById2 (int id){
        Notes contact = null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTE, new String[]{"id", "name", "phone", }, "id=?",
                new String[]{String.valueOf(id)}, null, null, null);

      if (cursor.moveToFirst()) {

          int id_contact = cursor.getInt(cursor.getColumnIndex(KEY_ID));
          String name = cursor.getString(cursor.getColumnIndex(KEY_NOTE));
          String phone = cursor.getString(cursor.getColumnIndex(KEY_DATE));

           contact = new Notes(id, name, phone);
      }

        return  contact;

  }

  public void updateContact(Notes contact){
      SQLiteDatabase db = getWritableDatabase();

      ContentValues contentValues = new ContentValues();
      contentValues.put(KEY_NOTE, contact.getNote());
      contentValues.put(KEY_DATE,contact.getDate());

      db.update(TABLE_NOTE, contentValues, "id=?", new String[]{String.valueOf(contact.getId())});
  }
  public void deleteContact(int id){
      SQLiteDatabase db = this.getWritableDatabase();
      db.delete(TABLE_NOTE, "id=?", new String[]{String.valueOf(id)});

  }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
}

