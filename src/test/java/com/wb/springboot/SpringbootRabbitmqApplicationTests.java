package com.wb.springboot;

import com.wb.springboot.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void testAdmin() {
        //创建Exchange
        amqpAdmin.declareExchange(new FanoutExchange("admin.exchange"));
        //创建队列
        amqpAdmin.declareQueue(new Queue("admin.queue"));
        /**
         * 设置绑定
         * String destination：名称
         * DestinationType destinationType：类型(QUEUE、EXCHANGE)
         * String exchange：Exchange名称
         * String routingKey：路由键
         * Map<String, Object> arguments：参数
         */
        amqpAdmin.declareBinding(new Binding("admin.queue", Binding.DestinationType.QUEUE, "admin.exchange", "", null));
    }

    @Test
    public void testUser() {
        User user = new User().setId(2)
                .setUsername("李四")
                .setPassword("123456");
        rabbitTemplate.convertAndSend("admin.exchange", "", user);
    }

    @Test
    public void contextLoads() {

        rabbitTemplate.convertAndSend("wb.news", "wb.news", "哈哈哈");

    }

}
