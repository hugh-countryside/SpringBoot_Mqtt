# SpringBoot_Mqtt
spring boot 整合mqtt  简单实现了服务端和客户端模块，简单易用

## 下载配置mqtt服务器
1. 推荐`Apollo`，从 http://activemq.apache.org/apollo/download.html 下载windows对应的apollo版本

2. 解压到相应目录，我这里是解压到C:\apache-apollo下，此时会多出一个apache-apollo-1.7.1文件夹

3. 创建broker  打开命令行 在apache-apollo-1.7.1 的bin 目录下执行 apollo create myapollo C:\apache-apollo\broker 

4. 执行运行命令 C:\apache-apollo\broker\bin目录下，执行如下命令：apollo-broker run  

5. 快捷运行 在桌面新建一个txt 复制 cmd /k "cd /d C:\apache-apollo\broker\bin&&apollo-broker.cmd run" 进去，保存之后 改成mqtt.bat
鼠标双击运行mqtt服务器 

6. 用户信息一般在broker 的 etc目录下的 users.properties 初始 账号 一般是 admin   密码是 password

7. 推荐一个mqtt的客户端，打开googel应用商店，搜索MQTTlens，然后安装该插件即可

## mqtt服务配置
1.执行controller 的时候 http://localhost:8085/message/push?topic=hello  或者 http://localhost:8085/message/push?topic=hello/aaa/aa 之类的
 
 客户端订阅的是hello/#，只有相应的topic,控制台才会打印
