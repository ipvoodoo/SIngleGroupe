package ru.echodc.singlegroupe.common;


import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import ru.echodc.singlegroupe.model.view.BaseViewModel;
import ru.echodc.singlegroupe.ui.view.holder.BaseViewHolder;

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>> {

  private List<BaseViewModel> list = new ArrayList<>();
  //  Для создания кокретного типа ViewHolder
  private ArrayMap<Integer, BaseViewModel> mTypeInstances = new ArrayMap<>();

  @Override
  public BaseViewHolder<BaseViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
    return mTypeInstances.get(viewType).createViewHolder(parent);
  }

  @Override
  public void onBindViewHolder(BaseViewHolder<BaseViewModel> holder, int position) {
    holder.bindViewHolder(getItem(position));
  }

  @Override
  public void onViewRecycled(BaseViewHolder<BaseViewModel> holder) {
    super.onViewRecycled(holder);
    holder.unbindViewHolder();
  }

  //  Метод для регистрации типа Layout перед добавлением его в адаптер
  public void registerTypeInstance(BaseViewModel item) {
    //    Если список не содержит данный тип, то добавляем его сами
    if (!mTypeInstances.containsKey(item.getType())) {
      mTypeInstances.put(item.getType().getValue(), item);
    }
  }

  //  Метод для замены эелементов в списке
  public void setItems(List<BaseViewModel> items) {
    //    чистим список
    clearList();
    //    доюавляем новые эелементы
    addItems(items);
  }

  //  Метод для добавления элементов в список
  //  ? extends - означает, что можно передавать классы наследники в BaseViewModel
  public void addItems(List<? extends BaseViewModel> newItems) {

    for (BaseViewModel newItem : newItems) {
      //      Регистрируем тип, добавляем в массив и сообщаем адаптеру, что список изменился
      registerTypeInstance(newItem);
    }

    list.addAll(newItems);

    notifyDataSetChanged();
  }

  //  Метод для очистки списка
  public void clearList() {
    list.clear();
  }

  //  Метод возвращает тип View конкретного item
  @Override
  public int getItemViewType(int position) {
    return getItem(position).getType().getValue();
  }

  //  Метод возвращает размер списка item
  @Override
  public int getItemCount() {
    return list.size();
  }

  //  Метод для возврата item по позициям
  public BaseViewModel getItem(int position) {
    return list.get(position);
  }

  //  Метод будет перебирать все элементы списка,
  // проверять является ли элемент реальным и возвращать реальное количество элементов
  public int getRealItemCount() {
    int count = 0;
    for (int i = 0; i < getItemCount(); i++) {
      if (!getItem(i).isItemDecorator()) {
        count += 1;
      }
    }
    return count;
  }

  //  Для добавления элементов, в создаваемое сообщение
  public void insertItem(BaseViewModel newItem) {
    registerTypeInstance(newItem);

    list.add(newItem);
    notifyItemInserted(getItemCount() - 1);
  }

}