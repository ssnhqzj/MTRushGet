package com.qzj.rush.mt.api.service;


import com.qzj.rush.mt.api.HttpApi;
import com.qzj.rush.mt.api.entity.SrcContent;
import com.qzj.rush.mt.http.entity.RfResult;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtlasEsRemoteService {

    /**
     * 根据id集合获取ES中的资源内容
     * @param ids ID集合
     */
    public RfResult<List<SrcContent>> getResourceContent(List<String> ids) {
        try {
            Map<String, String> headers = new HashMap<>();


            Map<String, Object> params = new HashMap<>();
            Response<RfResult<List<SrcContent>>> response = HttpApi.getEsService()
                    .getResource(headers, params)
                    .execute();
            return response.body();
        } catch (IOException e) {
            throw  new RuntimeException("请求出错");
        }
    }

}
