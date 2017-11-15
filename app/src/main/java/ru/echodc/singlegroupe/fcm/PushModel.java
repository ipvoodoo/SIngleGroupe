package ru.echodc.singlegroupe.fcm;

import android.support.annotation.DrawableRes;
import ru.echodc.singlegroupe.model.Place;


public class PushModel {

  public String type;

  public String title;
  public String text;
  public String first_name;
  public String last_name;

  @DrawableRes
  public int icon;

  public Place place;


  public PushModel(String type, String title, String text, String first_name, String last_name, int icon, Place place) {
    this.type = type;
    this.title = title;
    this.text = text;
    this.first_name = first_name;
    this.last_name = last_name;
    this.icon = icon;
    this.place = place;
  }

  public PushModel(String type, String title, String text, int icon, Place place) {
    this.type = type;
    this.title = title;
    this.text = text;
    this.icon = icon;
    this.place = place;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getIcon() {
    return icon;
  }

  public void setIcon(int icon) {
    this.icon = icon;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public void setPlace(Place place) {
    this.place = place;
  }

  public Place getPlace() {
    return place;
  }
}