package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import uk.ac.wlv.railsyncsrilanka.SeatBooking.Seat_Booking;

public class Live_Location extends AppCompatActivity {
    public CardView back, mainline, northernline, easternline, coastalline, kelanivallyline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_location);

        back=findViewById(R.id.BKFSM);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Live_Location.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mainline=findViewById(R.id.ML);
        mainline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Live_Location.this, Live_Location_LN.class);
                intent.putExtra("line","1");
                intent.putExtra("title","Main Line");
                startActivity(intent);
            }
        });
        northernline=findViewById(R.id.NL);
        northernline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Live_Location.this, Live_Location_LN.class);
                intent.putExtra("line","2");
                intent.putExtra("title","Northern Line");
                startActivity(intent);
            }
        });
        easternline=findViewById(R.id.EL);
        easternline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Live_Location.this, Live_Location_LN.class);
                intent.putExtra("line","3");
                intent.putExtra("title","Eastern Line");
                startActivity(intent);
            }
        });
        coastalline=findViewById(R.id.CL);
        coastalline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Live_Location.this, Live_Location_LN.class);
                intent.putExtra("line","4");
                intent.putExtra("title","Coastal Line");
                startActivity(intent);
            }
        });
        kelanivallyline=findViewById(R.id.KVL);
        kelanivallyline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Live_Location.this, Live_Location_LN.class);
                intent.putExtra("line","5");
                intent.putExtra("title","Kelani Valley Line");
                startActivity(intent);
            }
        });

    }
}