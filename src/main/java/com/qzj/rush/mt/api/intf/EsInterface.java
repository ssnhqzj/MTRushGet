package com.qzj.rush.mt.api.intf;


import com.qzj.rush.mt.api.entity.SrcContent;
import com.qzj.rush.mt.http.entity.RfResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

import java.util.List;
import java.util.Map;

public interface EsInterface {


    @POST("https://www.gza-e.com/api/consumer/pro/people")
    Call<RfResult<List<SrcContent>>> getResource(@HeaderMap Map<String, String> headers, @Body Map<String, Object> params);
}
