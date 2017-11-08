# RedisUtils

Jedis操作Redis数据库
------- 
http://www.cnblogs.com/DreamDrive/p/5587137.html

CentOS 6.5下Redis安装部署配置指南
------- 
http://www.linuxidc.com/Linux/2016-03/129301.htm

启动redis
------- 
1.启动
$ redis-cli -h host -p port -a password <br> 
2.启动成功如下 <br> 
![image](http://www.runoob.com/wp-content/uploads/2014/11/redis-install1.png)

基础Redis命令
-------  
http://www.runoob.com/redis/redis-tutorial.html

项目依赖
-------  
```
        <!--spring boot -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <!--json -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.31</version>
        </dependency>
        <!-- lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.16</version>
        </dependency>
        <!--jedis -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>2.9.0</version>
        </dependency>
        <dependency>
            <groupId>commons-pool</groupId>
            <artifactId>commons-pool</artifactId>
            <version>1.6</version>
        </dependency>
```

## 作者信息

- 作者博客：http://blog.csdn.net/zhang_yinan

- 作者邮箱：zhangyinan07@hotmail.com
