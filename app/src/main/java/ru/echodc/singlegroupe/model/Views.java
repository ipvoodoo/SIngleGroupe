
package ru.echodc.singlegroupe.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

public class Views extends RealmObject {

  @SerializedName("count")
  @Expose
  private Integer count;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

}
