package ru.echodc.singlegroupe.fcm;


//общая модель push-сообщения
public abstract class FcmMessage {

  public static final String TYPE = "type";
  public static final String FROM_ID = "from_id";
  public static final String TEXT = "text";
  public static final String FIRST_NAME = "first_name";
  public static final String LAST_NAME = "last_name";

  public static final String TYPE_NEW_POST = "new_post";
  public static final String TYPE_REPLY = "reply";
  public static final String TYPE_COMMENT = "comment";

  protected String type;
  protected String fromId;
  protected String text;
  protected String first_name;
  protected String last_name;

  protected boolean valid;

  public boolean isValid() {
    return valid;
  }

  public abstract PushModel toPushModel();
}
