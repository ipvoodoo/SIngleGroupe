
package ru.echodc.singlegroupe.model.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;
import io.realm.RealmObject;

public class Photo extends RealmObject implements Attachment {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("album_id")
  @Expose
  private Integer albumId;
  @SerializedName("owner_id")
  @Expose
  private Integer ownerId;
  @SerializedName("user_id")
  @Expose
  private Integer userId;
  @SerializedName("photo_75")
  @Expose
  private String photo75;
  @SerializedName("photo_130")
  @Expose
  private String photo130;
  @SerializedName("photo_604")
  @Expose
  private String photo604;
  @SerializedName("photo_807")
  @Expose
  private String photo807;
  @SerializedName("photo_1280")
  @Expose
  private String photo1280;
  @SerializedName("width")
  @Expose
  private Integer width;
  @SerializedName("height")
  @Expose
  private Integer height;
  @SerializedName("text")
  @Expose
  private String text;
  @SerializedName("date")
  @Expose
  private Integer date;
  @SerializedName("access_key")
  @Expose
  private String accessKey;

  public int getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAlbumId() {
    return albumId;
  }

  public void setAlbumId(Integer albumId) {
    this.albumId = albumId;
  }

  public Integer getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Integer ownerId) {
    this.ownerId = ownerId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getPhoto75() {
    return photo75;
  }

  public void setPhoto75(String photo75) {
    this.photo75 = photo75;
  }

  public String getPhoto130() {
    return photo130;
  }

  public void setPhoto130(String photo130) {
    this.photo130 = photo130;
  }

  public String getPhoto604() {
    return photo604;
  }

  public void setPhoto604(String photo604) {
    this.photo604 = photo604;
  }

  public String getPhoto807() {
    return photo807;
  }

  public void setPhoto807(String photo807) {
    this.photo807 = photo807;
  }

  public String getPhoto1280() {
    return photo1280;
  }

  public void setPhoto1280(String photo1280) {
    this.photo1280 = photo1280;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Integer getDate() {
    return date;
  }

  public void setDate(Integer date) {
    this.date = date;
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  @Override
  public String getType() {
    return VKAttachments.TYPE_PHOTO;
  }
}
