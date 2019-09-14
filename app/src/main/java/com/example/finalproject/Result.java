package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class Result implements Serializable, Comparable<Result> {

    String checkMark;
    String actualResult;
    String correctAnswer;

    public Result(){}

    public String checkMark() {
        return checkMark;
    }

    public String getActualResult() {
        return actualResult;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Result(String checkMark, String actualResult, String correctAnswer){

        this.checkMark = checkMark;
        this.actualResult = actualResult;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Result{" +
                "checkMark='" + checkMark + '\'' +
                ", actualResult='" + actualResult + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }

    @Override
    public int compareTo(Result result) {
        return Double.valueOf(actualResult).compareTo(Double.valueOf(result.actualResult));
    }
}
