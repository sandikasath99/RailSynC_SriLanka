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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.ac.wlv.railsyncsrilanka.adapter.SearchTrainAdapter;
import uk.ac.wlv.railsyncsrilanka.api.MyApiCall;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;
import uk.ac.wlv.railsyncsrilanka.model.TrainScheduleModel;

public class Search_Train_Shedule extends AppCompatActivity {

    private MyApiCall myApiCall;
    private ArrayList<TrainScheduleModel> trainScheduleModels;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_train_shedule);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String startStation = getIntent().getExtras().getString("startStation");
        String endStation = getIntent().getExtras().getString("endStation");
        String date = getIntent().getExtras().getString("date");


        recyclerView = findViewById(R.id.searchTrains);
        TextView  stations = findViewById(R.id.stationsName);
        TextView  dates = findViewById(R.id.date);

        stations.setText(startStation +" - "+endStation);
        dates.setText(date);

        Retrofit retrofitClient = RetrofitClient.getClient();

        myApiCall=retrofitClient.create(MyApiCall.class);
        Call<ArrayList<TrainScheduleModel>> call = myApiCall.getScheduleTrain(startStation,endStation,date);

        call.enqueue(new Callback<ArrayList<TrainScheduleModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TrainScheduleModel>> call, Response<ArrayList<TrainScheduleModel>> response) {
                if (response.isSuccessful()){
                    trainScheduleModels=response.body();


                    if(trainScheduleModels!=null){
                        SearchTrainAdapter trainAdapter = new SearchTrainAdapter(trainScheduleModels,Search_Train_Shedule.this);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(Search_Train_Shedule.this);

                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(trainAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TrainScheduleModel>> call, Throwable t) {
                Toast.makeText(Search_Train_Shedule.this,"fail",Toast.LENGTH_LONG).show();
            }
        });

    }
}

