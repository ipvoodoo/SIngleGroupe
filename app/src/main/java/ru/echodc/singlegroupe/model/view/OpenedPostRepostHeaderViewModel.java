package ru.echodc.singlegroupe.model.view;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.common.utils.UiHelper;
import ru.echodc.singlegroupe.common.utils.Utils;
import ru.echodc.singlegroupe.model.WallItem;
import ru.echodc.singlegroupe.ui.view.holder.BaseViewHolder;

public class OpenedPostRepostHeaderViewModel extends BaseViewModel {

    private int mId;

    private String mProfileName;
    private String mProfilePhoto;

    private String mText;

    private long mDate;

    public OpenedPostRepostHeaderViewModel(WallItem forwardedPost) {
        this.mId = forwardedPost.getId();

        this.mProfileName = forwardedPost.getSenderName();
        this.mProfilePhoto = forwardedPost.getSenderPhoto();

        this.mText = forwardedPost.getText();

        this.mDate = forwardedPost.getDate();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.OpenedPostRepostHeader;
    }

    @Override
    public OpenedPostRepostHolder onCreateViewHolder(View view) {
        return new OpenedPostRepostHolder(view);
    }

    public int getId() {
        return mId;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public String getProfilePhoto() {
        return mProfilePhoto;
    }

    public String getText() {
        return mText;
    }

    public long getDate() {
        return mDate;
    }

    class OpenedPostRepostHolder extends BaseViewHolder<OpenedPostRepostHeaderViewModel> {

        @BindView(R.id.civ_repost_profile_image)
        public CircleImageView repostImage;

        @BindView(R.id.tv_repost_profile_name)
        public TextView repostName;

        @BindView(R.id.tv_repost_text)
        public TextView repostText;

        @BindView(R.id.tv_repost_date)
        public TextView repostDate;


        public OpenedPostRepostHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(OpenedPostRepostHeaderViewModel openedPostRepostHeaderViewModel) {
            Glide.with(itemView.getContext()).load(openedPostRepostHeaderViewModel.getProfilePhoto()).into(repostImage);
            repostName.setText(openedPostRepostHeaderViewModel.getProfileName());

            UiHelper.getInstance().setUpTextViewWithVisibility(repostText, openedPostRepostHeaderViewModel.getText());

            repostDate.setText(Utils.parseDate(openedPostRepostHeaderViewModel.getDate(), itemView.getContext()));
        }

        @Override
        public void unbindViewHolder() {
            repostImage.setImageBitmap(null);
            repostName.setText(null);

            repostText.setText(null);
            repostDate.setText(null);
        }



    }
}
