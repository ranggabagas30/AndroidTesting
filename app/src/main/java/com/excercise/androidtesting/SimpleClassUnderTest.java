package com.excercise.androidtesting;

import androidx.annotation.NonNull;

public class SimpleClassUnderTest {
    private InjectClass1 injectClass1;
    private InjectClass2 injectClass2;

    public SimpleClassUnderTest(@NonNull InjectClass1 injectClass1, @NonNull InjectClass2 injectClass2) {
        this.injectClass1 = injectClass1;
        this.injectClass2 = injectClass2;
    }

    public void check(int value1, int value2) throws IllegalStateException {
        if (this.injectClass1.check(value1)) {
            this.injectClass1.saveValues(value1, value2);
        } else {
            throw new IllegalStateException("Wrong State");
        }
    }

    public void initialize() {
        injectClass2.addListener(new SimpleClassCallback() {
            @Override
            public void onSimpleClass(int firstArgument, String secondArgument) {
                injectClass1.check(firstArgument);
            }
        });
    }

    interface SimpleClassCallback {
        void onSimpleClass(int firstArgument, String secondArgument);
    }
}
