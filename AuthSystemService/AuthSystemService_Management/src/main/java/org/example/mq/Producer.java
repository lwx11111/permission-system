//package org.example.mq;
//
//
//import com.alibaba.fastjson2.JSON;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//import springfox.documentation.schema.Example;
//
//import java.nio.charset.StandardCharsets;
//
///**
// * @Author 刘文轩
// * @Date 2024/4/16 15:56
// */
//@Component
//public class Producer {
//
//    @Autowired
//    private RocketMQTemplate rocketMqTemplate;
//
//    public String sendMessage(Example example) throws Exception {
//        // 发送创建消息
//        String json = JSON.toJSONString(example);
//        SendResult sendResult =  rocketMqTemplate.syncSend("consumerTopic",json);
//        // 发送延时消息 半个小时
//        SendResult delayResult = rocketMqTemplate.syncSend("delayTopic",
//                MessageBuilder.withPayload(sendResult.getMsgId().getBytes(StandardCharsets.UTF_8)).build(),
//                3000, 16);
//        return sendResult.getMsgId();
//    }
//}
