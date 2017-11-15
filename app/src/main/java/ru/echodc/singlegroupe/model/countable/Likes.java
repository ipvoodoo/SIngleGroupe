
package ru.echodc.singlegroupe.model.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

public class Likes extends RealmObject {

  @SerializedName("count")
  @Expose
  private Integer count;
  @SerializedName("user_likes")
  @Expose
  private Integer userLikes;
  @SerializedName("can_like")
  @Expose
  private Integer canLike;
  @SerializedName("can_publish")
  @Expose
  private Integer canPublish;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getUserLikes() {
    return userLikes;
  }

  public void setUserLikes(Integer userLikes) {
    this.userLikes = userLikes;
  }

  public Integer getCanLike() {
    return canLike;
  }

  public void setCanLike(Integer canLike) {
    this.canLike = canLike;
  }

  public Integer getCanPublish() {
    return canPublish;
  }

  public void setCanPublish(Integer canPublish) {
    this.canPublish = canPublish;
  }

  public boolean isUserLikes() {
    return userLikes == 1;
  }

}
