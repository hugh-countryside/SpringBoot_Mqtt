/**
 * Copyright (C) FileName: MessageController Author:   a9714 Date:     2018/8/20 11:28 Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zhouxy.mqttdemo.controller;

import com.zhouxy.mqttdemo.mqtt.MqttGateway;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {


  @Resource
  private MqttGateway mqttGateway;

  private Logger logger = LoggerFactory.getLogger(MessageController.class);


  /**
   * 发送消息
   */
  @GetMapping("/push")
  public String push(String topic) {
    String jsonParam = "{\"content_item_id\":\"{\\\"dcom_entity_id\\\":\\\"qti-1\\\",\\\""
        + "publish_id\\\":\\\"130032511534910600001f\\\",\\\"course_resource_id\\\":\\\"9002511534909200001\\\"}\",\"dcom_id\":\""
        + "dcom:9002511534909200001\",\"end_time\":\"2018-08-2212:05:09\",\"icom_id\":4629,\"rec_id\":11,\"record_data\":\"{"
        + "\\\"icom_record\\\":{\\\"machine_no\\\":1280861024197128019,\\\"frombook\\\":false,\\\"end_time\\\":\\\"2018-08-2212:05:0"
        + "9\\\",\\\"machine_name\\\":\\\"U28E\\\",\\\"version\\\":\\\"4.1.1\\\",\\\"icom_id\\\":4629,\\\"content_item"
        + "_id\\\":\\\"\\\",\\\"start_time\\\":\\\"2018-08-2212:05:09\\\",\\\"ke_record\\\":{\\\"ke_id\\\":\\\"quest"
        + "ion:9002511534909200001-subject:1-qtype:2\\\",\\\"rule_code\\\":0,\\\"ke_time\\\":\\\"2018-08-2212:"
        + "05:09\\\",\\\"export\\\":[{\\\"mode\\\":\\\"单项选择得分\\\",\\\"fact\\\":1,\\\"rule_code\\\":0,\\\"kin"
        + "d\\\":1,\\\"fact_entity\\\":\\\"[\\\\\\\"B\\\\\\\"]\\\",\\\"name\\\":\\\"单项选择得分\\\",\\\"should\\\""
        + ":\\\"\\\",\\\"quota_var\\\":\\\"QtiSingleChoice\\\",\\\"quota_name\\\":\\\"单项选择得分\\\",\\\"should_e"
        + "ntity\\\":\\\"[\\\\\\\"B\\\\\\\"]\\\"},{\\\"mode\\\":\\\"答题时间(ms)\\\",\\\"fact\\\":37734,\\\"rule_code\\"
        + "\":0,\\\"kind\\\":2,\\\"fact_entity\\\":\\\"\\\\\\\"\\\\\\\"\\\",\\\"name\\\":\\\"答题时间(ms)\\\",\\\"should\\\""
        + ":0,\\\"quota_var\\\":\\\"AnswerTime\\\",\\\"quota_name\\\":\\\"答题时间(ms)\\\",\\\"should_entity\\\":\\\"\\\\\\\"\\"
        + "\\\\\"\\\"}]},\\\"dcom_id\\\":\\\"dcom:9002511534909200001\\\",\\\"user_id\\\":1025628,\\\"machine\\\":\\\"U2"
        + "8E\\\",\\\"inst_id\\\":\\\"\\\",\\\"ebag_version\\\":\\\"6.2.1\\\"}}\",\"start_time\":\"2018-08-2212:05:09\",\"user_id\":1231225}";
    String userName = "hugh";
    logger.info("pushMessageToUser :{},{}", jsonParam, userName);
    // 发送消息到mtqq
    mqttGateway.sendToMqtt(jsonParam, topic);
    return "ok";
  }
}