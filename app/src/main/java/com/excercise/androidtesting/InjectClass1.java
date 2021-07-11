package com.excercise.androidtesting;

public class InjectClass1 {
    private int value1;
    private int value2;

    public boolean check(int value){
        if (value >= 5) return true;
        else return false;
    }

    public void saveValues(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}
