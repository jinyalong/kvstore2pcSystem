package cn.codefriday.kvstore2pcsystem.moudle_coordinator.core;

import cn.codefriday.kvstore2pcsystem.moudle_coordinator.coordinatorCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author codefriday
 * @data 2021/5/24
 */
//@Conditional({coordinatorCondition.class})
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling
public class TimingDetectionNode {
    //每隔30秒刷新一次节点
    @Scheduled(cron = "0/30 * * * * ?")
    private void checkNodes() {
        NodeManager.checkAlive();
    }
}
