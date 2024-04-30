package uk.ac.wlv.railsyncsrilanka.SeatBooking.MainLine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import uk.ac.wlv.railsyncsrilanka.R;
import uk.ac.wlv.railsyncsrilanka.SeatBooking.Seat_Booking;

public class Main_Line extends AppCompatActivity {

    public CardView back,view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seatbooking_main_line);

        //Buttons
        back=findViewById(R.id.BKFMS);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Line.this, Seat_Booking.class);
                startActivity(intent);
            }
        });
        view1=findViewById(R.id.MLTVIEW1);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Line.this, Kandy_Badulla_1126.class);
                startActivity(intent);
            }
        });
    }
}