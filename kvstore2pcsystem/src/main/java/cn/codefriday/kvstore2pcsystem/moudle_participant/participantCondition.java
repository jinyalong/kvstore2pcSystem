package cn.codefriday.kvstore2pcsystem.moudle_participant;

import cn.codefriday.kvstore2pcsystem.Config;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author codefriday
 * @data 2021/6/5
 */
public class participantCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return Config.getConfig().getMode()==1;
    }
}
