package com.androidbash.androidbashphpmysql;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.androidbash.androidbashphpmysql.R.id.genreA;
import static com.androidbash.androidbashphpmysql.R.id.imageA;
import static com.androidbash.androidbashphpmysql.R.id.mTitleA;

public class AddMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
    }

    public void addMovieToDB(View view) {
        EditText nameIn = (EditText) findViewById(mTitleA);
        String name = nameIn.getText().toString();
        EditText genreIn = (EditText) findViewById(genreA);
        String genre = genreIn.getText().toString();
        EditText imageIn = (EditText) findViewById(imageA);
        String image = imageIn.getText().toString();

        AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... movieInfo) {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("name", movieInfo[0])
                        .add("image", movieInfo[1])
                        .add("genre", movieInfo[2])
                        .build();
                Request request = new Request.Builder()
                        .url("https://kbensonweb.com/movies_add.php")
                        .post(formBody)
                        .build();
                try {
                    client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        asyncTask.execute(name,image,genre);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
