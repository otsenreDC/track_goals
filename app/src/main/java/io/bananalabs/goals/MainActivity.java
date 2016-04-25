package io.bananalabs.goals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import io.bananalabs.goals.utilities.Utilities;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener {

//    private MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mMainFragment = new MainFragment();
        mMainFragment.setMainFragmentListener(this);
        presentFragment(mMainFragment, false);
    }

    @Override
    public void presentFragment(Fragment fragment, boolean addToBackStack) {
        Utilities.presentFragment(getSupportFragmentManager(), fragment, R.id.frame_main, addToBackStack);
    }

}
