package ru.echodc.singlegroupe.rest.model.request;


import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;
import java.util.Map;
import ru.echodc.singlegroupe.consts.ApiConstants;

public class WallGetRequestModel extends BaseRequestModel {

  @SerializedName(VKApiConst.OWNER_ID)
  int ownerId;

  @SerializedName(VKApiConst.COUNT)
  int count = ApiConstants.DEFAULT_COUNT;

  @SerializedName(VKApiConst.OFFSET)
  int offset = 0;

  @SerializedName(VKApiConst.EXTENDED)
  int extended = 1;// всегда будет = 1 для получения profiles и group

  public WallGetRequestModel(int ownerId) {
    this.ownerId = ownerId;
  }

  public WallGetRequestModel(int ownerId, int count, int offset) {
    this.ownerId = ownerId;
    this.count = count;
    this.offset = offset;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
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

  // region ===================== BaseRequestModel =====================
  @Override
  public void onMapCreate(Map<String, String> map) {
    map.put(VKApiConst.OWNER_ID, String.valueOf(getOwnerId()));
    map.put(VKApiConst.COUNT, String.valueOf(getCount()));
    map.put(VKApiConst.OFFSET, String.valueOf(getOffset()));
    map.put(VKApiConst.EXTENDED, String.valueOf(getExtended()));
  }
  // endregion BaseRequestModel

}
