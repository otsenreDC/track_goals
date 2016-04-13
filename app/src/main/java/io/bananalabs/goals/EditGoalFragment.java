package io.bananalabs.goals;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import io.bananalabs.goals.models.Target;
import io.bananalabs.goals.utilities.Utilities;

/**
 * Created by EDC on 4/2/16.
 */
public class EditGoalFragment extends Fragment {

    private EditText mNameEditText;
    private EditText mGoalEditText;
    private EditText mDescriptionEditText;

    private Target mTarget;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getActivity() instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle(getString(R.string.settings));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTarget = Target.getInstance(getContext());

        View view = inflater.inflate(R.layout.fragment_edit_goals, container, false);

        mNameEditText = (EditText) view.findViewById(R.id.edit_name);
        mNameEditText.setText(mTarget.getName());
        mGoalEditText = (EditText) view.findViewById(R.id.edit_goal);
        mGoalEditText.setText(mTarget.getGoal().toString());
        mDescriptionEditText = (EditText) view.findViewById(R.id.edit_description);
        mDescriptionEditText.setText(mTarget.description());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
                if (saveConfiguration()) {
                    Utilities.closeKeyboard(getActivity());
                    getActivity().onBackPressed();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private boolean saveConfiguration() {
        boolean assertRequired = Utilities.assertEditText(mNameEditText);
        assertRequired &= Utilities.assertEditText(mGoalEditText);
        if (assertRequired)
            try {
                mTarget.setName(mNameEditText.getText().toString());
                mTarget.setGoal(Float.parseFloat(mGoalEditText.getText().toString()));
                mTarget.description(mDescriptionEditText.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        return assertRequired;
    }

}
