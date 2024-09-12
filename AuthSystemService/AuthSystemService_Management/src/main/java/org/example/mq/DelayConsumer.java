//package org.example.mq;
//
//import com.alibaba.fastjson2.JSON;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import springfox.documentation.schema.Example;
//
//
///**
// * @Author 刘文轩
// * @Date 2024/4/16 19:29
// * 订单超时未支付延时队列
// */
//@Component
//@RocketMQMessageListener(consumerGroup = ("delayConsumer"), topic = ("delayTopic"))
//public class DelayConsumer implements RocketMQListener<MessageExt> {
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void onMessage(MessageExt messageExt) {
//        try{
//            // 获取消息
//            byte[] body = messageExt.getBody();
//            Example example = JSON.parseObject(new String(body), Example.class);
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//    }
//}
