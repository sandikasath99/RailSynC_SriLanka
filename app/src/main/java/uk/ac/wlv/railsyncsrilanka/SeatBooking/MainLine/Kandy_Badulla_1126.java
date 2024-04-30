package uk.ac.wlv.railsyncsrilanka.SeatBooking.MainLine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import uk.ac.wlv.railsyncsrilanka.R;

public class Kandy_Badulla_1126 extends AppCompatActivity {

    public CardView back, normaltcktprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kandy_badulla1126);

        //Buttons
        back=findViewById(R.id.BKFMTTML);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Kandy_Badulla_1126.this, Main_Line.class);
                startActivity(intent);
            }
        });

        normaltcktprice=findViewById(R.id.NTF);
        normaltcktprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Kandy_Badulla_1126.this, Normal_Ticket_Price_KANDY2BADULLA.class);
                startActivity(intent);
            }
        });

    }
}