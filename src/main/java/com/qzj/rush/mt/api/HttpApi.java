package com.qzj.rush.mt.api;


import com.qzj.rush.mt.api.intf.EsInterface;
import com.qzj.rush.mt.http.RetrofitManager;

public class HttpApi {

    /**
     * 获取es远程调用服务
     */
    public static EsInterface getEsService() {
        return RetrofitManager.getApiService("http://wwww.test.com", EsInterface.class);
    }
}
