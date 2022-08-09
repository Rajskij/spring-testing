package com.haidukov.consumer;

import com.haidukov.consumer.config.DbConfigure;
import com.haidukov.consumer.config.JmsConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@PropertySource("classpath:application.properties")

@ContextConfiguration(
        classes = { DbConfigure.class, JmsConfig.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest(classes = ConsumerApplication.class)
public class ConsumerApplicationTest {
    @Test
    void contextLoads() {
    }
}
