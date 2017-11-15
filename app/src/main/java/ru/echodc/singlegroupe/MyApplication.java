package ru.echodc.singlegroupe;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.vk.sdk.VKSdk;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.echodc.singlegroupe.di.component.ApplicationComponent;
import ru.echodc.singlegroupe.di.component.DaggerApplicationComponent;
import ru.echodc.singlegroupe.di.module.ApplicationModule;


public class MyApplication extends Application {
  private static ApplicationComponent sApplicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    initComponent();

    VKSdk.initialize(this);

    //    Инициализация базы данных Realm
    Realm.init(this);
    RealmConfiguration realmConfiguration = new RealmConfiguration
        .Builder()
        .deleteRealmIfMigrationNeeded()
        .build();
    Realm.setDefaultConfiguration(realmConfiguration);

    //    Инициализация DrawerImageLoader, чтобы он мог загружать изображения из сети
    DrawerImageLoader.init(new AbstractDrawerImageLoader() {
      @Override
      public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
        Glide.with(imageView.getContext()).load(uri).into(imageView);
      }
    });

  }

  //  Метод для инициализации компонентов
  private void initComponent() {
    sApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this)).build();
  }

  //  Метод для получения компонента
  public static ApplicationComponent getApplicationComponent() {
    return sApplicationComponent;
  }
}
