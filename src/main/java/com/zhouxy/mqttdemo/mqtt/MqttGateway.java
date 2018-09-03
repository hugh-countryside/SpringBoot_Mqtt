/**
 * Copyright (C) FileName: MqttGateway Author:   a9714 Date:     2018/8/20 14:34 Description:
 * MqttGateway消息推送接口类 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zhouxy.mqttdemo.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 〈一句话功能简述〉<br> 
 * 〈MqttGateway消息推送接口类〉
 *
 * @author a9714
 * @create 2018/8/20
 * @since 1.0.0
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
  /**
   * data为发送的消息内容，topic为主题。指定topic，qos 默认为 1
   * 则我们的接口可以根据需要，向不同的主题发送消息，
   * 方便灵活应用。如果不指定，则使用默认配置的主题
   */
  void sendToMqtt(String data);

  void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

  void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic,
      @Header(MqttHeaders.QOS) int qos);
}