package ru.echodc.singlegroupe.model;


import android.os.Bundle;
import android.util.Log;
import io.realm.RealmObject;
import java.util.Map;
import ru.echodc.singlegroupe.common.utils.Utils;

public class Place extends RealmObject {

  public static final String PLACE = "place";
  public static final String OWNER_ID = "owner_id";
  public static final String POST_ID = "post_id";

  private String ownerId;
  private String postId;

  public Place() {

  }

  public Place(String source) {
    String[] place = Utils.splitString(source);
    this.ownerId = place[0];
    this.postId = place[1];
  }


  public Place(String ownerId, String postId) {
    this.ownerId = ownerId;
    this.postId = postId;
  }


  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Place)) {
      return false;
    }
    Place other = (Place) o;
    return other.ownerId.equals(ownerId)
        && other.postId.equals(postId);
  }

  public Place(Bundle source) {
    this.ownerId = source.getString(OWNER_ID);
    this.postId = source.getString(POST_ID);
  }

  public Place(Map<String, String> source) {
    this.ownerId = source.get(OWNER_ID);
    this.postId = source.get(POST_ID);
  }

  public Bundle toBundle() {
    Bundle bundle = new Bundle();
    Log.d("place", "toBundle. from: " + ownerId + " post: " + postId);
    bundle.putString(OWNER_ID, this.ownerId);
    bundle.putString(POST_ID, this.postId);

    return bundle;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public String getPostId() {
    return postId;
  }

  public void setPostId(String postId) {
    this.postId = postId;
  }
}
