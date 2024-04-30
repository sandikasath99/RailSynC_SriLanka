package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.ac.wlv.railsyncsrilanka.api.MyApiCall;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;

public class TrainSchedule_Mainline extends AppCompatActivity {

    String[] items = {"Colombo","Kandy","Nanu Oya","Ella","Badulla"};
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;
    ArrayAdapter<String> adapterItems,adapterItems2;

    private MyApiCall myApiCall;
    public CardView back;

    private ArrayList<StationModel> stationModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule_mainline);

        String line = getIntent().getExtras().getString("line");
        Toast.makeText(this, line, Toast.LENGTH_SHORT).show();

        Retrofit retrofitClient = RetrofitClient.getClient();


        myApiCall=retrofitClient.create(MyApiCall.class);
        Call<ArrayList<StationModel>> call = myApiCall.getStationsByLine();

        call.enqueue(new Callback<ArrayList<StationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<StationModel>> call, Response<ArrayList<StationModel>> response) {
                stationModels=response.body();

//                for (int i = 0; i < stationModels.size(); i++) {
//                    // Get the current StationItem object
//                    StationModel stationModel = stationModels.get(i);
//                    // Extract the name and store it in the array
//                    items[i] = stationModel.getName();
//                }

                autoCompleteTxt = findViewById(R.id.ACT1);
                adapterItems = new ArrayAdapter<String>(TrainSchedule_Mainline.this, R.layout.list_item,items);
                autoCompleteTxt.setAdapter(adapterItems);
            }

            @Override
            public void onFailure(Call<ArrayList<StationModel>> call, Throwable t) {
                Toast.makeText(TrainSchedule_Mainline.this, "Fail", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        //

//        autoCompleteTxt = findViewById(R.id.ACT1);
//        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
//        autoCompleteTxt.setAdapter(adapterItems);
//        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item:"+item,Toast.LENGTH_SHORT).show();
//
//            }
//        });

        autoCompleteTxt2 = findViewById(R.id.ACT2);
        adapterItems2 = new ArrayAdapter<String>(this, R.layout.list_item,items);
        autoCompleteTxt2.setAdapter(adapterItems2);
        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item:"+item,Toast.LENGTH_SHORT).show();

            }
        });


        //Buttons
        back=findViewById(R.id.BKFMLSTTC);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainSchedule_Mainline.this, Train_Schedule.class);
                startActivity(intent);
            }
        });
    }
}