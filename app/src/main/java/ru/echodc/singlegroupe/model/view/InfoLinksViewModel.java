package ru.echodc.singlegroupe.model.view;


import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.ui.view.holder.BaseViewHolder;

public class InfoLinksViewModel extends BaseViewModel {

  // region ===================== BaseViewModel =====================
  @Override
  public LayoutTypes getType() {
    return LayoutTypes.InfoLinks;
  }

  @Override
  public InfoLinkViewHolder onCreateViewHolder(View view) {
    return new InfoLinkViewHolder(view);
  }
  // endregion BaseViewModel


  static class InfoLinkViewHolder extends BaseViewHolder<InfoLinksViewModel> {

    @BindView(R.id.rv_links)
    RelativeLayout rvLinks;

    public InfoLinkViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {

    }

    @Override
    public void unbindViewHolder() {

    }
  }
}
