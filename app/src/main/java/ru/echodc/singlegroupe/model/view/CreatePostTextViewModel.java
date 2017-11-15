package ru.echodc.singlegroupe.model.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.ui.view.holder.BaseViewHolder;

//  Модель для текстового поля в списке элементов, создаваемой записи или комментария
public class CreatePostTextViewModel extends BaseViewModel {

  private String mMessage;

  public CreatePostTextViewModel() {
  }

  public String getMessage() {
    return mMessage;
  }

  public void setMessage(String message) {
    this.mMessage = message;
  }

  @Override
  public LayoutTypes getType() {
    return LayoutTypes.CreatePostText;
  }

  @Override
  protected BaseViewHolder onCreateViewHolder(View view) {
    return new NewPostTextViewHolder(view);
  }

  public static class NewPostTextViewHolder extends BaseViewHolder<CreatePostTextViewModel> {

    @BindView(R.id.et_message)
    EditText mEtMessage;

    public NewPostTextViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(CreatePostTextViewModel createPostTextViewModel) {
      mEtMessage.setText(createPostTextViewModel.getMessage());

      //      Слушатель изменений в тектовом поле, которые сохраняем в переменную mEtMessage
      mEtMessage.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          createPostTextViewModel.setMessage(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
      });
    }

    @Override
    public void unbindViewHolder() {

    }
  }
}
