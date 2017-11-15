package ru.echodc.singlegroupe.rest.api;


import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.echodc.singlegroupe.rest.model.response.Full;
import ru.echodc.singlegroupe.rest.model.response.VideosResponse;

public interface VideoApi {
    @GET(ApiMethods.VIDEO_GET)
    Observable<Full<VideosResponse>> get(@QueryMap Map<String, String> map);
}
