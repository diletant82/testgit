package com.example.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private LayoutInflater inflater;
    private Context myContext;
    List<Album> albums;

    public MyAdapter(Context context, List<Album> list) {
        inflater = LayoutInflater.from(context);
        this.albums = list;
        this.myContext = context;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.album, parent, false);
        MyHolder h = new MyHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        final Album album = albums.get(position);
        Glide.with(myContext).load(album.getThumbnailUrl())
                .apply(new RequestOptions()
                        .circleCrop())
                .into(holder.img);
        holder.txt.setText(album.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(myContext, Main2Activity.class);
                i.putExtra("id",album.getId());
                myContext.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img;
        TextView txt;
        RelativeLayout r;


        public MyHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txt = itemView.findViewById(R.id.text);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
