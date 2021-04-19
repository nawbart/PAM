package com.example.pam_gra;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
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

    //Size
    private int frameHeight, boxSize, screenWidth,screenHeight;

    // Position
    private float boxY;
    private float orangeX, orangeY;
    private float pinkX, pinkY;
    private float blackX, blackY;

    // Speed
    private int boxSpeed, orangeSpeed, pinkSpeed, blackSpeed;

    // Score
    private int score;

    // Timer
    private Timer timer = new Timer();
    private Handler handler = new Handler();

    // Status
    private boolean action_flg = false;
    private boolean start_flg = false;

    // SoundPlayer
    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // soundPlayer = new SoundPlayer(this);

        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);
        stworek = findViewById(R.id.box);
        punkt = findViewById(R.id.orange);
        punkt2 = findViewById(R.id.pink);
        bomba = findViewById(R.id.black);

        // Screen Size
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        int screenHeight = size.y;



        // Initial Positions
        punkt.setX(-80.0f);
        punkt.setY(-80.0f);
        punkt2.setX(-80.0f);
        punkt2.setY(-80.0f);
        bomba.setX(-80.0f);
        bomba.setY(-80.0f);

        //scoreLabel.setText("Score : " + score);
        scoreLabel.setText(getString(R.string.score, score));

    }

    }

    public void changePos() {
        hitCheck();

        // Orange
        orangeX -= orangeSpeed;
        if (orangeX < 0) {
            orangeX = screenWidth + 20;
            orangeY = (float) Math.floor(Math.random() * (frameHeight - punkt.getHeight()));
        }
        punkt.setX(orangeX);
        punkt.setY(orangeY);

        // Black
        blackX -= blackSpeed;
        if (blackX < 0) {
            blackX = screenWidth + 10;
            blackY = (float) Math.floor(Math.random() * (frameHeight - bomba.getHeight()));
        }
        bomba.setX(blackX);
        bomba.setY(blackY);

        // Pink
        pinkX -= pinkSpeed;
        if (pinkX < 0) {
            pinkX = screenWidth + 5000;
            pinkY = (float) Math.floor(Math.random() * (frameHeight - punkt2.getHeight()));
        }
        punkt2.setX(pinkX);
        punkt2.setY(pinkY);

        // Box
        if (action_flg) {
            // Touching
            boxY -= boxSpeed;
        } else {
            // Releasing
            boxY += boxSpeed;
        }

        if (boxY < 0) boxY = 0;
        if (boxY > frameHeight - boxSize) boxY = frameHeight - boxSize;

        stworek.setY(boxY);
    }

    public void hitCheck() {

        // Orange
        float orangeCenterX = orangeX + punkt.getWidth() / 2.0f;
        float orangeCenterY = orangeY + punkt.getHeight() / 2.0f;

        if (0 <= orangeCenterX && orangeCenterX <= boxSize &&
                boxY <= orangeCenterY && orangeCenterY <= boxY + boxSize) {
            orangeX = -100.0f;
            score += 10;
            //soundPlayer.playHitSound();
        }

        // Pink
        float pinkCenterX = pinkX + punkt2.getWidth() / 2.0f;
        float pinkCenterY = pinkY + punkt2.getHeight() / 2.0f;

        if (0 <= pinkCenterX && pinkCenterX <= boxSize &&
                boxY <= pinkCenterY && pinkCenterY <= boxY + boxSize) {
            pinkX = -100.0f;
            score += 30;
            //soundPlayer.playHitSound();
        }

        // Black
        float blackCenterX = blackX + bomba.getWidth() / 2.0f;
        float blackCenterY = blackY + bomba.getHeight() / 2.0f;

        if (0 <= blackCenterX && blackCenterX <= boxSize &&
                boxY <= blackCenterY && blackCenterY <= boxY + boxSize) {

            soundPlayer.playOverSound();

            // Game Over!!
            if (timer != null) {
                timer.cancel();
                timer = null;
            }

            // Show ResultActivity
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }
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

    private void hitCheck() {
    }

