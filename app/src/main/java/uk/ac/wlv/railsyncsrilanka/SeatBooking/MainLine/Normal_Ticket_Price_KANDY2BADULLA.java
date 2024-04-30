package uk.ac.wlv.railsyncsrilanka.SeatBooking.MainLine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import uk.ac.wlv.railsyncsrilanka.R;

public class Normal_Ticket_Price_KANDY2BADULLA extends AppCompatActivity {

    public CardView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_ticket_price_kandy2_badulla);

        //Buttons
        back=findViewById(R.id.BKFMTTML);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Normal_Ticket_Price_KANDY2BADULLA.this, Kandy_Badulla_1126.class);
                startActivity(intent);
            }
        });
    }
}