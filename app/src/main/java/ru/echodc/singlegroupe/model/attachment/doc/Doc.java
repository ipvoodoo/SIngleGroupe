package ru.echodc.singlegroupe.model.attachment.doc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;
import io.realm.RealmObject;
import ru.echodc.singlegroupe.model.attachment.Attachment;

public class Doc extends RealmObject implements Attachment {

  @SerializedName("id")
  @Expose
  private int id;
  @SerializedName("owner_id")
  @Expose
  private int ownerId;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("size")
  @Expose
  private int size;
  @SerializedName("ext")
  @Expose
  private String ext;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("date")
  @Expose
  private int date;
  @SerializedName("type")
  @Expose
  private int mtype;
  @SerializedName("access_key")
  @Expose
  private String accessKey;
  @SerializedName("preview")
  @Expose
  public Preview preview;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getExt() {
    return ext;
  }

  public void setExt(String ext) {
    this.ext = ext;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getDate() {
    return date;
  }

  public void setDate(int date) {
    this.date = date;
  }

  public int getMType() {
    return mtype;
  }

  public void setMtype(int mtype) {
    this.mtype = mtype;
  }

  @Override
  public String getType() {
    return VKAttachments.TYPE_DOC;
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public Preview getPreview() {
    return preview;
  }
}
