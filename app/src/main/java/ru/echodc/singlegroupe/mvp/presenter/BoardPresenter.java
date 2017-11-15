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
import ru.echodc.singlegroupe.model.Topic;
import ru.echodc.singlegroupe.model.view.BaseViewModel;
import ru.echodc.singlegroupe.model.view.TopicViewModel;
import ru.echodc.singlegroupe.mvp.view.BaseFeedView;
import ru.echodc.singlegroupe.rest.api.BoardApi;
import ru.echodc.singlegroupe.rest.model.request.BoardGetTopicsRequestModel;

@InjectViewState
public class BoardPresenter extends BaseFeedPresenter<BaseFeedView> {

  @Inject
  BoardApi mBoardApi;

  public BoardPresenter() {
    MyApplication.getApplicationComponent().inject(this);
  }


  // region ===================== BaseFeedPresenter =====================
  @Override
  public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
    return mBoardApi.getTopics(new BoardGetTopicsRequestModel(
        ApiConstants.MY_GROUP_ID, count, offset
    ).toMap())
        .flatMap(baseItemResponseFull ->
            Observable.fromIterable(baseItemResponseFull.response.getItems()))
        .doOnNext(topic -> topic.setGroupid(ApiConstants.MY_GROUP_ID))
        .doOnNext(this::saveToDb)
        .map(TopicViewModel::new);
  }

  @Override
  public Observable<BaseViewModel> onCreateRestoreDataObservable() {
    return Observable.fromCallable(getListFromrealmCallable())
        .flatMap(Observable::fromIterable)
        .map(TopicViewModel::new);
  }

  // endregion BaseFeedPresenter

  public Callable<List<Topic>> getListFromrealmCallable() {
    return () -> {
      String[] sortFields = {Member.ID};
      Sort[] sortOrder = {Sort.DESCENDING};

      Realm realm = Realm.getDefaultInstance();
      RealmResults<Topic> results = realm.where(Topic.class)
          .equalTo("groupId", ApiConstants.MY_GROUP_ID)
          .findAllSorted(sortFields, sortOrder);

      return realm.copyFromRealm(results);
    };
  }
}

















