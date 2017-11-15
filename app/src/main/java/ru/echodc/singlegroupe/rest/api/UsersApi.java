package ru.echodc.singlegroupe.rest.api;


import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.echodc.singlegroupe.model.Profile;
import ru.echodc.singlegroupe.rest.model.response.Full;

public interface UsersApi {
    //  Получаем список профилей
    @GET(ApiMethods.USERS_GET)
    Observable<Full<List<Profile>>> get(@QueryMap Map<String, String> map);
}
