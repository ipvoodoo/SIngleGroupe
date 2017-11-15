package ru.echodc.singlegroupe.model;


import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Member extends RealmObject {

  public static final String ID = "id";
  public static final String GROUP_ID = "group_id";
  public static final String PHOTO = "photo_100";
  public static final String FIRST_NAME = "first_name";
  public static final String LAST_NAME = "last_name";

  @PrimaryKey
  @SerializedName(ID)
  private int id;

  @SerializedName(GROUP_ID)
  private int group_id;

  @SerializedName(PHOTO)
  private String photo;

  @SerializedName(FIRST_NAME)
  private String firstName;

  @SerializedName(LAST_NAME)
  private String lastName;

  public Member() {
  }

  public Member(Profile profile) {
    this.id = profile.getId();
    this.photo = profile.getPhoto();
    this.firstName = profile.getFirstName();
    this.lastName = profile.getLastName();
  }

  // region ===================== GETTERS & SETTERS =====================

  public int getId() {
    return id;
  }

  public int getGroup_id() {
    return group_id;
  }

  public String getPhoto() {
    return photo;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setGroup_id(int group_id) {
    this.group_id = group_id;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }
  // endregion GETTERS & SETTERS

}




















