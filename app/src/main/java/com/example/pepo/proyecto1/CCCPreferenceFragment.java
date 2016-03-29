package com.example.pepo.proyecto1;

/**
 * Created by Pepo on 18/11/2015.
 */
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class CCCPreferenceFragment extends PreferenceFragment{

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
