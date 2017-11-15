package ru.echodc.singlegroupe.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import java.util.Date;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.model.Place;
import ru.echodc.singlegroupe.ui.activity.OpenedPostFromPushActivity;


public class NotificationHelper {

  public static final int DEFAULT_SMALL_ICON = R.drawable.ic_message_black_24dp;

  public static void notify(Context context, PushModel pushModel) {

    if (pushModel == null) {
      return;
    }

    int mId = (int) new Date().getTime();

    int smallIcon = pushModel.getIcon() != 0 ? pushModel.getIcon() : DEFAULT_SMALL_ICON;

    String title = (pushModel.getFirstName() != null || pushModel.getLastName() != null) ?
        (pushModel.getFirstName() + " " + pushModel.getLastName())
        : context.getResources().getString(R.string.vk_group_default_name);

    String text = pushModel.getText() != null ?
        pushModel.getText() : context.getResources().getString(R.string.message);

    switch (pushModel.getType()) {
      case FcmMessage.TYPE_REPLY:
        title += " отвечает:";
        break;
      case FcmMessage.TYPE_COMMENT:
        title += " комментирует:";
        break;
      case FcmMessage.TYPE_NEW_POST:
        title += " новая запись на стене";
        break;
    }

    NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(context)
            .setSmallIcon(smallIcon)
            .setContentTitle(title)
            .setContentText(text)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setAutoCancel(true);
    // Creates an explicit intent for an Activity in your app
    Intent resultIntent = new Intent(context, OpenedPostFromPushActivity.class);

    resultIntent.putExtra(Place.PLACE, pushModel.getPlace().toBundle());

    // The stack builder object will contain an artificial back stack for the
    // started Activity.
    // This ensures that navigating backward from the Activity leads out of
    // your application to the Home screen.
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
    // Adds the back stack for the Intent (but not the Intent itself)
    stackBuilder.addParentStack(OpenedPostFromPushActivity.class);
    // Adds the Intent that starts the Activity to the top of the stack
    stackBuilder.addNextIntent(resultIntent);
    PendingIntent resultPendingIntent =
        stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        );
    mBuilder.setContentIntent(resultPendingIntent);
    NotificationManager mNotificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    // mId allows you to update the notification later on.
    mNotificationManager.notify(mId, mBuilder.build());
  }
}

