package ru.echodc.singlegroupe.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import javax.inject.Inject;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.common.manager.MyFragmentManager;
import ru.echodc.singlegroupe.model.Place;
import ru.echodc.singlegroupe.ui.fragment.OpenedPostFragment;

public class OpenedPostFromPushActivity extends BaseActivity {
  @Inject
  MyFragmentManager myFragmentManager;
  private Place place;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MyApplication.getApplicationComponent().inject(this);


    Bundle bundle = getIntent().getBundleExtra(Place.PLACE);

    Place place = new Place(bundle);

    myFragmentManager.addFragment(this,
        OpenedPostFragment.newInstance(Integer.valueOf(place.getPostId())),
        R.id.main_wrapper);
  }

  @Override
  protected int getMainContentLayout() {
    return R.layout.activity_opened_post_from_push;
  }

  @Override
  public void onBackPressed() {

    Log.d("BACKSTACK", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));

    if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
      getSupportFragmentManager().popBackStack();
      super.onBackPressed();
    } else {
      startActivity(new Intent(this, MainActivity.class));
      finish();
    }
  }
}
