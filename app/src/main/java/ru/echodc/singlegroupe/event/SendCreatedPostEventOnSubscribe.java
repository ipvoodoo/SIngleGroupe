package ru.echodc.singlegroupe.event;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKRequest.VKRequestListener;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKAttachments;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

//  Класс для отправки запроса посредством SDK VK и преобразования ответа сервера в RX-цепочку данных
//  для создания записи на стене группы
public class SendCreatedPostEventOnSubscribe implements ObservableOnSubscribe<VKResponse> {

  private int mOwnerId;
  private String mMessage;
  private VKAttachments attachments;

  public SendCreatedPostEventOnSubscribe(int ownerId, int postId, String message, VKAttachments attachments) {
    this.mOwnerId = ownerId;
    this.mMessage = message;
    this.attachments = attachments;
  }

  public SendCreatedPostEventOnSubscribe(int myGroupId, String message, VKAttachments attachments) {
  }

  // Метод преобразовывает данные VKRequestListener в RX-цепочку данных
  @Override
  public void subscribe(@NonNull final ObservableEmitter<VKResponse> e) throws Exception {

    VKParameters parameters = new VKParameters();
    parameters.put(VKApiConst.OWNER_ID, mOwnerId);
    parameters.put(VKApiConst.ATTACHMENTS, attachments);
    parameters.put(VKApiConst.MESSAGE, mMessage);
    VKRequest request = VKApi.wall().addComment(parameters);
    request.attempts = 10;

    //    Отправляем запрос и создаем слушатель для получения ответа
    request.executeWithListener(new VKRequestListener() {
      @Override
      public void onComplete(VKResponse response) {
        super.onComplete(response);
        e.onNext(response);// - Передает ответ с сервера в RX цепочку
        e.onComplete();
      }

      @Override
      public void onError(VKError error) {
        super.onError(error);
        e.onError(error.httpError);
      }
    });
  }
}
