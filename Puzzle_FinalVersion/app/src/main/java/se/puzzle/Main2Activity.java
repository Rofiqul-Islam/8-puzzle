package se.puzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void Puzzle(View view){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void algo(View view){
        Intent intent=new Intent(this,Main22Activity.class);
        startActivity(intent);
    }

    public void credit(View view){
        Intent intent=new Intent(this,Main23Activity.class);
        startActivity(intent);
    }
}
