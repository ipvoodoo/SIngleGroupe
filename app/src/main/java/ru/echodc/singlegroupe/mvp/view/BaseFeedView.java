package ru.echodc.singlegroupe.mvp.view;

import com.arellomobile.mvp.MvpView;
import java.util.List;
import ru.echodc.singlegroupe.model.view.BaseViewModel;

public interface BaseFeedView extends MvpView {

    void showRefreshing();//- показ анимации загрузки(сверху)
    void hideRefreshing();//- скрытие анимации загрузки(сверху)
    void showListProgress();//- показ анимации загрузки(в центре
    void hideListProgress();//- скрытие анимации загрузки(в центре
    void showError(String message);//- отображение ошибки
    void setItems(List<BaseViewModel> items);//- замена существующего списка новым
    void addItems(List<BaseViewModel> items);//- добавление новых элементов списка в конец существующего
}
