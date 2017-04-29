package se.puzzle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    int arr[]=new int[10];
    public boolean run=false;
    AstarAlgo b;
    Stack stack=new Stack();
    final Handler handler = new Handler();
    int[] a = {0,1,2,3,4,5,6,7,8};
    String is[]={"zero","one","two","three","four","five","six","seven","eight"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update(a);
        runTimer();

    }
    public void shuffle(View view){
        run=false;

        String sf="182043765";
        for(int i=0;i<sf.length();i++)
        {
            a[i]=sf.charAt(i)-'0';
        }
        stack=new Stack();
        b=new AstarAlgo();
        update(a);
        start();
    }


   public void sort(View view) {
        run=true;



    }

    public void start(){
        try {
            Nodes goalNodeFound=b.puzzlesover(a);
            while (goalNodeFound.parent != null){//loop for goal state iteration
                if(goalNodeFound.move != null){
                    // System.out.println(goalNodeFound.state.toString());
                    stack.push(goalNodeFound.state);
                }
                goalNodeFound = goalNodeFound.parent;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void runTimer() {


        handler.post(new Runnable() {
            @Override
            public void run() {
                if (run == true && !stack.isEmpty()) {
                    //loop for file wrriting stage by stage
                    String str = stack.pop().toString();
                    str = str.replaceAll(",", "");
                    str = str.replaceAll(" ", "");
                    String s = "";
                    int i;
                    for (i = 1; i < str.length() - 1; i++)
                        s += str.charAt(i);

                    for (i = 0; i <= 8; i++) {
                        arr[i] = s.charAt(i) - '0';
                    }

                    update(arr);

                }


                handler.postDelayed(this, 2000);
            }

        });

    }
    void update(int u[])
    {
        ImageView id;
        int imageID,resID;
        for(int i=0;i<=8;i++) {
            String viewId = is[i];
            imageID = getResources().getIdentifier(viewId, "id", getPackageName());
            id = (ImageView) findViewById(imageID);
            resID = getResources().getIdentifier(is[u[i]], "drawable", getPackageName());
            id.setImageResource(resID);
        }

    }





}
