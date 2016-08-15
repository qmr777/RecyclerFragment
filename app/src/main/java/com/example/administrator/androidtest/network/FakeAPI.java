package com.example.administrator.androidtest.network;

import com.example.administrator.androidtest.Bean.FeedbackBean;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/11.
 *
 */
public class FakeAPI {
    public static String GetLvl1(){
        return "{\"code\":\"200\",\"msg\":\"查询成功\",\"result\":[{\"DataID\":2,\"DataText\"" +
                ":\"上传照片不成功(return 400)\",\"OrderBy\":1},{\"DataID\":1,\"DataText\":\"地图不显示\",\"OrderBy\":2}]}";
    }

    public static String GetLvl2(int dataID){
        if(dataID == 2){
            FeedbackBean bean = new FeedbackBean();
            bean.setCode("400");
            bean.setResult(new ArrayList<FeedbackBean.ResultBean>());
            return new Gson().toJson(bean);
        }

        return "{\n" +
                "    \"code\":\"200\",\n" +
                "    \"msg\":\"查询成功\",\n" +
                "    \"result\":[\n" +
                "        {\n" +
                "            \"DataID\":3,\n" +
                "            \"DataText\":\"工地现场\",\n" +
                "            \"OrderBy\":3\n" +
                "        },\n" +
                "        {\n" +
                "            \"DataID\":4,\n" +
                "            \"DataText\":\"工地检查\",\n" +
                "            \"OrderBy\":4\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}
