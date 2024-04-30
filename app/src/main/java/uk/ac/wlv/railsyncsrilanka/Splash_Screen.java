package uk.ac.wlv.railsyncsrilanka;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Splash_Screen extends AppCompatActivity {
    // Splash screen duration in milliseconds
    private static final int SPLASH_DISPLAY_DURATION = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Load the fade-in animation
        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_custom);
        // Apply the animation to the layout
        findViewById(android.R.id.content).startAnimation(fadeIn);
        // Delayed execution of MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start MainActivity with smooth animation
                Intent mainIntent = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(mainIntent);
                // Apply fade-out animation
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish(); // Finish Splash_Screen activity
            }
        }, SPLASH_DISPLAY_DURATION);
    }
}
