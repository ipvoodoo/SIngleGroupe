package ru.echodc.singlegroupe.mvp.view;


import ru.echodc.singlegroupe.model.view.NewsItemFooterViewModel;

public interface OpenedPostView extends BaseFeedView {

  void setFooter(NewsItemFooterViewModel viewModel);
}
