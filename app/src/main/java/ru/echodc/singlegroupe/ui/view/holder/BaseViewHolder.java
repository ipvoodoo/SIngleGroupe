package ru.echodc.singlegroupe.ui.view.holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import ru.echodc.singlegroupe.model.view.BaseViewModel;

public abstract class BaseViewHolder<Item extends BaseViewModel> extends RecyclerView.ViewHolder {

  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  //  Метод для заполнения макета данными из модели Item
  public abstract void bindViewHolder(Item item);
  //  Метод для разгрузки макета, когда он не используется
  public abstract void unbindViewHolder();
}
