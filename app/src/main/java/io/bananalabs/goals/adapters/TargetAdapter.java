package io.bananalabs.goals.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import io.bananalabs.goals.R;
import io.bananalabs.goals.models.Target;

/**
 * Created by EDC on 4/24/16.
 */
public class TargetAdapter extends CursorAdapter {

    public TargetAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_target, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String name = cursor.getString(cursor.getColumnIndex(Target.TargetContrat.COL_NAME));
        Float goal = cursor.getFloat(cursor.getColumnIndex(Target.TargetContrat.COL_GOAL));
        Float current = cursor.getFloat(cursor.getColumnIndex(Target.TargetContrat.COL_CURRENT));
        String description = cursor.getString(cursor.getColumnIndex(Target.TargetContrat.COL_DESCRIPTION));

        viewHolder.nameTextView.setText(name);
        viewHolder.goalTextView.setText("" + goal);
        viewHolder.currentTextView.setText("" + current);
        viewHolder.descriptionTextView.setText(description);
    }

    public static class ViewHolder {
        public final TextView nameTextView;
        public final TextView goalTextView;
        public final TextView currentTextView;
        public final TextView descriptionTextView;

        public ViewHolder(View view) {
            this.nameTextView = (TextView) view.findViewById(R.id.text_name);
            this.goalTextView = (TextView) view.findViewById(R.id.text_goal);
            this.currentTextView = (TextView) view.findViewById(R.id.text_current);
            this.descriptionTextView = (TextView) view.findViewById(R.id.text_description);
        }
    }
}
