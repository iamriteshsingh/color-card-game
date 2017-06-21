package com.chandan.color;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends Activity {

    public  TextView r11,r12,r13,r14,r21,r22,r23,r24,r31,r32,r33,r34,r41,r42,r43,r44,r51,r52,r53,r54,t1,t2,t3,t4,win;
    public Button b1;
    public static final String TAG="output";
    public static int x1;
    public static int x2;
    public static int x3;
    public static int x4;
    public static int round=1;
    public static int rcount;
    public static int rcnt;

    public static int sum[]= new int[]{0,0,0,0};

    public static int[][] arr=new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_score);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = 900;
        params.width = 1390;
        this.getWindow().setAttributes(params);

        this.setFinishOnTouchOutside(false);
        rcnt = rcount;

        r11=(TextView)findViewById(R.id.textView11);
        r12=(TextView)findViewById(R.id.textView12);
        r13=(TextView)findViewById(R.id.textView13);
        r14=(TextView)findViewById(R.id.textView14);
        r21=(TextView)findViewById(R.id.textView21);
        r22=(TextView)findViewById(R.id.textView22);
        r23=(TextView)findViewById(R.id.textView23);
        r24=(TextView)findViewById(R.id.textView24);
        r31=(TextView)findViewById(R.id.textView31);
        r32=(TextView)findViewById(R.id.textView32);
        r33=(TextView)findViewById(R.id.textView33);
        r34=(TextView)findViewById(R.id.textView34);
        r41=(TextView)findViewById(R.id.textView41);
        r42=(TextView)findViewById(R.id.textView42);
        r43=(TextView)findViewById(R.id.textView43);
        r44=(TextView)findViewById(R.id.textView44);
        r51=(TextView)findViewById(R.id.textView51);
        r52=(TextView)findViewById(R.id.textView52);
        r53=(TextView)findViewById(R.id.textView53);
        r54=(TextView)findViewById(R.id.textView54);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        win=(TextView)findViewById(R.id.gameWinner);
        b1=(Button)findViewById(R.id.scoreButton);

        fillScores();
        if(rcount>11) {
            Log.i(TAG," create"+round+" "+rcount);
            round++;
            rcount=0;
        }
        Log.i(TAG,"Score create"+round+" "+rcount);
    }
    public static void initializeScores()
    {
        for(int i=0;i<5;i++)
        {
            arr[i][0]=0;
            arr[i][1]=0;
            arr[i][2]=0;
            arr[i][3]=0;

        }
    }



    public void fillScores() {
        Log.i(TAG,"x1"+x1+" "+x2);
        arr[round-1][0]=x1;
        arr[round-1][1]=x2;
        arr[round-1][2]=x3;
        arr[round-1][3]=x4;
                r11.setText(arr[0][0]+"");
                r12.setText(arr[0][1]+"");
                r13.setText(arr[0][2]+"");
                r14.setText(arr[0][3]+"");


        r21.setText(arr[1][0]+"");
        r22.setText(arr[1][1]+"");
        r23.setText(arr[1][2]+"");
        r24.setText(arr[2][3]+"");

        r31.setText(arr[2][0]+"");
        r32.setText(arr[2][1]+"");
        r33.setText(arr[2][2]+"");
        r34.setText(arr[2][3]+"");

        r41.setText(arr[3][0]+"");
        r42.setText(arr[3][1]+"");
        r43.setText(arr[3][2]+"");
        r44.setText(arr[3][3]+"");

        r51.setText(arr[4][0]+"");
        r52.setText(arr[4][1]+"");
        r53.setText(arr[4][2]+"");
        r54.setText(arr[4][3]+"");

        x1=0;x2=0;x3=0;x4=0;
        if(round==5 && rcount>11)
        {
            for(int i=0;i<5;i++)
                {
                    sum[0]=sum[0]+arr[i][0];
                    sum[1]=sum[1]+arr[i][1];
                    sum[2]=sum[2]+arr[i][2];
                    sum[3]=sum[3]+arr[i][3];
                }
            t1.setText(sum[0]+"");
            t2.setText(sum[1]+"");
            t3.setText(sum[2]+"");
            t4.setText(sum[3]+"");
            int d = sum[0];
            int sss=4;
            for (int i = 0; i <sss-1; i++) {
                for (int j = 0; j < sss -i -1; j++) {
                    if (sum[j] > sum[j + 1]) {
                        int temp = sum[j];
                        sum[j] = sum[j + 1];
                        sum[j + 1] = temp;
                    }
                }
            }
            int ds =0;
            for(int i=0;i<4;i++)
            {
                if(sum[i]==d)
                {
                    ds=i+1;
                    break;
                }
            }
            Log.i(TAG,sum[0]+" "+sum[1]+" "+sum[2]+" "+sum[3]+" ");
            Log.i(TAG,"ds="+ds+" d="+d);
            switch(ds)
            {
                case 4:
                    win.setText("Congratulation ... !!! You Won ... !!!");
                    break;
                case 3:
                    win.setText("Good Try...You Came Second ... !!!");
                    break;
                case 2:
                    win.setText("You Came Third ... !!!");
                    break;
                case 1:
                    win.setText("You need Warm Up !! You Came Fourth..!!!");
                    break;
            }

        }
    }

    public void nextround(View view) {

        Log.i(TAG,round+" 0 "+rcnt);
        if(round==6 && rcnt>11) {
            initializeScores();
            startActivity(new Intent(this,MainActivity.class));
            round=1;
            win.setText("");
            Log.i(TAG,round+" 1 "+rcount);
        }
        else if(rcnt>11){
            Log.i(TAG,round+" 2 "+rcount);
            rcnt=0;
            startActivity(new Intent(this, GameActivity.class));

        }

        finish();
    }
}