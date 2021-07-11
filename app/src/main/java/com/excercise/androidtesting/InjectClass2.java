package com.excercise.androidtesting;

public class InjectClass2 {
    private SimpleClassUnderTest.SimpleClassCallback callback;
    public void addListener(SimpleClassUnderTest.SimpleClassCallback callback) {
        this.callback = callback;
    }
}
