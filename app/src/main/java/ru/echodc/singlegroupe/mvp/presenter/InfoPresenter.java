package ru.echodc.singlegroupe.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;
import io.reactivex.Observable;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.consts.ApiConstants;
import ru.echodc.singlegroupe.model.Group;
import ru.echodc.singlegroupe.model.view.BaseViewModel;
import ru.echodc.singlegroupe.model.view.InfoContactsViewModel;
import ru.echodc.singlegroupe.model.view.InfoLinksViewModel;
import ru.echodc.singlegroupe.model.view.InfoStatusViewModel;
import ru.echodc.singlegroupe.mvp.view.BaseFeedView;
import ru.echodc.singlegroupe.rest.api.GroupsApi;
import ru.echodc.singlegroupe.rest.model.request.GroupsGetByIdRequestModel;


@InjectViewState
public class InfoPresenter extends BaseFeedPresenter<BaseFeedView> {

  @Inject
  GroupsApi mGroupApi;

  public InfoPresenter() {
    MyApplication.getApplicationComponent().inject(this);
  }

  // region ===================== BaseFeedPresenter =====================
  @Override
  public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
    return mGroupApi.getById(new GroupsGetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
        .flatMap(listFull -> Observable.fromIterable(listFull.response))
        .doOnNext(this::saveToDb)
        .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
  }

  @Override
  public Observable<BaseViewModel> onCreateRestoreDataObservable() {
    return Observable.fromCallable(getListFromRealmCallable())
        .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
  }
  // endregion BaseFeedPresenter


  private List<BaseViewModel> parsePojoModel(Group group) {
    List<BaseViewModel> items = new ArrayList<>();
    items.add(new InfoStatusViewModel(group));
    items.add(new InfoContactsViewModel());
    items.add(new InfoLinksViewModel());

    return items;
  }

  public Callable<Group> getListFromRealmCallable() {
    return () -> {
      Realm realm = Realm.getDefaultInstance();
      Group result = realm.where(Group.class)
          .equalTo("id", Math.abs(ApiConstants.MY_GROUP_ID))
          .findFirst();
      return realm.copyFromRealm(result);
    };
  }
}



















