package com.example.demo;

import com.rollbar.api.payload.data.Server;
import com.rollbar.notifier.provider.Provider;

class ServerProvider implements Provider<Server> {

    @Override
    public Server provide() {
      return new Server.Builder()
          .root("com.example")
          .build();
    }
  }