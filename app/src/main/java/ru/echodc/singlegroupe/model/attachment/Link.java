package ru.echodc.singlegroupe.model.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;
import io.realm.RealmObject;


public class Link extends RealmObject implements Attachment {

  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("title")
  @Expose
  private String title;

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("caption")
  @Expose
  private String caption;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("photo")
  @Expose
  private Photo photo;
  @SerializedName("is_external")
  @Expose
  private int isExternal;


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Photo getPhoto() {
    return photo;
  }

  public void setPhoto(Photo photo) {
    this.photo = photo;
  }

  public int getIsExternal() {
    return isExternal;
  }

  public void setIsExternal(int isExternal) {
    this.isExternal = isExternal;
  }

  @Override
  public int getId() {
    return 0;
  }

  @Override
  public String getType() {
    return VKAttachments.TYPE_LINK;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
