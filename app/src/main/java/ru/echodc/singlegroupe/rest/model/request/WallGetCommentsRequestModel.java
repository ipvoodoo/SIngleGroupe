package ru.echodc.singlegroupe.rest.model.request;


import com.google.gson.annotations.SerializedName;
import java.util.Map;
import ru.echodc.singlegroupe.consts.ApiConstants;

public class WallGetCommentsRequestModel extends BaseRequestModel {
    @SerializedName(ApiConstants.OWNER_ID)
    int ownerId;

    @SerializedName(ApiConstants.POST_ID)
    int postId;

    @SerializedName(ApiConstants.COUNT)
    int count = ApiConstants.DEFAULT_COUNT;

    @SerializedName(ApiConstants.OFFSET)
    int offset = 0;

    @SerializedName(ApiConstants.EXTENDED)
    int extended = 1;

    @SerializedName(ApiConstants.NEED_LIKES)
    int needLikes = 1;

    public WallGetCommentsRequestModel(int ownerId, int postId) {
        this.ownerId = ownerId;
        this.postId = postId;
    }

    public WallGetCommentsRequestModel(int ownerId, int postId, int offset) {
        this.ownerId = ownerId;
        this.postId = postId;
        this.offset = offset;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getExtended() {
        return extended;
    }

    public void setExtended(int extended) {
        this.extended = extended;
    }

    public int getNeedLikes() {
        return needLikes;
    }

    public void setNeedLikes(int needLikes) {
        this.needLikes = needLikes;
    }


    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(ApiConstants.OWNER_ID, String.valueOf(getOwnerId()));
        map.put(ApiConstants.POST_ID, String.valueOf(getPostId()));
        map.put(ApiConstants.COUNT, String.valueOf(getCount()));
        map.put(ApiConstants.OFFSET, String.valueOf(getOffset()));
        map.put(ApiConstants.EXTENDED, String.valueOf(getExtended()));
        map.put(ApiConstants.NEED_LIKES, String.valueOf(getNeedLikes()));
    }
}