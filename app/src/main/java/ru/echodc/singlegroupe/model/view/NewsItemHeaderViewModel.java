package ru.echodc.singlegroupe.model.view;


import android.view.View;
import ru.echodc.singlegroupe.model.WallItem;
import ru.echodc.singlegroupe.ui.view.holder.BaseViewHolder;
import ru.echodc.singlegroupe.ui.view.holder.NewsItemHeaderHolder;

public class NewsItemHeaderViewModel extends BaseViewModel {

  //  Поля для идентификатора, профиля и фото отправителя
  private int mId;

  private String mProfilePhoto;
  private String mProfileName;
  // Является ли запись репостом
  private boolean mIsRepost;
  //  Поле для автора репоста
  private String mRepostProfileName;

  public NewsItemHeaderViewModel(WallItem wallItem) {
    this.mId = wallItem.getId();

    this.mProfilePhoto = wallItem.getSenderPhoto();
    this.mProfileName = wallItem.getSenderName();

    this.mIsRepost = wallItem.haveSharedRepost();

    if (mIsRepost) {
      this.mRepostProfileName = wallItem.getSharedRepost().getSenderName();
    }

  }

  // region ===================== BaseViewModel =====================
  @Override
  public LayoutTypes getType() {
    return LayoutTypes.NewsFeedItemHeader;
  }

  @Override
  protected BaseViewHolder onCreateViewHolder(View view) {
    return new NewsItemHeaderHolder(view);
  }
  // endregion BaseViewModel

  // region ===================== GETTERS =====================
  public int getId() {
    return mId;
  }

  public String getProfilePhoto() {
    return mProfilePhoto;
  }

  public String getProfileName() {
    return mProfileName;
  }

  public boolean isRepost() {
    return mIsRepost;
  }

  public String getRepostProfileName() {
    return mRepostProfileName;
  }

  // endregion GETTERS

}
