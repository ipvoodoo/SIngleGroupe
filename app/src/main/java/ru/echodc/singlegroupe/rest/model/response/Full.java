package ru.echodc.singlegroupe.rest.model.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//  Отвечает за парсинг ответов с сервера
public class Full<T> {

  @SerializedName("response")
  @Expose
  public T response;

}
