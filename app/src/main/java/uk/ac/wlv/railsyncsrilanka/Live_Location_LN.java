package uk.ac.wlv.railsyncsrilanka;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.ac.wlv.railsyncsrilanka.api.MyApiCall;
import uk.ac.wlv.railsyncsrilanka.api.RetrofitClient;
import uk.ac.wlv.railsyncsrilanka.model.StationModel;
import uk.ac.wlv.railsyncsrilanka.model.TrainLocationModel;
import uk.ac.wlv.railsyncsrilanka.model.TrainModel;

public class Live_Location_LN extends AppCompatActivity implements OnMapReadyCallback {

    private MyApiCall myApiCall;
    private ArrayList<StationModel> stationModels;
    private ArrayList<StationModel> stationLocationModels;
    private ArrayList<TrainModel> trainModels;
    ArrayAdapter<String> adapterItems, adapterItems2;
    AutoCompleteTextView autoCompleteTxt, autoCompleteTxt2;
    private String station, train, latitude, longitude, tspeed, stationLatitude, stationLongitude;
    private DatabaseReference mDatabase;
    private GoogleMap map;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 10;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker marker_current;
    private Marker currentLocationMarker; // Reference to the current location marker
    private Polyline polyline;
    private LatLng location1;
    private LatLng location2;

    TextView yourStation, arivalTime, speed;

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

        String title = getIntent().getExtras().getString("title");
        String line = getIntent().getExtras().getString("line");

        TextView textView22 = findViewById(R.id.textView22);
        textView22.setText(title);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        Retrofit retrofitClient = RetrofitClient.getClient();

        autoCompleteTxt = findViewById(R.id.ACT1);
        autoCompleteTxt2 = findViewById(R.id.ACT2);

        yourStation = findViewById(R.id.yourStation);
        arivalTime = findViewById(R.id.arivalTime);
        speed = findViewById(R.id.speed);

        myApiCall = retrofitClient.create(MyApiCall.class);
        Call<ArrayList<StationModel>> callStation = myApiCall.getStationsByLine(line);

        callStation.enqueue(new Callback<ArrayList<StationModel>>() {
            @Override
            public void onResponse(Call<ArrayList<StationModel>> call, Response<ArrayList<StationModel>> response) {
                Toast.makeText(Live_Location_LN.this, "Success", Toast.LENGTH_SHORT).show();
                stationModels = response.body();

                // Initialize the items array
                String[] items = new String[stationModels.size()];

                for (int i = 0; i < stationModels.size(); i++) {
                    // Get the current StationItem object
                    StationModel stationModel = stationModels.get(i);
                    // Extract the name and store it in the array
                    items[i] = stationModel.getName();
                }

                adapterItems = new ArrayAdapter<String>(Live_Location_LN.this, R.layout.list_item, items);
                autoCompleteTxt.setAdapter(adapterItems);
            }

            @Override
            public void onFailure(Call<ArrayList<StationModel>> call, Throwable t) {
                Toast.makeText(Live_Location_LN.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                station = item;
                findViewById(R.id.ACT2).setEnabled(true);
                findViewById(R.id.a).setEnabled(true);
                yourStation.setText(item);

                Call<ArrayList<StationModel>> callLocation = myApiCall.getStationLocation(item);
                callLocation.enqueue(new Callback<ArrayList<StationModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<StationModel>> call, Response<ArrayList<StationModel>> response) {
                        Toast.makeText(Live_Location_LN.this, "Success", Toast.LENGTH_SHORT).show();
                        stationLocationModels = response.body();

                        StationModel stationModel = stationLocationModels.get(0);
                        stationLongitude = stationModel.getLon();
                        stationLatitude = stationModel.getLan();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<StationModel>> call, Throwable t) {
                        Toast.makeText(Live_Location_LN.this, "Fail3", Toast.LENGTH_SHORT).show();
                    }
                });

                Call<ArrayList<TrainModel>> callTrain = myApiCall.getTrainsByLines(line, item);

                callTrain.enqueue(new Callback<ArrayList<TrainModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<TrainModel>> call, Response<ArrayList<TrainModel>> response) {
                        trainModels = response.body();

                        String[] items = new String[trainModels.size()];

                        for (int i = 0; i < trainModels.size(); i++) {
                            TrainModel trainModel = trainModels.get(i);
                            items[i] = trainModel.getName();
                        }

                        adapterItems2 = new ArrayAdapter<String>(Live_Location_LN.this, R.layout.list_item, items);
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

        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                train = item;
            }
        });

