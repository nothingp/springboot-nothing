# Kotlin版本 的 Spring Cloud下基于OAUTH2认证授权的实现  


![](https://github.com/nothingp/springboot-nothing/blob/master/images/architecture.png)


在`Spring Cloud`需要使用`OAUTH2`来实现多个微服务的统一认证授权，通过向`OAUTH服务`发送某个类型的`grant type`进行集中认证和授权，从而获得`access_token`，而这个token是受其他微服务信任的，我们在后续的访问可以通过`access_token`来进行，从而实现了微服务的统一认证授权。

本示例提供了四大部分：

- `discovery-service`:服务注册和发现的基本模块（暂时未实现）
- `ucenter`:OAUTH2认证授权中心及用户中心
- `backend-service`:一个模拟后台的服务，用户验证通过网关后，能直接获取用户信息
- `api-gateway`:边界网关(所有微服务都在它之后)，未来需要实现的是异构系统的接入

OAUTH2中的角色：

- `Resource Server`:被授权访问的资源
- `Authotization Server`：OAUTH2认证授权中心
- `Resource Owner`： 用户
- `Client`：使用API的客户端(如Android 、IOS、web app)

Grant Type：

- `Authorization Code`:用在服务端应用之间
- `Implicit`:用在移动app或者web app(这些app是在用户的设备上的，如在手机上调起微信来进行认证授权)
- `Resource Owner Password Credentials(password)`:应用直接都是受信任的(都是由一家公司开发的，本例子使用)
- `Client Credentials`:用在应用API访问。



后续升级计划：

- 适配现有流程的php产品，如wordpress、cms等，实现用户提议统一
- 旧的java架构快速接入
- sprin cloud config 
- api gateway 的可配置性：webservice、websocket等通过简单的界面配置，即可生效
- 分布式的链路跟踪
- discovery-service（服务发现） 与 docker 的集合


### 演示

#### 客户端调用

使用`Postman`向`http://localhost:8080/uaa/oauth/token`发送请求获得`access_token`(admin用户的如`76bf72fa-a391-42fb-b6f0-0216dcf40d7e`)

- admin用户

  ![](https://github.com/nothingp/springboot-nothing/blob/master/images/step1.jpeg)

  -------------

- 通过网关+access_token 获取用户信息

  ![](https://github.com/nothingp/springboot-nothing/blob/master/images/step2.jpeg)

  -------------
  
- 通过网关+access_token 调用后端的微服务

  ![](https://github.com/nothingp/springboot-nothing/blob/master/images/step3.jpeg)

  -------------
  
- 通过网关+access_token 调用后端的微服务获取用户信息，即多端的用户信息是贯通的

  ![](https://github.com/nothingp/springboot-nothing/blob/master/images/step4.jpeg)

  -------------