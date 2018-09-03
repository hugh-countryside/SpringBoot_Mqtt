/**
 * Copyright (C) FileName: MqttReceiveConfig Author:   a9714 Date:     2018/8/27 14:05 Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zhouxy.mqttdemo.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * mqtt客户端简单配置
 */
@Configuration
@IntegrationComponentScan
public class MqttReceiveConfig {

  @Value("${spring.mqtt.username}")
  private String username;

  @Value("${spring.mqtt.password}")
  private String password;

  @Value("${spring.mqtt.url}")
  private String hostUrl;

  @Value("${spring.mqtt.client.id}")
  private String clientId;

  @Value("${spring.mqtt.qos}")
  private Integer qos;

  @Value("${spring.mqtt.default.topic}")
  private String defaultTopic;


  @Bean
  public MqttConnectOptions getMqttConnectOptions(){
    MqttConnectOptions mqttConnectOptions=new MqttConnectOptions();
    mqttConnectOptions.setUserName(username);
    mqttConnectOptions.setPassword(password.toCharArray());
    mqttConnectOptions.setServerURIs(new String[]{hostUrl});
    mqttConnectOptions.setKeepAliveInterval(2);
    return mqttConnectOptions;
  }
  @Bean
  public MqttPahoClientFactory mqttClientFactory() {
    DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
    factory.setConnectionOptions(getMqttConnectOptions());
    return factory;
  }

  /**
   * 接收通道
   * @return
   */
  @Bean
  public MessageChannel mqttInputChannel() {
    return new DirectChannel();
  }

  /**
   * 配置client,监听的topic  监听的 是hello 和 hello次级
   */
  @Bean
  public MessageProducer inbound() {
    MqttPahoMessageDrivenChannelAdapter adapter =
        new MqttPahoMessageDrivenChannelAdapter(clientId+"_inbound", mqttClientFactory(),
            "hello/#");
    adapter.setConverter(new DefaultPahoMessageConverter());
    adapter.setQos(1);
    adapter.setOutputChannel(mqttInputChannel());
    return adapter;
  }

  /**
   * 通过通道获取数据
   * @return
   */
  @Bean
  @ServiceActivator(inputChannel = "mqttInputChannel")
  public MessageHandler handler() {
    return new ClientMessageHandler();
  }
}
