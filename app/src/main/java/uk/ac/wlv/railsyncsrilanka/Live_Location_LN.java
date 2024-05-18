package uk.ac.wlv.railsyncsrilanka;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.ac.wlv.railsyncsrilanka.api.MyApiCall;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;
import uk.ac.wlv.railsyncsrilanka.model.TrainModel;

public class Live_Location_LN extends AppCompatActivity {

    private MyApiCall myApiCall;
    private ArrayList<StationModel> stationModels;
    private ArrayList<TrainModel> trainModels;
    ArrayAdapter<String> adapterItems,adapterItems2;
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;
    private String station,train;

    TextView yourStation,arivalTime,speed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_live_location_ln);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String line = getIntent().getExtras().getString("line");

        Retrofit retrofitClient = RetrofitClient.getClient();

        autoCompleteTxt = findViewById(R.id.ACT1);
        autoCompleteTxt2 = findViewById(R.id.ACT2);

        yourStation = findViewById(R.id.yourStation);
        arivalTime = findViewById(R.id.arivalTime);
        speed = findViewById(R.id.speed);

        myApiCall=retrofitClient.create(MyApiCall.class);
        Call<ArrayList<StationModel>> callStation = myApiCall.getStationsByLine(line);

        callStation.enqueue(new Callback<ArrayList<StationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<StationModel>> call, Response<ArrayList<StationModel>> response) {
                Toast.makeText(Live_Location_LN.this, "Success", Toast.LENGTH_SHORT).show();
                stationModels=response.body();

                // Initialize the items array
                String[] items = new String[stationModels.size()];

                for (int i = 0; i < stationModels.size(); i++) {
                    // Get the current StationItem object
                    StationModel stationModel = stationModels.get(i);
                    // Extract the name and store it in the array
                    items[i] = stationModel.getName();
                }

                adapterItems = new ArrayAdapter<String>(Live_Location_LN.this, R.layout.list_item,items);
                autoCompleteTxt.setAdapter(adapterItems);

            }

            @Override
            public void onFailure(Call<ArrayList<StationModel>> call, Throwable t) {
                Toast.makeText(Live_Location_LN.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                station = item;
                findViewById(R.id.ACT2).setEnabled(true);
                findViewById(R.id.a).setEnabled(true);
                yourStation.setText(item);


                Call<ArrayList<TrainModel>> callTrain = myApiCall.getTrainsByLines(line,item);

                callTrain.enqueue(new Callback<ArrayList<TrainModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<TrainModel>> call, Response<ArrayList<TrainModel>> response) {
                        trainModels=response.body();

                        // Initialize the items array
                        String[] items = new String[trainModels.size()];

                        for (int i = 0; i < trainModels.size(); i++) {
                            // Get the current StationItem object
                            TrainModel trainModel = trainModels.get(i);
                            // Extract the name and store it in the array
                            items[i] = trainModel.getName();
                        }


                        adapterItems2 = new ArrayAdapter<String>(Live_Location_LN.this, R.layout.list_item,items);
                        autoCompleteTxt2.setAdapter(adapterItems2);

                    }

                    @Override
                    public void onFailure(Call<ArrayList<TrainModel>> call, Throwable t) {
                        Toast.makeText(Live_Location_LN.this, "Fail", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }
        });

        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                train = item;

            }
        });


    }
}