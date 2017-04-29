package se.puzzle;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.os.Handler;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Stack;
import java.util.concurrent.Delayed;


public class MainActivity extends AppCompatActivity {

    int arr[]=new int[10];
    public boolean run=false;
    AstarAlgorithm b;
    Stack stack=new Stack();
    Stack stack1=new Stack();
    ImageView direction;
    LinearLayout linlay;
    ImageView fdsolution;
    ImageView solvable;
    ImageView nsolvable;
    ImageView timeout;
    ImageView complete;
    ImageButton next;
    ImageButton previous;
    RadioGroup radiog;

    TextView text1;
    TextView text2;
    TextView text3;

    SeekBar secbar;

    long valu;
    long startTime;
    long endTime;
    int progress = 1700;
    Button star;
    Button playe;
    Button rando;
    Button set;
    Button shuff;
    int selected;

    EditText edtext;

    TextView up;
    TextView down;
    TextView left;
    TextView right;

    boolean solvablee=false;
    Handler hand= new Handler();
    int randm;
    final Handler handler = new Handler();
    int[] a = {0,1,2,3,4,5,6,7,8};
    String is[]={"zero","one","two","three","four","five","six","seven","eight"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        direction= (ImageView) findViewById(R.id.direction);
        linlay= (LinearLayout) findViewById(R.id.linlay);
        fdsolution=(ImageView) findViewById(R.id.finding);
        solvable= (ImageView) findViewById(R.id.solvable);
        nsolvable=(ImageView) findViewById(R.id.notsolvable);
        timeout=(ImageView) findViewById(R.id.timeout);
        complete=(ImageView) findViewById(R.id.complete);
        next=(ImageButton) findViewById(R.id.next1);
        previous=(ImageButton) findViewById(R.id.previous);
        secbar=(SeekBar) findViewById(R.id.seekBar);

        text1=(TextView) findViewById(R.id.text1);
        text2=(TextView) findViewById(R.id.text2);
        text3=(TextView) findViewById(R.id.text3);

        star=(Button) findViewById(R.id.start);;
        playe=(Button) findViewById(R.id.play);
        rando=(Button) findViewById(R.id.random);
        set=(Button) findViewById(R.id.set);
        shuff=(Button) findViewById(R.id.shuffle);
        radiog=(RadioGroup) findViewById(R.id.radiob);


        edtext=(EditText) findViewById(R.id.edtext);


        up = (TextView) findViewById(R.id.up);
        down = (TextView) findViewById(R.id.down);
        left = (TextView) findViewById(R.id.left);
        right = (TextView) findViewById(R.id.right);

        fdsolution.setVisibility(View.GONE);
        solvable.setVisibility(View.GONE);
        nsolvable.setVisibility(View.GONE);
        playe.setVisibility(View.GONE);
        direction.setVisibility(View.GONE);
        linlay.setVisibility(View.GONE);
        rando.setVisibility(View.GONE);
        set.setVisibility(View.GONE);
        edtext.setVisibility(View.GONE);
        star.setVisibility(View.GONE);
        timeout.setVisibility(View.GONE);
        shuff.setVisibility(View.VISIBLE);
        complete.setVisibility(View.GONE);
        radiog.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        previous.setVisibility(View.GONE);
        text1.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
        text3.setVisibility(View.GONE);
        secbar.setVisibility(View.GONE);
        secbar.setProgress(progress);
        secbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        b=new AstarAlgorithm();
        initialization();
        runTimer();
    }
    public void shuffle(View view){
        run=false;
        solvablee=false;
        playe.setText("Play");
        fdsolution.setVisibility(View.GONE);
        solvable.setVisibility(View.GONE);
        nsolvable.setVisibility(View.GONE);
        playe.setVisibility(View.GONE);
        direction.setVisibility(View.GONE);
        star.setVisibility(View.GONE);
        linlay.setVisibility(View.GONE);
        rando.setVisibility(View.VISIBLE);
        set.setVisibility(View.VISIBLE);
        edtext.setVisibility(View.VISIBLE);
        radiog.setVisibility(View.VISIBLE);
        text1.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
        text3.setVisibility(View.GONE);
        timeout.setVisibility(View.GONE);
        complete.setVisibility(View.GONE);
        shuff.setVisibility(View.GONE);
        direction.setVisibility(View.GONE);
        linlay.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        previous.setVisibility(View.GONE);
        secbar.setVisibility(View.VISIBLE);
        up.setText("");
        down.setText("");
        right.setText("");
        left.setText("");

    }

