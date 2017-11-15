package ru.echodc.singlegroupe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.ButterKnife;
import com.arellomobile.mvp.presenter.InjectPresenter;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.mvp.presenter.BaseFeedPresenter;
import ru.echodc.singlegroupe.mvp.presenter.InfoLinksPresenter;

public class InfoLinksFragment extends BaseFeedFragment{
  @InjectPresenter
  InfoLinksPresenter mPresenter;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setWithEndlessList(false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MyApplication.getApplicationComponent().inject(this);
    ButterKnife.bind(this, view);
  }

  @Override
  protected BaseFeedPresenter onCreateFeedPresenter() {
    return mPresenter;
  }

  @Override
  public int onCreateToolbarTitle() {
    return R.string.title_links;
  }
}
