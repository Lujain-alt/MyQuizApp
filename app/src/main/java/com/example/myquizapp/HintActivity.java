package com.example.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    TextView finalMark,trueMark,falseMark;

    Button home;
    ImageView star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        finalMark=findViewById(R.id.final_mark);
        trueMark=findViewById(R.id.true_mark);
        falseMark=findViewById(R.id.false_mark);
        home=findViewById(R.id.home_btn);
        star=findViewById(R.id.winner);


       finalMark.setText("the score is: "+" "+getIntent().getStringExtra("score"));
       trueMark.setText("the right answer are :"+" "+getIntent().getStringExtra("trueScore"));
        falseMark.setText("the Wrong answe are :"+" "+getIntent().getStringExtra("falseScore"));


        if((getIntent().getIntExtra("wof",1))==6)
            star.setImageResource(R.drawable.star);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HintActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}