package ru.echodc.singlegroupe.ui.view.holder;


import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import javax.inject.Inject;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.common.manager.MyFragmentManager;
import ru.echodc.singlegroupe.common.utils.UiHelper;
import ru.echodc.singlegroupe.model.view.NewsItemBodyViewModel;
import ru.echodc.singlegroupe.ui.activity.BaseActivity;
import ru.echodc.singlegroupe.ui.fragment.OpenedPostFragment;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

  @BindView(R.id.tv_text)
  public TextView tvText;

  @BindView(R.id.tv_attachments)
  public TextView tvAttachments;

  @Inject
  protected Typeface mFontGoogle;

  @Inject
  MyFragmentManager myFragmentManager;


  public NewsItemBodyHolder(View itemView) {
    super(itemView);
    //    Биндим вьюхи в конструктор
    ButterKnife.bind(this, itemView);
    MyApplication.getApplicationComponent().inject(this);

    if (tvAttachments != null) {
      tvAttachments.setTypeface(mFontGoogle);
    }
  }

  // region ===================== BaseViewHolder =====================
  @Override
  public void bindViewHolder(NewsItemBodyViewModel item) {
    tvText.setText(item.getText());
    tvAttachments.setText(item.getAttachmentString());

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        myFragmentManager.addFragment((BaseActivity) view.getContext(),
            OpenedPostFragment.newInstance(item.getId()),
            R.id.main_wrapper);

      }
    });
    UiHelper.getInstance().setUpTextViewWithVisibility(tvText, item.getText());
    UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, item.getAttachmentString());
  }

  @Override
  public void unbindViewHolder() {
    tvText.setText(null);
    tvAttachments.setText(null);
    itemView.setOnClickListener(null);
  }
  // endregion BaseViewHolder

}
