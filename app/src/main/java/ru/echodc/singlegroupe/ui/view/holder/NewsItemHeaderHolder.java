package ru.echodc.singlegroupe.ui.view.holder;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.model.view.NewsItemHeaderViewModel;

//  Создает и управляет полями Хэдера
public class NewsItemHeaderHolder extends BaseViewHolder<NewsItemHeaderViewModel> {

  //  Аватар
  @BindView(R.id.civ_profile_image)
  public CircleImageView civProfileImage;
  //  Имя пользователя
  @BindView(R.id.tv_profile_name)
  public TextView tvName;
  //  Аватар отправителя репоста
  @BindView(R.id.iv_reposted_icon)
  public ImageView ivRepostedIcon;
  //  Имя отправителя
  @BindView(R.id.tv_reposted_profile_name)
  public TextView tvRepostedProfileName;

  public NewsItemHeaderHolder(View itemView) {
    super(itemView);
    //    Биндим вьюхи в конструктор
    ButterKnife.bind(this, itemView);
  }

  // region ===================== BaseViewHolder =====================
  @Override
  public void bindViewHolder(NewsItemHeaderViewModel item) {
    //    Получаем контекст
    Context context = itemView.getContext();
    //    Получаем автар
    Glide.with(context)
        .load(item.getProfilePhoto())
        .into(civProfileImage);
    //    Устанавливаем имя отправителя
    tvName.setText(item.getProfileName());
    //    Проверяем на репост
    if (item.isRepost()) {
      ivRepostedIcon.setVisibility(View.VISIBLE);
      tvRepostedProfileName.setText(item.getRepostProfileName());
    } else {
      ivRepostedIcon.setVisibility(View.GONE);
      tvRepostedProfileName.setText(null);
    }

  }

  @Override
  public void unbindViewHolder() {
    //    Очищаем автарки и текстовые поля
    civProfileImage.setImageBitmap(null);
    tvName.setText(null);
    tvRepostedProfileName.setText(null);

  }
  // endregion BaseViewHolder


}
