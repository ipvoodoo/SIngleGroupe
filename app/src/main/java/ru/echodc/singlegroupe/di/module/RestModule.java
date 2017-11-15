package ru.echodc.singlegroupe.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import ru.echodc.singlegroupe.rest.RestClient;
import ru.echodc.singlegroupe.rest.api.AccountApi;
import ru.echodc.singlegroupe.rest.api.BoardApi;
import ru.echodc.singlegroupe.rest.api.GroupsApi;
import ru.echodc.singlegroupe.rest.api.UsersApi;
import ru.echodc.singlegroupe.rest.api.VideoApi;
import ru.echodc.singlegroupe.rest.api.WallApi;

@Module
public class RestModule {

  private RestClient mRestClient;

  //  Иниализация модуля
  public RestModule() {
    mRestClient = new RestClient();
  }

  //  Метод для получения RestClient
  @Provides
  @Singleton
  public RestClient provideRestClient() {
    return mRestClient;
  }

  @Provides
  @Singleton
  public WallApi provideWallApi() {
    return mRestClient.createService(WallApi.class);
  }

  @Provides
  @Singleton
  public UsersApi provideUsersApi() {
    return mRestClient.createService(UsersApi.class);
  }

  @Provides
  @Singleton
  public GroupsApi provideGroupsApi() {
    return mRestClient.createService(GroupsApi.class);
  }

  @Provides
  @Singleton
  public BoardApi provideBoardApi() {
    return mRestClient.createService(BoardApi.class);
  }

  @Provides
  @Singleton
  public VideoApi provideVideoApi() {
    return mRestClient.createService(VideoApi.class);
  }

  @Provides
  @Singleton
  public AccountApi provideAccountApi() {
    return mRestClient.createService(AccountApi.class);
  }
}