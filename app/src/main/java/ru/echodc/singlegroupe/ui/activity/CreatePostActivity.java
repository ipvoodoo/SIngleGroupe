package ru.echodc.singlegroupe.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiDocument;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKAttachments;
import droidninja.filepicker.FilePickerConst;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.common.BaseAdapter;
import ru.echodc.singlegroupe.consts.ApiConstants;
import ru.echodc.singlegroupe.event.SendCommentEventOnSubscribe;
import ru.echodc.singlegroupe.event.SendCreatedPostEventOnSubscribe;
import ru.echodc.singlegroupe.event.UploadFileEventOnSubscribe;
import ru.echodc.singlegroupe.event.UploadPhotoEventOnSubscribe;
import ru.echodc.singlegroupe.model.view.CreatePostTextViewModel;
import ru.echodc.singlegroupe.model.view.attachment.DocAttachmentViewModel;
import ru.echodc.singlegroupe.model.view.attachment.ImageAttachmentViewModel;
import ru.echodc.singlegroupe.ui.dialog.AddAttachmentDialogFragment;

public class CreatePostActivity extends BaseActivity{
  @BindView(R.id.toolbar)
  Toolbar mToolbar;

  VKAttachments attachments;

  CreatePostTextViewModel createPostTextViewModel;

  private BaseAdapter mAdapter;

  private RecyclerView mRecyclerView;

  String mType;
  int ownerId;
  int id;

  public static final String TAG = "CreatePostActivity";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ButterKnife.bind(this);

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      mType = bundle.getString("type");
      ownerId = bundle.getInt("owner_id");
      id = bundle.getInt("id");
    }
    mAdapter = new BaseAdapter();

    mRecyclerView = findViewById(R.id.rv_list);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    mRecyclerView.setAdapter(mAdapter);

    createPostTextViewModel = new CreatePostTextViewModel();

    mAdapter.insertItem(createPostTextViewModel);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

    getSupportActionBar().setTitle("Новая запись");

    attachments = new VKAttachments();
  }

  @Override
  protected int getMainContentLayout() {
    return R.layout.activity_create_post;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_create_post, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case android.R.id.home:
        finish();
        break;
      case R.id.action_attach:
        attach();
        break;
      case R.id.action_post:
        Log.d("create post,", " post");
        post();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

  private void post() {
    if (createPostTextViewModel.getMessage() == null) {
      Toast.makeText(this, "Добавьте текст сообщения", Toast.LENGTH_LONG).show();
      return;
    }

    //    Объявляем переменную интерфейса
    ObservableOnSubscribe<VKResponse> o;
    //    В зависимости от типа создаваемого сообщения, инициализируем переменную объектом
    //    соответствующего события
    if (mType != null && mType.equals("comment")) {
      //      Если сообщение
      o = new SendCommentEventOnSubscribe(ownerId, id, createPostTextViewModel.getMessage(), attachments);
    } else {
      //      Если пост на стену
      o = new SendCreatedPostEventOnSubscribe(ApiConstants.MY_GROUP_ID, createPostTextViewModel.getMessage(), attachments);
    }

    //    Создаем Observable и подписываем Observer на него
    Observable.create(o)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<VKResponse>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {

          }

          @Override
          public void onNext(@NonNull VKResponse vkResponse) {
            postResult(vkResponse);
          }

          @Override
          public void onError(@NonNull Throwable e) {
            e.printStackTrace();
            Toast.makeText(CreatePostActivity.this, "Запись не опубликована. озможно у вас не прав для пубдиукации!", Toast.LENGTH_LONG).show();
          }

          @Override
          public void onComplete() {

          }
        });
  }

  //  Закрытие Активити и передача сообщения об успехе в главное Активити
  private void postResult(VKResponse vkResponse) {
    Bundle conData = new Bundle();
    conData.putString("results", "Thanks Thanks");
    Intent intent = new Intent();
    intent.putExtras(conData);
    setResult(RESULT_OK, intent);
    finish();
  }

  //  создает диалог прикрепления
  private void attach() {
    AddAttachmentDialogFragment dialog = new AddAttachmentDialogFragment();
    dialog.show(getFragmentManager(), AddAttachmentDialogFragment.class.getSimpleName());
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Log.d("createPostActivity", "res = " + requestCode);

    //    В заивисимости от типа элементов, полученных от библиотеки FilePicker , вызываем LoadFile или loadPhoto
    switch (requestCode) {
      case FilePickerConst.REQUEST_CODE_PHOTO:
        if (resultCode == Activity.RESULT_OK && data != null) {
          Log.d(TAG, "onActivityResult: photo pick");
          List<String> photoPaths = new ArrayList<>();
          photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));

          for (String photoPath : photoPaths) {
            ImageAttachmentViewModel photo = new ImageAttachmentViewModel(photoPath);
            loadPhoto(photo);
          }
        }
        break;

      case FilePickerConst.REQUEST_CODE_DOC:
        if (resultCode == Activity.RESULT_OK && data != null) {
          List<String> docPaths = new ArrayList<>();
          docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));

          for (String docPath : docPaths) {
            File file = new File(docPath);
            loadFile(new DocAttachmentViewModel(file));
          }
        }
        break;
    }
  }

  void loadPhoto(final ImageAttachmentViewModel item) {
    getProgressBar().setVisibility(View.VISIBLE);

    Observable.create(new UploadPhotoEventOnSubscribe(item.getPhotoUrl()))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<VKApiPhoto>() {

          @Override
          public void onSubscribe(@NonNull Disposable d) {

          }

          @Override
          public void onNext(@NonNull VKApiPhoto vkApiPhoto) {
            attachments.add(vkApiPhoto);
            mAdapter.insertItem(item);
            Log.d("attach", "photo: " + vkApiPhoto.photo_130);
            Log.d("attach", "compl");
            Toast.makeText(CreatePostActivity.this, R.string.loading_competed, Toast.LENGTH_LONG).show();
          }

          @Override
          public void onError(@NonNull Throwable e) {
            e.printStackTrace();
            getProgressBar().setVisibility(View.GONE);
            Toast.makeText(CreatePostActivity.this, R.string.loading_failed, Toast.LENGTH_LONG).show();

            getProgressBar().setVisibility(View.GONE);
          }

          @Override
          public void onComplete() {
            getProgressBar().setVisibility(View.GONE);
          }
        });
  }

  void loadFile(final DocAttachmentViewModel docViewModel) {
    getProgressBar().setVisibility(View.VISIBLE);

    Observable.create(new UploadFileEventOnSubscribe(docViewModel.getmFile()))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<VKApiDocument>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {

          }

          @Override
          public void onNext(@NonNull VKApiDocument vkApiDocument) {
            attachments.add(vkApiDocument);
            Log.d("attach", "compl");

            mAdapter.insertItem(docViewModel);
            Toast.makeText(CreatePostActivity.this, R.string.loading_competed, Toast.LENGTH_LONG).show();
          }

          @Override
          public void onError(@NonNull Throwable e) {
            e.printStackTrace();
            getProgressBar().setVisibility(View.GONE);
            Toast.makeText(CreatePostActivity.this, R.string.loading_failed, Toast.LENGTH_LONG).show();
          }

          @Override
          public void onComplete() {
            getProgressBar().setVisibility(View.GONE);
          }
        });
  }
}
