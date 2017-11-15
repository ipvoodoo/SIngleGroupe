package ru.echodc.singlegroupe.rest.api;


import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.echodc.singlegroupe.model.CommentItem;
import ru.echodc.singlegroupe.rest.model.response.Full;
import ru.echodc.singlegroupe.rest.model.response.GetWallByIdResponse;
import ru.echodc.singlegroupe.rest.model.response.GetWallResponse;
import ru.echodc.singlegroupe.rest.model.response.ItemWithSendersResponse;

//  Формат запросов
public interface WallApi {

  //  Параметры запроса
  @GET(ApiMethods.WALL_GET)
  //  Call<GetWallResponse> get(@QueryMap Map<String, String> map);
  //  Меняем на Observable
  Observable<GetWallResponse> get(@QueryMap Map<String, String> map);

  @GET(ApiMethods.WALL_GET_BY_ID)
  Observable<GetWallByIdResponse> getById(@QueryMap Map<String, String> map);

  @GET(ApiMethods.WALL_GET_COMMENTS)
  Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);

}
