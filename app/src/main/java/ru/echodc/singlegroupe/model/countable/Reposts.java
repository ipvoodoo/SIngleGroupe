
package ru.echodc.singlegroupe.model.countable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

public class Reposts extends RealmObject {

  @SerializedName("count")
  @Expose
  private Integer count;
  @SerializedName("user_reposted")
  @Expose
  private Integer userReposted;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getUserReposted() {
    return userReposted;
  }

  public void setUserReposted(Integer userReposted) {
    this.userReposted = userReposted;
  }

}
