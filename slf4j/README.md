# 核心知识点
## 一. 基础概念
SLF4J，即简单日志门面（Simple Logging Facade for Java），不是具体的日志解决方案，它只服务于各种各样的日志系统。按照官方的说法，SLF4J 是一个用于日志系统的简单 Facade，允许最终用户在部署其应用时使用其所希望的日志系统。

## 二.  application.yml 文件中对日志的配置
```
logging:
  config: logback.xml
  level:
    com.itcodai.course03.dao: trace
```
logging.config 用来指定项目启动的时候，读取哪个配置文件，这里指定的日志配置文件是根路径下的 logback.xml 文件。

logging.level 用来指定具体的 Mapper 中日志的输出级别。

常用的日志级别按照从高到低依次为：ERROR、WARN、INFO、DEBUG。

## 三. logback.xml 配置文件解析
### 3.1 定义日志输出格式和存储路径
```
<configuration>
    <property name="LOG_PATTERN" value="%date{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n" />
    <property name="FILE_PATH" value="D:/logs/course03/demo.%d{yyyy-MM-dd}.%i.log" />
</configuration>
```
我们来看一下这个定义的含义：首先定义一个格式，命名为 “LOG_PATTERN”，该格式中 %date 表示日期，%thread 表示线程名，%-5level 表示级别从左显示5个字符宽度，%logger{36} 表示 Logger 名字最长36个字符，%msg 表示日志消息，%n 是换行符。

然后再定义名为 FILE_PATH 的文件路径，日志都会存储在该路径下。%i 表示第 i 个文件，当日志文件达到指定大小时，会将日志生成到新的文件里，这里的 i 就是文件索引，日志文件允许的大小可以设置，下面会讲解。这里需要注意的是，不管是 Windows 系统还是 Linux 系统，日志存储的路径必须是绝对路径。

### 3.2 定义控制台输出
```
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <!-- 按照上面配置的LOG_PATTERN来打印日志 -->
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>
</configuration>
```
使用 <appender> 节点设置控制台输出（class="ch.qos.logback.core.ConsoleAppender"）的配置，定义为 CONSOLE。使用上面定义好的输出格式（LOG_PATTERN）来输出，使用 ${} 引用进来即可。

### 3.3 定义日志文件的相关参数
```
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 按照上面配置的FILE_PATH路径来保存日志 -->
            <fileNamePattern>${FILE_PATH}</fileNamePattern>
            <!-- 日志保存15天 -->
            <maxHistory>15</maxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <!-- 单个日志文件的最大，超过则新建日志文件存储 -->
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>

        <encoder>
            <!-- 按照上面配置的LOG_PATTERN来打印日志 -->
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>
</configuration>
```
使用 <appender> 定义一个名为 “FILE” 的文件配置，主要是配置日志文件保存的时间、单个日志文件存储的大小，以及文件保存的路径和日志的输出格式。

### 3.4 定义日志输出级别
```
<configuration>
    <logger name="com.itcodai.course03" level="INFO" />
    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="FILE" />
    </root>
</configuration>
```
有了上面那些定义后，最后我们使用 <logger> 来定义一下项目中默认的日志输出级别，这里定义级别为 INFO，然后针对 INFO 级别的日志，使用 <root> 引用上面定义好的控制台日志输出和日志文件的参数。这样 logback.xml 文件中的配置就设置完了。
