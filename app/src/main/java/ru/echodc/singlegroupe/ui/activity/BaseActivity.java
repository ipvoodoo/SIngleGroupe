package ru.echodc.singlegroupe.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatActivity;
import javax.inject.Inject;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.common.manager.MyFragmentManager;
import ru.echodc.singlegroupe.ui.fragment.BaseFragment;


public abstract class BaseActivity extends MvpAppCompatActivity {
  @BindView(R.id.progress)
  protected ProgressBar mProgressBar;

  @BindView(R.id.main_wrapper)
  FrameLayout mParent;

  @Inject
  MyFragmentManager myFragmentManager;

  @Inject
  LayoutInflater mLayoutInflater;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.fab)
  public FloatingActionButton mFab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);

    ButterKnife.bind(this);//- Инициализация вьюх

    MyApplication.getApplicationComponent().inject(this);

    setSupportActionBar(toolbar);

    mLayoutInflater.inflate(getMainContentLayout(), mParent);

  }

  public ProgressBar getProgressBar() {
    return mProgressBar;
  }

  @LayoutRes//данная аннтоация возвращает ссылку на нужную разметку
  protected abstract int getMainContentLayout();

  //  Метод для изменения заголовка Тулбара
  public void fragmentOnScreen(BaseFragment baseFragment) {
    setToolbarTitle(baseFragment.createToolbarTitle(this));
    setupFabVisibility(baseFragment.needFab());
  }

  //  Метод установки заголовка у Тулбара
  private void setToolbarTitle(String title) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(title);
    }
  }

  public void setupFabVisibility(boolean needFab) {
    if (mFab == null) {
      return;
    }

    if (needFab) {
      mFab.show();
    } else {
      mFab.hide();
    }
  }

  //  Метод для установки корневого фрагмента
  public void setContent(BaseFragment fragment) {
    myFragmentManager.setFragment(this, fragment, R.id.main_wrapper);
  }

  //  Метод для установки дополнительных фрагментов
  public void addContent(BaseFragment fragment) {
    myFragmentManager.addFragment(this, fragment, R.id.main_wrapper);
  }

  //  Метод для удаления текущего фрагмента
  public boolean removeCurrentFragment() {
    return myFragmentManager.removeCurrentFragment(this);
  }

  //  Метод для удаления всех фрагментов
  public boolean removeFragment(BaseFragment fragment) {
    return myFragmentManager.removeFragment(this, fragment);
  }


  @Override
  public void onBackPressed() {
    removeCurrentFragment();
  }
}
