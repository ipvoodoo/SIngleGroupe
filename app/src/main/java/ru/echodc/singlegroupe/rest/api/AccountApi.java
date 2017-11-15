package ru.echodc.singlegroupe.rest.api;

import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.echodc.singlegroupe.rest.model.response.Full;


public interface AccountApi {

  @GET(ApiMethods.ACCOUNT_REGISTER_DEVICE)
  Observable<Full<Integer>> registerDevice(@QueryMap Map<String, String> map);
}
