package com.peterh139.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Peter on 05/04/2015.
 */
public class DetailFragment extends Fragment {

    private ShareActionProvider shareActionProvider;
    private String forecastString;

    @InjectView(R.id.textview_detail_text)
    TextView detailText;

    public DetailFragment() {
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail_fragment, menu);

        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(
                menu.findItem(R.id.action_share));
        shareActionProvider.setShareIntent(createShareIntent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.inject(this, rootView);

        Intent startingIntent = getActivity().getIntent();
        if(startingIntent != null && startingIntent.hasExtra(Intent.EXTRA_TEXT)){
            forecastString = startingIntent.getStringExtra(Intent.EXTRA_TEXT);
            detailText.setText(forecastString);
        }

        return rootView;
    }

    private Intent createShareIntent(){
        return new Intent(Intent.ACTION_SEND)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT,forecastString + " #SunshineApp");
    }
}