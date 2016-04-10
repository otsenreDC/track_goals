package io.bananalabs.goals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import io.bananalabs.goals.utilities.Utilities;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener{

    private MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFragment = new MainFragment();
        mMainFragment.setMainFragmentListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, mMainFragment).commit();
    }

    @Override
    public void presentFragment(Fragment fragment) {
        Utilities.presentFragment(getSupportFragmentManager(), fragment, R.id.frame_main, true);
    }
}
