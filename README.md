# Spring Cloud下基于OAUTH2认证授权的实现


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


### 5 演示

#### 5.1 客户端调用

使用`Postman`向`http://localhost:8080/uaa/oauth/token`发送请求获得`access_token`(admin用户的如`7f9b54d4-fd25-4a2c-a848-ddf8f119230b`)

- admin用户

  ![](https://raw.githubusercontent.com/wiselyman/uaa-zuul/master/images/post-admin.png)

  -------------

  ​

  ![](https://raw.githubusercontent.com/wiselyman/uaa-zuul/master/images/user-admin.png)

  -----------

  ​

  ![](https://raw.githubusercontent.com/wiselyman/uaa-zuul/master/images/demo-admin.png)

  -------

  ​

  ​

- wyf用户

  ![](https://raw.githubusercontent.com/wiselyman/uaa-zuul/master/images/post-wyf.png)

  ----

  ​

  ![](https://raw.githubusercontent.com/wiselyman/uaa-zuul/master/images/user-wyf.png)

  ----

  ​

  ![](https://raw.githubusercontent.com/wiselyman/uaa-zuul/master/images/demo-wyf.png)



#### 5.2 api-gateway中的webapp调用

暂时没有做测试，下次补充。

### 6 注销oauth2
#### 6.1 增加自定义注销`Endpoint`
所谓注销只需将`access_token`和`refresh_token`失效即可，我们模仿`org.springframework.security.oauth2.provider.endpoint.TokenEndpoint`写一个使`access_token`和`refresh_token`失效的`Endpoint`:

```
@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
    @ResponseBody
    public String revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)){
            return "注销成功";
        }else{
            return "注销失败";
        }
    }
}
```

#### 6.2 注销请求方式
![](https://raw.githubusercontent.com/wiselyman/uaa-zuul/master/images/logout.png)