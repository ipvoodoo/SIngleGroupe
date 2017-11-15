package ru.echodc.singlegroupe.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import ru.echodc.singlegroupe.common.manager.MyFragmentManager;
import ru.echodc.singlegroupe.common.manager.NetworkManager;

//  Для предоставления менеджеров
@Module
public class ManagerModule {

  @Provides
  @Singleton
  MyFragmentManager provideMyFragmentManager() {
    return new MyFragmentManager();
  }

  @Provides
  @Singleton
  NetworkManager provideNetworkManager() {
    return new NetworkManager();
  }
}
