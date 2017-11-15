package ru.echodc.singlegroupe.ui.dialog;

import android.Manifest.permission;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import droidninja.filepicker.FilePickerBuilder;

//  Выводит диалог добавления прикрепления
public class AddAttachmentDialogFragment extends DialogFragment {

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {

    if (ContextCompat.checkSelfPermission(getActivity(), permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission.READ_EXTERNAL_STORAGE)) {

      } else {
        ActivityCompat.requestPermissions(getActivity(), new String[]{
            permission.READ_EXTERNAL_STORAGE
        }, 123);
      }
    }

    //    В зависимости от выбранного типа прикрепления вызывается активити из библиотеки,
    //    отображающее списокэлементов для прикрепления
    //    после выбора, передает данные через контекст в метод onActivityResult CreatePostActivity
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle("Прикрепить")
        .setItems(new String[]{"Фото", "Документ"}, new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
              case 0:
                FilePickerBuilder
                    .getInstance()
                    .showFolderView(false)
                    .pickFile(getActivity());
                break;
              case 1:
                FilePickerBuilder
                    .getInstance()
                    .setMaxCount(10)
                    .pickFile(getActivity());
                break;
            }
          }
        });
    return builder.create();
  }
}
