package ru.echodc.singlegroupe.di.component;

import dagger.Component;
import javax.inject.Singleton;
import ru.echodc.singlegroupe.common.manager.NetworkManager;
import ru.echodc.singlegroupe.di.module.ApplicationModule;
import ru.echodc.singlegroupe.di.module.ManagerModule;
import ru.echodc.singlegroupe.di.module.RestModule;
import ru.echodc.singlegroupe.model.view.CommentBodyViewModel;
import ru.echodc.singlegroupe.model.view.CommentFooterViewModel;
import ru.echodc.singlegroupe.model.view.TopicViewModel;
import ru.echodc.singlegroupe.mvp.presenter.BoardPresenter;
import ru.echodc.singlegroupe.mvp.presenter.CommentsPresenter;
import ru.echodc.singlegroupe.mvp.presenter.InfoContactsPresenter;
import ru.echodc.singlegroupe.mvp.presenter.InfoLinksPresenter;
import ru.echodc.singlegroupe.mvp.presenter.InfoPresenter;
import ru.echodc.singlegroupe.mvp.presenter.MainPresenter;
import ru.echodc.singlegroupe.mvp.presenter.MembersPresenter;
import ru.echodc.singlegroupe.mvp.presenter.NewsFeedPresenter;
import ru.echodc.singlegroupe.mvp.presenter.OpenedCommentPresenter;
import ru.echodc.singlegroupe.mvp.presenter.OpenedPostPresenter;
import ru.echodc.singlegroupe.mvp.presenter.TopicCommentsPresenter;
import ru.echodc.singlegroupe.ui.activity.BaseActivity;
import ru.echodc.singlegroupe.ui.activity.MainActivity;
import ru.echodc.singlegroupe.ui.fragment.CommentsFragment;
import ru.echodc.singlegroupe.ui.fragment.InfoContactsFragment;
import ru.echodc.singlegroupe.ui.fragment.InfoLinksFragment;
import ru.echodc.singlegroupe.ui.fragment.NewsFeedFragment;
import ru.echodc.singlegroupe.ui.fragment.OpenedCommentFragment;
import ru.echodc.singlegroupe.ui.fragment.OpenedPostFragment;
import ru.echodc.singlegroupe.ui.fragment.TopicCommentsFragment;
import ru.echodc.singlegroupe.ui.view.holder.NewsItemBodyHolder;
import ru.echodc.singlegroupe.ui.view.holder.NewsItemFooterHolder;
import ru.echodc.singlegroupe.ui.view.holder.attachment.ImageAttachmentHolder;
import ru.echodc.singlegroupe.ui.view.holder.attachment.VideoAttachmentHolder;

@Singleton
@Component(
    modules = {ApplicationModule.class, RestModule.class, ManagerModule.class})
public interface ApplicationComponent {

  //  Методы для внедрения зависимостей
  //activities
  void inject(BaseActivity activity);
  void inject(MainActivity activity);

  //fragments
  void inject(NewsFeedFragment fragment);
  void inject(OpenedPostFragment fragment);
  void inject(OpenedCommentFragment fragment);
  void inject(CommentsFragment fragment);
  void inject(TopicCommentsFragment fragment);
  void inject(InfoLinksFragment fragment);
  void inject(InfoContactsFragment fragment);

  //holders
  void inject(NewsItemBodyHolder holder);
  void inject(NewsItemFooterHolder holder);
  void inject(ImageAttachmentHolder holder);
  void inject(VideoAttachmentHolder holder);
  void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
  void inject(CommentFooterViewModel.CommentFooterHolder holder);
  void inject(TopicViewModel.TopicViewHolder holder);

  //presenters
  void inject(NewsFeedPresenter presenter);
  void inject(MainPresenter presenter);
  void inject(MembersPresenter presenter);
  void inject(BoardPresenter presenter);
  void inject(InfoPresenter presenter);
  void inject(OpenedPostPresenter presenter);
  void inject(CommentsPresenter presenter);
  void inject(OpenedCommentPresenter presenter);
  void inject(TopicCommentsPresenter presenter);
  void inject(InfoLinksPresenter presenter);
  void inject(InfoContactsPresenter presenter);

  //managers
  void inject(NetworkManager manager);
}

