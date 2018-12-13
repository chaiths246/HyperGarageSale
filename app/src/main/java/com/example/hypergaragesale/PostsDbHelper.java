package com.example.hypergaragesale;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by Taral on 3/12/2016.
 */
public class PostsDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Posts.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String IMAGE_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";
    private static final String DOUBLE_TYPE=" REAL";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Posts.PostEntry.TABLE_NAME + " (" +
                    Posts.PostEntry._ID + " INTEGER PRIMARY KEY," +
                    Posts.PostEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    Posts.PostEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    Posts.PostEntry.COLUMN_NAME_PRICE + TEXT_TYPE + COMMA_SEP +
                    Posts.PostEntry.COLUMN_NAME_IMAGE+ IMAGE_TYPE + COMMA_SEP +
                    Posts.PostEntry.COLUMN_NAME_LATITUDE+ TEXT_TYPE + COMMA_SEP +
                    Posts.PostEntry.COLUMN_NAME_LANGITUDE+ TEXT_TYPE +


            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Posts.PostEntry.TABLE_NAME;

    public PostsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }




    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public Cursor getWordMatches(String query, String[] columns) {
        String selection = DATABASE_NAME + " MATCH ?";
        String[] selectionArgs = new String[] {query+"*"};

        return query(selection, selectionArgs, columns);
    }

    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(Posts.PostEntry.TABLE_NAME);

     PostsDbHelper mDatabaseOpenHelper = null;
        Cursor cursor = builder.query(mDatabaseOpenHelper.getReadableDatabase(),
                columns, selection, selectionArgs, null, null, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }


}
