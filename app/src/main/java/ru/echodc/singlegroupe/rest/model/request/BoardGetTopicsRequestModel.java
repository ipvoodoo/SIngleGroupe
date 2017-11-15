package ru.echodc.singlegroupe.rest.model.request;


import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;
import java.util.Map;
import ru.echodc.singlegroupe.consts.ApiConstants;

public class BoardGetTopicsRequestModel extends BaseRequestModel {

  @SerializedName(VKApiConst.GROUP_ID)
  int groupId;

  @SerializedName(VKApiConst.COUNT)
  int count = ApiConstants.DEFAULT_COUNT;

  @SerializedName(VKApiConst.OFFSET)
  int offset = 0;

  public BoardGetTopicsRequestModel(int groupId) {
    this.groupId = Math.abs(groupId);
  }

  public BoardGetTopicsRequestModel(int groupId, int count, int offset) {
    this.groupId = Math.abs(groupId);
    this.count = count;
    this.offset = offset;
  }

  // region ===================== BaseRequestModel =====================
  @Override
  public void onMapCreate(Map<String, String> map) {
    map.put(VKApiConst.GROUP_ID, String.valueOf(getGroupId()));
    map.put(VKApiConst.COUNT, String.valueOf(getCount()));
    map.put(VKApiConst.OFFSET, String.valueOf(getOffset()));

  }
  // endregion BaseRequestModel


  public int getGroupId() {
    return groupId;
  }

  public int getCount() {
    return count;
  }

  public int getOffset() {
    return offset;
  }

  public void setGroupId(int groupId) {
    this.groupId = Math.abs(groupId);
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }
}
