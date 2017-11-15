package ru.echodc.singlegroupe.rest.model.response;


import java.util.ArrayList;
import java.util.List;
import ru.echodc.singlegroupe.model.Group;
import ru.echodc.singlegroupe.model.Owner;
import ru.echodc.singlegroupe.model.Profile;

//  Для парсинга полей профиля и группы
public class ItemWithSendersResponse<T> extends BaseItemResponse<T> {

  private List<Profile> profiles = new ArrayList<>();
  private List<Group> groups = new ArrayList<>();

  private List<Profile> getProfiles() {
    return profiles;
  }

  private List<Group> getGroups() {
    return groups;
  }

  //  Для получения списка отправителей
  private List<Owner> getAllSenders() {
    List<Owner> all = new ArrayList<>();
    all.addAll(getProfiles());
    all.addAll(getGroups());
    return all;
  }

  //  Для получения конкретного отправителя, через перебор всех отправителей
  //  и получения его Id
  public Owner getSender(int id) {
    for (Owner owner : getAllSenders()) {
      if (owner.getId() == Math.abs(id)) {
        return owner;
      }
    }
    return null;
  }
}
