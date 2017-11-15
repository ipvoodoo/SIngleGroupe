package ru.echodc.singlegroupe.model.view.attachment;

import android.view.View;
import ru.echodc.singlegroupe.common.utils.Utils;
import ru.echodc.singlegroupe.model.attachment.video.Video;
import ru.echodc.singlegroupe.model.view.BaseViewModel;
import ru.echodc.singlegroupe.ui.view.holder.attachment.VideoAttachmentHolder;


public class VideoAttachmentViewModel extends BaseViewModel {

    private int id;
    private int ownerId;

    private String mTitle;
    private String mViewCount;
    private String mDuration;
    private String mImageUrl;

    public VideoAttachmentViewModel(Video video) {
        this.id = video.getId();
        this.ownerId = video.getOwnerId();

        if (video.getTitle().equals("")) {
            mTitle = "Video";
        } else {
            mTitle = video.getTitle();
        }

        mViewCount = Utils.formatViewsCount(video.getViews());

        mDuration = Utils.parseDuration(video.getDuration());

        mImageUrl = video.getPhoto320();
    }



    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentVideo;
    }

    @Override
    public VideoAttachmentHolder onCreateViewHolder(View view) {
        return new VideoAttachmentHolder(view);
    }


    public int getOwnerId() {
        return ownerId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getViewCount() {
        return mViewCount;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
