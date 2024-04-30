package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import uk.ac.wlv.railsyncsrilanka.SeatBooking.Seat_Booking;


public class Train_Schedule extends AppCompatActivity {

    public CardView back, mainline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);

        back=findViewById(R.id.BKFSCM);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Train_Schedule.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mainline=findViewById(R.id.ML);
        mainline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Train_Schedule.this, TrainSchedule_Mainline.class);
                startActivity(intent);
            }
        });
    }
}