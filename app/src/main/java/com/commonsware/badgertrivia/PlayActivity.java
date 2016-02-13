package com.commonsware.badgertrivia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by connerhuff on 2/13/16.
 */
public class PlayActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, PlayFragment.newInstance(null, null))
                .addToBackStack(null)
                .commit();
    }
}
