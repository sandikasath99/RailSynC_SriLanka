package uk.ac.wlv.railsyncsrilanka.SeatBooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import uk.ac.wlv.railsyncsrilanka.MainActivity;
import uk.ac.wlv.railsyncsrilanka.R;
import uk.ac.wlv.railsyncsrilanka.SeatBooking.MainLine.Main_Line;

public class Seat_Booking extends AppCompatActivity {

    public CardView mainline,northline,eastline,coastline,kelaniline,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_booking);

        //Buttons
        mainline=findViewById(R.id.ML);
        mainline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Seat_Booking.this, Main_Line.class);
                startActivity(intent);
            }
        });
        northline=findViewById(R.id.NL);
        northline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Seat_Booking.this, Northern_Line.class);
                startActivity(intent);
            }
        });
        eastline=findViewById(R.id.EL);
        eastline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Seat_Booking.this, Eastern_Line.class);
                startActivity(intent);
            }
        });
        coastline=findViewById(R.id.CL);
        coastline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Seat_Booking.this, Coast_Line.class);
                startActivity(intent);
            }
        });
        kelaniline=findViewById(R.id.KVL);
        kelaniline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Seat_Booking.this, Kelani_Line.class);
                startActivity(intent);
            }
        });
        back=findViewById(R.id.BKFSM);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Seat_Booking.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}