    public void random(View view){
        String rando[]={"235708146","182043765","278016534","812735460","618305427","216754038","486120573","321407865","083561742","823140687","354701826","214356078","765423180","436078125","312406875"};
        String sf=rando[randm];
        edtext.setText(sf);
        randm=(randm+1)%15;
        for(int i=0;i<sf.length();i++)
        {
            a[i]=sf.charAt(i)-'0';
        }
        stack=new Stack();
        stack1=new Stack();
        initialization();
        star.setVisibility(View.VISIBLE);
    }

    public void set(View view){
        String sf=edtext.getText().toString();
        int ab[]=new int[10];
        boolean nota=true;
            if(sf.length()==9) {
                for (int i = 0; i < sf.length(); i++) {
                    a[i] = sf.charAt(i) - '0';
                    if(a[i]>=0 && a[i]<=8)
                        ab[a[i]]=1;
                }
                for (int i = 0; i < sf.length(); i++) {
                    if(ab[i]==0)
                    {
                        nota=false;
                        edtext.setTextColor(Color.RED);
                        break;
                    }
                }
                if(nota) {
                    edtext.setTextColor(Color.rgb(255,255,255));
                    stack = new Stack();
                    stack1 = new Stack();
                    initialization();
                    star.setVisibility(View.VISIBLE);
                }
            }
        else
                edtext.setTextColor(Color.RED);
    }
    public void initialization(){
        ImageView zero = (ImageView) findViewById(R.id.zero);
        int resID = getResources().getIdentifier(is[a[0]], "drawable", getPackageName());
        zero.setImageResource(resID);
        ImageView one = (ImageView) findViewById(R.id.one);
        resID = getResources().getIdentifier(is[a[1]], "drawable", getPackageName());
        one.setImageResource(resID);
        ImageView two = (ImageView) findViewById(R.id.two);
        resID = getResources().getIdentifier(is[a[2]], "drawable", getPackageName());
        two.setImageResource(resID);

        ImageView three = (ImageView) findViewById(R.id.three);
        resID = getResources().getIdentifier(is[a[3]], "drawable", getPackageName());
        three.setImageResource(resID);

        ImageView four = (ImageView) findViewById(R.id.four);
        resID = getResources().getIdentifier(is[a[4]], "drawable", getPackageName());
        four.setImageResource(resID);

        ImageView five = (ImageView) findViewById(R.id.five);
        resID = getResources().getIdentifier(is[a[5]], "drawable", getPackageName());
        five.setImageResource(resID);

        ImageView six = (ImageView) findViewById(R.id.six);
        resID = getResources().getIdentifier(is[a[6]], "drawable", getPackageName());
        six.setImageResource(resID);

        ImageView seven = (ImageView) findViewById(R.id.seven);
        resID = getResources().getIdentifier(is[a[7]], "drawable", getPackageName());
        seven.setImageResource(resID);

        ImageView eight = (ImageView) findViewById(R.id.eight);
        resID = getResources().getIdentifier(is[a[8]], "drawable", getPackageName());
        eight.setImageResource(resID);

    }

   public void sort(View view) throws InterruptedException {
       fdsolution.setVisibility(View.VISIBLE);
       shuff.setVisibility(View.INVISIBLE);
       rando.setVisibility(View.GONE);
       set.setVisibility(View.GONE);
       edtext.setVisibility(View.GONE);

       int select=radiog.getCheckedRadioButtonId();
       if(select==R.id.hamming)
           selected=1;
       else
            selected=2;

       radiog.setVisibility(View.GONE);
       startTime = System.currentTimeMillis();

       endTime=startTime+15000;
       start();
    }

