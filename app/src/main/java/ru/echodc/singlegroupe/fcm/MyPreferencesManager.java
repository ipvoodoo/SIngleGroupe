package ru.echodc.singlegroupe.fcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyPreferencesManager {


  public static final String PREF_PUSH_NOTIFICATION_ADMIN = "pref_push_notification_admin";
  public static final String PREF_PUSH_NOTIFICATION_MEMBERS = "pref_push_notification_members";
  public static final String PREF_PUSH_NOTIFICATION_COMMENT_REPLIES = "pref_push_notification_comment_replies";


  private Context mContext;
  private SharedPreferences mSharedPref;

  private static MyPreferencesManager ourInstance = new MyPreferencesManager();

  public static MyPreferencesManager getInstance() {
    return ourInstance;
  }

  public void init(Context context) {
    this.mContext = context;
    mSharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);

  }

  public MyPreferencesManager() {

  }


  public boolean getPushNotificationPost() {
    return mSharedPref.getBoolean(PREF_PUSH_NOTIFICATION_ADMIN, true);
  }

  public boolean getPushNotificationComment() {
    return mSharedPref.getBoolean(PREF_PUSH_NOTIFICATION_MEMBERS, false);
  }

  public boolean getPushNotificationCommentReplies() {
    return mSharedPref.getBoolean(PREF_PUSH_NOTIFICATION_COMMENT_REPLIES, true);
  }
}