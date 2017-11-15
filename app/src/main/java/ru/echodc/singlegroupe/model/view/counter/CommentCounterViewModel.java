package ru.echodc.singlegroupe.model.view.counter;


import ru.echodc.singlegroupe.model.countable.Comments;

//    Счетчик комментариев
public class CommentCounterViewModel extends CounterViewModel {

  private Comments mComments;

  public CommentCounterViewModel(Comments comments) {
    super(comments.getCount());

    this.mComments = comments;
  }

  public Comments getComments() {
    return mComments;
  }
}
