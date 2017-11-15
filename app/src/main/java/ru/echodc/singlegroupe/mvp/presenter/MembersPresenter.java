package ru.echodc.singlegroupe.mvp.presenter;


import com.arellomobile.mvp.InjectViewState;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.consts.ApiConstants;
import ru.echodc.singlegroupe.model.Member;
import ru.echodc.singlegroupe.model.view.BaseViewModel;
import ru.echodc.singlegroupe.model.view.MemberViewModel;
import ru.echodc.singlegroupe.mvp.view.BaseFeedView;
import ru.echodc.singlegroupe.rest.api.GroupsApi;
import ru.echodc.singlegroupe.rest.model.request.GroupsGetMembersRequestModel;

@InjectViewState
public class MembersPresenter extends BaseFeedPresenter<BaseFeedView> {

  @Inject
  GroupsApi mGroupsApi;

  public MembersPresenter() {
    MyApplication.getApplicationComponent().inject(this);
  }

  // region ===================== BaseFeedPresenter =====================
  @Override
  public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
    return mGroupsApi.getMembers(new GroupsGetMembersRequestModel(
        ApiConstants.MY_GROUP_ID, count, offset).toMap())
        .flatMap(baseItemResponseFull -> {
          return Observable.fromIterable(baseItemResponseFull.response.getItems());
        })
        .doOnNext(member -> saveToDb(member))
        .map(member -> new MemberViewModel(member));
  }

  @Override
  public Observable<BaseViewModel> onCreateRestoreDataObservable() {
    return Observable.fromCallable(getListFromRealmCallable())
        .flatMap(Observable::fromIterable)
        .map(member -> new MemberViewModel(member));
  }
  // endregion BaseFeedPresenter


  public Callable<List<Member>> getListFromRealmCallable() {
    return () -> {
      String[] sortFields = {Member.ID};
      Sort[] sortOrder = {Sort.ASCENDING};

      Realm realm = Realm.getDefaultInstance();
      RealmResults<Member> results = realm.where(Member.class)
          .findAllSorted(sortFields, sortOrder);
      return realm.copyFromRealm(results);
    };
  }
}


















