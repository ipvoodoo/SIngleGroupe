package ru.echodc.singlegroupe.rest.api;


import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.echodc.singlegroupe.model.CommentItem;
import ru.echodc.singlegroupe.model.Topic;
import ru.echodc.singlegroupe.rest.model.response.BaseItemResponse;
import ru.echodc.singlegroupe.rest.model.response.Full;
import ru.echodc.singlegroupe.rest.model.response.ItemWithSendersResponse;

public interface BoardApi {
    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);

    @GET(ApiMethods.BOARD_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
