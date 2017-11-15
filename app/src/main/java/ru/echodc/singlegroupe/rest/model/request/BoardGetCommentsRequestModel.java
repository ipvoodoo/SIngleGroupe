package ru.echodc.singlegroupe.rest.model.request;


import com.google.gson.annotations.SerializedName;
import java.util.Map;
import ru.echodc.singlegroupe.consts.ApiConstants;

public class BoardGetCommentsRequestModel extends BaseRequestModel {
    @SerializedName(ApiConstants.GROUP_ID)
    int groupId;

    @SerializedName(ApiConstants.TOPIC_ID)
    int topicId;

    @SerializedName(ApiConstants.COUNT)
    int count = ApiConstants.DEFAULT_COUNT;

    @SerializedName(ApiConstants.OFFSET)
    int offset = 0;

    @SerializedName(ApiConstants.EXTENDED)
    int extended = 1;

    @SerializedName(ApiConstants.NEED_LIKES)
    int needLikes = 1;

    public BoardGetCommentsRequestModel(int groupId, int topicId) {
        this.groupId = Math.abs(groupId);
        this.topicId = topicId;
    }

    public BoardGetCommentsRequestModel(int groupId, int topicId, int offset) {
        this.groupId = Math.abs(groupId);
        this.topicId = topicId;
        this.offset = offset;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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
        map.put(ApiConstants.GROUP_ID, String.valueOf(getGroupId()));
        map.put(ApiConstants.TOPIC_ID, String.valueOf(getTopicId()));
        map.put(ApiConstants.COUNT, String.valueOf(getCount()));
        map.put(ApiConstants.OFFSET, String.valueOf(getOffset()));
        map.put(ApiConstants.EXTENDED, String.valueOf(getExtended()));
        map.put(ApiConstants.NEED_LIKES, String.valueOf(getNeedLikes()));
    }
}

