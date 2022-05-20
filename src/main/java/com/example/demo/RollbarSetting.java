package com.example.demo;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
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
public class RollbarSetting {

  protected static final String accessToken = "ACCESS_TOKEN";
  protected static final String codeVersion = "38d74530913059f93dbdb36b03787476a9c275bc";
  protected static final String environment = "production";

  
  @Bean
  public Rollbar rollbar() {
    Rollbar rb = new Rollbar(rollbarConfig());
    rb.info("RollbarConfig completing");
    return rb;
  }

  private Config rollbarConfig() {
    RollbarConfig rbConfig = new RollbarConfig();
    return rbConfig.provide(ConfigBuilder.withAccessToken(""));
  }
}
