package com;

import com.common.config.OSSConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bowen on 16-3-13上午11:49.
 */

@Configuration
@ComponentScan("com.*")
@ServletComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties({OSSConfig.class})
//@SpringBootApplication
public class Application {//extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

//    @Value("${server.port}")
//    private Integer SERVER_PORT;

//    @Bean
//    MessageService mockMessageService() {
//        return new MessageService() {
//            public String getMessage() {
//                return "Hello World!";
//            }
//        };
//    }

    public static void main(String[] args) {
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(cn.bg.mybaties.com.Application.class);
//        MessagePrinter printer = context.getBean(MessagePrinter.class);
//        printer.printMessage();
//        SpringApplication.run(com.Application.class);

        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            logger.warn("Spring Boot use profile is : {}" , profile);
        }


    }

//    @Override
//    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
//        configurableEmbeddedServletContainer.setPort(SERVER_PORT);
//    }
}
