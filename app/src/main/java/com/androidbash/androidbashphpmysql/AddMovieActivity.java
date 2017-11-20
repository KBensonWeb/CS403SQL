package com.androidbash.androidbashphpmysql;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AddMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
    }

    private void addMovieToDB(String movieInfo) {

        AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... movieInfo) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://kbensonweb.com/movies_add.php?name=" + movieInfo[0] + "&image=" + movieInfo[1] + "&genre=" + movieInfo[2])
                        .build();
                try {
                    client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                MoviesAdapter adapter;
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(movieInfo);

    }
}
