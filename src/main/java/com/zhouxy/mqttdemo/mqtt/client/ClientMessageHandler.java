/**
 * Copyright (C) FileName: ClientMessageHandler Author:   a9714 Date:     2018/8/27 14:17
 * Description: History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zhouxy.mqttdemo.mqtt.client;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author a9714
 * @create 2018/8/27
 * @since 1.0.0
 */
public class ClientMessageHandler implements MessageHandler {


  @Override
  public void handleMessage(Message<?> message) throws MessagingException {
    String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
    if(topic.contains("hello")){
      //具体的客户端业务流程，可以写这儿
      System.out.println("订阅主题是:"+topic);
      System.out.println("消息内容是:"+message.getPayload().toString());
    }
  }
}