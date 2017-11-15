package ru.echodc.singlegroupe.model;


import com.vk.sdk.api.model.Identifiable;

//  Интерфейс для получения полного имени и фото
public interface Owner extends Identifiable {

  String getFullName();

  String getPhoto();
}