    public void Play(View view){
        solvable.setVisibility(View.GONE);
       // fdsolution.setVisibility(View.GONE);
        shuff.setVisibility(View.VISIBLE);
        if(run==false)
        {
            run=true;
            playe.setText("Pause");
            next.setVisibility(View.GONE);
            previous.setVisibility(View.GONE);
            secbar.setVisibility(View.VISIBLE);

        }
        else
        {
            run=false;
            playe.setText("Play");
            secbar.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
            previous.setVisibility(View.VISIBLE);

        }
        direction.setVisibility(View.VISIBLE);
        linlay.setVisibility(View.VISIBLE);

    }

    public int getInvCount(int a[])
    {
        int inv_count = 0;
        for (int i = 0; i < 9 - 1; i++)
            for (int j = i+1; j < 9; j++)
                // Value 0 is used for empty space
                if (a[j]!=0 && a[i]!=0 &&  a[i] > a[j])
                    inv_count++;
        return inv_count;
    }

    public boolean issolvable(int a[]){
            int inv_count=getInvCount(a);
            if((inv_count%2)==0)
                return true;
        else
                return false;
    }

    public void start() throws InterruptedException {
        star.setVisibility(View.GONE);

        if(issolvable(a)) {
            b.Puzzle(a,selected);

            hand.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Boolean flog=false;
                    while(System.currentTimeMillis()<endTime)
                    {
                        if(b.t.isAlive()==false)
                        {
                           // b.t.join();
                            Node goalNodeFound = b.gnode;
                            while (goalNodeFound.parent!= null) {//loop for goal state iteration
                                if (goalNodeFound.move != null) {
                                    // System.out.println(goalNodeFound.state.toString());
                                    goalNodeFound.parent.chmove = goalNodeFound.move;
                                    stack.push(goalNodeFound);
                                }
                                goalNodeFound = goalNodeFound.parent;
                            }
                            fdsolution.setVisibility(View.GONE);
                           solvable.setVisibility(View.VISIBLE);
                            playe.setVisibility(View.VISIBLE);
                            flog=true;
                            break;
                        }
                    }

                    if(flog==false)
                    {
                        b.t.interrupt();
                        fdsolution.setVisibility(View.GONE);
                        timeout.setVisibility(View.VISIBLE);
                        shuff.setVisibility(View.VISIBLE);
                    }
                }
            }, 50);
        }
        else
        {

            hand.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fdsolution.setVisibility(View.GONE);
                    nsolvable.setVisibility(View.VISIBLE);
                    shuff.setVisibility(View.VISIBLE);
                }
            }, 100);
        }

    }
    public void previous(View view){
        direction.setVisibility(View.VISIBLE);
        playe.setVisibility(View.VISIBLE);
        linlay.setVisibility(View.VISIBLE);
        complete.setVisibility(View.GONE);

        Handler han1=new Handler();
        han1.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!stack1.isEmpty()) {
                    //loop for file wrriting stage by stage
                    Node gl = (Node) stack1.pop();
                    stack.push(gl);
                    String str = gl.state.toString();
                    str = str.replaceAll(",", "");
                    str = str.replaceAll(" ", "");
                    String s = "";
                    int i;
                    for (i = 1; i < str.length() - 1; i++)
                        s += str.charAt(i);
                    //System.out.println(s);//After this line file wrriting is started;
                      /*  for(i=0;i<=8;i++){
                            arr[i]="";
                        }*/
                    for (i = 0; i <= 8; i++) {
                        arr[i] = s.charAt(i) - '0';
                    }
                    my(gl);
                    next.setVisibility(View.VISIBLE);

                } else {
                    previous.setVisibility(View.GONE);
                    direction.setVisibility(View.GONE);
                    linlay.setVisibility(View.GONE);
                }
            }

        }, 50);

    }

    public void next(View view){
        direction.setVisibility(View.VISIBLE);
        playe.setVisibility(View.VISIBLE);
        linlay.setVisibility(View.VISIBLE);
        complete.setVisibility(View.GONE);
        Handler han1=new Handler();
        han1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!stack.isEmpty()) {
                    //loop for file wrriting stage by stage
                    Node gl = (Node) stack.pop();
                    stack1.push(gl);
                    String str = gl.state.toString();
                    str = str.replaceAll(",", "");
                    str = str.replaceAll(" ", "");
                    String s = "";
                    int i;
                    for (i = 1; i < str.length() - 1; i++)
                        s += str.charAt(i);
                    //System.out.println(s);//After this line file wrriting is started;
                      /*  for(i=0;i<=8;i++){
                            arr[i]="";
                        }*/
                    for (i = 0; i <= 8; i++) {
                        arr[i] = s.charAt(i) - '0';
                    }
                    my(gl);
                    previous.setVisibility(View.VISIBLE);
                } else {
                    next.setVisibility(View.GONE);
                    direction.setVisibility(View.GONE);
                    linlay.setVisibility(View.GONE);
                    complete.setVisibility(View.VISIBLE);
                    playe.setVisibility(View.GONE);
                }
            }

        }, 200);

    }

    private void runTimer() {


        handler.post(new Runnable() {
            @Override
            public void run() {
                if (run == true && !stack.isEmpty()) {
                    //loop for file wrriting stage by stage
                    Node gl = (Node) stack.pop();
                    stack1.push(gl);
                    String str = gl.state.toString();
                    str = str.replaceAll(",", "");
                    str = str.replaceAll(" ", "");
                    String s = "";
                    int i;
                    for (i = 1; i < str.length() - 1; i++)
                        s += str.charAt(i);
                    //System.out.println(s);//After this line file wrriting is started;
                      /*  for(i=0;i<=8;i++){
                            arr[i]="";
                        }*/
                    for (i = 0; i <= 8; i++) {
                        arr[i] = s.charAt(i) - '0';
                    }
                    my(gl);

                }
                else if(run==true)
                {
                    secbar.setVisibility(View.GONE);
                    direction.setVisibility(View.GONE);
                    linlay.setVisibility(View.GONE);
                    complete.setVisibility(View.VISIBLE);
                    playe.setVisibility(View.GONE);
                }

                handler.postDelayed(this, 50+progress);
            }

        });

    }

    void my(Node gl)
    {
        ImageView zero = (ImageView) findViewById(R.id.zero);
        int resID = getResources().getIdentifier(is[arr[0]], "drawable", getPackageName());
        zero.setImageResource(resID);
        ImageView one = (ImageView) findViewById(R.id.one);
        resID = getResources().getIdentifier(is[arr[1]], "drawable", getPackageName());
        one.setImageResource(resID);
        ImageView two = (ImageView) findViewById(R.id.two);
        resID = getResources().getIdentifier(is[arr[2]], "drawable", getPackageName());
        two.setImageResource(resID);

        ImageView three = (ImageView) findViewById(R.id.three);
        resID = getResources().getIdentifier(is[arr[3]], "drawable", getPackageName());
        three.setImageResource(resID);

        ImageView four = (ImageView) findViewById(R.id.four);
        resID = getResources().getIdentifier(is[arr[4]], "drawable", getPackageName());
        four.setImageResource(resID);

        ImageView five = (ImageView) findViewById(R.id.five);
        resID = getResources().getIdentifier(is[arr[5]], "drawable", getPackageName());
        five.setImageResource(resID);

        ImageView six = (ImageView) findViewById(R.id.six);
        resID = getResources().getIdentifier(is[arr[6]], "drawable", getPackageName());
        six.setImageResource(resID);

        ImageView seven = (ImageView) findViewById(R.id.seven);
        resID = getResources().getIdentifier(is[arr[7]], "drawable", getPackageName());
        seven.setImageResource(resID);

        ImageView eight = (ImageView) findViewById(R.id.eight);
        resID = getResources().getIdentifier(is[arr[8]], "drawable", getPackageName());
        eight.setImageResource(resID);

        Node states[]=gl.states;
        up.setTextColor(Color.rgb(255,255,255));
        down.setTextColor(Color.rgb(255,255,255));
        left.setTextColor(Color.rgb(255,255,255));
        right.setTextColor(Color.rgb(255,255,255));
        char h='H';
        char m='M';

        text1.setText(((selected==1)?"H = Hamming":"M = Manhattan"));
        text1.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);
        text3.setVisibility(View.VISIBLE);

        if(states!=null) {
            if (states[0] != null) {
                if(states[0].visited==0)
                up.setText("Sequence: <" + TextUtils.join(", ", states[0].state) + " >\n"+((selected==1)?h:m)+" = " + states[0].count + "; D = "+states[0].distance+"; P = " + states[0].priority+";");
                else if(states[0].visited==1)
                    up.setText("Sequence: <" + TextUtils.join(", ", states[0].state) + " >\nVisited");
                else
                    up.setText("Sequence: <" + TextUtils.join(", ", states[0].state) + " >\nNot Calculated");
            }
            else
                up.setText("\n");

            if (states[1] != null)
            {
                if(states[1].visited==0)
                down.setText("Sequence: <" + TextUtils.join(", ", states[1].state) + " >\n"+((selected==1)?h:m)+" = " + states[1].count +"; D = "+states[1].distance+ "; P = " + states[1].priority+";");
                else if(states[1].visited==1)
                    down.setText("Sequence: <" + TextUtils.join(", ", states[1].state) + " >\nVisited");
                else
                    down.setText("Sequence: <" + TextUtils.join(", ", states[1].state) + " >\nNot Calculated");
            }
            else
                down.setText("\n");
            if (states[2] != null)
            {
                if(states[2].visited==0)
                left.setText("Sequence: <" + TextUtils.join(", ", states[2].state) + " >\n"+((selected==1)?h:m)+" = " + states[2].count + "; D = "+states[2].distance+"; P = " + states[2].priority+";");
                else if(states[2].visited==1)
                    left.setText("Sequence: <" + TextUtils.join(", ", states[2].state) + " >\nVisited");
                else
                    left.setText("Sequence: <" + TextUtils.join(", ", states[2].state) + " >\nNot Calculated");
            }
            else
                left.setText("\n");
            if (states[3] != null)
            {
                if(states[3].visited==0)
                    right.setText("Sequence: <" + TextUtils.join(", ", states[3].state) + " >\n"+((selected==1)?h:m)+" = " + states[3].count +"; D = "+states[3].distance+ "; P = " + states[3].priority+";");
                else if(states[3].visited==1)
                    right.setText("Sequence: <" + TextUtils.join(", ", states[3].state) + " >\nVisited");
                else
                    right.setText("Sequence: <" + TextUtils.join(", ", states[3].state) + " >\nNot Calculated");
            }
            else
                right.setText("\n");

            if(gl.chmove.equals("UP"))
                up.setTextColor(Color.rgb(255, 222, 0));
            else if(gl.chmove.equals("RIGHT"))
                right.setTextColor(Color.rgb(255, 222, 0));
            else if(gl.chmove.equals("DOWN"))
                down.setTextColor(Color.rgb(255, 222, 0));
            else if(gl.chmove.equals("LEFT"))
                left.setTextColor(Color.rgb(255, 222, 0));
        }
        else
        {
            text1.setVisibility(View.GONE);
            text2.setVisibility(View.GONE);
            text3.setVisibility(View.GONE);
            direction.setVisibility(View.GONE);
            linlay.setVisibility(View.GONE);
        }
    }

}
