package ru.echodc.singlegroupe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import ru.echodc.singlegroupe.CurrentUser;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.consts.ApiConstants;
import ru.echodc.singlegroupe.model.Profile;
import ru.echodc.singlegroupe.mvp.presenter.MainPresenter;
import ru.echodc.singlegroupe.mvp.view.MainView;
import ru.echodc.singlegroupe.rest.api.AccountApi;
import ru.echodc.singlegroupe.rest.model.request.AccountRegisterDeviceRequest;
import ru.echodc.singlegroupe.ui.fragment.BaseFragment;
import ru.echodc.singlegroupe.ui.fragment.NewsFeedFragment;

public class MainActivity extends BaseActivity implements MainView {

  @InjectPresenter
  MainPresenter mPresenter;

  private Drawer mDrawer;

  private AccountHeader mAccountHeader;

  DividerDrawerItem mDividerDrawerItem;

  @Inject
  AccountApi mAccountApi;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MyApplication.getApplicationComponent().inject(this);

//    String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
//    Log.d("MainActivity", "Fingerprint: " + Arrays.toString(fingerprints));
    //    Проверяем авторизацию
    mPresenter.checkAuth();
  }

  //  Инициализация Дровера
  public void setUpDrawer() {
    PrimaryDrawerItem item1 = new PrimaryDrawerItem()
        .withIdentifier(1)
        .withName(R.string.screen_name_news)
        .withIcon(GoogleMaterial.Icon.gmd_home)
        .withTextColorRes(R.color.vk_white)
        .withIconColorRes(R.color.colorIcon);
    PrimaryDrawerItem item2 = new PrimaryDrawerItem()
        .withIdentifier(2).withName(R.string.screen_name_my_posts)
        .withIcon(GoogleMaterial.Icon.gmd_list)
        .withTextColorRes(R.color.vk_white)
        .withIconColorRes(R.color.colorIcon);
    PrimaryDrawerItem item3 = new PrimaryDrawerItem()
        .withIdentifier(3).withName(R.string.screen_name_settings)
        .withIcon(GoogleMaterial.Icon.gmd_settings)
        .withTextColorRes(R.color.vk_white)
        .withIconColorRes(R.color.colorIcon);
    PrimaryDrawerItem item4 = new PrimaryDrawerItem()
        .withIdentifier(4).withName(R.string.screen_name_members)
        .withIcon(GoogleMaterial.Icon.gmd_people)
        .withTextColorRes(R.color.vk_white)
        .withIconColorRes(R.color.colorIcon);
    PrimaryDrawerItem item5 = new PrimaryDrawerItem()
        .withIdentifier(5).withName(R.string.screen_name_topics)
        .withIcon(GoogleMaterial.Icon.gmd_record_voice_over)
        .withTextColorRes(R.color.vk_white)
        .withIconColorRes(R.color.colorIcon);
    PrimaryDrawerItem item6 = new PrimaryDrawerItem()
        .withIdentifier(6).withName(R.string.screen_name_info)
        .withIcon(GoogleMaterial.Icon.gmd_info)
        .withTextColorRes(R.color.vk_white)
        .withIconColorRes(R.color.colorIcon);
    PrimaryDrawerItem item7 = new PrimaryDrawerItem()
        .withIdentifier(7).withName(R.string.screen_name_rules)
        .withIcon(GoogleMaterial.Icon.gmd_assignment)
        .withTextColorRes(R.color.vk_white)
        .withIconColorRes(R.color.colorIcon);

    mAccountHeader = new AccountHeaderBuilder()
        .withActivity(this)
        //        Фон хэдера
        .withHeaderBackground(R.drawable.nav_menu_header_bg)
        .withTextColorRes(R.color.vk_white)
        .build();
    mDrawer = new DrawerBuilder()
        .withActivity(this)
        .withToolbar(toolbar)
        .withTranslucentStatusBar(true)
        .withActionBarDrawerToggle(true)
        .withAccountHeader(mAccountHeader)
        .withSliderBackgroundColorRes(R.color.colorDrawerBackground)
        .addDrawerItems(item1, item2, item3,
            new SectionDrawerItem().withName("Группа").withTextColorRes(R.color.colorIcon),
            item4, item5, item6, item7)
        .withOnDrawerItemClickListener((view, position, drawerItem) -> {
          mPresenter.drawerItemClick((int) drawerItem.getIdentifier());
          return false;
        })
        .build();
  }

  @Override
  public void startSignIn() {
    VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE);
  }

  @Override
  public void signedIn() {
    Toast.makeText(this, "Current user id: " + CurrentUser.getId(), Toast.LENGTH_LONG).show();
    setContent(new NewsFeedFragment());
    setUpDrawer();

    //    Регистрируем устройство на сервере ВК как получатель push-уведомлений
    mAccountApi.registerDevice(new AccountRegisterDeviceRequest(Settings.Secure.getString(getContentResolver(),
        Settings.Secure.ANDROID_ID)).toMap())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();
  }

  @Override
  public void showCurrentUser(Profile profile) {
    List<IProfile> profileDrawerItems = new ArrayList<>();
    profileDrawerItems.add(new ProfileDrawerItem().withName(profile.getFullName())
        .withEmail(VKAccessToken.currentToken().email)
        .withIcon(profile.getDisplayProfilePhoto()));

    profileDrawerItems.add(new ProfileDrawerItem()
        .withName("Logout")
        .withTextColorRes(R.color.colorDrawerTextLogout)
        .withIcon(R.drawable.ic_exit_to_app_white_24dp)
        .withOnDrawerItemClickListener((view, position, drawerItem) -> {
          mAccountHeader.removeProfile(0);
          mAccountHeader.removeProfile(0);
          VKSdk.logout();
          return false;
        })
    );

    mAccountHeader.setProfiles(profileDrawerItems);

  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
      @Override
      public void onResult(VKAccessToken res) {
        // Пользователь успешно авторизовался
        mPresenter.checkAuth();
      }

      @Override
      public void onError(VKError error) {
        // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
      }
    })) {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override
  protected int getMainContentLayout() {
    return R.layout.activity_main;
  }

  @Override
  public void showFragmentFromDrawer(BaseFragment baseFragment) {
    setContent(baseFragment);
  }

  @Override
  public void startActivityFromDrawer(Class<?> act) {
    startActivity(new Intent(MainActivity.this, act));
  }
}
