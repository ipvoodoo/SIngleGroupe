package ru.echodc.singlegroupe.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import ru.echodc.singlegroupe.R;

//  Будет открывать оно настроек при старте
public class MyPreferencesFragment extends PreferenceFragment {

  public MyPreferencesFragment() {

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.preferences);

  }

}
