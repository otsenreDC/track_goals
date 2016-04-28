package io.bananalabs.goals;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import io.bananalabs.goals.adapters.TargetAdapter;
import io.bananalabs.goals.models.Target;
import io.bananalabs.goals.utilities.TargetEditActivity;
import io.bananalabs.goals.utilities.Utilities;
import za.co.cporm.model.loader.CPOrmLoader;
import za.co.cporm.model.query.Select;

/**
 * Created by EDC on 4/23/16.
 */
public class TargetListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    TargetAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_target_list, container, false);

        mAdapter = new TargetAdapter(getActivity(), null, 0);

        ListView listView = (ListView) view.findViewById(R.id.list_targets);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(onItemClick);

        view.findViewById(R.id.button_add).setOnClickListener(onAddClick);

        getActivity().getLoaderManager().initLoader(0, Bundle.EMPTY, this);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Select<Target> select = Select.from(Target.class);
        return new CPOrmLoader<>(getActivity(), select);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor c) {
        mAdapter.changeCursor(c);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mAdapter.changeCursor(null);
    }

    private View.OnClickListener onAddClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utilities.Toast(getActivity(), "new target");
            launchCreateTarget();
        }
    };

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = ((SimpleCursorAdapter) adapterView.getAdapter()).getCursor();
            if (cursor.moveToPosition(i)) {
                Long id = cursor.getLong(cursor.getColumnIndex(io.bananalabs.goals.models.Target.TargetContrat.COL_ID));
                Intent intent = new Intent(getActivity(), TargetShowActivity.class);
                intent.putExtra(TargetShowFragment.TARGET_ID, id);
                startActivity(intent);
            }
        }
    };

    private void launchCreateTarget() {
        startActivity(new Intent(getActivity(), TargetEditActivity.class));
    }

}
