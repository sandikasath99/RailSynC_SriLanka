package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.ac.wlv.railsyncsrilanka.api.MyApiCall;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;

public class Ticket_Pricing extends AppCompatActivity {
    private MyApiCall myApiCall;
    private ArrayList<StationModel> stationModels;
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;
    ArrayAdapter<String> adapterItems,adapterItems2;

    private String startStation,endStation;
    private CardView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_pricing);


        back=findViewById(R.id.BKFMLSTTC);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ticket_Pricing.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofitClient = RetrofitClient.getClient();

        autoCompleteTxt = findViewById(R.id.ACT1);
        autoCompleteTxt2 = findViewById(R.id.ACT2);

        myApiCall=retrofitClient.create(MyApiCall.class);
        Call<ArrayList<StationModel>> call = myApiCall.getStations();

        call.enqueue(new Callback<ArrayList<StationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<StationModel>> call, Response<ArrayList<StationModel>> response) {
                stationModels=response.body();

                // Initialize the items array
                String[] items = new String[stationModels.size()];

                for (int i = 0; i < stationModels.size(); i++) {
                    // Get the current StationItem object
                    StationModel stationModel = stationModels.get(i);
                    // Extract the name and store it in the array
                    items[i] = stationModel.getName();
                }


                adapterItems = new ArrayAdapter<String>(Ticket_Pricing.this, R.layout.list_item,items);
                autoCompleteTxt.setAdapter(adapterItems);

                adapterItems2 = new ArrayAdapter<String>(Ticket_Pricing.this, R.layout.list_item,items);
                autoCompleteTxt2.setAdapter(adapterItems2);
            }

            @Override
            public void onFailure(Call<ArrayList<StationModel>> call, Throwable t) {
                Toast.makeText(Ticket_Pricing.this, "Fail", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                startStation = item;
            }
        });

        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                endStation = item;

            }
        });

        findViewById(R.id.viewBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startStation==null){
                    Toast.makeText(Ticket_Pricing.this, "Please Select Start Station", Toast.LENGTH_SHORT).show();
                }else if (endStation==null){
                    Toast.makeText(Ticket_Pricing.this, "Please Select End Station", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Ticket_Pricing.this, ViewTicketPricing.class);
                    intent.putExtra("startStation",startStation);
                    intent.putExtra("endStation",endStation);
                    startActivity(intent);
                }
            }
        });

    }
}