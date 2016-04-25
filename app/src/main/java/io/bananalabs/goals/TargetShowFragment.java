package io.bananalabs.goals;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.bananalabs.goals.models.Target;
import za.co.cporm.model.query.Select;

/**
 * Created by EDC on 4/24/16.
 */
public class TargetShowFragment extends Fragment{

    public static final String TARGET_ID = "TargetShow:target_id";

    private Target mTarget;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Long id = null;
        Bundle bundle;
        if (getActivity().getIntent().getExtras()!= null) {
            bundle = getActivity().getIntent().getExtras();
            if (bundle.containsKey(TARGET_ID)) {
                id = bundle.getLong(TARGET_ID);
            }
        }

        if (id != null) {
            mTarget = Select.from(Target.class).whereEquals(Target.TargetContrat.COL_ID, id).first();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_target_show, container, false);
        if (mTarget != null) {
            ((TextView) view.findViewById(R.id.text_name)).setText(mTarget.getName());
            ((TextView) view.findViewById(R.id.text_current)).setText(mTarget.getCurrent().toString());
            ((TextView) view.findViewById(R.id.text_goal)).setText(mTarget.getGoal().toString());
            ((TextView) view.findViewById(R.id.text_description)).setText(mTarget.description());
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
