package com.scienjus.smartqq.tuling;


import com.xson.Json;
import com.xson.JsonArray;
import com.xson.JsonObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TulingApi {



    private static final String key = "3e10777cc66644278496d4c564369821"; // http://www.tuling123.com

    private static HttpClient httpClient = HttpClients.createDefault();

    /**
     * 访问图灵机器人API
     * @param info 聊天内容
     * @param user user字符串，用于确认上下文语境，可以用对方的QQ号。
     * @return 图灵机器人回复的内容
     * @throws IOException 发生网络错误时抛出该异常
     */
    public static TulingResponse getTuling(String info, String user) throws IOException {
        HttpPost httpPost = new HttpPost("http://www.tuling123.com/openapi/api");
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("key",key));
        nameValuePairList.add(new BasicNameValuePair("info",info));
        nameValuePairList.add(new BasicNameValuePair("userid",user));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList,"UTF-8"));
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            JsonObject jsonObject = Json.parseObject(httpResponse.getEntity().getContent());
            Integer code = jsonObject.getInt("code");
            TulingResponse.Type type = TulingResponse.Type.valueOf(code);
            TulingResponse tulingResponse = new TulingResponse();
            tulingResponse.setType(type);
            tulingResponse.setText(jsonObject.getString("text"));
            switch (type){
                case Text:
                    break;
                case Link:
                    tulingResponse.setUrl(jsonObject.getString("url"));
                    break;
                case News:
                    List<News> newsList = new ArrayList<>();
                    JsonArray list = jsonObject.getJsonArray("list");
                    for(int i=0;i<list.size();i++){
                        JsonObject jo = list.getJsonObject(i);
                        newsList.add(new News(jo.getString("article"),jo.getString("source"),jo.getString("icon"),jo.getString("detailurl")));
                    }
                    tulingResponse.setNewsList(newsList);
                    break;
                case Cook:
                    List<Cook> cookList = new ArrayList<>();
                    JsonArray jsonArray = jsonObject.getJsonArray("list");
                    for(int i=0;i<jsonArray.size();i++){
                        JsonObject jo = jsonArray.getJsonObject(i);
                        cookList.add(new Cook(jo.getString("name"),jo.getString("icon"),jo.getString("info"),jo.getString("detailurl")));
                    }
                    tulingResponse.setCookList(cookList);
                    break;
            }
            return tulingResponse;
        }finally {
            httpPost.abort();
        }
    }
}
