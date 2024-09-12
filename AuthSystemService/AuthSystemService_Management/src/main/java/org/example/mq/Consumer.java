//package org.example.mq;
//
//import com.alibaba.fastjson2.JSON;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import springfox.documentation.schema.Example;
//
///**
// * @Author 刘文轩
// * @Date 2024/4/16 15:56
// * 订单创建消费者
// */
//@Component
//@RocketMQMessageListener(consumerGroup = ("consumer"), topic = ("consumerTopic"))
//public class Consumer implements RocketMQListener<MessageExt> {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//
//    @Override
//    public void onMessage(MessageExt messageExt) {
//        try{
//            // 获取消息
//            byte[] body = messageExt.getBody();
//            Example example = JSON.parseObject(new String(body), Example.class);
//            // 逻辑
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
