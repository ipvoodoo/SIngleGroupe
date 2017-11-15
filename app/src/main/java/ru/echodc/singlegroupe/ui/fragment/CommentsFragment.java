package ru.echodc.singlegroupe.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.ButterKnife;
import com.arellomobile.mvp.presenter.InjectPresenter;
import ru.echodc.singlegroupe.MyApplication;
import ru.echodc.singlegroupe.R;
import ru.echodc.singlegroupe.model.Place;
import ru.echodc.singlegroupe.mvp.presenter.BaseFeedPresenter;
import ru.echodc.singlegroupe.mvp.presenter.CommentsPresenter;
import ru.echodc.singlegroupe.ui.activity.CreatePostActivity;

public class CommentsFragment extends BaseFeedFragment {

    public static CommentsFragment newInstance(Place place) {

        Bundle args = new Bundle();
        args.putAll(place.toBundle());

        CommentsFragment fragment = new CommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    CommentsPresenter mPresenter;

    Place mPlace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);

        mPlace = new Place(getArguments());
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseActivity(), CreatePostActivity.class);
                intent.putExtra("type", "comment");
                intent.putExtra("owner_id", Integer.parseInt(mPlace.getOwnerId()));
                intent.putExtra("id", Integer.parseInt(mPlace.getPostId()));
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setPlace(mPlace);
        return mPresenter;
    }


    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_comments;
    }

    @Override
    public boolean needFab() {
        return true;
    }
}
