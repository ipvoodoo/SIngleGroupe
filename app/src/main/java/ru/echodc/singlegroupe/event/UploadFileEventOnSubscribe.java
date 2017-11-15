package ru.echodc.singlegroupe.event;

import android.util.Log;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest.VKProgressType;
import com.vk.sdk.api.VKRequest.VKRequestListener;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiDocument;
import com.vk.sdk.api.model.VKDocsArray;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import java.io.File;

public class UploadFileEventOnSubscribe implements ObservableOnSubscribe<VKApiDocument> {

  private File mDoc;

  public UploadFileEventOnSubscribe(File doc) {
    this.mDoc = doc;
  }

  private static final String TAG = "UploadFileEventOnSubscr";

  //  Метод для загрузки документов в контакт
  @Override
  public void subscribe(@NonNull final ObservableEmitter<VKApiDocument> e) throws Exception {
    VKApi.docs().uploadWallDocRequest(mDoc).executeWithListener(new VKRequestListener() {
      @Override
      public void onComplete(VKResponse response) {
        super.onComplete(response);

        VKApiDocument doc = ((VKDocsArray) response.parsedModel).get(0);
        e.onNext(doc);
        e.onComplete();
      }

      @Override
      public void onError(VKError error) {
        super.onError(error);
        Log.d(TAG, "onError: " + error.apiError.errorMessage);
        e.onError(error.httpError);
      }

      @Override
      public void onProgress(VKProgressType progressType, long bytesLoaded, long bytesTotal) {
        super.onProgress(progressType, bytesLoaded, bytesTotal);
      }
    });
  }
}
