package com.chandan.color;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class GameActivity extends Activity {

    public double p[][];
    public static final String TAG="output";
    public Toast myToast=null;
    double fake[] = new double[13];
    public int nu[] = new int[2];
    static int[] imagesrc = {R.drawable.s_3, R.drawable.s_4
            , R.drawable.s_5, R.drawable.s_6, R.drawable.s_7
            , R.drawable.s_8, R.drawable.s_9, R.drawable.s_10
            , R.drawable.s_j, R.drawable.s_q, R.drawable.s_k, R.drawable.s_ace,
            R.drawable.d_2, R.drawable.d_3, R.drawable.d_4
            , R.drawable.d_5, R.drawable.d_6, R.drawable.d_7
            , R.drawable.d_8, R.drawable.d_9, R.drawable.d_10
            , R.drawable.d_j, R.drawable.d_q, R.drawable.d_k, R.drawable.d_ace,
            R.drawable.c_2, R.drawable.c_3, R.drawable.c_4
            , R.drawable.c_5, R.drawable.c_6, R.drawable.c_7
            , R.drawable.c_8, R.drawable.c_9, R.drawable.c_10
            , R.drawable.c_j, R.drawable.c_q, R.drawable.c_k, R.drawable.c_ace,
            R.drawable.h_2, R.drawable.h_3, R.drawable.h_4
            , R.drawable.h_5, R.drawable.h_6, R.drawable.h_7
            , R.drawable.h_8, R.drawable.h_9, R.drawable.h_10
            , R.drawable.h_j, R.drawable.h_q, R.drawable.h_k, R.drawable.h_ace,
            R.drawable.joker};

    public static int r = 0,x=0, card1 = 0;
    public static ImageView img2;
    public static int handCoun[] = new int[2];
    double n[]=new double[]{0,0,0,0};
    static int[] imgsrc=new int[13];
    static int imageviews[] = {R.id.imageView, R.id.imageView2, R.id.imageView3,
            R.id.imageView4, R.id.imageView5, R.id.imageView6,
            R.id.imageView7, R.id.imageView8, R.id.imageView9,
            R.id.imageView10, R.id.imageView11, R.id.imageView12, R.id.imageView13};
    public static double a[] = new double[52];

    public static int hc[];// displays hands win
    TextView bottom,left,top,right;
    TextView handLeft,handRight,handTop,handBottom;
    int xcount[] = new int[4];//hands of each player

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        double ac[] = new double[]{1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.10, 1.11, 1.12, 1.13, 1.14,
                2.02, 2.03, 2.04, 2.05, 2.06, 2.07, 2.08, 2.09, 2.10, 2.11, 2.12, 2.13, 2.14,
                3.02, 3.03, 3.04, 3.05, 3.06, 3.07, 3.08, 3.09, 3.10, 3.11, 3.12, 3.13, 3.14,
                4.02, 4.03, 4.04, 4.05, 4.06, 4.07, 4.08, 4.09, 4.10, 4.11, 4.12, 4.13, 4.14,
                5.0};
        hc = new int[]{0,0,0,0};
        // double a[] = new double[52];

         handLeft = (TextView) findViewById(R.id.handleft);
         handRight = (TextView) findViewById(R.id.handright);
         handTop = (TextView) findViewById(R.id.handtop);
         handBottom = (TextView) findViewById(R.id.handbottom);

        bottom = (TextView) findViewById(R.id.bottom);
        left = (TextView) findViewById(R.id.left);
        top = (TextView) findViewById(R.id.top);
        right = (TextView) findViewById(R.id.right);


        TextView roundDisp=(TextView)findViewById(R.id.round);
        roundDisp.setText("Round : "+ScoreActivity.round);

        System.arraycopy(ac, 0, a, 0, 52);

//        display(a);
//        display(ac);
//        for(int i =0 ;i<ac.length;i++)
//        {
//            System.out.print(ac[i]+" ");
//        }
//        System.out.println(" ");
//
//        for(int i =0 ;i<a.length;i++)
//            System.out.print(a[i]+"  ");
//        System.out.println(" ");
//
//        System.out.println(a.length);
        p= new double[4][13];
        boolean flag;
        do{
            ac = Sort(ac);
            flag = false;
            int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c = 1;
            for (int i = 0; i < ac.length; i++) {
                if (c == 4) {
                    p[3][c4] = ac[i];
                    //System.out.println("c4"+c4+" "+i);
                    c4++;
                    c = 1;
                    continue;
                }
                if (c == 3) {
                    p[2][c3] = ac[i];
                    //System.out.println("c3"+c3+" "+i);
                    c3++;
                    c = 4;
                    continue;
                }
                if (c == 2) {
                    p[1][c2] = ac[i];
                    //System.out.println("c2"+c2+" "+i);
                    c2++;
                    c = 3;
                    continue;
                }
                if (c == 1) {
                    p[0][c1] = ac[i];
                    fake[c1]=ac[i];
                    c1++;
                    c = 2;
                    continue;
                }

            }
            p[0] = ascendingOrder(p[0]);
            fake=ascendingOrder(fake);

            for (int i = 0; i < 13; i++) {
                //  System.out.println(p[0][i]);
                // int idx = find(0, 51, p[0][i]);
                int idx = 0;
                for (int l = 0; l < 52; l++) {
                    if (a[l] == p[0][i]) {
                        idx = l;
                        break;
                    }
                }
                imgsrc[i] = imagesrc[idx];
                //     System.out.println("idx = "+idx +" p [0][i] = "+p[0][i]+" imgsrc = "+imgsrc[i]);
                ImageView imageView = (ImageView) findViewById(imageviews[i]);
                imageView.setImageResource(imgsrc[i]);

            }

            p[1] = ascendingOrder(p[1]);
            p[2] = ascendingOrder(p[2]);
            p[3] = ascendingOrder(p[3]);
            System.out.println("PLAYER 1:");
            display(p[0]);

             xcount[0] = handCount(p[0]);
            if(xcount[0]==0 || (int)p[0][0]!=1)
                flag = true;
            System.out.println("  hand1 : " + xcount[0]);
            handBottom.setText("" + xcount[0]+" :");
            System.out.println(" ");

            System.out.println("PLAYER 2:");
            display(p[1]);
            xcount[1] = handCount(p[1]);

            if(xcount[1]==0 || (int)p[1][0]!=1)// if zero hand or zero gamecard in p1
                flag = true;
            System.out.println("  hand2 : " + xcount[1]);
            handLeft.setText("" + xcount[1]+" :");
            System.out.println(" ");
            System.out.println("PLAYER 3:");
            display(p[2]);
            xcount[2] = handCount(p[2]);

            if(xcount[2]==0 || (int)p[2][0]!=1)
                flag = true;
            System.out.println("  hand3 : " + xcount[2]);
            handTop.setText("" + xcount[2]+" :");
            System.out.println(" ");
            System.out.println("PLAYER 4:");
            display(p[3]);
            xcount[3] = handCount(p[3]);

            if(xcount[3]==0 || (int)p[3][0]!=1)
                flag = true;
            System.out.println("  hand4 : " + xcount[3]);
            handRight.setText("" + xcount[3]+" :");
            System.out.println(" ");
          //  nu[0] = x[0] + x[2];
          //  nu[1] = x[1] + x[3];
            System.out.println("flag="+flag);
        }while(flag);
        //gamePlay(p);
    }

    public static double[] Sort(double a[]) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int s = (int) (Math.random() * (n - 1));
            double t = a[s];
            a[s] = a[i];
            a[i] = t;

        }
        return (a);
    }
    public static int find(double num)
    {
        for (int l = 0; l < 52; l++) {
            if (a[l] == num) {
                return l;

            }
        }
        return -99;
    }

    public void remove(View view) {
        ImageView img=null;
        int user=-1;
        switch (view.getId()) {
            case R.id.imageView:
                user = 0;
                img = (ImageView) findViewById(R.id.imageView);

//                LinearLayout layout = (LinearLayout)findViewById(R.id.imagelinearLayout);
//                for (int i = 0; i < layout.getChildCount(); i++) {
//                    View child = layout.getChildAt(i);
//                    child.setEnabled(false);
//                }
                break;
            case R.id.imageView2:
                user = 1;
                img = (ImageView)findViewById(R.id.imageView2);

                break;
            case R.id.imageView3:
                user = 2;
                img = (ImageView)findViewById(R.id.imageView3);

                break;
            case R.id.imageView4:
                user = 3;
                img = (ImageView)findViewById(R.id.imageView4);

                break;
            case R.id.imageView5:
                user = 4;
                img = (ImageView)findViewById(R.id.imageView5);

                break;
            case R.id.imageView6:
                user = 5;
                img = (ImageView)findViewById(R.id.imageView6);

                break;
            case R.id.imageView7:
                user = 6;
                img = (ImageView)findViewById(R.id.imageView7);

                break;
            case R.id.imageView8:
                user = 7;
                img = (ImageView)findViewById(R.id.imageView8);

                break;
            case R.id.imageView9:
                user = 8;
                img = (ImageView)findViewById(R.id.imageView9);

                break;
            case R.id.imageView10:
                user = 9;
                img = (ImageView)findViewById(R.id.imageView10);

                break;
            case R.id.imageView11:
                user = 10;
                img = (ImageView)findViewById(R.id.imageView11);

                break;
            case R.id.imageView12:
                user = 11;
                img = (ImageView)findViewById(R.id.imageView12);

                break;
            case R.id.imageView13:
                user = 12;
                img = (ImageView)findViewById(R.id.imageView13);


//                LinearLayout layout = (LinearLayout)findViewById(R.id.imagelinearLayout);
//                for (int i = 0; i < layout.getChildCount(); i++) {
//                    View child = layout.getChildAt(i);
//                    child.setEnabled(false);
//                }
                break;
        }



        //   Log.i(TAG,user+" onclick   r= "+r);
        if(view.getId()==R.id.imagescore)
        {
            startActivity(new Intent(this,ScoreActivity.class));
        }
        else
            gamePlay(img,user);


    }




    public  void gamePlay(ImageView img,int user)
    {

        int i =0;
        System.out.println("\n\n\nGAME PLAY ...!!\n\n\n");
//        Scanner in = new Scanner(System.in);

        if(x==0)
        {

            System.out.println("YOUR CHANCE p1:- ");
            //  n[0] = in.nextDouble();


            if(fake[user]==5.0 )
            {
                if(r==0 ) {
                    //Toast.makeText(this, "Can't put Joker in 1st round", Toast.LENGTH_SHORT).show();
                    showToast("Can't put Joker in 1st round");
                    return;
                }
                else if(n[1]==0 && n[2]==0 && n[3]==0 && r!=12)
                {
                    showToast("Can't start a round with joker ");
                    return;
                }
                else if(r==12)
                {
                    n[0]=0.0;
                }
                else
                    n[0]=5.0;

            }
            else
                n[0] = fake[user];



            if(n[0]!=5.0 && card1!=(int)n[0] && n[0]!=0.0 && n[3]!=0.0 && r!=12)
            {
                int k;
                for(k=0;k<p[0].length;k++)
                    if((int)p[0][k]==card1)
                    {
                        if(card1==1)
                        {
                            showToast("You are left with spade ");
                            return;
                        }
                        if(card1==2)
                        {
                            showToast("You are left with diamond");
                            return;
                        }
                        if(card1==3)
                        {
                            showToast("You are left with club");
                            return;
                        }
                        if(card1==4)
                        {
                            showToast("You are left with heart ");
                            return;
                        }
                        System.out.println("You are still left with card type: "+card1);
                        // Toast.makeText(this,"You are still left with card type: "+card1,Toast.LENGTH_SHORT).show();
                    }
            }


            ViewGroup viewGroup= ((ViewGroup) img.getParent());
            viewGroup.removeView(img);
            img2 = (ImageView) findViewById(R.id.imageViewbottom);
            int z;

            for(z=0;z<fake.length;z++)
            {
                if(n[0]==fake[z] || n[0]<0.1)
                    break;
            }
            display(p[0]);
            display(fake);
            System.out.println("z="+z+" user="+user+" n[o] = "+n[0]);
            for(int m=0;m<13;m++)
                System.out.print(imagesrc[m]+" ");
            System.out.println();

            img2.setImageResource(imgsrc[z]);


            if(card1==0)
            card1=(int)n[0];
            p[0] = removeCard(p[0],n[0]);
            for(int m=0;m<imageviews.length;m++)
                System.out.print(imageviews[m]+" ");
            System.out.println();
            //    imageviews=removeImage(imageviews,user);

            for(int m=0;m<imageviews.length;m++)
                System.out.print(imageviews[m]+" ");
            System.out.println();

            if(n[1]==0) {
                System.out.println("nextmove1");
                n[1] = nextMove(p[1], n[0], n[1], n[2],n[3]);
                p[1] = removeCard(p[1], n[1]);
            }
            if(n[2]==0) {
                System.out.println("nextmove2");
                n[2] = nextMove(p[2], n[0], n[1], n[2],n[3]);
                p[2] = removeCard(p[2], n[2]);
            }
            if(n[3]==0) {
                System.out.println("nextmove3");
                n[3] = nextMove(p[3], n[0], n[1], n[2],n[3]);
                p[3] = removeCard(p[3], n[3]);
            }
        }
//        if(x!=0)
//        {
//            while( i < 4)
//            {
//                if(x==0)
//                {
//                    int temp=user;
//
//
//                        System.out.println("YOUR CHANCE p1:- ");
//                       // n[0] = in.nextDouble();
//                       //thread------- SystemClock.sleep(50000);
//                        n[0]= p[0][user];
//
//                    System.out.println("player 1: "+n[0]);
//                    if(n[0]==5.0 && (r==12))
//                        {
//                            n[0]=0.5;
//                            ((ViewGroup) img.getParent()).removeView(img);
//                            img2 = (ImageView) findViewById(R.id.imageViewbottom);
//                            img2.setImageResource(imgsrc[user]);
//
//                        }
//                         if(n[0]!=5.0 && card1!=(int)n[0])
//                        {
//                            int k;
//                            for(k=0;k<p[0].length;k++)
//                                if((int)p[0][k]==card1)
//                                {
//                                    System.out.println("You are still left with card type: "+card1);
//                                    break;
//                                }
//
//                        }
//                    else
//                         {
//                             ((ViewGroup) img.getParent()).removeView(img);
//                             img2 = (ImageView) findViewById(R.id.imageViewbottom);
//                             img2.setImageResource(imgsrc[user]);
//                         }
//
//
//                    p[0] = removeCard(p[0],n[0]);
//                    x++;i++;
//                }
//                else
//                {
//                    System.out.println("Player " + (x+1) );
//                    if(i==0)
//                    {
//                        n[x] = firstMove(p[x]);
//                        card1= (int)n[x];
//                        //System.out.println(n[x]);
//                        p[x] = removeCard(p[x],n[x]);
//                        //display(p[x]);
//                    }
//                    else
//                    {
//                        n[x]=nextMove(p[x],n[0],n[1],n[2]);
//                        p[x] = removeCard(p[x],n[x]);
//                    }
//                    x++;
//                    if(x==4)
//                        x=0;
//                    i++;
//                }
//            }
//        }




        winner(n);


    }

    public void winner(double n[])
    {

        int i;
        //SystemClock.sleep(2000);
        int pos=find(n[1]);



        img2 = (ImageView) findViewById(R.id.imageViewleft);
        img2.setImageResource(imagesrc[pos]);
        //SystemClock.sleep(3000);
        pos=find(n[2]);
        img2 = (ImageView) findViewById(R.id.imageViewup);
        img2.setImageResource(imagesrc[pos]);
        //SystemClock.sleep(4000);
        pos=find(n[3]);
        img2 = (ImageView) findViewById(R.id.imageViewright);
        img2.setImageResource(imagesrc[pos]);

        display(n);
        if(Math.max(Math.max(n[0],n[1]),Math.max(n[2],n[3]))==5)
        {
            System.out.println("joker was thrown inside winner");
            for( i=0;i<4;i++)
            {
                if((int)n[i]==5)
                {
                    x=i;
                    System.out.println(n[0]+" "+n[1]+" "+n[2]+" "+n[3]);
                    System.out.println("PLAYER " + (i+1) + " won this round ");
                    break;
                }
            }
        }
        else if((int)n[0]==1||(int)n[1]==1||(int)n[2]==1||(int)n[3]==1)
        {
            System.out.println("game card was thronw inside winner");
            double max=-1;
            for(i=0;i<4;i++)
            {
                if((int)n[i]==1)
                {
                    if(max<n[i])
                        max=n[i];
                }
            }
            for( i =0;i<4;i++)
            {
                if(max==n[i])
                {
                    x=i;
                    System.out.println(n[0]+" "+n[1]+" "+n[2]+" "+n[3]);
                    System.out.println("PLAYER " + (i+1) + " won this round ");
                }
            }
        }
        else
        {
            System.out.println("else winner part");
            for(i=0;i<4;i++)
                if(card1!=(int)n[i])
                    n[i]=0;
            System.out.println(n[0]+" "+n[1]+" "+n[2]+" "+n[3]);

            double m = Math.max(Math.max(n[0],n[1]),Math.max(n[2],n[3]));
            for( i =0;i<4;i++)
            {
                if(m==n[i])
                {
                    x=i;
                    System.out.println("PLAYER " + (i+1) + " won this round ");
                }
            }
        }
        // Toast.makeText(this,"PLAYER " + (x+1) + "won this round ",Toast.LENGTH_SHORT).show();
        
        clearTextBackground();
      //  showToast("PLAYER " + (x+1) + " won this round ");

        
        card1=0;
        if(x==0)
        {
            hc[0]++;
            bottom.setText(" "+hc[0]+"  ");
            bottom.setBackgroundColor(Color.parseColor("#1291bf"));
            handBottom.setBackgroundColor(Color.parseColor("#1291bf"));
        }
        else if(x==1)
        {
            hc[1]++;
            left.setText(" "+hc[1]+"  ");
            left.setBackgroundColor(Color.parseColor("#1291bf"));
            handLeft.setBackgroundColor(Color.parseColor("#1291bf"));
        }
        else if(x==2)
        {
            hc[2]++;
            top.setText(" "+hc[2]+"  ");
            top.setBackgroundColor(Color.parseColor("#1291bf"));
            handTop.setBackgroundColor(Color.parseColor("#1291bf"));
        }
        else
        {
            hc[3]++;
            right.setText(" "+hc[3]+"  ");
            right.setBackgroundColor(Color.parseColor("#1291bf"));
            handRight.setBackgroundColor(Color.parseColor("#1291bf"));
        }

//        if(x%2==0)
//            handCoun[0]++;
//        else
//            handCoun[1]++;

        if(r==12) {
            if(hc[0]>=xcount[0])
                ScoreActivity.x1=xcount[0];
            else
                ScoreActivity.x1=-xcount[0];
            if(hc[1]>=xcount[1])
                ScoreActivity.x2=xcount[1];
            else
                ScoreActivity.x2=-xcount[1];
            if(hc[2]>=xcount[2])
                ScoreActivity.x3=xcount[2];
            else
                ScoreActivity.x3=-xcount[2];
            if(hc[3]>=xcount[3])
                ScoreActivity.x4=xcount[3];
            else
                ScoreActivity.x4=-xcount[3];

            Log.i(TAG, " x1 = " + ScoreActivity.x1+" x2 "+ScoreActivity.x2);
            Log.i(TAG, " n1 = " + nu[0]+" n2 "+nu[1]);
            Log.i(TAG, " h1 = " + handCoun[0]+" h2 "+handCoun[1]);

//                ScoreActivity.x1=handCoun[0];
//            ScoreActivity.x2=handCoun[1];
            ScoreActivity.rcount=r;
            r=0;
            hc[0]=0;
            hc[1]=0;
            hc[2]=0;
            hc[3]=0;
            gameOver();

        }
        else {
            r++;

            Log.i(TAG, " r= " + r);
            display(p[0]);
            display(p[1]);
            display(p[2]);
            display(p[3]);
            for (int j = 0; j < 4; j++) {
                n[j] = 0;

            }
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    img2 = (ImageView) findViewById(R.id.imageViewleft);
                    img2.setImageResource(R.drawable.trans);
                    // img2.setImageDrawable(null);
                    img2 = (ImageView) findViewById(R.id.imageViewup);
                    img2.setImageResource(R.drawable.trans);
                    // img2.setImageDrawable(null);
                    img2 = (ImageView) findViewById(R.id.imageViewright);
                    img2.setImageResource(R.drawable.trans);
                    //img2.setImageDrawable(null);
                    img2 = (ImageView) findViewById(R.id.imageViewbottom);
                    img2.setImageResource(R.drawable.trans);
                    // img2.setImageDrawable(null);
                }
            }, 2000);


            if (x == 0) return;

            for (int j = x; j < 4; j++) {
                final int j1 = j;
                if (x == j) {
                    n[x] = firstMove(p[j]);
                    card1 = (int) n[x];
                    p[x] = removeCard(p[x], n[x]);
                } else {
                    System.out.println("nextmove6");
                    n[j] = nextMove(p[j], n[0], n[1], n[2], n[3]);
                    p[j] = removeCard(p[j], n[j]);
                }


                int findCard;
                if(n[j]==0.0)
                    findCard=51;
                else
                    findCard = find(n[j]);
                final int idx = findCard;

                Handler handler3 = new Handler();
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (j1 == 1) {
                            img2 = (ImageView) findViewById(R.id.imageViewleft);
                            img2.setImageResource(imagesrc[idx]);
                        } else if (j1 == 2) {
                            img2 = (ImageView) findViewById(R.id.imageViewup);
                            img2.setImageResource(imagesrc[idx]);
                        } else if (j1 == 3) {
                            img2 = (ImageView) findViewById(R.id.imageViewright);
                            img2.setImageResource(imagesrc[idx]);
                        }
                    }
                }, 3000);


            }
        }
        x=0;
    }

    private void clearTextBackground() {
        bottom.setBackgroundColor(Color.TRANSPARENT);
        handBottom.setBackgroundColor(Color.TRANSPARENT);
        left.setBackgroundColor(Color.TRANSPARENT);
        handLeft.setBackgroundColor(Color.TRANSPARENT);
        top.setBackgroundColor(Color.TRANSPARENT);
        handTop.setBackgroundColor(Color.TRANSPARENT);
        right.setBackgroundColor(Color.TRANSPARENT);
        handRight.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are You Sure To Quit?");
        builder.setMessage("Current Game Will Be Lost");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        r=0;
                        // startActivity(new Intent(this,MainActivity.class));
                        finish();
                    }
                });
        builder.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();
    }
    public void gameOver() {
        // Toast.makeText(this,"GAME OVER ...!!",Toast.LENGTH_SHORT).show();
         //showToast("GAME OVER ...!!");
        startActivity(new Intent(this, ScoreActivity.class));

        finish();
    }

    public static double firstMove(double p[])
    {
        double n=0 ;
        //int c1 =0 ,c2=0,c3=0,c4=0;
        for(int i = 0 ;i<p.length;i++)
        {
            if((p[i]-(int)p[i])>0.13)
            {
                n = p[i];

                break;
            }
        }
        if(n==0)
        { int c[] = new int[4];
            double min = 42;
            for(int i = 0 ;i<p.length;i++)
            {
//                if((int)p[i]==1)
//                    c[0]++;
                if((int)p[i]==2)
                    c[1]++;
                if((int)p[i]==3)
                    c[2]++;
                if((int)p[i]==4)
                    c[3]++;
            }
            for(int i=0;i<4;i++)
                if(c[i]==0)
                    c[i]=99;
            int count = Math.min(Math.min(c[0], c[1]),Math.min(c[3],c[2]));
            if(count == c[0]&&(c[1]==99||c[2]==99||c[3]==99))
                n=p[0];
            else
            {
                if(count == c[0])
                    count = Math.min(Math.min(c[1],c[2]),c[3]);

                for(int i=1;i<4;i++)
                {
                    if(count == c[i])
                    {
                        for(int j=0;j<p.length;j++)
                        {
                            if((i+1)==(int)p[j])
                            {
                                if(min>p[j])
                                    min=p[j];
                            }
                        }
                        break;
                    }

                }

                n= min;
            }
        }
        System.out.println(n);
        return n ;
    }
    public static double nextMove(double p[],double n1,double n2,double n3, double n4)
    {
        double min = 90;
        int c[]= new int[]{0,0,0,0};
        int c2 = Math.max(Math.max((int)n1, (int)n2), Math.max((int)n4,(int)n3));
        int flag =0 , joker = 0,gCard =0;
        for(int i=0;i<p.length;i++)
        {
            if(card1 == (int)p[i])
                flag = 1;
            if((int)p[i]==1)
                gCard = 1 ;
        }

        if(p[p.length-1] == 5.0)
            joker =1;
        if( (joker==1) && (r!=0) && ((n1-(int)n1>0.13) || (n2-(int)n2>0.13)||(n2-(int)n2>0.13) || (n4-(int)n4>0.13) ) )//joker case
            min = p[p.length-1];
        else if((r == 11 || r==12) && joker ==1)
            min = p[p.length-1];
        else if(c2==5)// joker is already thrown
        {
            System.out.println(" joker is already thrown");

            min=91;
            for(int i =0 ;i<p.length;i++)
            {
                if(card1==(int)p[i])
                {
                    if(min>p[i])
                        min=p[i];
                }
            }
            if(min==91)
            {
                for(int i = 0 ;i<p.length;i++)
                {
                    if((int)p[i]==2)
                        c[1]++;
                    if((int)p[i]==3)
                        c[2]++;
                    if((int)p[i]==4)
                        c[3]++;
                }
                for(int i=1;i<4;i++)
                    if(c[i]==0)
                        c[i]=99;
                int count = Math.min(c[1],Math.min(c[3],c[2]));
                if(count==99)
                    min=p[0];
                else {
                    for (int i = 1; i < 4; i++) {
                        if (count == c[i]) {
                            for (int j = 0; j < p.length; j++) {
                                if ((i + 1) == (int) p[j]) {

                                    min = p[j];
                                    break;
                                }
                            }
                            break;
                        }

                    }
                }
            }
        }


        else if(card1!=1 && flag==0 && gCard == 1)//if other color round bt have game card
        {
            int y =0;
            if((int)n1!=1 && (int)n2!=1 && (int)n3!=1 && (int)n4!=1 )
            {
                min = p[0];
            }
            else
            {
                //find max game card if thrown
                min = 0;
                if((int)n1==1)
                {
                    if(min<n1)
                        min=n1;
                }
                if((int)n2==1)
                {
                    if(min<n2)
                        min=n2;
                }
                if((int)n3==1)
                {
                    if(min<n3)
                        min=n3;
                }
                if((int)n4==1)
                {
                    if(min<n4)
                        min=n4;
                }

                for(int i =0;i<p.length;i++)
                {
                    if((int)p[i]==1)
                    {
                        if(p[i]>min)
                        {
                            min=p[i];
                            y=1;
                            break;
                        }
                    }
                }
                if(y==0) {
                    for (int i = 0; i < p.length; i++) {
                        if ((int) p[i] == 2)
                            c[1]++;
                        if ((int) p[i] == 3)
                            c[2]++;
                        if ((int) p[i] == 4)
                            c[3]++;
                    }
                    for (int i = 1; i < 4; i++)
                        if (c[i] == 0)
                            c[i] = 99;
                    int count = Math.min(c[1], Math.min(c[3], c[2]));

                    for (int i = 1; i < 4; i++) {
                        if (count == c[i]) {
                            for (int j = 0; j < p.length; j++) {
                                if ((i + 1) == (int) p[j]) {

                                    min = p[j];
                                    break;
                                }
                            }
                            break;
                        }

                    }
                }
            }
            System.out.println("if other color round bt have game card");
        }

        else if(card1 != 1 && flag == 0 && gCard ==0)//if other than game card but have no game card
        {
            System.out.println(" if other than game card but have no game card");
            for(int i = 0 ;i<p.length;i++)
            {
                if((int)p[i]==2)
                    c[1]++;
                if((int)p[i]==3)
                    c[2]++;
                if((int)p[i]==4)
                    c[3]++;
            }
            for(int i=1;i<4;i++)
                if(c[i]==0)
                    c[i]=99;
            int count = Math.min(c[1],Math.min(c[3],c[2]));

            for(int i=1;i<4;i++)
            {
                if(count == c[i])
                {
                    for(int j=0;j<p.length;j++)
                    {
                        if((i+1)==(int)p[j])
                        {

                            min=p[j];
                            break;
                        }
                    }
                    break;
                }

            }
        }
        else if(card1 == 1 && flag ==0)//game card round bt no game card
        {
            System.out.println(" game card round bt no game card");
            for(int i = 0 ;i<p.length;i++)
            {
                if((int)p[i]==2)
                    c[1]++;
                if((int)p[i]==3)
                    c[2]++;
                if((int)p[i]==4)
                    c[3]++;
            }
            for(int i=1;i<4;i++)
                if(c[i]==0)
                    c[i]=99;
            int count = Math.min(c[1],Math.min(c[3],c[2]));

            for(int i=1;i<4;i++)
            {
                if(count == c[i])
                {
                    for(int j=0;j<p.length;j++)
                    {
                        if((i+1)==(int)p[j])
                        {

                            min=p[j];
                            break;
                        }
                    }
                    break;
                }

            }
        }

        else if(card1!=1 && flag == 1 && (((int)n1==1)||((int)n2==1)||((int)n3==1)||((int)n4==1)))
        {
            for(int i=0;i<p.length;i++)
                if(card1 == (int)p[i])
                {
                    min = p[i];
                    break;
                }
        }

            //System.out.println("gcard = "+gCard+" joker = "+joker+" card1="+card1+" flag= "+flag);

        else
        {
            System.out.println(" normal");
            min = -1;
            double n = Math.max(Math.max(n1,n2),Math.max(n4, n3));
            if((int)n!=card1)
            {
                System.out.println("pass");
                if(card1==1)
                {
                    for(int i = 0;i<p.length;i++)
                    {
                        if(card1 == (int)p[i])
                        {
                            if(min<p[i])
                            {
                                min=p[i];
                            }
                        }
                    }
                }
                else if((int)n1==1||(int)n2==1||(int)n3==1||(int)n4==1)
                {	min=41;
                    for(int i = 0;i<p.length;i++)
                    {
                        if(card1 == (int)p[i])
                        {
                            min=p[i];
                            break;

                        }
                    }
                }
                else
                {
                    for(int i = 0;i<p.length;i++)
                    {
                        if(card1 == (int)p[i])
                        {
                            if(min<p[i])
                            {
                                min=p[i];
                            }
                        }
                    }
                }
            }
            else
            {
                for(int i = 0;i<p.length;i++)
                {
                    if((int)n == (int)p[i])
                    {
                        if(min<p[i])
                        {
                            min=p[i];
                        }
                    }
                }
                if(min<n)
                { min=41;
                    System.out.println("entering min");
                    for(int i = 0;i<p.length;i++)
                    {
                        if((int)n == (int)p[i])
                        {
                            min=p[i];
                            break;

                        }
                    }
                }
            }
        }
        System.out.println(min);
        return min;
    }

    public static double[] removeCard(double[] p, double c) {
        double[] copy = new double[p.length - 1];
        for (int i = 0; i < p.length; i++) {
            if (p[i] == c) {
                System.arraycopy(p, 0, copy, 0, i);
                System.arraycopy(p, i + 1, copy, i, p.length - i - 1);
                break;
            }
        }
        return copy;
    }

    public static int[] removeImage(int[] p, int c) {
        for (int i = 0; i < p.length-1; i++) {
            if (i >= c) {
                p[i]=p[i+1];
            }
        }
        return p;
    }


    public static int handCount(double p[]) {
        int c = 0;
        for (int i = 0; i < 13; i++) {
            if (p[i] == 1.12 || p[i] == 2.12 || p[i] == 3.12 || p[i] == 4.12 || p[i] == 5.0)
                c++;
            if (p[i] == 1.13 || p[i] == 2.13 || p[i] == 3.13 || p[i] == 4.13)
                c++;
            if (p[i] == 1.14 || p[i] == 2.14 || p[i] == 3.14 || p[i] == 4.14)
                c++;
        }
        return c;
    }

    public static double[] ascendingOrder(double p[]) {
        for (int i = 0; i < p.length - 1; i++) {
            for (int j = 0; j < p.length - i - 1; j++) {
                if (p[j] > p[j + 1]) {
                    double temp = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = temp;
                }
            }
        }
        return (p);
    }

    public static void display(double p[]) {
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + " ");
        }
        System.out.println(" ");
    }
    void showToast(String text)
    {
        if(myToast == null)
        {
            myToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        }

        myToast.setText(text);
        myToast.setDuration(Toast.LENGTH_SHORT);
        myToast.show();
    }
}