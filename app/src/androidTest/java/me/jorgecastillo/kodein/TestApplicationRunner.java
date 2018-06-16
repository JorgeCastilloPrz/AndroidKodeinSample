package me.jorgecastillo.kodein;

import android.app.Application;
import android.content.Context;

import com.github.tmurakami.dexopener.DexOpenerAndroidJUnitRunner;

public class TestApplicationRunner extends DexOpenerAndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, "me.jorgecastillo.kodein.TestApp", context);
    }
}
