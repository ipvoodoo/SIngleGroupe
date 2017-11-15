package ru.echodc.singlegroupe.model.attachment.doc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

public class Size extends RealmObject {

  @SerializedName("src")
  @Expose
  public String src;
  @SerializedName("width")
  @Expose
  public int width;
  @SerializedName("height")
  @Expose
  public int height;
  @SerializedName("type")
  @Expose
  public String type;


  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
