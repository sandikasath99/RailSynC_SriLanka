package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class TrainSchedule_Mainline extends AppCompatActivity {

    String[] items = {"Colombo","Kandy","Nanu Oya","Ella","Badulla"};
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2;
    ArrayAdapter<String> adapterItems,adapterItems2;

    public CardView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule_mainline);

        //
        autoCompleteTxt = findViewById(R.id.ACT1);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item:"+item,Toast.LENGTH_SHORT).show();

            }
        });

        autoCompleteTxt2 = findViewById(R.id.ACT2);
        adapterItems2 = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt2.setAdapter(adapterItems2);
        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item:"+item,Toast.LENGTH_SHORT).show();

            }
        });


        //Buttons
        back=findViewById(R.id.BKFMLSTTC);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainSchedule_Mainline.this, Train_Schedule.class);
                startActivity(intent);
            }
        });
    }
}