        findViewById(R.id.track).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        mDatabase.child("location").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (!task.isSuccessful()) {
                                    Log.e("firebase", "Error getting data", task.getException());
                                } else {
                                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                    String input = String.valueOf(task.getResult().getValue());
                                    input = input.substring(1, input.length() - 1);
                                    String[] keyValuePairs = input.split(", ");
                                    Map<String, String> map = new HashMap<>();
                                    for (String pair : keyValuePairs) {
                                        String[] entry = pair.split("=");
                                        map.put(entry[0], entry[1]);
                                    }
                                    latitude = map.get("latitude");
                                    longitude = map.get("longitude");
                                    tspeed = map.get("speed");
                                    TextView speedtxt = findViewById(R.id.speed);
                                    speedtxt.setText(tspeed + " Km/h");

                                    if (train.equals("Yaldewi")) {
                                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                                        mapFragment.getMapAsync((OnMapReadyCallback) Live_Location_LN.this);
                                    } else {
                                        Toast.makeText(Live_Location_LN.this, "This Train Has No Tracking Option", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }
                }, 0, 10, TimeUnit.SECONDS);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        location1 = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        location2 = new LatLng(Double.parseDouble(stationLatitude), Double.parseDouble(stationLongitude));

        // Remove old marker if it exists
        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }

        // Add new marker
        currentLocationMarker = map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.tracicon)).position(location1).title("Start Location"));
        map.addMarker(new MarkerOptions().position(location2).title("End Location"));

        map.getUiSettings().setZoomControlsEnabled(true);
        getDirection(location1, location2);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location1, 15));
    }

    public void getDirection(LatLng start, LatLng end) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/directions/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApiCall directionApi = retrofit.create(MyApiCall.class);

        String origin = start.latitude + "," + start.longitude;
        String destination = end.latitude + "," + end.longitude;
        String key = "AIzaSyDIqwwakLqANB1IY2tTlXJvlOb9sObHmTM";

        Call<JsonObject> apiJson = directionApi.getJson(origin, destination, true, key);
        apiJson.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject body = response.body();
                JsonArray routes = body.getAsJsonArray("routes");

                JsonObject route = routes.get(0).getAsJsonObject();
                JsonObject overviewPolyline = route.getAsJsonObject("overview_polyline");

                List<LatLng> points = PolyUtil.decode(overviewPolyline.get("points").getAsString());

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (polyline == null) {
                            PolylineOptions polylineOptions = new PolylineOptions();
                            polylineOptions.width(20);
                            polylineOptions.color(getColor(android.R.color.holo_blue_dark));
                            polylineOptions.addAll(points);
                            polyline = map.addPolyline(polylineOptions);
                        } else {
                            polyline.setPoints(points);
                        }
                        updateArrivalTime(points);
                    }
                });
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
    }

    private void updateArrivalTime(List<LatLng> points) {
        double distance = calculatePolylineDistance(points);
        double trainspeed = Double.parseDouble(tspeed);
        double arivvaltime = ((distance / 1000) / trainspeed) * 60;
        int arrivaltimeint = (int) Math.round(arivvaltime);
        TextView arivalTime = findViewById(R.id.arivalTime);
        arivalTime.setText(String.valueOf(arrivaltimeint) + " Min");
    }

    private double calculatePolylineDistance(List<LatLng> points) {
        double totalDistance = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            LatLng point1 = points.get(i);
            LatLng point2 = points.get(i + 1);
            totalDistance += SphericalUtil.computeDistanceBetween(point1, point2);
        }
        return totalDistance;
    }
}