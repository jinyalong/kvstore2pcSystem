package cn.codefriday.kvstore2pcsystem.moudle_coordinator.controller;


import cn.codefriday.kvstore2pcsystem.moudle_coordinator.coordinatorCondition;
import cn.codefriday.kvstore2pcsystem.moudle_coordinator.core.NodeManager;
import cn.codefriday.kvstore2pcsystem.moudle_coordinator.core.Participant;
import cn.codefriday.kvstore2pcsystem.moudle_coordinator.util.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author codefriday
 * @data 2021/5/21
 */
@Conditional({coordinatorCondition.class})
@RestController
@RequestMapping("/coordinate")
public class CoordinateController {
    @PostMapping("/heartbeat")
    public String heartbeat(HttpServletRequest request) {
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        boolean flag = NodeManager.refreshAliveNode(ip, port);
        return flag ? "true":"false";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request) {
        System.out.println("进入Register方法");
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        Participant participant = new Participant();
        participant.setIp(ip);
        participant.setPort(port);
        ArrayList<Participant> aliveParticipantList = NodeManager.getAliveParticipantList();
        Participant participant1 = null;
        for (Participant p : aliveParticipantList) {
            if(!p.getIp().equals(ip)){
                participant1 = p;
                break;
            }
        }
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        NodeManager.participants.put(participant,new Date());
        String s = HttpClientUtil.doPost("http://"+participant1.getIp()+":"+participant1.getPort()+"/participant/copy",stringStringHashMap);
        System.out.println("Register接口返回的结果："+s);
        return s;
    }
}
