package ru.echodc.singlegroupe.model.view;

import android.view.View;
import ru.echodc.singlegroupe.model.WallItem;
import ru.echodc.singlegroupe.ui.view.holder.NewsItemBodyHolder;


public class NewsItemBodyViewModel extends BaseViewModel {

  private int mId;

  private String mText;

  private String mAttachmentString;

  private boolean mIsRepost;

  public NewsItemBodyViewModel(WallItem wallItem) {
    this.mId = wallItem.getId();
    this.mIsRepost = wallItem.haveSharedRepost();

    if (mIsRepost) {
      this.mText = wallItem.getSharedRepost().getText();
      this.mAttachmentString = wallItem.getSharedRepost().getAttachmentsString();
    } else {
      this.mText = wallItem.getText();
      this.mAttachmentString = wallItem.getAttachmentsString();
    }
  }

  // region ===================== BaseViewModel =====================
  @Override
  public LayoutTypes getType() {
    return LayoutTypes.NewsFeedItemBody;
  }

  @Override
  public NewsItemBodyHolder onCreateViewHolder(View view) {
    return new NewsItemBodyHolder(view);
  }

  // endregion BaseViewModel

  // region ===================== GETTERS =====================
  public String getText() {
    return mText;
  }

  public int getId() {
    return mId;
  }

  public String getAttachmentString() {
    return mAttachmentString;
  }

  // endregion GETTERS


  @Override
  public boolean isItemDecorator() {
    return true;
  }
}












