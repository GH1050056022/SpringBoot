package com.lucio.demo.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 生产者模式
     * @param kafkaTemplate
     */
    public MyBean(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // ...

    public void sendMsg() {
        this.kafkaTemplate.send("someTopic", "Hello");
    }

    public void sendMsgAndCallBack(){
        this.kafkaTemplate.send("someTopic", "Hello").addCallback(success -> {
            // 消息发送到的topic
            String topic = null;
            if (success != null) {
                topic = success.getRecordMetadata().topic();
                // 消息发送到的分区
                int partition = success.getRecordMetadata().partition();
                // 消息在分区内的offset
                long offset = success.getRecordMetadata().offset();
                System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
            }else{
                System.out.println("发送消息失败:" + "SendResult为空！");
            }
        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }

    /**
     * 消费者模式
     * @param content
     */
    /*@KafkaListener(topics = "someTopic")
    public void processMessage(String content) {
        // ...
    }*/

}
