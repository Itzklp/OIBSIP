package com.example.quizapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizPage extends AppCompatActivity implements View.OnClickListener{
    long pressedTime;
    public static String questions[] ={
            "Which team win the 2022 football world cup that held in Qatar ?",
            "Which player was the player of the tournament in the 2022 football world cup that held in Qatar ?",
            "Which team Win the 2023 UEFA champions league ?",
            "Who is the highest goalscorer in the national team of india ?",
            "Which croatian player has won UEFA champions league, UEFA europa league and UEFA super cup ?",
            "Which player is considered as don of midfield ?",
            "Which player holds most red cards from following ?"
    };

    public static String choices[][] = {
            {"India","England","France","Argentina"},
            {"Mbappe","Martinez","Messi","Ronaldo"},
            {"Barcelona","Real Madrid","Inter","Manchester City"},
            {"Bhaichung Bhutia","Sunil Chhetri","Kalp Dalsania","None Of the Above"},
            {"Ivan Rakitic","Luka Modric","Ivan Perisic","Marcelo Brozovic"},
            {"Xavi","Iniesta","Busquest","Zidane"},
            {"Pique","Ramos","Khan","Christiansen"},
    };

    public static String correctAnswers[] = {
            "Argentina",
            "Messi",
            "Manchester City",
            "Sunil Chhetri",
            "Ivan Rakitic",
            "Iniesta",
            "Ramos"
    };
    TextView totalQuestions;
    TextView question;
    Button btn1, btn2, btn3, btn4, submit;

    int score=0;
    int totalQuestion = questions.length;
    int QuestionIndex = 0;
    String selectedAnswer = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestions = findViewById(R.id.total_question);
        question = findViewById(R.id.questions);
        btn1 = findViewById(R.id.opt1);
        btn2 = findViewById(R.id.opt2);
        btn3 = findViewById(R.id.opt3);
        btn4 = findViewById(R.id.opt4);
        submit = findViewById(R.id.submit_btn);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestions.setText("Total questions : "+totalQuestion);

        newQuestion();




    }

    @Override
    public void onClick(View view) {

        btn1.setBackgroundColor(Color.WHITE);
        btn2.setBackgroundColor(Color.WHITE);
        btn3.setBackgroundColor(Color.WHITE);
        btn4.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(correctAnswers[QuestionIndex])){
                score++;
            }
            QuestionIndex++;
            newQuestion();


        }else{
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.parseColor("#FC0388"));

        }

    }

    void newQuestion(){

        if(QuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        question.setText(questions[QuestionIndex]);
        btn1.setText(choices[QuestionIndex][0]);
        btn2.setText(choices[QuestionIndex][1]);
        btn3.setText(choices[QuestionIndex][2]);
        btn4.setText(choices[QuestionIndex][3]);

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Exit",(dialogInterface, i) -> exit() )
                .setCancelable(false)
                .show();


    }

    void exit(){
        finishAffinity();
    }
    public void onBackPressed(){
        if(pressedTime + 2000 >System.currentTimeMillis()){
            super.onBackPressed();
            finishAffinity();
        }else{
            Toast.makeText(getApplicationContext(),"Press Back Again To Exit",Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}
