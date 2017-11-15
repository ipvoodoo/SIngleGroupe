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
import ru.echodc.singlegroupe.model.Profile;
import ru.echodc.singlegroupe.model.view.BaseViewModel;
import ru.echodc.singlegroupe.model.view.MemberViewModel;
import ru.echodc.singlegroupe.mvp.view.BaseFeedView;
import ru.echodc.singlegroupe.rest.api.GroupsApi;
import ru.echodc.singlegroupe.rest.api.UsersApi;
import ru.echodc.singlegroupe.rest.model.request.GroupsGetByIdRequestModel;
import ru.echodc.singlegroupe.rest.model.request.UsersGetRequestModel;

@InjectViewState
public class InfoContactsPresenter extends BaseFeedPresenter<BaseFeedView> {
  @Inject
  GroupsApi mGroupApi;

  @Inject
  UsersApi mUserApi;

  public InfoContactsPresenter() {
    MyApplication.getApplicationComponent().inject(this);
  }

  @Override
  public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
    return mGroupApi.getById(new GroupsGetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
        .flatMap(listFull -> Observable.fromIterable(listFull.response))
        .flatMap(group-> Observable.fromIterable(group.getContactList()))
        .flatMap(contact -> mUserApi.get(new UsersGetRequestModel(String.valueOf(contact.getUserId())).toMap()))
        .flatMap(listFull -> Observable.fromIterable(listFull.response))
        .doOnNext(profile -> profile.setContact(true))
        .doOnNext(this::saveToDb)
        .flatMap(profile -> Observable.fromIterable(parsePojoModel(profile)));
  }

  @Override
  public Observable<BaseViewModel> onCreateRestoreDataObservable() {
    return Observable.fromCallable(getListFromRealmCallable())
        .flatMap(profile -> Observable.fromIterable(parsePojoModel(profile)));

  }

  private List<BaseViewModel> parsePojoModel(Profile profile) {
    List<BaseViewModel> items = new ArrayList<>();
    items.add(new MemberViewModel(profile));

    return items;
  }

  private List<BaseViewModel> parsePojoModel(List<Profile> profileList) {
    List<BaseViewModel> items = new ArrayList<>();
    for (Profile profile : profileList) {
      items.addAll(parsePojoModel(profile));
    }

    return items;
  }

  public Callable<List<Profile>> getListFromRealmCallable() {
    return () -> {
      Realm realm = Realm.getDefaultInstance();
      List<Profile> result = realm.where(Profile.class)
          .equalTo("isContact", true)
          .findAll();
      return realm.copyFromRealm(result);
    };
  }
}
