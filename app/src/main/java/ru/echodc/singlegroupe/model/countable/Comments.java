
package ru.echodc.singlegroupe.model.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

public class Comments extends RealmObject {

  @SerializedName("count")
  @Expose
  private Integer count;
  @SerializedName("can_post")
  @Expose
  private Integer canPost;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getCanPost() {
    return canPost;
  }

  public void setCanPost(Integer canPost) {
    this.canPost = canPost;
  }
}
