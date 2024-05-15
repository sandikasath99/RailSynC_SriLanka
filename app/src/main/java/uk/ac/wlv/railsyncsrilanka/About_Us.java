package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class About_Us extends AppCompatActivity {

    public CardView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        back=findViewById(R.id.BKFSM);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(About_Us.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}