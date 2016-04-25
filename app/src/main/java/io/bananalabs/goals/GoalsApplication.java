package io.bananalabs.goals;

import android.app.Application;

import za.co.cporm.model.CPOrm;

/**
 * Created by EDC on 4/23/16.
 */
public class GoalsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CPOrm.initialize(this);
    }
}
