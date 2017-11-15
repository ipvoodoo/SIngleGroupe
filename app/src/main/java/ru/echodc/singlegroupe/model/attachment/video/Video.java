package ru.echodc.singlegroupe.model.attachment.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.echodc.singlegroupe.model.attachment.Attachment;

public class Video extends RealmObject implements Attachment {

  @PrimaryKey
  @SerializedName("id")
  @Expose
  private int id;
  @SerializedName("owner_id")
  @Expose
  private int ownerId;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("duration")
  @Expose
  private int duration;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("date")
  @Expose
  private int date;
  @SerializedName("views")
  @Expose
  private int views;
  @SerializedName("comments")
  @Expose
  private int comments;
  @SerializedName("photo_130")
  @Expose
  private String photo130;
  @SerializedName("photo_320")
  @Expose
  private String photo320;
  @SerializedName("photo_800")
  @Expose
  private String photo800;
  @SerializedName("player")
  @Expose
  private String player;
  @SerializedName("access_key")
  @Expose
  private String accessKey;
  @SerializedName("can_add")
  @Expose
  private int canAdd;
  @SerializedName("files")
  @Expose
  public File files;


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

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getDate() {
    return date;
  }

  public void setDate(int date) {
    this.date = date;
  }

  public int getViews() {
    return views;
  }

  public void setViews(int views) {
    this.views = views;
  }

  public int getComments() {
    return comments;
  }

  public void setComments(int comments) {
    this.comments = comments;
  }

  public String getPhoto130() {
    return photo130;
  }

  public void setPhoto130(String photo130) {
    this.photo130 = photo130;
  }

  public String getPhoto320() {
    return photo320;
  }

  public void setPhoto320(String photo320) {
    this.photo320 = photo320;
  }

  public String getPhoto800() {
    return photo800;
  }

  public void setPhoto800(String photo800) {
    this.photo800 = photo800;
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public int getCanAdd() {
    return canAdd;
  }

  public void setCanAdd(int canAdd) {
    this.canAdd = canAdd;
  }

  public String getPlayer() {
    return player;
  }

  public void setPlayer(String player) {
    this.player = player;
  }

  @Override
  public String getType() {
    return VKAttachments.TYPE_VIDEO;
  }

  public File getFiles() {
    return files;
  }
}
