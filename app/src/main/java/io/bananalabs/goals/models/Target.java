package io.bananalabs.goals.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import io.bananalabs.goals.utilities.Utilities;

/**
 * Created by Ernesto De los Santos Cordero on 4/1/16.
 * <p/>
 * This class models the behavior of a goal;
 */
public class Target {

    private static final String GOAL_NAME = "Goals.Goal:name";
    private static final String GOAL_CURRENT = "Goals.Goal:current";
    private static final String GOAL_GOAL = "Goals.Goal:goal";
    private static final String GOAL_DESCRIPTION = "Goals.Goal:desciption";

    private String mName;
    private Float mCurrent;
    private Float mGoal;
    private String mDescription;

    private Context mContext;

    private static Target mTarget;

    private Target(Context context) {
        this.mContext = context;
        SharedPreferences sp = Utilities.getSharedPrefences(context);
        this.mCurrent = sp.getFloat(GOAL_CURRENT, (float) 0);
        this.mGoal = sp.getFloat(GOAL_GOAL, (float) 0);
        this.mName = sp.getString(GOAL_NAME, "Your Goal");
    }

    public static Target getInstance(@NonNull Context context) {
        if (mTarget != null)
            return mTarget;

        mTarget = new Target(context);
        return mTarget;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public Float getCurrent() {
        return mCurrent;
    }

    public void setCurrent(Float current) {
        this.mCurrent = current;
        Utilities.getSharedPrefences(mContext).edit().putFloat(GOAL_CURRENT, current).apply();
    }

    public Float getGoal() {
        return mGoal;
    }

    public void setGoal(Float goal) {
        this.mGoal = goal;
        Utilities.getSharedPrefences(mContext).edit().putFloat(GOAL_GOAL, goal).apply();
    }

    public String description() {
        return mDescription;
    }

    public void description(String description) {
        this.mDescription = description;
        Utilities.getSharedPrefences(mContext).edit().putString(GOAL_DESCRIPTION, description).apply();
    }

    public Context getContext() {
        return mContext;
    }

    public float getCurrentPercentage() {
        if (mGoal == 0)
            return 0;
        return mCurrent / mGoal * 100;
    }

    public void addAmountToCurrent(float amount) {
        setCurrent(getCurrent() + amount);
    }

    public void subtractAmountFromCurrent(float amount) {
        setCurrent(getCurrent() - amount);
    }

    public void clearAmount() {
        setCurrent((float)0);
    }
}
