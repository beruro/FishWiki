package com.hanafish.hanawiki.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.hanafish")
@SpringBootApplication
@MapperScan("com.hanafish.hanawiki.mapper")
@EnableScheduling
@EnableAsync
public class HanawikiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(HanawikiApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HanawikiApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
