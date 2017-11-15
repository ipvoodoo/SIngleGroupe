package ru.echodc.singlegroupe.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.vk.sdk.api.model.VKAttachments;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import ru.echodc.singlegroupe.model.attachment.ApiAttachment;

public class Utils {
  //  конвертирует список из прикреплений в строку шрифтов иконок
  public static String convertAttachmentsToFontIcons(List<ApiAttachment> attachments) {
    String attachmentsString = "";

    for (ApiAttachment attachment : attachments) {
      switch (attachment.getType()) {
        case VKAttachments.TYPE_PHOTO:
          attachmentsString += new String(new char[]{0xE251}) + " ";
          break;
        case VKAttachments.TYPE_AUDIO:
          attachmentsString += new String(new char[]{0xE310}) + " ";
          break;
        case VKAttachments.TYPE_VIDEO:
          attachmentsString += new String(new char[]{0xE02C}) + " ";
          break;
        case VKAttachments.TYPE_LINK:
          attachmentsString += new String(new char[]{0xE250}) + " ";
          break;
        case VKAttachments.TYPE_DOC:
          attachmentsString += new String(new char[]{0xE24D}) + " ";
          break;
      }
    }
    return attachmentsString;
  }
  //  метод конвертирует дату из формата long в более понятный
  public static String parseDate(long initialDate, Context context) {
    Locale currentlocale = context.getResources().getConfiguration().locale;

    Date date = new Date(initialDate * 1000);

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy в H:mm", currentlocale);

    if (calendar.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR) &&
        calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
      sdf = new SimpleDateFormat("сегодня в H:mm", currentlocale);
    } else if (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
      sdf = new SimpleDateFormat("d MMM в H:mm", currentlocale);
    }
    return sdf.format(date);
  }

  public static String parseDuration(long initialDuration) {
    Date date = new Date(initialDuration * 1000);
    SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

    return sdf.format(date);
  }


  public static String formatViewsCount(int viewsCount) {
    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
    DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

    symbols.setGroupingSeparator(' ');
    formatter.setDecimalFormatSymbols(symbols);

    return formatter.format(viewsCount);
  }

  public static String formatSize(long bytes) {
    int unit = 1000;
    if (bytes < unit) return bytes + " B";
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    String pre = String.valueOf(("kMGTPE").charAt(exp - 1));
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
  }


  public static String removeExtFromText(String s) {
    String str = s;
    int length = s.length();
    int removeCharsAfter = 0;

    for (int i = 0; i < length; i++) {
      String c = Character.toString(s.charAt(i));
      if (c.equals(".")) {
        removeCharsAfter = i;
      }
    }

    for (int i = removeCharsAfter; i < length; i++) {
      str = removeCharAt(str, removeCharsAfter);
    }

    return str;

  }

  public static String removeCharAt(String s, int pos) {
    StringBuilder sb = new StringBuilder(s);
    sb.deleteCharAt(pos);
    return sb.toString();
  }



  public static void openUrlInActionView(String url, Context context) {
    if (url != null) {
      Intent intent = new Intent(Intent.ACTION_VIEW);
      Uri uri = Uri.parse(url);
      intent.setData(uri);
      if (intent.resolveActivity(context.getPackageManager()) != null) {
        context.startActivity(intent);
      }
    }
  }

  public static String[] splitString(String s) {
    return s.replaceAll("[^-?0-9]+", " ").trim().split(" ");
  }
}
