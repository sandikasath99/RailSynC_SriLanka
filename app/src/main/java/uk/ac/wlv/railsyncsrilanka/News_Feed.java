package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.ac.wlv.railsyncsrilanka.adapter.NewsAdapter;
import uk.ac.wlv.railsyncsrilanka.adapter.SearchTrainAdapter;
import uk.ac.wlv.railsyncsrilanka.api.MyApiCall;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.NewsModel;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;

public class News_Feed extends AppCompatActivity {

    private MyApiCall myApiCall;
    private ArrayList<NewsModel> newsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        CardView back=findViewById(R.id.BKFMLSTTC);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(News_Feed.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofitClient = RetrofitClient.getClient();

        myApiCall=retrofitClient.create(MyApiCall.class);
        Call<ArrayList<NewsModel>> call = myApiCall.getNews();

        RecyclerView recyclerView = findViewById(R.id.newsView);

        call.enqueue(new Callback<ArrayList<NewsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsModel>> call, Response<ArrayList<NewsModel>> response) {
                if (response.isSuccessful()){
                    newsModels=response.body();

                    if(newsModels!=null){
                        NewsAdapter newsAdapter = new NewsAdapter(newsModels,News_Feed.this);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(News_Feed.this);

                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(newsAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<NewsModel>> call, Throwable t) {
                Toast.makeText(News_Feed.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}