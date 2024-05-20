/* 根据 Apache 许可证 2.0 版（“许可证”）获得许可；
 * 除非遵守许可证，否则您不得使用此文件。
 * 您可以在以下位置获取许可证副本：
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可证分发是在“按原样”基础上分发的，
 * 不提供任何类型的明示或暗示的保证或条件。
 * 请参阅许可证以了解管理权限的特定语言和
 * 许可证下的限制。
 */
package org.flowable.ui.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Filip Hrisafov
 */
public class FlowableUiAppEventRegistryCondition extends SpringBootCondition
    implements AutoConfigurationImportFilter, BeanFactoryAware, Condition, EnvironmentAware {

    // 这是故意在 flowable-ui-task-app 中实现的，因为只需要启用/禁用对开箱即用应用程序的依赖
    // 如果使用任务 UI Spring Boot Starter，那么人们需要为事件注册表添加适当的 Kafka、JMS 或 RabbitMQ

    protected BeanFactory beanFactory;

    protected Environment environment;

    @Override
    public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
        ConditionEvaluationReport report = ConditionEvaluationReport.find(this.beanFactory);
        Map<String, ConditionOutcome> conditions = getConditionOutcomes();

        ConditionOutcome[] outcomes = getOutcomes(autoConfigurationClasses, conditions);
        boolean[] match = new boolean[outcomes.length];
        for (int i = 0; i < outcomes.length; i++) {
            match[i] = (outcomes[i] == null || outcomes[i].isMatch());
            if (!match[i] && outcomes[i] != null) {
                logOutcome(autoConfigurationClasses[i], outcomes[i]);
                if (report != null) {
                    report.recordConditionEvaluation(autoConfigurationClasses[i], this, outcomes[i]);
                }
            }
        }
        return match;
    }

    protected Map<String, ConditionOutcome> getConditionOutcomes() {
        boolean jmsEnabled = environment.getProperty("flowable.task.app.jms-enabled", Boolean.class, false);
        boolean kafkaEnabled = environment.getProperty("flowable.task.app.kafka-enabled", Boolean.class, false);
        boolean rabbitEnabled = environment.getProperty("flowable.task.app.rabbit-enabled", Boolean.class, false);
        Map<String, ConditionOutcome> conditions = new HashMap<>();

        if (!jmsEnabled) {
            conditions.put("org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration",
                ConditionOutcome.noMatch("Property flowable.task.app.jms-enabled was not set to true")
            );
        }

        if (!kafkaEnabled) {
            conditions.put("org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration",
                ConditionOutcome.noMatch("Property flowable.task.app.kafka-enabled was not set to true")
            );
        }

        if (!rabbitEnabled) {
            conditions.put("org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration",
                ConditionOutcome.noMatch("Property flowable.task.app.rabbit-enabled was not set to true")
            );
        }
        return conditions;
    }

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return ConditionOutcome.noMatch(ConditionMessage.empty());
    }

    protected ConditionOutcome[] getOutcomes(String[] autoConfigurationClasses, Map<String, ConditionOutcome> conditionOutcomes) {
        ConditionOutcome[] outcomes = new ConditionOutcome[autoConfigurationClasses.length];

        for (int i = 0; i < autoConfigurationClasses.length; i++) {
            outcomes[i] = conditionOutcomes.get(autoConfigurationClasses[i]);
        }

        return outcomes;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
