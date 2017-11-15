package ru.echodc.singlegroupe.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import ru.echodc.singlegroupe.ui.activity.BaseActivity;

public abstract class BaseFragment extends MvpAppCompatFragment {

      @LayoutRes//данная аннтоация возвращает ссылку на нужную разметку
      protected abstract int getMainContentLayout();

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(getMainContentLayout(), container, false);
      }

      //  Метод для отображения загловка Тулбара
      public String createToolbarTitle(Context context) {
        return context.getString(onCreateToolbarTitle());
      }

      @StringRes
      //  Метод для запроса заголовка у дочерних фрагментов
      public abstract int onCreateToolbarTitle();

      public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
      }

      public boolean needFab() {
        return false;
  }
}
