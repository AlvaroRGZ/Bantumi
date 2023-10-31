package es.upm.miw.bantumi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

public class BantumiPrefs  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.preferences, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            findPreference("nombreJugador1").setOnPreferenceChangeListener(
                    (Preference.OnPreferenceChangeListener) (preference, newValue) -> {
                        Log.i(
                                MainActivity.LOG_TAG,
                                "onCreatePreferences(): " + preference + " = " + newValue
                        );
                        return true;
                    }
            );

            findPreference("nombreJugador2").setOnPreferenceChangeListener(
                    (Preference.OnPreferenceChangeListener) (preference, newValue) -> {
                        Log.i(
                                MainActivity.LOG_TAG,
                                "onCreatePreferences(): " + preference + " = " + newValue
                        );
                        return true;
                    }
            );

            findPreference("numeroSemillasIniciales").setOnPreferenceChangeListener(
                    (Preference.OnPreferenceChangeListener) (preference, newValue) -> {
                        Log.i(
                                MainActivity.LOG_TAG,
                                "onCreatePreferences(): " + preference + " = " + newValue
                        );
                        return true;
                    }
            );
        }
    }
}