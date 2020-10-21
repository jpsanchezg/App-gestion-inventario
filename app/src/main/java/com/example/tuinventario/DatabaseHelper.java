package com.example.tuinventario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;





public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "inventario";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String inventario, Object o, int i) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Productos.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Productos.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(String note,String cantidad) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Productos.COLUMN_MATERIAL, note);
        values.put(Productos.COLUMN_CANTIDAD, cantidad);


        // insert row
        long id = db.insert(Productos.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Productos getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Productos.TABLE_NAME,
                new String[]{Productos.COLUMN_ID, Productos.COLUMN_MATERIAL, Productos.COLUMN_CANTIDAD},
                Productos.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Productos note = new Productos(
                cursor.getInt(cursor.getColumnIndex(Productos.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Productos.COLUMN_MATERIAL)),
                cursor.getString(cursor.getColumnIndex(Productos.COLUMN_CANTIDAD)));



        // close the db connection
        cursor.close();

        return note;
    }

    public List<Productos> getAllNotes() {
        List<Productos> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Productos.TABLE_NAME + " ORDER BY " +
                Productos.COLUMN_CANTIDAD + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Productos product = new Productos();
                product.setId(cursor.getInt(cursor.getColumnIndex(Productos.COLUMN_ID)));
                product.setMaterial(cursor.getString(cursor.getColumnIndex(Productos.COLUMN_MATERIAL)));
                product.setCanti(cursor.getString(cursor.getColumnIndex(Productos.COLUMN_CANTIDAD)));
//                product.setUsado(cursor.getString(cursor.getColumnIndex(Productos.COLUMN_USADO)));

                notes.add(product);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Productos.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Productos note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Productos.COLUMN_MATERIAL, note.getMaterial());

        // updating row
        return db.update(Productos.TABLE_NAME, values, Productos.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }
    public int updateCanti(Productos note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values2 = new ContentValues();

        values2.put(Productos.COLUMN_CANTIDAD, note.getCanti());

        // updating row

        return db.update(Productos.TABLE_NAME, values2, Productos.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }
    /*public int updateUsad(Productos note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values3 = new ContentValues();

        values3.put(Productos.COLUMN_USADO, note.getUsado());

        // updating row

        return db.update(Productos.TABLE_NAME, values3, Productos.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }*/



    public void deleteNote(Productos note) {
        SQLiteDatabase db = this.getWritableDatabase();
      db.delete(Productos.TABLE_NAME, Productos.COLUMN_MATERIAL + " = ?", new String[]{String.valueOf(note.getMaterial())});
        db.close();
    }

}
