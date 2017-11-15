package ru.echodc.singlegroupe.common.manager;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import java.util.Stack;
import ru.echodc.singlegroupe.ui.activity.BaseActivity;
import ru.echodc.singlegroupe.ui.fragment.BaseFragment;

public class MyFragmentManager {
  //  На экране должен быть всегда один фрагмент
  //  данная константа нужна, чтобы случайно не улить последний фрагмент на экране
  private static final int EMPTY_FRAGMENT_STACK_SIZE = 1;
  //  Стек
  private Stack<BaseFragment> mFragmentStack = new Stack<>();
  //  Текущий фрамент
  private BaseFragment mCurrentFragment;

  //  Метод для установки корневого фрагмента
  public void setFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
    if (activity != null && !activity.isFinishing() && !isAlreadyContains(fragment)) {
      FragmentTransaction fragmentTransaction = createAddTransaction(activity, fragment, false);
      fragmentTransaction.replace(containerId, fragment);
      commitAddTransaction(activity, fragment, fragmentTransaction, false);
    }
  }

  //  Метод для добавления фрагментов поверх корневого, для открытия окон навигации
  public void addFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
    if (activity != null && !activity.isFinishing() && !isAlreadyContains(fragment)) {
      FragmentTransaction fragmentTransaction = createAddTransaction(activity, fragment, true);
      fragmentTransaction.add(containerId, fragment);
      commitAddTransaction(activity, fragment, fragmentTransaction, true);
    }
  }

  //  Метод для удаления текущего фрагмента
  public boolean removeCurrentFragment(BaseActivity activity) {
    return removeFragment(activity, mCurrentFragment);
  }

  //  Метод для удаления фрагмента
  public boolean removeFragment(BaseActivity activity, BaseFragment fragment) {
    //    Размер Стэка должен быть больше минимального, чтобы случайно не удалить корневой фрагмент
    boolean canRemove = fragment != null && mFragmentStack.size() > EMPTY_FRAGMENT_STACK_SIZE;

    if (canRemove) {
      FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

      //      Удаляем последний фрагмент из списка
      mFragmentStack.pop();
      //      Присваиваем последний элемент текущему фрагменту
      mCurrentFragment = mFragmentStack.lastElement();
      //      Коммитим транзакцию
      transaction.remove(fragment);
      commitTransaction(activity, transaction);
    }
    return canRemove;
  }

  //  Метод для добавления фрагментов в BackStack
  private FragmentTransaction createAddTransaction(BaseActivity activity, BaseFragment fragment,
      boolean addToBackStack) {
    FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

    if (addToBackStack) {
      fragmentTransaction.addToBackStack(fragment.getTag());
    }

    return fragmentTransaction;
  }

  //  Метод для коммита транзакции
  private void commitAddTransaction(BaseActivity activity, BaseFragment fragment, FragmentTransaction transaction,
      boolean addToBackStack) {
    if (transaction != null) {
      //      Присваиваем текущему фрагменту наш фрагмент
      mCurrentFragment = fragment;
      //    Если фрагменты не добавились в BackStack, создаем его
      if (!addToBackStack) {
        mFragmentStack = new Stack<>();
      }

      mFragmentStack.add(fragment);

      commitTransaction(activity, transaction);

    }
  }

  //  Метод для добавления любых транзакций, независимо добавился ли фрагмент или удалился
  private void commitTransaction(BaseActivity activity, FragmentTransaction transaction) {
    transaction.commit();

    activity.fragmentOnScreen(mCurrentFragment);
  }

  //  Проверяем, существует ли в Стэке текущий фрагмент
  public boolean isAlreadyContains(BaseFragment fragment) {
    if (fragment == null) {
      return false;
    }

    return mCurrentFragment != null
        && mCurrentFragment.getClass().getName().equals(fragment.getClass().getName());
  }


  public Stack<BaseFragment> getFragmentStack() {
    return mFragmentStack;
  }
}
