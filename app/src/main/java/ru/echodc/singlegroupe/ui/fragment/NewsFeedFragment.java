package ru.echodc.singlegroupe.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.arellomobile.mvp.presenter.InjectPresenter;
import javax.inject.Inject;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.mvp.presenter.BaseFeedPresenter;
import ru.echodc.singlegroupe.mvp.presenter.NewsFeedPresenter;
import ru.echodc.singlegroupe.rest.api.WallApi;
import ru.echodc.singlegroupe.ui.activity.CreatePostActivity;

public class NewsFeedFragment extends BaseFeedFragment {

  @Inject
  WallApi mWallApi;

  @InjectPresenter
  NewsFeedPresenter mPresenter;

  public NewsFeedFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    MyApplication.getApplicationComponent().inject(this);
  }

  @Override
  public void onResume() {
    super.onResume();
    getBaseActivity().mFab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = new Intent(getActivity(), CreatePostActivity.class);
        startActivityForResult(intent, 0);
      }
    });
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public int onCreateToolbarTitle() {
    return R.string.screen_name_news;
  }

  @Override
  protected BaseFeedPresenter onCreateFeedPresenter() {
    return mPresenter;
  }

  @Override
  public boolean needFab() {
    return true;
  }
}
