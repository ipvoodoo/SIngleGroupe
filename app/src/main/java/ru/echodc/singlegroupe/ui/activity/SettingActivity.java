package ru.echodc.singlegroupe.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.ui.fragment.MyPreferencesFragment;

public class SettingActivity extends BaseActivity {
  @Override
  protected int getMainContentLayout() {
    return R.layout.activity_setting;
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getFragmentManager().beginTransaction()
        .replace(R.id.main_wrapper, new MyPreferencesFragment())
        .commit();

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    toolbar.setTitle("Настройки");
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    toolbar.setTitle("Настройки");

    mFab.hide();

  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    int id = item.getItemId();

    switch (id) {
      case android.R.id.home:
        finish();
        break;
    }

    return super.onOptionsItemSelected(item);
  }


  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
