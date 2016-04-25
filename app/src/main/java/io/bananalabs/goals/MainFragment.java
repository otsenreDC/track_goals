package io.bananalabs.goals;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.bananalabs.goals.models.Target;
import io.bananalabs.goals.utilities.Utilities;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    private TextView mNameTextView;
    private TextView mTrackTestView;
    private EditText mAmountEditView;
    private FloatingActionButton mPlusButton;
    private FloatingActionButton mMinusButton;
    private LinearLayout mRemainingLinearLayout;
    private LinearLayout mAccomplishedLinearLayout;
    private TextView mAccomplishedTextView;
    private TextView mDescriptionTextView;
    private ImageButton mDescriptionButton;

    private Target mTarget;
    private MainFragmentListener mListener;

    private boolean isDescriptionVisible = false;

    public interface MainFragmentListener {
        void presentFragment(Fragment fragment, boolean addToBackStack);
    }

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        configureViews(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mTarget = Target.getInstance(getContext());

        updateUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle(getString(R.string.target));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case R.id.action_edit:
                presentEditFragment();
                return true;
            case R.id.action_about:
                startActivity(new Intent(getContext(), AboutActivity.class));
                return true;
            case R.id.action_target_list:
                startActivity(new Intent(getContext(), TargetListActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setMainFragmentListener(@NonNull MainFragmentListener listener) {
        mListener = listener;
    }

    private void presentEditFragment() {
        if (mListener != null)
            mListener.presentFragment(new EditGoalFragment(), true);
    }


    private void updateUI() {
        mNameTextView.setText(mTarget.getName());
        mTrackTestView.setText(String.format("%s / %s", mTarget.getCurrent().toString(), mTarget.getGoal().toString()));
        mAccomplishedTextView.setText(Float.toString(mTarget.getCurrentPercentage()) + "%");
        mDescriptionTextView.setText(mTarget.description());
        updateGraph();
    }

    private void updateGraph() {
        float percentage = mTarget.getCurrentPercentage();
        LinearLayout.LayoutParams accomplishedParams = (LinearLayout.LayoutParams) mAccomplishedLinearLayout.getLayoutParams();
        LinearLayout.LayoutParams remainingParams = (LinearLayout.LayoutParams) mRemainingLinearLayout.getLayoutParams();
        accomplishedParams.weight = 100 - percentage;
        remainingParams.weight = percentage;

        mRemainingLinearLayout.setLayoutParams(remainingParams);
        mAccomplishedLinearLayout.setLayoutParams(accomplishedParams);
    }

    private void configureViews(View view) {
        mNameTextView = (TextView) view.findViewById(R.id.text_name);
        mTrackTestView = (TextView) view.findViewById(R.id.text_track);
        mAmountEditView = (EditText) view.findViewById(R.id.edit_amount);
        mPlusButton = (FloatingActionButton) view.findViewById(R.id.button_plus);
        mMinusButton = (FloatingActionButton) view.findViewById(R.id.button_minus);
        mRemainingLinearLayout = (LinearLayout) view.findViewById(R.id.linear_accomplished);
        mAccomplishedLinearLayout = (LinearLayout) view.findViewById(R.id.linear_remaining);
        mAccomplishedTextView = (TextView) view.findViewById(R.id.text_accomplished);
        mDescriptionButton = (ImageButton) view.findViewById(R.id.button_show);
        mDescriptionTextView = (TextView) view.findViewById(R.id.text_description);

        mPlusButton.setOnClickListener(onRegisterClick);
        mMinusButton.setOnClickListener(onRegisterClick);
        mMinusButton.setOnLongClickListener(onLongClick);
        mDescriptionButton.setOnClickListener(onDescriptionClick);
    }

    private View.OnClickListener onRegisterClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utilities.closeKeyboard(getActivity());
            try {
                boolean updateUI = false;
                Long amount = Long.parseLong(mAmountEditView.getText().toString());
                int checkedID = view.getId();
                switch (checkedID) {
                    case R.id.button_plus:
                        mTarget.addAmountToCurrent(amount);
                        updateUI = true;
                        break;
                    case R.id.button_minus:
                        mTarget.subtractAmountFromCurrent(amount);
                        updateUI = true;
                        break;
                    default:
                        Utilities.Toast(getContext(), R.string.select_action);
                        break;
                }
                if (updateUI)
                    updateUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener onDescriptionClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            isDescriptionVisible = !isDescriptionVisible;
            view.setSelected(isDescriptionVisible);
            mDescriptionTextView.setVisibility(isDescriptionVisible ? View.VISIBLE : View.GONE);
        }
    };

    private View.OnLongClickListener onLongClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            showClearDialog(getContext());
            return true;
        }
    };

    private void showClearDialog(@NonNull Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.clear_progress);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mTarget.clearAmount();
                updateUI();
            }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create().show();
    }

}
