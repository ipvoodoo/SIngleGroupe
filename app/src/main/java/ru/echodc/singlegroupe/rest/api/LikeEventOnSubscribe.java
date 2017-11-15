package ru.echodc.singlegroupe.rest.api;


import android.support.annotation.NonNull;
import android.util.Log;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import org.json.JSONException;
import ru.echodc.singlegroupe.model.countable.Likes;

public class LikeEventOnSubscribe implements ObservableOnSubscribe<Integer> {


    private Likes mLikes;
    private String mType;
    private int mOwnerId;
    private int mItemId;

    public LikeEventOnSubscribe(String type, int ownerId, int itemId, Likes likes) {
        this.mLikes = likes;
        this.mType = type;
        this.mOwnerId = ownerId;
        this.mItemId = itemId;
    }

    private static final String TAG = "AddLikeEventOnSubscribe";


    @Override
    public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
        if (mLikes.getCanLike() == 1) {
            addLike(e);

        } else if (mLikes.getCanLike() == 0 && mLikes.isUserLikes()) {
            deleteLike(e);

        } else {
            e.onComplete();
        }
    }


    private void addLike(final ObservableEmitter<Integer> subscriber) {


        Log.d(TAG, "onComplete: 1 " + Thread.currentThread());
        addLike(mType, mOwnerId, mItemId, new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Log.d(TAG, "onComplete: 2 " + Thread.currentThread());
                try {
                    Log.d(TAG, "onComplete: 3 " + Thread.currentThread());
                    int likesCount = response.json.getJSONObject("response").getInt("likes");
                    subscriber.onNext(likesCount);
                    subscriber.onComplete();
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                subscriber.onError(error.httpError);

            }
        });
    }

    private void deleteLike(ObservableEmitter<Integer> subscriber) {
        deleteLike(mType, mOwnerId, mItemId, new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    int likesCount = response.json.getJSONObject("response").getInt("likes");
                    subscriber.onNext(likesCount);
                    subscriber.onComplete();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                subscriber.onError(error.httpError);

            }
        });
    }

    public static void addLike(String type, int ownerId, int itemId, VKRequest.VKRequestListener listener) {
        VKRequest request = new VKRequest("likes.add", VKParameters.from(
                "type", type,
                VKApiConst.OWNER_ID, ownerId,
                "item_id", itemId));
        request.attempts = 10;

        request.executeWithListener(listener);

    }

    public static void deleteLike(String type, int ownerId, int itemId, VKRequest.VKRequestListener listener) {
        VKRequest request = new VKRequest("likes.delete", VKParameters.from(
                "type", type,
                VKApiConst.OWNER_ID, ownerId,
                "item_id", itemId));
        request.attempts = 10;

        request.executeWithListener(listener);
    }
}
