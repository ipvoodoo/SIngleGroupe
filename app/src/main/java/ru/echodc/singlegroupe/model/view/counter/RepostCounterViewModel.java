package ru.echodc.singlegroupe.model.view.counter;


import ru.echodc.singlegroupe.model.countable.Reposts;

//    Счетчик репостов
public class RepostCounterViewModel extends CounterViewModel {

  private Reposts mReposts;

  public RepostCounterViewModel(Reposts reposts) {
    super(reposts.getCount());

    this.mReposts = reposts;
    if (mReposts.getUserReposted() == 1) {
      setAccentColor();
    }
  }

  public Reposts getReposts() {
    return mReposts;
  }
}
