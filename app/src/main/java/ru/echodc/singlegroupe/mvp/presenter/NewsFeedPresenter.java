package ru.echodc.singlegroupe.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import ru.echodc.singlegroupe.CurrentUser;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.common.utils.VkListHelper;
import ru.echodc.singlegroupe.consts.ApiConstants;
import ru.echodc.singlegroupe.model.WallItem;
import ru.echodc.singlegroupe.model.view.BaseViewModel;
import ru.echodc.singlegroupe.model.view.NewsItemBodyViewModel;
import ru.echodc.singlegroupe.model.view.NewsItemFooterViewModel;
import ru.echodc.singlegroupe.model.view.NewsItemHeaderViewModel;
import ru.echodc.singlegroupe.mvp.view.BaseFeedView;
import ru.echodc.singlegroupe.rest.api.WallApi;
import ru.echodc.singlegroupe.rest.model.request.WallGetRequestModel;

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {

  private boolean enableIdFiltering = false;

  @Inject
  WallApi mWallApi;

  public NewsFeedPresenter() {
    MyApplication.getApplicationComponent().inject(this);
  }

  // region ===================== BaseFeedPresenter =====================
  //  count — количество элементов
  //  offset —смещение
  @Override
  public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
    return mWallApi.get(new WallGetRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap())
        .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
        .compose(applyFilter())
        //        Сохраняем в базу данных
        .doOnNext(this::saveToDb)
        .flatMap(wallitem -> {
          List<BaseViewModel> baseItems = new ArrayList<>();
          baseItems.add(new NewsItemHeaderViewModel(wallitem));
          baseItems.add(new NewsItemBodyViewModel(wallitem));
          baseItems.add(new NewsItemFooterViewModel(wallitem));
          return Observable.fromIterable(baseItems);
        });
  }

  @Override
  public Observable<BaseViewModel> onCreateRestoreDataObservable() {
    return Observable.fromCallable(getListFromRealmCallable())
        .flatMap(Observable::fromIterable)
        .compose(applyFilter())
        .flatMap(wallItem -> Observable.fromIterable(parsePojoModel(wallItem)));
  }
  // endregion BaseFeedPresenter


  public void setEnableIdFiltering(boolean enableIdFiltering) {
    this.enableIdFiltering = enableIdFiltering;
  }

  protected ObservableTransformer<WallItem, WallItem> applyFilter() {
    if (enableIdFiltering && CurrentUser.getId() != null) {
      return baseItemObservable -> baseItemObservable.filter(
          wallItem -> CurrentUser.getId().equals(String.valueOf(wallItem.getFromId()))
      );
    } else {
      return baseItemObservable -> baseItemObservable;
    }
  }

  public Callable<List<WallItem>> getListFromRealmCallable() {
    return () -> {
      String[] sortFields = {"date"};
      Sort[] sortOrder = {Sort.DESCENDING};
      Realm realm = Realm.getDefaultInstance();
      RealmResults<WallItem> realmResults = realm.where(WallItem.class)
          .findAllSorted(sortFields, sortOrder);
      return realm.copyFromRealm(realmResults);
    };
  }


  private List<BaseViewModel> parsePojoModel(WallItem wallItem) {
    List<BaseViewModel> baseItems = new ArrayList<>();
    baseItems.add(new NewsItemHeaderViewModel(wallItem));
    baseItems.add(new NewsItemBodyViewModel(wallItem));
    baseItems.add(new NewsItemFooterViewModel(wallItem));

    return baseItems;
  }
}





















