package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import uk.ac.wlv.railsyncsrilanka.SeatBooking.Seat_Booking;

public class MainActivity extends AppCompatActivity  {
    public CardView tracker,scheduler,booking,pricing,news,alerts,security,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons
        tracker=findViewById(R.id.t1);
        tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Live_Location.class);
                startActivity(intent);
            }
        });
        scheduler=findViewById(R.id.t2);
        scheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Train_Schedule.class);
                startActivity(intent);
            }
        });
        booking=findViewById(R.id.t3);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Seat_Booking.class);
                startActivity(intent);
            }
        });
        pricing=findViewById(R.id.t4);
        pricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Ticket_Pricing.class);
                startActivity(intent);
            }
        });
        news=findViewById(R.id.t5);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, News_Feed.class);
                startActivity(intent);
            }
        });
        alerts=findViewById(R.id.t6);
        alerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Train_Alerts.class);
                startActivity(intent);
            }
        });
        security = findViewById(R.id.t7);
        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and show a dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Inflate the layout for the dialog content
                View dialogView = getLayoutInflater().inflate(R.layout.activity_railway_security, null);
                builder.setView(dialogView);

                // Button inside the dialog
                Button dialogButton = dialogView.findViewById(R.id.button3);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Perform action when button inside dialog is clicked
                        // For example, opening the phone dialer
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:+9476 8812232"));
                        startActivity(intent);
                    }
                });

                // Create and show the dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        about=findViewById(R.id.t8);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, About_Us.class);
                startActivity(intent);
            }
        });


        //Date and Time
        setTitle("Date & time");
        Calendar calendar = Calendar.getInstance();
        String currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        }
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);



    }

}