package uk.ac.wlv.railsyncsrilanka;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.ac.wlv.railsyncsrilanka.api.MyApiCall;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;
import uk.ac.wlv.railsyncsrilanka.model.TrainLocationModel;
import uk.ac.wlv.railsyncsrilanka.model.TrainModel;

public class Live_Location_LN extends AppCompatActivity implements OnMapReadyCallback{

    private MyApiCall myApiCall;
    private ArrayList<StationModel> stationModels;
    private ArrayList<TrainModel> trainModels;
    ArrayAdapter<String> adapterItems,adapterItems2;
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;
    private String station,train,latitude,longitude,tspeed;
    private DatabaseReference mDatabase;
    private GoogleMap map;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 10;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker marker_current;


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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

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

        findViewById(R.id.track).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("location").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            String input=String.valueOf(task.getResult().getValue());
                            input = input.substring(1, input.length() - 1);
                            String[] keyValuePairs = input.split(", ");
                            Map<String, String> map = new HashMap<>();
                            for (String pair : keyValuePairs) {
                                // Split each pair by the equals sign
                                String[] entry = pair.split("=");
                                map.put(entry[0], entry[1]);
                            }
                            Toast.makeText(Live_Location_LN.this,map.get("speed"), Toast.LENGTH_SHORT).show();
                            latitude=map.get("latitude");
                            longitude=map.get("longitude");
                            tspeed=map.get("speed");

                            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                            mapFragment.getMapAsync((OnMapReadyCallback) Live_Location_LN.this);

                        }
                    }
                });

            }
        });

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);

        if (checkPermissions()) {
            map.setMyLocationEnabled(true);
            setDeliverLocation();
        } else {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

    }


    private boolean checkPermissions() {
        boolean permission = false;
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            permission = true;
        }

        return permission;
    }

    @SuppressLint("MissingPermission")
    private void setDeliverLocation() {
        if (checkPermissions()) {
            LatLng latLng = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
            MarkerOptions options = new MarkerOptions().title("Deliver Location").position(latLng);
            marker_current = map.addMarker(options);
            moveCamera(latLng);
        }
    }

    public void moveCamera(LatLng latLng) {
        CameraPosition cameraPosition = CameraPosition.builder().target(latLng).zoom(15f).build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
    }
}