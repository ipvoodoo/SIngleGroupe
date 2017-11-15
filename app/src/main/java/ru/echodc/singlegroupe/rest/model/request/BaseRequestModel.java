package ru.echodc.singlegroupe.rest.model.request;


import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;
import java.util.HashMap;
import java.util.Map;
import ru.echodc.singlegroupe.CurrentUser;
import ru.echodc.singlegroupe.consts.ApiConstants;

public abstract class BaseRequestModel {

  //  Чтобы Retrofit понимал название полей, добавляем @SerializedName
  @SerializedName(VKApiConst.VERSION)
  Double version = ApiConstants.DEFAULT_VERSION;

  @SerializedName(VKApiConst.ACCESS_TOKEN)
  String accessToken = CurrentUser.getAccessToken();

  private Double getVersion() {
    return version;
  }

  public String getAccessToken() {
    return accessToken;
  }

  //  Метод для преобразования полей класса в массив значений
  public Map<String, String> toMap() {
    Map<String, String> map = new HashMap<>();
    map.put(VKApiConst.VERSION, String.valueOf(getVersion()));

    //    Если accessToken != null в map его не добавляем, а наследуем из базовой модели
    if (accessToken != null) {
      map.put(VKApiConst.ACCESS_TOKEN, getAccessToken());
    }

    onMapCreate(map);

    return map;
  }

  //  Метод для передачи map в дочерние классы
  public abstract void onMapCreate(Map<String, String> map);
}
