package com.chandan.color;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener{


    private ImageView play,exit,instruct;

    @Override
    protected void onStart() {
        super.onStart();
        Animation rotation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.animate);

        play.startAnimation(rotation);
        exit.startAnimation(rotation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        play = (ImageView)findViewById(R.id.play);
        exit = (ImageView)findViewById(R.id.exit);
        instruct = (ImageView) findViewById(R.id.instruct);

        play.setOnClickListener(this);
        instruct.setOnClickListener(this);
        exit.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
      switch(view.getId())
      {
          case R.id.play:
              ScoreActivity.initializeScores();
              ScoreActivity.round=1;
              startActivity(new Intent(this, GameActivity.class));
              break;
          case R.id.instruct:
              startActivity(new Intent(this, instruction.class));
              break;
          case R.id.exit:
              finishAffinity();
              break;
      }
    }


}
