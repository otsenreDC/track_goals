package io.bananalabs.goals;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.bananalabs.goals.utilities.Utilities;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void goTo(View view) {
        final int viewId = view.getId();
        String url = null;
        if (viewId == R.id.text_icons8) {
            url = view.getContext().getString(R.string.icons_8_url);
        } else if (viewId == R.id.text_roman_nurik) {
            url = view.getContext().getString(R.string.android_asset_studio_url);
        }

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (url != null && browserIntent.resolveActivity(getPackageManager()) != null)
            startActivity(browserIntent);
        else
            Utilities.Toast(getApplicationContext(), R.string.error_no_browser_available);
    }
}
