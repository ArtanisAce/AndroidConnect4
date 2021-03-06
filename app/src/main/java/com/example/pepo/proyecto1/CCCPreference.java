package com.example.pepo.proyecto1;

/**
 * Created by Pepo on 18/11/2015.
 */
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class CCCPreference extends Activity {
    public final static String PLAY_MUSIC_KEY = "music";
    public final static boolean PLAY_MUSIC_DEFAULT = true;
    public final static String PLAYER_KEY = "playerName";
    public final static String PLAYER_DEFAULT = "unspecified";
    public final static String COLOR_KEY = "color_fichas";
    public final static String  COLOR_DEFAULT = "default";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_void);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        CCCPreferenceFragment fragment = new CCCPreferenceFragment();
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }
}
