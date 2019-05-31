package com.codeepisodes.myapplication.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeepisodes.myapplication.DTO.Product;
import com.codeepisodes.myapplication.Helper.DownloadImageTask;
import com.codeepisodes.myapplication.R;
import com.pkmmte.pkrss.Article;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private List<Article> articles;

    public FeedAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.item_adapter_feed, viewGroup, false);
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder , int position) {
        final Article article = articles.get(position);

        holder.txt_title.setText(article.getTitle());
        holder.txt_desc.setText(article.getAuthor());
        new DownloadImageTask (holder.featuredImage).execute(article.getEnclosure().getUrl());
        holder.btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, article.getSource());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_title, txt_desc;
        private AppCompatButton btn_link;
        private ImageView featuredImage;

        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );

            this.txt_title = itemView.findViewById ( R.id.title );
            this.txt_desc = itemView.findViewById ( R.id.desc );
            this.btn_link = itemView.findViewById ( R.id.btnLink );
            this.featuredImage = itemView.findViewById ( R.id.featuredImage );
        }
    }
}
