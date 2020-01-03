package com.example.myquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button startBTN,femaleBTN,maleBTN,db,dm,hci,android;

   EditText nameEditText;

   boolean gender,checked=false;
    TextView counter,text_question;
    int count=0;
    int current_index;
    Questions questionText[];
    RadioGroup radioGender;


   // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=findViewById(R.id.db_btn);
        dm=findViewById(R.id.dm_btn);
        hci=findViewById(R.id.hci_btn);
        android=findViewById(R.id.android_btn);
        radioGender=findViewById(R.id.radio_gender);


        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dm.setEnabled(false);
                hci.setEnabled(false);
                android.setEnabled(false);
                 checked=true;



                questionText=new Questions[]
                        {
                                new Questions(R.string.DBQuestion1Easy,true,1),
                                new Questions(R.string.DBQquestion2Easy,false,1),
                                new Questions(R.string.DBQquestion3Easy,true,1),
                                new Questions(R.string.DBQuestion1Mid,false,2),
                                new Questions(R.string.DBQquestion2Mid,false,2),
                                new Questions(R.string.DBQquestion3Mid,true,2),
                                new Questions(R.string.DBQuestion1Defficalt,false,3),
                                new Questions(R.string.DBQquestion2Defficalt,false,3),
                                new Questions(R.string.DBQquestion3Defficalt,true,3)
                        };


            }
        });


        dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.setEnabled(false);
                hci.setEnabled(false);
                android.setEnabled(false);
                checked=true;
               /* text_question.setVisibility(View.VISIBLE);
                true_false.setVisibility(View.VISIBLE);
                preNext.setVisibility(View.VISIBLE);*/

                questionText=new Questions[]
                        {
                                new Questions(R.string.DMQuestion1Easy,false,1),
                                new Questions(R.string.DMQuestion2Easy,true,1),
                                new Questions(R.string.DMQuestion3Easy,true,1),
                                new Questions(R.string.DMQuestion1Mid,false,2),
                                new Questions(R.string.DMQuestion2Mid,true,2),
                                new Questions(R.string.DMQuestion3Mid,true,2),
                                new Questions(R.string.DMQuestion1Defficalt,true,3),
                                new Questions(R.string.DMQuestion2Defficalt,true,3),
                                new Questions(R.string.DMQuestion3Defficalt,true,3)
                        };



            }
        });

        hci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.setEnabled(false);
                dm.setEnabled(false);
                android.setEnabled(false);
                checked=true;


                questionText=new Questions[]
                        {
                                new Questions(R.string.HCIQuestion1Easy,true,1),
                                new Questions(R.string.HCIQuestion2Easy,true,1),
                                new Questions(R.string.HCIQuestion3Easy,true,1),

                                new Questions(R.string.HCIQuestion1Mid,true,2),
                                new Questions(R.string.HCIQuestion2Mid,false,2),
                                new Questions(R.string.HCIQuestion3Mid,true,2),

                                new Questions(R.string.HCIQuestion1Defficalt,true,3),
                                new Questions(R.string.HCIQuestion2Defficalt,false,3),
                                new Questions(R.string.HCIQuestion3Defficalt,false,3)
                        };

            }
        });

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.setEnabled(false);
                dm.setEnabled(false);
                hci.setEnabled(false);
                checked=true;

                questionText=new Questions[]
                        {
                                new Questions(R.string.AndroidQuestion1Easy,true,1),
                                new Questions(R.string.AndroidQuestion2Easy,true,1),
                                new Questions(R.string.AndroidQuestion3Easy,false,1),
                                new Questions(R.string.AndroidQuestion1Mid,true,2),
                                new Questions(R.string.AndroidQuestion2Mid,true,2),
                                new Questions(R.string.AndroidQuestion3Mid,false,2),
                                new Questions(R.string.AndroidQuestion1Defficalt,false,3),
                                new Questions(R.string.AndroidQuestion2Defficalt,false,3),
                                new Questions(R.string.AndroidQuestion3Defficalt,true,3)
                        };


            }
        });

        femaleBTN = findViewById(R.id.female_button);
        femaleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender=false;
            }
        });
        maleBTN = findViewById(R.id.male_button);
        maleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender=true;
            }
        });
        nameEditText = findViewById(R.id.name_edit_text);

        startBTN = findViewById(R.id.start_btton);
        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!nameEditText.getText().toString().equals(""))
                {
                    Intent i=new Intent(MainActivity.this,FinalQuizActivity.class);

                }
                else {
                  Toast.makeText(MainActivity.this,"Please,Enter your name",Toast.LENGTH_SHORT).show();
                    if(checked==false)
                    {
                        Toast.makeText(MainActivity.this,"Please, choose Catogry",Toast.LENGTH_SHORT).show();

                    }
                }

                if(!nameEditText.getText().toString().equals("") && checked ==true)
                {
                    int selectedId = radioGender.getCheckedRadioButtonId();
                    if(selectedId==(R.id.male_button))
                    {
                        gender=true;
                    }

                    else if(selectedId==(R.id.female_button))
                    {
                        gender=false;
                    }



                    Intent mainIntent=new Intent(MainActivity.this,FinalQuizActivity.class);
                // mainIntent.putExtra("hint",questionText[current_index].isAnswerTrue());

                mainIntent.putExtra("gender",gender);
                mainIntent.putExtra("name", nameEditText.getText().toString());

                mainIntent.putExtra("question",questionText);
                startActivity(mainIntent);


                }


            }
        });
    }
}

