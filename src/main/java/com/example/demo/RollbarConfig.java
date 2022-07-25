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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Configuration()
@EnableWebMvc
@ComponentScan({
    "com.example.demo",
    "com.rollbar.spring"
})
public class RollbarConfig implements ConfigProvider {

  @Autowired
  private Environment env;
  
  @Bean
  public Rollbar rollbar() {

    ConfigBuilder cb = ConfigBuilder.withAccessToken("");
    Rollbar rb = new Rollbar(provide(cb));
    rb.info("RollbarConfig completing");
    return rb;
  }


  @Override
  public Config provide(ConfigBuilder configBuilder) {

    String accessToken = env.getProperty("x.accessToken");
    String environment = env.getProperty("x.environment");
    String codeVersion = env.getProperty("x.codeVersion");

    System.out.println(accessToken);

    // Reference ConfigBuilder.java for all the properties you can set for Rollbar
    return RollbarSpringConfigBuilder.withAccessToken(accessToken)
        .environment(environment)
        .codeVersion(codeVersion)
        .server(new ServerProvider())
        .person(new PersonProvider())
        .transformer(new PayloadTransformer())
        .build();
  }

}
