package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.ac.wlv.railsyncsrilanka.api.MyApiCall;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;

public class TrainSchedule_Mainline extends AppCompatActivity {

    String[] items = {""};
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;
    ArrayAdapter<String> adapterItems,adapterItems2;

    private MyApiCall myApiCall;
    public CardView back;
    private ArrayList<StationModel> stationModels;

    private Button pickDateBtn;
    private TextView selectedDateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule_mainline);

        String line = getIntent().getExtras().getString("line");
        Toast.makeText(this, line, Toast.LENGTH_SHORT).show();

        Retrofit retrofitClient = RetrofitClient.getClient();


        myApiCall=retrofitClient.create(MyApiCall.class);
        Call<ArrayList<StationModel>> call = myApiCall.getStationsByLine(line);

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

                autoCompleteTxt = findViewById(R.id.ACT1);
                adapterItems = new ArrayAdapter<String>(TrainSchedule_Mainline.this, R.layout.list_item,items);
                autoCompleteTxt.setAdapter(adapterItems);

                autoCompleteTxt2 = findViewById(R.id.ACT2);
                adapterItems2 = new ArrayAdapter<String>(TrainSchedule_Mainline.this, R.layout.list_item,items);
                autoCompleteTxt2.setAdapter(adapterItems2);
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

//
//        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item:"+item,Toast.LENGTH_SHORT).show();
//
//            }
//        });


        // on below line we are initializing our variables.
        pickDateBtn = findViewById(R.id.idBtnPickDate);
        selectedDateTV = findViewById(R.id.idTVSelectedDate);

        // on below line we are adding click listener for our pick date button
        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        TrainSchedule_Mainline.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                selectedDateTV.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
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