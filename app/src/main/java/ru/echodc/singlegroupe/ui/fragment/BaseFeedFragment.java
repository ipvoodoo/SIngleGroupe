package ru.echodc.singlegroupe.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.common.BaseAdapter;
import ru.echodc.singlegroupe.common.manager.MyLinearLayoutManager;
import ru.echodc.singlegroupe.model.view.BaseViewModel;
import ru.echodc.singlegroupe.mvp.presenter.BaseFeedPresenter;
import ru.echodc.singlegroupe.mvp.view.BaseFeedView;

public abstract class BaseFeedFragment extends BaseFragment implements BaseFeedView {

  @BindView(R.id.rv_list)
  RecyclerView mRecyclerView;

  BaseAdapter mAdapter;

  @BindView(R.id.swipe_refresh)
  protected SwipeRefreshLayout mSwipeRefreshLayout;

  protected ProgressBar mProgressBar;

  protected BaseFeedPresenter mBaseFeedPresenter;

  private boolean isWithEndlessList;


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ButterKnife.bind(this, view);

    setUpRecyclerView(view);
    setUpAdapter(mRecyclerView);
    setUpSwipeToRefreshLayout(view);
    //    за создание презентера отвечает класс-наследник
    mBaseFeedPresenter = onCreateFeedPresenter();
    //    для того чтобы загружать данные в первый раз
    mBaseFeedPresenter.loadStart();

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    isWithEndlessList = true;
  }

  @Override
  protected int getMainContentLayout() {
    return R.layout.fragment_feed;
  }

  private void setUpRecyclerView(View rootView) {
    //    кастомный LayoutManager, который умеет проверять, нужно ли загружать новые элементы
    MyLinearLayoutManager mLinearLayoutManager = new MyLinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(mLinearLayoutManager);
    //    ScrollListener, который «слушает» список и оповещает, когда он скроллится
    if (isWithEndlessList) {
      mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
          if (mLinearLayoutManager.isOnNextPagePosition()) {
            mBaseFeedPresenter.loadNext(mAdapter.getRealItemCount());
          }
        }
      });
    }
    ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
  }

  protected void setUpAdapter(RecyclerView rv) {
    mAdapter = new BaseAdapter();
    rv.setAdapter(mAdapter);
  }

  //  Инициализация свайпа
  private void setUpSwipeToRefreshLayout(View rootView) {
    //  для того чтобы обновлять данные при свайпе вниз
    mSwipeRefreshLayout.setOnRefreshListener(() -> onCreateFeedPresenter().loadRefresh());

    mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
    mProgressBar = getBaseActivity().getProgressBar();
  }

  // region ===================== BaseFeedView =====================
  @Override
  public void showRefreshing() {

  }

  @Override
  public void hideRefreshing() {
    mSwipeRefreshLayout.setRefreshing(false);

  }

  @Override
  public void showListProgress() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideListProgress() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override
  public void showError(String message) {
    Toast.makeText(getBaseActivity(), message, Toast.LENGTH_LONG).show();

  }

  @Override
  public void setItems(List<BaseViewModel> items) {
    mAdapter.setItems(items);

  }

  @Override
  public void addItems(List<BaseViewModel> items) {
    mAdapter.addItems(items);

  }
  // endregion BaseFeedView


  protected abstract BaseFeedPresenter onCreateFeedPresenter();

  //  метод для ее получения от класса-наследника
  public void setWithEndlessList(boolean withEndlessList) {
    isWithEndlessList = withEndlessList;
  }


}












