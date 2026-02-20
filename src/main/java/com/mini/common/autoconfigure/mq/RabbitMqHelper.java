package com.mini.common.autoconfigure.mq;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import static com.mini.common.constants.Constant.REQUEST_ID_HEADER;

@Slf4j
public class RabbitMqHelper {
    private final RabbitTemplate rabbitTemplate;
    private final ThreadPoolTaskExecutor executor;
    private final MessagePostProcessor processor = new BasicIdMessageProcessor();

    public RabbitMqHelper(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(10);
        //配置最大线程数
        executor.setMaxPoolSize(15);
        //配置队列大小
        executor.setQueueCapacity(99999);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("mq-async-send-handler");

        //设置拒绝策略：线程池已经达到最大线程数，且当前任务数量超过队列容量，则丢弃任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //初始化线程池
        executor.initialize();

    }

    /**
     * 根据exchange和routingKey发送消息
     */
    public <T> void send(String exchange, String routingKey, T message) {
        log.debug("准备发送消息，exchange：{}， RoutingKey：{}， message：{}", exchange, routingKey, message);
        // 1.设置消息标示，用于消息确认，消息发送失败直接抛出异常，交给调用者处理
        String id = UUID.randomUUID().toString(true);
        CorrelationData correlationData = new CorrelationData(id);
        // 2.设置发送超时时间为500毫秒
        rabbitTemplate.setReplyTimeout(500);
        // 3.发送消息,同时设置id
        rabbitTemplate.convertAndSend(exchange, routingKey, message, processor, correlationData);
    }

    /**
     * 根据exchange和routingKey发送消息，并且可以设置延迟时间
     */
    public <T> void sendDelayMessage(String exchange, String routingKey, T message, Duration delay) {
        // 1.设置消息标示，用于消息确认，消息发送失败直接抛出异常，交给调用者处理
        String id = UUID.randomUUID().toString(true);
        CorrelationData correlationData = new CorrelationData(id);
        // 2.设置发送超时时间为500毫秒
        rabbitTemplate.setReplyTimeout(500);
        // 3.发送消息，同时设置消息id
        rabbitTemplate.convertAndSend(exchange, routingKey, message, new DelayedMessageProcessor(delay), correlationData);
    }


    /**
     * 根据exchange和routingKey 异步发送消息，并指定一个延迟时间
     *
     * @param exchange   交换机
     * @param routingKey 路由键
     * @param message    消息
     * @param time       延迟时间
     * @param <T>        消息类型
     */
    public <T> void sendAsync(String exchange, String routingKey, T message, Long time) {
        String requestId = MDC.get(REQUEST_ID_HEADER);
        CompletableFuture.runAsync(() -> {
            try {
                MDC.put(REQUEST_ID_HEADER, requestId);
                // 发送延迟消息
                if (time != null && time > 0) {
                    sendDelayMessage(exchange, routingKey, message, Duration.ofMillis(time));
                } else {
                    send(exchange, routingKey, message);
                }
            } catch (Exception e) {
                log.error("推送消息异常，t:{},", message, e);
            }
        }, executor);
    }

    /**
     * 根据exchange和routingKey 异步发送消息
     *
     * @param exchange   交换机
     * @param routingKey 路由KEY
     * @param message    数据
     * @param <T>        数据类型
     */
    public <T> void sendAsync(String exchange, String routingKey, T message) {
        sendAsync(exchange, routingKey, message, null);
    }
}
