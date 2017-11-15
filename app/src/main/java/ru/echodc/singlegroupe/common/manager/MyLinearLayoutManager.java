package ru.echodc.singlegroupe.common.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * класс имеет доступ к view списка и будет проверять,
 * находится ли список в той позиции, в которой нужно подгружать следующие элементы
 */
public class MyLinearLayoutManager extends LinearLayoutManager {
  public MyLinearLayoutManager(Context context) {
    super(context);
  }

  public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
    super(context, orientation, reverseLayout);
  }

  public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public boolean isOnNextPagePosition() {
    int visibleItemCount = getChildCount();
    int totalItemCount = getItemCount();
    int pastVisibleItems = findFirstVisibleItemPosition();

    return (visibleItemCount + pastVisibleItems) >= totalItemCount / 2;
  }
}
