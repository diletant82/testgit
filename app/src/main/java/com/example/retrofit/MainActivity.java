package com.example.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    IEndpoint iEndpoint;
    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<Album> albums;
    Button button;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.find);
        button.setOnClickListener(this);
        editText=findViewById(R.id.myEditText);

        iEndpoint = RetrofitClientInstance.getRetrofitInstance().create(IEndpoint.class);
        Call<List<Album>> call = iEndpoint.getAll();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void generateDataList(List<Album> list) {
        recyclerView = findViewById(R.id.recycler);
        MyAdapter adapter = new MyAdapter(this,list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        String s= editText.getText().toString();
        iEndpoint = RetrofitClientInstance.getRetrofitInstance().create(IEndpoint.class);
        Call<List<Album>> call = iEndpoint.getAllById(Integer.parseInt(s));
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                generateDataList(response.body());
                Log.d("test", "onResponse() called with: call = [" + call.request().url() );


            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
