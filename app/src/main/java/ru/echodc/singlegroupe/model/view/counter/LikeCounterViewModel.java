package ru.echodc.singlegroupe.model.view.counter;


import ru.echodc.singlegroupe.model.countable.Likes;

//    Счетчик лайков
public class LikeCounterViewModel extends CounterViewModel {

  private Likes mLikes;

  public LikeCounterViewModel(Likes likes) {
    super(likes.getCount());

    this.mLikes = likes;

    if (mLikes.getUserLikes() == 1) {
      setAccentColor();
    }
  }

  public Likes getLikes() {
    return mLikes;
  }
}
