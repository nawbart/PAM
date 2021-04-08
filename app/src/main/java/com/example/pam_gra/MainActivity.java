package com.example.pam_gra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // Elements
    private TextView scoreLabel, startLabel;
    private ImageView stworek, punkt, punkt2, bomba;

    private int boxY;


    // Timer
    private Timer timer = new Timer();
    private Handler handler = new Handler();

    // Status
    private boolean action_flg = false;
    private boolean start_flg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);
        stworek = findViewById(R.id.box);
        punkt = findViewById(R.id.orange);
        punkt2 = findViewById(R.id.pink);
        bomba = findViewById(R.id.black);


        // Initial Positions
        punkt.setX(-80.0f);
        punkt.setY(-80.0f);
        punkt2.setX(-80.0f);
        punkt2.setY(-80.0f);
        bomba.setX(-80.0f);
        bomba.setY(-80.0f);

        startLabel.setVisibility(View.INVISIBLE);
        boxY = 500;




    } else {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            action_flg = true;

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            action_flg = false;
        }
    }


    }

    public void changePos() {
    if (action_flg == true) {
        boxY -= 20;
    }
    else {
        boxY += 20;
    }
      box.setY(boxY);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!start_flg) {
            start_flg = true;

            // FrameHeight
            FrameLayout frameLayout = findViewById(R.id.frame);
            frameHeight = frameLayout.getHeight();

            // Box
            boxY = box.getY();
            boxSize = box.getHeight();

            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        } else {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }
        return super.onTouchEvent(event);
    }
}

