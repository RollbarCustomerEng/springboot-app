package com.example.demo;

import com.rollbar.notifier.Rollbar;

import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
import com.rollbar.notifier.config.ConfigProvider;
import com.rollbar.spring.webmvc.RollbarSpringConfigBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration()
@EnableWebMvc
@ComponentScan({
    "com.example.demo",
    "com.rollbar.spring"
})
public class RollbarConfig implements ConfigProvider {

  
  @Bean
  public Rollbar rollbar() {

    ConfigBuilder cb = ConfigBuilder.withAccessToken("");
    Rollbar rb = new Rollbar(provide(cb));
    rb.info("RollbarConfig completing");
    return rb;
  }


  @Override
  public Config provide(ConfigBuilder configBuilder) {

    String accessToken = System.getenv("access_token");
    String environment = System.getenv("environment");
    String codeVersion = System.getenv("code_version");

    // Reference ConfigBuilder.java for all the properties you can set for Rollbar
    return RollbarSpringConfigBuilder.withAccessToken(accessToken)
        .enabled(true)
        .environment(environment)
        .codeVersion(codeVersion)
        .server(new ServerProvider())
        .person(new PersonProvider())
        .transformer(new PayloadTransformer())
        .build();
  }

  }
