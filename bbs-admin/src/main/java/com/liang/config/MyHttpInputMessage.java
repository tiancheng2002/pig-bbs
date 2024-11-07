package com.liang.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;

    private InputStream body;

    public MyHttpInputMessage(HttpInputMessage inputMessage, Type targetType) throws IOException {
        String jsonObject = new BufferedReader(new InputStreamReader(inputMessage.getBody())).lines().collect(Collectors.joining(System.lineSeparator()));
        //将前端的json对象转换成Map
        Map map = JSONObject.parseObject(jsonObject, Map.class);
        List<?> params = null;
        String typeName = targetType.getTypeName().substring(15,targetType.getTypeName().length()-1);
        try {
            //根据需要的类型将map中的数据转换成List集合
            params = JSONObject.parseArray((String) map.get("params"), Class.forName(targetType.getTypeName().substring(15,targetType.getTypeName().length()-1)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.headers = inputMessage.getHeaders();
        this.body = new ByteArrayInputStream(JSONObject.toJSONString(params).getBytes());
    }

    @Override
    public InputStream getBody() throws IOException {
        return this.body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
