package com.example.hitorigoto.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hitorigoto.Models.User;

import java.util.ArrayList;

public class dbUser extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_User";
    public static final String TABLE_NAME = "user";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "id_user";
    public static final String KEY_NAME = "full_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT," +
            KEY_PASSWORD + " TEXT);";

    public dbUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        onCreate(db);
    }

    public long addUserDetail(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getFull_name());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        return db.insert(TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                User user = new User();
                user.setId_user(c.getInt(c.getColumnIndex(KEY_ID)));
                user.setFull_name(c.getString(c.getColumnIndex(KEY_NAME)));
                user.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                user.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
                userArrayList.add(user);
            } while (c.moveToNext());
        }
        c.close();
        return userArrayList;
    }

    @SuppressLint("Range")
    public int checkAccount(String email, String password) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                String temp_email = c.getString(c.getColumnIndex(KEY_EMAIL));
                String temp_password = c.getString(c.getColumnIndex(KEY_PASSWORD));
                if (email.equals(temp_email)) {
                    if (password.equals(temp_password)) {
                        // Return 1 if account is found and password matches (SUCCESSFUL LOGIN)
                        return 1;
                    } else {
                        // Return 2 if account is found but password is incorrect
                        return 2;
                    }
                }
            } while (c.moveToNext());
        }
        // Return 0 if account is not found
        return 0;
    }

    @SuppressLint("Range")
    public User getAccount(String email, String password) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                KEY_EMAIL + "='" + email + "' AND " + KEY_PASSWORD + "='" + password + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        User logging_in = new User();

        if (c.moveToFirst()) {
            logging_in.setId_user(c.getInt(c.getColumnIndex(KEY_ID)));
            logging_in.setFull_name(c.getString(c.getColumnIndex(KEY_NAME)));
            logging_in.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
            logging_in.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
        }
        c.close();
        return logging_in;
    }
}
