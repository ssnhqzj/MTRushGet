package com.qzj.rush.mt.exec;

import com.qzj.rush.mt.api.entity.SrcContent;
import com.qzj.rush.mt.api.service.AtlasEsRemoteService;
import com.qzj.rush.mt.http.entity.RfResult;

import java.util.ArrayList;
import java.util.List;

public class JCYS_Main {

    public static void main(String[] args) {
        AtlasEsRemoteService service = new AtlasEsRemoteService();

        List<String> ids = new ArrayList<>();
        ids.add("123456");
        RfResult<List<SrcContent>> rfResult = service.getResourceContent(ids);
        System.out.println("==============");
    }
}
