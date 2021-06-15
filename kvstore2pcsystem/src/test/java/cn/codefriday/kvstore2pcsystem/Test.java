package cn.codefriday.kvstore2pcsystem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author codefriday
 * @data 2021/6/11
 */
public class Test {
    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("k1","v1");
        String s = JSONObject.toJSONString(hashMap);
        System.out.println(s);
        Map<String,String> map = (Map<String, String>)JSON.parseObject(s,new TypeReference<Map<String,String>>(){});
        HashMap<String,String> hashMap1 = (HashMap<String, String>) map;
        System.out.println(hashMap1);
    }
}
