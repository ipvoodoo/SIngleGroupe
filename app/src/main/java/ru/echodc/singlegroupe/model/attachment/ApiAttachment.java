
package ru.echodc.singlegroupe.model.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;
import io.realm.RealmObject;
import java.util.NoSuchElementException;
import ru.echodc.singlegroupe.model.attachment.doc.Doc;
import ru.echodc.singlegroupe.model.attachment.video.Video;

//    класс ApiAttachment служит контейнером для самих аттачментов
public class ApiAttachment extends RealmObject {

  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("photo")
  @Expose
  private Photo photo;

  private Audio audio;
  private Video video;
  private Doc doc;
  private Link link;
  private Page page;

  //    Метод для определения типа прикреплений
  public Attachment getAttachment() {
    switch (type) {
      case VKAttachments.TYPE_PHOTO:
        return photo;
      case VKAttachments.TYPE_AUDIO:
        return audio;
      case VKAttachments.TYPE_VIDEO:
        return video;
      case VKAttachments.TYPE_DOC:
        return doc;
      case VKAttachments.TYPE_LINK:
        return link;
      case VKAttachments.TYPE_WIKI_PAGE:
        return page;
      default:
        throw new NoSuchElementException("Attachment type " + type + " is not supported");
    }
  }

  public void setAudio(Audio audio) {
    this.audio = audio;
  }

  public void setVideo(Video video) {
    this.video = video;
  }

  public void setDoc(Doc doc) {
    this.doc = doc;
  }

  public void setLink(Link link) {
    this.link = link;
  }

  public void setPage(Page page) {
    this.page = page;
  }

  public Audio getAudio() {
    return audio;
  }

  public Video getVideo() {
    return video;
  }

  public Doc getDoc() {
    return doc;
  }

  public Link getLink() {
    return link;
  }

  public Page getPage() {
    return page;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Photo getPhoto() {
    return photo;
  }

  public void setPhoto(Photo photo) {
    this.photo = photo;
  }

}
