package ru.echodc.singlegroupe.mvp.view;

import com.arellomobile.mvp.MvpView;
import ru.echodc.singlegroupe.model.Profile;
import ru.echodc.singlegroupe.ui.fragment.BaseFragment;

public interface MainView extends MvpView {

  void startSignIn();

  void signedIn();

  void showCurrentUser(Profile profile);

  void showFragmentFromDrawer(BaseFragment baseFragment);

  void startActivityFromDrawer(Class<?> act);
}
