package se.puzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import android.os.Handler;
import java.util.logging.LogRecord;

public class Main22Activity extends AppCompatActivity {


    ImageView imageV;
    ImageView image2;
    ImageView image3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        imageV=(ImageView) findViewById(R.id.imageV);
        image2=(ImageView) findViewById(R.id.image2);
        image3=(ImageView) findViewById(R.id.image3);
        imageV.setVisibility(View.GONE);
        image2.setVisibility(View.GONE);
        image3.setVisibility(View.GONE);
    }
    public void asea(View view){
        image2.setVisibility(View.GONE);
        imageV.setVisibility(View.GONE);
        image3.setVisibility(View.VISIBLE);
    }
    public void hamming(View view){
        image3.setVisibility(View.GONE);
        image2.setVisibility(View.GONE);
        imageV.setVisibility(View.VISIBLE);
    }
    public void manhatton(View view){
        image3.setVisibility(View.GONE);
        imageV.setVisibility(View.GONE);
        image2.setVisibility(View.VISIBLE);
    }
}
