package ru.echodc.singlegroupe.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.Identifiable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Topic extends RealmObject implements Identifiable {

  @PrimaryKey
  @SerializedName("id")
  @Expose
  public int id;

  @SerializedName("title")
  @Expose
  public String title;

  @SerializedName("comments")
  @Expose
  public int comments;

  public int groupid;

  @Override
  public int getId() {
    return id;
  }

  // region ===================== GETTERS & SETTERS =====================
  public String getTitle() {
    return title;
  }

  public int getComments() {
    return comments;
  }

  public int getGroupid() {
    return groupid;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setComments(int comments) {
    this.comments = comments;
  }

  public void setGroupid(int groupid) {
    this.groupid = groupid;
  }

  // endregion GETTERS & SETTERS

}
