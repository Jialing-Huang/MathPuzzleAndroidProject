package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Claim the visible components
    EditText editTextScreen;
    TextView textViewShowEquation,header;

    Button padNumberZero,padNumberOne,padNumberTwo,padNumberThree,padNumberFour,padNumberFive,padNumberSix,padNumberSeven,padNumberEight,padNumberNine;
    Button padSignalPoint,padSignalNegative;

    Button padGenerate,padValidate,padClear;

    Button btnScore,btnFinish;

    String editViewShownResult = "";

    static double result = 0.0;

    int calculationTimes = 0;
    int correctTimes = 0;
    int wrongTimes = 0;


    Result resultItems;
    static ArrayList<Result> resultList = new ArrayList<>();
    static ArrayList<Result> rightList = new ArrayList<>();
    static ArrayList<Result> wrongList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        receiveData();

    }

    private void receiveData() {
        Intent myIntent = getIntent();
        String name = "";
        String score = "";

        if(myIntent.hasExtra("registeredName")){
            name = myIntent.getStringExtra("registeredName");
        }

        if(myIntent.hasExtra("score")){
            score = myIntent.getStringExtra("score");
        }

        header.setText("Name: " + name + " Score: " + score);

    }

    private void initialize(){
        editTextScreen = findViewById(R.id.editTextScreen);
        textViewShowEquation = findViewById(R.id.textViewShowEquation);
        header = findViewById(R.id.header);
        header.setText("Math Quiz");

        padNumberZero = findViewById(R.id.padNumberZero);
        padNumberZero.setOnClickListener(this);

        padNumberOne = findViewById(R.id.padNumberOne);
        padNumberOne.setOnClickListener(this);

        padNumberTwo = findViewById(R.id.padNumberTwo);
        padNumberTwo.setOnClickListener(this);

        padNumberThree = findViewById(R.id.padNumberThree);
        padNumberThree.setOnClickListener(this);

        padNumberFour = findViewById(R.id.padNumberFour);
        padNumberFour.setOnClickListener(this);

        padNumberFive = findViewById(R.id.padNumberFive);
        padNumberFive.setOnClickListener(this);

        padNumberSix = findViewById(R.id.padNumberSix);
        padNumberSix.setOnClickListener(this);

        padNumberSeven = findViewById(R.id.padNumberSeven);
        padNumberSeven.setOnClickListener(this);

        padNumberEight = findViewById(R.id.padNumberEight);
        padNumberEight.setOnClickListener(this);

        padNumberNine = findViewById(R.id.padNumberNine);
        padNumberNine.setOnClickListener(this);

        padSignalPoint = findViewById(R.id.padSignalPoint);
        padSignalPoint.setOnClickListener(this);

        padSignalNegative = findViewById(R.id.padSignalNegative);
        padSignalNegative.setOnClickListener(this);

        padGenerate = findViewById(R.id.padGenerate);
        padGenerate.setOnClickListener(this);

        padValidate = findViewById(R.id.padValidate);
        padValidate.setOnClickListener(this);

        padClear = findViewById(R.id.padClear);
        padClear.setOnClickListener(this);

        btnScore = findViewById(R.id.btnScore);
        btnScore.setOnClickListener(this);

        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Ensure Negative signal only available at the first position of input
        if(editViewShownResult == ""){
            if(v.getId() == R.id.padSignalNegative){
                editViewShownResult +=  padSignalNegative.getTag().toString();
            }
        }else{
            padSignalNegative.setClickable(false);
        }

        //Identify click actions
        switch (v.getId()){
            case R.id.padNumberZero:
                editViewShownResult +=  padNumberZero.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padNumberOne:
                editViewShownResult +=  padNumberOne.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padNumberTwo:
                editViewShownResult +=  padNumberTwo.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padNumberThree:
                editViewShownResult +=  padNumberThree.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padNumberFour:
                editViewShownResult +=  padNumberFour.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padNumberFive:
                editViewShownResult +=  padNumberFive.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padNumberSix:
                editViewShownResult +=  padNumberSix.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padNumberSeven:
                editViewShownResult +=  padNumberSeven.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padNumberEight:
                editViewShownResult +=  padNumberEight.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padNumberNine:
                editViewShownResult +=  padNumberNine.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                break;

            case R.id.padSignalPoint:
                editViewShownResult +=  padSignalPoint.getTag().toString();
                editTextScreen.setText(editViewShownResult);
                padSignalPoint.setClickable(false);  //Ensure point only available once
                break;

            case R.id.padGenerate:
                generate();
                break;

            case R.id.padValidate:
                validate();
                break;

            case R.id.padClear:
                clearRun();
                break;

            case R.id.btnScore:
                score();
                break;

            case R.id.btnFinish:
                finishRun();
                break;
        }

    }

    private void generate() {

        //Set random value for operation
        final int firstNumber = new Random().nextInt(100);
        final int secondNumber = new Random().nextInt(100);
        final int random = new Random().nextInt(4);

        //Identify to show which calculation format
        try{
            switch (random){
                case 0:
                    result = (double) (firstNumber + secondNumber);
                    textViewShowEquation.setText(Integer.toString(firstNumber) + "+" + Integer.toString(secondNumber) );
                    break;
                case 1:
                    result = (double) (firstNumber - secondNumber);
                    textViewShowEquation.setText(Integer.toString(firstNumber) + "-" + Integer.toString(secondNumber));
                    break;
                case 2:
                    result = (double) (firstNumber * secondNumber);
                    textViewShowEquation.setText(Integer.toString(firstNumber) + "*" + Integer.toString(secondNumber));
                    break;
                case 3:
                    result = (double) (firstNumber / secondNumber);
                    textViewShowEquation.setText(Integer.toString(firstNumber) + "/" + Integer.toString(secondNumber));
                    break;
            }
        }catch(IllegalArgumentException e){
            Toast.makeText(this,"Error at operation, please clear and re-enter", Toast.LENGTH_SHORT).show();
        }

    }

    private void finishRun() {
        finish();
    }

    private void validate() {
        //Create new class to collect info

        String checkmark = "";
        String actualresult = "";
        String correctanswer = Double.toString(result);

        resultItems = new Result(checkmark, actualresult, correctanswer);

        calculationTimes++;

        if(Double.valueOf(editViewShownResult) == result ){
            //Create a toast objecct
            Toast toast = Toast.makeText(this, "You are so smart", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM,0,0);
            //Set used icon
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.laughing);
            //Set layout
            LinearLayout toastView = (LinearLayout) toast.getView();
            toastView.setOrientation(LinearLayout.HORIZONTAL);
            //Add the icon to the layout
            toastView.addView(imageView,1);
            toast.show();

            correctTimes++;
            resultItems.checkMark = "Correct";

        }else{
            //Create a toast objecct
            Toast toast = Toast.makeText(this, "Are you nuts?", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            //Set used icon
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.stupid);
            //Set layout
            LinearLayout toastView = (LinearLayout) toast.getView();
            toastView.setOrientation(LinearLayout.HORIZONTAL);
            //Add the icon to the layout
            toastView.addView(imageView,0);
            toast.show();

            wrongTimes++;
            resultItems.checkMark = "Wrong";
        }

        resultItems.actualResult = (String) Double.toString(Double.parseDouble(editViewShownResult));
        //Send a data class to the list
        resultList.add(resultItems);
        if(resultItems.checkMark == "Correct"){
            rightList.add(resultItems);
        }else{
            wrongList.add(resultItems);
        }
    }

    private void score() {
        //Send all required info into resultList

        //Sent the resultList to ResultActivity by intent and bundle
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("dataFromCalculation", resultList);
        if(resultList.size() == 0){
            Toast.makeText(this,"No operation done, please do at least one time operation", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra("dataByScore", bundle);
        startActivity(intent);
    }

    private void clearRun() {
        editViewShownResult = "";
        editTextScreen.setText(null);
        padSignalPoint.setClickable(true); //Reset point to be clickable
        padSignalNegative.setClickable(true); //Reset negative to be clickable

    }
}
