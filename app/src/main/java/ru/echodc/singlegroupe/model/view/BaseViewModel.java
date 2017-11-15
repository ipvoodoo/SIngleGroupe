package ru.echodc.singlegroupe.model.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.ui.view.holder.BaseViewHolder;


public abstract class BaseViewModel {

  //  Метод для отличия типов Layout
  public abstract LayoutTypes getType();

  //  Перекладываем ответственность за создание ViewHolder на дочерние классы
  public BaseViewHolder createViewHolder(ViewGroup parent) {
    return onCreateViewHolder(LayoutInflater.from(parent.getContext())
        //        Конретный дочерний класс, будет возвращать свой тип
        .inflate(getType().getValue(), parent, false));
  }

  protected abstract BaseViewHolder onCreateViewHolder(View view);

  //  Типы Layout
  public enum LayoutTypes {
    NewsFeedItemHeader(R.layout.item_news_header),
    NewsFeedItemBody(R.layout.item_news_body),
    NewsFeedItemFooter(R.layout.item_news_footer),
    Member(R.layout.item_member),
    Topic(R.layout.item_topic),
    InfoStatus(R.layout.item_info_status),
    InfoContacts(R.layout.item_info_contacts),
    InfoLinks(R.layout.item_info_links),
    AttachmentAudio(R.layout.item_attachment_audio),
    AttachmentDoc(R.layout.item_attachment_doc),
    AttachmentDocImage(R.layout.item_attachment_doc_image),
    AttachmentImage(R.layout.item_attachment_image),
    AttachmentLink(R.layout.item_attachment_link),
    AttachmentLinkExternal(R.layout.item_attachment_link_external),
    AttachmentPage(R.layout.item_attachment_page),
    AttachmentVideo(R.layout.item_attachment_video),

    OpenedPostHeader(R.layout.item_opened_post_header),
    OpenedPostRepostHeader(R.layout.item_opened_post_repost_header),

    CommentHeader(R.layout.item_comment_header),
    CommentBody(R.layout.item_comment_body),
    CommentFooter(R.layout.item_comment_footer),
    CreatePostText(R.layout.item_create_post_text);

    //  Для инициализации Layout
    private final int id;

    LayoutTypes(int resId) {
      this.id = resId;
    }

    @LayoutRes
    public int getValue() {
      return id;
    }
  }

  //  этот метод в тех моделях, которые не будем считать реальными
  public boolean isItemDecorator() {
    return false;
  }
}
