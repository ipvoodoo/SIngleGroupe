package ru.echodc.singlegroupe.di.module;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

//  Данный модуль отвечает за предоставление контекста
@Module
public class ApplicationModule {

  private Application mApplication;

  //  Конструктор для инициализации
  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Singleton
  @Provides
  //  Для получения экземпляра application
  public Context provideContext() {
    return mApplication;
  }

  @Provides
  @Singleton
  LayoutInflater provideLayoutInflater() {
    return (LayoutInflater) mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }


  @Provides
  @Singleton
    //  Подтянем шрифты
  Typeface provideGoogleFontTypeface(Context context) {
    return Typeface.createFromAsset(context.getAssets(), "MaterialIcons-Regular.ttf");
  }
}
