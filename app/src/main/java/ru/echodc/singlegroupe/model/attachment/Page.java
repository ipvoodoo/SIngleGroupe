package ru.echodc.singlegroupe.model.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;


public class Page extends RealmObject implements Attachment {

  @SerializedName("id")
  @Expose
  private int id;

  @SerializedName("title")
  @Expose
  private String title;

  @SerializedName("view_url")
  @Expose
  private String url;

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String getType() {
    return "page";
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
