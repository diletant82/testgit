package com.example.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    ImageView img;
    FloatingActionButton mFab;


    Album a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        img=findViewById(R.id.image);
// amnlfgnkldfgnas;lgnl;fgndfklng
        mFab = findViewById(R.id.fab);



        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Cao Dule!", Toast.LENGTH_SHORT).show();
            }
        });



        Intent i=getIntent();
        int id=i.getIntExtra("id",0);
        Log.d("test", "onCreate() called with: savedInstanceState = [" +id);


        IEndpoint iEndpoint = RetrofitClientInstance.getRetrofitInstance().create(IEndpoint.class);
        Call<Album> call = iEndpoint.getAlbum(id);
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                a=response.body();
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                getSupportActionBar().setTitle(a.getTitle());
                Glide.with(Main2Activity.this).load(a.getThumbnailUrl())
                        .apply(new RequestOptions()
                                .circleCrop())
                        .into(img);


            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(Main2Activity.this, "Error!", Toast.LENGTH_SHORT).show();


            }
        });
    }

}
