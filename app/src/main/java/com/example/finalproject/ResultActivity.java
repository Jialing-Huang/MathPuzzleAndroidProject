package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import static com.example.finalproject.MainActivity.result;
import static com.example.finalproject.MainActivity.resultList;
import static com.example.finalproject.MainActivity.rightList;
import static com.example.finalproject.MainActivity.wrongList;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup radioGroup;
    RadioButton radioAll, radioRight, radioWrong,radioSortAsc, radioSortDesc;
    EditText editTextRegister;
    TextView textViewScore;
    Button btnBack, btnShow;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initialize();
    }

    private void initialize() {
        editTextRegister = findViewById(R.id.editTextRegister);
        textViewScore = findViewById(R.id.textViewScore);

        listView = findViewById(R.id.listView);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);

        radioGroup = findViewById(R.id.radioGroup);

        double correctRate = 100*rightList.size()/resultList.size();
        textViewScore.setText(Double.toString(correctRate)+"%");

    }

    public void showListView(View view) {
        int selectedRadioBtn = radioGroup.getCheckedRadioButtonId();
        switch(selectedRadioBtn){
            case R.id.radioAll:
                initialzeListView(resultList);
                break;
            case R.id.radioRight:
                initialzeListView(rightList);
                break;
            case R.id.radioWrong:
                initialzeListView(wrongList);
                break;
            case R.id.radioSortAsc:
               Collections.sort(resultList);
                initialzeListView(resultList);
                break;
            case R.id.radioSortDesc:
                Collections.sort(resultList, Collections.reverseOrder());
                initialzeListView(resultList);
                break;
        }
    }

    public void initialzeListView(ArrayList<Result> itemList) {
       listView = findViewById(R.id.listView);
        //1- Define an arratAdapter
        ArrayAdapter<Result> resultArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                itemList);

        //2- set arrayadapter to the listview
        listView.setAdapter(resultArrayAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBack){
            String returnName = editTextRegister.getText().toString();
            String score = textViewScore.getText().toString();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("registeredName", returnName);
            intent.putExtra("score", score);
            startActivity(intent);
        }
    }


}
