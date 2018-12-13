package com.example.hypergaragesale;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Filter;
import android.widget.Toast;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, query, Toast.LENGTH_LONG).show();
//use the query to search your data somehow
            //String sql = "SELECT * FROM " + DbConstants.TABLE_WORDS + " WHERE " + DbConstants.WORD + " LIKE '%" + searchText + "%'";
            PostsDbHelper helper = new PostsDbHelper(this);

            Cursor cursor = helper.getWordMatches(query, null);


            if (cursor.getCount() > 0) {
                // means search has returned data

                if (cursor.moveToFirst()) {
                    do {
                        String wordId = cursor.getString(cursor.getColumnIndex(Posts.PostEntry.TABLE_NAME));
//                        String word = cursor.getString(cursor.getColumnIndex(DbConstants.WORD));
//                        String meaning = cursor.getString(cursor.getColumnIndex(DbConstants.MEANING));
//                        String category = cursor.getString(cursor.getColumnIndex(DbConstants.CATEGORY));


                        // display your search result here in RecyclerView or in any manner
                    } while (cursor.moveToNext());
                }

            } else {
                Toast.makeText(this, "No data was found in the system!", Toast.LENGTH_LONG).show();
            }
            cursor.close();

        }

    }

}

