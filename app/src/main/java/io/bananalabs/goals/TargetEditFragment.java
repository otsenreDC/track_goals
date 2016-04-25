package io.bananalabs.goals;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import io.bananalabs.goals.models.Target;
import io.bananalabs.goals.utilities.Utilities;

/**
 * Created by EDC on 4/24/16.
 */
public class TargetEditFragment extends Fragment {

    private EditText mNameEditText;
    private EditText mTargetEditText;
    private EditText mDescriptionEditText;

    private Target mTarget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_target_edit, container, false);

        mNameEditText = (EditText) view.findViewById(R.id.edit_name);
        mTargetEditText = (EditText) view.findViewById(R.id.edit_goal);
        mDescriptionEditText = (EditText) view.findViewById(R.id.edit_description);

        view.findViewById(R.id.button_save).setOnClickListener(saveClick);

        return view;
    }

    private void save() {
        if (requireInputs()) {
            if (mTarget == null)
                mTarget = new Target();

            mTarget.setName(mNameEditText.getText().toString());
            mTarget.setGoal(Float.parseFloat(mTargetEditText.getText().toString()));
            mTarget.description(mDescriptionEditText.getText().toString());

            mTarget.save();
            getActivity().finish();
        }
    }

    private boolean requireInputs() {
        boolean asserted = Utilities.assertEditText(mNameEditText);
        asserted &= Utilities.assertEditText(mTargetEditText);

        return asserted;
    }

    private View.OnClickListener saveClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            save();
        }
    };
}
