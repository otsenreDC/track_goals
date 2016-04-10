package io.bananalabs.goals.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.Toast;

import io.bananalabs.goals.R;

/**
 * Created by EDC on 4/1/16.
 */
public class Utilities {

    public static SharedPreferences getSharedPrefences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void Toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static void Toast(Context context, int res) {
        Toast(context, context.getString(res));
    }

    public static void presentFragment(FragmentManager fm, Fragment fragment, int res, boolean addToBackStack) {
        FragmentTransaction t = fm.beginTransaction();
        t.replace(res, fragment);
        if (addToBackStack)
            t.addToBackStack(null);
        t.commit();
    }

    public static boolean assertEditText (EditText editText) {
        String string = editText.getText().toString();
        if (!string.isEmpty()) {
            return true;
        } else {
            editText.setError(editText.getContext().getString(R.string.error));
            return false;
        }
    }
}
