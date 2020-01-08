package com.wb.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot整合 RabbitMQ
 * 1.导入rabbitmq starter
 * 2.@EnableRabbit开启rabbitmq
 *
 * 详见RabbitAutoConfiguration自动配置类
 *      1.RabbitTemplate：执行RabbitMQ消息的操作
 *      2.AmqpAdmin：管理控制，包括创建交换器、队列、绑定规则等
 *
 *  默认RabbitMQ序列化方式为SimpleMessageConverter JDK序列化
 *  可以通过自定义Jackson2JsonMessageConverter设置序列化方式
 *
 */
@SpringBootApplication
@EnableRabbit
public class SpringbootRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqApplication.class, args);
    }

    /**
     * 自定义Jackson2JsonMessageConverter 使用JSON序列化方式
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
