package ru.echodc.singlegroupe.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import ru.echodc.singlegroupe.CurrentUser;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.common.manager.MyFragmentManager;
import ru.echodc.singlegroupe.common.manager.NetworkManager;
import ru.echodc.singlegroupe.model.Profile;
import ru.echodc.singlegroupe.mvp.view.MainView;
import ru.echodc.singlegroupe.rest.api.UsersApi;
import ru.echodc.singlegroupe.rest.model.request.UsersGetRequestModel;
import ru.echodc.singlegroupe.ui.activity.SettingActivity;
import ru.echodc.singlegroupe.ui.fragment.BaseFragment;
import ru.echodc.singlegroupe.ui.fragment.BoardFragment;
import ru.echodc.singlegroupe.ui.fragment.GroupRulesFragment;
import ru.echodc.singlegroupe.ui.fragment.InfoFragment;
import ru.echodc.singlegroupe.ui.fragment.MembersFragment;
import ru.echodc.singlegroupe.ui.fragment.MyPostsFragment;
import ru.echodc.singlegroupe.ui.fragment.NewsFeedFragment;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

  @Inject
  MyFragmentManager myFragmentManager;

  @Inject
  UsersApi mUserApi;

  @Inject
  NetworkManager mNetworkManager;

  //  Проверка на авторизованность пользователя
  public void checkAuth() {
    if (!CurrentUser.isAuthorized()) {
      getViewState().startSignIn();
    } else {
      getCurrentUser();
      getViewState().signedIn();
    }
  }

  //  Получаем компонент через конструктор презентера
  public MainPresenter() {
    MyApplication.getApplicationComponent().inject(this);
  }


  //  методы, для получения, сохранения и восстановления данных пользователя
  //  обращается к методу UsersApi.get -> трансформирует оператором flatMap()
  //  обсервабл Observable<Full<List<Profile>>>
  //  в Observable<Profile> -> в операторе doOnNext() сохраняет Profile в бд с помощью метода saveToDb(…)
  public Observable<Profile> getProfileFromNetwork() {
    return mUserApi.get(new UsersGetRequestModel(CurrentUser.getId()).toMap())
        .flatMap(listFull -> Observable.fromIterable(listFull.response))
        .doOnNext(this::saveToDb);
  }

  //  трансформирует возвращаемый методом getListFromRealmCallable() Callable
  // с восстановленными данными в Observable<Profile>
  private Observable<Profile> getProfileFromDb() {
    return Observable.fromCallable(getListFromRealmCallable());
  }

  public void saveToDb(RealmObject item) {
    Realm realm = Realm.getDefaultInstance();
    realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
  }

  public Callable<Profile> getListFromRealmCallable() {
    return () -> {
      Realm realm = Realm.getDefaultInstance();
      Profile realmResults = realm.where(Profile.class)
          .equalTo("id", Integer.parseInt(CurrentUser.getId()))
          .findFirst();
      return realm.copyFromRealm(realmResults);
    };
  }

  //  метод, который будем вызывать из активити для заполнения хедера данными пользователя
  private void getCurrentUser() {
    mNetworkManager.getNetworkObservable()
        .flatMap(aBoolean -> {
          if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
          }
          return aBoolean
              ? getProfileFromNetwork()
              : getProfileFromDb();
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(profile -> {
          getViewState().showCurrentUser(profile);
        }, error -> {
          error.printStackTrace();
        });
  }

  // region ===================== Events =====================
  public void drawerItemClick(int id) {
    BaseFragment fragment = null;

    switch (id) {
      case 1:
        fragment = new NewsFeedFragment();
        break;
      case 2:
        fragment = new MyPostsFragment();
        break;
      case 3:
        getViewState().startActivityFromDrawer(SettingActivity.class);
        return;
      case 4:
        fragment = new MembersFragment();
        break;
      case 5:
        fragment = new BoardFragment();
        break;
      case 6:
        fragment = new InfoFragment();
        break;
      case 7:
        fragment = new GroupRulesFragment();
        break;
    }

    if (fragment != null && !myFragmentManager.isAlreadyContains(fragment)) {
      getViewState().showFragmentFromDrawer(fragment);
    }
  }

  // endregion Events

}
























