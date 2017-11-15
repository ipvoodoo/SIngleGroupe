package ru.echodc.singlegroupe.fcm;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

  private static final String TAG = "MyFMService";

  //выполняется когда приходит сообщение
  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    // Handle data payload of FCM messages.
    Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());

    Log.d(TAG, "FCM Notification: " + remoteMessage.getNotification());
    Log.d(TAG, "FCM data: " + remoteMessage.getData());

    Log.d(TAG, "FCM Data Message: " + remoteMessage.getFrom());

    sendNotification(remoteMessage);
  }


  private void sendNotification(RemoteMessage remoteMessage) {
    PushModel pushModel = PushUtils.parseFcmMessage(remoteMessage.getData()).toPushModel();

    if (pushModel.getType().equals(FcmMessage.TYPE_REPLY)
        && !MyPreferencesManager.getInstance().getPushNotificationCommentReplies()) {
      return;
    }

    if (pushModel.getType().equals(FcmMessage.TYPE_COMMENT)
        && !MyPreferencesManager.getInstance().getPushNotificationComment()) {
      return;
    }

    if (pushModel.getType().equals(FcmMessage.TYPE_NEW_POST)
        && !MyPreferencesManager.getInstance().getPushNotificationPost()) {
      return;
    }

    NotificationHelper.notify(
        this,
        pushModel);
  }
}



