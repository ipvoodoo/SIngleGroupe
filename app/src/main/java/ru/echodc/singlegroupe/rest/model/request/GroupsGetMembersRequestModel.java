package ru.echodc.singlegroupe.rest.model.request;


import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;
import java.util.Map;
import ru.echodc.singlegroupe.consts.ApiConstants;

public class GroupsGetMembersRequestModel extends BaseRequestModel {

  @SerializedName(VKApiConst.GROUP_ID)
  int groupId;

  @SerializedName(VKApiConst.COUNT)
  int count = ApiConstants.DEFAULT_COUNT;

  @SerializedName(VKApiConst.OFFSET)
  int offset;

  @SerializedName(VKApiConst.FIELDS)
  String fields = ApiConstants.DEFAULT_MEMBER_FIELDS;

  public GroupsGetMembersRequestModel(int groupId) {
    this.groupId = Math.abs(groupId);
  }

  public GroupsGetMembersRequestModel(int groupId, int count, int offset) {
    this.groupId = Math.abs(groupId);
    this.count = count;
    this.offset = offset;
  }

  public int getGroupId() {
    return groupId;
  }

  public int getCount() {
    return count;
  }

  public int getOffset() {
    return offset;
  }

  public String getFields() {
    return fields;
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

  public void setFields(String fields) {
    this.fields = fields;
  }

  // region ===================== BaseRequestModel =====================
  @Override
  public void onMapCreate(Map<String, String> map) {
    map.put(VKApiConst.GROUP_ID, String.valueOf(getGroupId()));
    map.put(VKApiConst.COUNT, String.valueOf(getCount()));
    map.put(VKApiConst.OFFSET, String.valueOf(getOffset()));
    map.put(VKApiConst.FIELDS, String.valueOf(getFields()));
  }
  // endregion BaseRequestModel

}
















