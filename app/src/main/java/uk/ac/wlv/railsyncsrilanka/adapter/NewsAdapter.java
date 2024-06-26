package uk.ac.wlv.railsyncsrilanka.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uk.ac.wlv.railsyncsrilanka.News_View;
import uk.ac.wlv.railsyncsrilanka.R;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.NewsModel;
import com.squareup.picasso.Picasso;
public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private ArrayList<NewsModel> newses;
    private Context context;
    private String BASE_URL;
    public NewsAdapter(ArrayList<NewsModel> newses, Context context) {
        this.newses = newses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_news_cart, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsModel newsModel= newses.get(position);
        holder.newsTitle.setText(newsModel.getTitle());
        holder.description.setText(newsModel.getDescription());

        BASE_URL = RetrofitClient.getBaseUrl();
        String imageUrl = BASE_URL+newsModel.getImg_path();

        Picasso.get().load(imageUrl).into(holder.news_img);

        holder.news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, News_View.class);
                intent.putExtra("title",newsModel.getTitle());
                intent.putExtra("description",newsModel.getDescription());
                intent.putExtra("img",newsModel.getImg_path());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        TextView description;
ImageView news_img;
        CardView news;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.description);
            news = itemView.findViewById(R.id.news);
            news_img = itemView.findViewById(R.id.news_img);

        }
    }
}
