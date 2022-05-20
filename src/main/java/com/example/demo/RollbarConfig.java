package com.example.demo;

import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
import com.rollbar.notifier.config.ConfigProvider;
import com.rollbar.spring.webmvc.RollbarSpringConfigBuilder;

public class RollbarConfig implements ConfigProvider {

  
  @Override
  public Config provide(ConfigBuilder configBuilder) {

    // Set environment and (code) version as part of yuor build process

    // Reference ConfigBuilder.java for all the properties you can set for Rollbar
    return RollbarSpringConfigBuilder.withAccessToken(RollbarSetting.accessToken)
        .environment(RollbarSetting.environment)
        .codeVersion(RollbarSetting.codeVersion)
        .server(new ServerProvider())
        .person(new PersonProvider())
        .transformer(new PayloadTransformer())
        .build();
  }

  }
