package ru.echodc.singlegroupe.mvp.view;

import com.arellomobile.mvp.MvpView;
import ru.echodc.singlegroupe.model.WallItem;
import ru.echodc.singlegroupe.model.view.counter.LikeCounterViewModel;

public interface PostFooterView extends MvpView {
    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}
