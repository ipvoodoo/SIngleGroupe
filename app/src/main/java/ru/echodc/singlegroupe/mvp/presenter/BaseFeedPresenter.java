package ru.echodc.singlegroupe.mvp.presenter;


import com.arellomobile.mvp.MvpPresenter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;
import java.util.List;
import javax.inject.Inject;
import ru.echodc.singlegroupe.common.manager.NetworkManager;
import ru.echodc.singlegroupe.model.view.BaseViewModel;
import ru.echodc.singlegroupe.mvp.view.BaseFeedView;

public abstract class BaseFeedPresenter<V extends BaseFeedView> extends MvpPresenter<V> {

  public static final int START_PAGE_SIZE = 15;
  public static final int NEXT_PAGE_SIZE = 15;

  private boolean mIsInLoading;

  @Inject
  NetworkManager mNetworkManager;

  //  Метод для инициирования загрузки данных
  public void loadData(ProgressType progressType, int offset, int count) {
    if (mIsInLoading) {
      return;
    }
    mIsInLoading = true;
    /**обсервабл создает класс NetworkManager.
     *  Этот Observable возвращает булевую переменную: true — устройство имеет доступ к vk api,
     *  false — нет. В методе loadData(…),
     *  с помощью оператора flatMap() трансформируем Observable<Boolean> в Observable<BaseViewModel>,
     *  который в зависимости от доступа к апи излучает либо данные из сети, либо данные из бд
     */

    mNetworkManager.getNetworkObservable()
        .flatMap(aBoolean -> {
          if (!aBoolean && offset > 0) {
            return Observable.empty();
          }
          return aBoolean//    абстрактный метод для создания Observable, который излучает данные, взятые из сети.

              ? onCreateLoadDataObservable(count, offset)
              : onCreateRestoreDataObservable();
        })

        .toList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(disposable -> {
          onLoadStart(progressType);
        })
        .doFinally(() -> {
          onLoadingFinish(progressType);
        })
        .subscribe(repositories -> {
          onLoadingSuccess(progressType, repositories);
        }, error -> {
          error.printStackTrace();
          onLoadingFailed(error);
        });
  }

  //  Метод для получения данных из сети
  public abstract Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset);

  //  Типы прогресс баров
  public enum ProgressType {
    Refreshing, ListProgress, Paging
  }

  //  Показываем различные прогресс бары
  public void showProgress(ProgressType progressType) {
    switch (progressType) {
      case Refreshing:
        getViewState().showRefreshing();
        break;
      case ListProgress:
        getViewState().showListProgress();
        break;
    }
  }

  //  Скрываем прогресс бары
  public void hideProgress(ProgressType progressType) {
    switch (progressType) {
      case Refreshing:
        getViewState().hideRefreshing();
        break;
      case ListProgress:
        getViewState().hideListProgress();
        break;
    }
  }

  // region ===================== Loaders =====================
  //  В зависимости от типа загрузки, выбирается свой лоадер
  //  Вызывается при первой загрузке
  public void loadStart() {
    loadData(ProgressType.ListProgress, 0, START_PAGE_SIZE);
  }

  //  Вызывается при загрузке новых эдементов при прокрутке
  public void loadNext(int offset) {
    loadData(ProgressType.Paging, offset, NEXT_PAGE_SIZE);
  }

  //  Вызывется при обновлении списка
  public void loadRefresh() {
    loadData(ProgressType.Refreshing, 0, START_PAGE_SIZE);
  }

  public void onLoadStart(ProgressType progressType) {
    showProgress(progressType);
  }

  public void onLoadingFinish(ProgressType progressType) {
    mIsInLoading = false;
    hideProgress(progressType);
  }

  public void onLoadingFailed(Throwable throwable) {
    getViewState().showError(throwable.getMessage());
    throwable.printStackTrace();
  }

  public void onLoadingSuccess(ProgressType progressType, List<BaseViewModel> items) {
    if (progressType == ProgressType.Paging) {
      getViewState().addItems(items);
    } else {
      getViewState().setItems(items);
    }
  }

  // endregion Loaders

  // region ===================== DataBase =====================
  public void saveToDb(RealmObject item) {
    Realm realm = Realm.getDefaultInstance();
    realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
  }

  public abstract Observable<BaseViewModel> onCreateRestoreDataObservable();

  // endregion DataBase

}



















