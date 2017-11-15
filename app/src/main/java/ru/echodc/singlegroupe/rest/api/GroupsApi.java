package ru.echodc.singlegroupe.rest.api;


import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.echodc.singlegroupe.model.Group;
import ru.echodc.singlegroupe.model.Member;
import ru.echodc.singlegroupe.rest.model.response.BaseItemResponse;
import ru.echodc.singlegroupe.rest.model.response.Full;

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<Full<BaseItemResponse<Member>>> getMembers(@QueryMap Map<String, String> map);

    @GET(ApiMethods.GROUPS_GET_BY_ID)
    Observable<Full<List<Group>>> getById(@QueryMap Map<String, String> map);
}
