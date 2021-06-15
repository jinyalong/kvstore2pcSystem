package cn.codefriday.kvstore2pcsystem.moudle_participant.core;

import cn.codefriday.kvstore2pcsystem.moudle_participant.participantCondition;
import cn.codefriday.kvstore2pcsystem.moudle_participant.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author codefriday
 * @data 2021/6/5
 */
@Conditional({participantCondition.class})
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class HeartBeatToCoo {
    private boolean flag = false;
    @Autowired
    Participant participant;

    @Autowired
    MainServer server;

    @Scheduled(cron = "0/5 * * * * ?")
    private void heartBeat(){
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("ip", participant.getIp());
        stringStringHashMap.put("port", participant.getPort());
        String s = HttpClientUtil.doPost("http://" + participant.getCo_addr() + ":" + "8080" + "/coordinate/heartbeat", stringStringHashMap);
        System.out.println("心跳结果："+s);
        if(s.equals("true")&&!flag){
            System.out.println("开始复制~");
            String s1 = HttpClientUtil.doPost("http://" + participant.getCo_addr() + ":" + "8080" + "/coordinate/register", stringStringHashMap);
            System.out.println("[DEBUG]返回的字符串："+s1);
            DB database1 = JSONObject.parseObject(s1, DB.class);
            Set<Map.Entry<String, String>> entries = database1.database.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                String key = entry.getKey();
                String value = entry.getValue();
                server.getDatabase().set(key,value);
                System.out.println(key+"----"+value+"----"+server.getDatabase().database.size()+"===="+server.getDatabase().database);
            }
            flag = true;
        }
    }
}
