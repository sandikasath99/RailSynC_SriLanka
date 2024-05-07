package uk.ac.wlv.railsyncsrilanka;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.ac.wlv.railsyncsrilanka.adapter.SearchTrainAdapter;
import uk.ac.wlv.railsyncsrilanka.api.MyApiCall;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.TicketPriceModel;
import uk.ac.wlv.railsyncsrilanka.model.TrainScheduleModel;

public class ViewTicketPricing extends AppCompatActivity {

    private MyApiCall myApiCall;
    private ArrayList<TicketPriceModel> ticketPriceModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_ticket_pricing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String startStation = getIntent().getExtras().getString("startStation");
        String endStation = getIntent().getExtras().getString("endStation");

        TextView fromStation = findViewById(R.id.from);
        TextView to = findViewById(R.id.to);

        fromStation.setText(startStation);
        to.setText(endStation);

        TextView price1 = findViewById(R.id.price1);
        TextView price2 = findViewById(R.id.price2);
        TextView price3 = findViewById(R.id.price3);
        TextView price4 = findViewById(R.id.price4);
        TextView price5 = findViewById(R.id.price5);
        TextView price6 = findViewById(R.id.price6);
        TextView price7 = findViewById(R.id.price7);
        TextView price8 = findViewById(R.id.price8);

        Retrofit retrofitClient = RetrofitClient.getClient();

        myApiCall=retrofitClient.create(MyApiCall.class);
        Call<ArrayList<TicketPriceModel>> call = myApiCall.getTicketPrice(startStation,endStation);

        call.enqueue(new Callback<ArrayList<TicketPriceModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TicketPriceModel>> call, Response<ArrayList<TicketPriceModel>> response) {
                if (response.isSuccessful()){
                    ticketPriceModels=response.body();

                    if(ticketPriceModels!=null){
                            TicketPriceModel model = ticketPriceModels.get(0);

                            price1.setText("Rs."+model.getNormal_2d()+".00");
                            price2.setText("Rs."+model.getNormal_3rd()+".00");
                            price3.setText("Rs."+model.getSeason_private_2nd()+".00");
                            price4.setText("Rs."+model.getSeason_private_3rd()+".00");
                            price5.setText("Rs."+model.getSeason_gov_2nd()+".00");
                            price6.setText("Rs."+model.getSeason_gov_3rd()+".00");
                            price7.setText("Rs."+model.getSeason_gov3_2nd()+".00");
                            price8.setText("Rs."+model.getSeason_gov3_3rd()+".00");

                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<TicketPriceModel>> call, Throwable t) {
                Toast.makeText(ViewTicketPricing.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}