package com.wb.springboot.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者，监听消息
 */
@Component
public class Consumer {

    /**
     * @RabbitListener: 监听消息
     *
     * 消息的 content_type 属性表示消息 body 数据以什么数据格式存储，接收消息除了使用 Message 对象接收消息（包含消息属性等信息）之外，还可直接使用对应类型接收消息 body 内容，但若方法参数类型不正确会抛异常：
     *      application/octet-stream：二进制字节数组存储，使用 byte[]
     *      application/x-java-serialized-object：java 对象序列化格式存储，使用 Object、相应类型（反序列化时类型应该同包同名，否者会抛出找不到类异常）
     *      text/plain：文本数据类型存储，使用 String
     *      application/json：JSON 格式，使用 Object、相应类型
     * @param message
     */
    @RabbitListener(queues = {"queue.news", "admin.queue"})
    public void listen(Message message) {
        //获取消息体
        System.out.println(message.getBody());
        //获取消息头
        System.out.println(message.getMessageProperties());
    }

}
