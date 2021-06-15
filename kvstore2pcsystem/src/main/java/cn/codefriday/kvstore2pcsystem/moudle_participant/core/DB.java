package cn.codefriday.kvstore2pcsystem.moudle_participant.core;

import cn.codefriday.kvstore2pcsystem.moudle_participant.participantCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author codefriday
 * @data 2021/5/22
 */
@Conditional({participantCondition.class})
@Component
public class DB {
    private int removeCnt = 0;
    public HashMap<String, String> database;

    public DB() {
        database = new HashMap<>();
    }

    public void set(String key, String value) {
        database.put(key, value);
    }

    /*
        没有key时返回null
     */
    public String get(String key) {
        return database.get(key);
    }

    public boolean del(LinkedList<String> keys) {
        int cnt = 0;
        for (String key : keys) {
            if (database.remove(key) != null)
                cnt++;
        }
        removeCnt = cnt;
        return true;
    }

    public int getRemoveCnt() {
        return removeCnt;
    }

}
