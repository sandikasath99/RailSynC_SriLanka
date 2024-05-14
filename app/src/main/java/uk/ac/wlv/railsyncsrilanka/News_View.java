package uk.ac.wlv.railsyncsrilanka;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;

public class News_View extends AppCompatActivity {

    private String BASE_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView back=findViewById(R.id.BKFMLSTTC);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(News_View.this, News_Feed.class);
                startActivity(intent);
            }
        });

        String title = getIntent().getExtras().getString("title");
        String description = getIntent().getExtras().getString("description");
        String img = getIntent().getExtras().getString("img");

       BASE_URL = RetrofitClient.getBaseUrl();

        TextView titleTxt = findViewById(R.id.textView3);
        TextView descriptionTxt = findViewById(R.id.textView254);
        ImageView imageView = findViewById(R.id.imageView18);
        String imageUrl = BASE_URL+img; // URL of the image
        Picasso.get().load(imageUrl).into(imageView);

        titleTxt.setText(title);
        descriptionTxt.setText(description);
    }
}